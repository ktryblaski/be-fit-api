package pl.karol_trybalski.befit.domain.entity;

import lombok.Data;
import pl.karol_trybalski.befit.domain.entity.base.BaseEntity;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "meal")
@Data
public class Meal extends BaseEntity {

    private String name;
    private String description;

    @ManyToMany
    @JoinTable(name = "meal_ingredient",
            joinColumns = {@JoinColumn(name = "meal_id")},
            inverseJoinColumns = {@JoinColumn(name = "ingredient_id")})
    private Set<Ingredient> ingredients;

    @ManyToMany
    @JoinTable(name = "diet_meal",
            joinColumns = {@JoinColumn(name = "meal_id")},
            inverseJoinColumns = {@JoinColumn(name = "diet_id")})
    private Set<Diet> diets;

}
