
package co.edu.udea.transacciones.datos;

import co.edu.udea.transacciones.dominio.PersonaDTO;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author JuanCamiloC
 */
public class PersonaDAOImp implements PersonaDAO{
    
    private Connection conexionTransaccional;   
    //Cadenas para CRUD sobre la tabla de personas
    private static final String SQL_SELECT = "SELECT id_persona, nombre, apellido, email, telefono FROM persona";
    private static final String SQL_INSERT = "INSERT INTO persona(nombre, apellido, email, telefono) VALUES(?, ?, ?, ?)";
    private static final String SQL_UPDATE = "UPDATE persona SET nombre=?, apellido=?, email=?, telefono=? WHERE id_persona=?";
    private static final String SQL_DELETE = "DELETE FROM persona WHERE id_persona=?";

    public PersonaDAOImp() {

    }

    public PersonaDAOImp(Connection conexionTransaccional) {
        this.conexionTransaccional = conexionTransaccional;
    }

    @Override
    public List<PersonaDTO> selectPersonas() throws SQLException {
        Connection conn = null;
        PreparedStatement prepStmt = null;
        ResultSet rs = null;
        PersonaDTO persona;
        List<PersonaDTO> personas = new ArrayList<>();
        try {
            conn = this.conexionTransaccional != null ? this.conexionTransaccional : ConexionBDD.getConnection();
            prepStmt = conn.prepareStatement(SQL_SELECT);
            rs = prepStmt.executeQuery();
            while (rs.next()) {
                int idPersona = rs.getInt("id_persona");
                String nombre = rs.getString("nombre");
                String apellido = rs.getString("apellido");
                String email = rs.getString("email");
                String telefono = rs.getString("telefono");
                persona = new PersonaDTO(idPersona, nombre, apellido, email, telefono);
                personas.add(persona);
            }
        } finally {
            ConexionBDD.close(rs);
            ConexionBDD.close(prepStmt);
            if (this.conexionTransaccional == null) {
                ConexionBDD.close(conn);
            }
        }
        return(personas);
    }

    @Override
    public int insertPersona(PersonaDTO persona) throws SQLException {
        Connection conn = null;
        PreparedStatement stmt = null;
        int filasAfectadas = 0;
        try {
            conn = this.conexionTransaccional != null ? this.conexionTransaccional : ConexionBDD.getConnection();
            stmt = conn.prepareStatement(SQL_INSERT);
            stmt.setString(1, persona.getNombre());
            stmt.setString(2, persona.getApellido());
            stmt.setString(3, persona.getEmail());
            stmt.setString(4, persona.getTelefono());            
            filasAfectadas = stmt.executeUpdate();
            System.out.println("Registros afectados:" + filasAfectadas);
        } finally {
            ConexionBDD.close(stmt);
            if (this.conexionTransaccional == null) {
                ConexionBDD.close(conn);
            }
        }
        return(filasAfectadas);
    }

    @Override
    public int updatePersona(PersonaDTO persona) throws SQLException {
        Connection conn = null;
        PreparedStatement stmt = null;
        int filasAfectadas = 0;
        try {
            conn = this.conexionTransaccional != null ? this.conexionTransaccional : ConexionBDD.getConnection();            
            stmt = conn.prepareStatement(SQL_UPDATE);
            stmt.setString(1, persona.getNombre());
            stmt.setString(2, persona.getApellido());
            stmt.setString(3, persona.getEmail());
            stmt.setString(4, persona.getTelefono());
            stmt.setInt(5, persona.getIdPersona());
            filasAfectadas = stmt.executeUpdate();
            System.out.println("Registros actualizado:" + filasAfectadas);
        } finally {
            ConexionBDD.close(stmt);
            if (this.conexionTransaccional == null) {
                ConexionBDD.close(conn);
            }
        }
        return(filasAfectadas);
    }

    @Override
    public int deletePersona(PersonaDTO persona) throws SQLException {
        Connection conn = null;
        PreparedStatement stmt = null;
        int filasAfectadas = 0;
        try {
            conn = this.conexionTransaccional != null ? this.conexionTransaccional : ConexionBDD.getConnection();            
            stmt = conn.prepareStatement(SQL_DELETE);
            stmt.setInt(1, persona.getIdPersona());
            filasAfectadas = stmt.executeUpdate();
            System.out.println("Registros eliminados:" + filasAfectadas);
        } finally {
            ConexionBDD.close(stmt);
            if (this.conexionTransaccional == null) {
                ConexionBDD.close(conn);
            }
        }
        return(filasAfectadas);
    }
}
