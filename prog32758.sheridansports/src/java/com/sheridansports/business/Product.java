
package com.sheridansports.business;

import java.io.Serializable;

/**
 * An example of a JavaBean that maps with the Product table of the SheridanSports
 * database.
 * All JavaBeans must conform to the following conventions:
 * 1) Must implement the Serializable interface
 * 2) All class variables must be encapsulated
 * 3) Must have a no-argument constructor that sets all variables to default values
 * 4) All properties that may be accessed by a JSP must have getter/setter methods
 * 
 */
public class Product implements Serializable {
    private String productId;
    private String manufacturer;
    private String item;
    private String description;
    private double price;
    private boolean available;
    
    public Product() {
        productId = "";
        manufacturer = "";
        item = "";
        description = "";
        price = 0.0;
        available = false;
    }

    /**
     * @return the productId
     */
    public String getProductId() {
        return productId;
    }

    /**
     * @param productId the productId to set
     */
    public void setProductId(String productId) {
        this.productId = productId;
    }

    /**
     * @return the manufacturer
     */
    public String getManufacturer() {
        return manufacturer;
    }

    /**
     * @param manufacturer the manufacturer to set
     */
    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    /**
     * @return the item
     */
    public String getItem() {
        return item;
    }

    /**
     * @param item the item to set
     */
    public void setItem(String item) {
        this.item = item;
    }

    /**
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * @param description the description to set
     */
    public void setDescription(String description) {
        this.description = description;
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

    /**
     * @return the available
     */
    public boolean isAvailable() {
        return available;
    }

    /**
     * @param available the available to set
     */
    public void setAvailable(boolean available) {
        this.available = available;
    }
    
}
