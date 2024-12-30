package maxiso.datalayer.merch;

import java.util.*;

public abstract class Merch {
    private String qrCode;
    private String categories;
    private double retailPrice;
    private String collection;

    /**
     * @param qrCode
     * @param categories
     * @param retailPrice
     * @param collection
     */
    public Merch(String qrCode,String categories,double retailPrice, String collection){
        this.qrCode=qrCode;
        this.categories=categories;
        this.retailPrice= retailPrice;
        this.collection= collection;
    }

    public String getQRCode(){
        return this.qrCode;
    }
    public String getCategories(){
        return this.categories;
    }
    public double getRetailPrice(){
        return this.retailPrice;
    }

    public String getCollection(){
        return this.collection;
    } 

}
