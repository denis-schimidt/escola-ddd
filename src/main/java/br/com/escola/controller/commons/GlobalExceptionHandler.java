package br.com.escola.controller.commons;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<ErrorResponse> handleConstraintViolationException(ConstraintViolationException exception, HttpServletRequest request) {

        try {
            List<String> messages = exception.getConstraintViolations()
                    .stream()
                    .map(ConstraintViolation::getMessage)
                    .collect(Collectors.toList());

            return new ResponseEntity<>(ErrorResponse.from(messages), BAD_REQUEST);

        } catch (Exception e) {
            return new ResponseEntity<>(ErrorResponse.from(exception.getMessage()), INTERNAL_SERVER_ERROR);
        }
    }

    static class ErrorResponse {
        private List<String> messagensDeErro;

        public List<String> getMessagensDeErro() {
            return messagensDeErro;
        }

        private ErrorResponse(List<String> messagensDeErro) {
            this.messagensDeErro = messagensDeErro;
        }

        static ErrorResponse from(List<String> messages) {
            return new ErrorResponse(messages);
        }

        static ErrorResponse from(String message) {
            return new ErrorResponse(Arrays.asList(message));
        }
    }
}
