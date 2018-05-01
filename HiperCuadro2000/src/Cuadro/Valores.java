package Cuadro;

/*
 * Clase encargada de almacenar todos los datos necesarios para poder interactuar
 * con la interfaz y el monitor
 * 
 * @param velocidad Double en donde se almacena la velocidad
 * @param distancia Double en donde se almacena la distancia
 * @param velocidad_mantenida Double en donde se almacena la velocidad mantenida
 * @param velocidad_media Double en donde se almacena la velocidad media
 * @param revoluciones Entero en donde se almacena el n�mero de revoluciones total
 * @param gasolina Entero en donde se almacena el nivel de gasolina
 * @param motor Objeto de la clase Motor encargado de decidir si esta encendido o no
 * @param estadoPalanca Objeto de la clase Palanca Enumerado encargado de decir el estado en el que se encuentra la palanca
 * @param freno Objeto de la clase Freno que guarda el nivel de frenado
 * @param acelerador Objeto de la clase Acelerador que guarda el nivel de acelerado
 * @param aceite Booleano que indica si hay que realizar un cambio de aceite
 * @param pastillas Booleano que indica si hay que realizar un cambio de pastillas
 * @param revision Booleano que indica si hay que realizar una revisi�n
 * @param radio_eje Entero p�blico con el radio del eje
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
	 * M�todo para obtener una instancia del singleton Valores
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
	 * M�todo para almacenar la velocidad
	 * @param velocidad2 Double que ser� guardado en velocidad
	 * 
	 */
	public void almacenarVelocidad(double velocidad2){
		velocidad = velocidad2;
		
	}
	
	
	/*
	   * M�todo para devolver la velocidad almacenada
	   * 
	   * @return velocidad La velocidad almacenada
	   */
	
	public double leerVelocidad(){
		return velocidad;		
	}
	
	/*
	 * M�todo para almacenar la velocidad media
	 * @param velocidad_media Double que ser� guardado en velocidad_media
	 * 
	 */
	public void almacenarVelocidadMedia(double velocidad_media){
		this.velocidad_media = velocidad_media;
		
	}
	/*
	   * M�todo para devolver la velocidad media almacenada
	   * 
	   * @return velocidad_media La velocidad media almacenada
	   */
	
	public double leerVelocidadMedia(){
		return velocidad_media;		
	}
	
	/*
	 * M�todo para almacenar la distancia
	 * @param dis Double que ser� guardado en distancia
	 * 
	 */
	public void almacenarDistancia(double dis){
		distancia = dis;
	}
	
	
	/*
	   * M�todo para devolver la distancia total guardada
	   * 
	   * @return distancia Distancia total recorrida
	   */
	
	
	public double leerDistancia(){
		return distancia;
	}
	
	/*
	 * M�todo para almacenar la velocidad mantenida
	 * @param vel Double que ser� guardado en velocidad_mantenida
	 * 
	 */
	public void almacenarVelMantenida(double vel){
		velocidad_mantenida=vel;
	}
	
	/*
	   * M�todo para devolver la velocidad mantenida
	   * 
	   * @return velocidad_mantenida La velocidad almacenada
	   */
	
	
	public double leerVelMantenida(){
		return velocidad_mantenida;
	}
	
	/*
	 * M�todo para almacenar las revoluciones totales
	 * @param revs Double que ser� guardado en revoluciones
	 * 
	 */
	public void almacenarRevoluciones( int revs ){
		
		this.revoluciones = revs;
		
	}
	
	/*
	   * M�todo para devolver el n�mero de revoluciones total
	   * 
	   * @return this.revoluciones El n�mero total de revoluciones
	   */
	
	
	
	public int get_revoluciones(){
		
		return this.revoluciones;
		
	}
	
	/*
	 * M�todo para almacenar posici�n del freno
	 * @param freno Objeto de tipo Freno que ser� guardado en this.freno
	 * 
	 */
	public void almacenarFreno( Freno freno ){
		
		this.freno = freno;
		
	}
	
	
	/*
	   * M�todo para devolver el freno
	   * 
	   * @return this.freno  El freno con su posici�n y su estado
	   */
	
	public Freno get_freno(){
		
		return this.freno;
		
	}
	
	/*
	 * M�todo para almacenar la posicion del acelerador
	 * @param acelerador Objeto de tipo Acelerador que ser� guardado en this.acelerador
	 * 
	 */
	public void almacenarAcelerador( Acelerador acelerador ){
		
		this.acelerador = acelerador;
		
	}
	/*
	   * M�todo para devolver el acelerador
	   * 
	   * @return this.acelerador  El acelerador con su posici�n y su estado
	   */
	public Acelerador get_acelerador(){
		
		return this.acelerador;
		
	}
	
	/*
	 * M�todo para almacenar el nivel de gasolina
	 * @param gasolina Entero que ser� guardado en gasolina
	 * 
	 */
	public void almacenarGasolina( int gasolina ){
		
		this.gasolina = gasolina;
		
	}
	
	/*
	   * M�todo para devolver el nivel de gasolina
	   * 
	   * @return this.gasolina  La cantidad de gasolina restante
	   */
	
	public int get_gasolina(){
		
		return this.gasolina;
		
	}
	
	/*
	 * M�todo para almacenar el estado del motor
	 * @param motor Objeto tipo Motor  que ser� guardado en motor
	 * 
	 */
	public void almacenarMotor( Motor motor ){
		
		this.motor = motor;
		
	}
	
	/*
	   * M�todo para devolver el motor 
	   * 
	   * @return motor El motor con su estado
	   */
	
	public Motor get_motor(){
		
		return motor;
		
	}
	/*
	 * M�todo para almacenar el estado de la palanca
	 * @param estado Objeto tipo Palanca que ser� guardado en estadoPalanca
	 * 
	 */
	public void almacenarPalanca( Palanca estado ){
		
		this.estadoPalanca = estado;
		
	}
	/*
	   * M�todo para devolver la palanca
	   * 
	   * @return this.estadoPalanca La palanca y su estado
	   */
	
	public Palanca get_palanca(){
		
		return this.estadoPalanca;
		
	}
	/*
	 * M�todo para almacenar si hay que cambiar el aceite
	 * @param y Booleano que ser� guardado en velocidad_media
	 * 
	 */
	public void almacenarAceite( boolean y ){
		
		aceite = y;
		
	}
	
	/*
	   * M�todo que devuelve si hay que hacer un cambio de aceite
	   * 
	   * @return aceite Booleano de cambio de aceite
	   */
	
	
	
	public boolean get_aceite(){
		
		return aceite;
		
	}
	/*
	 * M�todo para almacenar si hay que cambiar las pastillas
	 * @param y Booleano que ser� guardado en pastillas
	 * 
	 */
	public void almacenarPastillas( boolean y ){
		
		pastillas = y;
		
	}
	
	/*
	   * M�todo que devuelve si hay que hacer un cambio de pastillas
	   * 
	   * @return pastillas Booleano de cambio de pastillas
	   */
	
	
	public boolean get_pastillas(){
		
		return pastillas;
		
	}
	/*
	 * M�todo para almacenar si hay que hacer una revivisi�n
	 * @param y Booleano que ser� guardado en revision
	 * 
	 */
	public void almacenarRevision( boolean y ){
		
		revision = y;
		
	}
	
	/*
	   * M�todo que devuelve si hay que hacer una revisi�n
	   * 
	   * @return revision Booleano de cambio de pastillas
	   */
	
	public boolean get_revision(){
		
		return revision;
		
	}
	
}
