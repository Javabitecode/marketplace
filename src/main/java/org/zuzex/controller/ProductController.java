package org.zuzex.controller;

import io.quarkus.cache.CacheResult;
import lombok.RequiredArgsConstructor;
import org.zuzex.dto.ProductDto;
import org.zuzex.model.Check;
import org.zuzex.model.Product;
import org.zuzex.service.CheckService;
import org.zuzex.service.ProductService;
import org.zuzex.util.mapper.ProductMapper;

import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

import static org.zuzex.constant.AppConstants.ADMIN;
import static org.zuzex.constant.AppConstants.USER;
import static org.zuzex.constant.UriConstants.*;

@RequiredArgsConstructor
@Path(PRODUCT_PATH_V1)
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
/*TODO Проверить создателя ресурса и дать права обновления только ему и админую(AUTH).
 *  Перенести в интерфейс*/
public class ProductController {

    private final ProductMapper productMapper;
    private final ProductService productService;
    private final CheckService checkService;

    @GET
    @Path(PRODUCT_ID_PATH)
    public ProductDto getProductById(@PathParam(PRODUCT_ID) Long productId) {
        return productMapper.toProductDto(productService.getProductById(productId));
    }

    @RolesAllowed(value = {USER, ADMIN})
    @POST
    @Path(ADD_PRODUCT_TO_SHOP)
    public ProductDto addProductToShop(@PathParam(SHOP_ID) Long shopId,
                                       @PathParam(CATEGORY_ID) Long categoryId,
                                       @Valid ProductDto productDto) {
        Product product = productMapper.toProduct(productDto);
        return productMapper
                .toProductDto(productService.addProductToShop(product, shopId, categoryId));
    }

    @PermitAll
    @GET
    @CacheResult(cacheName = "get-products-cache")
    public List<ProductDto> getAllProducts() {
        return productService.getAllProduct()
                .stream()
                .map(productMapper::toProductDto)
                .toList();
    }

    @PermitAll
    @POST
    @Path(PRODUCT_ID_PATH)
    public void sellProduct(@PathParam(PRODUCT_ID) Long productId) {
        productService.sellProduct(productId);
    }

    @PermitAll
    @POST
    public Check sellProducts(List<Long> productIdList) {
        return checkService.createCheckByListProduct(productIdList);
    }

    @RolesAllowed(value = ADMIN)
    @PUT
    @Path(UPDATE_PRODUCT)
    public ProductDto updateProduct(@PathParam(PRODUCT_ID) Long productId,
                                    @PathParam(SHOP_ID) Long shopId,
                                    @PathParam(CATEGORY_ID) Long categoryId,
                                    ProductDto productDto) {
        Product product = productMapper.toProduct(productDto);
        return productMapper.toProductDto(productService.updateProduct(product, productId, shopId, categoryId));
    }

    @RolesAllowed(value = ADMIN)
    @DELETE
    @Path(PRODUCT_ID_PATH)
    public void deleteProductById(@PathParam(PRODUCT_ID) Long productId) {
        productService.deleteProductById(productId);
    }
}