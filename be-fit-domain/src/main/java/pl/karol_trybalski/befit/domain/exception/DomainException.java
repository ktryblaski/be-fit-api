package pl.karol_trybalski.befit.domain.exception;

public class DomainException extends RuntimeException {

    public final DomainError error;

    public DomainException(DomainError error) {
        this.error = error;
    }

}
