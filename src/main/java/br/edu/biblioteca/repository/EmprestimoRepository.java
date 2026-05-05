package br.edu.biblioteca.repository;

import br.edu.biblioteca.entity.Emprestimo;
import br.edu.biblioteca.entity.StatusEmprestimo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EmprestimoRepository extends JpaRepository<Emprestimo, Long> {
    List<Emprestimo> findByStatus(StatusEmprestimo status);
}
