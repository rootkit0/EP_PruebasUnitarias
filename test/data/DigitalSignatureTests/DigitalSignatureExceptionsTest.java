package data.DigitalSignatureTests;

import data.DigitalSignature;
import exceptions.NullObjectException;
import exceptions.WrongIdCodeFormat;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class DigitalSignatureExceptionsTest {

    @Test
    public void nullConstructorParameterTest() {
        assertThrows(NullObjectException.class, () -> new DigitalSignature(null));
    }

    @Test
    public void formatParameterTest() {
        String name = "Xavier";
        byte[] bArray = name.getBytes();
        assertDoesNotThrow(() -> new DigitalSignature(bArray));
    }

    @Test
    public void nullEqualsParameterTest() throws NullObjectException, WrongIdCodeFormat {
        String name = "Xavier";
        byte[] bArray = name.getBytes();
        assertNotEquals(null, new DigitalSignature(bArray));
    }
}
