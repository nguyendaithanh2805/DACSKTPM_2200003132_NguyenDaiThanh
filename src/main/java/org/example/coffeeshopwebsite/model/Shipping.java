package org.example.coffeeshopwebsite.model;
import jakarta.persistence.*;

@Entity
@Table(name = "tbl_shipping")
public class Shipping {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "shipping_fee", nullable = false)
    private Double shippingFee;

    public Shipping() {
    }

    public Shipping(Long id, Double shippingFee) {
        this.id = id;
        this.shippingFee = shippingFee;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getShippingFee() {
        return shippingFee;
    }

    public void setShippingFee(Double shippingFee) {
        this.shippingFee = shippingFee;
    }
}
