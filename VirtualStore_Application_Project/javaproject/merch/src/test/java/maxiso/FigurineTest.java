package maxiso;

import static org.junit.Assert.*;
import org.junit.*;

import maxiso.datalayer.merch.Figurine;

import java.util.*;

public class FigurineTest {

    private static final double DELTA = 0.0001;
    
    @Test
    public void testConstructorInitialization() {
        String QRCode = "QR123456";
        String categories = "Collectibles";
        String collection = "Marvel"; // Changed from List<String> to String
        double retailPrice = 29.99;
        String material = "Plastic";
        int boxCount = 1000;
        boolean limitedEdition = true;

        Figurine figurine = new Figurine(QRCode, categories, collection, retailPrice, material, boxCount, limitedEdition);

        assertEquals(QRCode, figurine.getQRCode());
        assertEquals(categories, figurine.getCategories());
        assertEquals(collection, figurine.getCollection());
        assertEquals(retailPrice, figurine.getRetailPrice(), DELTA);
        assertEquals(material, figurine.getMaterial());
        assertEquals(boxCount, figurine.getBoxCount());
        assertTrue(figurine.isLimitedEdition());
    }

    @Test
    public void testGetMaterial() {
        String collection = "Marvel"; // Changed from List<String> to String

        Figurine figurine = new Figurine("QR123456", "Collectibles", collection, 29.99, "Plastic", 1000, true);

        assertEquals("Plastic", figurine.getMaterial());
    }

    @Test
    public void testGetBoxCount() {
        String collection = "Marvel"; // Changed from List<String> to String

        Figurine figurine = new Figurine("QR123456", "Collectibles", collection, 29.99, "Plastic", 1000, true);

        assertEquals(1000, figurine.getBoxCount());
    }

    @Test
    public void testIsLimitedEdition() {
        String collection = "Marvel"; // Changed from List<String> to String

        Figurine figurine = new Figurine("QR123456", "Collectibles", collection, 29.99, "Plastic", 1000, true);

        assertTrue(figurine.isLimitedEdition());
    }

    @Test
    public void testGetQRCode() {
        String collection = "Marvel"; // Changed from List<String> to String

        Figurine figurine = new Figurine("QR123456", "Collectibles", collection, 29.99, "Plastic", 1000, true);

        assertEquals("QR123456", figurine.getQRCode());
    }

    @Test
    public void testGetCategories() {
        String collection = "Marvel"; // Changed from List<String> to String

        Figurine figurine = new Figurine("QR123456", "Collectibles", collection, 29.99, "Plastic", 1000, true);

        assertEquals("Collectibles", figurine.getCategories());
    }

    @Test
    public void testGetCollection() {
        String collection = "Marvel"; // Changed from List<String> to String

        Figurine figurine = new Figurine("QR123456", "Collectibles", collection, 29.99, "Plastic", 1000, true);

        assertEquals(collection, figurine.getCollection());
    }

    @Test
    public void testGetRetailPrice() {
        String collection = "Marvel"; // Changed from List<String> to String

        Figurine figurine = new Figurine("QR123456", "Collectibles", collection, 29.99, "Plastic", 1000, true);

        assertEquals(29.99, figurine.getRetailPrice(), DELTA);
    }

}