package maxiso.displaylayer;

import java.util.*;
import maxiso.datalayer.merch.*;

/**
 * Handles display logic for Electronics.
 */
public class ElectronicDisplay implements IDisplay {

    /**
     * Displays the collection of the Electronic.
     * 
     * @param merch The merch item.
     * @return The formatted collection display.
     */
    @Override
    public String collectionDisplay(Merch merch) {
        if (merch instanceof Electronic) {
            Electronic electronic = (Electronic) merch;
            return "==================\n" +
                   "Name: " + electronic.getCategories() + "\n" +
                   "Price: $" + electronic.getRetailPrice() + "\n" +
                   "==================\n";
        }
        throw new IllegalArgumentException("Merch is not an Electronic");
    }
    

    /**
     * Displays the categories of the Electronic.
     * 
     * @param merch The merch item.
     * @return The formatted category display.
     */
    @Override
    public String categoryDisplay(Merch merch) {
        if (merch instanceof Electronic) {
            Electronic electronic = (Electronic) merch;
            return "------------------\n" +
                   electronic.getCategories() + "\n" +
                   "------------------\n";
        }
        throw new IllegalArgumentException("Merch is not an Electronic");
    }

    /**
     * Displays all details of the Electronic.
     * 
     * @param merch The merch item.
     * @return The formatted details display.
     */
    @Override
    public String allDisplay(Merch merch) {
        if (merch instanceof Electronic) {
            Electronic electronic = (Electronic) merch;
            StringBuilder display = new StringBuilder();
            display.append("------------------\n")
                   .append(electronic.getCollection()).append("\n")
                   .append("------------------\n")
                   .append(electronic.getCategories()).append("\n")
                   .append("------------------\n");

            display.append("PRODUCT TYPE: ").append(electronic.getProductType()).append("\n")
                   .append("MODEL NUMBER: ").append(electronic.getModelNumber()).append("\n")
                   .append("BATTERY CAPACITY: ").append(electronic.getBatteryCapacity()).append(" mAh\n")
                   .append("WEIGHT: ").append(String.format("%.2f", electronic.getWeight())).append(" kg\n")
                   .append("WATER RESISTANT: ").append(electronic.isWaterResistant() ? "Yes" : "No").append("\n")
                   .append("WIRELESS: ").append(electronic.isWireless() ? "Yes" : "No").append("\n")
                   .append("------------------\n")
                   .append("PRICE: ").append(String.format("%.2f", electronic.getRetailPrice())).append("\n")
                   .append("------------------\n");

            return display.toString();
        }
        throw new IllegalArgumentException("Merch is not an Electronic");
    }

    /**
     * Gets all collections from a list of Electronics.
     * 
     * @param merchList The list of merch items.
     * @return A set of collection names.
     */
    @Override
    public HashSet<String> getCollections(List<Merch> merchList) {
        HashSet<String> possibleCollections = new HashSet<>();
        for (Merch m : merchList) {
            if (m instanceof Electronic) {
                Electronic electronic = (Electronic) m;
                possibleCollections.add(electronic.getCollection());
            }
        }
        return possibleCollections;
    }
}
