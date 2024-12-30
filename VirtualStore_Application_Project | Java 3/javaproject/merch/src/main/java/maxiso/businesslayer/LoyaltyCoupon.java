package maxiso.businesslayer;

public class LoyaltyCoupon extends Coupon {
    /**
     * Constructuctor.
     *
     */
    public LoyaltyCoupon() {
        super(0.0);
    }

    /**
     * Calculates the discount based on the user's points
     *
     * @param points The user's loyalty points (inisde RegisteredUsder clas)
     */
    public void calculateDiscount(int points) {
        if (points >= 100) {
            setDiscount(0.30);
        } 
        else if (points >= 50) {
            setDiscount(0.20);
        } 
        else if (points >= 10) {
            setDiscount(0.10);
        } 
        else {
            setDiscount(0.0);
        }
    }

    /**
     * Checks if the user even qualifies for a discount
     *
     * @param points The user's loyalty points
     * @return True if the user has enough points, false otherwise
     */
    public boolean canApply(int points) {
        return points >= 10;
    }
}
