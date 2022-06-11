package com.example.employeemanage.model;

import java.io.Serializable;

public class Order implements Serializable {
    private Long id;
    private Double totalMoney;
    private String timeOrder;
    private Long employeeId;
    private String customerName;
    private String customerPhone;

    public Order(String timeOrder, Double totalMoney , String customerName, String customerPhone, Long employeeId) {
        this.totalMoney = totalMoney;
        this.timeOrder = timeOrder;
        this.employeeId = employeeId;
        this.customerName = customerName;
        this.customerPhone = customerPhone;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCustomerPhone() {
        return customerPhone;
    }

    public void setCustomerPhone(String customerPhone) {
        this.customerPhone = customerPhone;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getTotalMoney() {
        return totalMoney;
    }

    public void setTotalMoney(Double totalMoney) {
        this.totalMoney = totalMoney;
    }

    public String getTimeOrder() {
        return timeOrder;
    }

    public void setTimeOrder(String timeOrder) {
        this.timeOrder = timeOrder;
    }

    public Long getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Long employeeId) {
        this.employeeId = employeeId;
    }
}
