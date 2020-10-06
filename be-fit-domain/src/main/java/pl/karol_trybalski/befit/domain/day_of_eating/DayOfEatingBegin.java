package pl.karol_trybalski.befit.domain.day_of_eating;

import lombok.Data;

@Data
public class DayOfEatingBegin {

  private DayOfEatingBeginOrigin origin;
  private Long originDayId;

}
