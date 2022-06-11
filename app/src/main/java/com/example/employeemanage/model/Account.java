package com.example.employeemanage.model;

import java.io.Serializable;

public class Account implements Serializable {
    private Long id;
    private String userName;
    private String passWord;
    private String fullName;
    private String email;
    private Integer kind;   //1 : admin , 0 : employee
    private String phone;
    private byte[] avatar;


    public Account() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getKind() {
        return kind;
    }

    public void setKind(Integer kind) {
        this.kind = kind;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public byte[] getAvatar() {
        return avatar;
    }

    public void setAvatar(byte[] avatar) {
        this.avatar = avatar;
    }

    public Account(String userName, String passWord, String fullName, String email, Integer kind, String phone, byte[] avatar) {
        this.userName = userName;
        this.passWord = passWord;
        this.fullName = fullName;
        this.email = email;
        this.kind = kind;
        this.phone = phone;
        this.avatar = avatar;
    }
}
