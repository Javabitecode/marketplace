package org.zuzex.service;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.zuzex.dto.CategoryDto;
import org.zuzex.dto.ProductDto;
import org.zuzex.dto.ShopDto;
import org.zuzex.model.Category;
import org.zuzex.model.Product;
import org.zuzex.model.Shop;

@Mapper(componentModel = "cdi")
public interface ProductMapper {

    Product toProduct(ProductDto productDto);

    CategoryDto toCategoryDto(Category category);

    ShopDto toShopDto(Shop shop);

    @Mapping(target = "category", expression = "java(toCategoryDto(product.getCategory()))")
    @Mapping(target = "shopDto", expression = "java(toShopDto(product.getShop()))")
    ProductDto toProductDto(Product product);
}
