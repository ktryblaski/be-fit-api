package pl.karol_trybalski.befit.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.karol_trybalski.befit.domain.entity.Macronutrients;
import pl.karol_trybalski.befit.persistence.repository.MacronutrientsRepository;
import pl.karol_trybalski.befit.service.base.BaseService;
import pl.karol_trybalski.befit.domain.entity.Product;
import pl.karol_trybalski.befit.persistence.repository.ProductRepository;

@Service
@Transactional
public class ProductServiceImpl extends BaseService<Product, ProductRepository, Long> {

    final MacronutrientsRepository macronutrientsRepository;

    @Autowired
    public ProductServiceImpl(final ProductRepository repository,
                              final MacronutrientsRepository macronutrientsRepository) {
        super(repository);
        this.macronutrientsRepository = macronutrientsRepository;
    }

    public Long save(Product product) {
        Macronutrients savedMacronutrients = this.macronutrientsRepository.save(product.getMacronutrients());

        product.setMacronutrients(savedMacronutrients);
        Product savedProduct = this.repository.save(product);

        return savedProduct.getId();
    }

    public Product favourite(Long id) {
        Product product = getOne(id);
        product.setFavourite(true);

        return product;
    }

    public Product unfavourite(Long id) {
        Product product = getOne(id);
        product.setFavourite(false);

        return product;
    }

}
