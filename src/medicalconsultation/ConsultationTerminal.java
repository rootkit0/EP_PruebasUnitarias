package medicalconsultation;

import data.DigitalSignature;
import data.HealthCardID;
import data.ProductID;
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
    private boolean searchCatalogue = false;

    public ConsultationTerminal(HealthNationalService HNS, ScheduledVisitAgenda SVA, DigitalSignature dSign) {
        this.HNS = HNS;
        this.SVA = SVA;
        this.dSign = dSign;
    }

    public void initRevision() throws HealthCardException, NotValidePrescriptionException, ConnectException {
        //Recuperar identificacion paciente
        this.hcID = SVA.getHealthCardID();
        if(this.hcID == null) throw new HealthCardException("Error obtaining HealthCardID");
        //Descargar eReceta
        this.ePrescription = HNS.getePrescription(this.hcID);
        if(this.ePrescription == null) throw new NotValidePrescriptionException("Error obtaining ePrescription");
    }

    public void initPrescriptionEdition() throws AnyCurrentPrescriptionException, NotFinishedTreatmentException {
        Date today = new Date();
        if(ePrescription.getEndDate().after(today)) throw new NotFinishedTreatmentException("Treatment still in progress");
        if(ePrescription.getPrescDate().before(today)) throw new AnyCurrentPrescriptionException("Treatment still not started");
    }

    public void searchForProducts(String keyWord) throws AnyKeyWordMedicineException, ConnectException {
        products = HNS.getProductsByKW(keyWord);
        if(products == null) throw new AnyKeyWordMedicineException("Couldn't find any product containing the keyword");
        searchCatalogue = true;
        //Print the products
        for(int i=0; i<products.size(); ++i) {
            System.out.println("Product " + i +
                    ":\tCode: " + products.get(i).getUpcCode());
        }
    }

    public void selectProduct(int option) throws AnyMedicineSearchException, ConnectException {
        if(!searchCatalogue) {
            throw new AnyMedicineSearchException("Have to lookup catalogue before select a product");
        }
        this.product = products.get(option);
        if(product == null) {
            throw new AnyMedicineSearchException("Selected product does not exist");
        }
        else {
            searchCatalogue = false;
        }
        System.out.println("Chosen option: " + option + "\n" +
                ":\tCode: " + product.getUpcCode() +
                "\t Description: " + product.getDescription() +
                "\t Price: " + product.getPrice() + "\n");
    }

    public void enterMedicineGuidelines(String[] instruc) throws AnySelectedMedicineException, IncorrectTakingGuidelinesException {
        ProductID prodID = product.getUpcCode();
        if(prodID == null) {
            throw new AnySelectedMedicineException("Obtained productID is null");
        }
        try {
            ePrescription.addLine(product.getUpcCode(), instruc);
        }
        catch(Exception e) {
            throw new IncorrectTakingGuidelinesException("Incomplete or wrong information on instruc");
        }
    }

    public void enterTreatmentEndingDate(Date date) throws IncorrectEndingDateException {
        if(date == null || date.before(new Date())) {
            throw new IncorrectEndingDateException("Null or expired date");
        }
        ePrescription.setEndDate(date);
        ePrescription.setPrescDate(new Date());
    }

    public void sendePrescription() throws ConnectException, NotValidePrescription, eSignatureException, NotCompletedMedicalPrescription {
        if(ePrescription == null) {
            throw new NotValidePrescription("Null object, expected an ePrescription");
        }
        if(dSign == null) {
            throw new eSignatureException("Null object, expected doctor signature");
        }
        ePrescription.seteSign(dSign);
        ePrescription = HNS.sendePrescription(ePrescription);
    }

    public void printePresc() throws PrintingException {
        if(ePrescription == null) {
            throw new PrintingException("Null object, expected an ePrescription");
        }
        System.out.println(ePrescription.toString());
    }
}
