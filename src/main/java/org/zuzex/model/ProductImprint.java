package org.zuzex.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.zuzex.enums.Currency;

import java.io.Serializable;
import java.util.UUID;

import static org.zuzex.enums.Currency.RUB;

@Data
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
public class ProductImprint implements Serializable {
    @Builder.Default
    private Currency currency = RUB;
    private String name;
    private Long price;
    private UUID article;
    private Category category;
    private String shopName;
}
