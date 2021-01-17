package services;

import data.DigitalSignature;
import data.HealthCardID;
import data.ProductID;
import exceptions.*;
import medicalconsultation.MedicalPrescription;
import medicalconsultation.ProductSpecification;

import java.math.BigDecimal;
import java.util.*;

public class HealthNationalServiceSample implements HealthNationalService {
    private Map<HealthCardID, MedicalPrescription> prescDB = new HashMap<>();
    private Map<String, List<ProductSpecification>> prodDB = new HashMap<>();
    private List<ProductSpecification> prodSpecs;
    private HealthCardID healthCardID;
    private int prescCode;

    public void HealthNationalServiceSample(HealthCardID hcID) throws Exception {
        //Add elements to prescDB
        HealthCardID hcID1 = new HealthCardID("BBBBBBBBQR648597807024000012");
        HealthCardID hcID2 = new HealthCardID("BBBBBBBBQR648597807024000013");
        HealthCardID hcID3 = new HealthCardID("BBBBBBBBQR648597807024000014");
        HealthCardID hcID4 = new HealthCardID("BBBBBBBBQR648597807024000015");
        DigitalSignature dSign = new DigitalSignature("Xavier".getBytes());

        String[] instruc1 = new String[] {"AFTERBREAKFAST", "10", "instructions1", "20", "30", "HOUR"};
        ProductID prod1 = new ProductID("123456789012");
        String[] instruc2 = new String[] {"AFTERLUNCH", "40", "instructions2", "50", "60", "DAY"};
        ProductID prod2 = new ProductID("234567890123");
        String[] instruc3 = new String[] {"AFTERDINNER", "70", "instructions3", "80", "90", "WEEK"};
        ProductID prod3 = new ProductID("345678901234");

        MedicalPrescription medPresc1 = new MedicalPrescription(100, new Date(2020-1900, Calendar.JANUARY, 25), new Date(2021-1900, Calendar.FEBRUARY, 25), hcID1, dSign);
        MedicalPrescription medPresc2 = new MedicalPrescription(101, new Date(2020-1900, Calendar.FEBRUARY, 25), new Date(2021-1900, Calendar.MARCH, 25), hcID2, dSign);
        MedicalPrescription medPresc3 = new MedicalPrescription(102, new Date(2020-1900, Calendar.MARCH, 25), new Date(2021-1900, Calendar.MAY, 25), hcID3, dSign);

        medPresc1.addLine(prod1, instruc1);
        medPresc1.addLine(prod2, instruc2);
        medPresc2.addLine(prod1, instruc1);
        medPresc2.addLine(prod2, instruc2);
        medPresc3.addLine(prod1, instruc1);
        medPresc3.addLine(prod2, instruc2);
        medPresc3.addLine(prod3, instruc3);

        prescDB.put(hcID1, medPresc1);
        prescDB.put(hcID2, medPresc2);
        prescDB.put(hcID3, medPresc3);
        prescDB.put(hcID4, null);

        //Add elements to prodDB
        ProductID antiInflamatorio1 = new ProductID("000000000001");
        ProductID antiInflamatorio2 = new ProductID("000000000002");
        ProductID analgesico1 = new ProductID("000000000004");
        ProductID analgesico2 = new ProductID("000000000005");
        ProductID antipiretico1 = new ProductID("000000000006");
        ProductID antipiretico2 = new ProductID("000000000007");

        ProductSpecification infla1 = new ProductSpecification(antiInflamatorio1, "500mg", new BigDecimal("1.5"));
        ProductSpecification infla2 = new ProductSpecification(antiInflamatorio2, "1gr", new BigDecimal("2.5"));
        ProductSpecification anal1 = new ProductSpecification(analgesico1, "500mg", new BigDecimal("3.5"));
        ProductSpecification anal2 = new ProductSpecification(analgesico2, "1gr", new BigDecimal("4.5"));
        ProductSpecification pire1 = new ProductSpecification(antipiretico1, "500mg", new BigDecimal("5.5"));
        ProductSpecification pire2 = new ProductSpecification(antipiretico2, "1gr", new BigDecimal("6.5"));

        prodDB.put("Antiinflamatorio", new ArrayList<>(Arrays.asList(infla1, infla2)));
        prodDB.put("Analgesico", new ArrayList<>(Arrays.asList(anal1, anal2)));
        prodDB.put("Antipiretico", new ArrayList<>(Arrays.asList(pire1, pire2)));
    }

    @Override
    public MedicalPrescription getePrescription(HealthCardID hcID) throws HealthCardException, NotValidePrescriptionException, ConnectException {
        try {
            if (!prescDB.containsKey(hcID)) {
                healthCardID = null;
                throw new HealthCardException("");
            }
            if (prescDB.get(hcID) == null) {
                healthCardID = null;
                throw new NotValidePrescriptionException("");
            }
            healthCardID = hcID;
            return prescDB.get(hcID);
        } catch (HealthCardException e) {
            throw new HealthCardException("");
        } catch (NotValidePrescriptionException e) {
            throw new NotValidePrescriptionException("");
        } catch (Exception e) {
            healthCardID = null;
            throw new ConnectException("");
        }
    }

    @Override
    public List<ProductSpecification> getProductsByKW(String keyWord) throws AnyKeyWordMedicineException, ConnectException {
        try {
            if (!prodDB.containsKey(keyWord)) {
                prodSpecs = null;
                throw new AnyKeyWordMedicineException("");
            }
            prodSpecs = prodDB.get(keyWord);
            return prodDB.get(keyWord);
        } catch (AnyKeyWordMedicineException e) {
            throw new AnyKeyWordMedicineException("");
        } catch (Exception e) {
            prodSpecs = null;
            throw new ConnectException("");
        }
    }

    @Override
    public ProductSpecification getProductSpecific(int opt) throws AnyMedicineSearchException, ConnectException {
        try {
            if (prodSpecs == null || prodSpecs.isEmpty()) {
                throw new AnyMedicineSearchException("");
            }
            if (opt >= prodSpecs.size() || opt < 0) {
                throw new AnyMedicineSearchException("");
            }
            return prodSpecs.get(opt);
        } catch (AnyMedicineSearchException e){
            throw new AnyMedicineSearchException("");
        } catch (Exception e) {
            throw new ConnectException("");
        }
    }

    @Override
    public MedicalPrescription sendePrescription(MedicalPrescription ePresc) throws ConnectException, NotValidePrescription, eSignatureException, NotCompletedMedicalPrescription {
        try {
            if (ePresc == null || ePresc.getLines().isEmpty()) {
                throw new NotValidePrescription("");
            }
            if (ePresc.geteSign() == null) {
                throw new eSignatureException("");
            }
            if (ePresc.getPrescDate() == null || ePresc.getEndDate() == null) {
                throw new NotCompletedMedicalPrescription("");
            }
            prescCode++;
            ePresc.setPrescCode(prescCode);
            prescDB.put(healthCardID, ePresc);
            return prescDB.get(healthCardID);
        } catch (NotValidePrescription e) {
            throw new NotValidePrescription("");
        } catch (eSignatureException e) {
            throw new eSignatureException("");
        } catch (NotCompletedMedicalPrescription e) {
            throw new NotCompletedMedicalPrescription("");
        } catch (Exception e) {
            throw new ConnectException("");
        }
    }
}
