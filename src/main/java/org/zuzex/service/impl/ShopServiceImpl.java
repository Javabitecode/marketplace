package org.zuzex.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.zuzex.exception.ShopAlreadyExistsException;
import org.zuzex.exception.ShopNotFoundException;
import org.zuzex.model.Shop;
import org.zuzex.repository.ShopRepository;
import org.zuzex.service.ShopService;

import javax.enterprise.context.ApplicationScoped;
import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.Objects.nonNull;
import static org.zuzex.constant.Constants.SHOP_EXISTS;
import static org.zuzex.constant.Constants.SHOP_NOT_FOUND;

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
        Shop shop = shopRepository.findByIdOptional(id)
                .orElseThrow(() -> new ShopNotFoundException(SHOP_NOT_FOUND));
        log.info("IN getShopById - id: {} successfully get by id", id);
        return shop;
    }

    @Transactional
    @Override
    public Shop updateShopName(Shop shop, Long shopId) {
        Shop shopDb = getShopById(shopId);
        shopDb.setName(shop.getName());
        shopRepository.persist(shopDb);
        log.info("IN updateShop - shop: {} successfully updated", shopDb);
        return shopDb;
    }

    @Override
    public void deleteShop(Long id) {
        shopRepository.deleteById(id);
    }
}
