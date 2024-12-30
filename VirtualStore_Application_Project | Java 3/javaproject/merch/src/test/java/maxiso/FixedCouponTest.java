package maxiso;

import static org.junit.Assert.*;
import org.junit.*;

import maxiso.businesslayer.FixedCoupon;

public class FixedCouponTest {

    private static final double DELTA = 0.0001;

    @Test
    public void testCoupons() {
        FixedCoupon coupon = new FixedCoupon("MAXISO10", 10.00);
        assertEquals("MAXISO10", coupon.getCouponName());
        assertEquals(10.00, coupon.getDiscount(), DELTA);

        coupon = new FixedCoupon("HI20", 20.00);
        assertEquals("HI20", coupon.getCouponName());
        assertEquals(20.00, coupon.getDiscount(), DELTA);

        coupon = new FixedCoupon("HAWKTUAH", 50.00);
        assertEquals("HAWKTUAH", coupon.getCouponName());
        assertEquals(50.00, coupon.getDiscount(), DELTA);

        coupon = new FixedCoupon("THOSEWHOKNOW", 100.00);
        assertEquals("THOSEWHOKNOW", coupon.getCouponName());
        assertEquals(100.00, coupon.getDiscount(), DELTA);

        coupon = new FixedCoupon("EMPTY", 0.00);
        assertEquals("EMPTY", coupon.getCouponName());
        assertEquals(0.00, coupon.getDiscount(), DELTA);

        coupon = new FixedCoupon("INVALID", 0.00);
        assertEquals("INVALID", coupon.getCouponName());
        assertEquals(0.00, coupon.getDiscount(), DELTA);
    }

    @Test
    public void testToString() {
        FixedCoupon coupon = new FixedCoupon("MAXISO10", 10.00);
        String expected = "Coupon: MAXISO10, Discount: 10.0$";
        assertEquals(expected, coupon.toString());

        coupon = new FixedCoupon("EMPTY", 0.00);
        expected = "Coupon: EMPTY, Discount: 0.0$";
        assertEquals(expected, coupon.toString());
    }
}
