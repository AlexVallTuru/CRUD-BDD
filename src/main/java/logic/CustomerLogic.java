/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package logic;

import data.ConnectionDB;
import data.CustomerDB;
import java.sql.Connection;
import java.sql.SQLException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import logic.classes.Customer;
import logic.classes.AppConfig;

/**
 *
 * @author Alex
 */
public class CustomerLogic {

    //Objeto de conección BDD
    Connection conn;

    //Llista observable de objetos de la classe Customer
    ObservableList<Customer> llistaObservableCustomer;

    /**
     * Constructor capa lógica
     *
     * @throws SQLException
     */
    public CustomerLogic() throws SQLException {
        // Inicializamos connexió amb BD

        conn = ConnectionDB.getInstance().getConnection();
        // Inicializamos colección
        llistaObservableCustomer = FXCollections.<Customer>observableArrayList();
    }

    /**
     * Rellena la ObservableList con los registros de la tabla
     *
     * @throws SQLException
     */
    public void carregarCustomer() throws SQLException {

        this.llistaObservableCustomer.setAll(CustomerDB.carregarCustomer(conn));

    }

    /**
     * Añade a una persona
     *
     * @throws SQLException
     */
    public void afegirCustomer(Customer customer) throws SQLException, Exception {
        if (!validaNullsCustomer(customer)) {
            CustomerDB.insereixNouCustomer(conn, customer);
            llistaObservableCustomer.add(customer);
        } else {
            throw new Exception ("No se pueden dejar campos vacios");
        }
    }

    /**
     * Elimina un Customer
     *
     * @param as
     * @throws SQLException
     */
    public void eliminarCustomer(Customer customer) throws SQLException {

        CustomerDB.eliminaCustomer(conn, customer);
        // Eliminamos el objeto de la ObservableList
        llistaObservableCustomer.remove(customer);
    }

    /**
     * Cierra la conección con la BDD
     *
     * @throws SQLException
     */
    public void tancarConnexio() throws SQLException {
        this.conn.close();
    }

    /**
     * Añade el objeto a la ObservableList
     * 
     * @throws SQLException 
     */
    public void setData() throws SQLException {
        this.llistaObservableCustomer.setAll(CustomerDB.carregarCustomer(conn));
    }

    /**
     * Retorna la ObservableList
     * 
     * @return 
     */
    public ObservableList<Customer> getCustomerObservableList() {
        return llistaObservableCustomer;
    }

    /**
     * Modifica los datos del objeto seleccionado
     * 
     * @param customer
     * @throws SQLException
     * @throws Exception 
     */
    public void modificarCustomer(Customer customer) throws SQLException, Exception {

        CustomerDB.modificaCustomer(conn, customer);
    }
    
    /**
     * Metodo extra para que todos los usuarios tengan todos los campos rellenados
     * Credit Limit se controla con una excepción, igual que DNI
     * 
     * @param customer
     * @return 
     */
    public boolean validaNullsCustomer(Customer customer) {
        if (customer.getBirthDate().isBlank() || customer.getCustomerEmail().isBlank() || customer.getCustomerName().isBlank() || customer.getPhoneNumber().isBlank() || customer.getIdCard().isBlank()) {
            return true;
        }
        return false;
    }

    /**
     * Calcula la edad de la persona
     * 
     * @throws SQLException
     * @throws Exception 
     */
    public void calcularEdad() throws SQLException, Exception {
        AppConfig appconfig = null;

        appconfig.getMinCustomerAge();

    }

}
