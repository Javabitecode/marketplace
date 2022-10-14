package org.zuzex.service;

import org.mapstruct.Mapper;
import org.zuzex.dto.ProductDto;
import org.zuzex.model.Product;

@Mapper(componentModel = "cdi")
public interface ProductMapper {

    Product toProduct(ProductDto productDto);

    ProductDto toProductDto(Product product);
}
