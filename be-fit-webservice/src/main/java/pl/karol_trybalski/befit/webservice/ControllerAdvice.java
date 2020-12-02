package pl.karol_trybalski.befit.webservice;

import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;
import pl.karol_trybalski.befit.domain.exception.DomainError;
import pl.karol_trybalski.befit.domain.exception.DomainException;
import pl.karol_trybalski.befit.webservice.util.ResponseUtils;

import javax.persistence.EntityNotFoundException;

@Slf4j
@RestControllerAdvice
public class ControllerAdvice {

    @ExceptionHandler(NoHandlerFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ApiResponse<?> handleNoHandlerFound(NoHandlerFoundException e) {
        log.error("An exception occurred!", e);
        return ApiResponse.from(DomainError.METHOD_NOT_FOUND);
    }

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    @ResponseStatus(HttpStatus.METHOD_NOT_ALLOWED)
    public ApiResponse<?> handleHttpRequestMethodNotSupported(HttpRequestMethodNotSupportedException e) {
        log.error("An exception occurred!", e);
        return ApiResponse.from(DomainError.METHOD_NOT_ALLOWED);
    }

    @ExceptionHandler(EntityNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ApiResponse<?> handleEntityNotFound(EntityNotFoundException e) {
        log.error("An exception occurred!", e);
        return ApiResponse.from(DomainError.ENTITY_NOT_FOUND);
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public ApiResponse<?> handleDataIntegrityViolation(DataIntegrityViolationException e) {
        log.error("An exception occurred!", e);
        return ApiResponse.from(DomainError.DATA_INTEGRITY_VIOLATION);
    }

    @ExceptionHandler(DomainException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity<ApiResponse<?>> handleDomainException(DomainException e) {
        log.error("An exception occurred!", e);
        return new ResponseEntity<>(
          ApiResponse.from(e.error),
          ResponseUtils.getHttpStatus(e.error)
        );
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ApiResponse<?> handle(Exception e) {
        log.error("An exception occurred!", e);
        return ApiResponse.from(DomainError.GENERAL);
    }

    // TODO
    // add more exception handlers

}
