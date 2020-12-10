package io.github.emfsilva.clienteApi.exceptions;

import io.github.emfsilva.clienteApi.exceptions.type.DataIntegrationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;


// promove um tratamento de exceção unificado em toda a aplicação, ou seja
// todas as exceptions lançadas irão passae por esse controller.
@ControllerAdvice
public class ResourseExceptionHandler {

    @ExceptionHandler(DataIntegrationException.class)
    public ResponseEntity<StandardError> dataError(DataIntegrationException e, HttpServletRequest request) {
        // Monta um objeto do tipo StandarError informando os dados da Exception
        StandardError error = new StandardError(HttpStatus.BAD_REQUEST.value(), e.getMessage(), System.currentTimeMillis());

        // Envia uma resposta de erro ao usuário contento uma mensagem de erro
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }
}
