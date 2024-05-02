package com.email.Controller;

import ch.qos.logback.classic.Logger;
import com.email.Model.CustomResponse;
import com.email.Model.EmailRequest;
import com.email.Service.EmailService;
import jakarta.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;


@RestController
@RequestMapping("/api/v1/email")
public class EmailController {
    @Autowired
    private EmailService emailService;
    private Logger logger;

    @PostMapping("/send")
    public ResponseEntity<?> sendEmail(@RequestBody EmailRequest request)
    {
        emailService.sendEmailWithHtml(request.getTo(), request.getSubject(),request.getMessage());
        return ResponseEntity.ok(CustomResponse.builder().message("Email Sent Successfully")
                .httpStatus(HttpStatus.OK).success(true).build());

    }



    @PostMapping("/sendwithfile")
    public ResponseEntity<CustomResponse> sendWithFile(@RequestParam("to") String to,
                                                       @RequestParam("subject") String subject,
                                                       @RequestParam("message") String message,
                                                       @RequestParam("file") MultipartFile file) throws MessagingException {
        try {
            emailService.sendEmailWithFile(to, subject, message, file.getInputStream());
            return ResponseEntity.ok(CustomResponse.builder().message("Email Sent Successfully")
                    .httpStatus(HttpStatus.OK).success(true).build());
        } catch (IOException e) {
            logger.error("Error reading file content", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(CustomResponse.builder().message("Failed to read file content").success(false).build());
        }
    }




}
