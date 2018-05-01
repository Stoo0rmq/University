package Monitor;

import java.util.ArrayList;

import Cuadro.Valores;

/*
 * Clase encargada de la monitorizacion y gestion
 * de las etiquetas del Applet
 * 
 * @param hebramonitor Referencia a la hebra del monitor
 * @param valores referencia al singleton Valores
 * @param deposito referencia al Objeto Deposito
 * @param tecnico Referencia al Objeto Tecnico
 * @param cont Contador para la gestion del agotamiento del deposito de combustible, inicialmente vale 1
 * 
 */
public class Monitor_etiquetas {
	
	private HebraMonitor hebramonitor;
	private Valores valores;
	private Deposito deposito;
	private Tecnico tecnico;
	private int cont;
	/*
	 * Constructor por parametros.
	 * 
	 * @param deposito Instancia del objeto Deposito
	 * @param tecnico Instancia del objeto Tecnico
	 * 
	 */
	public Monitor_etiquetas( Deposito deposito, Tecnico tecnico ){
		
		valores = Valores.getInstance();
		this.hebramonitor = new HebraMonitor( this );
		this.deposito = deposito;
		this.tecnico = tecnico;
		this.cont = 1;
	}
	
	/*
	 * Funcion sincronizada para la concurrencia que gestiona los eventos
	 * de los componentes que esta clase monitoriza.
	 * 
	 * Si desde la ultima vez k se midio se ha llegado a 4 *10^4 revoluciones
	 * el deposito se agota una unidad y se almacena.
	 * 
	 * Se comprueba cada uno de los componentes tecnicos del vehiculo y si
	 * es necesario realizar una comprobacion, ésta se almacena en valores para
	 * que sea gestionada por el proceso pertinente.
	 * 
	 */
	synchronized public void evento(){
		
		if( valores.get_revoluciones() > 40000 * cont ){
			
			deposito.agotar();
			valores.almacenarGasolina( deposito.get_estado() );
			cont++;
			
		}
		
		ArrayList <Boolean> tecs = tecnico.comprobacion();
		
		if( tecs.get( 0 ) ){
			
			valores.almacenarAceite( true );
			
		}
		if( tecs.get( 1 ) ){
			
			valores.almacenarPastillas( true );
			
		}
		if( tecs.get( 2 ) ){
			
			valores.almacenarRevision( true );
			
		}
			
		
	}
	
	/*
	 * Metodo que devuelve la hebra del monitor.
	 * 
	 * @return this.hebramonitor La hebra del monitor
	 */
	public HebraMonitor get_hebra(){
		
		return this.hebramonitor;
		
	}
	
}
