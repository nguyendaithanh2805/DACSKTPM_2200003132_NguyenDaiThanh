package org.example.coffeeshopwebsite.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "tbl_cart")
public class Cart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "total_bill",nullable = false)
    private Double totalBill;

    @ManyToMany(mappedBy = "carts", cascade = CascadeType.ALL)
    private List<Product> products;

    public Cart() {
    }

    public Cart(Long id, Double totalBill, List<Product> products) {
        this.id = id;
        this.totalBill = totalBill;
        this.products = products;
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

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }
}
