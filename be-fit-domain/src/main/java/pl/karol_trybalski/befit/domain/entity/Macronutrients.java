package pl.karol_trybalski.befit.domain.entity;

import lombok.Data;
import pl.karol_trybalski.befit.domain.entity.base.BaseEntity;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "macronutrients")
@Data
public class Macronutrients extends BaseEntity {

    private int carbohydrates;
    private int proteins;
    private int fats;

}
