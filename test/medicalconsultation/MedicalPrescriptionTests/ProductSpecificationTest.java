package medicalconsultation.MedicalPrescriptionTests;

import data.ProductID;
import exceptions.NullObjectException;
import exceptions.WrongIdCodeFormat;
import medicalconsultation.ProductSpecification;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class ProductSpecificationTest implements MedicalConsultationMethodsTest {

    private ProductSpecification prodSpec;
    private ProductID prodID;
    private BigDecimal bd;

    @BeforeEach
    public void init() throws Exception {
        this.prodID = new ProductID("012345678901");
        this.bd = new BigDecimal("0.5");
        this.prodSpec = new ProductSpecification(prodID, "Ibuprofeno 600mg, comprimidos recubiertos", bd);
    }

    @Test
    public void getUpcCodeTest() throws Exception {
        ProductID expected = new ProductID("012345678901");
        assertEquals(prodSpec.getUpcCode(), expected);
    }

    @Test
    public void getDescriptionTest() {
        String expected = "Ibuprofeno 600mg, comprimidos recubiertos";
        assertEquals(prodSpec.getDescription(), expected);
    }

    @Test
    public void getPriceTest() {
        BigDecimal expected = new BigDecimal("0.5");
        assertEquals(prodSpec.getPrice(), expected);
    }

    @Test
    public void setUpcCodeTest() throws Exception {
        ProductID prodID2 = new ProductID("000000000000");
        prodSpec.setUpcCode(prodID2);
        assertEquals(prodSpec.getUpcCode(), prodID2);
    }

    @Test
    public void setDescriptionTest() {
        String desc = "Trankimazin 2mg, comprimidos";
        prodSpec.setDescription(desc);
        assertEquals(prodSpec.getDescription(), desc);
    }

    @Test
    public void setPriceTest() {
        BigDecimal bd2 = new BigDecimal("5.5");
        prodSpec.setPrice(bd2);
        assertEquals(prodSpec.getPrice(), bd2);
    }

    @Override
    @Test
    public void equalsTest() {
        ProductSpecification prodSpec2 = new ProductSpecification(prodID, "Ibuprofeno 600mg, comprimidos recubiertos", bd);
        assertEquals(prodSpec, prodSpec2);
    }

    @Override
    @Test
    public void notEqualsTest() throws NullObjectException, WrongIdCodeFormat {
        ProductID prodID2 = new ProductID("000000000000");
        //Not equal ProductID
        ProductSpecification prodSpec2 = new ProductSpecification(prodID2, "Ibuprofeno 600mg, comprimidos recubiertos", bd);
        assertNotEquals(prodSpec, prodSpec2);
        //Not equal Description
        prodSpec2 = new ProductSpecification(prodID, "Trankimazin 2mg, comprimidos", bd);
        assertNotEquals(prodSpec, prodSpec2);
        //Not equal BigDecimal
        prodSpec2 = new ProductSpecification(prodID, "Ibuprofeno 600mg, comprimidos recubiertos", new BigDecimal("5.5"));
        assertNotEquals(prodSpec, prodSpec2);
    }
}
