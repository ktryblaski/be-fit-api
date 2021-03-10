package pl.karol_trybalski.befit.domain.user;

import lombok.Getter;
import lombok.Setter;
import pl.karol_trybalski.befit.domain.entity.base.BaseEntity;

import javax.persistence.Entity;
import javax.persistence.Table;

@Getter
@Setter
@Entity
@Table(name = "permission")
public class Permission extends BaseEntity {

  private String code;

}
