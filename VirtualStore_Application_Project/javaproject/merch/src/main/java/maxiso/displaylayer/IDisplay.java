package maxiso.displaylayer;

import java.util.*;
import maxiso.datalayer.merch.*;

/**
 * Interface for displaying details of merchandise items.
 */
public interface IDisplay {

    /**
     * Displays the collection of a merchandise item.
     * 
     * @param merch The merch item.
     * @return The formatted collection display.
     */
    String collectionDisplay(Merch merch);

    /**
     * Displays the categories of a merchandise item.
     * 
     * @param merch The merch item.
     * @return The formatted category display.
     */
    String categoryDisplay(Merch merch);

    /**
     * Displays all details of a merchandise item.
     * 
     * @param merch The merch item.
     * @return The formatted details display.
     */
    String allDisplay(Merch merch);

    /**
     * Gets all collections from a list of merchandise items.
     * 
     * @param merchList The list of merch items.
     * @return A set of collection names.
     */
    HashSet<String> getCollections(List<Merch> merchList);
}
