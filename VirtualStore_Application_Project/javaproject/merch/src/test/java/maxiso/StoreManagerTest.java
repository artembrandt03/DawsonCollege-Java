package maxiso;

import static org.junit.Assert.*;
import org.junit.Test;
import java.util.*;

import maxiso.businesslayer.*;
import maxiso.displaylayer.*;
import maxiso.datalayer.merch.*;

public class StoreManagerTest {
    @Test
    public void testFindUser() {
        ShoppingCart cart = new ShoppingCart();
        List<RegisteredUser> users = new ArrayList<>();
        users.add(new RegisteredUser("existingUser", "password", 0));

        StoreManager manager = new StoreManager(null, cart, new ArrayList<>());
        assertTrue("User should be found", manager.findUser(users, "existingUser"));
        assertFalse("User should not be found", manager.findUser(users, "nonExistingUser"));
    }

    @Test
    public void testAddPointsToUser() {
        ShoppingCart cart = new ShoppingCart();
        cart.addItem(new Figurine("QR123", "Collectibles", "One Piece", 50.0, "PVC", 3, true));
        cart.addItem(new Plushie("QR456", "Toys", 100.0, "Sanrio", "Pink", 15, "Cotton", "Polyester", true));

        RegisteredUser user = new RegisteredUser("user1", "password123", 0);
        StoreManager manager = new StoreManager(user, cart, new ArrayList<>());

        manager.addPointsToUser();
        assertEquals("Points should be calculated correctly", 15, user.getPoints());
    }

    @Test
    public void testViewCart() {
        ShoppingCart cart = new ShoppingCart();
        cart.addItem(new Figurine("QR123", "Collectibles", "One Piece", 50.0, "PVC", 3, true));
        cart.addItem(new Plushie("QR456", "Toys", 100.0, "Sanrio", "Pink", 15, "Cotton", "Polyester", true));

        RegisteredUser user = new RegisteredUser("user1", "password123", 0);
        StoreManager manager = new StoreManager(user, cart, new ArrayList<>());

        String cartContents = cart.printCart();
        assertTrue("Cart should contain Figurine", cartContents.contains("Figurine"));
        assertTrue("Cart should contain Plushie", cartContents.contains("Plushie"));
    }

    @Test
    public void testPayment() {
        ShoppingCart cart = new ShoppingCart();
        cart.addItem(new Figurine("QR123", "Collectibles", "One Piece", 50.0, "PVC", 3, true));
        cart.addItem(new Plushie("QR456", "Toys", 100.0, "Sanrio", "Pink", 15, "Cotton", "Polyester", true));

        RegisteredUser user = new RegisteredUser("user1", "password123", 0);
        StoreManager manager = new StoreManager(user, cart, new ArrayList<>());

        manager.Payment();
        assertEquals("Cart should be empty after payment", 0.0, cart.getPriceBeforeTax(), 0.001);
    }

    @Test
    public void testFilterMerch() {
        List<Merch> products = new ArrayList<>();
        products.add(new Figurine("QR123", "Collectibles", "One Piece", 50.0, "PVC", 3, true));
        products.add(new Plushie("QR456", "Toys", 100.0, "Sanrio", "Pink", 15, "Cotton", "Polyester", true));

        IFilter priceFilter = new PriceMinimum(60);
        List<Merch> filtered = StoreManager.filterMerch(products, priceFilter);

        assertEquals("Only one item should pass the filter", 1, filtered.size());
    }
}
