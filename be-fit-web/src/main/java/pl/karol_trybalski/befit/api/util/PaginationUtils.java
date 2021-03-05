package pl.karol_trybalski.befit.api.util;

import lombok.extern.slf4j.Slf4j;
import org.springframework.util.CollectionUtils;
import org.springframework.util.MultiValueMap;
import org.springframework.util.StringUtils;
import pl.karol_trybalski.befit.service.util.pagination.Pagination;
import pl.karol_trybalski.befit.service.util.sort.SortDirection;
import pl.karol_trybalski.befit.service.util.sort.SortField;

import javax.annotation.Nonnull;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@Slf4j
public class PaginationUtils {

  private static final String PAGE = "page";
  private static final String PAGE_SIZE = "pageSize";
  private static final String SORT = "sort";
  private static final char ASCENDING = '+';
  private static final char DESCENDING = '-';

  private static final Predicate<String> POSITIVE_NUMBER = Pattern.compile("^[1-9]\\d*$").asPredicate();
  private static final Predicate<String> NOT_NEGATIVE_NUMBER = Pattern.compile("^\\d+$").asPredicate();

  private PaginationUtils() { }

  public static <E extends Enum<E>> Pagination<E> buildPagination(MultiValueMap<String, String> params, @Nonnull Class<E> enumClass) {
    Integer page = resolvePage(params);
    Integer pageSize = resolvePageSize(params);
    List<SortField<E>> sortFields = buildSortFields(params, enumClass);

    return new Pagination<>(page, pageSize, sortFields);
  }

  private static Integer resolvePage(MultiValueMap<String, String> params) {
    if(params == null || !params.containsKey(PAGE)) {
      return null;
    }

    String page = params.getFirst(PAGE);
    if(page != null && NOT_NEGATIVE_NUMBER.test(page)) {
      return Integer.parseInt(page);
    } else {
      log.warn("Cannot resolve page parameter with value {}", page);
      return null;
    }
  }

  private static Integer resolvePageSize(MultiValueMap<String, String> params) {
    if(params == null || !params.containsKey(PAGE_SIZE)) {
      return null;
    }

    String pageSize = params.getFirst(PAGE_SIZE);
    if(pageSize != null && POSITIVE_NUMBER.test(pageSize)) {
      return Integer.parseInt(pageSize);
    } else {
      log.warn("Cannot resolve page size parameter with value {}", pageSize);
      return null;
    }
  }

  private static <E extends Enum<E>> List<SortField<E>> buildSortFields(MultiValueMap<String, String> params, @Nonnull Class<E> enumClass) {
    List<SortField<E>> sortFields = new ArrayList<>();

    if(params == null || CollectionUtils.isEmpty(params.get(SORT))) {
      return sortFields;
    }

    Map<String, E> enumValuesMap = Arrays.stream(enumClass.getEnumConstants()).collect(Collectors.toMap(Enum::toString, e -> e));

    for (String sort : params.get(SORT)) {
      String fieldName = resolveFieldName(sort);
      SortDirection sortDirection = resolveSortDirection(sort);

      if(StringUtils.hasLength(fieldName) && enumValuesMap.containsKey(fieldName)) {
        sortFields.add(SortField.of(enumValuesMap.get(fieldName), sortDirection));
      } else {
        log.warn("Cannot resolve sort field with value {}", sort);
      }
    }

    return sortFields;
  }

  private static String resolveFieldName(String sortText) {
    if (!StringUtils.hasText(sortText)) {
      return null;
    }

    char orderIndicator = sortText.charAt(0);
    if(orderIndicator == ASCENDING || orderIndicator == DESCENDING){
      return sortText.length() > 1 ? sortText.substring(1) : "";
    }

    return sortText;
  }

  private static SortDirection resolveSortDirection(String sortText) {
    if (!StringUtils.hasText(sortText)) {
      return null;
    }

    return sortText.charAt(0) == DESCENDING ? SortDirection.DESCENDING : SortDirection.ASCENDING;
  }

}
