/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gcsociety;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.PriorityQueue;

/**
 *
 * @author Agustin, Jose y Borja
 */
public class Mapa {
    private ArrayList<celda> soluciones ;
    public ArrayList<celda> usados;
    private int coordenadas[][];
    private String nombre;
    private int ultimaX, ultimaY;
    public final int fila = 100, columna = 100;
    
    public Mapa(){

        coordenadas = new int[fila][columna];
        
    }
    public Mapa(String nombre){
        coordenadas = new int[fila][columna];
        this.nombre = nombre;
    }
   
    // Constructor utilizando un buffer de lectura
    Mapa(BufferedReader br,String nombre) throws IOException {
        this.nombre = nombre;
        coordenadas = new int [fila][columna];
        int j=0;
        int i=0;
        
        String _x = br.readLine();
        ultimaX = Integer.parseInt(_x);
        
        String _y = br.readLine();
        ultimaY = Integer.parseInt(_y);
        
        String linea;
        while((linea = br.readLine()) != null){
            while(j != linea.length()){
                int v = Character.getNumericValue(linea.charAt(j));
                coordenadas[i][j] = v;
                j++;
            }
            j = 0;
            i++;
        }
    }
    
    public String getNombre(){
        return nombre;
    }
    
    public void rellenarRadar(int radar, int x ,int y){
        if(radar == 4)
            coordenadas[y][x] = 0;
        else
            coordenadas[y][x] = radar;

    }
    
    public int[][] getCoordenadas(){
        return coordenadas;
    }
    
    public void guardarMapa(int x, int y){
        try{
            FileWriter fw = new FileWriter("mapas/"+this.nombre+".txt");
            PrintWriter pw = new PrintWriter(fw);
            
            pw.println(x);
            pw.println(y);
            
            for(int i=0;i<fila;i++){
                for(int j=0;j<columna;j++){
                    pw.print(coordenadas[i][j]);  
                }
                pw.println();
            }
            
            fw.close();
        }
        catch(IOException e){
            
        }
    }

    /**
     * @param coordenadas the coordenadas to set
     */
    public void setCoordenadas(int[][] coordenadas) {
        this.coordenadas = coordenadas;
    }

    /**
     * @param nombre the nombre to set
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * @return the ultimaX
     */
    public int getUltimaX() {
        return ultimaX;
    }

    /**
     * @param ultimaX the ultimaX to set
     */
    public void setUltimaX(int ultimaX) {
        this.ultimaX = ultimaX;
    }

    /**
     * @return the ultimaY
     */
    public int getUltimaY() {
        return ultimaY;
    }

    /**
     * @param ultimaY the ultimaY to set
     */
    public void setUltimaY(int ultimaY) {
        this.ultimaY = ultimaY;
    }
    
    
    private  ArrayList<celda> expandirNodos( celda poll, celda cfinal) {
        // Añadiremos todos los nodos visitables a la cola
        
        ArrayList<celda> devolver = new ArrayList<>();
       // celda nueva;
        boolean xmenor=false,xmayor=false,ymenor=false,ymayor=false;
        if(poll.x-1 >= 0)
            xmenor=true;
        if(poll.x+1 <= (coordenadas.length-1))
            xmayor=true;
        if(poll.y-1 >= 0)
            ymenor=true;
        if(poll.y+1 <= (coordenadas.length-1))
            ymayor=true;
        
        //Arriba izquierda
        if(xmenor && ymenor && coordenadas[poll.x-1][poll.y-1] != 1 && coordenadas[poll.x-1][poll.y-1] != 2 && coordenadas[poll.x-1][poll.y-1] != 4){
            celda nueva = new celda(poll.x-1,poll.y-1,coordenadas[poll.x-1][poll.y-1],cfinal.x,cfinal.y,poll.x,poll.y);
            if(coordenadas[poll.x-1][poll.y-1] == 3)
                soluciones.add(nueva);
            else
                devolver.add(nueva);
        }
        //Arriba
        if(ymenor && coordenadas[poll.x][poll.y-1] != 1 && coordenadas[poll.x][poll.y-1] != 2 && coordenadas[poll.x][poll.y-1] != 4){
            celda nueva = new celda(poll.x,poll.y-1,coordenadas[poll.x][poll.y-1],cfinal.x,cfinal.y,poll.x,poll.y);          
            if(coordenadas[poll.x][poll.y-1] == 3)
                soluciones.add(nueva);
            else
                devolver.add(nueva);        
        }  
        //Arriba derecha
        if(xmayor && ymenor && coordenadas[poll.x+1][poll.y-1] != 1 && coordenadas[poll.x+1][poll.y-1] != 2 && coordenadas[poll.x+1][poll.y-1] != 4){
            celda nueva = new celda(poll.x+1,poll.y-1,coordenadas[poll.x+1][poll.y-1],cfinal.x,cfinal.y,poll.x,poll.y);          
            if(coordenadas[poll.x+1][poll.y-1] == 3)
                soluciones.add(nueva);
            else
                devolver.add(nueva);        
        }
        //Izquierda
        if(xmenor && coordenadas[poll.x-1][poll.y] != 1 && coordenadas[poll.x-1][poll.y] != 2  && coordenadas[poll.x-1][poll.y] != 4){
            celda nueva = new celda(poll.x-1,poll.y,coordenadas[poll.x-1][poll.y],cfinal.x,cfinal.y,poll.x,poll.y);
            if(coordenadas[poll.x-1][poll.y] == 3)
                soluciones.add(nueva);
            else
                devolver.add(nueva);        
        }
        //Derecha
        if(xmayor && coordenadas[poll.x+1][poll.y] != 1 && coordenadas[poll.x+1][poll.y] != 2 && coordenadas[poll.x+1][poll.y] != 4){
            celda nueva = new celda(poll.x+1,poll.y,coordenadas[poll.x+1][poll.y],cfinal.x,cfinal.y,poll.x,poll.y);          
            if(coordenadas[poll.x+1][poll.y] == 3)
                soluciones.add(nueva);
            else
                devolver.add(nueva);        
        }
        //Abajo Izquierda 
        if(xmenor && ymayor && coordenadas[poll.x-1][poll.y+1] != 1 && coordenadas[poll.x-1][poll.y+1] != 2 && coordenadas[poll.x-1][poll.y+1] != 4){
            celda nueva = new celda(poll.x-1,poll.y+1,coordenadas[poll.x-1][poll.y+1],cfinal.x,cfinal.y,poll.x,poll.y);
            if(coordenadas[poll.x-1][poll.y+1] == 3)
                soluciones.add(nueva);
            else
                devolver.add(nueva);        
        }
        //Abajo
        if(ymayor && coordenadas[poll.x][poll.y+1] != 1 && coordenadas[poll.x][poll.y+1] != 2 && coordenadas[poll.x][poll.y+1] != 4){
            celda nueva = new celda(poll.x,poll.y+1,coordenadas[poll.x][poll.y+1],cfinal.x,cfinal.y,poll.x,poll.y);
            if(coordenadas[poll.x][poll.y+1] == 3)
                soluciones.add(nueva);
            else
                devolver.add(nueva);        
        }
        //Abajo derecha
        if(xmayor && ymayor &&coordenadas[poll.x+1][poll.y+1] != 1 && coordenadas[poll.x+1][poll.y+1] != 2 && coordenadas[poll.x+1][poll.y+1] != 4){
            celda nueva = new celda(poll.x+1,poll.y+1,coordenadas[poll.x+1][poll.y+1],cfinal.x,cfinal.y,poll.x,poll.y);
            if(coordenadas[poll.x+1][poll.y+1] == 3)
                soluciones.add(nueva);
            else
                devolver.add(nueva);        
        }
        
        return devolver;
}

    private double calcularCoste(celda get) {
        double devolver=0.0;
        Iterator <celda> itusados = usados.iterator();
        while (get.xanterior != 0 && get.yanterior != 0){
            devolver+=get.dEuclidea;
            for(int k=0;k<usados.size();k++){
                if(get.xanterior ==  usados.get(k).x && get.yanterior ==  usados.get(k).y)
                    get = usados.get(k);
            }
        }
        devolver+=get.dEuclidea;
        return devolver;
    }
/*
    public String obtenerSiguientePaso(int actualx,int actualy){
        celda cfinal = buscarFinalCercana(); 
        celda inicial = new celda(actualx,actualy,coordenadas[actualx][actualy],cfinal.x,cfinal.y,actualx,actualy);
        criterioCola criterio = new criterioCola();
        PriorityQueue<celda> colavivos = new PriorityQueue<celda>(1000,criterio);
        usados = new ArrayList();
        // Para añadir, pq1.offer(celda);
        // Para devolver el mejor y borrarlo de la cola pq2.poll()
        colavivos.offer(inicial);
        while(!colavivos.isEmpty()){ // Mientras quede algun nodo vivo
           celda actual = colavivos.poll(); // Sacamos el mejor nodo
           
           ArrayList<celda> nodos =  expandirNodos(actual,cfinal); // Expandimos el mejor nodo
           usados.add(actual); // Añadimos a usados el nodo que habiamos usado antes

           // No los añadimos si estan en la cola de vivos o en la de usados
           Iterator <celda> itusados = usados.iterator();
           Iterator <celda> itcolavivos = colavivos.iterator();
           while (itusados.hasNext()){
            // itusados.next() para sacar el siguiente
            celda auxiliar =  itusados.next();
            for(int k=0;k<nodos.size();k++){
                if(auxiliar.x ==  nodos.get(k).x && auxiliar.y ==  nodos.get(k).y)
                    nodos.remove(k);
            }
           }
           while (itcolavivos.hasNext()){
            celda auxiliar =  itcolavivos.next();
            for(int k=0;k<nodos.size();k++){
                if(auxiliar.x ==  nodos.get(k).x && auxiliar.y ==  nodos.get(k).y)
                    nodos.remove(k);
            }
           }
           // Ahora que hemos descartado todos los ya usados, añadiremos a la lista de nodos vivos los que sacamos anteriormente
           for(int k=0;k<nodos.size();k++){
               colavivos.offer(nodos.get(k));
           }

        }
        System.out.println("jkashdfklajsdhfkljasdhfkaljdfhakjsdfhkasjdfhaskjdaksjdfhasdkjlf");
        Iterator <celda> itusados = usados.iterator();
        while(itusados.hasNext()){
            celda c = itusados.next();
            //System.out.println("Nodo "+c.y +" "+c.x+ " padre: "+c.yanterior+" "+c.xanterior+ " valor casilla: "+c.valor);
        }

        double mejor = 100000;
        int nodoelegido = 0;
        for(int k=0;k<soluciones.size();k++){
            //System.out.println("Soluciones estan en : "+soluciones.get(k).x+" "+soluciones.get(k).y + " coste: "+ calcularCoste(soluciones.get(k)));
            if(calcularCoste(soluciones.get(k)) <= mejor)
                nodoelegido = k;
        }
        celda mejorc = soluciones.get(nodoelegido);

        System.out.println("El mejor camino es :");
        while(mejorc.xanterior != actualx && mejorc.yanterior != actualy ){
            for(int k=0;k<usados.size();k++){
                if((mejorc.xanterior == usados.get(k).x && mejorc.yanterior == usados.get(k).y) ){
                    mejorc = usados.get(k);
                }
            }
        }
        String accion ="";
        if(mejorc.x == actualx-1 && mejorc.y == actualy-1){
            accion = "moveNW";
        }
        if(mejorc.x == actualx-1 && mejorc.y == actualy){
            accion = "moveN";
        }
        if(mejorc.x == actualx-1 && mejorc.y == actualy+1){
            accion = "moveNE";
        }
        if(mejorc.x == actualx && mejorc.y == actualy-1){
            accion = "moveW";
        }
        if(mejorc.x == actualx && mejorc.y == actualy+1){
            accion = "moveE";
        }
        if(mejorc.x == actualx+1 && mejorc.y == actualy-1){
            accion = "moveSW";
        }
        if(mejorc.x == actualx+1 && mejorc.y == actualy){
            accion = "moveS";
        }
        if(mejorc.x == actualx+1 && mejorc.y == actualy+1){
            accion = "moveSE";
        }        
            return accion;
    }
*/
    public celda buscarFinalCercana() {
        celda devolver = new celda();
        for(int i=0;i<coordenadas.length;i++){
            for(int j=0;j<coordenadas.length;j++){
                if(coordenadas[i][j] == 3){

                        devolver.x = j;
                        devolver.y = i;
                    
                }
            }
        }
        
        return devolver;
    }
    
    
}