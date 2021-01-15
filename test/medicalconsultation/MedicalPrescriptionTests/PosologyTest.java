package medicalconsultation.MedicalPrescriptionTests;

import medicalconsultation.FqUnit;
import medicalconsultation.Posology;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class PosologyTest implements MedicalConsultationMethodsTest {

    private Posology posology;

    public void init() {
        posology = new Posology(3.14f, 2.16f, FqUnit.HOUR);
    }

    @Test
    public void getDoseTest() {
        float expected = 3.14f;
        float obtained = this.posology.getDose();
        float not_expected = 2.16f;
        assertEquals(expected, obtained);
        assertNotEquals(not_expected, obtained);
    }

    @Test
    public void getFreqTest() {
        float expected = 2.16f;
        float obtained = this.posology.getFreq();
        float not_expected = 3.14f;
        assertEquals(expected, obtained);
        assertNotEquals(not_expected, obtained);
    }

    @Test
    public void getFreqUnitTest() {
        FqUnit expected = FqUnit.HOUR;
        FqUnit obtained = this.posology.getFreqUnit();
        FqUnit not_expected = FqUnit.DAY;
        assertEquals(expected, obtained);
        assertNotEquals(not_expected, obtained);
    }

    @Override
    @Test
    public void equalsTest() {
        Posology posology2 = new Posology(3.14f, 2.16f, FqUnit.HOUR);
        assertEquals(posology, posology2);
    }

    @Override
    @Test
    public void notEqualsTest() throws Exception {
        //Not equal dose
        Posology posology2 = new Posology(4.13f, 2.16f, FqUnit.HOUR);
        assertNotEquals(posology, posology2);
        //Not equal freq
        posology2 = new Posology(3.14f, 6.12F, FqUnit.HOUR);
        assertNotEquals(posology, posology2);
        //Not equal frequnit
        posology2 = new Posology(3.14f, 2.16f, FqUnit.DAY);
        assertNotEquals(posology, posology2);
    }

    @Override
    public void gettersTest() {

    }

    @Override
    public void settersTest() {

    }
}
