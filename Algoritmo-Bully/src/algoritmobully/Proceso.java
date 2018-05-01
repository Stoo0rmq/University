// Desarrollo de Sistemas Distribuidos
// Grado en Ingeniería Informática
// 
// Curso 2015/2016
// 
// Borja Cañavate Bordons
// Baltasar Ruiz Hernández
// Juan José Jiménez García
//
// 3º Ingeniería del Software
// 
// Trabajo del Tema 3
// Algoritmo Valentón (Bully)
//
// Proceso.java

package algoritmobully;

public class Proceso extends Thread{  
    private int id ;
    private int estado ;
    private int jefe ;
    private boolean mepresentocomocandidato ;
    private boolean jefeconocido ;
    private boolean hedetectadoerror;
    
    public static final int CORRIENDO = 0 ;
    public static final int ERROR = 1 ;
    
    private static int NUMPROCESOS ;
    
    private Controlador controlador ;
    
    public Proceso (int elid, int eljefe){
        this.id = elid ;
        this.estado = CORRIENDO ;
        this.jefe = eljefe ;
        this.mepresentocomocandidato = false ;
        this.jefeconocido = true ;
    }
    
    public int getEstado(){
        return estado ;
    }
    
    public int getIdentificador() {
        return id;
    }
    
    public boolean soyCandidato() {
        return mepresentocomocandidato;
    }
    
    public boolean heDetectadoError() {
        return hedetectadoerror;
    }
    
    public static int getNumProcesos(){
        return NUMPROCESOS ;
    }
    
    public int getJefe(){
        return jefe ;
    }
    
    public void setEstado (int elestado){
        this.estado = elestado ;
    }
    
    public static void setNumProcesos (int numprocesos){
        NUMPROCESOS = numprocesos ;
    }
    
    public void setControlador (Controlador elcontrolador){
        this.controlador = elcontrolador ;
    }
    
    public int notificarCaidaCoordinador(int idremitente){
        int resultado = 0 ;
        
        if (estado == ERROR)
            resultado = -1 ;
        
        else if (estado == CORRIENDO){
            resultado = 1 ;
            // System.out.println("SOY LA HEBRA " + id + " Y VOY A REALIZAR ELECCIONES DESDE EL NOTIFICARCAIDA") ;
            
            if (!mepresentocomocandidato)
                realizarElecciones() ;
        }
        
        return resultado ;
    }
    
    public void notificarCambioCoordinador (int elnuevojefe){
        this.jefe = elnuevojefe ;
        mepresentocomocandidato = false ;
        jefeconocido = true ;
        System.out.println("Soy la hebra " + id + " y he recibido la notificación de cambio de jefe (ahora es el " + elnuevojefe + ")") ;
    }
    
    /*
        A este método se accederá si y solo si alguno de los procesos en estado
        CORRIENDO detecta que ha fallado el maestro, según la definición del algoritmo
        varios procesos pueden entrar en este método a la vez
    */
    
    @SuppressWarnings({"CallToPrintStackTrace", "SleepWhileInLoop"})
    public void realizarElecciones(){
        this.mepresentocomocandidato = true ;
        this.jefeconocido = false ;

        boolean soyeljefe = true ;
        
        System.out.println("SOY LA HEBRA " + id + " Y VOY A REALIZAR ELECCIONES") ;

        for (int i = id + 1 ; i < NUMPROCESOS && estado == Proceso.CORRIENDO ; i++){ 
            if (!jefeconocido){
                System.out.println("Soy la hebra " + id + " y solicito respuesta a la hebra " + i) ; // La hebra que solicite elecciones, debe enviar un mensaje y recibir una respuesta

                int resultadomensaje = controlador.getProcesoConID(i).notificarCaidaCoordinador(id) ; // 

                System.out.println("Soy la hebra " + id + " y he recibido la respuesta " + resultadomensaje + " de la hebra " + i) ;

                if (resultadomensaje == 1){ // Si la respuesta del proceso al que se le ha preguntado el estado es 1, entonces es una buena respuesta
                    soyeljefe = false ; // IMPORTANTE: si soyeljefe nunca pasa a false, entonces somos el mejor proceso
                    int cuentaatras = 0 ;
                    while (cuentaatras < 10){ // Si se llega a la cuenta atras, entonces hay que convocar elecciones nuevamente, no hemos recibido las respuestas que esperabamos
                        try{
                            sleep(25) ;
                            if (jefeconocido)
                                break ;
                            else
                                cuentaatras++ ;
                        }
                        catch (Exception e){
                            e.printStackTrace() ;
                        }
                    }
                    
                    if (cuentaatras == 10){
                        System.out.println("Soy la hebra " + id + " y he esperado demasiado, inicio nuevas elecciones") ;
                        realizarElecciones() ;
                    }
                }
            }
        }

        if (soyeljefe && !jefeconocido && estado == Proceso.CORRIENDO){  // Si ningun proceso ha respondido, entonces yo soy el mas prioritario y me proclamo maestro
            try{
                Thread.sleep(100) ;
            }
            catch (InterruptedException e){
                e.printStackTrace() ;
            }
            System.out.println("Soy la hebra " + id + " y soy el nuevo jefe") ;
            for (int i = 0 ; i < NUMPROCESOS ; i++){
                if (controlador.getProcesoConID(i).getEstado() == Proceso.CORRIENDO)
                    controlador.getProcesoConID(i).notificarCambioCoordinador(this.id) ;
            }
        }
    }
    
    @Override
    @SuppressWarnings({"SleepWhileInLoop", "CallToPrintStackTrace"})
    public void run(){
        while (true){
            try{
                sleep(1000) ; // Cada segundo, el proceso comprobará si el maestro ha fallado
            }
            catch(InterruptedException e){
                e.printStackTrace() ;
            }
            
            if (id != jefe && estado == Proceso.CORRIENDO){ // Si no somos el jefe y nuestro estado es CORRIENDO
                int estadodeljefe = controlador.getProcesoConID(jefe).getEstado() ;
                System.out.println("Soy la hebra " + id + " y el estado de mi jefe (ID " + jefe + ") es " + estadodeljefe) ;
            
                if (estadodeljefe == Proceso.ERROR){ // Si detectamos que el maestro ha fallado
                    System.out.println("Soy la hebra " + id + " y he detectado un error en mi jefe") ;
                    hedetectadoerror = true;
                    // System.out.println("SOY LA HEBRA " + id + " Y VOY A REALIZAR ELECCIONES DESDE EL RUN") ;
                    
                    if (!mepresentocomocandidato) // Si el maestro ha fallado tendremos que realizar elecciones
                        realizarElecciones() ;
                }
                else {
                    hedetectadoerror = false;
                }
            }
        }
    }
}
