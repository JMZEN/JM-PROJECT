package jm.stockx.dto;

import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Null;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class StyleDto {

    @Null
    private Long id;

    @NotBlank
    private String name;
}