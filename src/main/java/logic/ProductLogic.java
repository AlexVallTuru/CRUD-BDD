/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logic;

import data.ConnectionDB;
import data.ProductDB;
import java.sql.Connection;
import java.sql.SQLException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import logic.classes.Product;

/**
 *
 * @author Aitor
 */
public class ProductLogic {
    
    Connection conn;
    ObservableList<Product> productsOList;
    
    public ProductLogic() throws SQLException {
        // Inicialitzar connexió
        conn = ConnectionDB.getInstance().getConnection();
        //Inicialitzar col·lecció
        productsOList = FXCollections.<Product>observableArrayList();
    }
    
    /**
     * Omplir la observableList amb els continguts de la taula products
     * 
     * @throws SQLException 
     */
    public void setData() throws SQLException {
        this.productsOList.setAll(ProductDB.productsToList(conn));
    }
    
    /**
     * Obte la observableList
     * 
     * @return 
     */
    public ObservableList<Product> getProductObservableList() {
        return productsOList;
    }
}
