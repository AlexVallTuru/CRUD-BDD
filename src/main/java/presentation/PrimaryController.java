package presentation;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public class PrimaryController implements Initializable {

    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

    @FXML
    private TableView<?> orderDetailTableView;

    @FXML
    private TableColumn colOrderNum, colProductName, colPriceEach, colQuantity, colOrderLineNumber, colTotalPrice;

    @FXML
    private Button createOrder;

    @FXML
    private Button addProduct;

    @FXML
    private Button orderDetailDeleteButton;

    @FXML
    private Button orderDetailUpdateBtn;

    @FXML
    private Button searchOrder;

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

    @FXML
    void onActionAddProduct(ActionEvent event) {

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
    void onActionSearchOrder(ActionEvent event) {

    }

    @FXML
    void onActionCreateOrder(ActionEvent event) {

    }

    @FXML
    void onActionOrderDetailUpdateBtn(ActionEvent event) {

    }
}
