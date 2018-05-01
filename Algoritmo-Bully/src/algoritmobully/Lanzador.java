// Desarrollo de Sistemas Distribuidos
// Grado en Ingeniería Informática
// 
// Curso 2015/2016
// 
// Borja Cañavate Bordons
// Baltasar Ruiz Hernández
// Juan José Jiménez García
//
// 3º Ingeniería del Software
// 
// Trabajo del Tema 3
// Algoritmo Valentón (Bully)
//
// Lanzador.java

package algoritmobully;

/**
 * Simulación del algoritmo Bully, ideado originariamente para establecer un
 * sistema de prioridades, con un proceso maestro y otros procesos esclavos.
 */

/*
Clase cuya unica tarea consiste en crear 5 procesos e iniciar la interfaz

Existe un controlador que lleva la cuenta de todos los procesos, y es el que 
inicia todos sus procesos.

Por defecto, el proceso maestro es el que tiene el número
más grande.
*/

public class Lanzador{
    public static void main(String args[]){
        
        Interfaz interfaz = new Interfaz();
        Controlador controlador = new Controlador(interfaz) ;
        int procesojefe = 4;
        interfaz.getPanelBully().setControlador(controlador);
        
        for (int i = 0 ; i < 5 ; i++){
            System.out.println("Añadida hebra " + i + " al controlador") ;
            Proceso nuevoproceso = new Proceso(i, procesojefe) ;
            controlador.aniadirProceso(nuevoproceso) ;
        }
        
        Proceso.setNumProcesos(Integer.parseInt(args[0])) ;
        
        interfaz.setVisible(true);
        controlador.arrancar() ;
    }
}
