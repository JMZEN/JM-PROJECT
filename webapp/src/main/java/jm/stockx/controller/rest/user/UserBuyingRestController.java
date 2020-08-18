package jm.stockx.controller.rest.user;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jm.stockx.BuyingService;
import jm.stockx.entity.BuyingInfo;
import jm.stockx.enums.Status;
import jm.stockx.util.Response;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/rest/api/user/buy")
@Tag(name = "buy", description = "Buy API")
@Slf4j
public class UserBuyingRestController {
    private final BuyingService buyingService;

    @Autowired
    public UserBuyingRestController(BuyingService buyingService) {
        this.buyingService = buyingService;
    }

    @PutMapping(value = "/{id}")
    @Operation(
            operationId = "updateBuyingStatus",
            summary = "Update Buying status by id",
            responses = {
                    @ApiResponse(responseCode = "200",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = BuyingInfo.class)
                            ),
                            description = "OK: buyingInfo updated successfully"
                    ),
                    @ApiResponse(responseCode = "400", description = "NOT_FOUND: unable to update buyingInfo")
            })
    public Response<?> updateBuyingStatusById(@PathVariable("id") Long id, Status status) {
        buyingService.updateBuyingStatus(id, status);
        log.info("Информация о продаже успешно обновлена");
        return Response.ok().build();
    }
}
