package maxiso.displaylayer;

import java.util.*;
import maxiso.datalayer.merch.*;

/**
 * Handles display logic for all merch types (Figurines, Plushies, Electronics).
 */
public class AllDisplay implements IDisplay {

    @Override
    public String collectionDisplay(Merch merch) {
        String collectionDisplay = "";

        if (merch instanceof Figurine) {
            FigurineDisplay figDisplay = new FigurineDisplay();
            collectionDisplay += figDisplay.collectionDisplay(merch);
        } else if (merch instanceof Plushie) {
            PlushieDisplay plushieDisplay = new PlushieDisplay();
            collectionDisplay += plushieDisplay.collectionDisplay(merch);
        } else if (merch instanceof Electronic) {
            ElectronicDisplay electronicDisplay = new ElectronicDisplay();
            collectionDisplay += electronicDisplay.collectionDisplay(merch);
        } else {
            throw new IllegalArgumentException("Unsupported merch type");
        }

        return collectionDisplay;
    }

    @Override
    public String categoryDisplay(Merch merch) {
        String categoryDisplay = "";

        if (merch instanceof Figurine) {
            FigurineDisplay figDisplay = new FigurineDisplay();
            categoryDisplay += figDisplay.categoryDisplay(merch);
        } else if (merch instanceof Plushie) {
            PlushieDisplay plushieDisplay = new PlushieDisplay();
            categoryDisplay += plushieDisplay.categoryDisplay(merch);
        } else if (merch instanceof Electronic) {
            ElectronicDisplay electronicDisplay = new ElectronicDisplay();
            categoryDisplay += electronicDisplay.categoryDisplay(merch);
        } else {
            throw new IllegalArgumentException("Unsupported merch type");
        }

        return categoryDisplay;
    }

    @Override
    public String allDisplay(Merch merch) {
        String allDisplay = "";

        if (merch instanceof Figurine) {
            FigurineDisplay figDisplay = new FigurineDisplay();
            allDisplay += figDisplay.allDisplay(merch);
        } else if (merch instanceof Plushie) {
            PlushieDisplay plushieDisplay = new PlushieDisplay();
            allDisplay += plushieDisplay.allDisplay(merch);
        } else if (merch instanceof Electronic) {
            ElectronicDisplay electronicDisplay = new ElectronicDisplay();
            allDisplay += electronicDisplay.allDisplay(merch);
        } else {
            throw new IllegalArgumentException("Unsupported merch type");
        }

        return allDisplay;
    }

    @Override
    public HashSet<String> getCollections(List<Merch> merchList) {
        HashSet<String> possibleCollections = new HashSet<>();

        for (Merch m : merchList) {
            if (m instanceof Figurine) {
                Figurine figurine = (Figurine) m;
                possibleCollections.add(figurine.getCollection());
            } else if (m instanceof Plushie) {
                Plushie plushie = (Plushie) m;
                possibleCollections.add(plushie.getCollection());
            } else if (m instanceof Electronic) {
                Electronic electronic = (Electronic) m;
                possibleCollections.add(electronic.getCollection());
            }
        }

        return possibleCollections;
    }

}
