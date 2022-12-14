/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import logic.classes.Product;

/**
 *
 * @author Aitor
 */
public class ProductDB {
    
    /**
     * Crea un ArrayList de tots els productes a la BBDD
     * 
     * @param conn
     * @return 
     * @throws SQLException 
     */
    public static ArrayList<Product> productsToList(Connection conn) throws SQLException {
        
        ArrayList<Product> productsList = new ArrayList<>();
        
        //Crear query
        Statement query;
        query = conn.createStatement();
        query.executeQuery("SELECT * FROM products");
        
        ResultSet rs = query.getResultSet();
        
        // Passar tots els resultats al ArrayList i retornar
        while (rs.next()) {
            productsList.add(new Product(
                    rs.getInt("productCode"), rs.getString("productName"), 
                    rs.getString("productDescription"), rs.getInt("quantityInStock"), 
                    rs.getDouble("buyPrice")));
        }
        return productsList;
    }
    
    /**
     * Inserta un nou producte a la BBDD
     * 
     * @param conn
     * @param product
     * @throws SQLException 
     */
    public static void insertProduct(Connection conn, Product product) throws SQLException {
        // Preparem una sentencia sql i carreguem les dades de la BBDD per treballar amb aquesta
        Statement query = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
        query.executeQuery("SELECT * FROM products");
        ResultSet rs = query.getResultSet();
        
        // Anem a l'ultima posició de la taula i obtenim les noves dades
        rs.moveToInsertRow();
        rs.updateString("productName", product.getProductName());
        rs.updateString("productDescription", product.getProductDescription());
        rs.updateInt("quantityInStock", product.getQuantityInStock());
        rs.updateDouble("buyPrice", product.getBuyPrice());
        
        // Afegim les dades a la BBDD
        rs.insertRow();
    }
    
    /**
     * Modifica la entrada a la BBDD seleccionada des-de la observableList
     * 
     * @param conn
     * @param product
     * @throws SQLException 
     */
    public static void modifyProduct(Connection conn, Product product) throws SQLException {
        /**
         * Preparem una sentencia sql que buscara el producte amb la mateixa ID
         * Que la proporcionada
         */
        Statement query = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
        query.executeQuery("SELECT * FROM products WHERE productCode = " + String.valueOf(product.getProductCode()));
        ResultSet rs = query.getResultSet();
        
        // Enviem els nous valors a la BBDD
        if (rs.next()) {
            rs.updateString("productName", product.getProductName());
            rs.updateString("productDescription", product.getProductDescription());
            rs.updateInt("quantityInStock", product.getQuantityInStock());
            rs.updateDouble("buyPrice", product.getBuyPrice());
            
            rs.updateRow();
        }
    }
    
    /**
     * Elimina la entrada de la taula que coincideix amb la clau proporcionada
     * 
     * @param conn
     * @param product
     * @throws SQLException 
     */
    public static void deleteProduct(Connection conn, Product product) throws SQLException {
        Statement query = conn.createStatement();
        
        String sqlStr = "DELETE FROM products WHERE productCode = " + String.valueOf(product.getProductCode());
        query.executeUpdate(sqlStr);
    }
}
