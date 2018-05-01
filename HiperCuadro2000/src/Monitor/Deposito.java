package Monitor;

/*
 * Clase encargada de la gestion del deposito de combustible
 * del coche.
 * 
 * @param estado Estado del deposito en un tanto por ciento, inicialmente esta lleno (100%)
 * 
 */
public class Deposito {
	
	private int estado;
	
	/*
	 * Constructor por defecto
	 * 
	 */
	public Deposito(){
		
		this.estado = 100;
		
	}
	
	/*
	 * Funcion sincronizada para la concurrencia que
	 * devuelve el estado en que se encuentra el deposito.
	 * 
	 * @return this.estado Estado del deposito
	 * 
	 */
	synchronized public int get_estado(){
		
		return this.estado;
		
	}
	
	/*
	 * Funcion sincronizada para la concurrencia que agota en
	 * una unidad del tanto por cien el deposito de combustible.
	 * 
	 * Si el deposito está a 0, no se pone en estado negativo
	 * 
	 */
	synchronized public void agotar(){
		
		if( this.estado > 0 )
			this.estado--;
		
	}
	
	/*
	 * Funcion que se encarga de repostar el vehiculo.
	 * Cuando se la llama se cambia el estado del deposito al 100%
	 * 
	 */
	synchronized public void repostar(){
		
		this.estado = 100;
		
	}
	
}
