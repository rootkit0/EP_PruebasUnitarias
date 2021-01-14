package data.HealthCardIdTests;
import data.HealthCardID;
import exceptions.NullObjectException;
import exceptions.WrongIdCodeFormat;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class HealthCardIdExceptionsTest {

    @Test
    public void nullConstructorParameterTest() {
        assertThrows(NullObjectException.class, () -> new HealthCardID(null));
    }

    @Test
    public void wrongFormatParameterTest() {
        assertThrows(WrongIdCodeFormat.class, () -> new HealthCardID("ABBBBBBBQR648597807024000012"));
        assertThrows(WrongIdCodeFormat.class, () -> new HealthCardID("BBBBBBBBQ1648597807024000012"));
        assertThrows(WrongIdCodeFormat.class, () -> new HealthCardID("BBBBBBBBQR64859A807024000012"));
        assertThrows(WrongIdCodeFormat.class, () -> new HealthCardID("BBBBBBBBQR64859780702400001A"));
        assertDoesNotThrow(() -> new HealthCardID("BBBBBBBBQR648597807024000012"));
    }

    @Test
    public void nullEqualsParameterTest() throws NullObjectException, WrongIdCodeFormat {
        assertNotEquals(null, new HealthCardID("BBBBBBBBQR648597807024000012"));
    }
}
