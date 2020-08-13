package jm.stockx.dto;

import jm.stockx.entity.Item;
import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class ItemPutDto {

    @NotNull
    private Long id;

    @NotBlank(message = "Добавьте название товара. Название не может быть пустым или состоять из одних пробелов")
    private String name;

    @Positive(message = "Цена должна быть положительной")
    @NotNull
    private Double price;

    @Positive(message = "Цена должна быть положительной")
    @NotNull
    private Double lowestAsk;

    @Positive(message = "Ставка должна быть положительной")
    @NotNull
    private Double highestBid;

    @NotBlank
    private String condition;

    public ItemPutDto(Item item) {
        this.id = item.getId();
        this.name = item.getName();
        this.price = item.getPrice();
        this.lowestAsk = item.getLowestAsk();
        this.highestBid = item.getHighestBid();
        this.condition = item.getCondition();
    }
}
