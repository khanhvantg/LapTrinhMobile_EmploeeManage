package com.example.employeemanage.model;

public class Product {
    private Long id;
    private String name;
    private Double price;
    private Integer saleOff;
    private String description;

    public Product(String name, Double price,String description, Integer saleOff) {
        this.name = name;
        this.price = price;
        this.saleOff = saleOff;
        this.description = description;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Integer getSaleOff() {
        return saleOff;
    }

    public void setSaleOff(Integer saleOff) {
        this.saleOff = saleOff;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
