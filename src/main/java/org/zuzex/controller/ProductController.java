package org.zuzex.controller;

import lombok.RequiredArgsConstructor;
import org.zuzex.dto.ProductDto;
import org.zuzex.model.Product;
import org.zuzex.service.ProductMapper;
import org.zuzex.service.ProductService;

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

    @GET
    @Path(("/{productId}"))
    public Product getProductById(@PathParam("productId") Long productId) {
        return productService.getProductById(productId);
    }

    @POST
    @Path(("/shops/{shopId}"))
    public ProductDto addProductToShop(@PathParam("shopId") Long shopId,
                                       ProductDto productDto) {
        Product product = productMapper.toProduct(productDto);
        return productMapper.toProductDto(productService.addProductToShop(product, shopId));
    }

    @GET
    public List<ProductDto> getAllProducts() {
        return productService.getAllProduct()
                .stream()
                .map(productMapper::toProductDto)
                .toList();
    }

    @POST
    @Path(("/sell/{productId}"))
    public void sellProduct(@PathParam("productId") Long productId) {
        productService.sellProduct(productId);
    }

    @POST
    @Path(("{productId}"))
    public ProductDto updateProduct(@PathParam("productId") Long productId,
                                    ProductDto productDto) {
        Product product = productMapper.toProduct(productDto);
        return productMapper.toProductDto(productService.updateProduct(product, productId));
    }

    @DELETE
    public void deleteProductById(@PathParam("productId") Long productId) {
        productService.deleteProductById(productId);
    }
}