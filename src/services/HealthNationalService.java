package services;

import medicalconsultation.MedicalPrescription;
import data.HealthCardID;
import exceptions.*;
import medicalconsultation.ProductSpecification;

import java.util.List;

public interface HealthNationalService {
    MedicalPrescription getePrescription(HealthCardID hcID)
            throws HealthCardException, NotValidePrescriptionException, ConnectException;
    List<ProductSpecification> getProductsByKW(String keyWord)
            throws AnyKeyWordMedicineException, ConnectException;
    ProductSpecification getProductSpecific(int opt)
            throws AnyMedicineSearchException, ConnectException;
    MedicalPrescription sendePrescription(MedicalPrescription ePresc)
            throws ConnectException, NotValidePrescription, eSignatureException, NotCompletedMedicalPrescription;
}
