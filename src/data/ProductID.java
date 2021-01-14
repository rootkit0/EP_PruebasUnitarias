package data;

import exceptions.NullObjectException;
import exceptions.WrongIdCodeFormat;

final public class ProductID {
    private final String upcCode;

    public ProductID(String code) throws NullObjectException, WrongIdCodeFormat {
        if(code == null) {
            throw new NullObjectException("ProductID is null");
        }
        if(checkProductID(code)) {
            this.upcCode = code;
        }
        else {
            throw new WrongIdCodeFormat("ProductID has wrong format");
        }
    }

    public String getUpcCode() {
        return upcCode;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProductID productID = (ProductID) o;
        return upcCode.equals(productID.upcCode);
    }

    @Override
    public int hashCode() {
        return upcCode.hashCode();
    }

    @Override
    public String toString() {
        return "ProductID{" + "product code='" + upcCode + '\'' + '}';
    }

    private boolean checkProductID(String upcCode) {
        //Check the size of the code
        if(upcCode.length() == 12) {
            //Check that all characters are numbers
            for(int i=0; i<12; ++i) {
                if(!Character.isDigit(upcCode.charAt(i))) {
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
