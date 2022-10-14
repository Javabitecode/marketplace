package org.zuzex.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.zuzex.enums.CURRENCY;

import java.util.UUID;

import static org.zuzex.enums.CURRENCY.RUB;

@Data
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
public class ProductImprint {
    @Builder.Default
    private CURRENCY currency = RUB;
    private String name;
    private Long price;
    private UUID article;
    private Category category;
    private String shopName;
}
