package org.zuzex.model;

import io.quarkus.mongodb.panache.PanacheMongoEntityBase;
import io.quarkus.mongodb.panache.common.MongoEntity;
import lombok.*;
import org.bson.codecs.pojo.annotations.BsonId;
import org.bson.codecs.pojo.annotations.BsonProperty;

import javax.persistence.*;

@Setter
@Getter
@ToString
@MongoEntity(collection = "Shop")
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
public class Shop {

    @BsonId
    private Long id;

    @BsonProperty("name")
    private String name;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Shop))
            return false;
        Shop entity = (Shop) o;
        return id != null &&
                id.equals(entity.getId());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
