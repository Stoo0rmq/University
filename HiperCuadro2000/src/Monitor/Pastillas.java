package Monitor;

/*
 * Clase para gestionar el estado de las pastillas 
 * de freno, hereda de la clase InfoTecnica
 * 
 */
public class Pastillas extends InfoTecnica{
	
	/*
	 * Constructor por defecto
	 * 
	 */
	public Pastillas(){
		
		super();
		
		this.setmaxRevoluciones(100000000);
		
	}
	
}
