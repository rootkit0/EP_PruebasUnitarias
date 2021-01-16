package medicalconsultation.MedicalPrescriptionTests.MedicalPrescriptionTests;

import data.DigitalSignature;
import data.HealthCardID;
import data.ProductID;
import exceptions.IncorrectTakingGuidelinesException;
import exceptions.NullObjectException;
import exceptions.WrongIdCodeFormat;
import medicalconsultation.*;
import medicalconsultation.MedicalPrescriptionTests.MedicalConsultationMethodsTest;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class MedicalPrescriptionMethodsTest implements MedicalConsultationMethodsTest {

    static MedicalPrescription medPresc;
    static ArrayList<MedicalPrescriptionLine> prescLines;
    static MedicalPrescriptionLine mpl, mpl2, mpl3;
    static HealthCardID hcID;
    static DigitalSignature dSign;

    @BeforeAll
    public static void initContent() throws Exception {
        //Init hcCardID and doctorSignature
        hcID = new HealthCardID("BBBBBBBBQR648597807024000012");
        dSign = new DigitalSignature("Xavier".getBytes());
        //Initialize 3 different medPrescLines
        TakingGuideline tg = new TakingGuideline(DayMoment.AFTERBREAKFAST, 10f, "instructions1", 20f, 30f, FqUnit.HOUR);
        mpl = new MedicalPrescriptionLine(new ProductID("123456789012"), tg);
        tg = new TakingGuideline(DayMoment.AFTERLUNCH, 10.2f, "instructions2", 20.2f, 30.2f, FqUnit.DAY);
        mpl2 = new MedicalPrescriptionLine(new ProductID("234567890123"), tg);
        tg = new TakingGuideline(DayMoment.AFTERDINNER, 10.3f, "instructions3", 20.3f, 30.3f, FqUnit.WEEK);
        mpl3 = new MedicalPrescriptionLine(new ProductID("345678901234"), tg);
    }

    @BeforeEach
    public void initTest() {
        prescLines = new ArrayList<MedicalPrescriptionLine>();
        prescLines.add(mpl);
        prescLines.add(mpl2);
        medPresc = new MedicalPrescription(0, new Date(), new Date(2021-1900, 11, 30), hcID, dSign, prescLines);
    }

    @Test
    public void addLine() throws Exception {
        ProductID prodID = new ProductID("123456789012");
        String[] instruc = new String[] {"AFTERLUNCH", "10", "instructions1", "20", "30", "HOUR"};
        medPresc.addLine(prodID, instruc);
        assertEquals(mpl, medPresc.getPrescriptionLine(prodID));
        //Wrong length
        String[] wrongLength = new String[] {"AFTERLUNCH", "10", "abc", "20", "30"};
        assertThrows(IncorrectTakingGuidelinesException.class, () -> {
            medPresc.addLine(prodID, wrongLength);
        });
        //Empty values
        String[] emptyValues = new String[] {"AFTERLUNCH", "", "", "", "", "DAY"};
        assertThrows(IncorrectTakingGuidelinesException.class, () -> {
            medPresc.addLine(prodID, emptyValues);
        });
        //Wrong day moment
        String[] wrongDayMoment = new String[] {"TEST", "10", "abc", "20", "30", "DAY"};
        assertThrows(IncorrectTakingGuidelinesException.class, () -> {
            medPresc.addLine(prodID, wrongDayMoment);
        });
        //Wrong FqUnit
        String[] wrongFqUnit = new String[] {"AFTERLUNCH", "10", "abc", "20", "30", "TEST"};
        assertThrows(IncorrectTakingGuidelinesException.class, () -> {
            medPresc.addLine(prodID, wrongFqUnit);
        });
    }

    @Test
    public void modifyLine() throws Exception {
        ProductID prodID = new ProductID("234567890123");
        String[] fullEmpty = new String[] {"", "", "", "", "", ""};
        
    }

    @Test
    public void removeLine() {

    }

    @Override
    @Test
    public void equalsTest() throws NullObjectException, WrongIdCodeFormat {

    }

    @Override
    @Test
    public void notEqualsTest() throws NullObjectException, WrongIdCodeFormat {

    }
}
