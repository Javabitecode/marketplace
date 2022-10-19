package org.zuzex.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.zuzex.exception.ProductIsOutOfStockException;
import org.zuzex.exception.ProductNotFoundException;
import org.zuzex.exception.ServiceException;
import org.zuzex.model.Category;
import org.zuzex.model.Product;
import org.zuzex.model.Shop;
import org.zuzex.repository.ProductRepository;
import org.zuzex.service.CategoryService;
import org.zuzex.service.ProductService;
import org.zuzex.service.ShopService;

import javax.enterprise.context.ApplicationScoped;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.Objects.isNull;
import static org.zuzex.constant.ExceptionConstants.*;
import static org.zuzex.util.GeneratorId.generateId;

@Slf4j
@ApplicationScoped
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final ShopService shopService;
    private final CategoryService categoryService;

    @Override
    public Product addProductToShop(Product product, Long shopId, Long categoryId) {
        Shop shopDb = shopService.getShopById(shopId);
        Category category = categoryService.getCategoryById(categoryId);
        product.setId(generateId());
        product.setCategory(category);
        product.setShop(shopDb);
        productRepository.persist(product);
        log.info("IN addProductToShop - product: {} successfully add prod", product);
        return product;
    }

    @Override
    public Product sellProduct(Long productId) {
        Product productDb = getProductById(productId);
        if (productDb.getQuantity() <= 0)
            throw new ProductIsOutOfStockException(PRODUCT_OUT_STOCK);
        log.info("IN productPurchase - quantity: {} before purchase", productDb.getQuantity());
        productDb.setQuantity(productDb.getQuantity() - 1);
        Product productUpdate = updateProductForPurchase(productDb);
        log.info("IN productPurchase - quantity: {} after purchase", productUpdate.getQuantity());
        return productUpdate;
    }

    private Product updateProductForPurchase(Product product) {
        if (isNull(product.getId()))
            throw new ServiceException(PRODUCT_DOES_NOT_ID);
        productRepository.update(product);
        log.info("IN updateProductForPurchase - product: {} successfully update", product);
        return product;
    }

    /*TODO сделать update 1 запросм в бд*/
    @Override
    public Product updateProduct(Product productUpdate, Long productId, Long shopId, Long categoryId) {
        Product productDb = getProductById(productId);
        Shop shopDb = shopService.getShopById(shopId);
        Category categoryDb = categoryService.getCategoryById(categoryId);
        productUpdate = productDb.toBuilder()
                .name(productUpdate.getName())
                .article(productUpdate.getArticle())
                .quantity(productUpdate.getQuantity())
                .currency(productUpdate.getCurrency())
                .price(productUpdate.getPrice())
                .build();
        productRepository.update(productUpdate);
        log.info("IN updateProduct - product: {} successfully update", productUpdate);
        return productUpdate;
    }

    @Override
    public List<Product> getAllProduct() {
        return productRepository.findAll()
                .stream()
                .collect(Collectors.toList());
    }

    @Override
    public Product getProductById(Long id) {
        Product product = productRepository.findByIdOptional(id)
                .orElseThrow(() -> new ProductNotFoundException(PRODUCT_NOT_FOUND));
        log.info("IN getProductById - id: {} successfully get by id", id);
        return product;
    }

    @Override
    public void deleteProductById(Long id) {
        productRepository.deleteById(id);
        log.info("IN deleteProductById - id: {} successfully deleted by id", id);
    }
}
