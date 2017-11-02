
package com.sheridansports.business;

import java.io.Serializable;
import java.time.LocalDateTime;


public class Purchase implements Serializable{
    private int purchaseId;
    //Instead of an int field for the foreign key cardId, have a card bean property in its place
    private Card card;
    private String notes;
    private LocalDateTime purchaseDate;
    
    public Purchase() {
        purchaseId = 0;
        card = new Card();
        notes = "";
        purchaseDate = LocalDateTime.now();
    }

    /**
     * @return the purchaseId
     */
    public int getPurchaseId() {
        return purchaseId;
    }

    /**
     * @param purchaseId the purchaseId to set
     */
    public void setPurchaseId(int purchaseId) {
        this.purchaseId = purchaseId;
    }

    /**
     * @return the card
     */
    public Card getCard() {
        return card;
    }

    /**
     * @param card the card to set
     */
    public void setCard(Card card) {
        this.card = card;
    }

    /**
     * @return the notes
     */
    public String getNotes() {
        return notes;
    }

    /**
     * @param notes the notes to set
     */
    public void setNotes(String notes) {
        this.notes = notes;
    }

    /**
     * @return the purchaseDate
     */
    public LocalDateTime getPurchaseDate() {
        return purchaseDate;
    }

    /**
     * @param purchaseDate the purchaseDate to set
     */
    public void setPurchaseDate(LocalDateTime purchaseDate) {
        this.purchaseDate = purchaseDate;
    }
    
}
