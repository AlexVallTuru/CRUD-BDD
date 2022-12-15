package presentation;

import java.lang.reflect.InvocationTargetException;
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
import logic.DateConverter;
import logic.AppConfigLogic;
import logic.CustomerLogic;
import logic.OrderDetailsLogic;
import logic.OrderLogic;
import logic.classes.Order;
import logic.ProductLogic;
import logic.classes.AppConfig;
import logic.classes.Customer;

public class PrimaryController implements Initializable {

    ProductLogic productLogicLayer;

    CustomerLogic customerLogicLayer;
    OrderLogic orderLogicLayer;

    AppConfigLogic appConfigLogic;
    OrderDetailsLogic orderDetailsLogicLayer;

    @FXML
    private TableView orderDetailTableView;

    @FXML
    private TableColumn colOrderNumDetails, colOrderDetailProductName, colPriceEach, colQuantity, colOrderLineNumber, colTotalPrice;

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

    /**
     * Elements productes
     */
    @FXML
    private TableView productsTableView;

    @FXML
    private TableColumn colProductCode, colProductName, colProductDescription, colQuantityInStock, colBuyPrice;

    @FXML
    private Button updateProductBtn;

    @FXML
    private Button addNewProductBtn;

    @FXML
    private Button deleteProductBtn;

    @FXML
    private TextField productCodeField;

    @FXML
    private TextField productNameField;

    @FXML
    private TextField productDescripcionField;

    @FXML
    private TextField quantityInStockField;

    @FXML
    private TextField buyPriceField;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //Inicializa la capa lógica, que incluye la conexión con la BBDD
        try {

            //ComboBox
            //clientComboBox.setItems();
            //productComboBox.setItems();
            //Order Logic
            orderLogicLayer = new OrderLogic();
            orderLogicLayer.setData();
            orderTableView.setItems(orderLogicLayer.getOrderObservableList());
            // Product logic
            productLogicLayer = new ProductLogic();
            productLogicLayer.setData();
            productsTableView.setItems(productLogicLayer.getProductObservableList());
            //Customer Logic
            customerLogicLayer = new CustomerLogic();
            actualizarTvCustomer(customerLogicLayer);
            //AppConfig Logic
            appConfigLogic = new AppConfigLogic();
            //Cargamos los datos del OBJETO en la base de datos
            appConfigLogic.setData();
            //Poner el DefaultCredit como valor por defecto
            defaultValorLimitCredit(appConfigLogic.getAppConfig());
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
        // Columnes Product
        colProductCode.setCellValueFactory(new PropertyValueFactory<>("productCode"));
        colProductName.setCellValueFactory(new PropertyValueFactory<>("productName"));
        colProductDescription.setCellValueFactory(new PropertyValueFactory<>("productDescription"));
        colQuantityInStock.setCellValueFactory(new PropertyValueFactory<>("quantityInStock"));
        colBuyPrice.setCellValueFactory(new PropertyValueFactory<>("buyPrice"));
        //Columnas Costumer
        col_customerEmail.setCellValueFactory(new PropertyValueFactory<>("customerEmail"));
        col_idCard.setCellValueFactory(new PropertyValueFactory<>("idCard"));
        col_customerName.setCellValueFactory(new PropertyValueFactory<>("customerName"));
        col_phoneNumber.setCellValueFactory(new PropertyValueFactory<>("phoneNumber"));
        col_birthDate.setCellValueFactory(new PropertyValueFactory<>("birthDate"));
        col_creditLimit.setCellValueFactory(new PropertyValueFactory<>("creditLimit"));
    }

    /**
     * Muestra una ventana con un mensaje
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

        //Cargar lista de objetos con setItems
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

        // capturem les noves dades
        Order order = getOrderFromView();

        try {
            orderLogicLayer.insertOrder();
        } catch (NumberFormatException e) {
            showMessage(1, "Dades incorrectes: " + e);
        } catch (SQLException e) {
            showMessage(1, "Error a l'inserir les dades: " + e);
        }

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

    /**
     * Recupera les dades del formulari
     *
     * @return Objecte order amb les dades
     * @throws NumberFormatException
     */
    private Order getOrderFromView() throws NumberFormatException {
        Order order = new Order();

        order.setOrderDate(DateConverter.convertToDate(orderDate.getValue()));
        order.setRequiredDate(DateConverter.convertToDate(requiredDate.getValue()));
        order.setShippedDate(DateConverter.convertToDate(shippedDate.getValue()));
        order.setCustomer(clientComboBox.getEditor().toString());

        return order;
    }

    // Funcions productes
    @FXML
    void onActionUpdateProductBtn(ActionEvent event) {

    }

    @FXML
    void onActionAddNewProductBtn(ActionEvent event) {

    }

    @FXML
    void onActionDeleteProductBtn(ActionEvent event) {

    }

    //CUSTOMER 
    @FXML
    private Button bt_aniadir, bt_actualizar, bt_eliminar, bt_limpiar;
    @FXML
    private TableView tv_customer;
    @FXML
    private TableColumn col_customerName, col_idCard, col_creditLimit, col_phoneNumber, col_customerEmail, col_birthDate;
    @FXML
    private TextField tf_customerName, tf_idCard, tf_creditLimit, tf_phoneNumber, tf_customerEmail, tf_birthDate;

    /**
     * Boton de añadir del apartado CLIENTE
     *
     * @param event
     */
    @FXML
    void onClick_bt_aniadir(ActionEvent event) {

        //Aqui obtenim la minima edat de la bdd i mira si es superior o igual
        try {
            if (comparadorEdades(appConfigLogic.getAppConfig())) {
                //Escrivim les dades dels texts fields a la base de dades
                customerLogicLayer.afegirCustomer(getCustomerFromView());
                actualizarTvCustomer(customerLogicLayer);
            } else {
                showMessage(0, "La minima edad es de " + appConfigLogic.getAppConfig().getMinCustomerAge() + " años");
            }
        } catch (SQLException exception) {
            if (primaryKeyRepetida()) {
                showMessage(1, "Solo se puede añadir un correo electronico, revise la tabla");
            }
            if (dniRepetido()) {
                showMessage(1, "Solo se puede añadir un DNI, revise la tabla");
            }
        }
    }

    /**
     * Comprueba si hay un correo repetido en el textfield y el tableview
     *
     * @return
     */
    private Boolean primaryKeyRepetida() {
        //En la parte izquiera del if obtenemos los datos del textflied y en la parte derecha obtenemos los datos de la observableList
        for (int i = 0; i < customerLogicLayer.getCustomerObservableList().size(); i++) {
            if (getCustomerFromView().getCustomerEmail().equals(customerLogicLayer.getCustomerObservableList().get(i).getCustomerEmail())) {
                return true;
            }
        }
        return false;
    }

    /**
     * Comprueba si hay un dni en el textfield y el tableview
     *
     * @return
     */
    private Boolean dniRepetido() {
        //En la parte izquiera del if obtenemos los datos del textflied y en la parte derecha obtenemos los datos de la observableList
        for (int i = 0; i < customerLogicLayer.getCustomerObservableList().size(); i++) {
            if (getCustomerFromView().getIdCard().equals(customerLogicLayer.getCustomerObservableList().get(i).getIdCard())) {
                return true;
            }
        }
        return false;
    }

    /**
     * Compara si la edad
     *
     * @return
     */
    private Boolean comparadorEdades(AppConfig appConfig) {
        if (appConfigLogic.calcularEdat(getCustomerFromView()) >= appConfig.getMinCustomerAge()) {
            return true;
        }
        return false;
    }

    /**
     * Boton Actualizar de la t
     *
     * @param event
     * @throws Exception
     */
    @FXML
    void onClick_bt_actualizar(ActionEvent event) throws Exception {
        try {
            if (comparadorEdades(appConfigLogic.getAppConfig())) {
                customerLogicLayer.modificarCustomer(getCustomerFromView());
                //Para actualizar la pagina
                actualizarTvCustomer(customerLogicLayer);
            } else {
                showMessage(0, "La minima edad es de " + appConfigLogic.getAppConfig().getMinCustomerAge() + " años");
            }
        } catch (SQLException exception) {
            if (dniRepetido()) {
                showMessage(1, "Solo se puede añadir un DNI, revise la tabla");
            }
        }
    }

    /**
     * Boton de eliminar del apartado CLIENTE
     *
     * @param event
     */
    @FXML
    void onClick_bt_eliminar(ActionEvent event) {
        // capturem l'objecte seleccionat a la taula
        Customer customer = getCustomerFromTable();
        try {
            customerLogicLayer.eliminarCustomer(customer);
        } catch (SQLException e) {
            showMessage(1, "Error al eliminar les dades: " + e);
        }
    }

    /**
     * Funcion obtener CLIENTE de los text fields
     *
     * @return @throws NumberFormatException
     */
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

    /**
     * Funcion encargada de seleccionar los datos del tableView al realizar un
     * click y pasarlos a los textsFields
     *
     * @param ev
     */
    @FXML
    private void handleOnMouseClicked(MouseEvent ev) {
        // si hem seleccionat un registre de la taula
        if (tv_customer.getSelectionModel().getSelectedItem() != null) {
            //Desabilitem el boto del mail al ser la primarykey i el boto añadir ja que no podem afeguir si actualitzem

            // agafem les dades de l'objecte seleccionat i els traspassem
            // als camps del formulari
            setCustomerToView(getCustomerFromTable());
            //Habilitem botó de modificar i eliminar
            activarSeleccioCustomer();
        }
    }

    /**
     * Boton encargado de limpiar los textos de los texts fields
     *
     * @param event
     */
    @FXML
    private void onClick_bt_limpiar(ActionEvent event) {
        desactivaSeleccioCustomer();
        limpiarRegistroTfCustomer();
    }

    /**
     * Esta funcion rellena los texts fields con el contenido del objecto
     *
     * @param customer
     */
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

    /**
     *
     * @return
     */
    private Customer getCustomerFromTable() {
        Customer customer = null;
        customer = (Customer) tv_customer.getSelectionModel().getSelectedItem();
        return customer;
    }

    private void defaultValorLimitCredit(AppConfig appConfig) {
        tf_creditLimit.setText(String.valueOf(appConfig.getDefaultCreditLimit()));
    }

    //deshabilitem botóns i fila seleccionada i netajem tf
    private void desactivaSeleccioCustomer() {
        tf_customerEmail.setDisable(false);
        bt_actualizar.setDisable(true);
        bt_eliminar.setDisable(true);
        tv_customer.getSelectionModel().clearSelection();
    }

    //Habilitem botóns i fila seleccionada
    private void activarSeleccioCustomer() {
        tf_customerEmail.setDisable(true);
        bt_actualizar.setDisable(false);
        bt_eliminar.setDisable(false);
    }

    //Limpia los text fields y setea el default
    private void limpiarRegistroTfCustomer() {
        tf_birthDate.clear();
        defaultValorLimitCredit(appConfigLogic.getAppConfig());
        tf_customerEmail.clear();
        tf_customerName.clear();
        tf_idCard.clear();
        tf_phoneNumber.clear();
    }

    /**
     * Añade todo el contenido de la base de datos a la ObservableList y despues
     * añade esta lista al Table View
     *
     * @param customerLogicLayer
     * @throws SQLException
     */
    private void actualizarTvCustomer(CustomerLogic customerLogicLayer) throws SQLException {
        customerLogicLayer.setData();
        tv_customer.setItems(customerLogicLayer.getCustomerObservableList());
    }
}
