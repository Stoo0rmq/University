package Cuadro;

import java.util.LinkedList;

/*
 * Clase para la gestion del calculo de la velocidad media recorrida por el vehículo.
 * 
 * @param valores referencia al singleton valores
 * @param velocidades Lista enlazada de reales en la que se guardan una serie de velocidades
 * para realizar la media aritmetica, se escogio la estructura por poder funcionar como una cola
 * 
 * 
 */
public class CalculadorVelocidadMedia {
	
	Valores valores;
	LinkedList <Double> velocidades;
	
	/*
	 * Constructor por defecto
	 * 
	 */
	public CalculadorVelocidadMedia(){
		
		valores = Valores.getInstance();
		velocidades = new LinkedList<>();
		
	}
	
	/*
	 * Funcion que se encarga de actualizar la velocidad
	 * 
	 * Si el numero de velocidades almacenadas es superior a 50
	 * se elimina 1 y se añade otra de la cola. Mientras tanto se almacenan hasta ese valor.
	 * Para la velocidad media se suman las velocidades y se hace la media aritmetica.
	 * 
	 */
	public void actualizar(){
		
		double velocidad = 0.0;
		
		if( velocidades.size() < 50 ){
			
			velocidades.addFirst( valores.leerVelocidad() );
			
		}else{
			
			velocidades.addFirst( valores.leerVelocidad() );
			velocidades.removeLast();
			
		}
		
		for( double v: velocidades){
			
			velocidad += v;
			
		}
		
		velocidad /= velocidades.size();
		
		valores.almacenarVelocidadMedia( velocidad );
		
	}
	
}
