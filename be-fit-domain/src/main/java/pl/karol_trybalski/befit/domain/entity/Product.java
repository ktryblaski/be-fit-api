package pl.karol_trybalski.befit.domain.entity;

import lombok.Data;
import pl.karol_trybalski.befit.domain.entity.base.BaseEntity;

import javax.persistence.*;

@Data
@Entity
@Table(name = "product")
public class Product extends BaseEntity {

    private String name;

    @OneToOne
    @JoinColumn(name = "macronutrients_id")
    private Macronutrients macronutrients;

}
