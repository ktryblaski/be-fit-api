package pl.karol_trybalski.befit.domain.entity;

import lombok.Data;
import pl.karol_trybalski.befit.domain.entity.base.BaseEntity;
import pl.karol_trybalski.befit.domain.enums.MealType;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;

@Entity
@Table(name = "v_meal")
@Data
public class MealView extends BaseEntity {

    private String name;
    private String description;

    @Enumerated(EnumType.STRING)
    private MealType type;

    private int weight;
    private int carbohydrates;
    private int proteins;
    private int fats;

}
