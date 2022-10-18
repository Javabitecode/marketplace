package org.zuzex.controller;

import lombok.RequiredArgsConstructor;
import org.zuzex.model.User;

import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import static org.zuzex.constant.AppConstants.ADMIN;
import static org.zuzex.constant.UriConstants.USER_ID;
import static org.zuzex.constant.UriConstants.USER_PATH_V1;

@Path(USER_PATH_V1)
@Produces(MediaType.APPLICATION_JSON)
@Consumes({MediaType.TEXT_PLAIN, MediaType.APPLICATION_JSON})
@RequiredArgsConstructor
public class UserController {

    @GET
    @RolesAllowed(ADMIN)
    @Path(USER_ID)
    public User getUserById(@PathParam("userId") Long id) {
        return User.findById(id);
    }

    /*TODO Перенести в отдельный сервис работу с users*/
    @Transactional
    @PermitAll
    @POST
    public Response createUser(User user) {
        User.add(user.username, user.password, "user");
        return Response.noContent().build();
    }
}
