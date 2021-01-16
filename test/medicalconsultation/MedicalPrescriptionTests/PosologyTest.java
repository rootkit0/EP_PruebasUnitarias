package medicalconsultation.MedicalPrescriptionTests;

import medicalconsultation.FqUnit;
import medicalconsultation.Posology;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class PosologyTest implements MedicalConsultationMethodsTest {

    private Posology posology;

    @BeforeEach
    public void init() {
        this.posology = new Posology(23.3f, 18.7f, FqUnit.HOUR);
    }

    @Test
    public void getDoseTest() {
        float expected = 23.3f;
        assertEquals(posology.getDose(), expected);
    }

    @Test
    public void getFreqTest() {
        float expected = 18.7f;
        assertEquals(posology.getFreq(), expected);
    }

    @Test
    public void getFreqUnitTest() {
        FqUnit expected = FqUnit.HOUR;
        assertEquals(posology.getFreqUnit(), expected);
    }

    @Test
    public void setDoseTest() {
        float d = 46.6f;
        posology.setDose(d);
        assertEquals(posology.getDose(), d);
    }

    @Test
    public void setFreqTest() {
        float f = 37.4f;
        posology.setFreq(f);
        assertEquals(posology.getFreq(), f);
    }

    @Test
    public void setFqUnitTest() {
        FqUnit fu = FqUnit.DAY;
        posology.setFreqUnit(fu);
        assertEquals(posology.getFreqUnit(), fu);
    }

    @Override
    @Test
    public void equalsTest() {
        Posology posology2 = new Posology(23.3f, 18.7f, FqUnit.HOUR);
        assertEquals(posology, posology2);
    }

    @Override
    @Test
    public void notEqualsTest() {
        //Not equal dose
        Posology posology2 = new Posology(46.6f, 18.7f, FqUnit.HOUR);
        assertNotEquals(posology, posology2);
        //Not equal freq
        posology2 = new Posology(23.3f, 37.4F, FqUnit.HOUR);
        assertNotEquals(posology, posology2);
        //Not equal frequnit
        posology2 = new Posology(23.3f, 18.7f, FqUnit.DAY);
        assertNotEquals(posology, posology2);
    }
}
