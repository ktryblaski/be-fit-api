package pl.karol_trybalski.befit.api.validation;

import com.google.common.collect.Sets;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Set;
import java.util.regex.Pattern;

public class PasswordValidator implements ConstraintValidator<Password, String> {

  private static final String LENGTH = "^.{6,}$";
  private static final String AT_LEAST_ONE_NUMBER = "^.*\\d.*$";
  private static final String AT_LEAST_ONE_LOWERCASE = "^.*[a-z].*$";
  private static final String AT_LEAST_ONE_UPPERCASE = "^.*[A-Z].*$";

  private static final Set<String> MATCHERS = Sets.newHashSet(
    LENGTH,
    AT_LEAST_ONE_NUMBER,
    AT_LEAST_ONE_LOWERCASE,
    AT_LEAST_ONE_UPPERCASE
  );

  @Override
  public boolean isValid(final String value, final ConstraintValidatorContext context) {
    return value != null && MATCHERS.stream().allMatch(m -> Pattern.compile(m).matcher(value).find());
  }

}
