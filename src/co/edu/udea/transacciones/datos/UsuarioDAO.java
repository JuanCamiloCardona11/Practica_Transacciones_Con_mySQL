
package co.edu.udea.transacciones.datos;

import co.edu.udea.transacciones.dominio.UsuarioDTO;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author JuanCamiloC
 */
public interface UsuarioDAO {
    public abstract List<UsuarioDTO> selectUsuarios() throws SQLException;
    
    public abstract int insertUsuario(UsuarioDTO usuario) throws SQLException;
    
    public abstract int updateUsuario(UsuarioDTO usuario) throws SQLException;
    
    public abstract int deleteUsuario(UsuarioDTO usuario) throws SQLException;
}
