package pl.karol_trybalski.befit.service.product;

import com.google.common.collect.ImmutableMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.karol_trybalski.befit.domain.entity.Macronutrients;
import pl.karol_trybalski.befit.domain.entity.product.Product;
import pl.karol_trybalski.befit.domain.entity.product.ProductView;
import pl.karol_trybalski.befit.persistence.repository.MacronutrientsRepository;
import pl.karol_trybalski.befit.persistence.repository.product.ProductRepository;
import pl.karol_trybalski.befit.persistence.repository.product.ProductViewRepository;
import pl.karol_trybalski.befit.service.util.pagination.Pagination;
import pl.karol_trybalski.befit.service.util.pagination.PaginationUtils;

import java.util.Map;

@Service
@Transactional
public class ProductServiceImpl {

  private static final Map<String, String> SORT_COLUMN_BY_SORT_FIELD = ImmutableMap.<String, String>builder()
    .put("NAME", "name")
    .put("PROTEINS", "proteins")
    .put("FATS", "fats")
    .put("CARBOHYDRATES", "carbohydrates")
    .put("CALORIES", "calories")
    .put("FAVOURITE", "favourite")
    .build();

  final ProductRepository repository;
  final ProductViewRepository viewRepository;
  final MacronutrientsRepository macronutrientsRepository;

  @Autowired
  public ProductServiceImpl(final ProductRepository repository,
                            final ProductViewRepository viewRepository,
                            final MacronutrientsRepository macronutrientsRepository) {

    this.repository = repository;
    this.viewRepository = viewRepository;
    this.macronutrientsRepository = macronutrientsRepository;
  }

  public Page<ProductView> findAll(final Pagination pagination) {
    Pageable pageable = PaginationUtils.buildPageable(pagination, SORT_COLUMN_BY_SORT_FIELD);
    return viewRepository.findAll(pageable);
  }

  public Product get(Long id) {
    return repository.getOne(id);
  }

  public Long save(Product product) {
    Macronutrients savedMacronutrients = macronutrientsRepository.save(product.getMacronutrients());

    product.setMacronutrients(savedMacronutrients);
    Product savedProduct = repository.save(product);

    return savedProduct.getId();
  }

  public Product favourite(Long id) {
    Product product = repository.getOne(id);
    product.setFavourite(true);

    return product;
  }

  public Product unfavourite(Long id) {
    Product product = repository.getOne(id);
    product.setFavourite(false);

    return product;
  }

}
