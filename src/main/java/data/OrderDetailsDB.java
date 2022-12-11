/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
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
     * Retorna el contingut de la taula en una col·lecció d'Assignatura
     *
     * @param conn
     * @return
     * @throws java.sql.SQLException
     */
    public static ArrayList<OrderDetails> orderDetailsToList(Connection conn) throws SQLException {

        ArrayList<OrderDetails> orderDetailsList = new ArrayList<>();

        Statement query;
        query = conn.createStatement();
        query.executeQuery("SELECT * FROM orderdetails");

        ResultSet rs = query.getResultSet();

        while (rs.next()) {
            Product product = getProduct(conn, rs.getInt("productCode"));
            orderDetailsList.add(new OrderDetails(product, null, rs.getInt("quantityOrdered"), rs.getDouble("priceEach"), rs.getInt("orderLineNumber")));
        }
        return orderDetailsList;
    }

    /**
     * Retorna un producto siempre y cuando coincida con el productCode.
     *
     * @param con
     * @param productCode
     * @return
     * @throws SQLException
     */
    public static Product getProduct(Connection con, int productCode) throws SQLException {
        Product product = null;

        Statement query;
        query = con.createStatement();
        query.executeQuery("SELECT * FROM products WHERE productCode = " + productCode);

        ResultSet rs = query.getResultSet();

        if (rs.next()) {
            product = new Product(rs.getInt("productCode"), rs.getString("productName"), rs.getString("productDescription"), rs.getInt("quantityInStock"), rs.getDouble("buyPrice"));
        }
        return product;
    }
}
