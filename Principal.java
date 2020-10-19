
/**
 * Clase donde inicia el programa.
 *
 * @author (David Vallejos)
 * @version (10/10/2020)
 */
public class Principal
{

    /**
     * Constructor for objects of class Principal
     */
    public Principal()
    {

    }

    /**
     * Instancia una vista, un tablero y un controlador para ejecutar el juego. 
     *
     * @param
     * @return
     */
    public static void main(String args []){
        Vista miVista=new Vista();
        Tablero miTablero=new Tablero();
        Controlador miControlador=new Controlador(miVista,miTablero);
        miControlador.jugar();

    }
}
