package Monitor;

import java.util.ArrayList;
import java.util.List;

/*
 * Clase que gestiona todas las comprobaciones de los componentes del coche.
 * 
 * @param elementos Lista que agrupa los componentes especificos del coche.
 * 
 */
public class Tecnico {
	
	private List <InfoTecnica> elementos;
	
	/*
	 * Constructor por defecto
	 * 
	 */
	public Tecnico(){
		
		this.elementos = new ArrayList<>();
		
	}
	
	/*
	 * Funcion para añadir elementos al array de componentes
	 * elementos.
	 * 
	 * @param elemento Elemento a añadir
	 * 
	 */
	public void addElemento( InfoTecnica elemento ){
		
		this.elementos.add( elemento );
		
	}
	
	/*
	 * Funcion que llama a cada uno de los componentes 
	 * almacenados para realizar las comprobaciones individuales
	 * pertinentes.
	 * 
	 * @return resultado Array de booleanos con el estado de cada componente.
	 * 
	 */
	public ArrayList<Boolean> comprobacion(){
		
		ArrayList <Boolean> resultado = new ArrayList<>();
		
		for( InfoTecnica e: elementos){
			
			resultado.add( e.comprobacion() );
			
		}
		
		return resultado;
		
	}
	
}
