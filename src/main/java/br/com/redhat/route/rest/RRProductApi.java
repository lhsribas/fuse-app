package br.com.redhat.route.rest;

import org.apache.camel.LoggingLevel;
import org.apache.camel.builder.RouteBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;

@Component
public class RRProductApi extends RouteBuilder {
    private static final Logger logger = LoggerFactory.getLogger(RRProductApi.class);

    @Override
    public void configure() throws Exception {
        rest("/products")
                .description("")

                .get("/all")

                .consumes(MediaType.APPLICATION_JSON_VALUE)
                .produces(MediaType.APPLICATION_JSON_VALUE)

                .route()
                .routeId("get-products")
                .to("direct:get-products")
                .log(LoggingLevel.DEBUG, logger, "Request dispatcher successfully to direct:get-products")
                .endRest();
    }
}
