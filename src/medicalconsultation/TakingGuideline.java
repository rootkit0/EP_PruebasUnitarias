package medicalconsultation;

public class TakingGuideline {
    private DayMoment dayMoment;
    private float duration;
    private String instructions;
    private Posology posology;

    public TakingGuideline(DayMoment dM, float du, String i, float d, float f, FqUnit u) {
        this.dayMoment = dM;
        this.duration = du;
        this.instructions = i;
        this.posology = new Posology(d, f, u);
    }

    public TakingGuideline(DayMoment dM, float du, String i, Posology p) {
        this.dayMoment = dM;
        this.duration = du;
        this.instructions = i;
        this.posology = p;
    }

    public DayMoment getDayMoment() {
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

    public void setDayMoment(DayMoment dayMoment) {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TakingGuideline tg = (TakingGuideline) o;
        return this.dayMoment == tg.dayMoment && this.duration == tg.duration && this.instructions == tg.instructions && this.posology == tg.posology;
    }
}
