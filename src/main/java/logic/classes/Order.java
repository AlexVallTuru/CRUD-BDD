package logic.classes;

import java.sql.Timestamp;

/**
 *
 * @author Ztudo
 */
public class Order {

    private int orderNumber;
    private Timestamp orderDate;
    private Timestamp requiredDate;
    private Timestamp shippedDate;
    private Customer customer;
    private double totalPrice;

    public Order(int orderNumber, Timestamp orderDate, Timestamp requiredDate, Timestamp shippedDate, Customer customer) {
        this.orderNumber = orderNumber;
        this.orderDate = orderDate;
        this.requiredDate = requiredDate;
        this.shippedDate = shippedDate;
        this.customer = customer;
    }

    public Order() {
    }

    public int getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(int orderNumber) {
        this.orderNumber = orderNumber;
    }

    public Timestamp getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Timestamp orderDate) {
        this.orderDate = orderDate;
    }

    public Timestamp getRequiredDate() {
        return requiredDate;
    }

    public void setRequiredDate(Timestamp requiredDate) {
        this.requiredDate = requiredDate;
    }

    public Timestamp getShippedDate() {
        return shippedDate;
    }

    public void setShippedDate(Timestamp shippedDate) {
        this.shippedDate = shippedDate;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    @Override
    public String toString() {
        return "Order{" + "orderNumber=" + orderNumber + ", dateTime=" + orderDate + ", requiredDate=" + requiredDate + ", shippedDate=" + shippedDate + ", customer=" + customer + '}';
    }
}
