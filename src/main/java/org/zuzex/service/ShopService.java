package org.zuzex.service;

import org.zuzex.model.Shop;

import java.util.List;

public interface ShopService {

    Shop createShop(Shop shop);

    List<Shop> getAllShop();

    Shop getShopById(Long id);

    Shop updateShop(Shop shop, Long shopId);

    void deleteShop(Long id);

}
