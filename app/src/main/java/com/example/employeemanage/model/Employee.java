package com.example.employeemanage.model;

public class Employee{
    private Long id;
    private String birthday;
    private int position;
    private Long accountId;
    private String address;
    private Integer gender;    // 0 là Nữ , 1 là Nam
    private String identityNumber;   //cmnd
    private Double salary = 0d;
    private String dateTime;
    private Double hours;

    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }

    public Double getHours() {
        return hours;
    }

    public void setHours(Double hours) {
        this.hours = hours;
    }

    public Employee(){

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public Long getAccountId() {
        return accountId;
    }

    public void setAccountId(Long accountId) {
        this.accountId = accountId;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Integer getGender() {
        return gender;
    }

    public void setGender(Integer gender) {
        this.gender = gender;
    }

    public String getIdentityNumber() {
        return identityNumber;
    }

    public void setIdentityNumber(String identityNumber) {
        this.identityNumber = identityNumber;
    }

    public Double getSalary() {
        return salary;
    }

    public void setSalary(Double salary) {
        this.salary = salary;
    }

    public Employee(String birthday, int position, Long accountId, String address, Integer gender, String identityNumber, Double salary, String dateTime, Double hours) {
        this.birthday = birthday;
        this.position = position;
        this.accountId = accountId;
        this.address = address;
        this.gender = gender;
        this.identityNumber = identityNumber;
        this.salary = salary;
        this.dateTime=dateTime;
        this.hours=hours;
    }
}
