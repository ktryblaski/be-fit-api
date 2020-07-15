package pl.karol_trybalski.befit.domain.entity;

import lombok.Data;
import pl.karol_trybalski.befit.domain.entity.base.BaseEntity;

import javax.persistence.*;

@Entity
@Table(name = "product")
@Data
public class Product extends BaseEntity {

    private String name;

    @ManyToOne
    @JoinColumn(name = "macronutrients_id")
    private Macronutrients macronutrients;

}
