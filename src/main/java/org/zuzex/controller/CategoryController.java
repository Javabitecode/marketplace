package org.zuzex.controller;

import io.quarkus.cache.CacheResult;
import lombok.RequiredArgsConstructor;
import org.zuzex.dto.CategoryDto;
import org.zuzex.model.Category;
import org.zuzex.service.CategoryService;
import org.zuzex.util.mapper.ProductMapper;

import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

import static org.zuzex.constant.AppConstants.ADMIN;
import static org.zuzex.constant.UriConstants.CATEGORY_ID;
import static org.zuzex.constant.UriConstants.CATEGORY_PATH_V1;

@RequiredArgsConstructor
@Path(CATEGORY_PATH_V1)
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
/*TODO Проверить создателя ресурса и дать права обновления только ему и админую(AUTH).
 *  Перенести в интерфейс*/
public class CategoryController {

    private final CategoryService categoryService;
    private final ProductMapper productMapper;

    @PermitAll
    @GET
    @Path(CATEGORY_ID)
    public CategoryDto getCategoryById(@PathParam("categoryId") Long categoryId) {
        return productMapper.toCategoryDto(categoryService.getCategoryById(categoryId));
    }

    @PermitAll
    @CacheResult(cacheName = "get-categories-cache")
    @GET
    public List<CategoryDto> getAllCategories() {
        return categoryService.getAllCategory()
                .stream()
                .map(productMapper::toCategoryDto)
                .toList();
    }

    @RolesAllowed(ADMIN)
    @POST
    public Response createCategory(CategoryDto categoryDto) {
        Category category = productMapper.toCategory(categoryDto);
        CategoryDto categoryAfterSave = productMapper.toCategoryDto(categoryService.createCategory(category));
        return Response.status(Response.Status.CREATED).entity(categoryAfterSave).build();
    }

    @RolesAllowed(ADMIN)
    @DELETE
    @Path(CATEGORY_ID)
    public Response deleteCategoryById(@PathParam("categoryId") Long categoryId) {
        categoryService.deleteCategory(categoryId);
        return Response.noContent().build();
    }
}
