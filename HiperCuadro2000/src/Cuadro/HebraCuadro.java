package Cuadro;

/*
 * Clase que implementa la hebra encargada de solicitar eventos al cuadro
 * en intervalos de 1s
 * 
 * @param INTERVALO intervalo de acción de la hebra en ms
 * @param c Referencia al cuadro
 * 
 */
public class HebraCuadro extends Thread {
	private int INTERVALO=1000;
	private Cuadro c;
	
	/*
	 * Constructor por defecto de la hebra
	 * 
	 */
	public HebraCuadro(Cuadro c) {
		
		this.c = c;
		
	}
	/*
	 * Runnable de la hebra, lanza peticiones cada INTERVALO al cuadro.
	 * 
	 * (non-Javadoc)
	 * @see java.lang.Thread#run()
	 */
	public void run(){
		
		while (true){
			try{
				sleep(INTERVALO); // 1s
			}
			catch(InterruptedException excepcion){
				excepcion.printStackTrace();
			}
			
			this.c.evento(INTERVALO); 
		}
		
		
	}
}
