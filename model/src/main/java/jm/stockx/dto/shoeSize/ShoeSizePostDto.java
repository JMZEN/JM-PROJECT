package jm.stockx.dto.shoeSize;

import jm.stockx.enums.ItemSizeTypes;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class ShoeSizePostDto {

    @Min(3)
    @NotNull
    private Double size;

    @NotNull
    private ItemSizeTypes sizeTypes;

    public ShoeSizePostDto(Long id, Double shoeSize, ItemSizeTypes sizeTypes) {
        this.size = shoeSize;
        this.sizeTypes = sizeTypes;
    }
}
