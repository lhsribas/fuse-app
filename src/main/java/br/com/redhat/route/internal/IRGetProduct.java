package br.com.redhat.route.internal;

import br.com.redhat.processor.ExchangeBodyInToBodyOutProcessor;
import br.com.redhat.route.rest.RRProductApi;
import org.apache.camel.LoggingLevel;
import org.apache.camel.builder.RouteBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class IRGetProduct extends RouteBuilder {
    private static final Logger logger = LoggerFactory.getLogger(IRGetProduct.class);

    @Override
    public void configure() throws Exception {
        from("direct:get-products")
                .routeId("ir-get-products-id")
                .description("")

                .toD("http4://{{url.get.products}}?bridgeEndpoint=true&connectionRequest=300&connectTimeout=300&socketTimeout=300")
                .process(new ExchangeBodyInToBodyOutProcessor())
                .log(LoggingLevel.INFO, logger, "${body}")

                .end();
    }
}
