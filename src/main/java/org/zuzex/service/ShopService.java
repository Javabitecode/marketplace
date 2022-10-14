package org.zuzex.service;

import org.zuzex.model.Product;
import org.zuzex.model.Shop;

import javax.transaction.Transactional;
import java.util.List;

public interface ShopService {

    Shop createShop(Shop shop);

    List<Shop> getAllShop();

    Shop getShopById(Long id);

    Shop updateShop(Shop shop);

    Shop addNewProductToShop(Product product, Long  shopId);

    void removeProductFromShop(Long productId, Long shopId);

    void deleteShop(Long id);

}
