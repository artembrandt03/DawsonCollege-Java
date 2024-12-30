package maxiso.businesslayer;
import java.util.ArrayList;
import java.util.List;

import maxiso.datalayer.merch.Merch;

/**
 * The ShoppingCart class represents a shopping cart that holds products,
 * calculates the price before and after tax, and applies available coupons for the user.
 */
public class ShoppingCart {

    private List<Merch> items;
    private double priceBeforeTax;
    private Coupon coupon;

    /**
     * Constructor
     * LOGIC: when user starts a session, we want to initialize a shopping cart object
     * and add things to it as we go There would be nothing to add at first, which is why
     * items and coupon is null and price is 0.
     * @param items The list of merchandise items in the cart.
     * @param priceBeforeTax The total price of items in the cart before tax.
     * @param coupon The coupon applied to the cart.
     */
    public ShoppingCart() { 
        this.items = new ArrayList<>(); //null was going to cause nullpointerexception if I tried to add new items in the cart
        this.priceBeforeTax = 0.0;
        this.coupon = null; // No coupon applied initially
    }

    /**
     * Returns the total price of items in the cart before tax
     *
     * @return The price before tax.
     */
    public double getPriceBeforeTax() {
        return this.priceBeforeTax;
    }

    /**
     * Returns the total price of items in the cart after tax
     *
     * @return The price after tax.
     */
    public double getPriceAfterTax() {
        double priceWithTax = this.priceBeforeTax * 1.15;
        return Math.round(priceWithTax * 100.0) / 100.0;
    }

    /**
     * Decreases the total price by the specified amount
     *
     * @param amount The amount to remove from the total price
     */
    public void removeFromPrice(double amount) {
        this.priceBeforeTax -= amount;
    }

    /**
     * Returns a string representation of all the items in the cart (if there is any)
     *
     * @return A string with each line representing a Merch item in the cart
     */
    public String printCart() {
        if (items.isEmpty()) {
            return "The cart is currently empty.";
        }

        StringBuilder cartContent = new StringBuilder();
        for (Merch item : items) {
            cartContent.append(item.toString()).append("\n");
        }

        return cartContent.toString();
    }

    /**
     * Sets the coupon for the shopping cart
     *
     * @param coupon The coupon to be applied to the cart in future (in another method to actuall apply it)
     */
    public void setCoupon(Coupon coupon) {
        this.coupon = coupon;
    }

    /**
     * Applies the available coupon to the price before tax.
     */
    public Boolean applyCoupon() {
        if (coupon != null) { //Check if a coupon is applied (if the coupon field has been initialized)
            if (coupon instanceof FixedCoupon) { //Fixed coupon logic: just subtract a fixed amount
                this.priceBeforeTax -= coupon.getDiscount();
            } 
            else if (coupon instanceof PercentageCoupon || coupon instanceof LoyaltyCoupon) { //Percentage coupon logic: just multiply by the discount % (example 0.9 for 10%)
                this.priceBeforeTax *= coupon.getDiscount();
            }
            return true;
        }
        return false; //No coupon applied
    }
    

    /**
     * Removes an item from the cart.
     *
     * @param item The Merch item to remove from the cart
     */
    public void removeItem(Merch item) {
        if (items.remove(item)) {
            priceBeforeTax -= item.getRetailPrice();
        } 
    }

    /**
     * Adds an item to the cart.
     *
     * @param item The Merch item to add to the cart
     */
    public void addItem(Merch item) {
        items.add(item);
        priceBeforeTax += item.getRetailPrice(); 
    }

    /**
    * Clears all items from the cart.
    */
    public void clearCart() {
        items.clear();
        priceBeforeTax = 0.0;
    }

    public List<Merch> getItems(){
        return items;
    }

    public void applyDiscount(double discountAmount) {
        double newTotal = this.priceBeforeTax - discountAmount; // Assuming totalPrice is a field in ShoppingCart
        this.priceBeforeTax = Math.max(0, newTotal); // Ensure total doesn't go below zero
    }

}

