/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pruebas;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.Comparator;
import java.util.Iterator;
import java.util.Vector;

/**
 *
 * @author Borja
 */
public class Pruebas {
    private static ArrayList<celda> soluciones ;
    public static ArrayList<celda> usados;
    private static ArrayList<celda> expandirNodos( celda poll,int [][] mapa) {
        // Añadiremos todos los nodos visitables a la cola
        // AQUI
        int finalx = 5;
        int finaly = 7;
        ArrayList<celda> devolver = new ArrayList<>();
        celda nueva;
        if(mapa[poll.x-1][poll.y-1] != 0 && mapa[poll.x-1][poll.y-1] != 2 && mapa[poll.x-1][poll.y-1] != 9 ){
           nueva = new celda(poll.x-1,poll.y-1,mapa[poll.x-1][poll.y-1],finalx,finaly,poll.x,poll.y);
           if(mapa[poll.x-1][poll.y-1] == 3)
               soluciones.add(nueva);
           else
            devolver.add(nueva);
        }
        if(mapa[poll.x][poll.y-1] != 0 && mapa[poll.x][poll.y-1] != 2 && mapa[poll.x][poll.y-1] != 9 ){
           nueva = new celda(poll.x,poll.y-1,mapa[poll.x][poll.y-1],finalx,finaly,poll.x,poll.y);
           if(mapa[poll.x][poll.y-1] == 3)
               soluciones.add(nueva);
           else
            devolver.add(nueva);        }        
        if(mapa[poll.x+1][poll.y-1] != 0 && mapa[poll.x+1][poll.y-1] != 2 && mapa[poll.x+1][poll.y-1] != 9 ){
           nueva = new celda(poll.x+1,poll.y-1,mapa[poll.x+1][poll.y-1],finalx,finaly,poll.x,poll.y);
           if(mapa[poll.x+1][poll.y-1] == 3)
               soluciones.add(nueva);
           else
            devolver.add(nueva);        }        
        if(mapa[poll.x-1][poll.y] != 0 && mapa[poll.x-1][poll.y] != 2 && mapa[poll.x-1][poll.y] != 9 ){
           nueva = new celda(poll.x-1,poll.y,mapa[poll.x-1][poll.y],finalx,finaly,poll.x,poll.y);
           if(mapa[poll.x-1][poll.y] == 3)
               soluciones.add(nueva);
           else
            devolver.add(nueva);        }
        if(mapa[poll.x+1][poll.y] != 0 && mapa[poll.x+1][poll.y] != 2 && mapa[poll.x+1][poll.y] != 9 ){
           nueva = new celda(poll.x+1,poll.y,mapa[poll.x+1][poll.y],finalx,finaly,poll.x,poll.y);
           if(mapa[poll.x+1][poll.y] == 3)
               soluciones.add(nueva);
           else
            devolver.add(nueva);        }            
        if(mapa[poll.x-1][poll.y+1] != 0 && mapa[poll.x-1][poll.y+1] != 2 && mapa[poll.x-1][poll.y+1] != 9 ){
           nueva = new celda(poll.x-1,poll.y+1,mapa[poll.x-1][poll.y+1],finalx,finaly,poll.x,poll.y);
           if(mapa[poll.x-1][poll.y+1] == 3)
               soluciones.add(nueva);
           else
            devolver.add(nueva);        }
        if(mapa[poll.x][poll.y+1] != 0 && mapa[poll.x][poll.y+1] != 2 && mapa[poll.x][poll.y+1] != 9 ){
           nueva = new celda(poll.x,poll.y+1,mapa[poll.x][poll.y+1],finalx,finaly,poll.x,poll.y);
           if(mapa[poll.x][poll.y+1] == 3)
               soluciones.add(nueva);
           else
            devolver.add(nueva);        }
        if(mapa[poll.x+1][poll.y+1] != 0 && mapa[poll.x+1][poll.y+1] != 2 && mapa[poll.x+1][poll.y+1] != 9 ){
           nueva = new celda(poll.x+1,poll.y+1,mapa[poll.x+1][poll.y+1],finalx,finaly,poll.x,poll.y);
           if(mapa[poll.x+1][poll.y+1] == 3)
               soluciones.add(nueva);
           else
            devolver.add(nueva);        }
        return devolver;
    }
    // AQUI
    private static double calcularCoste(celda get) {
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

    /**
     * @param args the command line arguments
     */
    	static class criterioCola implements Comparator<celda> {
                
                //  Negativo si el primero es menos prioritario
                // Zero si son iguales
                // Positivo si el primero es mas prioritario
                // a es 5 y b es 3 , el mas prioritario es 3
		public int compare(celda one, celda two) {                        
			return (int) (two.dEuclidea - one.dEuclidea);
		}
	}
    
    void calcularSiguientePaso(){
        
    }
    
    public static void main(String[] args) throws FileNotFoundException, IOException {
      soluciones = new ArrayList();
      boolean encontrado = true;
      int mapa[][] = new int[100][100];
      for(int a=0;a<100;a++)
          for(int b=0;b<100;b++)
              mapa[a][b]=9;
      String cadena;
      FileReader f = new FileReader("mapas/hola.txt");
      BufferedReader b = new BufferedReader(f);
      int j=0;
      int i=0;
      cadena = b.readLine();
                      System.out.println("mec1");

      if(cadena.charAt(0) == '9'){
          encontrado = false;
                          System.out.println("mec");

      }
      if(encontrado){

           while(j < cadena.length() && cadena.charAt(j) != '9' ){
            System.out.println("la cadena esta en "+j);
            if(cadena.charAt(j) != ' '){
                int x = Character.getNumericValue(cadena.charAt(j));             
                mapa[i][j] = x;
             
            }
               j++;
           }

           i=1;
      while((cadena = b.readLine())!=null) {
              j=0;
              while(j != cadena.length() && cadena.charAt(j) != '9' ){

                 int x = Character.getNumericValue(cadena.charAt(j));
                 System.out.println("voy a imprimir char  "+cadena.charAt(j));

                 System.out.println("voy a imprimir "+x);
                 //mapa[i][j] = cadena.charAt(j);
                 mapa[i][j] = x;

                 j++;
                }
             i++;
          }
          
          
          System.out.println(cadena);
      }
           
           
      
           
      if(encontrado){
          System.out.println("El mapa se ha encontrado, aqui esta el resultado");
           for ( i = 0; i < 100; i++){
            for ( j = 0; j < 100; j++){
                    System.out.print(mapa[i][j]);
            }
            System.out.print("\n");
        }
              FileWriter fichero = null;
    PrintWriter pw = null;
    try
    {
        fichero = new FileWriter("hola.txt");
        pw = new PrintWriter(fichero);

        for ( i = 0; i < 100; i++){
            for ( j = 0; j < 100; j++){
                    pw.print(mapa[i][j]);
            }
            pw.print("\n");
        }
    } catch (Exception e) {
        e.printStackTrace();
    } finally {
       try {
       // Nuevamente aprovechamos el finally para 
       // asegurarnos que se cierra el fichero.
       if (null != fichero)
          fichero.close();
       } catch (Exception e2) {
          e2.printStackTrace();
       }
    }
      }
      b.close();
      // AQUI
    // Vamos a empezar en (1,1) , la solucion esta en (7,5)EN ESTE CASO CONCRETO
    celda inicial = new celda(1,1,1,7,5,0,0);
    //PriorityQueue(int initialCapacity, Comparator<? super E> comparator)   
    criterioCola criterio = new criterioCola();
    PriorityQueue<celda> colavivos = new PriorityQueue<celda>(10,criterio);
    usados = new ArrayList();
    // Para añadir, pq1.offer(celda);
    // Para devolver el mejor y borrarlo de la cola pq2.poll()
    colavivos.offer(inicial);
    while(!colavivos.isEmpty()){ // Mientras quede algun nodo vivo
       celda actual = colavivos.poll(); // Sacamos el mejor nodo
       ArrayList<celda> nodos =  expandirNodos(actual,mapa); // Expandimos el mejor nodo
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
    Iterator <celda> itusados = usados.iterator();
    while(itusados.hasNext()){
        celda c = itusados.next();
        System.out.println("Nodo "+c.y +" "+c.x+ " padre: "+c.yanterior+" "+c.xanterior+ " valor casilla: "+c.valor);
    }
    
    double mejor = 100000;
    int nodoelegido = 0;
    for(int k=0;k<soluciones.size();k++){
        System.out.println("Soluciones estan en : "+soluciones.get(k).x+" "+soluciones.get(k).y + " coste: "+ calcularCoste(soluciones.get(k)));
        if(calcularCoste(soluciones.get(k)) <= mejor)
            nodoelegido = k;
    }
    celda mejorc = soluciones.get(nodoelegido);
    
    System.out.println("El mejor camino es :");
    while(mejorc.xanterior != 0 && mejorc.yanterior != 0 ){
        System.out.println(mejorc.y + " " + mejorc.x);
        for(int k=0;k<usados.size();k++){
            if(mejorc.xanterior == usados.get(k).x && mejorc.yanterior == usados.get(k).y ){
                mejorc = usados.get(k);
            }
        }
    }
        System.out.println(mejorc.y + " " + mejorc.x);

    }
}