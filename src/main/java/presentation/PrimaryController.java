package presentation;

import static java.lang.Integer.parseInt;
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
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
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
import logic.classes.OrderDetails;
import logic.classes.Product;

public class PrimaryController implements Initializable {

    //Logic Layer Classes
    ProductLogic productLogicLayer;
    CustomerLogic customerLogicLayer;
    OrderLogic orderLogicLayer;
    AppConfigLogic appConfigLogic;
    OrderDetailsLogic orderDetailsLogicLayer;

    //orderDetail TableView
    @FXML
    private TabPane tabPane;

    @FXML
    private Tab orderDetailPane;

    @FXML
    private TableView orderDetailTableView;

    @FXML
    private TableColumn colOrderNumDetails, colOrderDetailProductCode, colPriceEach, colQuantity, colOrderLineNumber, colTotalPrice;

    @FXML
    private ComboBox<Product> productComboBox;

    @FXML
    private TableView orderTableView;

    @FXML
    private TableColumn colOrderNum, colOrderDate, colRequiredDate, colShippedDate, colCustomerEmailOrder, colTotalOrderPrice;

    @FXML
    private Button createOrderBtn, addProductBtn, deleteOrderBtn, updateOrderBtn, openOrderBtn, searchRangeBtn;

    @FXML
    private Button orderDetailDeleteBtn, orderDetailUpdateBtn;

    @FXML
    private Label openedOrder;

    @FXML
    private ComboBox<Customer> clientComboBox;

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

            // AppConfig Logic
            appConfigLogic = new AppConfigLogic();
            // Cargamos los datos del OBJETO en la base de datos
            appConfigLogic.setData();
            //Order Logic
            orderLogicLayer = new OrderLogic();
            orderLogicLayer.setData();
            orderTableView.setItems(orderLogicLayer.getOrderObservableList());
            productQuantity.setText(String.valueOf(appConfigLogic.getAppConfig().getDefaultQuantityOrdered()));
            // Product logic
            productLogicLayer = new ProductLogic();
            productLogicLayer.setData();
            productsTableView.setItems(productLogicLayer.getProductObservableList());
            quantityInStockField.setText(String.valueOf(appConfigLogic.getAppConfig().getDefaultQuantityInStock()));
            //Customer Logic
            customerLogicLayer = new CustomerLogic();
            customerLogicLayer.setData();
            tv_customer.setItems(customerLogicLayer.getCustomerObservableList());
            actualizarTvCustomer(customerLogicLayer);
            //OrderDetails Logic
            orderDetailsLogicLayer = new OrderDetailsLogic();
            //ComboBox
            clientComboBox.setItems(customerLogicLayer.getCustomerObservableList());
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
        //colTotalOrderPrice.setCellValueFactory(new PropertyValueFactory<>("totalPrice"));
        // Columnas Product
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
        //Columnas OrderDetails
        colOrderNumDetails.setCellValueFactory(new PropertyValueFactory<>("orderId"));
        colOrderDetailProductCode.setCellValueFactory(new PropertyValueFactory<>("product"));
        colPriceEach.setCellValueFactory(new PropertyValueFactory<>("priceEach"));
        colQuantity.setCellValueFactory(new PropertyValueFactory<>("quantityOrdered"));
        colOrderLineNumber.setCellValueFactory(new PropertyValueFactory<>("orderLineNumber"));
        //colTotalPrice.setCellValueFactory(new PropertyValueFactory<>("orderNumber"));
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
    /*private void showInfo(String txt) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Info:");
        alert.setContentText(txt);

        alert.showAndWait();
    }*/
    // --------- //
    //<editor-fold defaultstate="collapsed" desc="Order Buttons">
    /**
     * Genera una order a partir del formulario y la envía a la BBDD.
     *
     * @param event
     */
    @FXML
    void onActionCreateOrderBtn(ActionEvent event) {

        try {
            Order order = getOrderFromForm();

            orderLogicLayer.insertOrder(order);

            orderLogicLayer.setData();
        } catch (NumberFormatException e) {
            showMessage(1, "Datos incorrectos: " + e);
        } catch (SQLException e) {
            showMessage(1, "Error al insertar los datos: " + e);
        } catch (Exception e) {
            showMessage(1, "Error: " + e);
        }
    }

    /**
     * Si hemos seleccionado un registro de la tabla Order, nos muestra los
     * datos en el formulario.
     *
     * @param ev
     */
    @FXML
    private void handleOrderOnMouseClicked(MouseEvent ev) {

        if (orderTableView.getSelectionModel().getSelectedItem() != null) {

            setOrderToView(getOrderFromTable());

            updateOrderBtn.setDisable(false);
            deleteOrderBtn.setDisable(false);
            createOrderBtn.setDisable(false);
            openOrderBtn.setDisable(false);
        } else {
            disableOrderSelection();
        }
    }

    /**
     * Abre la vista de detalle de pedido del pedido seleccionado.
     *
     * @param event
     */
    @FXML
    void onActionOpenOrderBtn(ActionEvent event) {
        try {
            initializeOrderDetails();

            productComboBox.setDisable(false);
            productQuantity.setDisable(false);
            priceEach.setDisable(false);
            addProductBtn.setDisable(false);

        } catch (SQLException e) {
            showMessage(1, "Error : " + e);
        }
        disableOrderSelection();
        disableOrderDetailSelection();
    }

    /**
     * Elimina el registro seleccionado en la tabla.
     *
     * @param event
     */
    @FXML
    void onActionDeleteOrderBtn(ActionEvent event) {

        try {
            Order order = getOrderFromTable();
            orderLogicLayer.deleteOrder(order);
        } catch (SQLException e) {
            showMessage(1, "Error intentando eliminar los datos: " + e);
        }
        disableOrderSelection();
    }

    /**
     * Modifica una order a partir del formulario y la envía a la BBDD.
     *
     * @param event
     */
    @FXML
    void onActionUpdateOrderBtn(ActionEvent event) {

        try {

            Order order = getOrderFromForm();
            orderLogicLayer.updateOrder(order);
            orderAsTableView(order);

        } catch (NumberFormatException e) {
            showMessage(1, "Dades incorrectes: " + e);
        } catch (SQLException e) {
            showMessage(1, "Error al modificar les dades: " + e);
        } catch (Exception e) {
            showMessage(1, "Error: " + e);
        }
        disableOrderSelection();
    }

    @FXML
    void onActionSearchRangeBtn(ActionEvent event) {

    }

    //</editor-fold>
    // --------- //
    //<editor-fold defaultstate="collapsed" desc="Order Private Methods">
    /**
     * Deshabilita botones, limpia la seleccion del usuario y deja los
     * DatePickers en blanco.
     */
    private void disableOrderSelection() {

        updateOrderBtn.setDisable(true);
        deleteOrderBtn.setDisable(true);
        openOrderBtn.setDisable(true);
        orderTableView.getSelectionModel().clearSelection();

        clientComboBox.valueProperty().set(null);
        orderDate.getEditor().clear();
        requiredDate.getEditor().clear();
        shippedDate.getEditor().clear();
    }

    /**
     * Rellena los campos del formulario con los datos de un objeto Order
     *
     * @param objecte Order
     */
    private void setOrderToView(Order order) {
        if (order != null) {

            orderDate.setValue(order.getOrderDate().toLocalDateTime().toLocalDate());
            requiredDate.setValue(order.getRequiredDate().toLocalDateTime().toLocalDate());
            shippedDate.setValue(order.getShippedDate().toLocalDateTime().toLocalDate());
            clientComboBox.setValue(order.getCustomer());

        }
    }

    /**
     * Refresca la tabla de forma manual tras modificar los atributos del
     * elemento Order.
     *
     * @param order
     */
    private void orderAsTableView(Order order) {

        Order asTableview = getOrderFromTable();

        asTableview.setOrderDate(order.getOrderDate());
        asTableview.setRequiredDate(order.getRequiredDate());
        asTableview.setShippedDate(order.getShippedDate());

        orderTableView.refresh();
    }

    /**
     * Recupera el objeto seleccionado de la tabla Order
     *
     * @return Objecto Order o null si no hay selección
     */
    private Order getOrderFromTable() {

        Order order = (Order) orderTableView.getSelectionModel().getSelectedItem();

        return order;
    }

    /**
     * Obtiene los datos del formulario y retorna un objeto
     *
     * @return Objeto order con los datos
     */
    private Order getOrderFromForm() throws Exception {

        Order order = new Order();

        if (orderDate.getValue() == null || requiredDate.getValue() == null || shippedDate.getValue() == null || clientComboBox.getSelectionModel().getSelectedItem() == null) {
            throw new Exception("No puedes dejar campos en blanco.");
        } else {
            order.setOrderDate(DateConverter.convertToTimestamp(orderDate.getValue()));
            order.setRequiredDate(DateConverter.convertToTimestamp(requiredDate.getValue()));
            order.setShippedDate(DateConverter.convertToTimestamp(shippedDate.getValue()));
            order.setCustomer(clientComboBox.getValue());
            return order;
        }
    }

    //</editor-fold>
    // --------- //
    //<editor-fold defaultstate="collapsed" desc="OrderDetails Buttons">
    @FXML
    void onActionAddProductBtn(ActionEvent event) {

        try {
            OrderDetails detail = getOrderDetailFromForm();

            orderDetailsLogicLayer.insertOrderDetail(detail);

            orderLogicLayer.setData();
        } catch (NumberFormatException e) {
            showMessage(1, "Datos incorrectos: " + e);
        } catch (SQLException e) {
            showMessage(1, "Error al insertar los datos: " + e);
        } catch (Exception e) {
            showMessage(1, "Error: " + e);
        }

        // Deixem el camp de quantitat amb el seu valor per defecte
        productQuantity.setText(String.valueOf(appConfigLogic.getAppConfig().getDefaultQuantityOrdered()));
    }

    @FXML
    void onActionOrderDetailDeleteBtn(ActionEvent event) {

        try {
            OrderDetails detail = getOrderDetailFromTable();
            orderDetailsLogicLayer.deleteOrderDetail(detail);
        } catch (SQLException e) {
            showMessage(1, "Error intentando eliminar los datos: " + e);
        }
        disableOrderDetailSelection();

    }

    @FXML
    void onActionOrderDetailUpdateBtn(ActionEvent event) {

        try {

            OrderDetails detail = getOrderDetailFromForm();
            orderDetailsLogicLayer.updateOrderDetail(detail);
            orderDetailAsTableView(detail);

        } catch (NumberFormatException e) {
            showMessage(1, "Dades incorrectes: " + e);
        } catch (SQLException e) {
            showMessage(1, "Error al modificar les dades: " + e);
        } catch (Exception e) {
            showMessage(1, "Error: " + e);
        }
        disableOrderDetailSelection();
    }

    /**
     * Si hemos seleccionado un registro de la tabla OrderDetails, nos muestra
     * los datos en el formulario.
     *
     * @param ev
     */
    @FXML
    private void handleOrderDetailOnMouseClicked(MouseEvent ev) {

        if (orderDetailTableView.getSelectionModel().getSelectedItem() != null) {

            setOrderDetailToView(getOrderDetailFromTable());

            orderDetailUpdateBtn.setDisable(false);
            orderDetailDeleteBtn.setDisable(false);
        } else {
            disableOrderDetailSelection();
        }
    }

    //</editor-fold>
    // --------- //
    //<editor-fold defaultstate="collapsed" desc="OrderDetails Private Methods">
    /**
     * Guarda el número de pedido, carga los productos en el ComboBox y muestra
     * los productos añadidos al pedido, después cambia de pestaña a Detalle de
     * pedido.
     *
     * @throws SQLException
     */
    private void initializeOrderDetails() throws SQLException {

        Order order = getOrderFromTable();
        int orderNumber = order.getOrderNumber();

        openedOrder.setText("" + orderNumber);
        productComboBox.setItems(productLogicLayer.getProductObservableList());
        orderDetailsLogicLayer.setData(orderNumber);
        orderDetailTableView.setItems(orderDetailsLogicLayer.getOrderDetailsObservableList());
        tabPane.getSelectionModel().select(orderDetailPane);
    }

    /**
     * Refresca la tabla de forma manual tras modificar los atributos del
     * elemento Order.
     *
     * @param order
     */
    private void orderDetailAsTableView(OrderDetails detail) {

        OrderDetails asTableview = getOrderDetailFromTable();

        asTableview.setPriceEach(detail.getPriceEach());
        asTableview.setQuantityOrdered(detail.getQuantityOrdered());

        orderDetailTableView.refresh();
    }

    /**
     * Deshabilita botones, limpia la seleccion del usuario y los text fields
     * los deja en blanco.
     */
    private void disableOrderDetailSelection() {

        orderDetailUpdateBtn.setDisable(true);
        orderDetailDeleteBtn.setDisable(true);
        orderDetailTableView.getSelectionModel().clearSelection();

        productComboBox.valueProperty().set(null);
        productQuantity.clear();
        priceEach.clear();
    }

    /**
     * Obtiene los datos del formulario y retorna un objeto
     *
     * @return Objeto orderDetails con los datos
     */
    private OrderDetails getOrderDetailFromForm() throws Exception {

        OrderDetails detail = getOrderDetailFromTable();

        if (productQuantity.getText().isEmpty() || priceEach.getText().isEmpty() || productComboBox.getSelectionModel().getSelectedItem() == null) {
            throw new Exception("No puedes dejar campos en blanco.");
        } else {
            detail.setQuantityOrdered(Integer.parseInt(productQuantity.getText()));
            detail.setPriceEach(Double.parseDouble(priceEach.getText()));

            return detail;
        }
    }

    /**
     * Recupera el objeto seleccionado de la tabla orderDetails
     *
     * @return Objecto Order o null si no hay selección
     */
    private OrderDetails getOrderDetailFromTable() {

        OrderDetails detail = (OrderDetails) orderDetailTableView.getSelectionModel().getSelectedItem();

        return detail;
    }

    /**
     * Rellena los campos del formulario con los datos de un objeto OrderDetail
     *
     * @param object OrderDetails
     */
    private void setOrderDetailToView(OrderDetails detail) {
        if (detail != null) {

            productQuantity.setText(Integer.toString(detail.getQuantityOrdered()));
            priceEach.setText(Double.toString(detail.getPriceEach()));
            productComboBox.setValue(detail.getProduct());
        }
    }

    //</editor-fold>
    // --------- //
    //<editor-fold defaultstate="collapsed" desc="Botones Products">
    /**
     * Envia a la capa logica el producto a editar seleccionado desde la
     * observableList
     *
     * @param event
     * @throws SQLException
     */
    @FXML
    void onActionUpdateProductBtn(ActionEvent event) throws SQLException {
        try {
            Product product = getProductFromView();
            productLogicLayer.checkProductEmptyFields(product);
            productLogicLayer.editProduct(product);

            // Actualizar la vista
            productLogicLayer.setData();
            productsTableView.setItems(productLogicLayer.getProductObservableList());
        } catch (NumberFormatException e) {
            showMessage(1, "Los campos de Stock y Precio son numericos y no pueden estar en blanco.");
        } catch (Exception e) {
            showMessage(1, e.getMessage());
        }
    }

    /**
     * Envia los valores de los campos de la nueva entrada a la capa logica
     *
     * @param event
     * @throws SQLException
     */
    @FXML
    void onActionAddNewProductBtn(ActionEvent event) throws SQLException {
        try {
            Product product = getProductFromView();
            productLogicLayer.checkProductEmptyFields(product);
            productLogicLayer.addProduct(product);

            // Actualizar la tabla
            productLogicLayer.setData();
            productsTableView.setItems(productLogicLayer.getProductObservableList());
        } catch (NumberFormatException e) {
            showMessage(1, "Los campos de Stock y Precio son numericos y no pueden estar en blanco.");
        } catch (Exception e) {
            showMessage(1, e.getMessage());
        }
    }

    /**
     * Envia la entrada seleccionada que se quiere eliminar a la capa logica
     *
     * @param event
     */
    @FXML
    void onActionDeleteProductBtn(ActionEvent event) {
        // Intenta eliminar la entrada. Si falla, muestra un mensaje con el error
        try {
            Product product = getProductFromTable();
            productLogicLayer.removeProduct(product);
        } catch (SQLException e) {
            showMessage(1, "Error al eliminar la entrada: " + e);
        } catch (NullPointerException e) {
            showMessage(1, "No hay ninguna entrada seleccionada.");
        }
    }

    /**
     * Desactiva los botones i limpia los campos de edicion
     *
     * @param event
     */
    @FXML
    void onActionCleanFieldsBtn(ActionEvent event) {
        // Desactivar botones i seleccion de la tabla
        updateProductBtn.setDisable(true);
        deleteProductBtn.setDisable(true);
        addNewProductBtn.setDisable(false);
        productsTableView.getSelectionModel().clearSelection();

        // Limpiar los campos de edicion
        productCodeField.clear();
        productNameField.clear();
        productDescriptionField.clear();
        quantityInStockField.setText(String.valueOf(appConfigLogic.getAppConfig().getDefaultQuantityInStock()));
        buyPriceField.clear();
    }

    //</editor-fold>
    // --------- //
    //<editor-fold defaultstate="collapsed" desc="Metodes Privats Productes">
    /**
     * Obtiene los valores de los campos
     *
     * @return
     * @throws NumberFormatException
     */
    private Product getProductFromView() throws NumberFormatException {
        Product product = new Product();

        // Si estamos modificando una entrada, captura el codigo del producto
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
     * Comprueba si hemos seleccionado una entrada de la tabla i envia los
     * valores a los campos de edicion para modificar o eliminar esta
     *
     * @param ev
     */
    @FXML
    private void handleProductMouseCicked(MouseEvent ev) {
        if (productsTableView.getSelectionModel().getSelectedItem() != null) {
            setProductToView(getProductFromTable());
            updateProductBtn.setDisable(false);
            deleteProductBtn.setDisable(false);
            addNewProductBtn.setDisable(true);
        }
    }

    /**
     * Envia los valores del producto seleccionado a los campos de edicion
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
     * Obtiene el producto de la tabla
     *
     * @return
     */
    private Product getProductFromTable() {
        Product product = (Product) productsTableView.getSelectionModel().getSelectedItem();
        return product;
    }

    //</editor-fold>
    // --------- //
    //CUSTOMER 
    @FXML
    private Button bt_aniadir, bt_actualizar, bt_eliminar, bt_limpiar;
    @FXML
    private TableView tv_customer;
    @FXML
    private TableColumn col_customerName, col_idCard, col_creditLimit, col_phoneNumber, col_customerEmail, col_birthDate;
    @FXML
    private TextField tf_customerName, tf_idCard, tf_creditLimit, tf_phoneNumber, tf_customerEmail, tf_birthDate;

    //<editor-fold defaultstate="collapsed" desc="Botons CUSTOMER">
    /**
     * Botón de añadir APARTADO CLIENTE
     *
     * @param event
     */
    @FXML
    void onClick_bt_aniadir(ActionEvent event) {

        //Aquí obtenemos la mínima edad de la bdd y mira si es superior o igual
        try {
            if (comparadorEdades(appConfigLogic.getAppConfig())) {
                // Comprobar que el DNI i el correo son validos
                customerLogicLayer.checkDni(getCustomerFromView().getIdCard());
                customerLogicLayer.checkEmail(getCustomerFromView().getCustomerEmail());
                //Escribimos los datos de los texts fields a la base de datos
                customerLogicLayer.afegirCustomer(getCustomerFromView());
                actualizarTvCustomer(customerLogicLayer);
            } else {
                showMessage(0, "La mínima edad es de " + appConfigLogic.getAppConfig().getMinCustomerAge() + " años");
            }
        } catch (SQLException ex) {
            if (primaryKeyRepetida()) {
                showMessage(1, "Solo se puede añadir un correo electrónico, revise la tabla");
            }
            if (dniRepetido()) {
                showMessage(1, "Solo se puede añadir un DNI, revise la tabla");
            }
        } catch (Exception ex) {
            showMessage(0, ex.getMessage());
        }
    }

    /**
     * Botón Actualizar datos de la tabla en los text fields Tiene un control de
     * excepciones APARTADO CLIENTE
     *
     * @param event
     * @throws Exception
     */
    @FXML
    void onClick_bt_actualizar(ActionEvent event) throws Exception {
        try {
            if (comparadorEdades(appConfigLogic.getAppConfig())) {
                // Comprobar que el DNI es valido
                customerLogicLayer.checkDni(getCustomerFromView().getIdCard());
                customerLogicLayer.modificarCustomer(getCustomerFromView());
                //Para actualizar la página
                customerLogicLayer.setData();
            } else {
                showMessage(0, "La mínima edad es de " + appConfigLogic.getAppConfig().getMinCustomerAge() + " años");
            }
        } catch (SQLException exception) {
            if (dniRepetido()) {
                showMessage(1, "Solo se puede añadir un DNI, revise la tabla");
            }
        } catch (Exception e) {
            showMessage(0, e.getMessage());
        }
    }

    /**
     * Botón de eliminar APARTADO CLIENTE
     *
     * @param event
     */
    @FXML
    void onClick_bt_eliminar(ActionEvent event) {
        // Capturamos el objeto de la base de datos
        Customer customer = getCustomerFromTable();
        try {
            customerLogicLayer.eliminarCustomer(customer);
        } catch (SQLException e) {
            showMessage(1, "Error al eliminar los datos: " + e);
        }
    }

    /**
     * Limpia los text fields y habilita el text field de email, APARTADO
     * CLIENTE
     *
     * @param event
     */
    @FXML
    private void onClick_bt_limpiar(ActionEvent event) {
        desactivaSeleccioCustomer();
        limpiarRegistroTfCustomer();
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Metodes privats CUSTOMER">
    /**
     * Al hacer clic si existe algún registro en table view, carga la
     * información en los texts fields y deshabilita el text field de email ya
     * que es la primary key para que no se pueda actualizar. APARTADO CLIENTE
     *
     * @param ev
     */
    @FXML
    private void handleOnMouseClicked(MouseEvent ev) {
        // Si hemos seleccionado algún registro de la tabla
        if (tv_customer.getSelectionModel().getSelectedItem() != null) {
            // Añadimos los datos del objeto seleccionado y los traspasamos
            // a los campos del formulario
            setCustomerToView(getCustomerFromTable());
            //Habilitamos el botón, modificar y eliminar
            activarSeleccioCustomer();
        }
    }

    /**
     * Esta función rellena los texts fields con el contenido del objeto
     * APARTADO CLIENTE
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
     * Guarda información a un objeto del sitio donde se encuentra el objeto
     * seleccionado APARTADO CLIENTE
     *
     * @return
     */
    private Customer getCustomerFromTable() {
        Customer customer = null;
        customer = (Customer) tv_customer.getSelectionModel().getSelectedItem();
        return customer;
    }

    /**
     * Función obtener cliente de los text fields APARTADO CLIENTE
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
     * Habilita botones y la el text field del mail APARTADO CLIENTE
     */
    private void activarSeleccioCustomer() {
        tf_customerEmail.setDisable(true);
        bt_actualizar.setDisable(false);
        bt_eliminar.setDisable(false);
    }

    /**
     * Limpia los text fields y setea el default APARTADO CLIENTE
     */
    private void limpiarRegistroTfCustomer() {
        tf_birthDate.clear();
        defaultValorLimitCredit(appConfigLogic.getAppConfig());
        tf_customerEmail.clear();
        tf_customerName.clear();
        tf_idCard.clear();
        tf_phoneNumber.clear();
    }

    /**
     * Deshabilita botones y fila seleccionada Limpia los texts Fields APARTADO
     * CLIENTE
     */
    private void desactivaSeleccioCustomer() {
        tf_customerEmail.setDisable(false);
        bt_actualizar.setDisable(true);
        bt_eliminar.setDisable(true);
        tv_customer.getSelectionModel().clearSelection();
    }

    /**
     * Compara si la edad del cliente es mayor a la edad mínima de appconfig
     * APARTADO CLIENTE
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
     * Añade todo el contenido de la base de datos a la ObservableList y después
     * añade esta lista al Table View APARTADO CLIENTE
     *
     * @param customerLogicLayer
     * @throws SQLException
     */
    private void actualizarTvCustomer(CustomerLogic customerLogicLayer) throws SQLException {
        customerLogicLayer.setData();
        tv_customer.setItems(customerLogicLayer.getCustomerObservableList());
    }

    /**
     * Esta función setea el text field de creditLimit al valor del objeto
     * appConfig defaultCreditLimit APARTADO CLIENTE
     *
     * @param appConfig
     */
    private void defaultValorLimitCredit(AppConfig appConfig) {
        tf_creditLimit.setText(String.valueOf(appConfig.getDefaultCreditLimit()));
    }

//</editor-fold>    
    //<editor-fold defaultstate="collapsed" desc="Varis CUSTOMER">
    /**
     * Comprueba si hay un correo repetido en el textfield y el tableview
     * APARTADO CLIENTE
     *
     * @return
     */
    private Boolean primaryKeyRepetida() {
        //En la parte izquierda del if obtenemos los datos del textflied y en la parte derecha obtenemos los datos de la observableList
        for (int i = 0; i < customerLogicLayer.getCustomerObservableList().size(); i++) {
            if (getCustomerFromView().getCustomerEmail().equals(customerLogicLayer.getCustomerObservableList().get(i).getCustomerEmail())) {
                return true;
            }
        }
        return false;
    }

    /**
     * Comprueba si hay un DNI repetido en el textfield y el tableview APARTADO
     * CLIENTE
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

//</editor-fold>
}
