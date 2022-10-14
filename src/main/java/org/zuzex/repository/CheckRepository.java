package org.zuzex.repository;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import org.zuzex.model.Check;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class CheckRepository implements PanacheRepository<Check> {
}
