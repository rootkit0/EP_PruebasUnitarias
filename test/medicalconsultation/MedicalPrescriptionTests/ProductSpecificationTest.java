package medicalconsultation.MedicalPrescriptionTests;

import data.ProductID;
import medicalconsultation.ProductSpecification;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class ProductSpecificationTest implements MedicalConsultationMethodsTest {

    private ProductSpecification productSpecification;
    private ProductID prodID;

    @BeforeEach
    public void init() throws Exception {
        prodID = new ProductID("012345678901");
        productSpecification = new ProductSpecification(prodID, "Ibuprofeno 600mg, comprimidos recubiertos", new BigDecimal("0.5"));
    }

    @Test
    public void getUpcCodeTest() throws Exception {
        ProductID expected = new ProductID("012345678901");
        ProductID obtained = this.productSpecification.getUpcCode();
        ProductID not_expected = new ProductID("000000000000");
        assertEquals(expected, obtained);
        assertNotEquals(not_expected, obtained);
    }

    @Test
    public void getDescriptionTest() {
        String expected = "Ibuprofeno 600mg, comprimidos recubiertos";
        String obtained = this.productSpecification.getDescription();
        String not_expected = "Trankimazin 2mg, comprimidos";
        assertEquals(expected, obtained);
        assertNotEquals(not_expected, obtained);
    }

    @Test
    public void getPriceTest() {
        BigDecimal expected = new BigDecimal("0.5");
        BigDecimal obtained = productSpecification.getPrice();
        BigDecimal not_expected = new BigDecimal("0.2");
        assertEquals(expected, obtained);
        assertNotEquals(not_expected, obtained);
    }

    //Interface classes
    @Override
    public void equalsTest() {

    }

    @Override
    public void notEqualsTest() {

    }

    @Override
    public void gettersTest() {

    }

    @Override
    public void settersTest() {

    }
}
