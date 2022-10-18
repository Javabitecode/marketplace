package org.zuzex.controller;

import io.quarkus.cache.CacheResult;
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
import static org.zuzex.constant.AppConstants.ADMIN;
import static org.zuzex.constant.AppConstants.USER;
import static org.zuzex.constant.UriConstants.SHOP_ID;
import static org.zuzex.constant.UriConstants.SHOP_PATH_V1;

@Slf4j
@Path(SHOP_PATH_V1)
@Produces(MediaType.APPLICATION_JSON)
@Consumes({MediaType.TEXT_PLAIN, MediaType.APPLICATION_JSON})
@RequiredArgsConstructor
/*TODO Проверить создателя ресурса и дать права обновления только ему и админую(AUTH).
 *  Перенести в интерфейс*/
public class ShopController {

    private final ShopService shopService;
    private final ShopMapper shopMapper;

    private final SecurityIdentity identity;

    @PermitAll
    @GET
    @Path(SHOP_ID)
    public ShopDto getShopById(@PathParam("shopId") Long id) {
        return shopMapper.toShopDto(shopService.getShopById(id));
    }

    @PermitAll
    @CacheResult(cacheName = "get-shops-cache")
    @GET
    public List<ShopDto> getAllShop() {
        return shopService.getAllShop()
                .stream()
                .map(shopMapper::toShopDto)
                .toList();
    }

    @RolesAllowed(value = {USER, ADMIN})
    @POST
    public Response createShop(ShopDto shopDto) {
        Shop shop = shopMapper.toShop(shopDto);
        ShopDto response = shopMapper.toShopDto(shopService.createShop(shop));
        return Response.status(CREATED).entity(response).build();
    }

    @RolesAllowed(value = {USER, ADMIN})
    @PUT
    @Path(SHOP_ID)
    public Response updateShopName(@PathParam("shopId") Long shopId,
                                   ShopDto shopDto) {
        log.info("User updateShopName - user: {}", identity.getPrincipal().getName());
        Shop shop = shopMapper.toShop(shopDto);
        ShopDto response = shopMapper.toShopDto(shopService.updateShopName(shop, shopId));
        return Response.ok().entity(response).build();
    }

    @RolesAllowed(value = {USER, ADMIN})
    @DELETE
    @Path(SHOP_ID)
    public Response deleteShopById(@PathParam("shopId") Long shopId) {
        shopService.deleteShop(shopId);
        return Response.noContent().build();
    }
}
