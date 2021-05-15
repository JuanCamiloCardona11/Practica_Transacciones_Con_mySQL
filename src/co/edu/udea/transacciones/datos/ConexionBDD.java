
package co.edu.udea.transacciones.datos;

import java.sql.*;

/**
 *
 * @author JuanCamiloC
 */
public class ConexionBDD {
    private static final String URL = "jdbc:mysql://localhost/transaccionesbdd?useSSL=false&useTimezone=true&serverTimezone=UTC&allowPublicKeyRetrieval=true";
    private static final String USER = "root";
    private static final String PASSSWORD = "camilo123root";
    
    public static Connection getConnection() throws SQLException {
        return((Connection)DriverManager.getConnection(URL, USER, PASSSWORD));
    }
    
    public static void close(ResultSet rs){     //Método close sobrecargado para cerrar cada objeto de flujo de datos
        try {
            rs.close();
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        }
    }       
    
    public static void close(PreparedStatement prepStmt){
        try {
            prepStmt.close();
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        }
    }
    
    public static void close(Connection conn){
        try {
            conn.close();
        } catch (SQLException ex) {
           ex.printStackTrace(System.out);
        }
    }
}
