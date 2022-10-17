package org.zuzex.controller;

import io.quarkus.security.identity.SecurityIdentity;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.zuzex.dto.ShopDto;
import org.zuzex.model.Shop;
import org.zuzex.service.ShopService;
import org.zuzex.util.mapper.ShopMapper;

import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

import static javax.ws.rs.core.Response.Status.CREATED;

@Slf4j
@Path("/api/v1/shops")
@Produces(MediaType.APPLICATION_JSON)
@Consumes({MediaType.TEXT_PLAIN, MediaType.APPLICATION_JSON})
@RequiredArgsConstructor
public class ShopController {

    private final ShopService shopService;
    private final ShopMapper shopMapper;

    private final SecurityIdentity identity;

    @PermitAll
    @GET
    @Path("/{shopId}")
    public ShopDto getShopById(@PathParam("shopId") Long id) {
        return shopMapper.toShopDto(shopService.getShopById(id));
    }

    @PermitAll
    @GET
    public List<ShopDto> getAllShop() {
        return shopService.getAllShop()
                .stream()
                .map(shopMapper::toShopDto)
                .toList();
    }

    @RolesAllowed("user")
    @POST
    public Response createShop(ShopDto shopDto) {
        Shop shop = shopMapper.toShop(shopDto);
        ShopDto response = shopMapper.toShopDto(shopService.createShop(shop));
        return Response.status(CREATED).entity(response).build();
    }

    /*TODO Проверить создателя магазина и дать права обновления только ему*/
    @RolesAllowed(value = {"user", "admin"})
    @PUT
    @Path("/{shopId}")
    public Response updateShopName(@PathParam("shopId") Long shopId,
                                   ShopDto shopDto) {
        log.info("User updateShopName - user: {}", identity.getPrincipal().getName());
        Shop shop = shopMapper.toShop(shopDto);
        ShopDto response = shopMapper.toShopDto(shopService.updateShopName(shop, shopId));
        return Response.ok().entity(response).build();
    }

    /*TODO Проверить создателя магазина и дать права обновления только ему*/
    @RolesAllowed(value = {"user", "admin"})
    @DELETE
    @Path("/{shopId}")
    public Response deleteShopById(@PathParam("shopId") Long shopId) {
        shopService.deleteShop(shopId);
        return Response.noContent().build();
    }
}
