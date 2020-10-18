package pl.karol_trybalski.befit.webservice;

import com.google.common.collect.ImmutableMap;
import org.springframework.http.HttpStatus;
import pl.karol_trybalski.befit.domain.exception.DomainError;

import java.util.Map;
import java.util.Set;

import static com.google.common.collect.Sets.newHashSet;
import static pl.karol_trybalski.befit.domain.exception.DomainError.*;
import static org.springframework.http.HttpStatus.*;

public class ResponseUtils {

  private static final Map<HttpStatus, Set<DomainError>> HTTP_STATUS_TO_DOMAIN_ERROR = ImmutableMap.<HttpStatus, Set<DomainError>>builder()
    .put(
      BAD_REQUEST, newHashSet(IDENTIFIERS_NOT_MATCH, MEAL_TEMPLATE_NO_INGREDIENTS, DAY_OF_EATING_INVALID_CREATION_DATA)
    )
    .put(
      CONFLICT, newHashSet(MEAL_TEMPLATE_ALREADY_ACTIVE, MEAL_TEMPLATE_ALREADY_INACTIVE, DAY_OF_EATING_DAY_ALREADY_BEGAN)
    )
    .put(
      UNPROCESSABLE_ENTITY, newHashSet(MEAL_TEMPLATE_GIVEN_INGREDIENT_NOT_EXIST, DAY_OF_EATING_ORIGIN_DAY_NOT_EXIST)
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
