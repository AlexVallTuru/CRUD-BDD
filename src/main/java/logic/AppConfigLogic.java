/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package logic;

import data.AppConfigDB;
import data.ConnectionDB;
import java.sql.Connection;
import java.sql.SQLException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
    ObservableList<AppConfig> llistaObservableAppConfig;

    public AppConfigLogic() throws SQLException {
        // inicialitzem connexió amb BD

        conn = ConnectionDB.getInstance().getConnection();
        // inicialitzem col.lecció
        llistaObservableAppConfig = FXCollections.<AppConfig>observableArrayList();
    }

    public ObservableList<AppConfig> getAppConfigObservableList() {
        return llistaObservableAppConfig;
    }
        public void setData() throws SQLException {
        this.llistaObservableAppConfig.setAll(AppConfigDB.carregarAppConfig(conn));
    } 
}
