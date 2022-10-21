package org.zuzex.model;

import io.quarkus.mongodb.panache.common.MongoEntity;
import lombok.*;
import org.bson.codecs.pojo.annotations.BsonId;
import org.bson.codecs.pojo.annotations.BsonProperty;

import java.util.List;

@Setter
@Getter
@ToString
@MongoEntity(collection = "checks")
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
public class Check {
    @BsonId
    private Long id;

    @BsonProperty("name")
    private String name;

    @BsonProperty("shop_name")
    private String shopName;

    @BsonProperty("product_imprints")
    private List<ProductImprint> productImprints;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Check))
            return false;
        Check entity = (Check) o;
        return id != null &&
                id.equals(entity.getId());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
