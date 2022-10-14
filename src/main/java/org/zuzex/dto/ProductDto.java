package org.zuzex.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.zuzex.enums.CURRENCY;
import org.zuzex.model.Category;

import java.util.UUID;

import static org.zuzex.enums.CURRENCY.RUB;

@Data
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ProductDto {
    @Builder.Default
    private CURRENCY currency = RUB;
    private String name;
    private Long price;
    private UUID article;
    private Long quantity;
    private CategoryDto category;
    private ShopDto shopDto;
}
