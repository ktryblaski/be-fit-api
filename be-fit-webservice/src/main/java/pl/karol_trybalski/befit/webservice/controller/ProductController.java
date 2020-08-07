package pl.karol_trybalski.befit.webservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.karol_trybalski.befit.dto.dto.ProductDTO;
import pl.karol_trybalski.befit.dto.mapper.ProductMapper;
import pl.karol_trybalski.befit.service.ProductServiceImpl;
import pl.karol_trybalski.befit.webservice.ApiResponse;

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
    public ApiResponse<List<ProductDTO>> list() {
        return ApiResponse.from(
                productService.findAll().stream().map(ProductMapper.INSTANCE::map).collect(Collectors.toList())
        );
    }

    @PostMapping
    public ApiResponse<Long> create(final @RequestBody ProductDTO product) {
        return ApiResponse.from(
                productService.save(ProductMapper.INSTANCE.map(product))
        );
    }

    @GetMapping("/{id}")
    public ApiResponse<ProductDTO> get(final @PathVariable Long id) {
        return ApiResponse.from(
                ProductMapper.INSTANCE.map(productService.getOne(id))
        );
    }

}
