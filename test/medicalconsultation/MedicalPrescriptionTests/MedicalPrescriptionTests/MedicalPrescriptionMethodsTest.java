package medicalconsultation.MedicalPrescriptionTests.MedicalPrescriptionTests;

import data.DigitalSignature;
import data.HealthCardID;
import data.ProductID;
import medicalconsultation.*;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class MedicalPrescriptionMethodsTest {

    private MedicalPrescription medPresc;
    private ArrayList<MedicalPrescriptionLine> prescLines;
    private MedicalPrescriptionLine mpl, mpl2, mpl3;
    private HealthCardID hcID;
    private DigitalSignature dSign;

    @BeforeAll
    public void initContent() throws Exception {
        //Init hcCardID and doctorSignature
        hcID = new HealthCardID("BBBBBBBBQR648597807024000012");
        dSign = new DigitalSignature("Xavier".getBytes());
        //Initialize 3 different medPrescLines
        TakingGuideline tg = new TakingGuideline(DayMoment.AFTERBREAKFAST, 10.1f, "instructions1", 20.1f, 30.1f, FqUnit.HOUR);
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

}
