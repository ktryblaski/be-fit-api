package pl.karol_trybalski.befit.domain.exception;

public enum DomainError {

    GENERAL("common.general"),
    METHOD_NOT_FOUND("common.method_not_found"),
    METHOD_NOT_ALLOWED("common.method_not_allowed"),
    ENTITY_NOT_FOUND("common.entity_not_found"),
    DATA_INTEGRITY_VIOLATION("common.data_integrity_violation"),

    // TODO
    BAD_DATA("common.bad_data"),

    MEAL_TEMPLATE_ALREADY_ACTIVE("domain.meal_template.already_active"),
    MEAL_TEMPLATE_ALREADY_INACTIVE("domain.meal_template.already_inactive"),

    DAY_OF_EATING_DAY_ALREADY_BEGAN("domain.day_of_eating.day_already_began"),
    DAY_OF_EATING_INVALID_CREATION_DATA("domain.day_of_eating.invalid_creation_data"),
    DAY_OF_EATING_ORIGIN_DAY_NOT_EXIST("domain.day_of_eating.origin_day_not_exist"),
    ;

    public final String code;

    DomainError(String code) {
        this.code = code;
    }
}
