/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package data;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import logic.classes.AppConfig;

/**
 *
 * @author Alex
 */
public class AppConfigDB {

    public static AppConfig carregarAppConfig(Connection con) throws SQLException {
        AppConfig appConfig = new AppConfig();

        Statement sentencia;

        sentencia = con.createStatement();
        //Carreguem les dates del mysql
        sentencia.executeQuery("SELECT * FROM appconfig");
        //Guardem les dades carregades a un fitxer amb el que treballarem
        ResultSet rs = sentencia.getResultSet();
        while (rs.next()) {
            /**
             * Carreguem les dades que hi han a assignatura i les nem afeguint a
             * una coleccio tipus array list que contindra l'objecte amb totes
             * les dades de totes les posicions*
             */
            appConfig = new AppConfig(rs.getDouble("defaultCreditLimit"),
                    rs.getInt("defaultQuantityInStock"),
                    rs.getInt("defaultQuantityOrdered"),
                    rs.getInt("defaultProductBenefit"),
                    rs.getInt("minShippingHours"),
                    rs.getInt("minCustomerAge"),
                    rs.getInt("maxLinesPerOrder"),
                    rs.getDouble("maxOrderAmount"));
        }
        return appConfig;
    }
}
