package Monitor;

import Cuadro.Valores;

/*
 * Clase que gestiona los eventos que tienen lugar en el Monitor
 * 
 * @param valores referencia al singleton Valores
 * @param deposito referencia a la instancia del objeto Deposito
 * 
 */
public class Eventos {
	
	private Valores valores;
	private Deposito deposito;
	
	/*
	 * Constructor por parametros
	 * 
	 * @param deposito instancia del objeto deposito
	 */
	public Eventos( Deposito deposito ){
		
		valores = Valores.getInstance();
		this.deposito = deposito;
	}
	
	/*
	 * Funcion sincronizada para la concurrencia
	 * que gestiona el reseteo del deposito cuando
	 * se pide.
	 * 
	 */
	synchronized public void repostar(){
		
		deposito.repostar();
		valores.almacenarGasolina( 100 );
		
	}
	
	/*
	 * Funcion sincronizada para la concurrencia
	 * que gestiona el cambio de aceite cuando
	 * se pide.
	 * 
	 */
	synchronized public void cambioAceite(){
		
		valores.almacenarAceite( false );
		
	}
	
	/*
	 * Funcion sincronizada para la concurrencia
	 * que gestiona el cambio de pastillas de freno
	 * cuando se pide.
	 * 
	 */
	synchronized public void cambioPastillas(){
		
		valores.almacenarPastillas( false );
		
	}
	
	/*
	 * Funcion sincronizada para la concurrencia
	 * que gestiona la revision general cuando se 
	 * pide.
	 * 
	 */
	synchronized public void revision(){
		
		valores.almacenarRevision( false );
		
	}
	
}
