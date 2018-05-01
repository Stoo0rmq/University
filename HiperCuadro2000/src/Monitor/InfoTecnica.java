package Monitor;

import Cuadro.Valores;

/*
 * Clase padre para la jerarquia de componentes de recambio.
 * Se encarga de hacer comprobaciones cuando se le pide
 * para saber si es necesario un cambio de componente.
 * Cada clase hija tendra un numero de revoluciones maxima propia.
 * 
 * @param maxRevoluciones revoluciones maximas que puede soportar una pieza
 * antes de ser cambiada
 * @param numcambios Entero inicializado a 1 para gestionar mas de 1 cambio
 * @valores referencias al singleton Valores
 * 
 * 
 */
public class InfoTecnica {
	
	private int maxRevoluciones;
	 private int numcambios;
	private Valores valores;
	
	/*
	 * Constructor por defecto
	 * 
	 */
	public InfoTecnica(){
		
		valores = Valores.getInstance();
		this.numcambios = 1;
		
	}
	
	/*
	 * Funcion para la comprobacion del cambio de componente.
	 * En caso de necesitar un cambio se incrementa el numero de cambios en 1.
	 * 
	 * @return boolean devuelve 1 si es necesario cambio, y 0 en caso contrario
	 */
	public boolean comprobacion( ){
		
		if( valores.get_revoluciones() <= maxRevoluciones*numcambios )
			return false;
		else{
			
			numcambios++;
			return true;
		}
			
		
	}
	
	public int getmaxRevoluciones(){
		return maxRevoluciones;
	}
	public void setmaxRevoluciones(int valor){
			maxRevoluciones=valor;
	}
	
}
