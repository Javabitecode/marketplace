package org.zuzex.util.mapper;

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
    Category toCategory(CategoryDto categoryDto);

    Shop toShop(ShopDto shopDto);

    @Mapping(target = "category", expression = "java(toCategory(productDto.getCategoryDto()))")
    @Mapping(target = "shop", expression = "java(toShop(productDto.getShopDto()))")
    Product toProduct(ProductDto productDto);

    CategoryDto toCategoryDto(Category category);

    ShopDto toShopDto(Shop shop);

    @Mapping(target = "categoryDto", expression = "java(toCategoryDto(product.getCategory()))")
    @Mapping(target = "shopDto", expression = "java(toShopDto(product.getShop()))")
    ProductDto toProductDto(Product product);
}
