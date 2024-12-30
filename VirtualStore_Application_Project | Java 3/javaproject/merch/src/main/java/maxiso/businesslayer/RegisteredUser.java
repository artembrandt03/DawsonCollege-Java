package maxiso.businesslayer;
import java.util.Date;

public class RegisteredUser implements User {
    
    private String username;
    private String password;
    private int points;
    private Date dateOfCreation;

    /**
     * Constructor
     *
     * @param username 
     * @param password 
     * @param points
     */
    public RegisteredUser(String username, String password, int points) {
        this.username = username;
        this.password = password;
        this.points = points;
        this.dateOfCreation = new Date();
    }

    
    public int getPoints() {
        return this.points;
    }

    /**
     * Adds points to the user's account.
     *
     * @param points The points that will be added.
     */
    public void addPoints(int points) {
        this.points += points;
    }

    public String getUsername() {
        return this.username;
    }

    public String getPassword() {
        return this.password;
    }

    /**
     * This method checks if username and password match/
     *
     * @param username The username to check.
     * @param password The password to check.
     * @return True if credentials match, otherwise will return false.
     */
    public boolean getAccess(String username, String password) {
        if(this.username.equals(username) && this.password.equals(password)){
            return true;
        } else {
            return false;
        }
    }

    public Date getDateOfCreation() {
        return this.dateOfCreation;
    }

    /**
     * Resets password.
     *
     * @param newPassword The new password to set for the user.
     */
    public void resetPassword(String newPassword) {
        this.password = newPassword;
    }

    /**
     * Deducts points from the user's account.
     *
     * @param points The points to deduct.
     */
    public void spendPoints(int points) {
        if (points <= this.points) {
            this.points -= points;
        } else {
            throw new IllegalArgumentException("Insufficient points to spend.");
        }
    }


    @Override
    public String toString() {
        return "RegisteredUser [username=" + username + ", points=" + points + ", dateOfCreation=" + dateOfCreation
                + "]";
    }
    
}
