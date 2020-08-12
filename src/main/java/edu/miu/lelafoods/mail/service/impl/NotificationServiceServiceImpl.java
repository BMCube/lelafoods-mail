package edu.miu.lelafoods.mail.service.impl;

import edu.miu.lelafoods.mail.domain.Cart;
import edu.miu.lelafoods.mail.service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring5.SpringTemplateEngine;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import java.util.Locale;

@Service
public class NotificationServiceServiceImpl implements NotificationService {

    private static final String Logo = "templates/images/logo.png";
    private static final String PNG_MIME = "image/png";

    @Autowired
    private JavaMailSender javaMailSender;

    @Autowired
    private SpringTemplateEngine templateEngine;

    public void sendNotification(String from,  final String recipientEmail, Cart cart, String subject, final Locale locale)  throws MessagingException {
        {
            final Context thymeContext = new Context(locale);
            //thymeContext.setVariable("name", toName);
            thymeContext.setVariable("cart", cart);
            final MimeMessage mimeMessage = this.javaMailSender.createMimeMessage();
            final MimeMessageHelper message = new MimeMessageHelper(mimeMessage, true, "UTF-8");
            message.setSubject(subject);
            message.setTo(recipientEmail);
            final String htmlContent = this.templateEngine.process("mailSend.html", thymeContext);
            message.setText(htmlContent, true /* isHtml */);
            message.addInline("BMCube", new ClassPathResource(Logo), PNG_MIME);
            this.javaMailSender.send(mimeMessage);
        }
    }
}
