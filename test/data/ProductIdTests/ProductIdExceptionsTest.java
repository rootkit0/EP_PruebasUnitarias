package data.ProductIdTests;

import data.ProductID;
import exceptions.NullObjectException;
import exceptions.WrongIdCodeFormat;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ProductIdExceptionsTest {

    @Test
    public void nullConstructorParameterTest() {
        assertThrows(NullObjectException.class, () -> new ProductID(null));
    }

    @Test
    public void wrongFormatParameterTest() {
        assertThrows(WrongIdCodeFormat.class, () -> new ProductID("0123456789AB"));
        assertThrows(WrongIdCodeFormat.class, () -> new ProductID("AB2345678901"));
        assertDoesNotThrow(() -> new ProductID("012345678901"));
    }

    @Test
    public void nullEqualsParameterTest() throws NullObjectException, WrongIdCodeFormat {
        assertNotEquals(null, new ProductID("012345678901"));
    }
}
