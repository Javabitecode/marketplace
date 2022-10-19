package org.zuzex.repository;

import io.quarkus.mongodb.panache.PanacheMongoRepositoryBase;
import org.zuzex.model.Category;

import javax.enterprise.context.ApplicationScoped;
import java.util.Optional;

@ApplicationScoped
public class CategoryRepository implements PanacheMongoRepositoryBase<Category, Long> {
    public Optional<Category> findByName(String name) {
        return find("name", name).stream().findFirst();
    }
}
