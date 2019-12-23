package br.com.redhat.route.internal;

import br.com.redhat.model.Order;
import br.com.redhat.util.RandomLongUtil;
import org.apache.camel.Exchange;
import org.apache.camel.ExchangePattern;
import org.apache.camel.LoggingLevel;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.dataformat.JsonLibrary;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class IRSendOrderToMOM extends RouteBuilder {
    private static final Logger logger = LoggerFactory.getLogger(IRSendOrderToMOM.class);

    @Value("${order.queue}")
    private String orderQueue;

    @Override
    public void configure() throws Exception {
        from("direct:send-order-to-mom")
                .routeId("send-order-to-mom")
                .routeDescription("This route send messages for the ActiveMQ")
                .setExchangePattern(ExchangePattern.InOnly)
                .unmarshal().json(JsonLibrary.Jackson, Order.class)
                .process(exchange -> {

                    Order order = exchange.getIn().getBody(Order.class);
                    order.setOrderNumber(RandomLongUtil.get());

                })
                .marshal().json(JsonLibrary.Jackson)
                .to(orderQueue)
                .log(LoggingLevel.DEBUG, logger, "Message is successfully sent to he Order Queue")
                .end();
    }
}
