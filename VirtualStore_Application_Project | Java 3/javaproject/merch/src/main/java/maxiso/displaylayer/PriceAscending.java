package maxiso.displaylayer;

import java.util.Comparator;

import maxiso.datalayer.merch.Merch;

public class PriceAscending implements Comparator<Merch> {

    @Override
    public int compare(Merch o1, Merch o2) {
        return (int)(o1.getRetailPrice() - o2.getRetailPrice());
    }
    
    
}
