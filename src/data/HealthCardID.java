package data;

final public class HealthCardID {
    private final String personalID;

    public HealthCardID(String code) {
        this.personalID = code;
    }

    public String getPersonalID() {
        return personalID;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        HealthCardID hcardID = (HealthCardID) o;
        return personalID.equals(hcardID.personalID);
    }

    @Override
    public int hashCode() {
        return personalID.hashCode();
    }

    @Override
    public String toString() {
        return "HealthCardID{" + "personal code='" + personalID + '\'' + '}';
    }

    private boolean checkHealthCardID(String personalID) {
        //Check the size of the personalID
        if(personalID.length() == 28) {
            for(int i=0; i<28; ++i) {
                //Check first 8 chars = B
                if(i<8 && personalID.charAt(i) != 'B') {
                    return false;
                }
                //Check next 2 are letters
                if(i>=8 && i<10 && !Character.isLetter(personalID.charAt(i))) {
                    return false;
                }
                //Check next 6 are numbers
                if(i>=10 && !Character.isDigit(personalID.charAt(i))) {
                    return false;
                }
            }
            return true;
        }
        else {
            return false;
        }
    }
}
