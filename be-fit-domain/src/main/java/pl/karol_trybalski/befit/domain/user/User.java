package pl.karol_trybalski.befit.domain.user;

import lombok.Getter;
import lombok.Setter;
import pl.karol_trybalski.befit.domain.entity.base.BaseEntity;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "users")
public class User extends BaseEntity {

  private String name;
  private String surname;
  private String email;
  private String password;

  @Column(name = "register_at")
  private LocalDateTime registerAt;

  private boolean locked;

  @ManyToMany
  @JoinTable(
    name = "users_permission",
    joinColumns = {@JoinColumn(name = "user_id")},
    inverseJoinColumns = {@JoinColumn(name = "permission_id")}
  )
  private Set<Permission> permissions;

}
