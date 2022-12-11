package presentation;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Region;
import logic.OrderDetailsLogic;
import logic.OrderLogic;

public class PrimaryController implements Initializable {

    OrderLogic orderLogicLayer;
    OrderDetailsLogic orderDetailsLogicLayer;

    @FXML
    private TableView orderDetailTableView;

    @FXML
    private TableColumn colOrderNumDetails, colProductName, colPriceEach, colQuantity, colOrderLineNumber, colTotalPrice;

    @FXML
    private TableView orderTableView;

    @FXML
    private TableColumn colOrderNum, colOrderDate, colRequiredDate, colShippedDate, colCustomerEmailOrder, colTotalOrderPrice;

    @FXML
    private Button createOrderBtn;

    @FXML
    private Button addProductBtn;

    @FXML
    private Button orderDetailDeleteButton;

    @FXML
    private Button orderDetailUpdateBtn;

    @FXML
    private Button searchOrderDetail;

    @FXML
    private Button refreshOrderBtn;

    @FXML
    private Button deleteOrderBtn;

    @FXML
    private Button modifyOrderBtn;

    @FXML
    private Button searchOrderBtn;

    @FXML
    private ComboBox<?> clientComboBox;

    @FXML
    private ComboBox<?> productComboBox;

    @FXML
    private TextField orderNum;

    @FXML
    private TextField priceEach;

    @FXML
    private TextField productQuantity;

    @FXML
    private DatePicker requiredDate;

    @FXML
    private DatePicker orderDate;

    @FXML
    private DatePicker shippedDate;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //Inicializa la capa lógica, que incluye la conexión con la BBDD
        try {
            orderLogicLayer = new OrderLogic();
            orderLogicLayer.setData();
            orderTableView.setItems(orderLogicLayer.getOrderObservableList());
        } catch (SQLException ex) {
            showMessage(1, "Error cargando datos: " + ex.toString());
        } catch (Exception ex) {
            showMessage(1, "Error iniciando la capa lógica: " + ex.toString());
        }

        //Vínculo entre los atributos de la clase Order y las columnas de orderTableView
        colOrderNum.setCellValueFactory(new PropertyValueFactory<>("orderNumber"));
        colOrderDate.setCellValueFactory(new PropertyValueFactory<>("orderDate"));
        colRequiredDate.setCellValueFactory(new PropertyValueFactory<>("requiredDate"));
        colShippedDate.setCellValueFactory(new PropertyValueFactory<>("shippedDate"));
        colCustomerEmailOrder.setCellValueFactory(new PropertyValueFactory<>("customer"));
        //colTotalOrderPrice.setCellValueFactory(new PropertyValueFactory<>("Descripcio"));

    }

    /**
     * Mostra una ventana con un mensaje
     *
     * @param tipus 0 = info, 1 = error
     * @param txt
     */
    private void showMessage(int type, String txt) {

        Alert alert;

        switch (type) {
            case 0: {
                alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("INFORMACIÓN");
            }
            break;
            case 1: {
                alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("ERROR");
            }
            break;
            default:
                alert = new Alert(Alert.AlertType.INFORMATION);
        }

        alert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
        alert.setContentText(txt);
        alert.showAndWait();
    }

    /**
     * Muestra una info por pantalla
     *
     * @param txt
     */
    private void showInfo(String txt) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Info:");
        alert.setContentText(txt);

        alert.showAndWait();
    }

    @FXML
    void onActionAddProductBtn(ActionEvent event) {

    }

    @FXML
    void onActionOrderDetailDeleteButton(ActionEvent event) {

    }

    @FXML
    void onActionClientComboBox(ActionEvent event) {

    }

    @FXML
    void onActionProductComboBox(ActionEvent event) {

    }

    @FXML
    void onActionOrderDate(ActionEvent event) {

    }

    @FXML
    void onActionShippedDate(ActionEvent event) {

    }

    @FXML
    void onActionRequiredDate(ActionEvent event) {

    }

    @FXML
    void onActionOrderNum(ActionEvent event) {

    }

    @FXML
    void onActionProductQuantity(ActionEvent event) {

    }

    @FXML
    void onActionPriceEach(ActionEvent event) {

    }

    @FXML
    void onActionSearchOrderDetail(ActionEvent event) {

    }

    @FXML
    void onActionCreateOrderBtn(ActionEvent event) {

    }

    @FXML
    void onActionOrderDetailUpdateBtn(ActionEvent event) {

    }

    @FXML
    void onActionRefreshOrderBtn(ActionEvent event) {

    }

    @FXML
    void onActionDeleteOrderBtn(ActionEvent event) {

    }

    @FXML
    void onActionModifyOrderBtn(ActionEvent event) {

    }

    @FXML
    void onActionSearchOrderBtn(ActionEvent event) {

    }
}
