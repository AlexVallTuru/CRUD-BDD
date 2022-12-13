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
}
