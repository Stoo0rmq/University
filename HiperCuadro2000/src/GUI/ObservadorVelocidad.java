package GUI;

/*
 * Clase que hereda de Observable e implementa Observador que se encarga
 * especificamente de observar los valores de las velocidades del Applet
 * 
 * 
 */
public class ObservadorVelocidad extends Observable implements Observador{
	
	/*
	 * Constructor por paramestros
	 * 
	 * @param panel referencia a la instancia del objeto que implementa
	 * Actualizador
	 * 
	 */
	public ObservadorVelocidad( Actualizador panel ){
		
		super( panel );
		
	}
	
	/*
	 * Implementa la funcion evento de la interfaz observado.
	 * Actualiza el objeto que implementa la interfaz Actualizador
	 * 
	 * (non-Javadoc)
	 * @see GUI.Observador#evento()
	 */
	public void evento(){
		
		this.observado.actualizar();
		
	}
	
}
