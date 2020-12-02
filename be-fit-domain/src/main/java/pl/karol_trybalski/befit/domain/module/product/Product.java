package pl.karol_trybalski.befit.domain.module.product;

import lombok.Data;
import pl.karol_trybalski.befit.domain.entity.Macronutrients;
import pl.karol_trybalski.befit.domain.entity.base.BaseEntity;

import javax.persistence.*;

@Data
@Entity
@Table(name = "product")
public class Product extends BaseEntity {

    private String name;
    private boolean favourite;

    @OneToOne
    @JoinColumn(name = "macronutrients_id")
    private Macronutrients macronutrients;

}
