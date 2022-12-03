package com.kamelia.ebc.webserver.config;

import com.kamelia.webservice.service.Hello;
import com.kamelia.webservice.service.HelloServiceLocator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.xml.rpc.ServiceException;

@Configuration
public class SOAPConfiguration {

    @Bean
    public Hello hello() throws ServiceException {
        return new HelloServiceLocator().getHello();
    }

}
