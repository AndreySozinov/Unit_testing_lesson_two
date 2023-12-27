package ru.savrey;

import java.util.Objects;

public class Product {
    private Integer id;
    private String name;
    private Double price;
    private Integer quantity;

    public Product() {
    }

    public Product(Integer id, String name, Double price, Integer quantity) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Double getPrice() {
        return price;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return Objects.equals(id, product.id) && Objects.equals(name, product.name) &&
                Objects.equals(price, product.price) && Objects.equals(quantity, product.quantity);
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 29 * hash +Objects.hashCode(this.id);
        hash = 29 * hash +Objects.hashCode(this.name);
        hash = 29 * hash +Objects.hashCode(this.price);
        hash = 29 * hash +Objects.hashCode(this.quantity);
        return hash;
    }
}
