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
     */
    public void updateOrder(Order order) throws SQLException {

        OrderDB.updateOrder(conn, order);

    }

    public void deleteOrder(Order order) throws SQLException {

        OrderDB.deleteOrder(conn, order);

        ordersOList.remove(order);
    }
}
