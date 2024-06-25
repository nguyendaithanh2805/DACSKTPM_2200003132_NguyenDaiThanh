package org.example.coffeeshopwebsite.model;

import jakarta.persistence.*;

@Entity
@Table(name = "tbl_product_cart")
public class ProductCart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "product_id", referencedColumnName = "id", foreignKey = @ForeignKey(name = "FK_ProductCart_Product"))
    private Product product;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "cart_id", referencedColumnName = "id", foreignKey = @ForeignKey(name = "FK_ProductCart_Cart"))
    private Cart cart;

    public ProductCart() {
    }

    public ProductCart(Long id, Product product, Cart cart) {
        this.id = id;
        this.product = product;
        this.cart = cart;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Cart getCart() {
        return cart;
    }

    public void setCart(Cart cart) {
        this.cart = cart;
    }
}
