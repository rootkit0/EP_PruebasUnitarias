package data.HealthCardIdTests;

import data.HealthCardID;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class HealthCardIdMethodsTest {

    private HealthCardID hCardID;
    private final String personalID = "BBBBBBBBQR648597807024000012";
    private final String personalID2 = "BBBBBBBBQR648597807024000000";

    @BeforeEach
    public void init() throws Exception {
        hCardID = new HealthCardID(personalID);
    }

    @Test
    public void getPersonalIDTest() {
        assertEquals(hCardID.getPersonalID(), personalID);
        assertNotEquals(hCardID.getPersonalID(), personalID2);
    }

    @Test
    public void equalsTest() throws Exception {
        HealthCardID hCardID2 = new HealthCardID(personalID);
        assertEquals(hCardID, hCardID2);
    }

    @Test
    public void notEqualsTest() throws Exception {
        HealthCardID hCardID2 = new HealthCardID(personalID2);
        assertNotEquals(hCardID, hCardID2);
        assertNotEquals(hCardID, null);
        assertNotEquals(hCardID2, null);
    }

    @Test
    public void hashCodeTest() throws Exception {
        HealthCardID hCardID2 = new HealthCardID(personalID);
        HealthCardID hCardID3 = new HealthCardID(personalID2);
        assertEquals(hCardID.hashCode(), hCardID2.hashCode());
        assertNotEquals(hCardID2.hashCode(), hCardID3.hashCode());
    }

    @Test
    public void toStringTest() {
        String code = "HealthCardID{" + "personal code='" + personalID + '\'' + '}';
        String code2 = "HealthCardID{" + "personal code='" + personalID2 + '\'' + '}';
        assertEquals(hCardID.toString(), code);
        assertNotEquals(hCardID.toString(), code2);
    }
}
