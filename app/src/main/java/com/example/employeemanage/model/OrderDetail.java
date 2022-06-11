package com.example.employeemanage.model;

public class OrderDetail {
    private Long id;
    private Long productId;
    private Long ordersId;
    private Integer quantity;

    public OrderDetail(Integer quantity, Long productId , Long ordersId) {
        this.productId = productId;
        this.ordersId = ordersId;
        this.quantity = quantity;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public Long getOrdersId() {
        return ordersId;
    }

    public void setOrdersId(Long ordersId) {
        this.ordersId = ordersId;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}
