package com.pricetag;

import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.stereotype.Component;

@Component
public class MessageSource extends ResourceBundleMessageSource {

    public MessageSource() {
        this.setBasenames("messages");
        this.setUseCodeAsDefaultMessage(true);
    }
}
