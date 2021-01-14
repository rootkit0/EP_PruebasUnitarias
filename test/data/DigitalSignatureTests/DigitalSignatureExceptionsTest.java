package data.DigitalSignatureTests;

import data.DigitalSignature;
import exceptions.NullObjectException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class DigitalSignatureExceptionsTest {

    @Test
    public void nullConstructorExceptionTest() {
        assertThrows(NullObjectException.class, () -> new DigitalSignature(null));
    }

    @Test
    public void formatErrorExceptionTest() {
        String name = "Xavier";
        byte[] bArray = name.getBytes();
        assertDoesNotThrow(() -> new DigitalSignature(bArray));
    }

    @Test
    public void nullEqualsExceptionTest() throws Exception {
        String name = "Xavier";
        byte[] bArray = name.getBytes();
        assertNotEquals(null, new DigitalSignature(bArray));
    }
}
