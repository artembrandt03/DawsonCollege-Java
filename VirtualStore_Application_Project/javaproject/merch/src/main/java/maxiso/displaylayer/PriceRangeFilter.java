package maxiso.displaylayer;

import maxiso.datalayer.merch.Merch;

//idea: be able to not just filter by ascedning/descending price, but set a custom range
public class PriceRangeFilter implements IFilter {
    private double minPrice;
    private double maxPrice;

    public PriceRangeFilter(double minPrice, double maxPrice) {
        this.minPrice = minPrice;
        this.maxPrice = maxPrice;
    }

    @Override
    public boolean filter(Merch merch) {
        double price = merch.getRetailPrice();
        return price >= minPrice && price <= maxPrice;
    }

}
