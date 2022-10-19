package org.zuzex.repository;

import io.quarkus.mongodb.panache.PanacheMongoRepositoryBase;
import org.zuzex.model.Product;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class ProductRepository implements PanacheMongoRepositoryBase<Product, Long> {
}
