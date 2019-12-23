package br.com.redhat.model;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;

public class Order implements Serializable {
    @NotNull
    private Customer customer;

    @NotNull
    private List<Product> products;

    private Long priority;

    private Long orderNumber;

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public Long getPriority() {
        return priority;
    }

    public void setPriority(Long priority) {
        this.priority = priority;
    }

    public Long getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(Long orderNumber) {
        this.orderNumber = orderNumber;
    }
}