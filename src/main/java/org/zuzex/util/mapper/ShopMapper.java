package org.zuzex.util.mapper;

import org.mapstruct.Mapper;
import org.zuzex.dto.ShopDto;
import org.zuzex.model.Shop;

@Mapper(componentModel = "cdi")
public interface ShopMapper {

    Shop toShop(ShopDto shopDto);

    ShopDto toShopDto(Shop shop);
}
