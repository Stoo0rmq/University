
/* @author BCB
 * 
 * 
 */

package gcsociety;
public class Posicion {
	private int x;
	private int y;
	
	public Posicion(int x,int y){
		this.x = x;
		this.y = y;
	}
	public boolean equals(Posicion pos){
		if(pos.x == this.x && pos.y == this.y)
			return true;
		else return false;
	}
	public String imprimir(){
		String devuelve = x + "," + y;
		return devuelve;
	}
}
