package org.zuzex.controller;

import org.zuzex.dto.ProductDto;
import org.zuzex.dto.ShopDto;
import org.zuzex.model.Product;
import org.zuzex.model.Shop;
import org.zuzex.service.ProductMapper;
import org.zuzex.service.ShopMapper;
import org.zuzex.service.ShopService;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

import static javax.ws.rs.core.Response.Status.CREATED;

@Path("/api/v1/shops")
@Produces(MediaType.APPLICATION_JSON)
@Consumes({MediaType.TEXT_PLAIN, MediaType.APPLICATION_JSON})
public class ShopController {
    @Inject
    ShopService shopService;
    @Inject
    ShopMapper shopMapper;

    @Inject
    ProductMapper productMapper;

    @GET
    @Path(("/{shopId}"))
    public ShopDto getShopById(@PathParam("shopId") Long id) {
        return shopMapper.toShopDto(shopService.getShopById(id));
    }


    @GET
    public List<ShopDto> getAllShop() {
        return shopService.getAllShop()
                .stream()
                .map(shop -> shopMapper.toShopDto(shop))
                .toList();
    }

    @PUT
    @Path(("/{shopId}"))
    public Response updateShop(@PathParam("shopId") Long shopId, ShopDto shopDto) {
        Shop shop = shopMapper.toShop(shopDto);
        shop.setId(shopId);
        ShopDto response = shopMapper.toShopDto(shopService.updateShop(shop));
        return Response.ok().entity(response).build();
    }

    @POST
    @Path(("/{shopId}"))
    public Response addNewProductToShop(@PathParam("shopId") Long shopId, ProductDto productDto) {
        Product product = productMapper.toProduct(productDto);
        ShopDto response = shopMapper.toShopDto(shopService.addNewProductToShop(product, shopId));
        return Response.status(CREATED).entity(response).build() ;
    }

    @POST
    @Path(("/{shopId}/products/{productId}"))
    public Response removeProductFromShop(@PathParam("shopId") Long shopId,
                                      @PathParam("productId") Long productId) {
        shopService.removeProductFromShop(productId, shopId);
        return Response.noContent().build();
    }

    @POST
    public Response createShop(ShopDto shopDto) {
        Shop shop = shopMapper.toShop(shopDto);
        ShopDto response = shopMapper.toShopDto(shopService.createShop(shop));
        return Response.status(CREATED).entity(response).build();
    }

    @DELETE
    @Path(("/{shopId}"))
    public Response deleteShopById(@PathParam("shopId") Long shopId) {
        shopService.deleteShop(shopId);
        return Response.noContent().build();
    }
}
