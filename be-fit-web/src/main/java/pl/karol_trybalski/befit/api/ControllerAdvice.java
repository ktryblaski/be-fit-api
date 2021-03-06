package pl.karol_trybalski.befit.api;

import com.google.common.base.MoreObjects;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;
import pl.karol_trybalski.befit.api.util.ResponseUtils;
import pl.karol_trybalski.befit.domain.exception.DomainError;
import pl.karol_trybalski.befit.domain.exception.DomainException;

import javax.persistence.EntityNotFoundException;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collector;
import java.util.stream.Collectors;

@Slf4j
@RestControllerAdvice
public class ControllerAdvice {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiResponse<Map<String, String>> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        log.error("An exception occurred!", e);

        final Map<String, String> errors = e.getFieldErrors().stream().collect(
          Collectors.toMap(FieldError::getField, fe -> MoreObjects.firstNonNull(fe.getDefaultMessage(), ""))
        );
        return ApiResponse.from(errors, DomainError.INVALID_DATA);
    }

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

}
