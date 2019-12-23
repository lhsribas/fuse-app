package br.com.redhat.route.internal;

import br.com.redhat.processor.ExchangeBodyInToBodyOutProcessor;
import org.apache.camel.LoggingLevel;
import org.apache.camel.builder.RouteBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class IRGetOrder extends RouteBuilder {
    private static final Logger logger = LoggerFactory.getLogger(IRGetProduct.class);

    @Override
    public void configure() throws Exception {
        from("direct:get-orders")
                .routeId("ir-get-orders-id")
                .description("")

                .toD("http4://{{url.get.orders}}?bridgeEndpoint=true&connectionRequest=300&connectTimeout=300&socketTimeout=300")
                .process(new ExchangeBodyInToBodyOutProcessor())
                .log(LoggingLevel.INFO, logger, "${body}")

                .end();
    }
}
