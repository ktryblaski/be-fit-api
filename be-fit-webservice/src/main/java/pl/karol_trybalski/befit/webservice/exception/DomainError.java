package pl.karol_trybalski.befit.webservice.exception;

public enum DomainError {

    GENERAL("common.general"),
    METHOD_NOT_FOUND("common.method_not_found"),
    METHOD_NOT_ALLOWED("common.method_not_allowed"),
    ENTITY_NOT_FOUND("common.entity_not_found"),
    DATA_INTEGRITY_VIOLATION("domain.data_integrity_violation")
    ;

    public final String code;

    DomainError(String code) {
        this.code = code;
    }
}
