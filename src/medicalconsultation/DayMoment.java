package medicalconsultation;

public enum DayMoment {
    BEFOREBREAKFAST, DURINGBREAKFAST, AFTERBREAKFAST, BEFORELUNCH,
    DURINGLUNCH, AFTERLUNCH, BEFOREDINNER, DURINGDINNER, AFTERDINNER,
    BEFOREMEALS, DURINGMEALS, AFTERMEALS;

    public static DayMoment getDayMoment(String moment) {
        switch(moment) {
            case "BEFOREBREAKFAST": return DayMoment.BEFOREBREAKFAST;
            case "DURINGBREAKFAST": return DayMoment.DURINGBREAKFAST;
            case "AFTERBREAKFAST": return DayMoment.AFTERBREAKFAST;
            case "BREFORELUNCH": return DayMoment.BEFORELUNCH;
            case "DURINGLUNCH": return DayMoment.DURINGLUNCH;
            case "AFTERLUNCH": return DayMoment.AFTERLUNCH;
            case "BEFOREDINNER": return DayMoment.BEFOREDINNER;
            case "DURINGDINNER": return DayMoment.DURINGDINNER;
            case "AFTERDINNER": return DayMoment.AFTERDINNER;
            case "BEFOREMEALS": return DayMoment.BEFOREMEALS;
            case "DURINGMEALS": return DayMoment.DURINGMEALS;
            case "AFTERMEALS": return DayMoment.AFTERMEALS;
            default: return null;
        }
    }
}
