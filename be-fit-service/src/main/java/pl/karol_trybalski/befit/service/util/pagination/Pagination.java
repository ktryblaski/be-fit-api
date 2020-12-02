package pl.karol_trybalski.befit.service.util.pagination;

import lombok.AllArgsConstructor;
import pl.karol_trybalski.befit.service.util.sort.SortField;

import java.util.List;

@AllArgsConstructor
public class Pagination<E extends Enum<E>> {

  public final Integer page;
  public final Integer pageSize;
  public final List<SortField<E>> sortFields;

}
