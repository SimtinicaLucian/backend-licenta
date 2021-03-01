package com.apsoft.conta.user.model;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
public class UserModel {
    private String username;
    private String email;
    private String password;

    public UserModel(String name,String mail, String pass){
        this.username = name;
        this.email = mail;
        this.password = pass;
    }
}
