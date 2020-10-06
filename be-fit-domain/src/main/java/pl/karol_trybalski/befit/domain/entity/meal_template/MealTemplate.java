package pl.karol_trybalski.befit.domain.entity.meal_template;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import pl.karol_trybalski.befit.domain.entity.Ingredient;
import pl.karol_trybalski.befit.domain.entity.base.BaseEntity;

import javax.persistence.*;
import java.util.Set;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "meal_template")
public class MealTemplate extends BaseEntity {

    private String name;
    private String description;
    private boolean active;

    @ManyToMany
    @JoinTable(name = "meal_template_ingredient",
            joinColumns = {@JoinColumn(name = "meal_template_id")},
            inverseJoinColumns = {@JoinColumn(name = "ingredient_id")})
    private Set<Ingredient> ingredients;

}
