/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sheridansports.business;

import java.io.Serializable;


public class PurchaseItem implements Serializable{
    
    private int purchaseItemId;
    private Purchase purchase;
    private Product product;
    private int quantity;
    private double price;
    
    public PurchaseItem(){
        purchaseItemId = 0;
        purchase = new Purchase();
        product = new Product();
        quantity=0;
        price = 0.0;
    }

    /**
     * @return the purchaseItemId
     */
    public int getPurchaseItemId() {
        return purchaseItemId;
    }

    /**
     * @param purchaseItemId the purchaseItemId to set
     */
    public void setPurchaseItemId(int purchaseItemId) {
        this.purchaseItemId = purchaseItemId;
    }

    /**
     * @return the purchase
     */
    public Purchase getPurchase() {
        return purchase;
    }

    /**
     * @param purchase the purchase to set
     */
    public void setPurchase(Purchase purchase) {
        this.purchase = purchase;
    }

    /**
     * @return the product
     */
    public Product getProduct() {
        return product;
    }

    /**
     * @param product the product to set
     */
    public void setProduct(Product product) {
        this.product = product;
    }

    /**
     * @return the quantity
     */
    public int getQuantity() {
        return quantity;
    }

    /**
     * @param quantity the quantity to set
     */
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    /**
     * @return the price
     */
    public double getPrice() {
        return price;
    }

    /**
     * @param price the price to set
     */
    public void setPrice(double price) {
        this.price = price;
    }
    
}
