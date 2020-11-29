package pl.karol_trybalski.befit.persistence.repository.product;

import org.springframework.stereotype.Repository;
import pl.karol_trybalski.befit.domain.entity.product.ProductView;
import pl.karol_trybalski.befit.persistence.base.BaseRepository;

@Repository
public interface ProductViewRepository extends BaseRepository<ProductView, Long> { }
