package pl.karol_trybalski.befit.service.util.pagination;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.util.CollectionUtils;
import pl.karol_trybalski.befit.domain.SortableColumn;
import pl.karol_trybalski.befit.service.util.sort.SortDirection;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class PaginationUtils {

  private static final int DEFAULT_PAGE = 0;
  private static final int DEFAULT_PAGE_SIZE = 10;

  private PaginationUtils() { }

  public static Pageable buildPageable(final Pagination pagination,
                                       final SortableColumn[] sortableColumns) {

    final int page = pagination != null && pagination.page != null ? pagination.page : DEFAULT_PAGE;
    final int pageSize = pagination != null && pagination.pageSize != null ? pagination.pageSize : DEFAULT_PAGE_SIZE;
    final Sort sort = buildSort(pagination, sortableColumns);

    return PageRequest.of(page, pageSize, sort);
  }

  private static Sort buildSort(final Pagination pagination,
                                final SortableColumn[] sortableColumns) {

    if (pagination == null || CollectionUtils.isEmpty(pagination.sortFields)) {
      return Sort.unsorted();
    }

    final Map<String, String> sortableColumnsMap
      = Arrays.stream(sortableColumns).collect(Collectors.toMap(SortableColumn::getName, SortableColumn::getColumnName));


    final List<Sort.Order> orders = pagination.sortFields.stream()
      .filter(sf -> sortableColumnsMap.containsKey(sf.sortBy))
      .map(sf -> {
        final String columnName = sortableColumnsMap.get(sf.sortBy);
        return sf.sortDirection == SortDirection.ASCENDING ? Sort.Order.asc(columnName) : Sort.Order.desc(columnName);
      }).collect(Collectors.toList());

    return Sort.by(orders);
  }

}
