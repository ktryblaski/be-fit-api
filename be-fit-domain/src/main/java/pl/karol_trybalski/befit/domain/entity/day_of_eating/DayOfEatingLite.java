package pl.karol_trybalski.befit.domain.entity.day_of_eating;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import pl.karol_trybalski.befit.domain.entity.base.BaseEntity;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "v_day_of_eating")
public class DayOfEatingLite extends BaseEntity {

  @Column(name = "day_date")
  private LocalDate dayDate;

  @Column(name = "meals_number")
  private int mealsNumber;

}
