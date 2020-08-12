package edu.miu.lelafoods.mail.Controller;

import edu.miu.lelafoods.mail.domain.Cart;
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
    ResponseEntity sendSimpleEmail(
                                    final Locale locale){


        Food food = new Food();
        food.setPrice(12.4);
        food.setName("Pasta");
        Order order1 = new Order();
        order1.setFood(food);
        order1.setOrderQuantity(2);
       String recipientEmail = "biruk.bekele@gmail.com";
       String recipientSender= "mail.eatest@gmail.com";

        List<Order> orders = new ArrayList<>();
        orders.add(order1);
        Cart cart = new Cart();
        cart.setOrderStatus("New Order");
//        here you can map the reciverName= cart.customer.getname();
//        String reciverName = "Brck_test";
        cart.setCustomerName("Brck_test");

        String subject="";
        if(cart.getOrderStatus()!=null){
            subject=cart.getOrderStatus();
        }
        cart.setOrder(orders);

       try {


        notificationService.sendNotification(
                recipientSender,  recipientEmail,
                cart, subject,new Locale("en"));


    }
       catch (MailException | MessagingException mailException) {
           LOG.error("Error while sending out email..{}", mailException.getStackTrace());
           return new ResponseEntity<>("Unable to send email", HttpStatus.INTERNAL_SERVER_ERROR);
       }

        return new ResponseEntity<>("Please check your inbox", HttpStatus.OK);
    }

}
