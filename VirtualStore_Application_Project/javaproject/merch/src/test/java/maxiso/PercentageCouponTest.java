package maxiso;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import maxiso.businesslayer.PercentageCoupon;

public class PercentageCouponTest {

    private static final double DELTA = 0.0001;

    @Test
    public void testConstructor() {
        // Test the constructor with a 10% discount (10.00)
        double expected = 0.9; // 10% discount as a decimal (0.10)
        PercentageCoupon coupon = new PercentageCoupon("Summer Sale", 10.00);
        double result = coupon.getDiscount();
        assertEquals(expected, result, DELTA);
    }

    @Test
    public void testGetCouponName() {
        // Test getting the coupon name
        String expected = "Summer Sale";
        PercentageCoupon coupon = new PercentageCoupon("Summer Sale", 10.00);
        String result = coupon.getCouponName();
        assertEquals(expected, result);
    }

    @Test
    public void testToString() {
        // Test the toString method output
        String expected = "Coupon: Summer Sale, Discount: 90.0%";
        PercentageCoupon coupon = new PercentageCoupon("Summer Sale", 10.00);
        String result = coupon.toString();
        assertEquals(expected, result);
    }
}
