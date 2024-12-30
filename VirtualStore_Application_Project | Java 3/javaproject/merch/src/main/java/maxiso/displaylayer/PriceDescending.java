package maxiso.displaylayer;

import java.util.Comparator;

import maxiso.datalayer.merch.Merch;

public class PriceDescending implements Comparator<Merch> {

    @Override
    public int compare(Merch o1, Merch o2) {
        return (int)(o2.getRetailPrice() - o1.getRetailPrice());
    }
    
    
}
