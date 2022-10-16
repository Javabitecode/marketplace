package org.zuzex.repository;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import org.zuzex.model.Category;
import org.zuzex.model.Shop;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class CategoryRepository implements PanacheRepository<Category> {
    public Category findByName(String name){
        return find("name",name).firstResult();
    }
}
