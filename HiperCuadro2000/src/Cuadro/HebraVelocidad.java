package Cuadro;

/*
 * Clase que implementa la hebra encargada de solicitar actualizaciones 
 * para la velocidad media cada 0.1s
 * 
 * @param INTERVALO intervalo de acción de la hebra en ms
 * @param cd Objeto del calculador de velocidad media
 * 
 */
public class HebraVelocidad extends Thread{
	
	private int INTERVALO = 100;
	CalculadorVelocidadMedia cvm;
	
	/*
	 * Constructor por defecto
	 * 
	 */
	public HebraVelocidad(){
		
		cvm = new CalculadorVelocidadMedia();
		
	}
	
	/*
	 * Runnable de la hebra, lanza peticiones cada INTERVALO al calculador
	 * de velocidad media.
	 * 
	 * (non-Javadoc)
	 * @see java.lang.Thread#run()
	 */
	public void run(){
		while (true){
			try{
				sleep(INTERVALO); // 0.1 s
			}
			catch(InterruptedException excepcion){
				excepcion.printStackTrace();
			}
			
			this.cvm.actualizar();
			
		}
		
		
	}
	
}
