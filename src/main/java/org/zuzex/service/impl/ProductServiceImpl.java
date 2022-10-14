package org.zuzex.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.zuzex.exception.ProductIsOutOfStockException;
import org.zuzex.exception.ProductNotFoundException;
import org.zuzex.exception.ServiceException;
import org.zuzex.model.Product;
import org.zuzex.repository.ProductRepository;
import org.zuzex.service.ProductService;

import javax.enterprise.context.ApplicationScoped;
import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.Objects.isNull;
import static org.zuzex.constant.Constants.*;

@Slf4j
@ApplicationScoped
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    @Transactional
    @Override
    public void sellProduct(Long productId) {
        Product productDb = getProductById(productId);
        if (productDb.getQuantity() <= 0)
            throw new ProductIsOutOfStockException(PRODUCT_OUT_STOCK);
        log.info("IN productPurchase - quantity: {} before purchase", productDb.getQuantity());
        productDb.setQuantity(productDb.getQuantity() - 1);
        updateProductForPurchase(productDb);
    }
    private Product updateProductForPurchase(Product product) {
        if (isNull(product.getId()))
            throw new ServiceException(PRODUCT_DOES_NOT_ID);
        productRepository.persist(product);
        log.info("IN updateProduct - product: {} successfully update", product);
        return product;
    }

    @Override
    public Product updateProduct(Product product) {
        if (isNull(product.getId()))
            throw new ServiceException(PRODUCT_DOES_NOT_ID);
        Product productDb = getProductById(product.getId());
        product.setId(productDb.getId());
        productRepository.persist(product);
        log.info("IN updateProduct - product: {} successfully update", product);
        return product;
    }

    @Override
    public List<Product> getAllProduct() {
        return productRepository.findAll()
                .stream()
                .collect(Collectors.toList());
    }

    @Override
    public Product getProductById(Long id) {
        Product product = productRepository.findById(id);
        if (isNull(product))
            throw new ProductNotFoundException(PRODUCT_NOT_FOUND);
        log.info("IN getProductById - id: {} successfully get by id", id);
        return product;
    }

    @Override
    public void deleteProductById(Long id) {
        productRepository.deleteById(id);
        log.info("IN deleteProductById - id: {} successfully deleted by id", id);
    }
}
