package com.fileTransfer.camel;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;
//import org.apache.camel.spi.annotations.Component;
import org.springframework.stereotype.Component;

@Component
public class MyFileRouter extends RouteBuilder {
    
    @Override
    public void configure() throws Exception {
        from("file:C:\\Users\\Okafor Handel\\OneDrive\\Documents\\camel\\Data stream")
            .log("${body}")
            .process(new FileProcessorService())
        .to("file:C:\\Users\\Okafor Handel\\OneDrive\\Documents\\camel\\Output");
    }

    public class FileProcessorService implements Processor {

        public void process(Exchange exchange) throws Exception {
            String originalFileContent = (String) exchange.getIn().getBody(String.class);
            String upperCaseFileContent = originalFileContent.toUpperCase();
            exchange.getIn().setBody(upperCaseFileContent);
    
        }

        
    }
}