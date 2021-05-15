
package co.edu.udea.transacciones.datos;

import co.edu.udea.transacciones.dominio.UsuarioDTO;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author JuanCamiloC
 */
public class UsuarioDAOImp implements UsuarioDAO {
    private Connection conexionTransaccional;
    //Cadenas para CRUD sobre la tabla de usuarios
    private static final String SQL_SELECT = "SELECT id_usuario, username, password FROM usuario";
    private static final String SQL_INSERT = "INSERT INTO usuario(username, password) VALUES(?, ?)";
    private static final String SQL_UPDATE = "UPDATE usuario SET username=?, password=? WHERE id_usuario=?";
    private static final String SQL_DELETE = "DELETE FROM usuario WHERE id_usuario=?";

    public UsuarioDAOImp() {

    }

    public UsuarioDAOImp(Connection conexionTransaccional) {
        this.conexionTransaccional = conexionTransaccional;
    }
    
    @Override
    public List<UsuarioDTO> selectUsuarios() throws SQLException {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        UsuarioDTO usuario;
        List<UsuarioDTO> usuarios = new ArrayList<>();
        try {
            conn = this.conexionTransaccional != null ? this.conexionTransaccional : ConexionBDD.getConnection();
            stmt = conn.prepareStatement(SQL_SELECT);
            rs = stmt.executeQuery();
            while (rs.next()) {
                int idUsuario = rs.getInt("id_usuario");
                String username = rs.getString("username");
                String password = rs.getString("password");
                usuario = new UsuarioDTO(idUsuario, username, password);
                usuarios.add(usuario);
            }
        } finally {
            ConexionBDD.close(rs);
            ConexionBDD.close(stmt);
            if (this.conexionTransaccional == null) {
                ConexionBDD.close(conn);
            }
        }
        return(usuarios);
    }

    @Override
    public int insertUsuario(UsuarioDTO usuario) throws SQLException {
        Connection conn = null;
        PreparedStatement prepStmt = null;
        int filasAfectadas = 0;
        try {
            conn = this.conexionTransaccional != null ? this.conexionTransaccional : ConexionBDD.getConnection();
            prepStmt = conn.prepareStatement(SQL_INSERT);
            prepStmt.setString(1, usuario.getUsername());
            prepStmt.setString(2, usuario.getPassword());           
            filasAfectadas = prepStmt.executeUpdate();
            System.out.println("Registros afectados:" + filasAfectadas);
        } finally {
            ConexionBDD.close(prepStmt);
            if (this.conexionTransaccional == null) {
                ConexionBDD.close(conn);
            }
        }
        return(filasAfectadas);
    }

    @Override
    public int updateUsuario(UsuarioDTO usuario) throws SQLException {
        Connection conn = null;
        PreparedStatement stmt = null;
        int filasAfectadas = 0;
        try {
            conn = this.conexionTransaccional != null ? this.conexionTransaccional : ConexionBDD.getConnection();
            System.out.println("ejecutando query: " + SQL_UPDATE);
            stmt = conn.prepareStatement(SQL_UPDATE);
            stmt.setString(1, usuario.getUsername());
            stmt.setString(2, usuario.getPassword());
            stmt.setInt(3, usuario.getIdUsuario());
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
    public int deleteUsuario(UsuarioDTO usuario) throws SQLException {
        Connection conn = null;
        PreparedStatement prepStmt = null;
        int filasAfectadas = 0;
        try {
            conn = this.conexionTransaccional != null ? this.conexionTransaccional : ConexionBDD.getConnection();            
            prepStmt = conn.prepareStatement(SQL_DELETE);
            prepStmt.setInt(1, usuario.getIdUsuario());
            filasAfectadas = prepStmt.executeUpdate();
            System.out.println("Registros eliminados:" + filasAfectadas);
        } finally {
            ConexionBDD.close(prepStmt);
             if (this.conexionTransaccional == null) {
                ConexionBDD.close(conn);
            }
        }
        return(filasAfectadas);
    }    
}
