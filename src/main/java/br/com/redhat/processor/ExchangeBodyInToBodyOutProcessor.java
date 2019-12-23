package br.com.redhat.processor;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;

public class ExchangeBodyInToBodyOutProcessor implements Processor {
    @Override
    public void process(Exchange exchange) throws Exception {
        final String body = exchange.getIn().getBody(String.class);
        exchange.getOut().setBody(body);
    }
}
