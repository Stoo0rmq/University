package GUI;

import java.util.ArrayList;
import java.util.List;

import Cuadro.*;
import Monitor.*;

/*
 * Clase encargada de la simulacion del Applet
 * 
 * @param cuadro Referencia a la hebra encargada del cuadro
 * @param monitor Referencia a la hebra encargada del monitor
 * @param distancia Referencia a la hebra encargada de la distanca
 * @param velocidad Referencia a la hebra encargada de la velocidad media
 * @param observers Lista que almacena los observadores creados en el Applet
 * 
 */
public class Simulacion extends Thread {
	
	HebraCuadro cuadro;
	HebraMonitor monitor;
	HebraDistancia distancia;
	HebraVelocidad velocidad;
	
	List <Observador> observers;
	
	/*
	 * Constructor por parametros
	 * 
	 * @param hebra1 Instancia de la hebra del cuadro
	 * @param hebra2 Instancia de la hebra del monitor
	 * @param hebra3 Instancia de la hebra de la distancia
	 * @param hebra4 Instancia de la hebra de la velocidad media
	 * 
	 */
	public Simulacion( HebraCuadro hebra1, HebraMonitor hebra2,HebraDistancia hebra3,HebraVelocidad hebra4 ){
		
		this.cuadro = hebra1;
		this.monitor = hebra2;
		this.distancia = hebra3;
		this.velocidad = hebra4;
		
		this.observers = new ArrayList<>();
		
	}
	
	/*
	 * Funcion que añade un nuevo observador a la lista de observadores de la simulacion
	 * 
	 * @param o Observador que se añade a la lista
	 */
	public void setObserver( Observador o ){
		
		this.observers.add( o );
		
	}
	
	/*
	 * Implementacion del runnable de la simulacion.
	 * Se encarga de poner en marcha todas las hebras y periodicamente
	 * notificar a todos los observadores que observen.
	 * 
	 * (non-Javadoc)
	 * @see java.lang.Thread#run()
	 */
	public void run(){
		
		cuadro.start();
		monitor.start();
		distancia.start();
		velocidad.start();
		
		while(true){			
            try {            
                sleep((long)(250)); // 1/4 de segundo
            } catch (InterruptedException e) {
            	e.printStackTrace();
            }
            
            for( Observador o: this.observers)
            	o.evento();
            
		}
		
	}
	
}
