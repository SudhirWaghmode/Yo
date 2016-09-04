package com.samasara.app.core.resources;

import com.google.gson.Gson;
import com.google.inject.Inject;
import com.samasara.app.core.models.requestModels.UserDetails;
import com.samasara.app.core.services.LoginService;
import io.dropwizard.hibernate.UnitOfWork;
import org.eclipse.jetty.http.HttpStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

/**
 * Created by waghmode.tayappa on 04/09/16.
 */
@Path("/login")
public class LoginResource {
    private final static Logger logger = LoggerFactory.getLogger(LoginResource.class);
    private final static Gson gson = new Gson();

    @Inject
    private LoginService loginService;

    @GET
    public Response get(){
        return Response.ok().entity("yo!!!!!!").build();
    }

    @POST
    @Path("/register")
    @UnitOfWork
    public Response registerUser(String body){
        logger.info("Parameter received to register user : {}",body);
        if (body == null)
            return Response.status(HttpStatus.BAD_REQUEST_400).entity("Please provide all details ").build();

        UserDetails details = gson.fromJson(body,UserDetails.class);
        boolean yo = loginService.registerUser(details);
        return Response.ok().entity(yo).build();
    }

    @POST
    @Path("/authenticate")
    @UnitOfWork
    public Response isAuthenticateUser(String body){
        logger.info("Parameter received to authenticate user : {}",body);
        if (body == null)
            return Response.status(HttpStatus.BAD_REQUEST_400).entity("Please provide all details ").build();

        UserDetails details = gson.fromJson(body,UserDetails.class);
        boolean yo = loginService.isAuthenticateUser(details);
        return Response.ok().entity(yo).build();
    }
}
