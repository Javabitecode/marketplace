package org.zuzex.repository;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import org.zuzex.model.Product;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class ProductRepository implements PanacheRepository<Product> {
}
