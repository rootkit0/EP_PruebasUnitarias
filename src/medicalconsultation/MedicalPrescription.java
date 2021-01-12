package medicalconsultation;

import data.DigitalSignature;
import data.HealthCardID;
import data.ProductID;
import exceptions.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;

public class MedicalPrescription {
    private int prescCode;
    private Date prescDate;
    private Date endDate;
    private HealthCardID hcID;
    private DigitalSignature eSign;
    private ArrayList<MedicalPrescriptionLine> prescLines;

    public MedicalPrescription (int prescCode, Date prescDate, Date endDate, HealthCardID hcID, DigitalSignature eSign, ArrayList<MedicalPrescriptionLine> prescLines) {
        this.prescCode = prescCode;
        this.prescDate = prescDate;
        this.endDate = endDate;
        this.hcID = hcID;
        this.eSign = eSign;
        this.prescLines = prescLines;
    }

    public MedicalPrescription (int prescCode, Date prescDate, Date endDate, HealthCardID hcID, DigitalSignature eSign) {
        this.prescCode = prescCode;
        this.prescDate = prescDate;
        this.endDate = endDate;
        this.hcID = hcID;
        this.eSign = eSign;
        this.prescLines = new ArrayList<MedicalPrescriptionLine>();
    }

    public void addLine(ProductID prodID, String[] instruc) throws IncorrectTakingGuidelinesException {
        TakingGuideline tg = new TakingGuideline(
                DayMoment.getDayMoment(instruc[0]),
                Float.parseFloat(instruc[1]),
                instruc[2],
                Float.parseFloat(instruc[3]),
                Float.parseFloat(instruc[4]),
                FqUnit.getFqUnit(instruc[5]));
        prescLines.add(new MedicalPrescriptionLine(prodID, tg));
    }

    public void modifyLine(ProductID prodID, String[] instruc) throws ProductNotInPrescription, IncorrectTakingGuidelinesException {
        MedicalPrescriptionLine prescLine = getPrescriptionLine(prodID);
        TakingGuideline tg = prescLine.getTakingGuideline();
        if(!instruc[0].isEmpty()) {
            tg.setDayMoment(DayMoment.getDayMoment(instruc[0]));
        }
        if(!instruc[1].isEmpty()) {
            tg.setDuration(Float.parseFloat(instruc[1]));
        }
        if(!instruc[2].isEmpty()) {
            tg.setInstructions(instruc[2]);
        }
        if(!instruc[3].isEmpty()) {
            tg.getPosology().setDose(Float.parseFloat(instruc[3]));
        }
        if(!instruc[4].isEmpty()) {
            tg.getPosology().setFreq(Float.parseFloat(instruc[4]));
        }
        if(!instruc[5].isEmpty()) {
            tg.getPosology().setFreqUnit(FqUnit.getFqUnit(instruc[5]));
        }
        prescLine.setTakingGuideline(tg);
    }

    public void removeLine(ProductID prodID) throws ProductNotInPrescription {
        MedicalPrescriptionLine prescLine = getPrescriptionLine(prodID);
        prescLines.remove(prescLine);
    }

    public MedicalPrescriptionLine getPrescriptionLine(ProductID prodID) {
        Iterator<MedicalPrescriptionLine> iter = prescLines.iterator();
        while(iter.hasNext()) {
            MedicalPrescriptionLine tmp = iter.next();
            if(tmp.getProductID().equals(prodID)) {
                return tmp;
            }
        }
        return null;
    }

    public int getPrescCode() {
        return prescCode;
    }

    public Date getPrescDate() {
        return prescDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public HealthCardID getHcID() {
        return hcID;
    }

    public DigitalSignature geteSign() {
        return eSign;
    }

    public ArrayList<MedicalPrescriptionLine> getPrescLines() {
        return prescLines;
    }

    public void setPrescCode(int prescCode) {
        this.prescCode = prescCode;
    }

    public void setPrescDate(Date prescDate) {
        this.prescDate = prescDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public void setHcID(HealthCardID hcID) {
        this.hcID = hcID;
    }

    public void seteSign(DigitalSignature eSign) {
        this.eSign = eSign;
    }

    public void setPrescLines(ArrayList<MedicalPrescriptionLine> prescLines) {
        this.prescLines = prescLines;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MedicalPrescription mp = (MedicalPrescription) o;
        return this.prescCode == mp.prescCode && this.prescDate.equals(mp.prescDate) &&
                this.endDate.equals(mp.endDate) && this.hcID.equals(mp.hcID) &&
                this.eSign.equals(mp.eSign) && this.prescLines.equals(mp.prescLines);
    }
}
