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
    private Posology p;

    @BeforeEach
    public void init() {
        this.p = new Posology(24.16f, 12.82f, FqUnit.HOUR);
        this.takingGuideline = new TakingGuideline(DayMoment.AFTERLUNCH, 3.14f, "instructions", p);
    }

    @Test
    public void getDayMomentTest() {
        DayMoment expected = DayMoment.AFTERLUNCH;
        assertEquals(takingGuideline.getDayMoment(), expected);
    }

    @Test
    public void getDurationTest() {
        float expected = 3.14f;
        assertEquals(takingGuideline.getDuration(), expected);
    }

    @Test
    public void getInstructionsTest() {
        String expected = "instructions";
        assertEquals(takingGuideline.getInstructions(), expected);
    }

    @Test
    public void getPosologyTest() {
        Posology expected = new Posology(24.16f, 12.82f, FqUnit.HOUR);
        assertEquals(takingGuideline.getPosology(), expected);
    }

    @Test
    public void setDayMomentTest() {
        DayMoment dm = DayMoment.DURINGDINNER;
        takingGuideline.setDayMoment(dm);
        assertEquals(takingGuideline.getDayMoment(), dm);
    }

    @Test
    public void setDurationTest() {
        Float d = 50.99f;
        takingGuideline.setDuration(d);
        assertEquals(takingGuideline.getDuration(), d);
    }

    @Test
    public void setInstructionsTest() {
        String ins = "setting_instructions";
        takingGuideline.setInstructions(ins);
        assertEquals(takingGuideline.getInstructions(), ins);
    }

    @Test
    public void setPosologyTest() {
        Posology pos = new Posology(20f, 40f, FqUnit.DAY);
        takingGuideline.setPosology(pos);
        assertEquals(takingGuideline.getPosology(), pos);
    }

    @Override
    @Test
    public void equalsTest() {
        TakingGuideline takingGuideline2 = new TakingGuideline(DayMoment.AFTERLUNCH, 3.14f, "instructions", p);
        assertEquals(takingGuideline, takingGuideline2);
    }

    @Override
    @Test
    public void notEqualsTest() {
        //Not equal dayMoment
        TakingGuideline takingGuideline2 = new TakingGuideline(DayMoment.DURINGDINNER, 3.14f, "instructions", p);
        assertNotEquals(takingGuideline, takingGuideline2);
        //Not equal duration
        takingGuideline2 = new TakingGuideline(DayMoment.AFTERLUNCH, 50.99f, "instructions", p);
        assertNotEquals(takingGuideline, takingGuideline2);
        //Not equal instructions
        takingGuideline2 = new TakingGuideline(DayMoment.AFTERLUNCH, 3.14f, "fake_instructions", p);
        assertNotEquals(takingGuideline, takingGuideline2);
        //Not equal posology
        takingGuideline2 = new TakingGuideline(DayMoment.AFTERLUNCH, 3.14f, "instructions", 20f, 40f, FqUnit.DAY);
        assertNotEquals(takingGuideline, takingGuideline2);
    }
}
