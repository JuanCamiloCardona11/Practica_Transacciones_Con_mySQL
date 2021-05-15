
package co.edu.udea.transacciones.transaccionesmysql;

import co.edu.udea.transacciones.datos.ConexionBDD;
import co.edu.udea.transacciones.datos.*;
import co.edu.udea.transacciones.dominio.PersonaDTO;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author JuanCamiloC
 */
public class ManejoPersonas {

    public static void ingresarPersona() {
        Connection conexion = null;
        PersonaDAO adminPersonas = new PersonaDAOImp(conexion);
        try {
            conexion = ConexionBDD.getConnection();
            if (conexion.getAutoCommit()) {
                conexion.setAutoCommit(false);
            }                                                            
            String nombre = JOptionPane.showInputDialog(null, "Ingrese el nombre de la nueva persona: "),
                   apellido = JOptionPane.showInputDialog(null, "Ingrese el apellido de la nueva persona: "),
                   email =  JOptionPane.showInputDialog(null, "Ingrese el email de la nueva persona: "),
                   telefono = JOptionPane.showInputDialog(null, "Ingrese el teléfono de la nueva persona: ");            
            PersonaDTO personaDTO = new PersonaDTO(nombre, apellido, email, telefono);            
            adminPersonas.insertPersona(personaDTO);                       
            conexion.commit();
            System.out.println("Se ha hecho commit de la transaccion de ingreso de la nueva persona: " + personaDTO);
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
    
    public static void seleccionarPersonas() {        
        Connection conexion = null;
        PersonaDAO adminPersonas = new PersonaDAOImp(conexion);
        try {
            conexion = ConexionBDD.getConnection();                                                    
            List<PersonaDTO> personas = adminPersonas.selectPersonas();            
            
            personas.forEach((persona) -> {
                System.out.println("Persona DTO:" + persona);
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
    
    public static void actualizarPersona() {
        Connection conexion = null;
        PersonaDAO adminPersonas = new PersonaDAOImp(conexion);
        try {
            conexion = ConexionBDD.getConnection();
            if (conexion.getAutoCommit()) {
                conexion.setAutoCommit(false);
            }                                                
            int idPersona = Integer.parseInt(JOptionPane.showInputDialog(null, "Ingrese el id de la persona que desea actualizar: "));
            String nombre = JOptionPane.showInputDialog(null, "Ingrese el nuevo nombre: "),
                   apellido = JOptionPane.showInputDialog(null, "Ingrese el nuevo apellido: "),
                   email =  JOptionPane.showInputDialog(null, "Ingrese el nuevo email: "),
                   telefono = JOptionPane.showInputDialog(null, "Ingrese el nuevo teléfono: ");            
            PersonaDTO personaDTO = new PersonaDTO(idPersona, nombre, apellido, email, telefono); 
            int filasAfectadas = adminPersonas.updatePersona(personaDTO);                      
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
    
    public static void eliminarPersona() {
        Connection conexion = null;
        PersonaDAO adminPersonas = new PersonaDAOImp(conexion);
        try {
            conexion = ConexionBDD.getConnection();
            if (conexion.getAutoCommit()) {
                conexion.setAutoCommit(false);
            }                                                            
            int idPersonaElim = Integer.parseInt(JOptionPane.showInputDialog("Ingrese el id de la persona que desea eliminar: "));
            PersonaDTO personaEliminar = new PersonaDTO(idPersonaElim);
            adminPersonas.deletePersona(personaEliminar);
            System.out.println("Se ha eliminado exitosamente la persona con id " + idPersonaElim);
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
