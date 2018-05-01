package Cuadro;

/*
 * Clase encargada de almacenar todos los datos necesarios para poder interactuar
 * con la interfaz y el monitor
 * 
 * @param velocidad Double en donde se almacena la velocidad
 * @param distancia Double en donde se almacena la distancia
 * @param velocidad_mantenida Double en donde se almacena la velocidad mantenida
 * @param velocidad_media Double en donde se almacena la velocidad media
 * @param revoluciones Entero en donde se almacena el número de revoluciones total
 * @param gasolina Entero en donde se almacena el nivel de gasolina
 * @param motor Objeto de la clase Motor encargado de decidir si esta encendido o no
 * @param estadoPalanca Objeto de la clase Palanca Enumerado encargado de decir el estado en el que se encuentra la palanca
 * @param freno Objeto de la clase Freno que guarda el nivel de frenado
 * @param acelerador Objeto de la clase Acelerador que guarda el nivel de acelerado
 * @param aceite Booleano que indica si hay que realizar un cambio de aceite
 * @param pastillas Booleano que indica si hay que realizar un cambio de pastillas
 * @param revision Booleano que indica si hay que realizar una revisión
 * @param radio_eje Entero público con el radio del eje
 * 
 */


public class Valores {
	
	private double velocidad;
	private double distancia;
	private double velocidad_mantenida;
	private double velocidad_media;
	private int revoluciones;
	private int gasolina;
	private Motor motor;
	private Palanca estadoPalanca;
	private Freno freno;
	private Acelerador acelerador;
	private boolean aceite;
	private boolean pastillas;
	private boolean revision;
	public int radio_eje = 2;
	
	
	private static Valores INSTANCE = null;
	
	/*
	 * Constructor por defecto
	 * 
	 * 
	 */
	
	
	
	private Valores(){
		
		revoluciones = 0;
		velocidad = 0;
		distancia = 0;
		velocidad_mantenida = 0;
		gasolina = 100;
		motor = Motor.APAGADO;
		estadoPalanca = Palanca.APAGADO;
		freno = new Freno();
		acelerador = new Acelerador();
		aceite = false;
		pastillas = false;
		revision = false;
		
	}
	
	/*
	 * Método para obtener una instancia del singleton Valores
	 * 
	 * 
	 */
	
	
	
	public static Valores getInstance() {
		if( INSTANCE == null){
			
			INSTANCE = new Valores();
			
		}
		return INSTANCE;
	}
	
	
	/*
	 * Método para almacenar la velocidad
	 * @param velocidad2 Double que será guardado en velocidad
	 * 
	 */
	public void almacenarVelocidad(double velocidad2){
		velocidad = velocidad2;
		
	}
	
	
	/*
	   * Método para devolver la velocidad almacenada
	   * 
	   * @return velocidad La velocidad almacenada
	   */
	
	public double leerVelocidad(){
		return velocidad;		
	}
	
	/*
	 * Método para almacenar la velocidad media
	 * @param velocidad_media Double que será guardado en velocidad_media
	 * 
	 */
	public void almacenarVelocidadMedia(double velocidad_media){
		this.velocidad_media = velocidad_media;
		
	}
	/*
	   * Método para devolver la velocidad media almacenada
	   * 
	   * @return velocidad_media La velocidad media almacenada
	   */
	
	public double leerVelocidadMedia(){
		return velocidad_media;		
	}
	
	/*
	 * Método para almacenar la distancia
	 * @param dis Double que será guardado en distancia
	 * 
	 */
	public void almacenarDistancia(double dis){
		distancia = dis;
	}
	
	
	/*
	   * Método para devolver la distancia total guardada
	   * 
	   * @return distancia Distancia total recorrida
	   */
	
	
	public double leerDistancia(){
		return distancia;
	}
	
	/*
	 * Método para almacenar la velocidad mantenida
	 * @param vel Double que será guardado en velocidad_mantenida
	 * 
	 */
	public void almacenarVelMantenida(double vel){
		velocidad_mantenida=vel;
	}
	
	/*
	   * Método para devolver la velocidad mantenida
	   * 
	   * @return velocidad_mantenida La velocidad almacenada
	   */
	
	
	public double leerVelMantenida(){
		return velocidad_mantenida;
	}
	
	/*
	 * Método para almacenar las revoluciones totales
	 * @param revs Double que será guardado en revoluciones
	 * 
	 */
	public void almacenarRevoluciones( int revs ){
		
		this.revoluciones = revs;
		
	}
	
	/*
	   * Método para devolver el número de revoluciones total
	   * 
	   * @return this.revoluciones El número total de revoluciones
	   */
	
	
	
	public int get_revoluciones(){
		
		return this.revoluciones;
		
	}
	
	/*
	 * Método para almacenar posición del freno
	 * @param freno Objeto de tipo Freno que será guardado en this.freno
	 * 
	 */
	public void almacenarFreno( Freno freno ){
		
		this.freno = freno;
		
	}
	
	
	/*
	   * Método para devolver el freno
	   * 
	   * @return this.freno  El freno con su posición y su estado
	   */
	
	public Freno get_freno(){
		
		return this.freno;
		
	}
	
	/*
	 * Método para almacenar la posicion del acelerador
	 * @param acelerador Objeto de tipo Acelerador que será guardado en this.acelerador
	 * 
	 */
	public void almacenarAcelerador( Acelerador acelerador ){
		
		this.acelerador = acelerador;
		
	}
	/*
	   * Método para devolver el acelerador
	   * 
	   * @return this.acelerador  El acelerador con su posición y su estado
	   */
	public Acelerador get_acelerador(){
		
		return this.acelerador;
		
	}
	
	/*
	 * Método para almacenar el nivel de gasolina
	 * @param gasolina Entero que será guardado en gasolina
	 * 
	 */
	public void almacenarGasolina( int gasolina ){
		
		this.gasolina = gasolina;
		
	}
	
	/*
	   * Método para devolver el nivel de gasolina
	   * 
	   * @return this.gasolina  La cantidad de gasolina restante
	   */
	
	public int get_gasolina(){
		
		return this.gasolina;
		
	}
	
	/*
	 * Método para almacenar el estado del motor
	 * @param motor Objeto tipo Motor  que será guardado en motor
	 * 
	 */
	public void almacenarMotor( Motor motor ){
		
		this.motor = motor;
		
	}
	
	/*
	   * Método para devolver el motor 
	   * 
	   * @return motor El motor con su estado
	   */
	
	public Motor get_motor(){
		
		return motor;
		
	}
	/*
	 * Método para almacenar el estado de la palanca
	 * @param estado Objeto tipo Palanca que será guardado en estadoPalanca
	 * 
	 */
	public void almacenarPalanca( Palanca estado ){
		
		this.estadoPalanca = estado;
		
	}
	/*
	   * Método para devolver la palanca
	   * 
	   * @return this.estadoPalanca La palanca y su estado
	   */
	
	public Palanca get_palanca(){
		
		return this.estadoPalanca;
		
	}
	/*
	 * Método para almacenar si hay que cambiar el aceite
	 * @param y Booleano que será guardado en velocidad_media
	 * 
	 */
	public void almacenarAceite( boolean y ){
		
		aceite = y;
		
	}
	
	/*
	   * Método que devuelve si hay que hacer un cambio de aceite
	   * 
	   * @return aceite Booleano de cambio de aceite
	   */
	
	
	
	public boolean get_aceite(){
		
		return aceite;
		
	}
	/*
	 * Método para almacenar si hay que cambiar las pastillas
	 * @param y Booleano que será guardado en pastillas
	 * 
	 */
	public void almacenarPastillas( boolean y ){
		
		pastillas = y;
		
	}
	
	/*
	   * Método que devuelve si hay que hacer un cambio de pastillas
	   * 
	   * @return pastillas Booleano de cambio de pastillas
	   */
	
	
	public boolean get_pastillas(){
		
		return pastillas;
		
	}
	/*
	 * Método para almacenar si hay que hacer una revivisión
	 * @param y Booleano que será guardado en revision
	 * 
	 */
	public void almacenarRevision( boolean y ){
		
		revision = y;
		
	}
	
	/*
	   * Método que devuelve si hay que hacer una revisión
	   * 
	   * @return revision Booleano de cambio de pastillas
	   */
	
	public boolean get_revision(){
		
		return revision;
		
	}
	
}
