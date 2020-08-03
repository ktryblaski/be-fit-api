package pl.karol_trybalski.befit.domain.entity;

import lombok.Data;
import pl.karol_trybalski.befit.domain.entity.base.BaseEntity;
import pl.karol_trybalski.befit.domain.enums.DietType;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@Entity
@Table(name = "v_diet")
public class DietView extends BaseEntity {

    private String name;

    @Column(name = "start_date")
    private LocalDate startDate;
    @Column(name = "end_date")
    private LocalDate endDate;
    @Enumerated(EnumType.STRING)
    private DietType type;

    private int carbohydrates;
    private int proteins;
    private int fats;
    private int meals;

}
