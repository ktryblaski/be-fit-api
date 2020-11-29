package pl.karol_trybalski.befit.service.util.pagination;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.util.CollectionUtils;
import pl.karol_trybalski.befit.service.util.sort.SortDirection;
import pl.karol_trybalski.befit.service.util.sort.SortField;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class PaginationUtils {

  private static final int DEFAULT_PAGE_SIZE = 10;

  private PaginationUtils() { }

  public static Pageable buildPageable(Pagination pagination, Map<String, String> sortColumnByField) {
    return PageRequest.of(
      pagination.page != null ? pagination.page : 0,
      pagination.pageSize != null ? pagination.pageSize : DEFAULT_PAGE_SIZE,
      buildSort(pagination.sortFields, sortColumnByField)
    );
  }

  private static Sort buildSort(List<SortField> sortFields, Map<String, String> sortColumnByField) {
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
