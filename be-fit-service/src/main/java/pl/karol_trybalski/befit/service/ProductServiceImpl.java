package pl.karol_trybalski.befit.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.karol_trybalski.befit.domain.entity.Macronutrients;
import pl.karol_trybalski.befit.persistence.repository.MacronutrientsRepository;
import pl.karol_trybalski.befit.service.base.BaseService;
import pl.karol_trybalski.befit.domain.entity.Product;
import pl.karol_trybalski.befit.persistence.repository.ProductRepository;

@Service
public class ProductServiceImpl extends BaseService<Product, Long> {

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

}
