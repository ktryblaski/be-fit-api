package pl.karol_trybalski.befit.service.util.pagination;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.util.CollectionUtils;
import pl.karol_trybalski.befit.service.util.sort.SortDirection;

import javax.annotation.Nonnull;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

public class PaginationUtils {

  private static final int DEFAULT_PAGE = 0;
  private static final int DEFAULT_PAGE_SIZE = 10;

  private PaginationUtils() { }

  public static <E extends Enum<E>> Pageable buildPageable(Pagination<E> pagination, @Nonnull Function<E, String> column) {
    int page = pagination != null && pagination.page != null ? pagination.page : DEFAULT_PAGE;
    int pageSize = pagination != null && pagination.pageSize != null ? pagination.pageSize : DEFAULT_PAGE_SIZE;
    Sort sort = buildSort(pagination, column);

    return PageRequest.of(page, pageSize, sort);
  }

  private static <E extends Enum<E>> Sort buildSort(Pagination<E> pagination, Function<E, String> column) {
    if (pagination == null || CollectionUtils.isEmpty(pagination.sortFields)) {
      return Sort.unsorted();
    }

    List<Sort.Order> orders = pagination.sortFields.stream().map(sf -> {
      String columnName = column.apply(sf.sortBy);
      return sf.sortDirection == SortDirection.ASCENDING ? Sort.Order.asc(columnName) : Sort.Order.desc(columnName);
    }).collect(Collectors.toList());

    return Sort.by(orders);
  }

}
