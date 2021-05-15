
package co.edu.udea.transacciones.transaccionesmysql;

import javax.swing.JOptionPane;

/**
 *
 * @author JuanCamiloC
 */
public class TransaccionesmySQL {    
    public static void main(String[] args) {        
        char opcionPanel = TransaccionesmySQL.elegirPanel();
        while(opcionPanel != '3'){            
            TransaccionesmySQL.desplegarPanelEledigo(opcionPanel);
            opcionPanel = TransaccionesmySQL.elegirPanel();
        }
    }

    private static char elegirPanel() {
        char opcElegida;
        do {
            opcElegida = JOptionPane.showInputDialog(null,"Ingrese una opción: \n" +
                                                          "1. Ir al pánel de personas.\n" +
                                                          "2. Ir al pánel de usuarios.\n" +
                                                          "3. Salir del sistema.").charAt(0);            
        } while((opcElegida <= '0') || (opcElegida >= '4'));
        return(opcElegida);
    }

    private static void desplegarPanelEledigo(char opcionPanel) {
        switch(opcionPanel){
            case '1': TransaccionesmySQL.desplegarPanelPersonas();
                break;
            case '2': TransaccionesmySQL.desplegarPanelUsuarios();
                break;
            case '3':
                int opcionSalir = JOptionPane.showConfirmDialog(null,"¿Desea salir definitivamente del sistema?");
                if(opcionSalir == 0) {
                    JOptionPane.showMessageDialog(null,"Ha decidido salir, hasta luego");
                }
                break;
            default:
                break;
        }        
    }

    private static void desplegarPanelPersonas() {
        char opcElegida;
        do {
            opcElegida = JOptionPane.showInputDialog(null,"Ingrese una opción: \n" +
                                                          "1. Ingresar una nueva persona.\n" +
                                                          "2. Buscar personas.\n" +
                                                          "3. Actualizar una persona.\n" +
                                                          "4. Eliminar una persona.\n" + 
                                                          "0. Atrás.").charAt(0);            
        } while((opcElegida < '0') || (opcElegida >= '5'));
        if(opcElegida != '0') {
            TransaccionesmySQL.ejecutarAccion(opcElegida);
        }              
    }

    private static void desplegarPanelUsuarios() {
        char opcElegida;
        do {
            opcElegida = JOptionPane.showInputDialog(null,"Ingrese una opción: \n" +
                                                          "5. Ingresar un nuevo usuario.\n" +
                                                          "6. Buscar usuarios.\n" +
                                                          "7. Actualizar un usuario.\n" +
                                                          "8. Eliminar un usuario.\n" + 
                                                          "9. Atrás.").charAt(0);            
        } while((opcElegida <= '4') || (opcElegida > '9'));
        if(opcElegida != '9') {
            TransaccionesmySQL.ejecutarAccion(opcElegida);
        }        
    }

    private static void ejecutarAccion(char opcionElegida) {
        switch(opcionElegida) {
            case '1': ManejoPersonas.ingresarPersona();
                break;
            case '2': ManejoPersonas.seleccionarPersonas();
                break;
            case '3': ManejoPersonas.actualizarPersona();
                break;
            case '4': ManejoPersonas.eliminarPersona();
                break;
            case '5': ManejoUsuarios.ingresarUsuario();
                break;
            case '6': ManejoUsuarios.seleccionarUsuario();
                break;
            case '7': ManejoUsuarios.actualizarUsuario();
                break;
            case '8': ManejoUsuarios.eliminarUsuario();
                break;
            case '0': //No se hace nada aquí
                break;
            case '9': //No se hace nada aquí
                break;
            default: 
                break;
        }
    }            
}
