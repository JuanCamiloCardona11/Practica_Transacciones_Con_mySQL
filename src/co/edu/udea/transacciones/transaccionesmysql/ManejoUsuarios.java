
package co.edu.udea.transacciones.transaccionesmysql;

import co.edu.udea.transacciones.datos.*;
import co.edu.udea.transacciones.dominio.UsuarioDTO;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author JuanCamiloC
 */
public class ManejoUsuarios {
    
    public static void ingresarUsuario() {
        Connection conexion = null;
        UsuarioDAO adminUsuarios = new UsuarioDAOImp(conexion);
        try {
            conexion = ConexionBDD.getConnection();
            if (conexion.getAutoCommit()) {
                conexion.setAutoCommit(false);
            }                                                            
            String username = JOptionPane.showInputDialog(null, "Ingrese el username del nuevo usuario: "),
                   password = JOptionPane.showInputDialog(null, "Ingrese la constraseña del nuevo usuario: ");                               
            UsuarioDTO usuarioDTO = new UsuarioDTO(username, password);            
            adminUsuarios.insertUsuario(usuarioDTO);                       
            conexion.commit();
            System.out.println("Se ha hecho commit de la transaccion de ingreso del nuevo usuario: " + usuarioDTO);
        } catch (SQLException ex1) {
            ex1.printStackTrace(System.out);
            System.out.println("Algo ha salido mal, entramos al rollback.");
            try {
                if(conexion != null) {
                    conexion.rollback();
                }
            } catch (SQLException ex2) {
                ex2.printStackTrace(System.out);
            }
        }
    }
    
    public static void seleccionarUsuario() {        
        Connection conexion = null;
        UsuarioDAO adminUsuarios = new UsuarioDAOImp(conexion);
        try {
            conexion = ConexionBDD.getConnection();                                                    
            List<UsuarioDTO> usuarios = adminUsuarios.selectUsuarios();            
            
            usuarios.forEach((persona) -> {
                System.out.println("Usuario DTO:" + persona);
            });           
        } catch (SQLException ex1) {
            ex1.printStackTrace(System.out);
            System.out.println("Algo salió mal, entramos al rollback");
            try {
                if(conexion != null) {
                    conexion.rollback();
                }
            } catch (SQLException ex2) {
                ex1.printStackTrace(System.out);
            }
        }
    }
    
    public static void actualizarUsuario() {
        Connection conexion = null;
        UsuarioDAO adminUsuarios = new UsuarioDAOImp(conexion);
        try {
            conexion = ConexionBDD.getConnection();
            if (conexion.getAutoCommit()) {
                conexion.setAutoCommit(false);
            }                                                
            int idPersona = Integer.parseInt(JOptionPane.showInputDialog(null, "Ingrese el id del usuario que desea actualizar: "));            
            String username = JOptionPane.showInputDialog(null, "Ingrese el nuevo username: "),
                   password = JOptionPane.showInputDialog(null, "Ingrese la nueva contraseña: ");            
            UsuarioDTO usuarioDTO = new UsuarioDTO(idPersona, username, password); 
            int filasAfectadas = adminUsuarios.updateUsuario(usuarioDTO);                      
            System.out.println("Se han afectado " + filasAfectadas + " registros en la base de datos.");
            conexion.commit();
            System.out.println("Se ha hecho commit de la transaccion");
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
            System.out.println("Algo salió mal, entramos al rollback");
            try {
                if(conexion != null) {
                    conexion.rollback();
                }    
            } catch (SQLException ex1) {
                ex1.printStackTrace(System.out);
            }
        }
    }
    
    public static void eliminarUsuario() {
        Connection conexion = null;
        UsuarioDAO adminUsuarios = new UsuarioDAOImp(conexion);
        try {
            conexion = ConexionBDD.getConnection();
            if (conexion.getAutoCommit()) {
                conexion.setAutoCommit(false);
            }                                                            
            int idUsuarioElim = Integer.parseInt(JOptionPane.showInputDialog("Ingrese el id del usuario que desea eliminar: "));
            UsuarioDTO usuarioEliminar = new UsuarioDTO(idUsuarioElim);
            adminUsuarios.deleteUsuario(usuarioEliminar);
            System.out.println("Se ha eliminado exitosamente el usuario con id " + idUsuarioElim);
            conexion.commit();
            System.out.println("Se ha hecho commit de la transaccion");
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
            System.out.println("Algo salió mal, entramos al rollback");
            try {
                if(conexion != null) {
                    conexion.rollback();
                }
            } catch (SQLException ex1) {
                ex1.printStackTrace(System.out);
            }
        }
    }
}
