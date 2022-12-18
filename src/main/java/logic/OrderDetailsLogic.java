package logic;

import data.ConnectionDB;
import data.OrderDetailsDB;
import java.sql.Connection;
import java.sql.SQLException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import logic.classes.OrderDetails;

/**
 *
 * @author Ztudo
 */
public class OrderDetailsLogic {

    Connection conn;
    ObservableList<OrderDetails> orderDetailsOList;

    public OrderDetailsLogic() throws SQLException {
        // inicialitzem connexió amb BD pero passant per la capa d'aplicació
        conn = ConnectionDB.getInstance().getConnection();

        // inicialitzem col.lecció
        orderDetailsOList = FXCollections.<OrderDetails>observableArrayList();
    }

    /**
     * Rellena los registros de la tabla con los productos del nº de pedido.
     *
     * @param orderNum
     * @throws SQLException
     */
    public void setData(int orderNum) throws SQLException {

        this.orderDetailsOList.setAll(OrderDetailsDB.orderDetailsToList(conn, orderNum));

    }

    /**
     * Obtiene la lista observable
     *
     * @return
     */
    public ObservableList<OrderDetails> getOrderDetailsObservableList() {
        return orderDetailsOList;
    }

    /**
     * Añade un producto en el pedido.
     *
     * @param detail
     * @throws SQLException
     */
    public void insertOrderDetail(OrderDetails detail) throws SQLException {

        detail.setOrderLineNumber(OrderDetailsDB.insertOrderDetail(conn, detail));

        orderDetailsOList.add(detail);
    }

    /**
     * Actualiza un registro de la BBDD.
     *
     * @param detail
     * @throws SQLException
     */
    public void updateOrderDetail(OrderDetails detail) throws SQLException {

        OrderDetailsDB.updateOrderDetail(conn, detail);

    }
}
