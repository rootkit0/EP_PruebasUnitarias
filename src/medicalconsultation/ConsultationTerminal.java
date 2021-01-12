package medicalconsultation;

import data.DigitalSignature;
import data.HealthCardID;
import exceptions.*;
import services.HealthNationalService;
import services.ScheduledVisitAgenda;

import java.util.Date;
import java.util.List;

public class ConsultationTerminal {
    private HealthNationalService HNS;
    private ScheduledVisitAgenda SVA;
    private HealthCardID hcID;
    private MedicalPrescription ePrescription;
    private List<ProductSpecification> products;
    private ProductSpecification product;
    private DigitalSignature dSign;

    public ConsultationTerminal(HealthNationalService HNS, ScheduledVisitAgenda SVA) {
        this.HNS = HNS;
        this.SVA = SVA;
    }

    public void initRevision() throws HealthCardException, NotValidePrescriptionException, ConnectException {
        //Recuperar identificacion paciente
        this.hcID = SVA.getHealthCardID();
        if(this.hcID == null) throw new HealthCardException();
        //Descargar eReceta
        this.ePrescription = HNS.getePrescription(this.hcID);
        if(this.ePrescription == null) throw new NotValidePrescriptionException();
    }

    public void initPrescriptionEdition() throws AnyCurrentPrescriptionException, NotFinishedTreatmentException {
        if(ePrescription.getEndDate().after(new Date())) throw new NotFinishedTreatmentException();
        if(ePrescription.getEndDate().before(new Date())) throw new AnyCurrentPrescriptionException();
    }

    public void searchForProducts(String keyWord) throws AnyKeyWordMedicineException, ConnectException {
        products = HNS.getProductsByKW(keyWord);
        if(products == null) throw new AnyKeyWordMedicineException();
        for(int i=0; i<products.size(); ++i) {
            System.out.println("Product " + i +
                    ":\tCode: " + products.get(i).getUpcCode());
        }
    }

    public void selectProduct(int option) throws AnyMedicineSearchException, ConnectException {
        this.product = products.get(option);
        if(product == null) throw new AnyMedicineSearchException();
        System.out.println("Chosen option: " + option + "\n" +
                ":\tCode: " + product.getUpcCode() +
                "\t Description: " + product.getDescription() +
                "\t Price: " + product.getPrice() + "\n");
    }

    public void enterMedicineGuidelines(String[] instruc) throws AnySelectedMedicineException, IncorrectTakingGuidelinesException {
        ePrescription.addLine(product.getUpcCode(), instruc);
    }

    public void enterTreatmentEndingDate(Date date) throws IncorrectEndingDateException {
        ePrescription.setEndDate(date);
        ePrescription.setPrescDate(new Date());
    }

    public void sendePrescription() throws ConnectException, NotValidePrescription, eSignatureException, NotCompletedMedicalPrescription {
        ePrescription.seteSign(dSign);
        this.HNS.sendePrescription(ePrescription);
    }

    public void printePresc() throws PrintingException {
        try {
            throw new PrintingException();
        }
        catch(PrintingException pe) {
            pe.printStackTrace();
        }
    }
}
