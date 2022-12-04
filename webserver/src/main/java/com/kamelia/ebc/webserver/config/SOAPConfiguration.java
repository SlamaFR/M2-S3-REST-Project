package com.kamelia.ebc.webserver.config;

import com.kamelia.ebc.bank.BankService;
import com.kamelia.ebc.bank.BankServiceServiceLocator;
import com.kamelia.webservice.service.GustaveBikeService;
import com.kamelia.webservice.service.GustaveBikeServiceServiceLocator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.xml.rpc.ServiceException;

@Configuration
public class SOAPConfiguration {

    @Bean
    public GustaveBikeService gustaveBikeService() throws ServiceException {
        return new GustaveBikeServiceServiceLocator().getGustaveBikeService();
    }

    @Bean
    public BankService bankService() throws ServiceException {
        return new BankServiceServiceLocator().getBankService();
    }

}
