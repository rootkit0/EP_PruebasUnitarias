package medicalconsultation.MedicalPrescriptionTests;

import data.ProductID;
import medicalconsultation.DayMoment;
import medicalconsultation.FqUnit;
import medicalconsultation.MedicalPrescriptionLine;
import medicalconsultation.TakingGuideline;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class MedicalPrescriptionLineTest implements MedicalConsultationMethodsTest {

    private MedicalPrescriptionLine medicalPrescriptionLine;

    @BeforeEach
    public void init() throws Exception {
        medicalPrescriptionLine = new MedicalPrescriptionLine(new ProductID("012345678901"),
                new TakingGuideline(DayMoment.AFTERLUNCH, 3.14f, "instructions", 2.16f, 1.82f, FqUnit.HOUR));
    }

    @Test
    public void getProductIDTest() throws Exception {
        ProductID expected = new ProductID("012345678901");
        ProductID obtained = this.medicalPrescriptionLine.getProductID();
        ProductID not_expected = new ProductID("00000000000");
        assertEquals(expected, obtained);
        assertNotEquals(not_expected, obtained);
    }

    @Test
    public void getTakingGuidelineTest() throws Exception {
        TakingGuideline expected = new TakingGuideline(DayMoment.AFTERLUNCH, 3.14f, "instructions", 2.16f, 1.82f, FqUnit.HOUR);
        TakingGuideline obtained = this.medicalPrescriptionLine.getTakingGuideline();
        TakingGuideline not_expected = new TakingGuideline(DayMoment.DURINGDINNER, 3.14f, "fake_instructions", 2.16f, 1.82f, FqUnit.DAY);
        assertEquals(expected, obtained);
        assertNotEquals(not_expected, obtained);
    }

    @Override
    @Test
    public void equalsTest() throws Exception {
        MedicalPrescriptionLine medicalPrescriptionLine2 = new MedicalPrescriptionLine(new ProductID("012345678901"),
                new TakingGuideline(DayMoment.AFTERLUNCH, 3.14f, "instructions", 2.16f, 1.82f, FqUnit.HOUR));
        assertEquals(medicalPrescriptionLine, medicalPrescriptionLine2);
    }

    @Override
    public void notEqualsTest() throws Exception {

    }

    @Override
    public void gettersTest() {

    }

    @Override
    public void settersTest() {

    }
}
