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
        byte[] bArray = "Xavier".getBytes();
        assertDoesNotThrow(() -> new DigitalSignature(bArray));
    }

    @Test
    public void nullEqualsExceptionTest() throws Exception {
        byte[] bArray = "Xavier".getBytes();
        assertNotEquals(null, new DigitalSignature(bArray));
    }
}
