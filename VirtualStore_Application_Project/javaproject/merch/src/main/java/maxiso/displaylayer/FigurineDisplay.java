package maxiso.displaylayer;

import java.util.*;
import maxiso.datalayer.merch.*;

/**
 * Handles display logic for Figurines.
 */
public class FigurineDisplay implements IDisplay {

    /**
     * Displays the collection of the Figurine.
     * 
     * @param merch The merch item.
     * @return The formatted collection display.
     */
    @Override
    public String collectionDisplay(Merch merch) {
        if (merch instanceof Figurine) {
            Figurine figurine = (Figurine) merch;
            return "------------------\n" +
                   figurine.getCollection() + "\n" +
                   "------------------\n";
        }
        throw new IllegalArgumentException("Merch is not a Figurine");
    }

    /**
     * Displays the categories of the Figurine.
     * 
     * @param merch The merch item.
     * @return The formatted category display.
     */
    @Override
    public String categoryDisplay(Merch merch) {
        if (merch instanceof Figurine) {
            Figurine figurine = (Figurine) merch;
            return "==================\n" +
                   "Name: " + figurine.getCategories() + "\n" +
                   "Price: $" + figurine.getRetailPrice() + "\n" +
                   "==================\n";
        }
        throw new IllegalArgumentException("Merch is not a Figurine");
    }

    /**
     * Displays all details of the Figurine.
     * 
     * @param merch The merch item.
     * @return The formatted details display.
     */
    @Override
    public String allDisplay(Merch merch) {
        if (merch instanceof Figurine) {
            Figurine figurine = (Figurine) merch;
            StringBuilder display = new StringBuilder();
            display.append("------------------\n")
                   .append(figurine.getCollection()).append("\n")
                   .append("------------------\n")
                   .append(figurine.getCategories()).append("\n")
                   .append("------------------\n");

            if (figurine.isLimitedEdition()) {
                display.append("LIMITED EDITION ;)\n");
            }

            display.append("------------------\n")
                   .append("MADE FROM ").append(figurine.getMaterial()).append("\n")
                   .append("PRICE: ").append(String.format("%.2f", figurine.getRetailPrice())).append("\n")
                   .append("------------------\n");

            return display.toString();
        }
        throw new IllegalArgumentException("Merch is not a Figurine");
    }

    /**
     * Gets all collections from a list of Figurines.
     * 
     * @param merchList The list of merch items.
     * @return A set of collection names.
     */
    @Override
    public HashSet<String> getCollections(List<Merch> merchList) {
        HashSet<String> possibleCollections = new HashSet<>();
        for (Merch m : merchList) {
            if (m instanceof Figurine) {
                Figurine figurine = (Figurine) m;
                possibleCollections.add(figurine.getCollection());
            }
        }
        return possibleCollections;
    }

}
