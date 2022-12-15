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
     * Retorna el contenido de la tabla en una colección de "Orders"
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
            ordersList.add(new Order(rs.getInt("orderNumber"), rs.getTimestamp("orderDate"), rs.getTimestamp("requiredDate"), rs.getTimestamp("shippedDate"), rs.getString("customers_CustomerEmail")));
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
        query.executeQuery("SELECT * FROM orders");

        ResultSet rs = query.getResultSet();

        rs.moveToInsertRow();

        rs.updateTimestamp("orderDate", order.getOrderDate());
        rs.updateTimestamp("requiredDate", order.getRequiredDate());
        rs.updateTimestamp("shippedDate", order.getShippedDate());
        rs.updateString("customers_customerEmail", order.getCustomer());

        rs.insertRow();
    }

    /**
     * Actualiza un nuevo pedido
     *
     * @param conn
     * @param order
     * @throws SQLException
     */
    public static void updateOrder(Connection conn, Order order) throws SQLException {

        Statement query;
        query = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
        query.executeQuery("SELECT * FROM orders WHERE customers_customerEmail = " + order.getCustomer());

        ResultSet rs = query.getResultSet();

        if (rs.next()) {
            rs.updateTimestamp("orderDate", order.getOrderDate());
            rs.updateTimestamp("requiredDate", order.getRequiredDate());
            rs.updateTimestamp("shippedDate", order.getShippedDate());

            rs.updateRow();
        }
    }

    /**
     * Elimina el registro Order recibido como parámetro de la BBDD.
     *
     * @param conn
     * @param order
     * @throws SQLException
     */
    public static void deleteOrder(Connection conn, Order order) throws SQLException {

        Statement query;
        query = conn.createStatement();

        String sqlStr = "DELETE FROM orders WHERE orderNumber = " + order.getOrderNumber();

        query.executeUpdate(sqlStr);
    }
}
