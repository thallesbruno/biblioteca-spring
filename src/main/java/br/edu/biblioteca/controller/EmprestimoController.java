package br.edu.biblioteca.controller;

import br.edu.biblioteca.entity.Emprestimo;
import br.edu.biblioteca.service.EmprestimoService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/emprestimos")
public class EmprestimoController {

    private final EmprestimoService service;

    public EmprestimoController(EmprestimoService service) {
        this.service = service;
    }

    @GetMapping
    public List<Emprestimo> listar() {
        return service.listar();
    }

    @GetMapping("/ativos")
    public List<Emprestimo> listarAtivos() {
        return service.listarAtivos();
    }

    @GetMapping("/{id}")
    public Emprestimo buscar(@PathVariable Long id) {
        return service.buscarPorId(id);
    }
}
