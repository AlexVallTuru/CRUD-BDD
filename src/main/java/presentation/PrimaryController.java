package presentation;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public class PrimaryController {

    @FXML
    private Button createOrder;

    @FXML
    private TableColumn<?, ?> colQuantity;

    @FXML
    private TableColumn<?, ?> colPriceEach;

    @FXML
    private Button addProduct;

    @FXML
    private ComboBox<?> clientComboBox;

    @FXML
    private DatePicker requiredDate;

    @FXML
    private TextField orderNum;

    @FXML
    private TableColumn<?, ?> colOrderNum;

    @FXML
    private ComboBox<?> productComboBox;

    @FXML
    private Button orderDetailDeleteButton;

    @FXML
    private TextField priceEach;

    @FXML
    private TableColumn<?, ?> colOrderLineNumber;

    @FXML
    private Button orderDetailUpdateBtn;

    @FXML
    private TextField productQuantity;

    @FXML
    private TableView<?> orderDetailTableView;

    @FXML
    private TableColumn<?, ?> colTotalPrice;

    @FXML
    private DatePicker orderDate;

    @FXML
    private DatePicker shippedDate;

    @FXML
    private TableColumn<?, ?> colProductName;

    @FXML
    private Button searchOrder;

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
