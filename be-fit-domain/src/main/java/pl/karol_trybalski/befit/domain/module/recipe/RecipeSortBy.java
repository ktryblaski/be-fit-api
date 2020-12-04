package pl.karol_trybalski.befit.domain.module.recipe;

import java.util.function.Function;

public enum RecipeSortBy {

  NAME("name"),
  DESCRIPTION("description"),
  ACTIVE("active"),
  WEIGHT("weight"),
  PROTEINS("proteins"),
  FATS("fats"),
  CARBOHYDRATES("carbohydrates"),
  ;

  public static Function<RecipeSortBy, String> GET_COLUMN = p -> p.column;

  public final String column;

  RecipeSortBy(String column) {
    this.column = column;
  }

}
