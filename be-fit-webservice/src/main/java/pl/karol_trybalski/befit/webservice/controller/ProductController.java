package pl.karol_trybalski.befit.webservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.karol_trybalski.befit.domain.entity.Product;
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
        return productService.findAll()
                .stream()
                .map(ProductMapper.INSTANCE::map)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public ProductDTO get(@PathVariable Long id) {
        return productService.findById(id)
                .map(ProductMapper.INSTANCE::map)
                .orElse(null);
    }

}
