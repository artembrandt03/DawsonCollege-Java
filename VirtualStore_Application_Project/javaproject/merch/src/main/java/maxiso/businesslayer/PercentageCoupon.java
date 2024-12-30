package maxiso.businesslayer;

public class PercentageCoupon extends Coupon{

    private String couponName;

    public PercentageCoupon(String couponName, Double discount){
        super(1-(discount / 100));
        this.couponName = couponName;
    }

    public String getCouponName() {
        return couponName;
    }

    @Override
    public String toString() {
        return "Coupon: " + couponName + ", Discount: " + getDiscount()*100 + "%";
    }
    
}
