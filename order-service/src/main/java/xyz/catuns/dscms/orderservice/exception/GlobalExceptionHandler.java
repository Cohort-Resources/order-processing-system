package xyz.catuns.dscms.orderservice.exception;

import jakarta.validation.ConstraintViolationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;
import java.util.Map;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {


    @ExceptionHandler(Exception.class)
    public ResponseEntity<ProblemDetail>  handleException(Exception ex) {
        log.debug("[{}] {}", ex.getClass().getSimpleName(), ex.getMessage());
        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(
                HttpStatus.INTERNAL_SERVER_ERROR,
                ex.getMessage()
        );
        return ResponseEntity.status(problemDetail.getStatus()).body(problemDetail);
    }

    @ExceptionHandler(InvalidRequestException.class)
    public ResponseEntity<ProblemDetail>  handleInvalidRequestException(InvalidRequestException ex) {

        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(
                HttpStatus.BAD_REQUEST,
                ex.getMessage()
        );

        problemDetail.setProperty("cause", "failed because of " + ex.getCauseName());
        return ResponseEntity.status(problemDetail.getStatus()).body(problemDetail);
    }

    @ExceptionHandler(ControllerException.class)
    public ResponseEntity<ProblemDetail>  handleControllerException(ControllerException ex) {
        log.debug("[ControllerException - {}] {}", ex.getClass().getSimpleName(), ex.toString());

//        problemDetail.setProperty("errors", List.of("password must be 10 chars", "email must be valid"));
        return ResponseEntity.status(ex.getStatusCode()).body(ex.getBody());
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<ProblemDetail>  handleConstraintViolationException(ConstraintViolationException ex) {
        log.debug("[{}] {}", ex.getClass().getSimpleName(), ex.toString());

        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(
                HttpStatus.BAD_REQUEST,
                ex.getMessage()
        );

        problemDetail.setProperty("errors", ex.getConstraintViolations());

        return ResponseEntity.status(problemDetail.getStatus()).body(problemDetail);
    }


    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ProblemDetail>  handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
        log.debug("[{}] {}", ex.getClass().getSimpleName(), ex.toString());

        ProblemDetail body = ex.getBody();
        FieldError fieldError = ex.getBindingResult().getFieldError();
        body.setProperty("errors", List.of(fieldError.getDefaultMessage()));
        return ResponseEntity.status(ex.getStatusCode()).body(body);
    }

}
