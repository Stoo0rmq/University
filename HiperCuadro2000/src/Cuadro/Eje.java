package Cuadro;


/*
 * Clase para la gestion del eje del coche
 * 
 * @param radio Radio del eje
 * @param vueltas Rotaciones del eje
 * 
 */
public class Eje {
	
	private int vueltas;
	public int radio = 2;
	
	/*
	 * Constructor por defecto
	 * 
	 * 
	 */
	public Eje(){
		
		this.vueltas = 0;
		
	}
	
	/*
	 * Almacena mas vueltas del eje
	 * 
	 * @param vuel Vueltas a añadir
	 * 
	 */
	public void incrementarVueltas(int vuel){
		vueltas+=vuel;
		
	}
	/*
	 * Resetea el valor de las vueltas
	 * 
	 */
	public void resetear(){
		vueltas=0;
	}
	/*
	 * 
	 * @return vueltas devuelve las vueltas del eje almacenadas
	 */
	public int devolverVueltas(){
		return vueltas;
	}
}
