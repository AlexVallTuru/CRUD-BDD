/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Alex
 */
public class CustomerDB {
        public static Connection connectarBD() throws SQLException
    {
        Connection ret = null;
        
        // si tot ha anat correcte, getConnection retorna una instància de la classe Connection
        // Aquesta és la classe base per a poder realitzar qualsevol accès a BBDD
        ret =  DriverManager.getConnection("jdbc:mysql://localhost:3306/m03uf6_22_23?useUnicode=true&"
                            + "useJDBCCompliantTimezoneShift=true&"
                            + "useLegacyDatetimeCode=false&serverTimezone=UTC, root, 123456");
        
        return ret;
    }
}
