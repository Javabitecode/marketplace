package org.zuzex.model;

import io.quarkus.mongodb.panache.common.MongoEntity;
import lombok.*;
import org.bson.codecs.pojo.annotations.BsonId;
import org.bson.codecs.pojo.annotations.BsonProperty;
import org.zuzex.enums.Currency;

import java.util.UUID;

import static org.zuzex.enums.Currency.RUB;


@Setter
@Getter
@ToString
@MongoEntity(collection = "product")
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
public class Product {
    @BsonId
    private Long id;

    @BsonProperty("name")
    private String name;

    @BsonProperty("price")
    private Long price;

    @BsonProperty("currency")
    @Builder.Default
    private Currency currency = RUB;

    @BsonProperty("article")
    private UUID article;

    @BsonProperty("quantity")
    private Long quantity;

    @ToString.Exclude
    private Category category;

    @ToString.Exclude
    private Shop shop;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Product)) return false;
        return id != null && id.equals(((Product) o).getId());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
