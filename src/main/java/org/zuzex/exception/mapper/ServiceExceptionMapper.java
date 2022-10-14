package org.zuzex.exception.mapper;

import lombok.extern.slf4j.Slf4j;
import org.zuzex.exception.ErrorResponse;
import org.zuzex.exception.ServiceException;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;
import java.util.UUID;

@Provider
@Slf4j
public class ServiceExceptionMapper implements ExceptionMapper<ServiceException> {

    @Override
    public Response toResponse(ServiceException e) {
        String errorId = UUID.randomUUID().toString();
        log.error("Service exception. ErrorId[{}]", errorId);
        ErrorResponse.ErrorMessage errorMessage = new ErrorResponse.ErrorMessage(e.getMessage());
        ErrorResponse errorResponse = new ErrorResponse(errorId, errorMessage);
        return Response.status(Response.Status.BAD_REQUEST).entity(errorResponse).build();
    }
}