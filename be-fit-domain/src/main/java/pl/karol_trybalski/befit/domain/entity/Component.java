package pl.karol_trybalski.befit.domain.entity;

import lombok.Data;
import pl.karol_trybalski.befit.domain.base.BaseEntity;

import javax.persistence.*;

@Entity
@Table(name = "component")
@Data
public class Component extends BaseEntity {

    private String name;

    @ManyToOne
    @JoinColumn(name = "macronutrients_id")
    private Macronutrients macronutrients;

}
