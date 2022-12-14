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

    Connection conn;

    AppConfig appConfig;

    public AppConfigLogic() throws SQLException {

        conn = ConnectionDB.getInstance().getConnection();

        AppConfig appConfig = new AppConfig();
    }

    public AppConfig getAppConfig() {
        return appConfig;
    }

    public void setData() throws SQLException {
        this.appConfig = AppConfigDB.carregarAppConfig(conn);
    }
}
