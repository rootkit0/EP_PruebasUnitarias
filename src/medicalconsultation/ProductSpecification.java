package medicalconsultation;

import data.ProductID;

import java.math.BigDecimal;

public class ProductSpecification {
    private ProductID upcCode;
    private String description;
    private BigDecimal price;

    public ProductSpecification(ProductID upcCode, String description, BigDecimal price) {
        this.upcCode = upcCode;
        this.description = description;
        this.price = price;
    }

    public ProductID getUpcCode() {
        return upcCode;
    }

    public String getDescription() {
        return description;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setUpcCode(ProductID upcCode) {
        this.upcCode = upcCode;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProductSpecification ps = (ProductSpecification) o;
        return this.upcCode == ps.upcCode && this.description == ps.description && this.price == ps.price;
    }
}
