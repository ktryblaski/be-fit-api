package pl.karol_trybalski.befit.domain.exception;

public enum DomainError {

    GENERAL("common.general"),
    INVALID_DATA("common.invalid_data"),
    METHOD_NOT_FOUND("common.method_not_found"),
    METHOD_NOT_ALLOWED("common.method_not_allowed"),
    ENTITY_NOT_FOUND("common.entity_not_found"),
    DATA_INTEGRITY_VIOLATION("common.data_integrity_violation"),
    IDENTIFIERS_NOT_MATCH("common.identifiers_not_match"),

    RECIPE_NO_INGREDIENTS("domain.recipe.no_ingredients"),
    RECIPE_ALREADY_ACTIVE("domain.recipe.already_active"),
    RECIPE_ALREADY_INACTIVE("domain.recipe.already_inactive"),
    RECIPE_GIVEN_INGREDIENT_NOT_EXIST("domain.recipe.given_ingredient_not_exist"),

    DAY_OF_EATING_DAY_ALREADY_BEGAN("domain.day_of_eating.day_already_began"),
    DAY_OF_EATING_INVALID_CREATION_DATA("domain.day_of_eating.invalid_creation_data"),
    DAY_OF_EATING_ORIGIN_DAY_NOT_EXIST("domain.day_of_eating.origin_day_not_exist"),

    USER_WITH_GIVEN_EMAIL_ALREADY_EXISTS("domain.user_with_given_email_already_exists"),
    ;

    public final String code;

    DomainError(String code) {
        this.code = code;
    }
}
