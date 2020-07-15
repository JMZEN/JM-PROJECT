package jm.stockx.dto;

import jm.stockx.entity.Item;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class ItemPutDto {

    private long id;

    private double price;

    private double lowestAsk;

    private double highestBid;

    private LocalDate dateRelease;

    private String condition;

    private byte[] itemImage;

    public ItemPutDto(Item item) {
        this.id = item.getId();
        this.price = item.getPrice();
        this.lowestAsk = item.getLowestAsk();
        this.highestBid = item.getHighestBid();
        this.dateRelease = item.getDateRelease();
        this.condition = item.getCondition();
        this.itemImage = item.getItemImage();
    }

}
