package pl.karol_trybalski.befit.service;

import com.google.common.collect.ImmutableMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.karol_trybalski.befit.domain.entity.Macronutrients;
import pl.karol_trybalski.befit.persistence.repository.MacronutrientsRepository;
import pl.karol_trybalski.befit.service.base.BaseService;
import pl.karol_trybalski.befit.domain.entity.Product;
import pl.karol_trybalski.befit.persistence.repository.ProductRepository;
import pl.karol_trybalski.befit.service.util.sort.SortField;
import pl.karol_trybalski.befit.service.util.sort.SortUtils;

import java.util.List;
import java.util.Map;

@Service
@Transactional
public class ProductServiceImpl extends BaseService<Product, ProductRepository, Long> {

  private static final Map<String, String> SORT_COLUMN_BY_SORT_FIELD = ImmutableMap.of(
    "NAME", "name"
  );

  final MacronutrientsRepository macronutrientsRepository;

  @Autowired
  public ProductServiceImpl(final ProductRepository repository,
                            final MacronutrientsRepository macronutrientsRepository) {
    super(repository);
    this.macronutrientsRepository = macronutrientsRepository;
  }

  public List<Product> findAll(final List<SortField> sortFields) {
    return this.repository.findAll(
      SortUtils.buildSort(sortFields, SORT_COLUMN_BY_SORT_FIELD)
    );
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
