/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package logic;

import data.AppConfigDB;
import data.ConnectionDB;
import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import logic.classes.AppConfig;
import logic.classes.Customer;

/**
 *
 * @author Alex
 */
public class AppConfigLogic {

    //Objecte connexió a la BBDD
    Connection conn;
    //llista observable d'objectes de la classe Customer
    AppConfig appConfig;

    public AppConfigLogic() throws SQLException {
        // inicialitzem connexió amb BD

        conn = ConnectionDB.getInstance().getConnection();
        // inicialitzem col.lecció
        AppConfig appConfig = new AppConfig();
    }

    public AppConfig getAppConfig() {
        return appConfig;
    }

    public void setData() throws SQLException {
        this.appConfig = AppConfigDB.carregarAppConfig(conn);
    }
    
    public int calcularEdat(Customer customer) {
        //Creamos un formato de fecha 
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        //A traves del parse pasamos la fecha al formato y le pasamos la fecha de la base de datos y el formato al que la queremos pasar
        LocalDate fechaNac = LocalDate.parse(customer.getBirthDate(), fmt);
        
        //Creamos una variable donde le metemos la fecha de hoy
        LocalDate ahora = LocalDate.now();

        //Utilizamos el metodo period para crear un objeto que nos restara dos fechas y nos obtendra años, meses y dias.
        Period periodo = Period.between(fechaNac, ahora);
        
        
        //Esta variable obtendra la edad de la persona.
        return periodo.getYears();
    }


}
