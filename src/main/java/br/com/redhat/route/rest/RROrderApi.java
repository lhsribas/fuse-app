package br.com.redhat.route.rest;

import br.com.redhat.model.Order;
import org.apache.camel.LoggingLevel;
import org.apache.camel.builder.RouteBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;

@Component
public class RROrderApi extends RouteBuilder {
    private static final Logger logger = LoggerFactory.getLogger(RROrderApi.class);

    @Override
    public void configure() throws Exception {
        rest("/orders")
                .description("")

                .post()

                .consumes(MediaType.APPLICATION_JSON_VALUE)
                .produces(MediaType.APPLICATION_JSON_VALUE)
                .type(Order.class)

                .route()
                .routeId("post-orders")
                .to("direct:send-order-to-mom")
                .log(LoggingLevel.DEBUG, logger, "Request dispatcher successfully to direct:send-order-to-mom")
                .endRest()

                .get("/all")

                .consumes(MediaType.APPLICATION_JSON_VALUE)
                .produces(MediaType.APPLICATION_JSON_VALUE)

                .route()
                .routeId("get-orders")
                .to("direct:get-orders")
                .log(LoggingLevel.DEBUG, logger, "Request dispatcher successfully to direct:get-orders")
                .endRest();
    }
}
