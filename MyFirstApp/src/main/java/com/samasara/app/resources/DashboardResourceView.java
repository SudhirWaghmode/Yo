package com.samasara.app.resources;

import com.codahale.metrics.annotation.Timed;
import com.samasara.app.views.DashboardView;
import io.dropwizard.views.View;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import java.nio.charset.StandardCharsets;

/**
 * Created by waghmode.tayappa on 04/09/16.
 */
@Path("/log")
@Produces("text/html")
public class DashboardResourceView {
    Logger logger = LoggerFactory.getLogger(DashboardResourceView.class);

    @GET
    @Timed
    public View getMainPage(){
        logger.info("received call for main page");
        return new DashboardView("dashboard.ftl", StandardCharsets.ISO_8859_1);
    }
}
