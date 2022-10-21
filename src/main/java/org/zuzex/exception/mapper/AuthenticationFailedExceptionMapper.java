package org.zuzex.exception.mapper;

import io.quarkus.security.AuthenticationFailedException;

import javax.annotation.Priority;
import javax.ws.rs.Priorities;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
@Priority(Priorities.AUTHENTICATION)
/*TODO Переписать ответ*/
public class AuthenticationFailedExceptionMapper implements ExceptionMapper<AuthenticationFailedException> {

    @Override
    public Response toResponse(AuthenticationFailedException exception) {
        return Response.status(401).header("WWW-Authenticate", "Basic realm=\"Quarkus\"").build();
    }
}