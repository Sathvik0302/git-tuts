package com.email.Service;

import com.email.Model.Messages;

import java.io.File;
import java.io.InputStream;
import java.util.List;

public interface EmailService {
    // for single person
    void sendEmail(String to,String subject,String message);

    // for multiple persons
    void sendEmails(String []to,String subject,String message);

    //void sendEmailWithHtml
    void sendEmailWithHtml(String to, String subject ,String htmlCode);

    //void sendEmailWithFile
    void sendEmailWithFile(String to, String subject, String message, File file);

    void sendEmailWithFile(String to, String subject, String message, InputStream inputStream);

    List<Messages> getInboxMessages();
}
