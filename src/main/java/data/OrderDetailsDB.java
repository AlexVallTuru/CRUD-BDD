package data;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import logic.classes.OrderDetails;
import logic.classes.Product;

/**
 *
 * @author Ztudo
 */
public class OrderDetailsDB {

    /**
     * Retorna el contenido de la table en una colección de OrderDetails
     *
     * @param conn
     * @param orderNum
     * @return
     * @throws java.sql.SQLException
     */
    public static ArrayList<OrderDetails> orderDetailsToList(Connection conn, int orderNum) throws SQLException {

        ArrayList<OrderDetails> orderDetailsList = new ArrayList<>();

        Statement query;
        query = conn.createStatement();
        query.executeQuery("SELECT * FROM orderdetails WHERE orderNumber =" + orderNum);

        ResultSet rs = query.getResultSet();

        while (rs.next()) {
            Product product = getProduct(conn, rs.getInt("productCode"));
            orderDetailsList.add(new OrderDetails(product, null, rs.getInt("quantityOrdered"), rs.getDouble("priceEach"), rs.getInt("orderLineNumber")));
        }
        return orderDetailsList;
    }

    /**
     * Retorna un producto cuyo productCode coincida con el pasado por
     * parámetro.
     *
     * @param conn
     * @param productCode
     * @return
     * @throws SQLException
     */
    public static Product getProduct(Connection conn, int productCode) throws SQLException {
        Product product = null;

        Statement query;
        query = conn.createStatement();
        query.executeQuery("SELECT * FROM products WHERE productCode = " + productCode);

        ResultSet rs = query.getResultSet();

        if (rs.next()) {
            product = new Product(rs.getInt("productCode"), rs.getString("productName"), rs.getString("productDescription"), rs.getInt("quantityInStock"), rs.getDouble("buyPrice"));
        }
        return product;
    }
}
