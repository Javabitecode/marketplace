package org.zuzex.repository;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import org.zuzex.model.User;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class UserRepository implements PanacheRepository<User> {

    public User findByIds(long id) {
        return findById(id);
    }
}
