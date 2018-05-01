package Cuadro;

/*
 *  Clase para manejar el acelerador del sistema
 *  
 * 	@param posicion Posicion int del 0 al 10 del acelerador, inicialmente vale 0
 */

public class Acelerador
{
	 private int posicion;
	 
	 /*
	  * Constructor por defecto
	  * 
	  */
	 public Acelerador(){
		 posicion=0;
	 }
	 
	 /*
	  * Aumenta la potencia de aceleracion
	  * 
	  */
	 public void pisaracelerador(){
		 if(posicion < 10)
			 posicion++;
	 }
	 /*
	  * Decrementa la potencia de aceleracion
	  * 
	  */
	 public void soltaracelerador(){
		 if(posicion > 0)
			 posicion--;
	 }
	 
	 /*
	  * Devuelve la potencia del acelerador
	  * @return posicion posicion del acelerador
	  */
	 public int leerposicion(){
		 return posicion;
	 }

}