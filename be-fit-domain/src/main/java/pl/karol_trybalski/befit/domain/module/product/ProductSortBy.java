package pl.karol_trybalski.befit.domain.module.product;

import java.util.function.Function;

public enum ProductSortBy {

  NAME("name"),
  FAVOURITE("favourite"),
  PROTEINS("proteins"),
  FATS("fats"),
  CARBOHYDRATES("carbohydrates"),
  CALORIES("calories"),
  ;

  public static Function<ProductSortBy, String> GET_COLUMN = p -> p.column;

  public final String column;

  ProductSortBy(String column) {
    this.column = column;
  }

}
