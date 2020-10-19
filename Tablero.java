
/**
 * La clase Tablero corresponde a una matriz de String cuyas entradas pueden modificarse.
 *
 * @author (David)
 * @version (19/9/2020)
 */
public class Tablero
{
    //matriz correspondiente al tablero de juego
    private String matrizTablero[][]=new String [6][7];

    //variable que indica el turno, solo toma los valores 0 o 1
    private int indiceTurno;

    //variable de finchas de los jugadores
    private String [] fichasJugadores={"N","B"};

    //variable que guarda la posición [i,j] en la matrizTablero de la última ficha colocada 
    private int[] ultimaJugada={-1,-1};

    /**
     * Constructor por defecto, rellena la matrizTablero con la letra O
     *@param
     *@return
     */
    public Tablero()
    {
        for(int fila=0;fila<6;fila++){
            for(int columna=0;columna<7;columna++){
                matrizTablero[fila][columna]="O";
            }
        }
    }

    /**
     * Asigna a la variable indiceTurno un valor que recibe por parámetro
     *@param int miturno
     *@return
     */
    public void setIndiceTurno(int miTurno){
        indiceTurno=miTurno;
    }

    /**
     * Devuelve la matrizTablero
     *@param 
     *@return String[][] matrizTablero;
     */
    public String[][] getMatrizTablero(){

        return matrizTablero;

    }

    /**
     * Recibe un número de columna. Devuelve true si la columna está llena y false si no.
     *
     * @param  int numeroColumna
     * @return boolean
     */
    public boolean columnaLlena (int numeroColumna){
        if(matrizTablero[0][numeroColumna-1].equals("O")){
            return false;
        }

        return true;
    }

    /**
     * Coloca una letra en el tablero dependiendo del turno de juego
     *
     * @param  int numeroColumna
     * @return 
     */
    public void apilar(int numeroColumna)
    {
        // put your code here
        for(int indiceFila=0; indiceFila<matrizTablero.length;indiceFila++){
            if(matrizTablero[matrizTablero.length-indiceFila-1][numeroColumna-1].equals("O")){
                matrizTablero[matrizTablero.length-indiceFila-1][numeroColumna-1]=fichasJugadores[indiceTurno];
                ultimaJugada[0]=matrizTablero.length-indiceFila-1;
                ultimaJugada[1]=numeroColumna-1;

                indiceFila=matrizTablero.length;
            }
        }
    }

    /**
     * Guarda en un String el estado actual del tablero
     *
     * @param
     * @return String impresion
     */
    public String tableroActual(){
        String impresion="";

        for(int indiceFila=0; indiceFila<matrizTablero.length;indiceFila++){
            for(int indiceColumna=0;indiceColumna<matrizTablero[0].length;indiceColumna++){
                impresion+=matrizTablero[indiceFila][indiceColumna]+"  ";
            }
            impresion+="\n";
        }

        return impresion;
    }

    /**
     * Revisa si ya se encuentran cuatro fichas iguales en línea en el tablero
     *
     * @param 
     * @return boolean
     */
    public boolean cuatroEnLinea(){

        int contadorFichasIguales=1;

        // si está iniciando el juego devuelve falso
        if(ultimaJugada[0]==-1){
            return false;
        }

        //revisión por columna, si hay cuatro espacios abajo los recorre para saber si las entradas son iguales
        if(ultimaJugada[0]<3){
            //si hay 4 espacios abajo revisa si son iguales, si lo sifon devuelve true
            for(int contadorFila=ultimaJugada[0]+1;contadorFila<ultimaJugada[0]+4;contadorFila++){
                if(matrizTablero[ultimaJugada[0]][ultimaJugada[1]].equals(matrizTablero[contadorFila][ultimaJugada[1]])){
                    contadorFichasIguales++;
                }else{
                    contadorFila=ultimaJugada[0]+4;
                }
            }
        }

        //si las 4 entradas son iguales se devuelve true
        if(contadorFichasIguales==4){
            return true;
        }else{contadorFichasIguales=1;}

        //se revisa las filas recogiendo primero el numero de fichas identicas del lado izquierdo      
        for(int contadorColumna=1;contadorColumna<4;contadorColumna++){
            if(ultimaJugada[1]+1-contadorColumna>0&&matrizTablero[ultimaJugada[0]][ultimaJugada[1]].equals(
                matrizTablero[ultimaJugada[0]][ultimaJugada[1]-contadorColumna])){
                contadorFichasIguales++;
            }else{contadorColumna=4;}
        }

        //se revisa las filas recogiendo luego el numero de fichas identicas del lado derecho
        for(int contadorColumna=1;contadorColumna<4;contadorColumna++){
            if(contadorColumna+ultimaJugada[1]<6&&matrizTablero[ultimaJugada[0]][ultimaJugada[1]].equals(
                matrizTablero[ultimaJugada[0]][contadorColumna+ultimaJugada[1]])){
                contadorFichasIguales++;
            }else{contadorColumna=4;}
        }

        //si las 4 entradas son iguales se devuelve true
        if(contadorFichasIguales>=4){
            return true;
        }else{contadorFichasIguales=1;}

        //se revisa las diagonales decrecientes recogiendo primero el número de fichas identicas del lado izquierdo      
        for(int contadorMixto=1;contadorMixto<4;contadorMixto++){
            if(ultimaJugada[1]+1-contadorMixto>0&&ultimaJugada[0]+1-contadorMixto>0&&
            matrizTablero[ultimaJugada[0]][ultimaJugada[1]].equals(matrizTablero[ultimaJugada[0]-contadorMixto][ultimaJugada[1]-contadorMixto])){
                contadorFichasIguales++;
            }else{contadorMixto=4;}
        }

        //se revisa las diagonales decrecientes recogiendo luego el número de fichas identicas del lado derecho
        for(int contadorMixto=1;contadorMixto<4;contadorMixto++){
            if(contadorMixto+ultimaJugada[1]<7&&contadorMixto+ultimaJugada[0]<6&&
            matrizTablero[ultimaJugada[0]][ultimaJugada[1]].equals(matrizTablero[ultimaJugada[0]+contadorMixto][contadorMixto+ultimaJugada[1]])){
                contadorFichasIguales++;
            }else{contadorMixto=4;}
        }

        //si las 4 entradas son iguales se devuelve true
        if(contadorFichasIguales>=4){
            return true;
        }else{contadorFichasIguales=1;}

        //se revisa las diagonales crecientes recogiendo primero el número de fichas identicas del lado izquierdo      
        for(int contadorMixto=1;contadorMixto<4;contadorMixto++){
            if(ultimaJugada[1]+1-contadorMixto>0&&ultimaJugada[0]+contadorMixto<6&&
            matrizTablero[ultimaJugada[0]][ultimaJugada[1]].equals(matrizTablero[ultimaJugada[0]+contadorMixto][ultimaJugada[1]-contadorMixto])){
                contadorFichasIguales++;
            }else{contadorMixto=4;}
        }

        //se revisa las diagonales crecientes recogiendo luego el número de fichas identicas del lado derecho
        for(int contadorMixto=1;contadorMixto<4;contadorMixto++){
            if(contadorMixto+ultimaJugada[1]<7&&ultimaJugada[0]+1-contadorMixto>0&&
            matrizTablero[ultimaJugada[0]][ultimaJugada[1]].equals(matrizTablero[ultimaJugada[0]-contadorMixto][contadorMixto+ultimaJugada[1]])){
                contadorFichasIguales++;
            }else{contadorMixto=4;}
        }

        //si las 4 entradas son iguales se devuelve true
        if(contadorFichasIguales>=4){
            return true;
        }else{contadorFichasIguales=1;}

        return false;
    }

    /**
     * Revisa si hay espacios disponibles en el tablero, cuando no hay espacios retorna false, en caso contrario retorna true
     *
     * @param 
     * @return boolean
     */
    public boolean espacioEnTablero(){
        for(int contadorFilas=0; contadorFilas<6; contadorFilas++){
            for(int contadorColumnas=0; contadorColumnas<7; contadorColumnas++){
                if(matrizTablero[contadorFilas][contadorColumnas].equals("O")){
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Se encarga de seleccionar la columna donde jugará el computador
     *
     * @param 
     * @return int
     */
    public int busquedaInteligente(){

        //gane vertical
        for(int colum=0; colum<7;colum++) {
            for(int fila=1; fila<4;fila++) {
                if(matrizTablero[fila][colum].equals("B") && matrizTablero[fila+1][colum].equals("B") && matrizTablero[fila+2][colum].equals("B")){
                    if(matrizTablero[fila-1][colum].equals("O")&&columnaLlena(colum+1)==false){
                        return colum+1;
                    }
                }
            }
        }

        // bloqueo horizontal
        int filaImportante = -1;
        int contRevisiones = 0;
        int cantN = 0;

        for (int colum = 0; colum < 7; colum++) {
            for (int fila = 0; fila < 6; fila++) {
                if (matrizTablero[fila][colum].equals("O")) {
                    filaImportante = fila;
                }
            }

            if (filaImportante >= 0) {
                /* revisa iquierda */
                if (colum - 1 >= 0) {
                    if (matrizTablero[filaImportante][colum - 1].equals("N")) {
                        contRevisiones += 1;
                        cantN += 1;
                        if (colum - 2 >= 0) {
                            if (matrizTablero[filaImportante][colum - 2].equals("N")) {
                                contRevisiones += 1;
                                cantN += 1;
                                if (colum - 3 >= 0) {
                                    if (matrizTablero[filaImportante][colum - 3].equals("N")) {
                                        contRevisiones += 1;
                                        cantN += 1;
                                    }
                                }
                            }
                        }
                    }
                }

                /* revisa derecha */
                if (colum + 1 <= 6) {
                    if (matrizTablero[filaImportante][colum + 1].equals("N")&& contRevisiones<4) {
                        contRevisiones += 1;
                        cantN += 1;
                        if (colum + 2  <= 6) {
                            if (matrizTablero[filaImportante][colum + 2].equals("N") && contRevisiones<4) {
                                contRevisiones += 1;
                                cantN += 1;
                                if (colum + 3  <= 6 ) {
                                    if (matrizTablero[filaImportante][colum + 3].equals("N") && contRevisiones<4) {
                                        contRevisiones += 1;
                                        cantN += 1;
                                    }
                                }
                            }
                        }
                    }
                }
            }
            if(cantN==3) {
                if(columnaLlena(colum+1)==false){
                    return colum+1;}
            }

            contRevisiones = 0;
            cantN = 0;
            filaImportante = -1;

        }

        //bloqueo por columna 
        int posicion=1;
        int contadorFichasIguales=1;

        if(ultimaJugada[0]<4 && ultimaJugada[0]>0){
            //si hay 3 espacios abajo revisa si son iguales, bloquea
            for(int contadorFila=ultimaJugada[0]+1;contadorFila<ultimaJugada[0]+3;contadorFila++){
                if(matrizTablero[ultimaJugada[0]][ultimaJugada[1]].equals(matrizTablero[contadorFila][ultimaJugada[1]])){
                    contadorFichasIguales++;
                }else{
                    contadorFila=ultimaJugada[0]+3;
                }
            }
            if(contadorFichasIguales==3){
                return ultimaJugada[1]+1;
            }else{
                contadorFichasIguales=1;
            }
        }

        //busca espacio vacío para jugar
        //juega en las posiciones inferiores si hay espacio
        for(int buscarPorFila=2;buscarPorFila<6;buscarPorFila++){
            for(int buscarPorColumna=0; buscarPorColumna<7; buscarPorColumna++){
                if(matrizTablero[buscarPorFila][buscarPorColumna].equals("O")){
                    if(columnaLlena(buscarPorColumna+1)==false){
                        return buscarPorColumna+1;}
                }
            }
        }

        //juega en las posiciones superiores si no hay espacio en las inferiores
        for(int buscarPorFila=0;buscarPorFila<2;buscarPorFila++){
            for(int buscarPorColumna=0; buscarPorColumna<7; buscarPorColumna++){
                if(matrizTablero[buscarPorFila][buscarPorColumna].equals("O")){
                    if(columnaLlena(buscarPorColumna+1)==false){
                        return buscarPorColumna+1;}
                }
            }
        }
        return posicion;
    }
}