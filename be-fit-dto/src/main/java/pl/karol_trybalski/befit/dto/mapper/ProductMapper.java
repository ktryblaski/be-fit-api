package pl.karol_trybalski.befit.dto.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import pl.karol_trybalski.befit.domain.entity.product.Product;
import pl.karol_trybalski.befit.domain.entity.product.ProductView;
import pl.karol_trybalski.befit.dto.dto.product.ProductDTO;
import pl.karol_trybalski.befit.dto.dto.product.ProductViewDTO;

@Mapper
public interface ProductMapper {

    ProductMapper INSTANCE = Mappers.getMapper(ProductMapper.class);

    ProductViewDTO map(ProductView product);
    ProductDTO map(Product product);

    Product map(ProductDTO product);

}
