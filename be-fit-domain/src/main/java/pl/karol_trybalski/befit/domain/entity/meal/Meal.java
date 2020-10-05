package pl.karol_trybalski.befit.domain.entity.meal;

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
@Table(name = "meal")
public class Meal extends BaseEntity {

    private String name;
    private String description;

    @ManyToMany
    @JoinTable(name = "meal_ingredient",
            joinColumns = {@JoinColumn(name = "meal_id")},
            inverseJoinColumns = {@JoinColumn(name = "ingredient_id")})
    private Set<Ingredient> ingredients;

}
