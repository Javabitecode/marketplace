package org.zuzex.service;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.zuzex.dto.CategoryDto;
import org.zuzex.dto.ProductDto;
import org.zuzex.model.Category;
import org.zuzex.model.Product;

@Mapper(componentModel = "cdi")
public interface ProductMapper {

    Product toProduct(ProductDto productDto);

    CategoryDto toCategoryDto(Category category);

    @Mapping(target = "category", expression = "java(toCategoryDto(product.getCategory()))")
    ProductDto toProductDto(Product product);
}
