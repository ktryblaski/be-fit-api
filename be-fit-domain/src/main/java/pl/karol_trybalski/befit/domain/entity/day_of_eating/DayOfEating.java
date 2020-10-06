package pl.karol_trybalski.befit.domain.entity.day_of_eating;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import pl.karol_trybalski.befit.domain.entity.base.BaseEntity;
import pl.karol_trybalski.befit.domain.entity.meal.Meal;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Set;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "day_of_eating")
public class DayOfEating extends BaseEntity {

  @Column(name = "day_date")
  private LocalDate dayDate;

  @ManyToMany(fetch = FetchType.EAGER)
  @JoinTable(name = "day_of_eating_meal",
    joinColumns = {@JoinColumn(name = "day_of_eating_id")},
    inverseJoinColumns = {@JoinColumn(name = "meal_id")})
  private Set<Meal> meals;

}
