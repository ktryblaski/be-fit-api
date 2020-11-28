package pl.karol_trybalski.befit.service.util.sort;

import org.springframework.data.domain.Sort;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class SortUtils {

  private SortUtils() { }

  public static Sort buildSort(List<SortField> sortFields, Map<String, String> sortColumnByField) {
    if (CollectionUtils.isEmpty(sortFields) || CollectionUtils.isEmpty(sortColumnByField)) {
      return Sort.unsorted();
    }

    List<Sort.Order> orders = sortFields.stream().filter(sf -> sortColumnByField.containsKey(sf.sortBy)).map(sf -> {
      String column = sortColumnByField.get(sf.sortBy);
      return sf.sortDirection == SortDirection.ASCENDING
        ? Sort.Order.asc(column)
        : Sort.Order.desc(column);
    }).collect(Collectors.toList());

    return Sort.by(orders);
  }

}
