package GUI;

/*
 * Observador especifico para los valores de la logica de los
 * botones en funcion de los eventos de la palanca, acelerador y freno.
 * Implementa la interfaz Observador y hereda de Observable
 * 
 */
public class ObservadorBotones extends Observable implements Observador{
	
	/*
	 * Constructor por paramestros
	 * 
	 * @param panel referencia a la instancia del objeto que implementa
	 * Actualizador
	 * 
	 */
	public ObservadorBotones( Actualizador panel ){
		
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
