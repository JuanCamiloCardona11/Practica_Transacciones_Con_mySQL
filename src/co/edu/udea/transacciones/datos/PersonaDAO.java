
package co.edu.udea.transacciones.datos;

import co.edu.udea.transacciones.dominio.PersonaDTO;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author JuanCamiloC
 */
public interface PersonaDAO {
    public abstract List<PersonaDTO> selectPersonas() throws SQLException;
    
    public abstract int insertPersona(PersonaDTO persona) throws SQLException;
    
    public abstract int updatePersona(PersonaDTO persona) throws SQLException;
    
    public abstract int deletePersona(PersonaDTO persona) throws SQLException;
}
