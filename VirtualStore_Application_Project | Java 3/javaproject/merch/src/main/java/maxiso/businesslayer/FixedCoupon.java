package maxiso.businesslayer;

public class FixedCoupon extends Coupon {

    private String couponName;

    public FixedCoupon(String couponName, Double amount){
        super(amount);
        this.couponName = couponName;
    }

    public String getCouponName() {
        return couponName;
    }

    @Override
    public String toString() {
        return "Coupon: " + couponName + ", Discount: " + getDiscount() + "$";
    }
    
}
