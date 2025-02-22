package walab.nanuri.auth.exception.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import walab.nanuri.auth.exception.FailedHisnetLoginException;
import walab.nanuri.base.response.ExceptionResponse;

@RestControllerAdvice
public class AuthExceptionController {
    @ExceptionHandler(FailedHisnetLoginException.class)
    public ResponseEntity<ExceptionResponse> handleFailedHisnetLoginException(FailedHisnetLoginException e) {
        ExceptionResponse response = ExceptionResponse.builder()
                .error(e.getStatus().toString())
                .message(e.getMessage())
                .build();
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
    }
}