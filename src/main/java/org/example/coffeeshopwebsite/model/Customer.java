package org.example.coffeeshopwebsite.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
@DiscriminatorValue("CUSTOMER")
public class Customer extends Account{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "full_name", nullable = false)
    private String fullName;
    private Boolean gender;
    private String email;
    private String address;

    @OneToMany(mappedBy = "customer")
    private List<Cart> carts;

    public Customer() {
    }

    public Customer(Long id, String fullName, Boolean gender, String email, String address, List<Cart> carts) {
        super();
        this.id = id;
        this.fullName = fullName;
        this.gender = gender;
        this.email = email;
        this.address = address;
        this.carts = carts;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public Boolean getGender() {
        return gender;
    }

    public void setGender(Boolean gender) {
        this.gender = gender;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public List<Cart> getCarts() {
        return carts;
    }

    public void setCarts(List<Cart> carts) {
        this.carts = carts;
    }
}
