package pl.karol_trybalski.befit.api.util;

import com.google.common.collect.ImmutableMap;
import org.springframework.http.HttpStatus;
import pl.karol_trybalski.befit.domain.exception.DomainError;

import java.util.Map;
import java.util.Set;

import static com.google.common.collect.Sets.newHashSet;
import static org.springframework.http.HttpStatus.*;
import static pl.karol_trybalski.befit.domain.exception.DomainError.*;

// TODO - think how to make this prettier and more elegant
public class ResponseUtils {

  private static final Map<HttpStatus, Set<DomainError>> HTTP_STATUS_TO_DOMAIN_ERROR = ImmutableMap.<HttpStatus, Set<DomainError>>builder()
    .put(
      BAD_REQUEST, newHashSet(IDENTIFIERS_NOT_MATCH, RECIPE_NO_INGREDIENTS, DAY_OF_EATING_INVALID_CREATION_DATA)
    )
    .put(
      CONFLICT, newHashSet(RECIPE_ALREADY_ACTIVE, RECIPE_ALREADY_INACTIVE, DAY_OF_EATING_DAY_ALREADY_BEGAN, USER_WITH_GIVEN_EMAIL_ALREADY_EXISTS)
    )
    .put(
      UNPROCESSABLE_ENTITY, newHashSet(RECIPE_GIVEN_INGREDIENT_NOT_EXIST, DAY_OF_EATING_ORIGIN_DAY_NOT_EXIST)
    )
    .build();


  public static HttpStatus getHttpStatus(DomainError domainError) {
    if(domainError == null) {
      return INTERNAL_SERVER_ERROR;
    }

    return HTTP_STATUS_TO_DOMAIN_ERROR.entrySet().stream()
      .filter(entry -> entry.getValue().contains(domainError))
      .findFirst()
      .map(Map.Entry::getKey)
      .orElse(INTERNAL_SERVER_ERROR);
  }
}
