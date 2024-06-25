package org.example.coffeeshopwebsite.model;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "tbl_cart")
public class Cart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "total_bill",nullable = false)
    private Double totalBill;

    @Column(nullable = false)
    private Integer quantity;

    @OneToMany(mappedBy = "cart")
    private List<ProductCart> productCarts;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id", foreignKey = @ForeignKey(name = "FK_Cart_User"))
    private User user;

    public Cart() {
    }

    public Cart(Long id, Double totalBill, Integer quantity, List<ProductCart> productCarts, User user) {
        this.id = id;
        this.totalBill = totalBill;
        this.quantity = quantity;
        this.productCarts = productCarts;
        this.user = user;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getTotalBill() {
        return totalBill;
    }

    public void setTotalBill(Double totalBill) {
        this.totalBill = totalBill;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public List<ProductCart> getProductCarts() {
        return productCarts;
    }

    public void setProductCarts(List<ProductCart> productCarts) {
        this.productCarts = productCarts;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
