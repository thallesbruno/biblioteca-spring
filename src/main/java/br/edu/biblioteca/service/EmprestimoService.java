package br.edu.biblioteca.service;

import br.edu.biblioteca.entity.*;
import br.edu.biblioteca.exception.RegraNegocioException;
import br.edu.biblioteca.exception.RecursoNaoEncontradoException;
import br.edu.biblioteca.repository.EmprestimoRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
public class EmprestimoService {

    private final EmprestimoRepository repository;
    private final ClienteService clienteService;
    private final LivroService livroService;

    public EmprestimoService(EmprestimoRepository repository, ClienteService clienteService, LivroService livroService) {
        this.repository = repository;
        this.clienteService = clienteService;
        this.livroService = livroService;
    }

    public List<Emprestimo> listar() {
        return repository.findAll();
    }

    public List<Emprestimo> listarAtivos() {
        return repository.findByStatus(StatusEmprestimo.ATIVO);
    }

    public Emprestimo buscarPorId(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Empréstimo não encontrado."));
    }

    @Transactional
    public Emprestimo emprestar(Long clienteId, Long livroId) {
        Cliente cliente = clienteService.buscarPorId(clienteId);
        Livro livro = livroService.buscarPorId(livroId);

        if (!livro.estaDisponivel()) {
            throw new RegraNegocioException("Não foi possível realizar o empréstimo: o livro está indisponível.");
        }

        livro.reduzirEstoque();

        Emprestimo emprestimo = new Emprestimo(
                cliente,
                livro,
                LocalDate.now(),
                LocalDate.now().plusDays(7)
        );

        return repository.save(emprestimo);
    }

    @Transactional
    public Emprestimo devolver(Long emprestimoId) {
        Emprestimo emprestimo = buscarPorId(emprestimoId);

        if (emprestimo.getStatus() == StatusEmprestimo.DEVOLVIDO) {
            throw new RegraNegocioException("Este empréstimo já foi devolvido anteriormente.");
        }

        emprestimo.getLivro().devolverAoEstoque();
        emprestimo.registrarDevolucao(LocalDate.now());

        return repository.save(emprestimo);
    }
}
