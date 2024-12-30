package maxiso.displaylayer;

import maxiso.datalayer.merch.Merch;

public class CollectionFilter implements IFilter {

    private String targetCollection;

    public CollectionFilter(String targetCollection) {
        this.targetCollection = targetCollection;
    }

    @Override
    public boolean filter(Merch merch) {
        return merch.getCollection().equalsIgnoreCase(targetCollection);
    }
}