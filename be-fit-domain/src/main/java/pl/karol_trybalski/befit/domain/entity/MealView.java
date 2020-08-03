package pl.karol_trybalski.befit.domain.entity;

import lombok.Data;
import pl.karol_trybalski.befit.domain.entity.base.BaseEntity;

import javax.persistence.Entity;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "v_meal")
public class MealView extends BaseEntity {

    private String name;
    private int weight;
    private int carbohydrates;
    private int proteins;
    private int fats;

}
