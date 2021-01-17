package consultationterminal;

import data.DigitalSignature;
import data.HealthCardID;
import data.ProductID;
import exceptions.*;
import medicalconsultation.*;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import services.HealthNationalService;
import services.ScheduledVisitAgenda;

import java.math.BigDecimal;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

public class ConsultationTerminalTest {
    static ConsultationTerminal ct;
    static HealthNationalServiceDoble hns;
    static HealthNationalServiceDobleNull hnsNull;
    static ScheduledVisitAgendaDoble sva;
    static ScheduledVisitAgendaDobleNull svaNull;
    static HealthCardID hcID;
    static DigitalSignature dSign;
    static MedicalPrescription medPresc;

    @BeforeAll
    static void initContent() throws Exception {
        ct = new ConsultationTerminal(new DigitalSignature("Xavier".getBytes()));
        hns = new HealthNationalServiceDoble();
        hnsNull = new HealthNationalServiceDobleNull();
        sva = new ScheduledVisitAgendaDoble();
        svaNull = new ScheduledVisitAgendaDobleNull();
        hcID = new HealthCardID("BBBBBBBBQR648597807024000012");
        dSign = new DigitalSignature("Xavier".getBytes());

        //Create medicalprescriptionlines
        TakingGuideline tg1 = new TakingGuideline(DayMoment.AFTERBREAKFAST, 10, "instructions1", 20, 30, FqUnit.HOUR);
        MedicalPrescriptionLine mpl1 = new MedicalPrescriptionLine(new ProductID("012345678901"), tg1);
        TakingGuideline tg2 = new TakingGuideline(DayMoment.AFTERLUNCH, 40, "instructions2", 50, 60, FqUnit.DAY);
        MedicalPrescriptionLine mpl2 = new MedicalPrescriptionLine(new ProductID("123456789012"), tg2);
        //Create arraylist of presc lines and add them
        ArrayList<MedicalPrescriptionLine> prescLines = new ArrayList<>();
        prescLines.add(mpl1);
        prescLines.add(mpl2);
        //Create medical prescription
        medPresc = new MedicalPrescription(100, new Date(), new Date(2021-1900, Calendar.DECEMBER, 25), hcID, dSign, prescLines);
    }

    @BeforeEach
    void initTest() throws Exception {
        ct = new ConsultationTerminal(new DigitalSignature("Xavier".getBytes()));
        ct.setHNS(hns);
        ct.setSVA(sva);
    }

    @Test
    void initRevisionTest() throws Exception {
        ct.initRevision();
        assertEquals(hcID, ct.getHcID());
        assertEquals(medPresc, ct.getePrescription());
        //Test HNS
        ct.setHNS(hnsNull);
        assertThrows(NotValidePrescriptionException.class, () -> {
            ct.initRevision();
        });
        initTest();
        //Test SVA
        ct.setSVA(svaNull);
        assertThrows(HealthCardException.class, () -> {
            ct.initRevision();
        });
    }

    @Test
    void initPrescriptionEditionTest() throws Exception {

    }

    @Test
    void searchForProductsTest() throws Exception {

    }

    @Test
    void selectProductTest() throws Exception {

    }

    @Test
    void enterMedicineGuidelinesTest() throws Exception {

    }

    @Test
    void enterTreatmentEndingDateTest() throws Exception {

    }

    @Test
    void sendePrescription() throws Exception {

    }

    private static class HealthNationalServiceDoble implements HealthNationalService {
        ProductID item1;
        ProductID item2;
        ProductID item3;

        ProductSpecification spec1;
        ProductSpecification spec2;
        ProductSpecification spec3;

        @Override
        public MedicalPrescription getePrescription(HealthCardID hcID) throws HealthCardException, NotValidePrescriptionException, ConnectException {
            return medPresc;
        }

        @Override
        public List<ProductSpecification> getProductsByKW(String keyWord) throws AnyKeyWordMedicineException, ConnectException {
            try {
                item1 = new ProductID("123456789012");
                item2 = new ProductID("234567890123");
                item3 = new ProductID("345678901234");
            } catch (NullObjectException | WrongIdCodeFormat e) {
                e.printStackTrace();
            }

            spec1 = new ProductSpecification(item1, "AntiInflamatorio 500gr", new BigDecimal("1.5"));
            spec2 = new ProductSpecification(item2, "Analgesico 1gr", new BigDecimal("3"));
            spec3 = new ProductSpecification(item3, "Antipiretico 1.5gr", new BigDecimal("4.5"));

            return new ArrayList<>(Arrays.asList(spec1, spec2, spec3));
        }

        @Override
        public ProductSpecification getProductSpecific(int opt) throws AnyMedicineSearchException, ConnectException {
            return spec1;
        }

        @Override
        public MedicalPrescription sendePrescription(MedicalPrescription ePresc) throws ConnectException, NotValidePrescription, eSignatureException, NotCompletedMedicalPrescription {
            return ePresc;
        }
    }

    private static class HealthNationalServiceDobleNull implements HealthNationalService {
        @Override
        public MedicalPrescription getePrescription(HealthCardID hcID) throws HealthCardException, NotValidePrescriptionException, ConnectException {
            return null;
        }

        @Override
        public List<ProductSpecification> getProductsByKW(String keyWord) throws AnyKeyWordMedicineException, ConnectException {
            return null;
        }

        @Override
        public ProductSpecification getProductSpecific(int opt) throws AnyMedicineSearchException, ConnectException {
            return null;
        }

        @Override
        public MedicalPrescription sendePrescription(MedicalPrescription ePresc) throws ConnectException, NotValidePrescription, eSignatureException, NotCompletedMedicalPrescription {
            return null;
        }
    }

    private static class ScheduledVisitAgendaDoble implements ScheduledVisitAgenda {
        @Override
        public HealthCardID getHealthCardID() throws HealthCardException {
            try {
                return new HealthCardID("BBBBBBBBQR648597807024000012");
            } catch (NullObjectException | WrongIdCodeFormat e) {
                e.printStackTrace();
            }
            return null;
        }
    }

    private static class ScheduledVisitAgendaDobleNull implements ScheduledVisitAgenda {
        @Override
        public HealthCardID getHealthCardID() throws HealthCardException {
            return null;
        }
    }
}