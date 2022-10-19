package org.zuzex.model;

import io.quarkus.mongodb.panache.common.MongoEntity;
import lombok.*;
import org.bson.codecs.pojo.annotations.BsonId;
import org.bson.codecs.pojo.annotations.BsonProperty;

@Setter
@Getter
@ToString
@MongoEntity(collection = "Category")
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
public class Category {
    @BsonId
    private Long id;

    @BsonProperty("name")
    private String name;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Category))
            return false;
        Category entity = (Category) o;
        return id != null &&
                id.equals(entity.getId());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
