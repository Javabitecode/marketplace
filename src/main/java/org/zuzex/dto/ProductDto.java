package org.zuzex.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.zuzex.enums.Currency;

import javax.validation.constraints.NotNull;
import java.util.UUID;

import static org.zuzex.enums.Currency.RUB;

@Data
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ProductDto {

    @Builder.Default
    private Currency currency = RUB;

    @NotNull(message = "Имя продукта не должно равняться null")
    private String name;

    @NotNull(message = "Цена продукта не должна равняться null")
    private Long price;

    private UUID article;

    @NotNull(message = "Количество не должно равняться null")
    private Long quantity;

    private CategoryDto categoryDto;
    private ShopDto shopDto;
}
