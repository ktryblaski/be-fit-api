package pl.karol_trybalski.befit.api;

import lombok.extern.slf4j.Slf4j;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;
import pl.karol_trybalski.befit.service.util.pagination.Pagination;
import pl.karol_trybalski.befit.service.util.sort.SortDirection;
import pl.karol_trybalski.befit.service.util.sort.SortField;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.regex.Pattern;

@Slf4j
@Component
public class PageableParametersResolver implements HandlerMethodArgumentResolver {

  private static final String PAGE = "page";
  private static final String PAGE_SIZE = "pageSize";
  private static final String SORT = "sort";
  private static final char ASCENDING = '+';
  private static final char DESCENDING = '-';

  private static final Predicate<String> POSITIVE_NUMBER = Pattern.compile("^[1-9]\\d*$").asPredicate();
  private static final Predicate<String> NOT_NEGATIVE_NUMBER = Pattern.compile("^\\d+$").asPredicate();

  @Override
  public boolean supportsParameter(final MethodParameter methodParameter) {
    return methodParameter.getParameterType().isAssignableFrom(Pagination.class);
  }

  @Override
  public Object resolveArgument(final MethodParameter methodParameter,
                                final ModelAndViewContainer mavContainer,
                                final NativeWebRequest request,
                                final WebDataBinderFactory binderFactory) {

    final Integer page = resolvePage(request);
    final Integer pageSize = resolvePageSize(request);
    final List<SortField> sortFields = buildSortFields(request);

    return new Pagination(page, pageSize, sortFields);
  }

  private Integer resolvePage(final NativeWebRequest request) {
    final String page = request.getParameter(PAGE);

    if(page != null && NOT_NEGATIVE_NUMBER.test(page)) {
      return Integer.parseInt(page);
    } else {
      log.warn("Cannot resolve page parameter with value {}", page);
      return null;
    }
  }

  private  Integer resolvePageSize(final NativeWebRequest request) {
    final String pageSize = request.getParameter(PAGE_SIZE);

    if(pageSize != null && POSITIVE_NUMBER.test(pageSize)) {
      return Integer.parseInt(pageSize);
    } else {
      log.warn("Cannot resolve page size parameter with value {}", pageSize);
      return null;
    }
  }

  private List<SortField> buildSortFields(final NativeWebRequest request) {
    final List<SortField> sortFields = new ArrayList<>();

    final String[] sorts = request.getParameterValues(SORT);

    if(sorts == null || sorts.length == 0) {
      return sortFields;
    }

    for (String sort : sorts) {
      final String fieldName = resolveFieldName(sort);
      final SortDirection sortDirection = resolveSortDirection(sort);

      if(StringUtils.hasLength(fieldName)) {
        sortFields.add(SortField.of(fieldName, sortDirection));
      }
    }

    return sortFields;
  }

  private String resolveFieldName(String sortText) {
    if (!StringUtils.hasText(sortText)) {
      return null;
    }

    char orderIndicator = sortText.charAt(0);
    if(orderIndicator == ASCENDING || orderIndicator == DESCENDING){
      return sortText.length() > 1 ? sortText.substring(1) : "";
    }

    return sortText;
  }

  private SortDirection resolveSortDirection(String sortText) {
    if (!StringUtils.hasText(sortText)) {
      return null;
    }

    return sortText.charAt(0) == DESCENDING ? SortDirection.DESCENDING : SortDirection.ASCENDING;
  }


}
