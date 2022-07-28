package com.pricetag.consumer.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;
import org.springframework.jms.config.JmsListenerContainerFactory;

@EnableJms
@Configuration
public class ActiveMQConfig {


    @Bean
    public JmsListenerContainerFactory<?> queueListenerFactory() {
        /*     factory.setMessageConverter(messageConverter());*/
        return new DefaultJmsListenerContainerFactory();
    }

  /*  @Bean
    public MessageConverter messageConverter() {
        MappingJackson2MessageConverter converter = new MappingJackson2MessageConverter();
        converter.setTargetType(MessageType.TEXT);
        return converter;
    }*/

}