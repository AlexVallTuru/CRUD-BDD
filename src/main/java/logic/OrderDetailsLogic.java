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
     * Omple la llistaObservable amb els registres de la taula
     *
     * @throws SQLException
     */
    public void setData() throws SQLException {

        this.orderDetailsOList.setAll(OrderDetailsDB.orderDetailsToList(conn));

    }
}
