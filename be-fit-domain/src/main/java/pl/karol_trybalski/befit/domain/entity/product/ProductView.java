package pl.karol_trybalski.befit.domain.entity.product;

import lombok.Data;
import pl.karol_trybalski.befit.domain.entity.base.BaseEntity;

import javax.persistence.Entity;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "v_product")
public class ProductView extends BaseEntity {

  private String name;
  private boolean favourite;
  private int proteins;
  private int fats;
  private int carbohydrates;
  private int calories;

}
