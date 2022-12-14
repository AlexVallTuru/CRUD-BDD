/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package logic;

import data.ConnectionDB;
import data.OrderDB;
import java.sql.Connection;
import java.sql.SQLException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import logic.classes.Order;

/**
 *
 * @author Kiwi
 */
public class OrderLogic {

    Connection conn;
    ObservableList<Order> ordersOList;

    public OrderLogic() throws SQLException {
        // inicialitzem connexió amb BD pero passant per la capa d'aplicació
        conn = ConnectionDB.getInstance().getConnection();

        // inicialitzem col.lecció
        ordersOList = FXCollections.<Order>observableArrayList();
    }

    /**
     * Omple la llistaObservable amb els registres de la taula
     *
     * @throws SQLException
     */
    public void setData() throws SQLException {
        this.ordersOList.setAll(OrderDB.ordersToList(conn));
    }

    /**
     * Obtiene la lista observable
     *
     * @return
     */
    public ObservableList<Order> getOrderObservableList() {
        return ordersOList;
    }

    public void insertOrder() throws SQLException {

        Order order = new Order();

        // afegim una nova assignatura
        OrderDB.insertOrder(conn, order);

        // Si tot ha anat bé, afegim l'objecte a la llista observable.
        // NOTA: Quan afegim o eliminem elements de la collecció, la taula es
        // refresca de forma automàtica, amb el mateix efecte que
        // si fessim taulaAssignatura.refresh()
        ordersOList.add(order);
    }
}
