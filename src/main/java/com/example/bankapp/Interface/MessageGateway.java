package com.example.bankapp.Interface;

import org.springframework.integration.annotation.Gateway;
import org.springframework.integration.annotation.MessagingGateway;
import org.springframework.stereotype.Component;

@MessagingGateway
@Component
public interface MessageGateway {

    @Gateway(requestChannel = "inputChannel")
    public <S> void sendMessage(S request);
}
