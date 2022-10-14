package org.zuzex.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.zuzex.enums.CURRENCY;

import javax.persistence.*;
import java.util.UUID;

import static org.zuzex.enums.CURRENCY.RUB;


@Setter
@Getter
@ToString
@Entity(name = "Product")
@Table(name = "product")
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "product_seq")
    @SequenceGenerator(name = "product_seq", sequenceName = "product_sequence")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "price")
    private Long price;

    @Enumerated(EnumType.STRING)
    @Builder.Default
    private CURRENCY currency = RUB;

    @Column(name = "article",
            nullable = false,
            updatable = false)
    private UUID article;

    @Column(name = "quantity", nullable = false)
    private Long quantity;

    @ManyToOne
    @JoinColumn(name = "category_id", nullable = false)
    private Category category;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "shop_id", nullable = false)
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
