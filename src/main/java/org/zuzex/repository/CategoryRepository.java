package org.zuzex.repository;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import org.zuzex.model.Category;

import javax.enterprise.context.ApplicationScoped;
import java.util.Optional;

@ApplicationScoped
public class CategoryRepository implements PanacheRepository<Category> {
    public Optional<Category> findByName(String name) {
        return find("name", name).stream().findFirst();
    }
}
