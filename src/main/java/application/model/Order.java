/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package application.model;

/**
 *
 * @author Ztudo
 */
public class Order {

    private int orderNumber;
    private String dateTime;
    private String requiredDate;
    private String shippedDate;
    private Customer customer;

    public Order(int orderNumber, String dateTime, String requiredDate, String shippedDate, Customer customer) {
        this.orderNumber = orderNumber;
        this.dateTime = dateTime;
        this.requiredDate = requiredDate;
        this.shippedDate = shippedDate;
        this.customer = customer;
    }

    public int getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(int orderNumber) {
        this.orderNumber = orderNumber;
    }

    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }

    public String getRequiredDate() {
        return requiredDate;
    }

    public void setRequiredDate(String requiredDate) {
        this.requiredDate = requiredDate;
    }

    public String getShippedDate() {
        return shippedDate;
    }

    public void setShippedDate(String shippedDate) {
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
        return "Order{" + "orderNumber=" + orderNumber + ", dateTime=" + dateTime + ", requiredDate=" + requiredDate + ", shippedDate=" + shippedDate + ", customer=" + customer + '}';
    }
}
