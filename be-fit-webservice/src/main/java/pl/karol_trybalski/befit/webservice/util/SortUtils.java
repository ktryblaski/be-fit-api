package pl.karol_trybalski.befit.webservice.util;

import org.springframework.util.MultiValueMap;
import org.springframework.util.StringUtils;
import pl.karol_trybalski.befit.service.util.sort.SortDirection;
import pl.karol_trybalski.befit.service.util.sort.SortField;

import java.util.ArrayList;
import java.util.List;

public class SortUtils {

  private static final String SORT = "sort";
  private static final String SORT_SEPARATOR = ",";

  private SortUtils() { }

  public static List<SortField> buildSortFields(MultiValueMap<String, String> params) {
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
