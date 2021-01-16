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

    //Constructor to initialize prescLines
    public MedicalPrescription (int prescCode, Date prescDate, Date endDate, HealthCardID hcID, DigitalSignature eSign) {
        this.prescCode = prescCode;
        this.prescDate = prescDate;
        this.endDate = endDate;
        this.hcID = hcID;
        this.eSign = eSign;
        this.prescLines = new ArrayList<MedicalPrescriptionLine>();
    }

    public void addLine(ProductID prodID, String[] instruc) throws IncorrectTakingGuidelinesException {
        //Check that information is complete
        if(instruc.length != 6 || instruc[0].isEmpty() || instruc[1].isEmpty() || instruc[2].isEmpty() ||
            instruc[3].isEmpty() || instruc[4].isEmpty() || instruc[5].isEmpty()) {
            throw new IncorrectTakingGuidelinesException("Incomplete information or incorrect format of the instruc");
        }
        //Check that information is correct
        if(DayMoment.getDayMoment(instruc[0]) == null) {
            throw new IncorrectTakingGuidelinesException("Incorrect DayMoment value");
        }
        if(FqUnit.getFqUnit(instruc[5]) == null) {
            throw new IncorrectTakingGuidelinesException("Incorrect FqUnit value");
        }
        try {
            TakingGuideline tg = new TakingGuideline(
                    DayMoment.getDayMoment(instruc[0]),
                    Float.parseFloat(instruc[1]),
                    instruc[2],
                    Float.parseFloat(instruc[3]),
                    Float.parseFloat(instruc[4]),
                    FqUnit.getFqUnit(instruc[5]));
            prescLines.add(new MedicalPrescriptionLine(prodID, tg));
        } catch(Exception e) {
            throw new IncorrectTakingGuidelinesException("Error adding the line to the prescLines array");
        }
    }

    public void modifyLine(ProductID prodID, String[] instruc) throws ProductNotInPrescription, IncorrectTakingGuidelinesException {
        //Check if product is in prescLines
        boolean found = false;
        for(int i=0; i<prescLines.size(); ++i) {
            if(prescLines.get(i).getProductID() == prodID) {
                found = true;
            }
        }
        if(!found) {
            throw new ProductNotInPrescription("Couldn't find the product on the prescription lines");
        }
        MedicalPrescriptionLine prescLine = getPrescriptionLine(prodID);
        TakingGuideline tg = prescLine.getTakingGuideline();
        try {
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
        catch(Exception e) {
            throw new IncorrectTakingGuidelinesException("Error setting the instruc on TakingGuideline");
        }
    }

    public void removeLine(ProductID prodID) throws ProductNotInPrescription {
        //Check if product is in prescLines
        boolean found = false;
        for(int i=0; i<prescLines.size(); ++i) {
            if(prescLines.get(i).getProductID() == prodID) {
                found = true;
            }
        }
        if(!found) {
            throw new ProductNotInPrescription("Couldn't find the product on the prescription lines");
        }
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
