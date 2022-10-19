package org.zuzex.repository;

import io.quarkus.mongodb.panache.PanacheMongoRepositoryBase;
import org.zuzex.model.Check;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class CheckRepository implements PanacheMongoRepositoryBase<Check, Long> {
}
