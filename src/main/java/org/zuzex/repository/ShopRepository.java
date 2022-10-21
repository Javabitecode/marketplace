package org.zuzex.repository;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import org.zuzex.model.Shop;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class ShopRepository implements PanacheRepository<Shop> {

    public Shop findByName(String name) {
        return find("name", name).firstResult();
    }
}
