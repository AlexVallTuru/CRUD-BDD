package logic;

import data.ConnectionDB;
import data.OrderDB;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
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
     * Actualiza un registro de la BBDD.
     *
     * @param order
     * @throws SQLException
     */
    public void updateOrder(Order order) throws SQLException {

        OrderDB.updateOrder(conn, order);

    }

    /**
     * Elimina un registro de la BBDD
     *
     * @param order
     * @throws SQLException
     */
    public void deleteOrder(Order order) throws SQLException {

        OrderDB.deleteOrder(conn, order);

        ordersOList.remove(order);
    }

    public void filteredOrder(Timestamp p_fecha_Desde, Timestamp p_fecha_Hasta) throws SQLException {

        this.ordersOList.setAll(OrderDB.ordersToListFiltered(conn,p_fecha_Desde, p_fecha_Hasta));
    }

}
