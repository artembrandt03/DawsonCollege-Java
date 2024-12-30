package maxiso;

import static org.junit.Assert.*;
import org.junit.*;

import maxiso.datalayer.merch.Electronic;
import maxiso.datalayer.merch.electronicProduct;

import java.util.*;

public class ElectronicTest {
    
    @Test
    public void testConstructor() {
        electronicProduct productType = electronicProduct.POWERBANK;
        String modelNumber = "PB1234";
        int batteryCapacity = 10000; //mAh
        double weight = 250.0;
        boolean waterResistant = true;
        boolean wireless = true;
        String qrCode = "QR123456";
        String categories = "Electronics";
        double retailPrice = 49.99;
        String collection = "Power Collection";

        Electronic electronic = new Electronic(productType, modelNumber, batteryCapacity, weight, waterResistant, wireless, qrCode, categories, retailPrice, collection);

        assertEquals(productType, electronic.getProductType());
        assertEquals(modelNumber, electronic.getModelNumber());
        assertEquals(batteryCapacity, electronic.getBatteryCapacity());
        assertEquals(weight, electronic.getWeight(), 0.001);
        assertEquals(waterResistant, electronic.isWaterResistant());
        assertEquals(wireless, electronic.isWireless());
        assertEquals(qrCode, electronic.getQRCode());
        assertEquals(categories, electronic.getCategories());
        assertEquals(retailPrice, electronic.getRetailPrice(), 0.001);
        assertEquals(collection, electronic.getCollection());
    }

    @Test
    public void testGetProductType() {
        electronicProduct productType = electronicProduct.POWERBANK;
        Electronic electronic = new Electronic(productType, "PB1234", 10000, 250.0, true, true, "QR123456", "Electronics", 49.99, "Power Collection");

        assertEquals(productType, electronic.getProductType());
    }

    @Test
    public void testGetModelNumber() {
        String modelNumber = "PB1234";
        Electronic electronic = new Electronic(electronicProduct.POWERBANK, modelNumber, 10000, 250.0, true, true, "QR123456", "Electronics", 49.99, "Power Collection");

        assertEquals(modelNumber, electronic.getModelNumber());
    }

    @Test
    public void testGetBatteryCapacity() {
        int batteryCapacity = 10000;
        Electronic electronic = new Electronic(electronicProduct.POWERBANK, "PB1234", batteryCapacity, 250.0, true, true, "QR123456", "Electronics", 49.99, "Power Collection");

        assertEquals(batteryCapacity, electronic.getBatteryCapacity());
    }

    @Test
    public void testGetWeight() {
        double weight = 250.0;
        Electronic electronic = new Electronic(electronicProduct.POWERBANK, "PB1234", 10000, weight, true, true, "QR123456", "Electronics", 49.99, "Power Collection");

        assertEquals(weight, electronic.getWeight(), 0.001);
    }

    @Test
    public void testIsWaterResistant() {
        boolean waterResistant = true;
        Electronic electronic = new Electronic(electronicProduct.POWERBANK, "PB1234", 10000, 250.0, waterResistant, true, "QR123456", "Electronics", 49.99, "Power Collection");

        assertEquals(waterResistant, electronic.isWaterResistant());
    }

    @Test
    public void testIsWireless() {
        boolean wireless = true;
        Electronic electronic = new Electronic(electronicProduct.POWERBANK, "PB1234", 10000, 250.0, true, wireless, "QR123456", "Electronics", 49.99, "Power Collection");

        assertEquals(wireless, electronic.isWireless());
    }


}
