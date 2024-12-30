package maxiso.datalayer.merch;

public class Plushie extends Merch{
    //field
    private String color;
    private int size;
    private String material;
    private String stuffing;
    private boolean washable;

    /**
     * @param color
     * @param size
     * @param material
     * @param stuffing
     * @param washable
     * 
     * 
     * categorie is the name of the character : Snow White
     * collection is like the brand :Disney
     */
    public Plushie(String qrCode, String categories, double retailPrice, String collection, String color,int size,String material,String stuffing,boolean washable){
        super( qrCode, categories, retailPrice, collection);
        this.color=color;
        this.size=size;
        this.material=material; 
        this.stuffing=stuffing;
        this.washable=washable;
    }

    public String getColor(){
        return this.color;
    }
    public int getSize(){
        return this.size;
    }
    public String getMaterial(){
        return this.material;
    }
    public String getStuffing(){
        return this.stuffing;
    }
    public boolean getWashable(){
        return this.washable;
    }

    @Override
    public String toString() {
        return "Plushie [color=" + color + ", size=" + size + ", material=" + material + ", stuffing=" + stuffing
                + ", washable=" + washable + ", getQRCode()=" + getQRCode() + ", getCategories()=" + getCategories()
                + ", getRetailPrice()=" + getRetailPrice() + ", getColor()=" + getColor() + ", getCollection()="
                + getCollection() + ", getSize()=" + getSize() + ", getMaterial()=" + getMaterial() + ", getStuffing()="
                + getStuffing() + ", getWashable()=" + getWashable() + "]";
    }

    
}