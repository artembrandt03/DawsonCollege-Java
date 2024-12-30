package maxiso.businesslayer;

public class UnregisteredUser implements User {

    private String guestID;

    /**
     * Constructor
     *
     * @param guestID a unique ID for the guest user.
     */
    public UnregisteredUser(String guestID) {
        this.guestID = guestID;
    }

    public String getGuestID() {
        return this.guestID;
    }
    
    

    @Override
    public String toString() {
        return "GuestID" + this.guestID ;
    }
    
}
