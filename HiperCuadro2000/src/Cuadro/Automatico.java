package Cuadro;


/*
 * Clase para la gestion de velocidad en modo automatico
 * 
 * @param valores referencia al singleton Valores
 */
public class Automatico {

	private Valores valores;
	
	/*
	 * Constructor por defecto
	 * 
	 */
	public Automatico(){
		
		valores = Valores.getInstance();
		
	}
	
	/*
	 * Ajusta el acelerador y el freno para llegar a la velocidad almacenada en valores
	 * 
	 * @param a Referencia al acelerador
	 * @param f Referencia al freno
	 * 
	 */
	public void mantenerVelocidad(Acelerador a ,Freno f ){
		
		if( valores.leerVelocidad() > valores.leerVelMantenida() ){
			a.soltaracelerador();
				
		
		}else if( valores.leerVelocidad() < valores.leerVelMantenida() ){
			a.pisaracelerador();

		}
		
	}
	
}
