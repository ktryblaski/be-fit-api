package pl.karol_trybalski.befit.domain.entity;

import lombok.Data;
import pl.karol_trybalski.befit.domain.entity.base.BaseEntity;

import javax.persistence.*;

@Entity
@Table(name = "ingredient")
@Data
public class Ingredient extends BaseEntity {

    private int weight;

    @ManyToOne
    @JoinColumn(name = "component_id")
    private Component component;

}
