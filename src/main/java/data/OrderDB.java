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
import logic.classes.Customer;
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
            Customer customer = getCustomer(conn, rs.getString("customers_customerEmail"));
            ordersList.add(new Order(rs.getInt("orderNumber"), rs.getString("orderDate"), rs.getString("requiredDate"), rs.getString("shippedDate"), customer));
        }
        return ordersList;
    }

    /**
     * Retorna un customer cuyo customerEmail coincida con el pasado por
     * parámetro.
     *
     * @param conn
     * @param customerEmail
     * @return
     * @throws SQLException
     */
    public static Customer getCustomer(Connection conn, String customerEmail) throws SQLException {
        Customer customer = null;

        Statement query;
        query = conn.createStatement();
        query.executeQuery("SELECT * FROM customers WHERE customerEmail = '" + customerEmail + "'");

        ResultSet rs = query.getResultSet();

        if (rs.next()) {
            customer = new Customer(rs.getString("customerEmail"), rs.getString("idCard"), rs.getString("customerName"), rs.getString("phone"), rs.getDouble("creditLimit"), rs.getString("birthDate"));
        }
        return customer;
    }

}
