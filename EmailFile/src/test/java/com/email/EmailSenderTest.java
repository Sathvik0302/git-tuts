package com.email;

import com.email.Model.Messages;
import com.email.Service.EmailService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.swing.text.html.HTML;
import java.awt.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.List;

import static org.springframework.test.util.AssertionErrors.fail;

@SpringBootTest
public class EmailSenderTest {

    @Autowired
    private EmailService emailService;
    @Test
    void emailSendTest(){
        System.out.println("sending email");
        emailService.sendEmail("dones.me.20@nitj.ac.in","This is email from SpringBoot",
                "This email is sent through emailService from spring boot");
    }

    @Test
    void sendHTMLInEmail() {
        String html = "" +
                "<h1 style=\"color:red; border:1px solid red;\">Welcome to Spring Boot</h1>" +
                "";
        emailService.sendEmailWithHtml("done.karthik2004@gmail.com", "Email from Spring Boot", html);
    }

    @Test
    void sendEmailWithFile(){
        emailService.sendEmailWithFile("dones.me.20@nitj.ac.in",
                "This is image uploaded","This is the Spring Boot file Sending",
                new File("C:\\Users\\sathvik.done\\Desktop\\Incture Works\\Intellij\\EmailFile\\src\\main\\resources\\static\\Image\\Dog.jpg")
        );
    }

    @Test
    void sendEmailWithInputStream(){
        File file=new File("C:\\Users\\sathvik.done\\Desktop\\Incture Works\\Intellij\\EmailFile\\src\\main\\resources\\static\\Image\\Dog.jpg");
        try {
            InputStream inputStream = new FileInputStream(file);
            emailService.sendEmailWithFile("dones.me.20@nitj.ac.in",
                    "This is image uploaded","This is the Spring Boot file Sending",
                    inputStream
                    );
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

    }

    @Test
    void getInbox() {
        try {
            List<Messages> inboxMessages = emailService.getInboxMessages();
            inboxMessages.forEach(item -> {
                System.out.println(item.getSubjects());
                System.out.println(item.getContent());
                System.out.println(item.getFiles());
                System.out.println("__________________________");
            });
        } catch (Exception e) {
            e.printStackTrace();
            fail("Failed to retrieve inbox messages: " + e.getMessage());
        }
    }




}
