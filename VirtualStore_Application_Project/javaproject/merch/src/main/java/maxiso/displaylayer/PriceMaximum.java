package maxiso.displaylayer;

import maxiso.datalayer.merch.Merch;

public class PriceMaximum implements IFilter {
    //ONLY by above rn, gonna add below.
    private int targetPrice;
    
    public PriceMaximum(int targetPrice) {
        this.targetPrice = targetPrice;
    }

    @Override
    public boolean filter(Merch merch) {
        return merch.getRetailPrice() < targetPrice;
    }
    
}
