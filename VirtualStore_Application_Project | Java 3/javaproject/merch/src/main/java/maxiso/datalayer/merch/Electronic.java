package maxiso.datalayer.merch;
import java.util.*;

public class Electronic extends Merch {

    //Fields section
    private electronicProduct productType; //Implements enum
    private String modelNumber;
    private int batteryCapacity;
    private double weight;
    private boolean waterResistant;
    private boolean wireless;

    //Constructor
    /**
     * 
     * @param productType
     * @param modelNumber
     * @param batteryCapacity
     * @param weight
     * @param waterResistant
     * @param wireless
     * @param qrCode
     * @param categories
     * @param retailPrice
     * @param collection
     */
    public Electronic(electronicProduct productType, String modelNumber, int batteryCapacity, double weight, boolean waterResistant, boolean wireless, String qrCode, String categories, double retailPrice, String collection) {
        super(qrCode, categories, retailPrice, collection);
        this.productType = productType;
        this.modelNumber = modelNumber;
        this.batteryCapacity = batteryCapacity;
        this.weight = weight;
        this.waterResistant = waterResistant;
        this.wireless = wireless;
    }

    //Getters section
    public electronicProduct getProductType() {
        return productType;
    }

    public String getModelNumber() {
        return modelNumber;
    }

    public int getBatteryCapacity() {
        return batteryCapacity;
    }

    public double getWeight() {
        return weight;
    }

    public boolean isWaterResistant() {
        return waterResistant;
    }

    public boolean isWireless() {
        return wireless;
    }

    @Override
    public String toString() {
        return "Electronic [productType=" + productType + ", modelNumber=" + modelNumber + ", batteryCapacity="
                + batteryCapacity + ", weight=" + weight + ", waterResistant=" + waterResistant + ", wireless="
                + wireless + ", getQRCode()=" + getQRCode() + ", getCategories()=" + getCategories()
                + ", getRetailPrice()=" + getRetailPrice() + ", getCollection()=" + getCollection()
                + ", getProductType()=" + getProductType() + ", getModelNumber()=" + getModelNumber()
                + ", getBatteryCapacity()=" + getBatteryCapacity() + ", getWeight()=" + getWeight()
                + ", isWaterResistant()=" + isWaterResistant() + ", isWireless()=" + isWireless() + "]";
    }

    
}