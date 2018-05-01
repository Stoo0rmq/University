package Monitor;

/*
 * Clase Aceite que hereda de InfoTecnica y encargada de la
 * gestion del componente del aceite del coche y su recambio
 * 
 * 
 */
public class Aceite extends InfoTecnica{
	
	/*
	 * Constructor por defecto
	 * 
	 */
	public Aceite(){
		
		super();
		this.setmaxRevoluciones(5000000); 
		
	}
	
}
