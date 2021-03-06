package pl.karol_trybalski.befit.domain.entity;

import lombok.Data;
import pl.karol_trybalski.befit.domain.entity.base.BaseEntity;

import javax.persistence.Entity;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "macronutrients")
public class Macronutrients extends BaseEntity {

    private int proteins;
    private int fats;
    private int carbohydrates;

}
