package com.company.gamestore.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Objects;

@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Table(name = "fee")
public class Fee {

    @Id
    @Column(name = "product_type")
    private String productType;

    @Column(name = "fee")
    @NotEmpty(message = "Fee must not be null nor empty.")
    private BigDecimal fee;

    public Fee() {}

    public Fee(String productType, BigDecimal fee) {
        this.productType = productType;
        this.fee = fee;
    }

    public String getProductType() {
        return productType;
    }

    public void setProductType(String productType) {
        this.productType = productType;
    }

    public BigDecimal getFee() {
        return fee;
    }

    public void setFee(BigDecimal fee) {
        this.fee = fee;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Fee)) return false;
        Fee fee1 = (Fee) o;
        return Objects.equals(productType, fee1.productType) && Objects.equals(fee, fee1.fee);
    }

    @Override
    public int hashCode() {
        return Objects.hash(productType, fee);
    }

    @Override
    public String toString() {
        return "Fee{" +
                "productType='" + productType + '\'' +
                ", fee=" + fee +
                '}';
    }

}