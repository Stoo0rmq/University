package GUI;

/*
 * Clase observable padre
 * 
 * @param observado Objeto que implementa la interfaz Actualizador.
 * 
 * 
 */
public class Observable {
	
	public Actualizador observado;
	
	/*
	 * Constructor por parametros.
	 * 
	 * @param panel Instancia a refenciar de tipo Actualizador
	 */
	public Observable( Actualizador panel ){
		
		this.observado = panel;
		
	}
	
}
