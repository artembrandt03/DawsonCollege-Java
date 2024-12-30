package maxiso.datalayer.merch;
import java.util.*;

public class Figurine extends Merch{
    private String material;
    private int boxCount;
    private boolean limitedEdition;
    
    public Figurine(String QRCode, String categories, String collection, double retailPrice, String material, int boxCount, boolean limitedEdition) {
        super(QRCode, categories, retailPrice, collection);
        this.material = material;
        this.boxCount = boxCount;
        this.limitedEdition = limitedEdition;
    }

    public String getMaterial() {
        return material;
    }

    public void setMaterial(String material) {
        this.material = material;
    }

    public int getBoxCount() {
        return boxCount;
    }

    public void setBoxCount(int boxCount) {
        this.boxCount = boxCount;
    }

    public boolean isLimitedEdition() {
        return limitedEdition;
    }

    public void setLimitedEdition(boolean limitedEdition) {
        this.limitedEdition = limitedEdition;
    }

    @Override
    public String toString() {
        return "Figurine [material=" + material + ", boxCount=" + boxCount + ", limitedEdition=" + limitedEdition
                + ", getMaterial()=" + getMaterial() + ", getQRCode()=" + getQRCode() + ", getCategories()="
                + getCategories() + ", getBoxCount()=" + getBoxCount() + ", getRetailPrice()=" + getRetailPrice()
                + ", getCollection()=" + getCollection() + ", isLimitedEdition()=" + isLimitedEdition() + "]";
    }

}