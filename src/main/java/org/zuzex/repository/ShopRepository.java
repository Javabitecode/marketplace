package org.zuzex.repository;

import io.quarkus.mongodb.panache.PanacheMongoRepositoryBase;
import org.zuzex.model.Shop;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class ShopRepository implements PanacheMongoRepositoryBase<Shop, Long> {

    public Shop findByName(String name) {
        return find("name", name).firstResult();
    }
}
