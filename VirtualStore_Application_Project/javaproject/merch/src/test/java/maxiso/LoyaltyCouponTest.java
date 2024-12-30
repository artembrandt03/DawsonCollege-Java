package maxiso;
import static org.junit.Assert.*;
import org.junit.*;
import maxiso.businesslayer.Coupon;
import maxiso.businesslayer.LoyaltyCoupon;

public class LoyaltyCouponTest {
    
    @Test
    public void testConstructor() {
        LoyaltyCoupon coupon = new LoyaltyCoupon();
        assertEquals("Initial discount should be 0", 0.0, coupon.getDiscount(), 0.001);
    }

    @Test
    public void testCalculateDiscount() {
        LoyaltyCoupon coupon = new LoyaltyCoupon();

        //for 0 points
        coupon.calculateDiscount(0);
        assertEquals("Discount should be 0 for less than 10 points", 0.0, coupon.getDiscount(), 0.001);

        //for 10 points
        coupon.calculateDiscount(10);
        assertEquals("Discount should be 0.10 for 10 points", 0.10, coupon.getDiscount(), 0.001);

        //for 50 points
        coupon.calculateDiscount(50);
        assertEquals("Discount should be 0.20 for 50 points", 0.20, coupon.getDiscount(), 0.001);

        //for 100 points
        coupon.calculateDiscount(100);
        assertEquals("Discount should be 0.30 for 100 or more points", 0.30, coupon.getDiscount(), 0.001);

        //more than 100 points
        coupon.calculateDiscount(150);
        assertEquals("Discount should still be 0.30 for more than 100 points", 0.30, coupon.getDiscount(), 0.001);
    }

    @Test
    public void testCanApply() {
        LoyaltyCoupon coupon = new LoyaltyCoupon();

        //for less than 10 points
        assertFalse("User with less than 10 points should not even qualify for a discount", coupon.canApply(0));

        //for exactly 10 points
        assertTrue("User with 10 points should qualify for a discount (it's 10 points or more)", coupon.canApply(10));

        //for more than 10 points
        assertTrue("User with 50 points should qualify for a discount", coupon.canApply(50));
    }

}
