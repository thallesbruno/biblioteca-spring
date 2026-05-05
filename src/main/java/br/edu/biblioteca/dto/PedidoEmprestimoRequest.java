package br.edu.biblioteca.dto;

import jakarta.validation.constraints.NotNull;

public class PedidoEmprestimoRequest {

    @NotNull
    private Long clienteId;

    @NotNull
    private Long livroId;

    public Long getClienteId() { return clienteId; }
    public Long getLivroId() { return livroId; }

    public void setClienteId(Long clienteId) { this.clienteId = clienteId; }
    public void setLivroId(Long livroId) { this.livroId = livroId; }
}
