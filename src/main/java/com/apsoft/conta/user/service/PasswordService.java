package com.apsoft.conta.user.service;

import com.apsoft.conta.mail.model.Mail;
import com.apsoft.conta.mail.service.MailService;
import com.apsoft.conta.security.payload.response.MessageResponse;
import com.apsoft.conta.user.persistence.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.view.RedirectView;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

@NoArgsConstructor
@Slf4j
@Service
public class PasswordService {
    @Value("${apsoft.app.emailFrom}")
    private String sender;

    @Autowired
    private UserService userService;

    @Autowired
    private MailService emailService;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public void processForgotPasswordRequest(String userEmail, HttpServletRequest request) {

        Optional<User> optional = userService.findUserByEmail(userEmail);

        if (!optional.isPresent()) {
            log.info("{} this email do not exist", userEmail);
        } else {
            User user = optional.get();
            user.setTokenReset(UUID.randomUUID().toString());
            userService.save(user);

            String appUrl = request.getScheme() + "://" + request.getServerName() + ":4200";

            Mail passwordResetEmail = new Mail();
            passwordResetEmail.setMailFrom(sender);
            passwordResetEmail.setMailTo(user.getEmail());
            passwordResetEmail.setMailSubject("Cerere de resetare a parolei ");
            passwordResetEmail.setMailContent("Pentru a vă reseta parola, faceți clic pe linkul de mai jos: \n" + appUrl
                    + "/reset?token=" + user.getTokenReset());

            emailService.sendMail(passwordResetEmail);
            log.info("A password reset link has been sent to {} " + userEmail);


        }
    }

    public ResponseEntity processForgotPasswordRequest(String token, HttpServletResponse res) {

        Optional<User> user = userService.findByTokenReset(token);

        if (user.isPresent()) {
            res.setStatus(HttpServletResponse.SC_OK);
            log.info("{} accessed the password reset page",user.get().getUsername());
            return ResponseEntity.ok(new MessageResponse("Access for reset password"));
        }

        log.warn("This token {} is wrong");
        res.setStatus(HttpServletResponse.SC_NOT_FOUND);
        return (ResponseEntity) ResponseEntity.badRequest().body(new MessageResponse("Wrong token"));

    }


    @Data
    public static class PasswordResetRequest {
        String token;
        String password;

    }


//    public void setNewPassword(Map<String, String> requestParams) {
//
//
//        Optional<User> user = userService.findByTokenReset(requestParams.get("token"));
//
//        if (user.isPresent()) {
//
//            User resetUser = user.get();
//
//            // Set new password
//            resetUser.setPassword(bCryptPasswordEncoder.encode(requestParams.get("password")));
//            log.info("Set new password for {}", user.get().getUsername());
//            // Set the reset token to null so it cannot be used again
//            resetUser.setTokenReset(null);
//            log.info("Token reset for {}", user.get().getUsername());
//
//            // Save user
//            userService.save(resetUser);
//        }
//    }

    public void setNewPassword(PasswordResetRequest passwordResetRequest) {


        Optional<User> user = userService.findByTokenReset(passwordResetRequest.token);

        if (user.isPresent()) {

            User resetUser = user.get();

            // Set new password
            resetUser.setPassword(bCryptPasswordEncoder.encode(passwordResetRequest.password));
            log.info("Set new password for {}", user.get().getUsername());
            // Set the reset token to null so it cannot be used again
            resetUser.setTokenReset(null);
            log.info("Token reset for {}", user.get().getUsername());

            // Save user
            userService.save(resetUser);
        }
    }

}

