package data.DigitalSignatureTests;

import data.DigitalSignature;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class DigitalSignatureMethodsTest {

    private DigitalSignature dSign;
    private final byte[] signature = "Xavier".getBytes();
    private final byte[] signature2 = "Montse".getBytes();

    @BeforeEach
    public void init() throws Exception {
        dSign = new DigitalSignature(signature);
    }

    @Test
    public void getDoctorSignatureTest() {
        assertEquals(dSign.getDoctorSignature(), signature);
        assertNotEquals(dSign.getDoctorSignature(), signature2);
    }

    @Test
    public void equalsTest() throws Exception {
        DigitalSignature dSign2 = new DigitalSignature(signature);
        assertEquals(dSign, dSign2);
    }

    @Test
    public void notEqualsTest() throws Exception {
        DigitalSignature dSign2 = new DigitalSignature(signature2);
        assertNotEquals(dSign, dSign2);
        assertNotEquals(dSign, null);
        assertNotEquals(dSign2, null);
    }

    @Test
    public void hashCodeTest() throws Exception {
        DigitalSignature dSign2 = new DigitalSignature(signature);
        DigitalSignature dSign3 = new DigitalSignature(signature2);
        assertEquals(dSign.hashCode(), dSign2.hashCode());
        assertNotEquals(dSign2.hashCode(), dSign3.hashCode());
    }

    @Test
    public void toStringTest() {
        String code = "DigitalSignature{" + "doctor signature='" + signature + '\'' + '}';
        String code2 = "DigitalSignature{" + "doctor signature='" + signature2 + '\'' + '}';
        assertEquals(dSign.toString(), code);
        assertNotEquals(dSign.toString(), code2);
    }
}
