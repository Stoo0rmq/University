
/*
 * @author BCB
 * 
 * 
 */
package gcsociety;
import java.util.ArrayList;




public class ListaPosiciones {
	ArrayList<Posicion>lista;
	ArrayList<Integer>repeticiones;
	
	ListaPosiciones(){
		lista = new ArrayList<Posicion>();
		repeticiones = new ArrayList<Integer>();
	}
	public void Add(Posicion pos){
		boolean ENCONTRADO=false;
		
		for(int i=0;i<lista.size();i++){
			if(lista.get(i).equals(pos)){
				ENCONTRADO = true;
				repeticiones.set(i,repeticiones.get(i)+1 );
			}
				
		}
		if(!ENCONTRADO){
		lista.add(pos);
		repeticiones.add(1);
		}
	}
	
	public boolean esta(Posicion pos){
		for(int i=0; i<lista.size();i++){
			if (lista.get(i).equals(pos))
				return true;
		}
		return false;
	}
	public int getRepeticiones(Posicion pos){
		int devolver=0;
		for(int i=0; i<lista.size();i++){
			if (lista.get(i).equals(pos))
				devolver = repeticiones.get(i);
		}
		return devolver;
	}
	public String imprimir(){
		String devuelve = "";
		for(int i=0;i<lista.size();i++){
			devuelve += "\n" + lista.get(i).imprimir() + " " + repeticiones.get(i); 
	
		}
		return devuelve;
	}
}
