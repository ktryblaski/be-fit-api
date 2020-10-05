package pl.karol_trybalski.befit.webservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.karol_trybalski.befit.dto.dto.ProductDTO;
import pl.karol_trybalski.befit.dto.mapper.ProductMapper;
import pl.karol_trybalski.befit.service.ProductServiceImpl;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(path = "/products")
public class ProductController {

    private final ProductServiceImpl productService;

    @Autowired
    public ProductController(final ProductServiceImpl productService) {
        this.productService = productService;
    }

    @GetMapping
    public List<ProductDTO> list() {
        return productService.findAll().stream().map(ProductMapper.INSTANCE::map).collect(Collectors.toList());
    }

    @PostMapping
    public Long create(final @RequestBody ProductDTO product) {
        return productService.save(ProductMapper.INSTANCE.map(product));
    }

    @GetMapping("/{id}")
    public ProductDTO get(final @PathVariable Long id) {
        return ProductMapper.INSTANCE.map(productService.getOne(id));
    }

}
