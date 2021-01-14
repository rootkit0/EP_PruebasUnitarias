package data;

import exceptions.NullObjectException;

final public class DigitalSignature {
    private final byte[] doctorSign;

    public DigitalSignature(byte[] sign) throws NullObjectException {
        if(sign == null) {
            throw new NullObjectException("Doctor signature is null");
        }
        this.doctorSign = sign;
    }

    public byte[] getDoctorSignature() {
        return doctorSign;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DigitalSignature digitalSign = (DigitalSignature) o;
        return doctorSign.equals(digitalSign.doctorSign);
    }

    @Override
    public int hashCode() {
        return doctorSign.hashCode();
    }

    @Override
    public String toString() {
        return "DigitalSignature{" + "doctor signature='" + doctorSign + '\'' + '}';
    }
}
