package data;

final public class ProductID {
    private final String upcCode;
    public ProductID(String code) {
        this.upcCode = code;
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
}
