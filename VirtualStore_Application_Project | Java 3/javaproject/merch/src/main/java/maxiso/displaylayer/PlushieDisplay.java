package maxiso.displaylayer;

import java.util.*;
import maxiso.datalayer.merch.*;

/**
 * Handles display logic for Plushies.
 */
public class PlushieDisplay implements IDisplay {

    /**
     * Displays the collection of the Plushie.
     * 
     * @param merch The merch item.
     * @return The formatted collection display.
     */
    @Override
    public String collectionDisplay(Merch merch) {
        if (merch instanceof Plushie) {
            Plushie plushie = (Plushie) merch;
            return "------------------\n" +
                   plushie.getCollection() + "\n" +
                   "------------------\n";
        }
        throw new IllegalArgumentException("Merch is not a Plushie");
    }

    /**
     * Displays the categories of the Plushie.
     * 
     * @param merch The merch item.
     * @return The formatted category display.
     */
    @Override
    public String categoryDisplay(Merch merch) {
        if (merch instanceof Plushie) {
            Plushie plushie = (Plushie) merch;
            return "==================\n" +
                   "Name: " + plushie.getCategories() + "\n" +
                   "Price: $" + plushie.getRetailPrice() + "\n" +
                   "==================\n";
        }
        throw new IllegalArgumentException("Merch is not a Plushie");
    }

    /**
     * Displays all details of the Plushie.
     * 
     * @param merch The merch item.
     * @return The formatted details display.
     */
    @Override
    public String allDisplay(Merch merch) {
        if (merch instanceof Plushie) {
            Plushie plushie = (Plushie) merch;
            StringBuilder display = new StringBuilder();
            display.append("------------------\n")
                   .append(plushie.getCollection()).append("\n")
                   .append("------------------\n")
                   .append(plushie.getCategories()).append("\n")
                   .append("------------------\n");

            display.append("COLOR: ").append(plushie.getColor()).append("\n")
                   .append("SIZE: ").append(plushie.getSize()).append("\n")
                   .append("MATERIAL: ").append(plushie.getMaterial()).append("\n")
                   .append("STUFFING: ").append(plushie.getStuffing()).append("\n")
                   .append("WASHABLE: ").append(plushie.getWashable() ? "Yes" : "No").append("\n")
                   .append("------------------\n")
                   .append("PRICE: ").append(String.format("%.2f", plushie.getRetailPrice())).append("\n")
                   .append("------------------\n");

            return display.toString();
        }
        throw new IllegalArgumentException("Merch is not a Plushie");
    }

    /**
     * Gets all collections from a list of Plushies.
     * 
     * @param merchList The list of merch items.
     * @return A set of collection names.
     */
    @Override
    public HashSet<String> getCollections(List<Merch> merchList) {
        HashSet<String> possibleCollections = new HashSet<>();
        for (Merch m : merchList) {
            if (m instanceof Plushie) {
                Plushie plushie = (Plushie) m;
                possibleCollections.add(plushie.getCollection());
            }
        }
        return possibleCollections;
    }
}
