package pl.karol_trybalski.befit.webservice;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;
import pl.karol_trybalski.befit.webservice.exception.DomainError;

import javax.persistence.EntityNotFoundException;

@RestControllerAdvice
public class ControllerAdvice {

    private static final Logger LOGGER = LoggerFactory.getLogger(ControllerAdvice.class);

    @ExceptionHandler(NoHandlerFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ApiResponse<?> handleNoHandlerFound(NoHandlerFoundException e) {
        LOGGER.error("An exception occurred!", e);
        return ApiResponse.from(DomainError.METHOD_NOT_FOUND);
    }

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    @ResponseStatus(HttpStatus.METHOD_NOT_ALLOWED)
    public ApiResponse<?> handleHttpRequestMethodNotSupported(HttpRequestMethodNotSupportedException e) {
        LOGGER.error("An exception occurred!", e);
        return ApiResponse.from(DomainError.METHOD_NOT_ALLOWED);
    }

    @ExceptionHandler(EntityNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ApiResponse<?> handleEntityNotFound(EntityNotFoundException e) {
        LOGGER.error("An exception occurred!", e);
        return ApiResponse.from(DomainError.ENTITY_NOT_FOUND);
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public ApiResponse<?> handleDataIntegrityViolation(DataIntegrityViolationException e) {
        LOGGER.error("An exception occurred!", e);
        return ApiResponse.from(DomainError.DATA_INTEGRITY_VIOLATION);
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ApiResponse<?> handle(Exception e) {
        LOGGER.error("An exception occurred!", e);
        return ApiResponse.from(DomainError.GENERAL);
    }

    // TODO
    // add more exception handlers

}
