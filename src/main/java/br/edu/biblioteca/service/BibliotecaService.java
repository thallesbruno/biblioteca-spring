package br.edu.biblioteca.service;

import br.edu.biblioteca.entity.Emprestimo;
import org.springframework.stereotype.Service;

@Service
public class BibliotecaService {

    private final EmprestimoService emprestimoService;

    public BibliotecaService(EmprestimoService emprestimoService) {
        this.emprestimoService = emprestimoService;
    }

    public String processarPedidoEmprestimo(Long clienteId, Long livroId) {
        Emprestimo emprestimo = emprestimoService.emprestar(clienteId, livroId);

        return String.format(
                "Empréstimo realizado com sucesso. O livro '%s' foi emprestado ao cliente %s. Data prevista para devolução: %s.",
                emprestimo.getLivro().getTitulo(),
                emprestimo.getCliente().getNome(),
                emprestimo.getDataPrevistaDevolucao()
        );
    }

    public String processarDevolucao(Long emprestimoId) {
        Emprestimo emprestimo = emprestimoService.devolver(emprestimoId);

        return String.format(
                "Devolução registrada com sucesso. O livro '%s' foi devolvido por %s. Agradecemos pela utilização da biblioteca.",
                emprestimo.getLivro().getTitulo(),
                emprestimo.getCliente().getNome()
        );
    }
}
