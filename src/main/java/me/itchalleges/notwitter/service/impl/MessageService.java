package me.itchalleges.notwitter.service.impl;

import me.itchalleges.notwitter.service.IMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.ResourceBundle;

/**
 * Date: 06/08/2016 6:10 PM
 */
@Service
public class MessageService implements IMessageService {

    @Autowired
    private ResourceBundle messages;

    @Override
    public String getMessage(String message, Object... args) {
        return String.format(messages.getString(message), args);
    }
}
