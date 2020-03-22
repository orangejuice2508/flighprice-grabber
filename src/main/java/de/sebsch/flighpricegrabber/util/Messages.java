package de.sebsch.flighpricegrabber.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.stereotype.Component;

import java.util.Locale;

@Component
public class Messages {

    private MessageSource messageSource;
    private MessageSourceAccessor messageSourceAccessor;

    @Autowired
    public Messages(MessageSource messageSource) {
        this.messageSource = messageSource;
        messageSourceAccessor = new MessageSourceAccessor(messageSource, Locale.getDefault());
    }

    public String get(String code){
        return messageSourceAccessor.getMessage(code);
    }
}
