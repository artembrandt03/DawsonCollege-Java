package maxiso.businesslayer;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import maxiso.datalayer.merch.CouponDataHandler;
import maxiso.datalayer.merch.Merch;
import maxiso.datalayer.merch.UserDataHandler;
import maxiso.displaylayer.IFilter;

public class StoreManager {
    private User user;
    private ShoppingCart userCart;
    private List<Merch> products;

    public StoreManager(User user, ShoppingCart userCart, List<Merch> products){
        this.user = user;
        this.userCart = userCart;
        this.products = products;
    }

    public ShoppingCart getShoppingCart(){
        return this.userCart;
    }

    public List<Merch> displayCart(){
        return this.products;
    }

    /**
     * @param username
     * @param password
     * @return
     */
    public boolean register(String username, String password){
        UserDataHandler userData= new UserDataHandler();
        RegisteredUser rUser = new RegisteredUser(username, password, 0);
        try {
            userData.registerUser(rUser);
            this.user=rUser; 
            System.out.println("Successfully Sign In!");
            return true;
        } catch (IOException e) {
            System.out.println("error: "+e);
        }

        return false;
    }
    
    
    /**
     * @param usernames
     * @param name
     * @return boolean
     * 
     * search if the user exist
     */
    public boolean findUser(List<RegisteredUser> usernames,String name){
        
        for(RegisteredUser username:usernames){
            if (name.equals(username.getUsername())){
                return true;
            }
        }
        //System.out.println("Username not found"); commented this print statement out to not interfere with my registerUser() in app
        return false;
         
    }

    /**
     * user get this many point by every dollar
     * 1 point = 10$
     */
    public void addPointsToUser(){
        
        if(this.user instanceof RegisteredUser){
            RegisteredUser rUser= (RegisteredUser) this.user;
            int point= (int) this.userCart.getPriceBeforeTax();
            point= point/10;
            rUser.addPoints(point);
            System.out.println("You now have "+rUser.getPoints()+" points!");
        
        }
        
    };

    /**
     * @param user
     */
    public void logOut(User user){
        
        if (this.user== null){
            System.out.println("No user have been log in yet!");
            return;
        }
        this.user=null;
        throw new IllegalArgumentException("Not written yet");
    }
    
    /**
     * @param username
     * @param password
     * @return boolean
     * 
     * login in user if user account exist
     * and check sign in
     * 
     * could review because quite long
     */
    public boolean logIn(String username, String password) {
        if(this.user instanceof UnregisteredUser){
            return false;
        }else if (this.user instanceof RegisteredUser){
            UserDataHandler userData= new UserDataHandler();
            boolean found=false;
            try {
                found=findUser(userData.loadUsers(),username);   
            } catch (IOException e) {
                System.out.println(e);
            }
            if(found){
                RegisteredUser resgisterUser= (RegisteredUser) this.user;
                if (resgisterUser.getAccess(username, password)) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * display user cart
     */
     public void viewCart(){
        this.userCart.printCart();
    }

    /**
     * make the payment
     * ask if there is any coupon, apply coupon if valid
     * then just pay and empty the cart
     */
    public void Payment(){
        if(this.userCart==null){
            return;
        }
        if (this.user instanceof RegisteredUser) {
            addPointsToUser();
        }
        userCart.clearCart();

    }

    public double subtotal(){
        return this.userCart.getPriceBeforeTax();
    }

    public double Total(){
        return this.userCart.getPriceAfterTax();
    }

    /**
     * @param product
     * 
     * add Item to cart
     */
    public void addItemToCart(Merch product){
            this.userCart.addItem(product);

    }
         
    /**
     * @param product
     * 
     * remove Item to cart
     */
    public void removeItemToCart(Merch product){
        if (product != null && this.userCart.getItems().contains(product)) {
            this.userCart.removeItem(product);
            this.userCart.removeFromPrice(product.getRetailPrice());
        }
    
    }


    /**
     * @param coupon
     */
    public boolean applyCoupon(String coupon){
        try {
            CouponDataHandler coupons = new CouponDataHandler("javaproject\\merch\\csv\\Coupons.csv");
            List<Coupon> loadedCoupons = coupons.loadCoupons();
            CouponManager manageCoupon= new CouponManager(loadedCoupons);
            if(manageCoupon.isValidCoupon(coupon)){
                Coupon validCoupon= validCoupon(loadedCoupons,coupon);
                //in process to create coupon csv as well as coupon manager
                if (validCoupon !=null){
                    this.userCart.setCoupon(validCoupon); 
                    if (this.userCart.applyCoupon()) { 
                        return true;
                    } else {
                        return false;
                    }
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return false;
    }


    /**
     * @param loadedCoupons
     * @param couponCode
     * @return coupon if found else return null
     * 
     */
    public Coupon validCoupon(List<Coupon> loadedCoupons, String couponCode){
        for (Coupon coupon : loadedCoupons) {
            if (coupon instanceof PercentageCoupon) {
                PercentageCoupon percentageCoupon = (PercentageCoupon) coupon;
                if (percentageCoupon.getCouponName().equals(couponCode)) {
                    return percentageCoupon;
                }
            } else if (coupon instanceof FixedCoupon) {
                FixedCoupon fixedCoupon = (FixedCoupon) coupon;
                if (fixedCoupon.getCouponName().equals(couponCode)) {
                    return fixedCoupon;
                }
            }
        }
        return null;
    }

    
    public User getUser(){
        return this.user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public ShoppingCart getUserCart() {
        return userCart;
    }

    public void setUserCart(ShoppingCart userCart) {
        this.userCart = userCart;
    }

    public List<Merch> getProducts() {
        return products;
    }

    public void setProducts(ArrayList<Merch> products) {
        this.products = products;
    }

    /**
     * @param input
     * @param filter
     * @return list that is filtered
     */
    public static List<Merch> filterMerch(List<Merch> input, IFilter filter){
        List<Merch> filterList = new ArrayList<>();
        for (Merch merch : input) {
            if(filter.filter(merch)){
                filterList.add(merch);
            }
        }
        return filterList;
    }

}
