package pl.karol_trybalski.befit.service.product;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.karol_trybalski.befit.domain.entity.Macronutrients;
import pl.karol_trybalski.befit.domain.module.product.Product;
import pl.karol_trybalski.befit.domain.module.product.ProductSortBy;
import pl.karol_trybalski.befit.domain.module.product.ProductView;
import pl.karol_trybalski.befit.persistence.repository.MacronutrientsRepository;
import pl.karol_trybalski.befit.persistence.repository.product.ProductRepository;
import pl.karol_trybalski.befit.persistence.repository.product.ProductViewRepository;
import pl.karol_trybalski.befit.service.util.pagination.Pagination;
import pl.karol_trybalski.befit.service.util.pagination.PaginationUtils;

@RequiredArgsConstructor
@Transactional
@Service
public class ProductServiceImpl {

  final ProductRepository repository;
  final ProductViewRepository viewRepository;
  final MacronutrientsRepository macronutrientsRepository;

  public Page<ProductView> findAll(final Pagination<ProductSortBy> pagination) {
    Pageable pageable = PaginationUtils.buildPageable(pagination, ProductSortBy.GET_COLUMN);
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
