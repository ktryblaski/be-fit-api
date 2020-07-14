package pl.karol_trybalski.befit.domain.entity;

import lombok.Data;
import pl.karol_trybalski.befit.domain.entity.base.BaseEntity;
import pl.karol_trybalski.befit.domain.enums.DietType;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Set;

@Entity
@Table(name = "diet")
@Data
public class Diet extends BaseEntity {

    private String name;

    @Column(name = "diet_start")
    private LocalDate dietStart;

    @Column(name = "diet_end")
    private LocalDate dietEnd;

    @Enumerated(EnumType.STRING)
    private DietType type;

    @OneToOne
    @JoinColumn(name = "macronutrients_id")
    private Macronutrients macronutrients;

    @ManyToMany
    @JoinTable(name = "diet_meal",
            joinColumns = {@JoinColumn(name = "diet_id")},
            inverseJoinColumns = {@JoinColumn(name = "meal_id")})
    private Set<Meal> meals;

}
