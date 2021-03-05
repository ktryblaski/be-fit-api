package pl.karol_trybalski.befit.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import pl.karol_trybalski.befit.domain.module.product.ProductSortBy;
import pl.karol_trybalski.befit.dto.dto.product.ProductDTO;
import pl.karol_trybalski.befit.dto.dto.product.ProductViewDTO;
import pl.karol_trybalski.befit.dto.mapper.ProductMapper;
import pl.karol_trybalski.befit.service.product.ProductServiceImpl;
import pl.karol_trybalski.befit.service.util.pagination.Pagination;

import static pl.karol_trybalski.befit.api.util.PaginationUtils.buildPagination;

@RestController
@RequestMapping(path = "/products")
public class ProductController {

    private final ProductServiceImpl productService;

    @Autowired
    public ProductController(final ProductServiceImpl productService) {
        this.productService = productService;
    }

    @GetMapping
    public Page<ProductViewDTO> list(@RequestParam MultiValueMap<String, String> params) {
        Pagination<ProductSortBy> pagination = buildPagination(params, ProductSortBy.class);
        return productService.findAll(pagination).map(ProductMapper.INSTANCE::map);
    }

    @PostMapping
    public Long create(final @RequestBody ProductDTO product) {
        return productService.save(ProductMapper.INSTANCE.map(product));
    }

    @GetMapping("/{id}")
    public ProductDTO get(final @PathVariable Long id) {
        return ProductMapper.INSTANCE.map(productService.get(id));
    }

    @PutMapping("/{id}/favourite")
    public ProductDTO favourite(final @PathVariable Long id) {
        return ProductMapper.INSTANCE.map(productService.favourite(id));
    }

    @PutMapping("/{id}/unfavourite")
    public ProductDTO unfavourite(final @PathVariable Long id) {
        return ProductMapper.INSTANCE.map(productService.unfavourite(id));
    }

}
