package pl.karol_trybalski.befit.domain.module.recipe;

import lombok.Data;
import pl.karol_trybalski.befit.domain.entity.base.BaseEntity;

import javax.persistence.Entity;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "v_recipe")
public class RecipeView extends BaseEntity {

  private String name;
  private String description;
  private boolean active;
  private double weight;
  private double proteins;
  private double fats;
  private double carbohydrates;
  private double calories;

}
