package edu.miu.lelafoods.mail.service;

import edu.miu.lelafoods.mail.domain.Cart;

import javax.mail.MessagingException;
import java.util.Locale;

public interface NotificationService {
    public void sendNotification(String from, final String recipientEmail, Cart cart, String subject, final Locale locale) throws MessagingException;
}
