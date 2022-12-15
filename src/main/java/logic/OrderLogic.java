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

    /**
     * Recibe un objeto tipo Order y lo envía a la capa de BBDD.
     *
     * @param order
     * @throws SQLException
     */
    public void insertOrder(Order order) throws SQLException {

        OrderDB.insertOrder(conn, order);

    }

    /**
     * Valida el format d'una assignatura i la modifica amb les dades
     * subministrades
     *
     * @param order
     * @throws SQLException
     * @throws Exception
     */
    public void updateOrder(Order order) throws SQLException, Exception {
        // si no valida el format del nom, genera una excepció
        /*if (!this.validaNomAssignatura(order.getNom())) {
            throw new Exception("El format del nom de l'assignatura no és correcte. Un exemple de format correcte seria ABC-123");
        }*/

        OrderDB.updateOrder(conn, order);
    }
}
