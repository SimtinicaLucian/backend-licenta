package com.apsoft.conta.mail.service;

import com.apsoft.conta.mail.model.Mail;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.util.Map;


@Service("mailService")
public class MailServiceImpl implements MailService {

    @Value("${apsoft.app.emailFrom}")
    private String emailFrom;

    @Value("${apsoft.app.emailTo}")
    private String emailTo;

    private String from;

    @Autowired
    private JavaMailSender mailSender;

    @Override
    public void sendMail(Mail mail) {
        MimeMessage mimeMessage = mailSender.createMimeMessage();

        try {
            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);
            mimeMessageHelper.setSubject(mail.getMailSubject());
            mimeMessageHelper.setFrom(emailFrom);
            mimeMessageHelper.setTo(mail.getMailTo());
            mimeMessageHelper.setText(mail.getMailContent());

            mailSender.send(mimeMessageHelper.getMimeMessage());


        } catch (MessagingException e) {
            e.printStackTrace();
        }

    }

    @Data
    public static class SendMailContact {
        String mailSubject;
        String from;
        String mailContent;

    }


    @Override
    public void sendMailTo(SendMailContact sendMailContact) {
        MimeMessage mimeMessage = mailSender.createMimeMessage();

        String title = String.format("Title: %s", sendMailContact.mailSubject);
        String email = String.format("From: %s", sendMailContact.from);
        String content = String.format("Content: %s", sendMailContact.mailContent);
        String contactContent = title + "\n" + email + "\n" + content;

        try {
            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);
            mimeMessageHelper.setSubject("Contact");
            mimeMessageHelper.setFrom(emailFrom);
            mimeMessageHelper.setTo(emailTo);
            mimeMessageHelper.setText(contactContent);

            mailSender.send(mimeMessageHelper.getMimeMessage());


        } catch (MessagingException e) {
            e.printStackTrace();
        }

    }

}
