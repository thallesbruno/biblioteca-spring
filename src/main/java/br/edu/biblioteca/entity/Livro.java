package br.edu.biblioteca.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PositiveOrZero;

@Entity
public class Livro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String titulo;

    @NotBlank
    private String autor;

    @NotBlank
    @Column(unique = true)
    private String isbn;

    @PositiveOrZero
    private Integer quantidadeDisponivel;

    public Livro() {}

    public Livro(String titulo, String autor, String isbn, Integer quantidadeDisponivel) {
        this.titulo = titulo;
        this.autor = autor;
        this.isbn = isbn;
        this.quantidadeDisponivel = quantidadeDisponivel;
    }

    public boolean estaDisponivel() {
        return quantidadeDisponivel != null && quantidadeDisponivel > 0;
    }

    public void reduzirEstoque() {
        if (!estaDisponivel()) {
            throw new IllegalStateException("Livro indisponível para empréstimo.");
        }
        quantidadeDisponivel--;
    }

    public void devolverAoEstoque() {
        quantidadeDisponivel++;
    }

    public Long getId() { return id; }
    public String getTitulo() { return titulo; }
    public String getAutor() { return autor; }
    public String getIsbn() { return isbn; }
    public Integer getQuantidadeDisponivel() { return quantidadeDisponivel; }

    public void setId(Long id) { this.id = id; }
    public void setTitulo(String titulo) { this.titulo = titulo; }
    public void setAutor(String autor) { this.autor = autor; }
    public void setIsbn(String isbn) { this.isbn = isbn; }
    public void setQuantidadeDisponivel(Integer quantidadeDisponivel) { this.quantidadeDisponivel = quantidadeDisponivel; }
}
