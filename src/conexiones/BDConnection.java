/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package conexiones;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 *
 * @author anyi4
 */
public class BDConnection {
    
    private static final String URL= "jdbc:mysql://localhost:3306/biblioteca?serverTimezone=UTC";
    private static final String USER= "root";
    private static final String  PASSWORD ="umg$123456";
   
   
    public static Connection getConnection(){
    
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(URL, USER,  PASSWORD);
                    
            System.out.println ("CONEXON DB EXITOSA ");
        } catch (SQLException e) {
            System.out.println("ERROR EN LA CONEXION DB "+  e.getMessage());
        }
            return conn ;
    
}

    public PreparedStatement prepareStatement(String query, int RETURN_GENERATED_KEYS) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
