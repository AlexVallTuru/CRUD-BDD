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
    
    /**
     * Inicialitzar connexió i col·lecció
     * 
     * @throws SQLException 
     */
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
    
    /**
     * Envia un nou producte a la capa BBDD
     * 
     * @param product
     * @throws SQLException 
     */
    public void addProduct(Product product) throws SQLException {
        ProductDB.insertProduct(conn, product);
        
        productsOList.add(product);
    }
    
    /**
     * Envia a la capa BBDD la entrada a eliminar i també elimina aquesta de la
     * observableList
     * 
     * @param product
     * @throws SQLException 
     */
    public void removeProduct(Product product) throws SQLException {
        ProductDB.deleteProduct(conn, product);
        productsOList.remove(product);
    }
    
    /**
     * Envia a la capa BBDD el producte modificat
     * 
     * @param product
     * @throws SQLException 
     */
    public void editProduct(Product product) throws SQLException {
        ProductDB.modifyProduct(conn, product);
    }
    
    /**
     * Tanca la connexió a la BBDD
     * 
     * @throws SQLException 
     */
    public void closeConn() throws SQLException {
        this.conn.close();
    }
}
