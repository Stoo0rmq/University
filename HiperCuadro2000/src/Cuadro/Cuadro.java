package Cuadro;

/*
 * Clase encargada de el control lógico de los eventos de la palanca
 * 
 * @param calculadorvel Objeto CalculadorVelocidad que gestionara la velocidad en función de la palanca
 * @param hebra1 Hebra encargada del cuadro
 * @param hebra2 Hebra encargada de la distancia
 * @param hebra3 Hebra encargada de la velocidad media
 * @param auto Objeto de la clase Automatico encargado de la posicion de la palanca en automatico
 * @param eje Objeto de la clase Eje encargado de gestionar las revoluciones
 * @param valores Referencia al singleton valores
 * @param last Booleano para no tener errores logicos con el modo automatico
 * 
 * 
 */
public class Cuadro 
{
 
  private CalculadorVelocidad calculadorvel;
  private HebraCuadro hebra1;
  private HebraDistancia hebra2;
  private HebraVelocidad hebra3;
  private Automatico auto;
  private Eje eje;
  
  boolean last;
  
  private Valores valores;
  
  
  /*
   * Constructor por defecto
   * 
   * 
   */
  public Cuadro(){
	  
	valores = Valores.getInstance();
	auto = new Automatico();
	calculadorvel = new CalculadorVelocidad();
	hebra1 =  new HebraCuadro(this);
	hebra2 = new HebraDistancia();
	hebra3 = new HebraVelocidad();
    eje = new Eje();
    
    last = false;
	
  }
  
  /*
   * Funcion que actualiza las revoluciones del eje en base al freno y al acelerador
   * 
   */
  private void actualizar_revoluciones(){
	  
	  if( valores.get_acelerador().leerposicion() * 1500 - valores.get_freno().get_posicion() * 400 > 0){
			eje.incrementarVueltas( valores.get_acelerador().leerposicion() * 1500 - valores.get_freno().get_posicion() * 400 );
			valores.almacenarRevoluciones( eje.devolverVueltas() );
	  }
  }
  
  /*
   * Funcion sincronizada para la concurrencia que se encarga de manejar los eventos
   * de la palanca y la funcion logica asociada.
   * 
   * @param periodo Tiempo entre eventos, necesario para el calculo de la velocidad
   */
  synchronized public void evento(int periodo){
		  
		if(valores.get_motor()== Motor.ENCENDIDO){
			
			if( valores.get_gasolina() == 0 ){
				
				valores.get_acelerador().soltaracelerador();
				valores.get_freno().soltarfreno();
				
				actualizar_revoluciones();
				
			}else if(valores.get_palanca() == Palanca.ACELERAR){
				
				valores.get_acelerador().pisaracelerador();
				valores.get_freno().soltarfreno();
				
				last = false;
				
				actualizar_revoluciones();
				
			}else if( valores.get_palanca() == Palanca.FRENAR ){
				
				valores.get_acelerador().soltaracelerador();
				valores.get_freno().pulsarfreno();
				
				actualizar_revoluciones();
				
			}else if( valores.get_palanca() == Palanca.MANTENIENDO ){
				
				valores.get_freno().soltarfreno();
				
				if( !last ){
					valores.almacenarVelMantenida( valores.leerVelocidad() );
					last = true;
				}
				
				auto.mantenerVelocidad( valores.get_acelerador(), valores.get_freno() );

				actualizar_revoluciones();
				
			}else if( valores.get_palanca() == Palanca.REINICIANDO ){
				
				auto.mantenerVelocidad( valores.get_acelerador(), valores.get_freno() );

				actualizar_revoluciones();
				
			}else{
				
				valores.get_acelerador().soltaracelerador();
				valores.get_freno().soltarfreno();
				
				actualizar_revoluciones();
			}
		
		}else{
			
			valores.get_acelerador().soltaracelerador();
			valores.get_freno().soltarfreno();
			
			valores.almacenarVelMantenida( 0 );
			
			actualizar_revoluciones();
			
		}
		
	
		calculadorvel.calcularVelocidad(periodo);
		
	}
	
  /*
   * Funcion que devuelve la hebra del cuadro
   * 
   * @return hebra1 La hebra en cuestion
   */
	public HebraCuadro get_hebra_cuadro(){
		  
		  return this.hebra1;
		  
	}
	
      /*
	   * Funcion que devuelve la hebra de la distancia
	   * 
	   * @return hebra2 La hebra en cuestion
	   */
	public HebraDistancia get_hebra_distancia(){
		  
		  return this.hebra2;
		  
	}
	
	  /*
	   * Funcion que devuelve la hebra de la velocidad
	   * 
	   * @return hebra3 La hebra en cuestion
	   */
	public HebraVelocidad get_hebra_velocidad(){
		  
		  return this.hebra3;
		  
	}
  
}

  