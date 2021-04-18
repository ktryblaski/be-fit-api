package pl.karol_trybalski.befit.domain.module.recipe;

import pl.karol_trybalski.befit.domain.SortableColumn;

public enum RecipeSortBy implements SortableColumn {

  NAME("name"),
  DESCRIPTION("description"),
  ACTIVE("active"),
  WEIGHT("weight"),
  PROTEINS("proteins"),
  FATS("fats"),
  CARBOHYDRATES("carbohydrates"),
  CALORIES("calories"),
  ;

  public final String column;

  RecipeSortBy(String column) {
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
