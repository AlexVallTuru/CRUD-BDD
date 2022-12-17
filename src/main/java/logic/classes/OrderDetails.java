package logic.classes;

/**
 *
 * @author Ztudo
 */
public class OrderDetails {

    private Product product;
    private Order order;
    private int quantityOrdered;
    private double priceEach;
    private int orderLineNumber;

    public OrderDetails(Product product, Order order, int quantityOrdered, double priceEach, int orderLineNumber) {
        this.product = product;
        this.order = order;
        this.quantityOrdered = quantityOrdered;
        this.priceEach = priceEach;
        this.orderLineNumber = orderLineNumber;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public int getQuantityOrdered() {
        return quantityOrdered;
    }

    public void setQuantityOrdered(int quantityOrdered) {
        this.quantityOrdered = quantityOrdered;
    }

    public double getPriceEach() {
        return priceEach;
    }

    public void setPriceEach(double priceEach) {
        this.priceEach = priceEach;
    }

    public int getOrderLineNumber() {
        return orderLineNumber;
    }

    public void setOrderLineNumber(int orderLineNumber) {
        this.orderLineNumber = orderLineNumber;
    }

    @Override
    public String toString() {
        return "OrderDetails{" + "product=" + product + ", order=" + order + ", quantityOrdered=" + quantityOrdered + ", priceEach=" + priceEach + ", orderLineNumber=" + orderLineNumber + '}';
    }
}
