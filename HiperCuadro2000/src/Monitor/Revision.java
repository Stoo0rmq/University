package Monitor;

/*
 * Clase para gestionar la revision general del sistema
 * Hereda de la clase InfoTecnica
 * 
 */
public class Revision extends InfoTecnica{
	
	/*
	 * Constructor por defecto.
	 * Hace uso del constructor de la clase padre
	 * 
	 * @param maxRevoluciones maxRevoluciones = 10^9
	 * 
	 */
	public Revision(){
			
		super();
		this.setmaxRevoluciones(1000000000);
		
	}
}
