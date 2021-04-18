package pl.karol_trybalski.befit.api.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;
import pl.karol_trybalski.befit.dto.dto.product.ProductDTO;
import pl.karol_trybalski.befit.dto.dto.product.ProductViewDTO;
import pl.karol_trybalski.befit.dto.mapper.ProductMapper;
import pl.karol_trybalski.befit.service.product.ProductServiceImpl;
import pl.karol_trybalski.befit.service.util.pagination.Pagination;

@RequiredArgsConstructor
@RequestMapping(path = "/products")
@RestController
public class ProductController {

    private final ProductServiceImpl productService;

    @GetMapping
    public Page<ProductViewDTO> list(final Pagination pagination) {
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
