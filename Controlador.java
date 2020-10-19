
/**
 * Encargado de la interacción entre la vista y el modelo.
 *
 * @author (David)
 * @version (04/10/2020
 */
public class Controlador
{
    private String[] jugador=new String[2];
    private Vista vistaInterna;
    private Tablero tableroInterno;

    public void setJugador(String nombreJugador, int posicion){
        jugador[posicion]=nombreJugador;
    }

    /**
     * Constructor a partir de la vista y el tablero que se va a usar
     */
    public Controlador(Vista nuevaVista, Tablero nuevoTablero)
    {
        this.vistaInterna=nuevaVista;
        this.tableroInterno=nuevoTablero;
    }

    public void jugar(){
        vistaInterna.seleccionDeJuego();
        modoDeJuego(vistaInterna.getEntradaNumericaDeUsuario());

    }

    /**
     * 
     */

    public void modoDeJuego(int seleccionDeJuego){
        switch(seleccionDeJuego){
            case 0: //ejecuta el modo de juego contra la computadora
            juegoIndividual();
            break;

            case 1: //ejecuta el modo de juego para dos jugadores
            juegoParaDos();
            break;

            case -1: // si se selecciona la x se cierra el programa
            seleccionDeJuego=2;
            break;

        }

    }

    public void juegoIndividual(){
        int contadorDeJuego=1;
        int contadorDeTurno=0;
        vistaInterna.informacionJugador();
        jugador[0]=vistaInterna.getEntradaTextoDeUsuario();
        if(vistaInterna.getEntradaTextoDeUsuario().equals("")){
            jugador[0]="Jugador1";
        }
        jugador[1]="Computador";
        tableroInterno=new Tablero();
        boolean declararGanador=false;

        while(valorarEstado()==false && tableroInterno.espacioEnTablero()){

            if(contadorDeTurno==0){
                do{
                    vistaInterna.jugarVisualizarTablero(tableroInterno.tableroActual(),jugador[contadorDeTurno]);

                    try{
                        tableroInterno.setIndiceTurno(contadorDeTurno);
                        if(tableroInterno.columnaLlena(vistaInterna.getEntradaNumericaDeUsuario())==false){
                            tableroInterno.apilar(vistaInterna.getEntradaNumericaDeUsuario()); 
                        }else{vistaInterna.setEntradaNumericaDeUsuario(-2);}
                    }catch(ArrayIndexOutOfBoundsException e2){
                        vistaInterna.confirmacionDeSalida();

                    }}while(vistaInterna.getEntradaNumericaDeUsuario()==-2);

            }else{

                do{
                    vistaInterna.jugarVisualizarTableroVersionComputador(tableroInterno.tableroActual(),jugador[contadorDeTurno]);

                    tableroInterno.setIndiceTurno(contadorDeTurno);
                    int busquedaTemporal=tableroInterno.busquedaInteligente();
                    if(tableroInterno.columnaLlena(busquedaTemporal)==false){
                        tableroInterno.apilar(busquedaTemporal);
                    }else{
                        vistaInterna.setEntradaNumericaDeUsuario(-2);

                    }
                }while(vistaInterna.getEntradaNumericaDeUsuario()==-2);

            }

            contadorDeJuego++;
            if(valorarEstado()==false){  
                if(contadorDeTurno==0){
                    contadorDeTurno=1;
                }else{contadorDeTurno=0;
                }
            }else{declararGanador=true;}
        }
        vistaInterna.anuncioFinalizacion(tableroInterno.tableroActual(),jugador[contadorDeTurno],tableroInterno.espacioEnTablero(),declararGanador);  
        jugar();

    }

    public void juegoParaDos(){
        int columnaDeJuego=0;
        int contadorDeJuego=1;
        int contadorDeTurno=0;
        boolean intento=true;
        boolean declararGanador=false;

        vistaInterna.informacionJugador();
        jugador[0]=vistaInterna.getEntradaTextoDeUsuario();
        if(vistaInterna.getEntradaTextoDeUsuario().equals("")){
            jugador[0]="Jugador1";
        }

        vistaInterna.informacionJugador();
        jugador[1]=vistaInterna.getEntradaTextoDeUsuario();
        if(vistaInterna.getEntradaTextoDeUsuario().equals("")){
            jugador[1]="Jugador2";
        }

        tableroInterno=new Tablero();
        while(
        valorarEstado()==false && tableroInterno.espacioEnTablero()){
            do{
                vistaInterna.jugarVisualizarTablero(tableroInterno.tableroActual(),jugador[contadorDeTurno]);

                try{
                    tableroInterno.setIndiceTurno(contadorDeTurno);
                    if(tableroInterno.columnaLlena(vistaInterna.getEntradaNumericaDeUsuario())==false){
                        tableroInterno.apilar(vistaInterna.getEntradaNumericaDeUsuario()); 
                    }else{vistaInterna.setEntradaNumericaDeUsuario(-2);}
                }catch(ArrayIndexOutOfBoundsException e2){
                    vistaInterna.confirmacionDeSalida();

                }}while(vistaInterna.getEntradaNumericaDeUsuario()==-2);          

            contadorDeJuego++;
            if(valorarEstado()==false){  
                if(contadorDeTurno==0){
                    contadorDeTurno=1;
                }else{contadorDeTurno=0;
                }
            }else{declararGanador=true;}
        }        

        vistaInterna.anuncioFinalizacion(tableroInterno.tableroActual(),jugador[contadorDeTurno],tableroInterno.espacioEnTablero(),declararGanador);  
        jugar();
    }

    public boolean valorarEstado(){
        if(tableroInterno.cuatroEnLinea()==true){
            return true;
        }else{
            return false;
        }
    }
}
