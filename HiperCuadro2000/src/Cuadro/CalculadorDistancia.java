package Cuadro;

/*
 * Clase para gestionar el calculo de la distancia recorrida
 * 
 * @param distancia Valor real de la distancia recorrida, inicialmente vale 0.0
 * @param valores parametro para instanciar la clase singleton valores
 * 
 */
public class CalculadorDistancia {
	
	double distancia;
	Valores valores;
	
	/*
	 * Constructor por defecto
	 * 
	 * 
	 */
	public CalculadorDistancia(){
		
		distancia = 0.0;
		valores = Valores.getInstance();
		
	}
	
	/*
	 * Función que actualiza la distancia en funcion de la velocidad recorrida y el tiempo.
	 * 
	 * @param periodo Tiempo con el que se calcula la distancia recorrida basandonos en 
	 * la velocidad actual
	 * 
	 * La distancia acumulada se guarda en el singleton valores
	 * 
	 */
	public void actualizar( int periodo ){
		
		// km -------------- km/h ----------------- ms
		//distancia += ( valores.leerVelocidad() / periodo);
		
		// coversiones : km / (h * 60 * 60) * ( periodo / 1000)
		distancia += ( ( valores.leerVelocidad() / 3600.0 ) * ( (double)periodo / 1000.0 ) );
		valores.almacenarDistancia( distancia );
		
	}
	
}
