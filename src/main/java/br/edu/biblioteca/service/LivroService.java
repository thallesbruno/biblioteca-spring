package br.edu.biblioteca.service;

import br.edu.biblioteca.entity.Livro;
import br.edu.biblioteca.exception.RecursoNaoEncontradoException;
import br.edu.biblioteca.repository.LivroRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LivroService {

    private final LivroRepository repository;

    public LivroService(LivroRepository repository) {
        this.repository = repository;
    }

    public Livro salvar(Livro livro) {
        return repository.save(livro);
    }

    public List<Livro> listar() {
        return repository.findAll();
    }

    public Livro buscarPorId(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Livro não encontrado."));
    }

    public Livro atualizar(Long id, Livro dados) {
        Livro livro = buscarPorId(id);
        livro.setTitulo(dados.getTitulo());
        livro.setAutor(dados.getAutor());
        livro.setIsbn(dados.getIsbn());
        livro.setQuantidadeDisponivel(dados.getQuantidadeDisponivel());
        return repository.save(livro);
    }

    public void excluir(Long id) {
        Livro livro = buscarPorId(id);
        repository.delete(livro);
    }
}
