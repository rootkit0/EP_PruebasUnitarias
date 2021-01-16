package medicalconsultation.MedicalPrescriptionTests;

import data.ProductID;
import exceptions.NullObjectException;
import exceptions.WrongIdCodeFormat;
import medicalconsultation.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class MedicalPrescriptionLineTest implements MedicalConsultationMethodsTest {

    private MedicalPrescriptionLine medicalPrescriptionLine;
    private ProductID prodID;
    private TakingGuideline tg;
    private Posology p;

    @BeforeEach
    public void init() throws Exception {
        this.prodID = new ProductID("012345678901");
        this.p = new Posology(10.15f, 20.30f, FqUnit.DAY);
        this.tg = new TakingGuideline(DayMoment.AFTERLUNCH, 3.14f, "instructions", p);
        this.medicalPrescriptionLine = new MedicalPrescriptionLine(prodID, tg);
    }

    @Test
    public void getProductIDTest() throws Exception {
        ProductID expected = new ProductID("012345678901");
        assertEquals(medicalPrescriptionLine.getProductID(), expected);
    }

    @Test
    public void getTakingGuidelineTest() {
        TakingGuideline expected = new TakingGuideline(DayMoment.AFTERLUNCH, 3.14f, "instructions", p);
        assertEquals(medicalPrescriptionLine.getTakingGuideline(), expected);
    }

    @Test
    public void setProductIDTest() throws Exception {
        ProductID prodID = new ProductID("000000000000");
        medicalPrescriptionLine.setProductID(prodID);
        assertEquals(medicalPrescriptionLine.getProductID(), prodID);
    }

    @Test
    public void setTakingGuidelineTest() {
        Posology p2 = new Posology(30.15f, 40.30f, FqUnit.WEEK);
        TakingGuideline tg = new TakingGuideline(DayMoment.DURINGDINNER, 2.71f, "new_instructions", p2);
        medicalPrescriptionLine.setTakingGuideline(tg);
        assertEquals(medicalPrescriptionLine.getTakingGuideline(), tg);
    }

    @Override
    @Test
    public void equalsTest() throws NullObjectException, WrongIdCodeFormat {
        ProductID prodID2 = new ProductID("012345678901");
        TakingGuideline tg2 = new TakingGuideline(DayMoment.AFTERLUNCH, 3.14f, "instructions", p);
        MedicalPrescriptionLine mpl2 = new MedicalPrescriptionLine(prodID2, tg2);
        assertEquals(medicalPrescriptionLine, mpl2);
    }

    @Override
    @Test
    public void notEqualsTest() throws NullObjectException, WrongIdCodeFormat {
        //Not equal productID
        ProductID prodID2 = new ProductID("000000000000");
        TakingGuideline tg2 = new TakingGuideline(DayMoment.AFTERLUNCH, 3.14f, "instructions", p);
        MedicalPrescriptionLine mpl2 = new MedicalPrescriptionLine(prodID2, tg2);
        assertNotEquals(medicalPrescriptionLine, mpl2);
        //Not equal takingGuideline
        prodID2 = new ProductID("012345678901");
        tg2 = new TakingGuideline(DayMoment.DURINGDINNER, 2.71f, "new_instructions", p);
        mpl2 = new MedicalPrescriptionLine(prodID2, tg2);
        assertNotEquals(medicalPrescriptionLine, mpl2);
    }
}
