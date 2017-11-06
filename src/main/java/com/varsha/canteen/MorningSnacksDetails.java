package com.varsha.canteen;

/**
 * Created by Varsha on 4/3/2016.
 */
public class MorningSnacksDetails {
    private String name ;
    private String price;
    private int imageNumber;
    private int quantity;



    public void MorningSnacksDetails(MorningSnacksDetails morningSnacksDetails)
    {
        this.name=morningSnacksDetails.name;
        this.price=morningSnacksDetails.price;
        this.imageNumber = morningSnacksDetails.imageNumber;
        this.quantity = morningSnacksDetails.quantity;

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public int getImageNumber() {
        return imageNumber;
    }

    public void setImageNumber(int imageNumber) {
        this.imageNumber = imageNumber;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

}
