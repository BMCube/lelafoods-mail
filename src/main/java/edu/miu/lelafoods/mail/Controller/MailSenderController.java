package edu.miu.lelafoods.mail.Controller;

import edu.miu.lelafoods.mail.domain.Cart;
import edu.miu.lelafoods.mail.domain.CartDto;
import edu.miu.lelafoods.mail.domain.Food;
import edu.miu.lelafoods.mail.domain.Order;
import edu.miu.lelafoods.mail.service.NotificationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.MailException;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;


@RestController
@RequestMapping(MailSenderController.BASE_URL)
public class MailSenderController {
    public static final String BASE_URL = "/notification/";
    Logger LOG = LoggerFactory.getLogger(MailSenderController.class);

    @Autowired
    NotificationService notificationService;


    @PostMapping("/email")
    public @ResponseBody
    ResponseEntity sendSimpleEmail(@RequestBody CartDto cartDto, HttpServletRequest request
    ) throws URISyntaxException
        {

            String subject = "";
            if (cartDto.getStatus() != null) {
                subject = cartDto.getStatus() +" Order";
            }


            try {
                String recipientSender = "mail.eatest@gmail.com";
                String recipientEmail = cartDto.getCustomer().getUsername();
                notificationService.sendNotification(
                        recipientSender, recipientEmail,
                        cartDto, subject, new Locale("en"));


            } catch (MailException | MessagingException mailException) {
                LOG.error("Error while sending out email..{}", mailException.getStackTrace());
                return new ResponseEntity<>("Unable to send email", HttpStatus.INTERNAL_SERVER_ERROR);
            }

            return new ResponseEntity<>("Please check your inbox", HttpStatus.OK);
        }

    }

