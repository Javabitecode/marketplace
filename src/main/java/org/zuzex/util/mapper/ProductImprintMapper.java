package org.zuzex.util.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.zuzex.model.Product;
import org.zuzex.model.ProductImprint;

@Mapper(componentModel = "cdi")
public interface ProductImprintMapper {

    @Mapping(target = "shopName", expression = "java(product.getShop().getName())")
    ProductImprint toProductImprint(Product product);
}
