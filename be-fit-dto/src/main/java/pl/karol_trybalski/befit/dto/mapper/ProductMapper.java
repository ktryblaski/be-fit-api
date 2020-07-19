package pl.karol_trybalski.befit.dto.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import pl.karol_trybalski.befit.domain.entity.Product;
import pl.karol_trybalski.befit.dto.dto.ProductDTO;

@Mapper
public interface ProductMapper {

    ProductMapper INSTANCE = Mappers.getMapper(ProductMapper.class);

    ProductDTO map(Product product);

    Product map(ProductDTO product);

}
