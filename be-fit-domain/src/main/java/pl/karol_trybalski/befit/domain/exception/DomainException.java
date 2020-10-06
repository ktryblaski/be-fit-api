package pl.karol_trybalski.befit.domain.exception;

public class DomainException extends RuntimeException {

    public final DomainError error;

    public DomainException(DomainError error) {
        this.error = error;
    }

    public DomainException(Throwable cause, DomainError error) {
        super(cause);
        this.error = error;
    }
}
