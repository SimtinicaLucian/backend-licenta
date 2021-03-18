package com.apsoft.conta.mail.controller;

import com.apsoft.conta.mail.model.Mail;
import com.apsoft.conta.mail.service.MailService;
import com.apsoft.conta.mail.service.MailServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/user/mail/")

public class MailController {
    @Autowired
    private MailService mailService;

    @Autowired
    private MailServiceImpl mailServiceImpl;

    @PostMapping("sendTo/{emailTo}")
//    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_MODERATOR') or hasRole('ROLE_ADMIN')")
    public void sendMail(@PathVariable String emailTo) {
        Mail mail = new Mail();
        mail.setMailTo(emailTo);
        mail.setMailSubject("Mail sent from Spring Boot");
        mail.setMailContent("This is a test message from Spring Boot \n Simtinica Cosmin Lucian, \n ApCost for conta");
        mailService.sendMail(mail);
    }


//    @PostMapping(value = "/test")
//    public void sendMailTo(@RequestParam Map<String, String> params){
//        mailService.sendMailTo(params);
//    }

    @PostMapping("/test")
    public void sendMailContact(@RequestBody MailServiceImpl.SendMailContact sendMailContact) {
        mailServiceImpl.sendMailTo(sendMailContact);
    }
}
