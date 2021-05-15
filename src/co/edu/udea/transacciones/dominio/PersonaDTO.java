
package co.edu.udea.transacciones.dominio;

/**
 *
 * @author JuanCamiloC
 */
public class PersonaDTO {
    
    private static int idActual;
    private int idPersona;
    private String nombre, apellido, email, telefono;     
    
    public PersonaDTO(){
        
    }
    
    public PersonaDTO(int idPersona){
        
    }       

    public PersonaDTO(String nombre, String apellido, String email, String telefono) {        
        PersonaDTO.idActual++;
        this.idPersona = PersonaDTO.idActual;
        this.nombre = nombre;
        this.apellido = apellido;
        this.email = email;
        this.telefono = telefono;
    }
    public PersonaDTO(int idPersona, String nombre, String apellido, String email, String telefono) {        
        PersonaDTO.idActual++;
        this.idPersona = PersonaDTO.idActual;
        this.nombre = nombre;
        this.apellido = apellido;
        this.email = email;
        this.telefono = telefono;
    }
    
    
    public int getIdPersona() {
        return(this.idPersona);
    }

    public void setIdPersona(int idPersona) {
        this.idPersona = idPersona;
    }

    public String getNombre() {
        return(this.nombre);
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return(this.apellido);
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getEmail() {
        return(this.email);
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefono() {
        return(this.telefono);
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    @Override
    public String toString() {
        return "Persona{" + "idPersona=" + this.idPersona + ", nombre=" + this.nombre + ", apellido=" + this.apellido + ", email=" + this.email + ", telefono=" + this.telefono + '}';
    }
}
