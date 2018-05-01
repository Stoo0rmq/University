package Cuadro;

/*
 * Clase que implementa la hebra encargada de solicitar actualizaciones 
 * para la distancia cada 0.1s
 * 
 * @param INTERVALO intervalo de acción de la hebra en ms
 * @param cd Objeto del calculador de distancia
 * 
 */
public class HebraDistancia extends Thread {
	
	private int INTERVALO=100;
	CalculadorDistancia cd;
	
	/*
	 * Constructor por defecto
	 * 
	 */
	public HebraDistancia(){
		
		this.cd = new CalculadorDistancia();
		
	}
	
	/*
	 * Runnable de la hebra, lanza peticiones cada INTERVALO al calculador de velocidad.
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
			
			this.cd.actualizar(INTERVALO); 
		}
		
		
	}
	
}
