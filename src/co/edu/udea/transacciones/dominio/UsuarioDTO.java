
package co.edu.udea.transacciones.dominio;

/**
 *
 * @author JuanCamiloC
 */
public class UsuarioDTO {
    private static int idActual;
    private int idUsuario;
    private String username, password;    

    public UsuarioDTO() {
    }

    public UsuarioDTO(int idUsuario) {
        this.idUsuario = idUsuario;
    }
    
    public UsuarioDTO(String username, String password) {
        UsuarioDTO.idActual++;
        this.username = username;
        this.password = password;
    }
    
    public UsuarioDTO(int idUsuario, String username, String password) {
        this.idUsuario = idUsuario;
        this.username = username;
        this.password = password;
    }
    
    public int getIdUsuario() {
        return(this.idUsuario);
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getUsername() {
        return(this.username);
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return(this.password);
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "Usuario{" + "idUsuario=" + this.idUsuario + ", username=" + this.username + ", password=" + this.password + '}';
    }
}
