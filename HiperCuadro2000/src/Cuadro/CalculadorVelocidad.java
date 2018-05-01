package Cuadro;

/*
 * Clase para la gestion del calculo de la velocidad en funcion de
 * las revoluciones, el radio del eje y el tiempo
 * 
 * @param vueltasAnteriores Entero en el que se almacenan las vueltas realizadas en
 * el anterior calculo
 * @param val Referencia al singleton valores
 * 
 */
public class CalculadorVelocidad
{
  private int vueltasAnteriores;
  
  private Valores val ;
  
  /*
   * Constructor por defecto
   * 
   * 
   */
  public CalculadorVelocidad(){
	  
	  val =  Valores.getInstance();
	  vueltasAnteriores = val.get_revoluciones();
	  
  }
  
  /*
   * Función sincronizada para la concurrencia que calcula la velocidad
   * 
   * @param periodo El tiempo entre medidas
   * 
   * Calcula primero la velocidad angular y luego la lineal
   * se actualizan las vueltas anteriores medidas y se guarda el
   * valor en valores
   */
  synchronized public void calcularVelocidad(int periodo){
	  
	  double velocidad = 0;
	  int aux = val.get_revoluciones();
	  vueltasAnteriores = aux - vueltasAnteriores;
	  
	  if( vueltasAnteriores > 0){
		
		  velocidad = (vueltasAnteriores * 2 * Math.PI) / periodo ; // Velocidad angular
		  velocidad *= val.radio_eje;	// Velocidad lineal
	  
	  }else{
		  
		  velocidad = 0;
		  
	  }
	  
	  vueltasAnteriores = aux;
	  
	  val.almacenarVelocidad( velocidad );
	  
  }
 
}