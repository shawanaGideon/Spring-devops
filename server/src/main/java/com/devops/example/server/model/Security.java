package com.devops.example.server.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.OptBoolean;
import org.springframework.data.annotation.Id;

import java.util.Date;

public class Security {

    @Id
    public String _id;

    public String name;
    public String category;
    public Long purchasePrice;

    @JsonFormat(pattern = "dd-MM-yyyy", lenient = OptBoolean.TRUE)
    public Date purchasedate;
    public Long quantity;


    public Security(){

    }

    public Security(String _id, String name, String category, Long purchasePrice, Date purchasedate, Long quantity) {
        this._id = _id;
        this.name = name;
        this.category = category;
        this.purchasePrice = purchasePrice;
        this.purchasedate = purchasedate;
        this.quantity = quantity;
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Long getPurchasePrice() {
        return purchasePrice;
    }

    public void setPurchasePrice(Long purchasePrice) {
        this.purchasePrice = purchasePrice;
    }

    public Date getPurchasedate() {
        return purchasedate;
    }

    public void setPurchasedate(Date purchasedate) {
        this.purchasedate = purchasedate;
    }

    public Long getQuantity() {
        return quantity;
    }

    public void setQuantity(Long quantity) {
        this.quantity = quantity;
    }
}
