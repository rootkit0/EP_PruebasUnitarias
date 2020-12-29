package medicalconsultation;

public class TakingGuideline {
    private float dayMoment;
    private float duration;
    private String instructions;
    private Posology posology;

    public TakingGuideline(float dM, float du, String i, float d, float f, FqUnit u) {
        this.dayMoment = dM;
        this.duration = du;
        this.instructions = i;
        this.posology.setDose(d);
        this.posology.setFreq(f);
        this.posology.setFreqUnit(u);
    }

    public float getDayMoment() {
        return dayMoment;
    }

    public float getDuration() {
        return duration;
    }

    public String getInstructions() {
        return instructions;
    }

    public Posology getPosology() {
        return posology;
    }

    public void setDayMoment(float dayMoment) {
        this.dayMoment = dayMoment;
    }

    public void setDuration(float duration) {
        this.duration = duration;
    }

    public void setInstructions(String instructions) {
        this.instructions = instructions;
    }

    public void setPosology(Posology posology) {
        this.posology = posology;
    }
}
