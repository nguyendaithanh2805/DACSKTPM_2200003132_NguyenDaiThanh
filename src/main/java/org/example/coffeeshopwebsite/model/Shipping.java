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

    @OneToOne
    @JoinColumn(name = "order_id", referencedColumnName = "id", foreignKey = @ForeignKey(name = "FK_Shipping_Order"))
    private Order order;

    public Shipping() {
    }

    public Shipping(Long id, Double shippingFee, Order order) {
        this.id = id;
        this.shippingFee = shippingFee;
        this.order = order;
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

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }
}
