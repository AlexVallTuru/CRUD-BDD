/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package logic;

import data.AppConfigDB;
import data.ConnectionDB;
import java.sql.Connection;
import java.sql.SQLException;
import logic.classes.AppConfig;

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
        appConfig = new AppConfig();
    }

    public AppConfig getAppConfig() {
        return appConfig;
    }

    public void setData() throws SQLException {
        this.appConfig = AppConfigDB.carregarAppConfig(conn);
    }



}
