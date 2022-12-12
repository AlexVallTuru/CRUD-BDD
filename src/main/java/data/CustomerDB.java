/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import logic.classes.Customer;

/**
 *
 * @author Alex
 */
public class CustomerDB {

    public static Connection connectarBD() throws SQLException {
        Connection ret = null;

        // si tot ha anat correcte, getConnection retorna una instància de la classe Connection
        // Aquesta és la classe base per a poder realitzar qualsevol accès a BBDD
        ret = DriverManager.getConnection("jdbc:mysql://localhost:3306/m03uf6_22_23?useUnicode=true&"
                + "useJDBCCompliantTimezoneShift=true&"
                + "useLegacyDatetimeCode=false&serverTimezone=UTC, root, 123456");

        return ret;
    }
    
    public static ArrayList<Customer> carregarCustomer(Connection con) throws SQLException {
        ArrayList<Customer> ret = new ArrayList<>();

        Statement sentencia;

        sentencia = con.createStatement();
        //Carreguem les dates del mysql
        sentencia.executeQuery("SELECT * FROM customers");
        //Guardem les dades carregades a un fitxer amb el que treballarem
        ResultSet rs = sentencia.getResultSet();
        while (rs.next()) {
            /**
             * Carreguem les dades que hi han a assignatura i les nem afeguint a
             * una coleccio tipus array list que contindra l'objecte amb totes
             * les dades de totes les posicions*
             */
            ret.add(new Customer(rs.getString("customerEmail"), rs.getString("idCard"),
                    rs.getString("customerName"), rs.getString("phone"), rs.getInt("creditLimit"), rs.getString("birthDate")));
        }
        return ret;
    }

    //Aixo es el setter
    public static int insereixNouCustomer(Connection con, Customer e) throws SQLException {
        Statement sentencia;
        int id = 1;

        sentencia = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);

        //Carguem les dades del mysql per treballarles
        sentencia.executeQuery("SELECT * FROM Assignatura order by idAssignatura asc");
        //Aquestes dades les posem a un fitxer per poderles treballar que es dira rs
        ResultSet rs = sentencia.getResultSet();

        // si hi ha algun registre a la taula
        if (rs.next()) {
            rs.last();
            id = rs.getInt("customerEmail") + 1;
        }

        //Aquesta comanda et coloca a la ultima posicio de la taula
        rs.moveToInsertRow();

        //Aqui es fan els setters
        rs.updateString("customerEmail", e.getCustomerEmail());
        rs.updateString("idCard", e.getIdCard());
        rs.updateString("customerName", e.getCustomerName());
        rs.updateString("phone", e.getPhoneNumber());
        rs.updateDouble("creditLimit", e.getCreditLimit());
        rs.updateString("birthDate", e.getBirthDate());
        
        //Afeguim les dades seleccionades a la taula mysql
        rs.insertRow();

        return id;
    }
}

