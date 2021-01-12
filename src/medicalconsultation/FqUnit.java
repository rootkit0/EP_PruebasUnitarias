package medicalconsultation;

public enum FqUnit {
    HOUR, DAY, WEEK, MONTH;

    public static FqUnit getFqUnit(String fqUnit) {
        switch (fqUnit) {
            case "HOUR": return FqUnit.HOUR;
            case "DAY": return FqUnit.DAY;
            case "WEEK": return FqUnit.WEEK;
            case "MONTH": return FqUnit.MONTH;
            default: return null;
        }
    }
}
