package pl.karol_trybalski.befit.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.karol_trybalski.befit.service.base.BaseService;
import pl.karol_trybalski.befit.domain.entity.Product;
import pl.karol_trybalski.befit.persistence.repository.ProductRepository;

@Service
public class ProductServiceImpl extends BaseService<Product, Long> {

    @Autowired
    public ProductServiceImpl(ProductRepository repository) {
        super(repository);
    }

}
