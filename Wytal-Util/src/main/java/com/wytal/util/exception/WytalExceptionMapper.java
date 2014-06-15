package com.wytal.util.exception;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response.ResponseBuilder;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import com.wytal.util.error.Error;
import com.wytal.util.error.Errors;
import com.wytal.util.response.Response;

@Produces(MediaType.APPLICATION_JSON)
@Provider
public class WytalExceptionMapper implements ExceptionMapper<WytalException>{

    @Override
    public javax.ws.rs.core.Response toResponse(WytalException fault) {
             ResponseBuilder r = javax.ws.rs.core.Response.status(javax.ws.rs.core.Response.Status.INTERNAL_SERVER_ERROR);
             r.entity(getErrorResponse(fault));
             return r.build();
    }

    private Response getErrorResponse(WytalException exception){
           Error error = new Error();
            error.setDescription(exception.description);
            error.setCode(exception.getMessage());
            error.setDetails(exception.details);
            Errors err  = new Errors();
            List<Error> errorList = new  ArrayList<>();
            errorList.add(error);
            err.setError(errorList);
            Response response = new Response();
            response.setErrors(err);
            return response;
    }


}
