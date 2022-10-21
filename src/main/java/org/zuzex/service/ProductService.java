package org.zuzex.service;

import org.zuzex.model.Product;

import javax.transaction.Transactional;
import java.util.List;

public interface ProductService {

    @Transactional
    Product addProductToShop(Product product, Long shopId, Long categoryId);

    @Transactional
    Product sellProduct(Long productId);

    Product updateProduct(Product product, Long productId, Long shopId, Long categoryId);

    List<Product> getAllProduct();

    Product getProductById(Long id);

    void deleteProductById(Long id);
}
