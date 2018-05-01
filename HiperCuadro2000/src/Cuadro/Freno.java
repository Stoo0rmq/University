package Cuadro;

/*
 * Clase para la gestion del freno del vehículo
 * 
 * @param posicion Potencia del freno en un momento dado de 0 a 10
 * 
 */
public class Freno
{
  private int posicion;
  /*
   * Constructor por defecto
   * 
   */
  public Freno()
  {
    posicion = 0;
  }
  
  /*
   * Decrementa la potencia del freno 
   * 
   */
  public void soltarfreno(){
	  if(posicion > 0)
		  posicion--;
  }
  /*
   * Aumenta la potencia del freno 
   * 
   */
  public void pulsarfreno(){
	  if(posicion < 10)
		  posicion++;
  }
  
  /* 
   * 
   * @return devuelve la potencia del freno en un momento dado
   */
  public int get_posicion(){
	  
	  return this.posicion;
	  
  }
   
}