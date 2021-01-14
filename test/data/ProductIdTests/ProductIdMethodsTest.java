package data.ProductIdTests;

import data.ProductID;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class ProductIdMethodsTest {

    private ProductID prodID;
    private final String upcCode = "012345678901";
    private final String upcCode2 = "000000000000";

    @BeforeEach
    public void init() throws Exception {
        prodID = new ProductID(upcCode);
    }

    @Test
    public void getProductIDTest() {
        assertEquals(prodID.getUpcCode(), upcCode);
        assertNotEquals(prodID.getUpcCode(), upcCode2);
    }

    @Test
    public void equalsTest() throws Exception {
        ProductID prodID2 = new ProductID(upcCode);
        assertEquals(prodID, prodID2);
    }

    @Test
    public void notEqualsTest() throws Exception {
        ProductID prodID2 = new ProductID(upcCode2);
        assertNotEquals(prodID, prodID2);
        assertNotEquals(prodID, null);
        assertNotEquals(prodID2, null);
    }

    @Test
    public void hashCodeTest() throws Exception {
        ProductID prodID2 = new ProductID(upcCode);
        ProductID prodID3 = new ProductID(upcCode2);
        assertEquals(prodID.hashCode(), prodID2.hashCode());
        assertNotEquals(prodID2.hashCode(), prodID3.hashCode());
    }

    @Test
    public void toStringTest() throws Exception {
        String code = "ProductID{" + "product code='" + upcCode + '\'' + '}';
        String code2 = "ProductID{" + "product code='" + upcCode2 + '\'' + '}';
        assertEquals(prodID.toString(), code);
        assertNotEquals(prodID.toString(), code2);
    }
}
