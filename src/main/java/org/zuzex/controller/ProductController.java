package org.zuzex.controller;

import lombok.RequiredArgsConstructor;
import org.zuzex.dto.ProductDto;
import org.zuzex.model.Check;
import org.zuzex.model.Product;
import org.zuzex.service.CheckService;
import org.zuzex.util.mapper.ProductMapper;
import org.zuzex.service.ProductService;

import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@RequiredArgsConstructor
@Path("/api/v1/products")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ProductController {

    private final ProductMapper productMapper;
    private final ProductService productService;
    private final CheckService checkService;

    @GET
    @Path("/{productId}")
    public ProductDto getProductById(@PathParam("productId") Long productId) {
        return productMapper.toProductDto(productService.getProductById(productId));
    }

    @POST
    @Path("/shops/{shopId}/categories/{categoryId}")
    public ProductDto addProductToShop(@PathParam("shopId") Long shopId,
                                       @PathParam("categoryId") Long categoryId,
                                       @Valid ProductDto productDto) {
        Product product = productMapper.toProduct(productDto);
        return productMapper
                .toProductDto(productService.addProductToShop(product, shopId, categoryId));
    }

    @GET
    public List<ProductDto> getAllProducts() {
        return productService.getAllProduct()
                .stream()
                .map(productMapper::toProductDto)
                .toList();
    }

    @POST
    @Path("/sell/{productId}")
    public void sellProduct(@PathParam("productId") Long productId) {
        productService.sellProduct(productId);
    }

    @POST
    @Path("/sell")
    public Check sellProducts(List<Long> productIdList) {
        return checkService.createCheckByListProduct(productIdList);
    }

    @PUT
    @Path("/{productId}/shops/{shopId}/categories/{categoryId}")
    public ProductDto updateProduct(@PathParam("productId") Long productId,
                                    @PathParam("shopId") Long shopId,
                                    @PathParam("categoryId") Long categoryId,
                                    ProductDto productDto) {
        Product product = productMapper.toProduct(productDto);
        return productMapper.toProductDto(productService.updateProduct(product, productId, shopId, categoryId));
    }

    @DELETE
    @Path("/{productId}")
    public void deleteProductById(@PathParam("productId") Long productId) {
        productService.deleteProductById(productId);
    }
}