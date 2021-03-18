package com.apsoft.conta.mail.service;

import com.apsoft.conta.mail.model.Mail;

import java.util.Map;

public interface MailService {
    void sendMail(Mail mail);


    void sendMailTo(MailServiceImpl.SendMailContact sendMailContact);
}
