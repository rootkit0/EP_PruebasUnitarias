package medicalconsultation;

import data.ProductID;

import java.util.Objects;

public class MedicalPrescriptionLine {
    private ProductID prodID;
    private TakingGuideline tg;

    public MedicalPrescriptionLine(ProductID prodID, TakingGuideline tg) {
        this.prodID = prodID;
        this.tg = tg;
    }

    public ProductID getProductID() {
        return prodID;
    }

    public TakingGuideline getTakingGuideline() {
        return tg;
    }

    public void setProductID(ProductID prodID) {
        this.prodID = prodID;
    }

    public void setTakingGuideline(TakingGuideline tg) {
        this.tg = tg;
    }

    @Override
    public int hashCode() {
        return Objects.hash(prodID, tg);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MedicalPrescriptionLine mpl = (MedicalPrescriptionLine) o;
        return this.prodID.equals(mpl.prodID) && this.tg.equals(mpl.tg);
    }
}
