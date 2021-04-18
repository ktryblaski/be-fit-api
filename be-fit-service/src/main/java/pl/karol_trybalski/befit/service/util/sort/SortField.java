package pl.karol_trybalski.befit.service.util.sort;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class SortField {

  public static SortField of(final String sortBy, final SortDirection sortDirection) {
    return new SortField(sortBy, sortDirection);
  }

  public final String sortBy;
  public final SortDirection sortDirection;

}
