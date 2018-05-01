package Monitor;

/*
 * Clase que implementa la hebra encargada de solicitar eventos al monitor
 * de etiquetas en intervalos de 0.5s
 * 
 * @param INTERVALO intervalo de acción de la hebra en ms
 * @param monitor Referencia al monitor de etiquetas
 * 
 */
public class HebraMonitor extends Thread {
	
	private int INTERVALO = 500;
	private Monitor_etiquetas monitor;
	
	/*
	 * Constructor por parametros
	 * 
	 * @param monitor Instancia del objeto Monitor_etiquetas
	 * 
	 */
	public HebraMonitor( Monitor_etiquetas monitor ){
		
		this.monitor = monitor;
		
	}
	
	/*
	 * Runnable de la hebra, lanza peticiones cada INTERVALO al monitor.
	 * 
	 * (non-Javadoc)
	 * @see java.lang.Thread#run()
	 */
	public void run(){
		while(true){			
            try {            
                sleep((long)( INTERVALO )); // 1/2 de segundo
            } catch (InterruptedException e) {
            	e.printStackTrace();
            }
            
            monitor.evento();
            
		}
	}
	
}
