package medicalconsultation.MedicalPrescriptionTests;

import exceptions.NullObjectException;
import exceptions.WrongIdCodeFormat;
import org.junit.jupiter.api.Test;

public interface MedicalConsultationMethodsTest {

    //Getters and setters are in separeted methods on each test class

    @Test
    void equalsTest() throws NullObjectException, WrongIdCodeFormat;

    @Test
    void notEqualsTest() throws NullObjectException, WrongIdCodeFormat;
}
