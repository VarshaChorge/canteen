package com.varsha.canteen;

import java.util.ArrayList;

/**
 * Created by Varsha on 4/3/2016.
 */
public class HeaderInfo {
    private String name;
    private ArrayList<MorningSnacksDetails> productList = new ArrayList<MorningSnacksDetails>();;

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public ArrayList<MorningSnacksDetails> getProductList() {
        return productList;
    }
    public void setProductList(ArrayList<MorningSnacksDetails> productList) {
        this.productList = productList;
    }
}

