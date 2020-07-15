package pl.karol_trybalski.befit.persistence.repository;

import org.springframework.stereotype.Repository;
import pl.karol_trybalski.befit.persistence.base.BaseRepository;
import pl.karol_trybalski.befit.domain.entity.Product;

@Repository
public interface ProductRepository extends BaseRepository<Product, Long> {

}
