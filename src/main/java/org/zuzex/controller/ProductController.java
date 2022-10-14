package org.zuzex.controller;

import org.zuzex.model.Product;
import org.zuzex.model.User;
import org.zuzex.service.ProductService;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Path("/api/v1/products")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ProductController {

    @Inject
    ProductService productService;

    @GET
    @Path(("/{productId}"))
    public Product getProductById(@PathParam("productId") Long productId) {
        return productService.getProductById(productId);
    }
}