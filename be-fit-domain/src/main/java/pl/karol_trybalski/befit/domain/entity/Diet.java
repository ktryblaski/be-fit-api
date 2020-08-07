package pl.karol_trybalski.befit.domain.entity;

import lombok.Data;
import pl.karol_trybalski.befit.domain.entity.base.BaseEntity;
import pl.karol_trybalski.befit.domain.enums.DietType;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Set;

@Data
@Entity
@Table(name = "diet")
public class Diet extends BaseEntity {

    private String name;
    private String description;

    @Column(name = "start_date")
    private LocalDate startDate;

    @Column(name = "end_date")
    private LocalDate endDate;

    @Enumerated(EnumType.STRING)
    private DietType type;

    @OneToOne
    @JoinColumn(name = "macronutrients_id")
    private Macronutrients macronutrients;

    @OneToMany(mappedBy = "diet")
    private Set<DietMeal> meals;

}
