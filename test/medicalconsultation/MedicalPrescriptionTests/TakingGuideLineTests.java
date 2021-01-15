package medicalconsultation.MedicalPrescriptionTests;

import medicalconsultation.DayMoment;
import medicalconsultation.FqUnit;
import medicalconsultation.Posology;
import medicalconsultation.TakingGuideline;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class TakingGuideLineTests implements MedicalConsultationMethodsTest {

    private TakingGuideline takingGuideline;

    @BeforeEach
    public void init() {
        takingGuideline = new TakingGuideline(DayMoment.AFTERLUNCH, 3.14f, "instructions", 2.16f, 1.82f, FqUnit.HOUR);
    }

    @Test
    public void getDayMomentTest() {
        DayMoment expected = DayMoment.AFTERLUNCH;
        DayMoment obtained = takingGuideline.getDayMoment();
        DayMoment not_expected = DayMoment.DURINGDINNER;
        assertEquals(expected, obtained);
        assertNotEquals(not_expected, obtained);
    }

    @Test
    public void getDurationTest() {
        float expected = 3.14f;
        float obtained = takingGuideline.getDuration();
        float not_expected = 4.13f;
        assertEquals(expected, obtained);
        assertNotEquals(not_expected, obtained);
    }

    @Test
    public void getInstructionsTest() {
        String expected = "instructions";
        String obtained = takingGuideline.getInstructions();
        String not_expected = "fake_instructions";
        assertEquals(expected, obtained);
        assertNotEquals(not_expected, obtained);
    }

    @Test
    public void getPosologyTest() {
        Posology expected = new Posology(2.16f, 1.82f, FqUnit.HOUR);
        Posology obtained = takingGuideline.getPosology();
        Posology not_expected = new Posology(6.12f, 2.81f, FqUnit.DAY);
        assertEquals(expected, obtained);
        assertNotEquals(not_expected, obtained);
    }

    @Override
    public void equalsTest() {
        TakingGuideline takingGuideline2 = new TakingGuideline(DayMoment.AFTERLUNCH, 3.14f, "instructions", 2.16f, 1.82f, FqUnit.HOUR);
        assertEquals(takingGuideline, takingGuideline2);
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
