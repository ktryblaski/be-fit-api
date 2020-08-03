package pl.karol_trybalski.befit.domain.entity;

import lombok.Getter;
import lombok.Setter;
import pl.karol_trybalski.befit.domain.entity.base.BaseEntity;
import pl.karol_trybalski.befit.domain.enums.MealType;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "diet_meal")
public class DietMeal extends BaseEntity {

    @Enumerated(EnumType.STRING)
    @Column(name = "meal_type")
    private MealType mealType;

    @ManyToOne
    @JoinColumn(name = "diet_id")
    private Diet diet;

    @ManyToOne
    @JoinColumn(name = "meal_id")
    private Meal meal;

}
