package presentation;

import static java.lang.Integer.parseInt;
import java.net.URL;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Month;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.application.Platform;
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
import logic.OrderDetailsLogic;
import logic.OrderLogic;
import logic.classes.Order;
import logic.AppConfigLogic;
import logic.CustomerLogic;
import logic.OrderDetailsLogic;
import logic.OrderLogic;
import logic.classes.Order;
import logic.ProductLogic;
import logic.classes.AppConfig;
import logic.classes.Customer;
import logic.classes.Product;

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
    private Button cleanFieldsBtn;
    
    @FXML
    private TextField productCodeField;

    @FXML
    private TextField productNameField;

    @FXML
    private TextField productDescriptionField;
    
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
            // AppConfig Logic
            appConfigLogic = new AppConfigLogic();
            // Cargamos los datos del OBJETO en la base de datos
            appConfigLogic.setData();
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
        //desactivaSeleccio();
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
    
    /* 
    Funcions productes
    */

    /**
     * Envia a la capa logica el producte a editar seleccionat des-de el
     * observableList
     * 
     * @param event
     * @throws SQLException 
     */
    @FXML
    void onActionUpdateProductBtn(ActionEvent event) throws SQLException {
        productLogicLayer.editProduct(getProductFromView());
        
        // Actualitzar la vista
        productLogicLayer.setData();
        productsTableView.setItems(productLogicLayer.getProductObservableList());
    }

    /**
     * Envia els valors dels camps a la capa lógica
     * 
     * @param event
     * @throws SQLException 
     */
    @FXML
    void onActionAddNewProductBtn(ActionEvent event) throws SQLException {
        productLogicLayer.addProduct(getProductFromView());
        
        // Actualitzar la taula
        productLogicLayer.setData();
        productsTableView.setItems(productLogicLayer.getProductObservableList());
    }
    
    /**
     * Envia l'entrada seleccionada que es vol eliminar de la taula a la capa
     * logica
     * 
     * @param event 
     */
    @FXML 
    void onActionDeleteProductBtn(ActionEvent event) {
        Product product = getProductFromTable();
        
        // Intenta eliminar l'entrada. Si falla, mostra un missatge amb l'error
        try {
            productLogicLayer.removeProduct(product);
        } catch (SQLException e) {
            showMessage(1, "Error eliminant l'entrada: " + e);
        }
    }
    
    /**
     * Desactiva botons i elimina dades als camps d'edicio
     * 
     * @param event 
     */
    @FXML
    void onActionCleanFieldsBtn(ActionEvent event) {
        // Desactivar botons i deseleccionar entrada de la taula
        updateProductBtn.setDisable(true);
        deleteProductBtn.setDisable(true);
        productsTableView.getSelectionModel().clearSelection();
        
        // Esborrar dades als camps d'edició
        productCodeField.clear();
        productNameField.clear();
        productDescriptionField.clear();
        quantityInStockField.clear();
        buyPriceField.clear();
    }
    
    /**
     * Obté els valors dels camps
     * 
     * @return
     * @throws NumberFormatException 
     */
    private Product getProductFromView() throws NumberFormatException {
        Product product = new Product();
        
        // Si estem modificant una entrada, existira un codi
        if (!productCodeField.getText().equals("")) {
            product.setProductCode(parseInt(productCodeField.getText()));
        }
        product.setProductName(productNameField.getText());
        product.setProductDescription(productDescriptionField.getText());
        product.setQuantityInStock(parseInt(quantityInStockField.getText()));
        product.setBuyPrice(Double.parseDouble(buyPriceField.getText()));
        
        return product;
    }
    
    /**
     * Comprova si hem seleccionat una entrada de la taula i envia els valors
     * als camps d'edició per a modificar o eliminar aquesta.
     * 
     * @param ev 
     */
    @FXML
    private void handleProductMouseCicked(MouseEvent ev) {
        if (productsTableView.getSelectionModel().getSelectedItem() != null) {
            setProductToView(getProductFromTable());
            updateProductBtn.setDisable(false);
            deleteProductBtn.setDisable(false);
        }
    }
    
    /**
     * Envia els valors del camp de la taula seleccionat als camps d'edició
     * 
     * @param product 
     */
    private void setProductToView(Product product) {
        if (product != null) {
            productCodeField.setText(String.valueOf(product.getProductCode()));
            productNameField.setText(product.getProductName());
            productDescriptionField.setText(product.getProductDescription());
            quantityInStockField.setText(String.valueOf(product.getQuantityInStock()));
            buyPriceField.setText(String.valueOf(product.getBuyPrice()));
        }
    }
    
    /**
     * Obte el producte de la taula
     * 
     * @return 
     */
    private Product getProductFromTable() {
        Product product = (Product) productsTableView.getSelectionModel().getSelectedItem();
        return product;
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

    @FXML
    void onClick_bt_aniadir(ActionEvent event) throws SQLException {

        AppConfig appConfig = appConfigLogic.getAppConfig();
        //Aqui obtenim la minima edat i si es superior o igual entra al if i escui a la base de dades
        if (appConfig.getMinCustomerAge() > calcularEdat(getCustomerFromView())) {
            customerLogicLayer.afegirCustomer(getCustomerFromView());

            //Para actualizar la pagina
            customerLogicLayer.setData();
            tv_customer.setItems(customerLogicLayer.getCustomerObservableList());
        }

    }

    public int calcularEdat(Customer customer) {
        //Creamos un formato de fecha 
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        //A traves del parse pasamos la fecha al formato y le pasamos la fecha de la base de datos y el formato al que la queremos pasar
        LocalDate fechaNac = LocalDate.parse(customer.getBirthDate(), fmt);

        //Creamos una variable donde le metemos la fecha de hoy
        LocalDate ahora = LocalDate.now();

        //Utilizamos el metodo period para crear un objeto que nos restara dos fechas y nos obtendra años, meses y dias.
        Period periodo = Period.between(fechaNac, ahora);

        //Esta variable obtendra la edad de la persona.
        return periodo.getYears();
    }

    @FXML
    void onClick_bt_actualizar(ActionEvent event) throws Exception {
        customerLogicLayer.modificarCustomer(getCustomerFromView());

        //Para actualizar la pagina
        customerLogicLayer.setData();
        tv_customer.setItems(customerLogicLayer.getCustomerObservableList());
    }

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
            //Desabilitem el boto del mail al ser la primarykey i el boto añadir ja que no podem afeguir si actualitzem
            tf_customerEmail.setDisable(true);
            // agafem les dades de l'objecte seleccionat i els traspassem
            // als camps del formulari
            setCustomerToView(getCustomerFromTable());

            //habilitem botó de modificar i eliminar
            bt_actualizar.setDisable(false);
            bt_eliminar.setDisable(false);
        } else {
            //desactivaSeleccio();
        }
    }

    @FXML
    private void onClick_bt_limpiar(ActionEvent event) {
        //Habilitem el boto del mail
        tf_customerEmail.setDisable(false);

        //limpiamos registro y desactivamos el click de la pantalla
        desactivaSeleccio();

        //seteamos todos los registros a vacio
        tf_birthDate.clear();
        tf_creditLimit.clear();
        tf_customerEmail.clear();
        tf_customerName.clear();
        tf_idCard.clear();
        tf_phoneNumber.clear();
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

    private Customer getCustomerFromTable() {
        Customer customer = null;

        customer = (Customer) tv_customer.getSelectionModel().getSelectedItem();

        return customer;
    }

    private void desactivaSeleccio() {
        //deshabilitem botóns i fila seleccionada
        bt_actualizar.setDisable(true);
        bt_eliminar.setDisable(true);
        tv_customer.getSelectionModel().clearSelection();
    }
}
