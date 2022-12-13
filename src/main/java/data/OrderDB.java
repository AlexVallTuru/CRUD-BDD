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
import logic.classes.Order;

/**
 *
 * @author Kiwi
 */
public class OrderDB {

    /**
     * Retorna el contingut de la taula en una col·lecció d'Assignatura
     *
     * @param conn
     * @return
     * @throws java.sql.SQLException
     */
    public static ArrayList<Order> ordersToList(Connection conn) throws SQLException {

        ArrayList<Order> ordersList = new ArrayList<>();

        Statement query;
        query = conn.createStatement();
        query.executeQuery("SELECT * FROM orders");

        ResultSet rs = query.getResultSet();
        while (rs.next()) {
            //Customer customer = getCustomer(conn, rs.getString("customers_customerEmail"));
            ordersList.add(new Order(rs.getInt("orderNumber"), rs.getDate("orderDate"), rs.getDate("requiredDate"), rs.getDate("shippedDate"), rs.getString("customers_CustomerEmail")));
        }
        return ordersList;
    }

    /**
     * Inserta un nuevo pedido
     *
     * @param conn
     * @param order
     * @throws SQLException
     */
    public static void insertOrder(Connection conn, Order order) throws SQLException {

        Statement query;
        query = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
        query.executeQuery("SELECT * FROM order BY orderNumber ASC");

        ResultSet rs = query.getResultSet();

        if (rs.next()) {
            rs.last();
        }

        rs.moveToInsertRow();

        rs.updateDate("orderDate", order.getOrderDate());
        rs.updateDate("requiredDate", order.getRequiredDate());
        rs.updateDate("shippedDate", order.getShippedDate());
        rs.updateString("customers_customerEmail", order.getCustomer());

        rs.insertRow();
        rs.getInt("orderNumber");
    }
}
