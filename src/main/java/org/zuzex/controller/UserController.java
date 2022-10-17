package org.zuzex.controller;

import lombok.RequiredArgsConstructor;
import org.zuzex.model.User;

import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/api/v1/users")
@Produces(MediaType.APPLICATION_JSON)
@Consumes({MediaType.TEXT_PLAIN, MediaType.APPLICATION_JSON})
@RequiredArgsConstructor
public class UserController {

    @GET
    @RolesAllowed("admin")
    @Path("/{userId}")
    public User getUserById(@PathParam("userId") Long id) {
        return User.findById(id);
    }

    @Transactional
    @PermitAll
    @POST
    public Response createUser(User user) {
        User.add(user.username, user.password, "user");
        return Response.noContent().build();
    }
}
