package maxiso;

import static org.junit.Assert.*;
import org.junit.*;

import maxiso.businesslayer.ShoppingCart;
import maxiso.businesslayer.FixedCoupon;
import maxiso.businesslayer.PercentageCoupon;
import maxiso.datalayer.merch.Figurine;
import maxiso.datalayer.merch.Merch;
import maxiso.datalayer.merch.Plushie;

public class ShoppingCartTest {

    @Test
    public void testConstructor() {
        ShoppingCart cart = new ShoppingCart();
        assertNotNull("Cart should initialize items list, but not make it null", cart.printCart());
        assertEquals("Initial price should be 0.0 (cart is empty)", 0.0, cart.getPriceBeforeTax(), 0.001);
        assertFalse("Cart should not have any coupon applied initially", cart.applyCoupon());
    }

    @Test
    public void testAddItem() {
        ShoppingCart cart = new ShoppingCart();
        Figurine figurine = new Figurine("QR123", "Collectibles", "One Piece", 50.0, "PVC", 3, true);
        Plushie plushie = new Plushie("QR456", "Toys", 100.0, "Sanrio", "Pink", 15, "Cotton", "Polyester", true);

        cart.addItem(figurine);
        cart.addItem(plushie);

        assertEquals("Cart total price should be the sum of those 2 items", 150.0, cart.getPriceBeforeTax(), 0.001);
        assertTrue("Cart should contain figurine", cart.printCart().contains("Figurine"));
        assertTrue("Cart should contain plushie", cart.printCart().contains("Plushie"));
    }

    @Test
    public void testRemoveItem() {
        ShoppingCart cart = new ShoppingCart();
        Figurine figurine = new Figurine("QR123", "Collectibles", "One Piece", 50.0, "Plastic", 3, true);
        Plushie plushie = new Plushie("QR456", "Toys", 100.0, "Sanrio", "Pink", 15, "Cotton", "Polyester", true);

        cart.addItem(figurine);
        cart.addItem(plushie);
        cart.removeItem(figurine);

        assertEquals("Cart price should decrease after removing the figurine", 100.0, cart.getPriceBeforeTax(), 0.001);
        assertFalse("Cart should not contain figurine since we removed it", cart.printCart().contains("Figurine"));
    }

    @Test
    public void testClearCart() {
        ShoppingCart cart = new ShoppingCart();
        Figurine figurine = new Figurine("QR123", "Collectibles", "One Piece", 50.0, "Plastic", 3, true);
        Plushie plushie = new Plushie("QR456", "Toys", 100.0, "Sanrio", "Pink", 15, "Cotton", "Polyester", true);

        cart.addItem(figurine);
        cart.addItem(plushie);
        cart.clearCart();

        assertEquals("Cart total price should be 0 after clearing it", 0.0, cart.getPriceBeforeTax(), 0.001);
        assertTrue("Cart should be empty after clearing it", cart.printCart().contains("The cart is currently empty"));
    }

    @Test
    public void testApplyFixedCoupon() {
        ShoppingCart cart = new ShoppingCart();
        Figurine figurine = new Figurine("QR123", "Collectibles", "One Piece", 50.0, "Plastic", 3, true);
        Plushie plushie = new Plushie("QR456", "Toys", 100.0, "Sanrio", "Pink", 15, "Cotton", "Polyester", true);
        FixedCoupon fixedCoupon = new FixedCoupon("MAXISO10", 10.0);

        cart.addItem(figurine);
        cart.addItem(plushie);
        cart.setCoupon(fixedCoupon);
        cart.applyCoupon();

        assertEquals("Price should decrease by the fixed discount (10$)", 140.0, cart.getPriceBeforeTax(), 0.001);
    }

    @Test
    public void testApplyPercentageCoupon() {
        ShoppingCart cart = new ShoppingCart();
        Figurine figurine = new Figurine("QR123", "Collectibles", "One Piece", 50.0, "Plastic", 3, true);
        Plushie plushie = new Plushie("QR456", "Toys", 100.0, "Sanrio", "Pink", 15, "Cotton", "Polyester", true);
        PercentageCoupon percentageCoupon = new PercentageCoupon("HI", 10.00);

        cart.addItem(figurine);
        cart.addItem(plushie);
        cart.setCoupon(percentageCoupon);
        cart.applyCoupon();

        assertEquals("Price should be 150 - 10% = 135$", 135.0, cart.getPriceBeforeTax(), 0.001);
    }

    @Test
    public void testNoCouponApplied() {
        ShoppingCart cart = new ShoppingCart();
        Figurine figurine = new Figurine("QR123", "Collectibles", "One Piece", 50.0, "PVC", 3, true);
        Plushie plushie = new Plushie("QR456", "Toys", 100.0, "Sanrio", "Pink", 15, "Cotton", "Polyester", true);

        cart.addItem(figurine);
        cart.addItem(plushie);

        boolean result = cart.applyCoupon(); //should be set to false

        assertFalse("applyCoupon should return false if no coupon is set", result);
        assertEquals("Price should remain the same since no coupon was applied", 150.0, cart.getPriceBeforeTax(), 0.001);
    }

    @Test
    public void testPriceAfterTax() {
        ShoppingCart cart = new ShoppingCart();
        Figurine figurine = new Figurine("QR123", "Collectibles", "One Piece", 50.0, "Plastic", 3, true);
        Plushie plushie = new Plushie("QR456", "Toys", 100.0, "Sanrio", "Pink", 15, "Cotton", "Polyester", true);

        cart.addItem(figurine);
        cart.addItem(plushie);

        double expectedAfterTax = 150.0 * 1.15;
        assertEquals(expectedAfterTax, cart.getPriceAfterTax(), 0.001);
    }

    @Test
    public void testRemoveFromPrice() {
        ShoppingCart cart = new ShoppingCart();
        Figurine figurine = new Figurine("QR123", "Collectibles", "One Piece", 50.0, "Plastic", 3, true);
        Plushie plushie = new Plushie("QR456", "Toys", 100.0, "Sanrio", "Pink", 15, "Cotton", "Polyester", true);

        cart.addItem(figurine);
        cart.addItem(plushie);
        cart.removeFromPrice(50.0);

        assertEquals("Price should decrease by 50$", 100.0, cart.getPriceBeforeTax(), 0.001);
    }




}