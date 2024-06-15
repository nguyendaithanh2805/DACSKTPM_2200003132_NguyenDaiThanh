package org.example.coffeeshopwebsite.model;
import jakarta.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "tbl_order")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "order_date")
    private Date orderDate;

    @Column(name = "shipping_date")
    private Date ShippingDate;

    @Column(nullable = false)
    private Boolean status;
    private String note;

    @ManyToMany(mappedBy = "orders")
    private List<Product> products;

    @OneToMany(mappedBy = "order")
    private List<Payment> payments;

    @OneToOne
    @JoinColumn(name = "shipping_id", referencedColumnName = "id", foreignKey = @ForeignKey(name = "FK_Order_Shipping"))
    private Shipping shipping;

    public Order() {
    }

    public Order(Long id, Date orderDate, Date shippingDate,
                 Boolean status, String note, List<Product> products,
                 List<Payment> payments, Shipping shipping) {
        this.id = id;
        this.orderDate = orderDate;
        ShippingDate = shippingDate;
        this.status = status;
        this.note = note;
        this.products = products;
        this.payments = payments;
        this.shipping = shipping;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public Date getShippingDate() {
        return ShippingDate;
    }

    public void setShippingDate(Date shippingDate) {
        ShippingDate = shippingDate;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public List<Payment> getPayments() {
        return payments;
    }

    public void setPayments(List<Payment> payments) {
        this.payments = payments;
    }

    public Shipping getShipping() {
        return shipping;
    }

    public void setShipping(Shipping shipping) {
        this.shipping = shipping;
    }
}
