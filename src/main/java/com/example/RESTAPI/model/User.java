package com.example.RESTAPI.model;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import jakarta.validation.constraints.NotBlank;

/**
 * @author Valeev A.R.
 */

public class User {

    @NotBlank
    private String login;

    @NotBlank
    private String password;

    @NotBlank
    private String date;

    public User(String login, String password) {
        this.login = login;
        this.password = password;
        this.date = this.getDatetimeConnect("dd.MM.yyyy HH:mm:ss");
    }

    private String getDatetimeConnect(String pattern) {
        DateFormat dateFormat = new SimpleDateFormat(pattern);
        Date date = new Date();
        String datetimeConnect = dateFormat.format(date);
        return dateFormat.format(date);
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public String getDate() {
        return date;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setDate(String date) {
        this.date = date;
    }

}
