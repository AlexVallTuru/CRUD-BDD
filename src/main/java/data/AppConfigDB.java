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

    /**
     * Carga los datos de la base de datos al objeto
     *
     * @param con
     * @return
     * @throws SQLException
     */
    public static AppConfig carregarAppConfig(Connection con) throws SQLException {
        AppConfig appConfig = new AppConfig();

        Statement sentencia;

        sentencia = con.createStatement();
        //Cargamos los datos de Mysql
        sentencia.executeQuery("SELECT * FROM appconfig");
        //Guarda los datos al fitchero que generamos
        ResultSet rs = sentencia.getResultSet();
        while (rs.next()) {
            
             //Cargamos los datos que hay en la base de datos y las asignamos a
             //un objeto ya que solo se creara un registro de appconfig a la
             //base de datos
            
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
