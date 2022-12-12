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
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Region;
import logic.CustomerLogic;
import logic.OrderDetailsLogic;
import logic.OrderLogic;
import logic.classes.Customer;

public class PrimaryController implements Initializable {

    CustomerLogic customerLogicLayer;
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
            //Customer Order
            orderLogicLayer = new OrderLogic();
            orderLogicLayer.setData();
            orderTableView.setItems(orderLogicLayer.getOrderObservableList());
            //Customer Logic
            customerLogicLayer = new CustomerLogic();
            customerLogicLayer.setData();
            tv_customer.setItems(customerLogicLayer.getCustomerObservableList());
        } catch (SQLException ex) {
            showMessage(1, "Error cargando datos: " + ex.toString());
        } catch (Exception ex) {
            showMessage(1, "Error iniciando la capa lógica: " + ex.toString());
        }

        //Vínculo entre los atributos de la clase Order y las columnas de orderTableView
        //Columnas Order
        colOrderNum.setCellValueFactory(new PropertyValueFactory<>("orderNumber"));
        colOrderDate.setCellValueFactory(new PropertyValueFactory<>("orderDate"));
        colRequiredDate.setCellValueFactory(new PropertyValueFactory<>("requiredDate"));
        colShippedDate.setCellValueFactory(new PropertyValueFactory<>("shippedDate"));
        colCustomerEmailOrder.setCellValueFactory(new PropertyValueFactory<>("customer"));
        //colTotalOrderPrice.setCellValueFactory(new PropertyValueFactory<>("Descripcio"));
        //Columnas Costumer
        col_customerEmail.setCellValueFactory(new PropertyValueFactory<>("customerEmail"));
        col_idCard.setCellValueFactory(new PropertyValueFactory<>("idCard"));
        col_customerName.setCellValueFactory(new PropertyValueFactory<>("customerName"));
        col_phoneNumber.setCellValueFactory(new PropertyValueFactory<>("phoneNumber"));
        col_birthDate.setCellValueFactory(new PropertyValueFactory<>("birthDate"));
        col_creditLimit.setCellValueFactory(new PropertyValueFactory<>("creditLimit"));
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
    //CUSTOMER 

    @FXML
    private Button bt_aniadir, bt_actualizar, bt_eliminar;
    @FXML
    private TableView tv_customer;
    @FXML
    private TableColumn col_customerName, col_idCard, col_creditLimit, col_phoneNumber, col_customerEmail, col_birthDate;
    @FXML
    private TextField tf_customerName, tf_idCard, tf_creditLimit, tf_phoneNumber, tf_customerEmail, tf_birthDate;

    @FXML
    void onClick_bt_aniadir(ActionEvent event) throws SQLException {

        customerLogicLayer.afegirCustomer(getCustomerFromView());

        //Para actualizar la pagina
        customerLogicLayer.setData();
        tv_customer.setItems(customerLogicLayer.getCustomerObservableList());
    }

    @FXML
    void onClick_bt_actualizar(ActionEvent event) throws Exception {
        customerLogicLayer.modificarCustomer(getCustomerFromView());
    }

    @FXML
    void onClick_bt_eliminar(ActionEvent event) {

    }

    private Customer getCustomerFromView() throws NumberFormatException {
        Customer customer = new Customer();

        customer.setCustomerName(tf_customerName.getText());
        customer.setCustomerEmail(tf_customerEmail.getText());
        customer.setIdCard(tf_idCard.getText());
        customer.setPhoneNumber(tf_phoneNumber.getText());
        customer.setBirthDate(tf_birthDate.getText());
        customer.setCreditLimit(Double.parseDouble(tf_creditLimit.getText()));

        return customer;
    }

    @FXML
    private void handleOnMouseClicked(MouseEvent ev) {
        // si hem seleccionat un registre de la taula
        if (tv_customer.getSelectionModel().getSelectedItem() != null) {
            // agafem les dades de l'objecte seleccionat i els traspassem
            // als camps del formulari
            setCustomerToView(getCustomerFromTable());

            //habilitem botó de modificar i eliminar
            //btnModificar.setDisable(false);
            //btnEliminar.setDisable(false);
        } else {
            //desactivaSeleccio();
        }
    }

    private void setCustomerToView(Customer customer) {
        if (customer != null) {
            tf_birthDate.setText(customer.getBirthDate());
            tf_customerEmail.setText(customer.getCustomerEmail());
            tf_creditLimit.setText(String.valueOf(customer.getCreditLimit()));
            tf_customerName.setText(customer.getCustomerName());
            tf_idCard.setText(customer.getIdCard());
            tf_phoneNumber.setText(customer.getPhoneNumber());
        }
    }
        private Customer getCustomerFromTable()
    {
        Customer customer = null;
        
        customer = (Customer)tv_customer.getSelectionModel().getSelectedItem();
        
        return customer;
    }

    /**
     * Deshabilita botons i selecció d'usuari
     */
    //private void desactivaSeleccio()
    /**
     * {
     * //deshabilitem botóns i fila seleccionada btnModificar.setDisable(true);
     * btnEliminar.setDisable(true);
     * taulaAssignatura.getSelectionModel().clearSelection();
    }*
     */
}
