package br.edu.biblioteca.exception;

import br.edu.biblioteca.dto.MensagemResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(RecursoNaoEncontradoException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public MensagemResponse tratarNaoEncontrado(RecursoNaoEncontradoException ex) {
        return new MensagemResponse(ex.getMessage());
    }

    @ExceptionHandler(RegraNegocioException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public MensagemResponse tratarRegraNegocio(RegraNegocioException ex) {
        return new MensagemResponse(ex.getMessage());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public MensagemResponse tratarValidacao(MethodArgumentNotValidException ex) {
        return new MensagemResponse("Há dados obrigatórios ou inválidos na requisição.");
    }
}
