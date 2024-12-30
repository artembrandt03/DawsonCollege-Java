package maxiso;
//doesn't run or compile yet, requires UnregisteredUser to be merged first
import static org.junit.Assert.assertEquals;
import org.junit.Test;

import maxiso.businesslayer.UnregisteredUser;

public class UnregisteredUserTest {
    
    @Test
    public void testConstructorAndGetGuestID() {
        String guestID = "0163";
        UnregisteredUser user = new UnregisteredUser("0163");
        String result= user.getGuestID();
        assertEquals(guestID,result);
    }
}
