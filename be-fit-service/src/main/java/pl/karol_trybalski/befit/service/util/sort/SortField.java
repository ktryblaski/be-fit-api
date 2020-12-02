package pl.karol_trybalski.befit.service.util.sort;

public class SortField<E extends Enum<E>> {

  public static <T extends Enum<T>> SortField<T> of(T sortBy, SortDirection sortDirection) {
    return new SortField<>(sortBy, sortDirection);
  }

  public final E sortBy;
  public final SortDirection sortDirection;

  private SortField(E sortBy, SortDirection sortDirection) {
    this.sortBy = sortBy;
    this.sortDirection = sortDirection;
  }

}
