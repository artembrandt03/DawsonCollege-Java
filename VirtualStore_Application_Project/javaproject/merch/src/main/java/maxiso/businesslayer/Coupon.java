package maxiso.businesslayer;

/**
 * The Coupon class is a abstract class for FixedCoupon and PercentageCoupon
 * It stores the common field "discount" that they both contain.
 */
public abstract class Coupon {

    private double discount;

    /**
     * Constructs a Coupon with a discount
     * 
     *
     * @param discount The amount of discount to be applied
     */
    public Coupon(double discount){
        this.discount = discount;
    }

    public double getDiscount(){
        return this.discount;
    }

    public void setDiscount(double newDiscount){
        this.discount = newDiscount;
    }

}
