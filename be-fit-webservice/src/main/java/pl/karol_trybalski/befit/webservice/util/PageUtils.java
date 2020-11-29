package pl.karol_trybalski.befit.webservice.util;

import org.springframework.util.MultiValueMap;
import org.springframework.util.StringUtils;
import pl.karol_trybalski.befit.service.util.pagination.Pagination;
import pl.karol_trybalski.befit.service.util.sort.SortDirection;
import pl.karol_trybalski.befit.service.util.sort.SortField;

import java.util.ArrayList;
import java.util.List;

public class PageUtils {

  private static final String PAGE = "page";
  private static final String PAGE_SIZE = "pageSize";
  private static final String SORT = "sort";
  private static final String SORT_SEPARATOR = ",";

  private PageUtils() { }

  public static Pagination buildPagination(MultiValueMap<String, String> params) {
    Integer page = null;
    Integer pageSize = null;
    List<SortField> sortFields = buildSortFields(params);

    if(params.containsKey(PAGE) && params.containsKey(PAGE_SIZE)) {
      try {
        page = Integer.valueOf(params.getFirst(PAGE));
        pageSize = Integer.valueOf(params.getFirst(PAGE_SIZE));
      } catch (Exception ignored) {
        // TODO what to do in that case?
      }
    }

    return new Pagination(page, pageSize, sortFields);
  }

  private static List<SortField> buildSortFields(MultiValueMap<String, String> params) {
    if(params == null || !StringUtils.hasText(params.getFirst(SORT))) {
      return new ArrayList<>();
    }

    List<SortField> sortFields = new ArrayList<>();

    String[] parts = params.getFirst(SORT).split(SORT_SEPARATOR);
    for (String part : parts) {
      if(!StringUtils.hasText(part)) {
        // TODO what to do in that case?
        continue;
      }

      sortFields.add(new SortField(resolveFieldName(part), resolveSortDirection(part)));
    }

    return sortFields;
  }

  private static String resolveFieldName(String sortText) {
    char orderIndicator = sortText.charAt(0);
    if(orderIndicator == '+' || orderIndicator == '-'){
      return sortText.length() > 1 ? sortText.substring(1) : "";
    }

    return sortText;
  }

  private static SortDirection resolveSortDirection(String sortText) {
    return sortText.charAt(0) == '-' ? SortDirection.DESCENDING : SortDirection.ASCENDING;
  }


}
