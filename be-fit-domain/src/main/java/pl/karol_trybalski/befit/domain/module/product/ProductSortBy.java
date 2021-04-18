package pl.karol_trybalski.befit.domain.module.product;

import pl.karol_trybalski.befit.domain.SortableColumn;

public enum ProductSortBy implements SortableColumn {

  NAME("name"),
  FAVOURITE("favourite"),
  PROTEINS("proteins"),
  FATS("fats"),
  CARBOHYDRATES("carbohydrates"),
  CALORIES("calories"),
  ;

  public final String column;

  ProductSortBy(String column) {
    this.column = column;
  }

  @Override
  public String getColumnName() {
    return column;
  }

  @Override
  public String getName() {
    return name();
  }

}
