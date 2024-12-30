package maxiso.businesslayer;

import java.util.*;

public class CouponManager {

    private List<Coupon> coupons;

    public CouponManager(List<Coupon> coupons) {
        this.coupons = coupons;
    }

    public boolean isValidCoupon(String input) {
        for (Coupon coupon : this.coupons) {
            if (coupon instanceof PercentageCoupon) {
                PercentageCoupon percentageCoupon = (PercentageCoupon) coupon;
                if (percentageCoupon.getCouponName().equals(input)) {
                    return true;
                }
            } else if (coupon instanceof FixedCoupon) {
                FixedCoupon fixedCoupon = (FixedCoupon) coupon;
                if (fixedCoupon.getCouponName().equals(input)) {
                    return true;
                }
            }
        }
        return false;
    }

    public Coupon getCoupon(String input) {
        for (Coupon coupon : this.coupons) {
            if (coupon instanceof PercentageCoupon) {
                PercentageCoupon percentageCoupon = (PercentageCoupon) coupon;
                if (percentageCoupon.getCouponName().equals(input)) {
                    return percentageCoupon;
                }
            } else if (coupon instanceof FixedCoupon) {
                FixedCoupon fixedCoupon = (FixedCoupon) coupon;
                if (fixedCoupon.getCouponName().equals(input)) {
                    return fixedCoupon;
                }
            }
        }
        return null;
    }
}
