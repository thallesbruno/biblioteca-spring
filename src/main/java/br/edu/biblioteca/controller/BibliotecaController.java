package br.edu.biblioteca.controller;

import br.edu.biblioteca.dto.MensagemResponse;
import br.edu.biblioteca.dto.PedidoEmprestimoRequest;
import br.edu.biblioteca.service.BibliotecaService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/biblioteca")
public class BibliotecaController {

    private final BibliotecaService service;

    public BibliotecaController(BibliotecaService service) {
        this.service = service;
    }

    @PostMapping("/emprestar")
    public MensagemResponse emprestar(@RequestBody @Valid PedidoEmprestimoRequest request) {
        String mensagem = service.processarPedidoEmprestimo(request.getClienteId(), request.getLivroId());
        return new MensagemResponse(mensagem);
    }

    @PostMapping("/devolver/{emprestimoId}")
    public MensagemResponse devolver(@PathVariable Long emprestimoId) {
        String mensagem = service.processarDevolucao(emprestimoId);
        return new MensagemResponse(mensagem);
    }
}
