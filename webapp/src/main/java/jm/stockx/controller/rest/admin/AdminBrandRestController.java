package jm.stockx.controller.rest.admin;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jm.stockx.BrandService;
import jm.stockx.dto.BrandDto;
import jm.stockx.dto.BrandPostDto;
import jm.stockx.dto.BrandPutDto;
import jm.stockx.entity.Brand;
import jm.stockx.util.Response;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.LinkedList;
import java.util.List;

@RestController
@RequestMapping(value = "/rest/api/admin/brand")
@Tag(name = "brand", description = "Brand API")
@Slf4j
public class AdminBrandRestController {
    private static final Logger logger = LoggerFactory.getLogger(AdminBrandRestController.class);

    private final BrandService brandService;

    public AdminBrandRestController(BrandService brandService) {
        this.brandService = brandService;
    }

    @GetMapping
    @Operation(
            operationId = "getAllBrands",
            summary = "Get all brands",
            responses = {
                    @ApiResponse(responseCode = "200",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(type = "array", implementation = Brand.class)
                            ),
                            description = "OK: brand list received"
                    ),
                    @ApiResponse(responseCode = "400", description = "NOT_FOUND: no brands found")
            })
    public Response<List<BrandDto>> getAllBrands() {
        List<BrandDto> brandDtos = new LinkedList<>();
        List<Brand> brands = brandService.getAll();
        for (Brand brand : brands
        ) {BrandDto brandDto = new BrandDto(brand);
        brandDtos.add(brandDto);
        }
        logger.info("Получен список брендов. Всего {} записей.", brandDtos.size());
        return Response.ok(brandDtos);
    }

    @GetMapping(value = "/{id}")
    @Operation(
            operationId = "getBrandById",
            summary = "Get brand by id",
            responses = {
                    @ApiResponse(responseCode = "200",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(type = "array", implementation = Brand.class)
                            ),
                            description = "OK: brand received"
                    ),
                    @ApiResponse(responseCode = "400", description = "NOT_FOUND: brand")
            })
    public Response<BrandDto> getBrandById(@PathVariable Long id) {
        if (brandService.isBrandExist(id)) {
            BrandDto brandDto = brandService.getBrandDtoById(id);
            logger.info("Получен бренд {} ", brandDto);
            return Response.ok(brandDto);
        }
        logger.warn("Бренд с id = {} в базе не найден", id);
        return Response.error(HttpStatus.BAD_REQUEST, "Brand not found");
    }

    @PostMapping
    @Operation(
            operationId = "createBrand",
            summary = "Create brand",
            responses = {
                    @ApiResponse(responseCode = "200",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = Brand.class)
                            ),
                            description = "OK: brand created"
                    ),
                    @ApiResponse(responseCode = "400", description = "NOT_FOUND: brand was not created")
            })
    public Response<?> createBrand(@RequestBody Brand brand) {
        if (brandService.isBrandExist(brand.getId())) {
            logger.warn("Бренд> {} уже существует в базе", brand.getName());
            return Response.error(HttpStatus.BAD_REQUEST, "This brand already exists in database");
        }
        brandService.create(brand);
        logger.info("Бренд {} успешно создан", brand);
        return Response.ok().build();
    }

    @PutMapping
    @Operation(
            operationId = "updateBrand",
            summary = "Update brand",
            responses = {
                    @ApiResponse(responseCode = "200",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = Brand.class)
                            ),
                            description = "OK: brand updated successfully"
                    ),
                    @ApiResponse(responseCode = "400", description = "NOT_FOUND: unable to update brand")
            })
    public Response<?> updateBrand(@RequestBody BrandPutDto brandPutDto) {
        String brandName = brandPutDto.getName();
        if (brandService.isBrandExist(brandPutDto.getId())) {
            Brand updateBrand = new Brand(brandPutDto.getId(), brandPutDto.getName());
            brandService.update(updateBrand);
            logger.info("Бренд {} успешно обновлен", brandName);
            return Response.ok().build();
        }
        logger.warn("Бренд {} в базе не найден", brandName);
        return Response.error(HttpStatus.BAD_REQUEST, "Brand not found");
    }

    @DeleteMapping(value = "/{id}")
    @Operation(
            operationId = "deleteBrand",
            summary = "Delete brand",
            responses = {
                    @ApiResponse(responseCode = "200", description = "OK: brand deleted successfully"),
                    @ApiResponse(responseCode = "400", description = "NOT FOUND: no brand with such id")
            }
    )
    public Response<?> deleteBrand(@PathVariable Long id) {
        if (brandService.isBrandExist(id)) {
            brandService.delete(id);
            logger.info("Бренд с id = {} успешно удалён", id);
            return Response.ok().build();
        }
        logger.warn("Бренд с id = {} в базе не найден", id);
        return Response.error(HttpStatus.BAD_REQUEST, "Brand not found");
    }
}
