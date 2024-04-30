package com.email.Service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.apache.tomcat.util.http.fileupload.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMailMessage;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.util.FileSystemUtils;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

@Service
public class EmailServiceImpl implements EmailService{

    @Autowired
    private JavaMailSender mailSender;

    private Logger logger= LoggerFactory.getLogger(EmailServiceImpl.class);
    @Override
    public void sendEmail(String to, String subject, String message) {
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setTo(to);
        simpleMailMessage.setSubject(subject);
        simpleMailMessage.setText(message);
        simpleMailMessage.setFrom("donesathvik@gmail.com");
        mailSender.send(simpleMailMessage);
        logger.info("Email has been sent  ....");
    }

    @Override
    public void sendEmail(String[] to, String subject, String message) {
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setTo(to);
        simpleMailMessage.setSubject(subject);
        simpleMailMessage.setText(message);
        simpleMailMessage.setFrom("donesathvik@gmail.com");
        mailSender.send(simpleMailMessage);
        logger.info("mail sent successfully .....");

    }

    @Override
    public void sendEmailWithHtml(String to, String subject, String htmlCode) {
        MimeMessage simpleMailMessage = mailSender.createMimeMessage();
        try {
            MimeMessageHelper helper=new MimeMessageHelper(simpleMailMessage,
                    true,"UTF-8");
            helper.setTo(to);
            helper.setFrom("donesathvik@gmail.com");
            helper.setSubject(subject);
            helper.setText(htmlCode,true);
            mailSender.send(simpleMailMessage);
            logger.info("Email has been sent  ....");
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public void sendEmailWithFile(String to, String subject, String message, File file) {
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        try {
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
            helper.setTo(to);
            helper.setFrom("donesathvik@gmail.com");
            helper.setText(message);
            FileSystemResource fileSystemResource =new FileSystemResource(file);
            helper.addAttachment(fileSystemResource.getFilename(),file);
            mailSender.send(mimeMessage);
            logger.info("email sent successfully ....");

        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }


    }

    @Override
    public void sendEmailWithFile(String to, String subject, String message, InputStream inputStream) {
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        try {
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
            helper.setTo(to);
            helper.setFrom("donesathvik@gmail.com");
            helper.setSubject(subject);
            helper.setText(message);
            File file = new File("test.jpg");
            Files.copy(inputStream,file.toPath(), StandardCopyOption.REPLACE_EXISTING);
            FileSystemResource fileSystemResource = new FileSystemResource(file);
            helper.addAttachment(fileSystemResource.getFilename(),file);
            mailSender.send(mimeMessage);
            logger.info("mail sent successfully");



        } catch (MessagingException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
