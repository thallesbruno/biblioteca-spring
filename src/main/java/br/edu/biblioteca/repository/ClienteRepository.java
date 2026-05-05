package br.edu.biblioteca.repository;

import br.edu.biblioteca.entity.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {
}
