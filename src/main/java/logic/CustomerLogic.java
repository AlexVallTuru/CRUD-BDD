/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package logic;

import data.ConnectionDB;
import data.CustomerDB;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.regex.Pattern;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import logic.classes.Customer;
import logic.classes.AppConfig;

/**
 *
 * @author Alex
 */
public class CustomerLogic {

    //Objecte connexió a la BBDD
    Connection conn;

    //llista observable d'objectes de la classe Customer
    ObservableList<Customer> llistaObservableCustomer;

    /**
     * Contructor capa lógica
     *
     * @throws SQLException
     */
    public CustomerLogic() throws SQLException {
        // inicialitzem connexió amb BD

        conn = ConnectionDB.getInstance().getConnection();
        // inicialitzem col.lecció
        llistaObservableCustomer = FXCollections.<Customer>observableArrayList();
    }

    /**
     * Omple la llistaObservable amb els registres de la taula
     *
     * @throws SQLException
     */
    public void carregarCustomer() throws SQLException {

        this.llistaObservableCustomer.setAll(CustomerDB.carregarCustomer(conn));

    }

    /**
     * Afegeix una persona
     *
     * @throws SQLException
     */
    public void afegirCustomer(Customer customer) throws SQLException {
        
        CustomerDB.insereixNouCustomer(conn, customer);

        llistaObservableCustomer.add(customer);
    }

    /**
     * Elimina una assignatura
     *
     * @param as
     * @throws SQLException
     */
    public void eliminarCustomer(Customer customer) throws SQLException {

        //l'eliminem de la BBDD
        CustomerDB.eliminaCustomer(conn, customer);
        // Si tot ha anat bé, eliminem l'objecte de la llista observable.
        // NOTA: Quan afegim o eliminem elements de la collecció, la taula es
        // refresca de forma automàtica.
        llistaObservableCustomer.remove(customer);
    }

    /**
     * Obté la llista observable
     *
     * @return
     */
    /**
     * Tanca la connexió amb la BBDD
     *
     * @throws SQLException
     */
    public void tancarConnexio() throws SQLException {
        this.conn.close();
    }

    /**
     * Valida el format en el nom d'una assignatura. Ha d'estar format per tres
     * lletres majuscules, un guió i tres dígits
     *
     * @param txt
     * @return
     */
    private boolean validaEmail(String txt) {
        Boolean ret = false;
        /**
         * Pattern regles = Pattern.compile("^([A-Z]{3}-[0-9]{3})$");
         *
         * if (regles.matcher(txt).find()) {ret = true;}
         *
         */
        return ret;
    }

    public void setData() throws SQLException {
        this.llistaObservableCustomer.setAll(CustomerDB.carregarCustomer(conn));
    }

    public ObservableList<Customer> getCustomerObservableList() {
        return llistaObservableCustomer;
    }



    public void modificarCustomer(Customer customer) throws SQLException, Exception {
        // si no valida el format del nom, genera una excepció
        /**
         * if (!this.validaNomAssignatura(as.getNom())) { throw new
         * Exception("El format del nom de l'assignatura no és correcte. Un
         * exemple de format correcte seria ABC-123"); }*
         */

        CustomerDB.modificaCustomer(conn, customer);
    }

    public void calcularEdad() throws SQLException, Exception {
        AppConfig appconfig = null;

        appconfig.getMinCustomerAge();

    }
}
