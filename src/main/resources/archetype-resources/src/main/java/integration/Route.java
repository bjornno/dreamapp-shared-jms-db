package ${groupId}.integration;

import org.apache.camel.spring.SpringRouteBuilder;


public class Route extends SpringRouteBuilder {
    @Override
    public void configure() throws Exception {

        from("file://data/in").to("jms:queue:order?jmsMessageType=Text");
        
        from("jms:queue:order").to("file://data/out");
    }
}
