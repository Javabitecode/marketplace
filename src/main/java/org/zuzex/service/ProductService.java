package org.zuzex.service;

import org.zuzex.model.Product;

import javax.transaction.Transactional;
import java.util.List;

public interface ProductService {

    @Transactional
    void sellProduct(Long productId);

    Product updateProduct(Product product);

    List<Product> getAllProduct();

    Product getProductById(Long id);

    void deleteProductById(Long id);
}
