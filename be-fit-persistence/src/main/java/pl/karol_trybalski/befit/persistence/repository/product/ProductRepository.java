package pl.karol_trybalski.befit.persistence.repository.product;

import org.springframework.stereotype.Repository;
import pl.karol_trybalski.befit.persistence.base.BaseRepository;
import pl.karol_trybalski.befit.domain.module.product.Product;

@Repository
public interface ProductRepository extends BaseRepository<Product, Long> { }
