package org.zuzex.repository;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import org.jboss.resteasy.annotations.Query;
import org.zuzex.model.Shop;

import javax.enterprise.context.ApplicationScoped;
import java.util.List;

@ApplicationScoped
public class ShopRepository implements PanacheRepository<Shop> {

    public Shop findByName(String name){
        return find("name",name).firstResult();
    }
}
