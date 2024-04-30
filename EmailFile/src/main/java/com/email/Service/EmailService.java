package com.email.Service;

import java.io.File;
import java.io.InputStream;

public interface EmailService {
    // for single person
    void sendEmail(String to,String subject,String message);

    // for multiple persons
    void sendEmail(String []to,String subject,String message);

    //void sendEmailWithHtml
    void sendEmailWithHtml(String to, String subject ,String htmlCode);

    //void sendEmailWithFile
    void sendEmailWithFile(String to, String subject, String message, File file);

    void sendEmailWithFile(String to, String subject, String message, InputStream inputStream);
}
