package maxiso.displaylayer;

import maxiso.datalayer.merch.Merch;

public class PriceMinimum implements IFilter {
    //ONLY by above rn, gonna add below.
    private int targetPrice;
    
    public PriceMinimum(int targetPrice) {
        this.targetPrice = targetPrice;
    }

    @Override
    public boolean filter(Merch merch) {
        return merch.getRetailPrice() > targetPrice;
    }
    
}
