import javax.swing.JOptionPane;
/**
 * Clase encargada de interactuar con el usuario.
 *
 * @author (David)
 * @version (07/10/2020)
 */
public class Vista
{
    // matriz de botones para seleccionar el modo de juego
    Object[]botones={"Un jugador","Dos jugadores", "Salir"};

    //variable encargada de recolectar la información numérica del usuario
    private int entradaNumericaDeUsuario;

    //variable encargada de recolectar la información de texto del usuario
    private String entradaTextoDeUsuario="vacio";

    /**
     * Permite acceder a la entradaNumericaDeUsuario
     * @return int
     */
    public int getEntradaNumericaDeUsuario(){
        return entradaNumericaDeUsuario;
    }

    
    /**
     * Permite asignar la entradaNumericaDeUsuario
     * @return
     */
    public void setEntradaNumericaDeUsuario(int nuevaEntradaNumerica){
        this.entradaNumericaDeUsuario=nuevaEntradaNumerica;
    }

    /**
     * Permite acceder a la entradaTextoDeUsuario
     * @return String
     */
    public String getEntradaTextoDeUsuario(){
        return entradaTextoDeUsuario;
    }

    /**
     * Permite asignar la entradaTextoDeUsuario
     * @return
     */
    public void setEntradaTextoDeUsuario(String nuevaEntradaTexto){
        this.entradaTextoDeUsuario=nuevaEntradaTexto;
    }

    /**
     * Constructor por defecto de la clase
     */
    public Vista()
    {
    }

    /**
     * Menú para seleccionar el modo de juego
     */
    public void seleccionDeJuego()
    {
        entradaNumericaDeUsuario=JOptionPane.showOptionDialog(null, "Bienvenido al juego cuatro en línea. Seleccione el modo de juego",
            "Menú principal",JOptionPane.YES_NO_CANCEL_OPTION,JOptionPane.WARNING_MESSAGE,null,botones,botones[0]);    

    }

    /**
     * Ventana para indicar el nombre de los jugadores
     * Si la entrada no es válida se pregunta si se desea salir del juego. Si no se desea finalizar se pregunta de nuevo por la información
     */
    public void informacionJugador(){

        do{
            entradaTextoDeUsuario=JOptionPane.showInputDialog(null, "Ingrese el nombre del Jugador:"); 
            if(entradaTextoDeUsuario==null){
                confirmacionDeSalida();
                if(entradaNumericaDeUsuario==-2){
                    entradaTextoDeUsuario="vacio";
                }
            }
        }while(entradaTextoDeUsuario.equals("vacio"));
    }

    /**
     * Ventana para confirmar salida del juego
     * Si se confirma la salida la ejecución finaliza
     */
    public void confirmacionDeSalida(){
        entradaNumericaDeUsuario=JOptionPane.showConfirmDialog(null, "Entrada no válida. ¿Desea salir del programa?","",JOptionPane.YES_NO_OPTION);
        if(entradaNumericaDeUsuario==0||entradaNumericaDeUsuario==-1){
            System.exit(0);
        }else{entradaNumericaDeUsuario=-2;}
    }

    /**
     * Mensaje que permite visualizar el tablero y recoge la información para la siguiente jugada.
     * @param String Tablero
     * @param String miJugador
     */
    public void jugarVisualizarTablero(String Tablero,String miJugador){
        do{try{
                entradaNumericaDeUsuario=Integer.parseInt(JOptionPane.showInputDialog(null, Tablero+"\n"+"Turno de "+miJugador+"\n"+"Ingrese el número de la columna donde quiere jugar:"));
            }catch(NumberFormatException e1){
                confirmacionDeSalida();
                entradaNumericaDeUsuario=-2;
                       }    
        }while(entradaNumericaDeUsuario==-2);
    }

    /**
     * Mensaje que permite visualizar el tablero antes de la jugada del computador en el modo de juego individual.
     * @param String Tablero
     * @param String miJugador
     */
    public void jugarVisualizarTableroVersionComputador(String Tablero, String miJugador){
        entradaNumericaDeUsuario=JOptionPane.showConfirmDialog(null, Tablero+"\n"+"Turno de "+miJugador+"\n", "",JOptionPane.YES_NO_OPTION);
     if(entradaNumericaDeUsuario==1||entradaNumericaDeUsuario==-1){
                confirmacionDeSalida();
    }}

    /**
     * Mensaje que permite visualizar el tablero e indicar el fin del juego en caso de empate o existir un ganador
     * @param String Tablero
     * @param String miJugador
     * @param boolean espacioEnTablero
     * @param boolean existeGanador
     */
    public void anuncioFinalizacion(String Tablero, String miJugador,boolean espacioEnTablero, boolean existeGanador){
        if(espacioEnTablero==false&&existeGanador==false){
            JOptionPane.showMessageDialog(null, Tablero+"\n"+"El juego ha sido empatado");
        }else{
            JOptionPane.showMessageDialog(null, Tablero+"\n"+"El ganador es el jugador: "+miJugador);
        }
    }
}