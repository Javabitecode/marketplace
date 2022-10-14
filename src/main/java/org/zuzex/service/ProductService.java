package org.zuzex.service;

import org.zuzex.model.Product;

import javax.transaction.Transactional;
import java.util.List;

public interface ProductService {

    @Transactional
    Product addProductToShop(Product product, Long shopId);

    @Transactional
    void sellProduct(Long productId);

    Product updateProduct(Product product, Long productId);

    List<Product> getAllProduct();

    Product getProductById(Long id);

    void deleteProductById(Long id);
}
