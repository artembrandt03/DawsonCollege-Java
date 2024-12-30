package maxiso;
//This doesn not compile or run yet, becasue RegisteredUser isn't merged yet (skeleton class)

import static org.junit.Assert.*;
import org.junit.*;

import maxiso.businesslayer.RegisteredUser;


public class RegisteredUserTest {
    
    @Test
    public void testConstructorWithGetters() {
        String username = "artembrandt";
        String password = "password123456";
        int points = 100;

        RegisteredUser user = new RegisteredUser(username, password, points);

        assertEquals(username, user.getUsername());
        assertEquals(password, user.getPassword());
        assertEquals(points, user.getPoints());
    }

    @Test
    public void testAddPoints() {
        RegisteredUser user = new RegisteredUser("artembrandt", "password123456", 50);

        user.addPoints(30);
        assertEquals(80, user.getPoints());

        user.addPoints(20);
        assertEquals(100, user.getPoints());
    }

    @Test
    public void testGetAccess() {
        String username = "artembrandt";
        String password = "password123456";
        RegisteredUser user = new RegisteredUser(username, password, 0);

        assertTrue(user.getAccess(username, password)); //should return true as both match
        assertFalse(user.getAccess(username, "wrongpass")); //should return false as the password is wrong
        assertFalse(user.getAccess("wronguser", password)); //should return false as username doesn't match
    }

    @Test
    public void testResetPassword() {
        RegisteredUser user = new RegisteredUser("artembrandt", "password123456", 0);

        String newPassword = "omnivox_123456";
        user.resetPassword(newPassword);
        assertEquals(newPassword, user.getPassword());
    }
}
