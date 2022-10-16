package org.zuzex.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.zuzex.exception.ProductNotFoundException;
import org.zuzex.exception.ServiceException;
import org.zuzex.exception.ShopAlreadyExistsException;
import org.zuzex.exception.ShopNotFoundException;
import org.zuzex.model.Product;
import org.zuzex.model.Shop;
import org.zuzex.repository.ShopRepository;
import org.zuzex.service.ProductService;
import org.zuzex.service.ShopService;

import javax.enterprise.context.ApplicationScoped;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;
import static org.zuzex.constant.Constants.*;

@Slf4j
@ApplicationScoped
@RequiredArgsConstructor
public class ShopServiceImpl implements ShopService {
    private final ShopRepository shopRepository;

    @Transactional
    @Override
    public Shop createShop(Shop shop) {
        Shop shopDb = shopRepository.findByName(shop.getName());
        if (nonNull(shopDb))
            throw new ShopAlreadyExistsException(SHOP_EXISTS);
        shopRepository.persist(shop);
        log.info("IN createShop - shop: {} successfully created", shop);
        return shop;
    }

    @Override
    public List<Shop> getAllShop() {
        return shopRepository.findAll()
                .stream()
                .collect(Collectors.toList());
    }

    @Override
    public Shop getShopById(Long id) {
        Shop shop = shopRepository.findById(id);
        if (isNull(shop))
            throw new ShopNotFoundException(SHOP_NOT_FOUND);
        log.info("IN getShopById - id: {} successfully get by id", id);
        return shop;
    }

    @Transactional
    @Override
    public Shop updateShopName(Shop shop, Long shopId) {
        shop.setId(shopId);
        if (isNull(shop.getId()))
            throw new ServiceException(SHOP_DOES_NOT_ID);
        shopRepository.update("name and ", shop.getName());
        log.info("IN updateShop - shop: {} successfully updated", shop);
        return shop;
    }

    @Override
    public void deleteShop(Long id) {
        shopRepository.deleteById(id);
    }

/*    @Transactional
    @Override
    public Shop addNewProductToShop(Product product, Long shopId) {
        Shop shopDb = getShopById(shopId);
        shopDb.addProduct(product);
        shopRepository.persist(shopDb);
        log.info("IN addProduct - product: {} successfully added", product);
        return shopDb;
    }*/

/*    @Transactional
    @Override
    public void removeProductFromShop(Long productId, Long shopId) {
        Shop shopDb = getShopById(shopId);
        Product product = shopDb.getProducts()
                .stream()
                .filter(prod -> Objects.equals(prod.getId(), productId))
                .findFirst()
                .orElseThrow(() -> new ProductNotFoundException(PRODUCT_NOT_FOUND));
        shopDb.removeProduct(product);
        shopRepository.persist(shopDb);
        log.info("IN addProduct - product: {} successfully added", product);
    }*/
}
