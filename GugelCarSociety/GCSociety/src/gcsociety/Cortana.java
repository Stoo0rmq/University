/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gcsociety;

import com.eclipsesource.json.Json;
import com.eclipsesource.json.JsonArray;
import com.eclipsesource.json.JsonObject;
import es.upv.dsic.gti_ia.core.ACLMessage;
import es.upv.dsic.gti_ia.core.AgentID;
import es.upv.dsic.gti_ia.core.SingleAgent;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

/**
 *
 * @author Borja, Agustin y Jose
 */

/*
Cortana gestiona todos los agentes vehiculos
toma las decisiones y realiza los cálculos.

Cortana se inicia desde GCSociety 


*/
    public class Cortana extends SingleAgent{
        private Mapa map=null;
        private final int NOROESTE=0,NORTE=1,NORESTE=2,OESTE=3,ESTE=5,SUROESTE=6,SUR=7,SURESTE=8;
        private final int LOGIN=0, PROCESARPROPIEDADES=1, DECIDIRESTRATEGIA=2, RECIBIR=3, DECIDIRACCION=4, FIN=5;
        private int estadoActual;
        private String nombreMapa;
        private boolean finalizado;
        private ACLMessage entrada, salida;
        private String clave;
        private String conversationID;
        private ArrayList<SensoresVehiculo> listaVehiculos;
        private boolean nohayMapa;
        private int estrategia;
        private boolean alcanzado00;
        private String anterior;
        private SensoresVehiculo vehiculoSeleccionado;
        int contador = 0;
        int ultima_x;
        int ultima_y;
        private int NUM_VEHICULOS = 4;
        
        public Cortana(AgentID aid,String nombremapa) throws Exception {
            super(aid);
            nombreMapa = nombremapa;
        }
        
        public Cortana(AgentID aid,Mapa map) throws Exception {
            super(aid);
            this.map = map;
            
        }
        
        
        @Override
        public void init(){
            System.out.println("\nAgente "+this.getName()+" iniciado.");
            finalizado = false;
            estadoActual = LOGIN;
            nohayMapa = false;
            listaVehiculos = new ArrayList<>();
            estrategia = 0;
            alcanzado00= false;
        }
        
        @Override
        public void execute(){

            System.out.println("Agente "+this.getName()+" ejecutando.");
            
            while(!finalizado){
                switch(estadoActual){
                    case LOGIN:
                        login();
                    break;
                    
                    case PROCESARPROPIEDADES:
                        asignarVehiculos();
                    break;
                    
                    case DECIDIRESTRATEGIA:
                        decidirEstrategia();
                    break;
                    
                    case DECIDIRACCION:
                        decidirAccion();
                    break;
                    
                    case RECIBIR:
                        recibirMensajes();
                    break;
                    
                    case FIN:
                        finalizarAgentes();
                    break;
                }
            }

        }

        public void login(){

            JsonObject mensajelogin = new JsonObject();
            
            if(map == null){
                mensajelogin.add("world",nombreMapa);
                nohayMapa = true;
                map = new Mapa(nombreMapa);
            }
            else
                mensajelogin.add("world",map.getNombre());
            
            crearEnviar("Jih",mensajelogin.toString(),ACLMessage.SUBSCRIBE);

            try {
                entrada = receiveACLMessage();
                
                if(entrada.getPerformativeInt() == ACLMessage.INFORM){
                    
                    conversationID = entrada.getConversationId();
                    System.out.println("Agnte: "+this.getName() + " recibe CID:"+conversationID);
                    
                    JsonObject mensajeCheck = new JsonObject();
                    mensajeCheck.add("command", "checkin");
                    
                    crearEnviar(GCSociety.n1,mensajeCheck.toString(),ACLMessage.REQUEST,conversationID);
                    crearEnviar(GCSociety.n2,mensajeCheck.toString(),ACLMessage.REQUEST,conversationID);
                    crearEnviar(GCSociety.n3,mensajeCheck.toString(),ACLMessage.REQUEST,conversationID);
                    crearEnviar(GCSociety.n4,mensajeCheck.toString(),ACLMessage.REQUEST,conversationID);        
                    
                    estadoActual = PROCESARPROPIEDADES;
  
                }
                else if(entrada.getPerformativeInt() == ACLMessage.NOT_UNDERSTOOD){
                    JsonObject respuestaCheck = Json.parse(entrada.getContent()).asObject();
                    System.err.println("ERROR NOT_UNDERSTOOD en el login del agente: "+this.getName()+" "+ respuestaCheck.get("details").asString());
                }
            } 
            catch (InterruptedException e) {
                estadoActual = FIN;
                System.err.println("Error al recibir entrada, en Login. " + e.getLocalizedMessage());
            }
 
        }

    private void asignarVehiculos() { //Estado procesarpropiedades.
        System.out.println("Cortana procesando capabilities");
        
        for(int i = 0; i < NUM_VEHICULOS; i++){ //Recibimos el mensaje de todos los vehiculos
            
            try{
                entrada = receiveACLMessage(); //Recibimos mensaje del los vehiculos

                    if(entrada.getPerformativeInt() == ACLMessage.UNKNOWN){
                        System.out.println("Cortana recibe el tipo del agente: "+entrada.getSender().getLocalName()+", que es: "+entrada.getContent());
                        //Parseamos el Json para sacar el tipo.
                        JsonObject respuestaTipoVehiculo = Json.parse(entrada.getContent()).asObject();
                        int tipo = respuestaTipoVehiculo.get("tipoVehiculo").asInt();

                        SensoresVehiculo s = new SensoresVehiculo();
                        s.setTipoVehiculo(tipo);
                        s.setNombre(entrada.getSender().getLocalName());
                        listaVehiculos.add(s);

                        //estadoActual = DECIDIRACCION;
                    }
            }
            catch (InterruptedException e) {
                estadoActual = FIN;
                System.err.println("Error al recibir entrada, en Login. " + e.getLocalizedMessage());
            }
        
        }
        
        estadoActual = DECIDIRESTRATEGIA;//Elegimos la estrategia a seguir.
    }
    
    public void decidirAccion(){
        //Comprobamo si hay almenos 1 vehiculo disponible
        //Si hay almenos 1 continuamos.
        if(comprobarSiHayVehiculoDisponible()){
            String accion = "moveE";
            
            if(estrategia == 1){//Buscar desde el principio
                if(contador > 1000){// para limitar el numero de pasos y que no se haga muy pesao, solo para pruebas.
                    map.guardarMapa(vehiculoSeleccionado.getX(), vehiculoSeleccionado.getY());
                    estadoActual=FIN;
                }
                else if(vehiculoSeleccionado.getBattery() < 5 && vehiculoSeleccionado.getEnergy() >= 5){
                    crearEnviar(vehiculoSeleccionado.getNombre(), "refuel", ACLMessage.REQUEST);
                    estadoActual = RECIBIR;
                }
                else{
                    ArrayList<Integer>radar = vehiculoSeleccionado.getRadar();
                    map.rellenarRadar(radar.get(4), vehiculoSeleccionado.getX(), vehiculoSeleccionado.getY());
                    if(vehiculoSeleccionado.getX() == 0 && vehiculoSeleccionado.getY() == 0 ){
                        alcanzado00=true;
                    }
                    if(alcanzado00){
                        contador++;
                        // Si esta en una esquina, termina
                        if((radar.get(3) == 2 && radar.get(6) == 2 && radar.get(7) == 2) || (radar.get(5) == 2 && radar.get(7) == 2 && radar.get(8) == 2) ){
                            //Ya hemos terminado de buscar (habria que guardar el mapa), tenemos que ir al objetivo.
                            map.guardarMapa(vehiculoSeleccionado.getX(), vehiculoSeleccionado.getY());//Guardamos el mapa.
                            System.out.println("Se acabo");
                            //estadoActual = FIN;
                            //Heuristica e ir al objetivo.
                            estrategia = 3;
                        }
                        else{
                            if(anterior.equals("moveE") && radar.get(5) != 2 && radar.get(5) != 4){
                                accion = "moveE";
                            } 
                            else if(anterior.equals("moveW") && radar.get(3) != 2 && radar.get(3) != 4){
                                accion = "moveW";
                            }                 
                            else if(radar.get(5) == 2 || radar.get(5) == 4){// Borde del mundo o vehiculo derecha
                                accion = "moveS";
                                if(vehiculoSeleccionado.getX() == 99 && vehiculoSeleccionado.getY() == 99)
                                    accion = "moveW";
                                else if(anterior.equals("moveS"))
                                    accion = "moveW";                    
                            }
                            else if(radar.get(3) == 2 || radar.get(3) == 4){// Borde del mundo o vehiculo izquierda
                                accion = "moveS";
                                if(vehiculoSeleccionado.getX() == 0 && vehiculoSeleccionado.getY() == 0)
                                    accion = "moveE";
                                else if(anterior.equals("moveS"))
                                    accion = "moveE";                    
                            }
                            else if(radar.get(7) == 2 || radar.get(7) == 4){// Borde del mundo o vehiculo abajo
                                accion = "moveE";
                            }
                            else if(radar.get(1) == 2 || radar.get(1) == 4){// Borde del mundo o vehiculo arriba
                                accion = "moveE";
                            }
                            
                            anterior = accion;
                            crearEnviar(vehiculoSeleccionado.getNombre(), accion, ACLMessage.REQUEST);
                            vehiculoSeleccionado.getRadar().clear();
                            estadoActual = RECIBIR;
                        }
                    }
                    else if(!alcanzado00){
                        if(vehiculoSeleccionado.getX() > 0 && vehiculoSeleccionado.getY() > 0){
                            //Diagonal arriba izquierda
                            accion = "moveNW";
                            if(radar.get(0) == 4)
                                accion = "moveW";//Si hay un vehiculo en la diagonal me muevo a la izquierda po ejemplo.
                        }
                        else if(vehiculoSeleccionado.getX() > 0 && vehiculoSeleccionado.getY() == 0){
                            //Diagonal arriba derecha
                            accion = "moveW";
                            if(radar.get(3) == 4)
                                accion = "moveS";//Si hay un vehiculo en la diagonal me muevo a la izquierda po ejemplo.
                        }
                        else if(vehiculoSeleccionado.getX() == 0 && vehiculoSeleccionado.getY() > 0){
                            //Diagonal abajo izquierda
                            accion = "moveN";
                            if(radar.get(1) == 4)
                                accion = "moveE";//Si hay un vehiculo en la diagonal me muevo a la izquierda po ejemplo.
                        }
                        
                        anterior = accion;
                        crearEnviar(vehiculoSeleccionado.getNombre(), accion, ACLMessage.REQUEST);
                        vehiculoSeleccionado.getRadar().clear();
                        estadoActual = RECIBIR;
                    }
                }
            }
            else if(estrategia == 2){//Seguir buscando por donde ibamos. 
                ArrayList<Integer>radar = vehiculoSeleccionado.getRadar();
                map.rellenarRadar(radar.get(4), vehiculoSeleccionado.getX(), vehiculoSeleccionado.getY());
                
                if(vehiculoSeleccionado.getBattery() < 5 && vehiculoSeleccionado.getEnergy() >= 5){
                    crearEnviar(vehiculoSeleccionado.getNombre(), "refuel", ACLMessage.REQUEST);
                    estadoActual = RECIBIR;
                }
                else if(vehiculoSeleccionado.getX() == ultima_x && vehiculoSeleccionado.getY() == ultima_y){
                    //Ya hemos llegado.
                    estrategia = 1;
                    alcanzado00 = true;
                }
                else{
                    if(vehiculoSeleccionado.getX() > ultima_x && vehiculoSeleccionado.getY() > ultima_y){
                        //Diagonal arriba izquierda
                        accion = "moveNW";
                        if(radar.get(0) == 4)
                            accion = "moveW";
                    }
                    else if(vehiculoSeleccionado.getX() < ultima_x && vehiculoSeleccionado.getY() > ultima_y){
                        //Diagonal arriba derecha
                        accion = "moveNE";
                        if(radar.get(2) == 4)
                            accion = "moveE";
                    }
                    else if(vehiculoSeleccionado.getX() > ultima_x && vehiculoSeleccionado.getY() < ultima_y){
                        //Diagonal abajo izquierda
                        accion = "moveSW";
                        if(radar.get(6) == 4)
                            accion = "moveW";
                    }
                    else if(vehiculoSeleccionado.getX() < ultima_x && vehiculoSeleccionado.getY() < ultima_y){
                        //Diagonal abajo derecha
                        accion = "moveSE";
                        if(radar.get(8) == 4)
                            accion = "moveE";
                    }
                    else if(vehiculoSeleccionado.getX() == ultima_x && vehiculoSeleccionado.getY() > ultima_y){
                        //Diagonal subir 
                        accion = "moveN";
                        if(radar.get(1) == 4 && vehiculoSeleccionado.getX() > 0)
                            accion = "moveW";
                        else if(radar.get(1) == 4 && vehiculoSeleccionado.getX() == 0)
                            accion = "moveE";
                    }
                    else if(vehiculoSeleccionado.getX() == ultima_x && vehiculoSeleccionado.getY() < ultima_y){
                        //Diagonal bajar 
                        accion = "moveS";
                        if(radar.get(7) == 4 && vehiculoSeleccionado.getX() > 0)
                            accion = "moveW";
                        else if(radar.get(7) == 4 && vehiculoSeleccionado.getX() == 0)
                            accion = "moveE";
                    }
                    else if(vehiculoSeleccionado.getX() > ultima_x && vehiculoSeleccionado.getY() == ultima_y){
                        //Diagonal subir 
                        accion = "moveW";
                        if(radar.get(3) == 4 && vehiculoSeleccionado.getY() > 0)
                            accion = "moveN";
                        else if(radar.get(3) == 4 && vehiculoSeleccionado.getY() == 0)
                            accion = "moveS";
                    }
                    else if(vehiculoSeleccionado.getX() < ultima_x && vehiculoSeleccionado.getY() == ultima_y){
                        //Diagonal bajar 
                        accion = "moveE";
                        if(radar.get(5) == 4 && vehiculoSeleccionado.getY() > 0)
                            accion = "moveN";
                        else if(radar.get(5) == 4 && vehiculoSeleccionado.getY() == 0)
                            accion = "moveS";
                    }

                    anterior = accion;
                    crearEnviar(vehiculoSeleccionado.getNombre(), accion, ACLMessage.REQUEST);
                    vehiculoSeleccionado.getRadar().clear();
                    estadoActual = RECIBIR;
                }
                    
                
            }
            else if(estrategia == 3){
                //accion = map.obtenerSiguientePaso(vehiculoSeleccionado.getX(), vehiculoSeleccionado.getY());
                //estadoActual = FIN;
                ArrayList<Integer>radar = vehiculoSeleccionado.getRadar();
                accion="";
                System.out.println("Estoy en estrategia 3, cogemos el primero que haya, da igual");
                int mapap [][] = map.getCoordenadas();
                if(mapap[vehiculoSeleccionado.getY()][vehiculoSeleccionado.getX()] == 3){
                    vehiculoSeleccionado.setGoal(true);
                }
                if(vehiculoSeleccionado.isGoal()){
                    celda nueva = new celda();
                    nueva.x = vehiculoSeleccionado.getX();
                    nueva.y = vehiculoSeleccionado.getY();
                    int index = buscarVehiculo(vehiculoSeleccionado.getNombre());
                    listaVehiculos.get(index).setFinalizado(true);
                    listaVehiculos.get(index).setDisponible(false);
                    
                    boolean finala=false;
                    for(int i=0;i<listaVehiculos.size() && !finala;i++){
                        if(!(listaVehiculos.get(i)).isFinalizado()){
                            vehiculoSeleccionado = listaVehiculos.get(i);
                            vehiculoSeleccionado.setX(-100);
                            vehiculoSeleccionado.setY(-100);
                            listaVehiculos.get(i).setDisponible(true);
                            vehiculoSeleccionado.setDisponible(true);
                        }
                    }
                }
             /*   else if(!vehiculoSeleccionado.isDisponible()){
                    for(int i=0;i<listaVehiculos.size();i++){
                        if(!(listaVehiculos.get(i)).isFinalizado()){
                            vehiculoSeleccionado = listaVehiculos.get(i);
                            vehiculoSeleccionado.setX(-100);
                            vehiculoSeleccionado.setY(-100);
                        }
                    }
                }*/



                if(vehiculoSeleccionado.getX() == -100 && vehiculoSeleccionado.getY() == -100){
                    crearEnviar(vehiculoSeleccionado.getNombre(), "refuel", ACLMessage.REQUEST);
                    estadoActual = RECIBIR;
                }
                else{
                    if(vehiculoSeleccionado.getBattery() < 5 && vehiculoSeleccionado.getEnergy() >= 5){
                        crearEnviar(vehiculoSeleccionado.getNombre(), "refuel", ACLMessage.REQUEST);
                        estadoActual = RECIBIR;
                    }            
                    else if(vehiculoSeleccionado.getTipoVehiculo() == 0){ // Si vuela ir directo al objetivo
                        celda objetivo = map.buscarFinalCercana();
                        objetivo.y = objetivo.y - vehiculoSeleccionado.getDesplazamiento_objetivo();
                        if(vehiculoSeleccionado.getX() > objetivo.x && vehiculoSeleccionado.getY() > objetivo.y){
                            //Diagonal arriba izquierda
                            accion = "moveNW";
                            if(radar.get(0) == 4)
                                accion = "moveW";
                        }
                        else if(vehiculoSeleccionado.getX() < objetivo.x && vehiculoSeleccionado.getY() > objetivo.y){
                            //Diagonal arriba derecha
                            accion = "moveNE";
                            if(radar.get(2) == 4)
                                accion = "moveE";
                        }
                        else if(vehiculoSeleccionado.getX() > objetivo.x && vehiculoSeleccionado.getY() < objetivo.y){
                            //Diagonal abajo izquierda
                            accion = "moveSW";
                            if(radar.get(6) == 4)
                                accion = "moveW";
                        }
                        else if(vehiculoSeleccionado.getX() < objetivo.x && vehiculoSeleccionado.getY() < objetivo.y){
                            //Diagonal abajo derecha
                            accion = "moveSE";
                            if(radar.get(8) == 4)
                                accion = "moveE";
                        }
                        else if(vehiculoSeleccionado.getX() == objetivo.x && vehiculoSeleccionado.getY() > objetivo.y){
                            //Diagonal subir 
                            accion = "moveN";
                            if(radar.get(1) == 4 && vehiculoSeleccionado.getX() > 0)
                                accion = "moveW";
                            else if(radar.get(1) == 4 && vehiculoSeleccionado.getX() == 0)
                                accion = "moveE";
                        }
                        else if(vehiculoSeleccionado.getX() == objetivo.x && vehiculoSeleccionado.getY() < objetivo.y){
                            //Diagonal bajar 
                            accion = "moveS";
                            if(radar.get(7) == 4 && vehiculoSeleccionado.getX() > 0)
                                accion = "moveW";
                            else if(radar.get(7) == 4 && vehiculoSeleccionado.getX() == 0)
                                accion = "moveE";
                        }
                        else if(vehiculoSeleccionado.getX() > objetivo.x && vehiculoSeleccionado.getY() == objetivo.y){
                            //Diagonal subir 
                            accion = "moveW";
                            if(radar.get(3) == 4 && vehiculoSeleccionado.getY() > 0)
                                accion = "moveN";
                            else if(radar.get(3) == 4 && vehiculoSeleccionado.getY() == 0)
                                accion = "moveS";
                        }
                        else if(vehiculoSeleccionado.getX() < objetivo.x && vehiculoSeleccionado.getY() == objetivo.y){
                            //Diagonal bajar 
                            accion = "moveE";
                            if(radar.get(5) == 4 && vehiculoSeleccionado.getY() > 0)
                                accion = "moveN";
                            else if(radar.get(5) == 4 && vehiculoSeleccionado.getY() == 0)
                                accion = "moveS";
                        }
                        crearEnviar(vehiculoSeleccionado.getNombre(), accion, ACLMessage.REQUEST);
                        vehiculoSeleccionado.getRadar().clear();
                        estadoActual = RECIBIR;
                    }
                    else{ // Si no vuela, ir al siguiente movimiento usando la heuristica
                        celda cfinal = map.buscarFinalCercana();
                        cfinal.y = cfinal.y - vehiculoSeleccionado.getDesplazamiento_objetivo();
                        String movimiento = calcularMovimiento(cfinal.x,cfinal.y);
                        crearEnviar(vehiculoSeleccionado.getNombre(), movimiento, ACLMessage.REQUEST);
                        estadoActual = RECIBIR;
                    }
                }
            }
        }
        else{//Sino hay ninguno disponible finalizamos.
            estadoActual = FIN;
        }
        
    }
    
    public boolean comprobarSiHayVehiculoDisponible(){
        boolean disponible = false;
        
        for(int i = 0; i < listaVehiculos.size(); i++){
            if(listaVehiculos.get(i).isDisponible()){
                disponible = true;
            }
        }
        
        /*for(int i = 0; i < listaVehiculos.size(); i++){
            if(!listaVehiculos.get(i).isFinalizado()){
                disponible = true;
            }
        }*/
        
        return disponible;
    }
    
    public void recibirMensajes(){
        
        //for(int j = 0; j < NUM_VEHICULOS; j++){
        
            try{
                entrada = receiveACLMessage(); //Recibimos mensaje del los vehiculos

                    if(entrada.getPerformativeInt() == ACLMessage.QUERY_REF){
                        System.out.println("Cortana recibe los sensores del agente: "+entrada.getSender().getLocalName());
                        System.out.println("-->"+entrada.getContent());

                        int vehiculoActual = buscarVehiculo(entrada.getSender().getLocalName());
                        this.vehiculoSeleccionado = listaVehiculos.get(vehiculoActual);
                        
                        //Parsemos el Json del sensor de vehiculo.
                        JsonObject respuestaCapabilities = Json.parse(entrada.getContent()).asObject();
                        JsonObject result = respuestaCapabilities.get("result").asObject();

                        listaVehiculos.get(vehiculoActual).setBattery(result.get("battery").asInt());
                        listaVehiculos.get(vehiculoActual).setX(result.get("x").asInt());
                        listaVehiculos.get(vehiculoActual).setY(result.get("y").asInt());
                        listaVehiculos.get(vehiculoActual).setEnergy(result.get("energy").asInt());
                        listaVehiculos.get(vehiculoActual).setGoal(result.get("goal").asBoolean());

                        JsonArray radar = result.get("sensor").asArray();
                        for (int i=0; i<radar.size(); i++){
                            //Vamos cogiendo los valores del radar.
                            listaVehiculos.get(vehiculoActual).setRadar(radar.get(i).asInt());
                        }


                        //estadoActual = DECIDIRACCION;
                    }
                    else if(entrada.getPerformativeInt() == ACLMessage.CANCEL){//De puebas.
                        System.out.println("CHOQUE del agente: "+entrada.getSender().getLocalName()+" cierro y guardo traza.");
                        int index = buscarVehiculo(entrada.getSender().getLocalName());
                        listaVehiculos.get(index).setDisponible(false);// Indicamos que ese vehiculo ya no esta disponible.
                        //NUM_VEHICULOS--;
                        //estadoActual = DECIDIRACCION;
                    }
            }
            catch (InterruptedException e) {
                estadoActual = FIN;
                System.err.println("Error al recibir entrada, en recibir mensajes. " + e.getLocalizedMessage());
            }
        
        //}
        
        estadoActual = DECIDIRACCION;
    }
    
    public int buscarVehiculo(String nombre){
        int index = -1;
        
        for(int i = 0; i < listaVehiculos.size(); i++){
            if(listaVehiculos.get(i).getNombre().equals(nombre)){
                index = i;
            }
        }
        
        return index;
    }
    
    private boolean hayVolador(){
        boolean resultado = false;
        for(int i=0;i<listaVehiculos.size();i++){
            if(listaVehiculos.get(i).getTipoVehiculo() == 0)
                // 0 Volador 1 Coche 2 Camion
                resultado=true;
        }
        return resultado;
    }
    
    private void decidirEstrategia() {
        
        if(hayVolador() && nohayMapa){
            estrategia = 1;
            estadoActual = RECIBIR;
            
            boolean encontrado=false;
            for(int i=0; i<listaVehiculos.size() && !encontrado;i++)
                if(listaVehiculos.get(i).getTipoVehiculo() == 0){
                    vehiculoSeleccionado = listaVehiculos.get(i);
                    vehiculoSeleccionado.setDisponible(true);
                    listaVehiculos.get(i).setDisponible(true);
                    encontrado=true;
                }
            
            crearEnviar(vehiculoSeleccionado.getNombre(), "refuel", ACLMessage.REQUEST);
            
        }
        else if( !nohayMapa){
           // if((map.getUltimaX() == map.getCoordenadas().length-1 && map.getUltimaY() == map.getCoordenadas().length-1) || (map.getUltimaX() == 0 && map.getUltimaY() == map.getCoordenadas().length-1) ){
                estadoActual = RECIBIR;
                vehiculoSeleccionado = listaVehiculos.get(0);
                vehiculoSeleccionado.setDisponible(true);
                listaVehiculos.get(0).setDisponible(true);
                crearEnviar(vehiculoSeleccionado.getNombre(), "refuel", ACLMessage.REQUEST);
                estrategia = 3;                 
           // }
        /*    else{
                if(hayVolador()){
                    estrategia = 2;
                    ultima_x = map.getUltimaX();
                    ultima_y = map.getUltimaY();
                    boolean encontrado=false;
                    for(int i=0; i<listaVehiculos.size() && !encontrado;i++){
                        if(listaVehiculos.get(i).getTipoVehiculo() == 0){
                            vehiculoSeleccionado = listaVehiculos.get(i);
                            vehiculoSeleccionado.setDisponible(true);
                            encontrado=true;
                        }
                    }
                    crearEnviar(vehiculoSeleccionado.getNombre(), "refuel", ACLMessage.REQUEST);
                    estadoActual = RECIBIR; 
                }
                else estadoActual = FIN;
            }*/
        }
        else{
            estadoActual = FIN;
        }
}
    
    public void finalizarAgentes(){
        System.out.println("Agente: "+this.getName() + " finalizando sesion");
        if(map != null)
            //map.guardarMapa(vehiculoSeleccionado.getX(), vehiculoSeleccionado.getY());
        
        
        crearEnviar(this.getAid(), "Jih", "", ACLMessage.CANCEL);//Solo cortana hace el cancel al sevidor (una vez).
        
        try{
            //Obtenemos la entrada.
            entrada = receiveACLMessage();
            
            if(entrada.getPerformativeInt() == ACLMessage.AGREE){//Les decimos a los vehiculos que terminen su ejecucion
                System.out.println("->Desconectado correctamente agente: "+this.getName()+"<-");
                crearEnviar(GCSociety.n1, "", ACLMessage.CANCEL);
                crearEnviar(GCSociety.n2, "", ACLMessage.CANCEL);
                crearEnviar(GCSociety.n3, "", ACLMessage.CANCEL);
                crearEnviar(GCSociety.n4, "", ACLMessage.CANCEL);
            }
            
            imprimirTraza();
            finalizado = true;
        }
        catch(InterruptedException e){
            System.err.println("Agente("+this.getName()+") Error de comunicacion");
            System.err.println("ERROR: "+e.getMessage());
        }
    }
    
    public void imprimirTraza(){
        try{
            System.out.println("Recibiendo traza.");
            ACLMessage inbox = this.receiveACLMessage();
            JsonObject injson = Json.parse(inbox.getContent()).asObject();
            JsonArray ja = injson.get("trace").asArray();
            byte[]data = new byte[ja.size()];
            for(int i = 0; i < data.length; i++){
                data[i] = (byte) ja.get(i).asInt();
            }
            FileOutputStream fos = new FileOutputStream("miTraza.png");
            fos.write(data);
            fos.close();
            System.out.println("Traza guardada");
        }
        catch(InterruptedException | IOException e){
            System.err.println("Error procesamiento de traza.");
        }
    }
    
    private void crearEnviar( String receiver, String mensaje, int performative) {
        salida = new ACLMessage();
        salida.setSender(this.getAid());
        salida.setReceiver(new AgentID(receiver));
        salida.setContent(mensaje);
        salida.setPerformative(performative);
        this.send(salida);


    }
    private void crearEnviar( String receiver, String mensaje, int performative, String contentid) {
        salida = new ACLMessage();
        salida.setSender(this.getAid());
        salida.setReceiver(new AgentID(receiver));
        salida.setConversationId(contentid);
        salida.setContent(mensaje);
        salida.setPerformative(performative);
        this.send(salida);
    }
    private void crearEnviar(AgentID aid, String receiver, String mensaje, int performative) {
        salida = new ACLMessage();
        salida.setSender(aid);
        salida.setReceiver(new AgentID(receiver));
        salida.setContent(mensaje);
        salida.setPerformative(performative);
        this.send(salida);
    }
    
    private String calcularMovimiento(int x_objetivo, int y_objetivo) {
        // Si la solucion esta en un sitio mas bajo subir
        int este=0,oeste=0,sur=0,norte=0,suroeste=0,sureste=0,noreste=0,noroeste=0,centro=0;
        String accion= "refuel";
        
        if(vehiculoSeleccionado.getTipoVehiculo() == 1){//Coche
            noroeste=6;
            norte=7;
            noreste=8;
            oeste=11;
            centro = 12;
            este=13;
            suroeste=16;
            sur=17;
            sureste=18;
            
        }
        if(vehiculoSeleccionado.getTipoVehiculo() == 2){//Camion
            noroeste=48;
            norte=49;
            noreste=50;
            oeste=59;
            centro = 60;
            este=61;
            suroeste=70;
            sur=71;
            sureste=72;
        }
           
        ArrayList<Integer>radar = vehiculoSeleccionado.getRadar();
        int valorx = vehiculoSeleccionado.getX();
        int valory = vehiculoSeleccionado.getY();
        // Lista con las prioridades, del 0 al 9
           ArrayList<Integer> prioridades =  establecer(valorx,valory);
           int x = prioridadMejor(prioridades);
        // NOROESTE
        // 0 1 2
        // 3 4 5
        // 6 7 8
        if(x_objetivo < valorx && y_objetivo < valory){
            if(prioridades.get(0) <= x && (radar.get(noroeste) == 0 || radar.get(noroeste) == 3)) accion = "moveNW";
            else if(prioridades.get(OESTE) <= x && (radar.get(oeste) == 0 || radar.get(oeste) == 3)) accion = "moveW";
            else if(prioridades.get(NORTE) <= x &&(radar.get(norte) == 0 || radar.get(norte) == 3)) accion = "moveN";
            else if(prioridades.get(SUROESTE) <= x &&(radar.get(suroeste) == 0 || radar.get(suroeste) == 3)) accion = "moveSW";
            else if(prioridades.get(NORESTE) <= x &&(radar.get(noreste) == 0 || radar.get(noreste) == 3)) accion = "moveNE";
            else if(prioridades.get(ESTE) <= x &&(radar.get(este) == 0 || radar.get(este) == 3)) accion = "moveE";
            else if(prioridades.get(SUR) <= x &&(radar.get(sur) == 0 || radar.get(sur) == 3)) accion = "moveS";
            else if(prioridades.get(SURESTE) <= x &&(radar.get(sureste) == 0 || radar.get(sureste) == 3)) accion = "moveSE";
        }
        // SUROESTE
        else if(x_objetivo < valorx && y_objetivo > valory){
            if(prioridades.get(SUROESTE) <= x && (radar.get(suroeste) == 0 || radar.get(suroeste) == 3)) accion = "moveSW";
            else if(prioridades.get(SUR) <= x && (radar.get(sur) == 0 || radar.get(sur) == 3)) accion = "moveS";
            else if(prioridades.get(OESTE) <= x && (radar.get(oeste) == 0 || radar.get(oeste) == 3)) accion = "moveW";
            else if(prioridades.get(SURESTE) <= x && (radar.get(sureste) == 0 || radar.get(sureste) == 3)) accion = "moveSE";
            else if(prioridades.get(NOROESTE) <= x && (radar.get(noroeste) == 0 || radar.get(noroeste) == 3)) accion = "moveNW";
            else if(prioridades.get(NORTE) <= x && (radar.get(norte) == 0 || radar.get(norte) == 3)) accion = "moveN";
            else if(prioridades.get(ESTE) <= x && (radar.get(este) == 0 || radar.get(este) == 3)) accion = "moveE";
            else if(prioridades.get(NORESTE) <= x && (radar.get(noreste) == 0 || radar.get(noreste) == 3)) accion = "moveNE";    
        }
        // NORESTE
        else if(x_objetivo > valorx && y_objetivo < valory){
            if(prioridades.get(NORESTE) <= x && (radar.get(noreste) == 0 || radar.get(noreste) == 3)) accion = "moveNE";
            else if(prioridades.get(NORTE) <= x && (radar.get(norte) == 0 || radar.get(norte) == 3)) accion = "moveN";
            else if(prioridades.get(ESTE) <= x && (radar.get(este) == 0 || radar.get(este) == 3)) accion = "moveE";
            else if(prioridades.get(NOROESTE) <= x && (radar.get(noroeste) == 0 || radar.get(noroeste) == 3)) accion = "moveNW";
            else if(prioridades.get(SURESTE) <= x && (radar.get(sureste) == 0 || radar.get(sureste) == 3)) accion = "moveSE";
            else if(prioridades.get(OESTE) <= x && (radar.get(oeste) == 0 || radar.get(oeste) == 3)) accion = "moveW";
            else if(prioridades.get(SUR) <= x && (radar.get(sur) == 0 || radar.get(sur) == 3)) accion = "moveS";
            else if(prioridades.get(SUROESTE) <= x && (radar.get(suroeste) == 0 || radar.get(sureste) == 3)) accion = "moveSE";
        }
        // SURESTE
        else if(x_objetivo > valorx && y_objetivo > valory){
            if(prioridades.get(SURESTE) <= x && (radar.get(sureste) == 0 || radar.get(sureste) == 3)) accion = "moveSE";
            else if(prioridades.get(ESTE) <= x && (radar.get(este) == 0 || radar.get(este) == 3)) accion = "moveE";
            else if(prioridades.get(SUR) <= x && (radar.get(sur) == 0 || radar.get(sur) == 3)) accion = "moveS";
            else if(prioridades.get(NORESTE) <= x && (radar.get(noreste) == 0 || radar.get(noreste) == 3)) accion = "moveNE";
            else if(prioridades.get(SUROESTE) <= x && (radar.get(suroeste) == 0 || radar.get(suroeste) == 3)) accion = "moveSW";
            else if(prioridades.get(NORTE) <= x && (radar.get(norte) == 0 || radar.get(norte) == 3)) accion = "moveN";         
            else if(prioridades.get(OESTE) <= x && (radar.get(oeste) == 0 || radar.get(oeste) == 3)) accion = "moveW";
            else if(prioridades.get(NOROESTE) <= x && (radar.get(noroeste) == 0 || radar.get(noroeste) == 3)) accion = "moveNW";
        }
        // NORTE
        else if(x_objetivo == valorx && y_objetivo < valory){
            if(prioridades.get(NORTE) <= x && (radar.get(norte) == 0 || radar.get(norte) == 3)) accion = "moveN";
            else if(prioridades.get(NORESTE) <= x && (radar.get(noreste) == 0 || radar.get(noreste) == 3)) accion = "moveNE";
            else if(prioridades.get(NOROESTE) <= x && (radar.get(noroeste) == 0 || radar.get(noroeste) == 3)) accion = "moveNW";
            else if(prioridades.get(OESTE) <= x && (radar.get(oeste) == 0 || radar.get(oeste) == 3)) accion = "moveW";
            else if(prioridades.get(ESTE) <= x && (radar.get(este) == 0 || radar.get(este) == 3)) accion = "moveE";
            else if(prioridades.get(SURESTE) <= x && (radar.get(sureste) == 0 || radar.get(sureste) == 3)) accion = "moveSE";
            else if(prioridades.get(SUROESTE) <= x && (radar.get(suroeste) == 0 || radar.get(suroeste) == 3)) accion = "moveSW";
            else if(prioridades.get(SUR) <= x && (radar.get(sur) == 0 || radar.get(sur) == 3)) accion = "moveS";    
        }
        // SUR
        else if(x_objetivo == valorx && y_objetivo > valory){
            if(prioridades.get(SUR) <= x && (radar.get(sur) == 0 || radar.get(sur) == 3)) accion = "moveS";
            else if(prioridades.get(SURESTE) <= x && (radar.get(sureste) == 0 || radar.get(sureste) == 3)) accion = "moveSE";
            else if(prioridades.get(SUROESTE) <= x && (radar.get(suroeste) == 0 || radar.get(suroeste) == 3)) accion = "moveSW";
            else if(prioridades.get(ESTE) <= x && (radar.get(este) == 0 || radar.get(este) == 3)) accion = "moveSW";
            else if(prioridades.get(OESTE) <= x && (radar.get(oeste) == 0 || radar.get(oeste) == 3)) accion = "moveW";
            else if(prioridades.get(ESTE) <= x && (radar.get(este) == 0 || radar.get(este) == 3)) accion = "moveE";
            else if(prioridades.get(NOROESTE) <= x && (radar.get(noroeste) == 0 || radar.get(noroeste) == 3)) accion = "moveN";
            else if(prioridades.get(NORESTE) <= x && (radar.get(noreste) == 0 || radar.get(noreste) == 3)) accion = "moveNE";
            else if(prioridades.get(NORTE) <= x && (radar.get(norte) == 0 || radar.get(norte) == 3)) accion = "moveN";   
        }
        // OESTE
        else if(x_objetivo < valorx && y_objetivo == valory){
            if(prioridades.get(OESTE) <= x && (radar.get(oeste) == 0 || radar.get(oeste) == 3)) accion = "moveW";
            else if(prioridades.get(NOROESTE) <= x && (radar.get(noroeste) == 0 || radar.get(noroeste) == 3)) accion = "moveNW";
            else if(prioridades.get(SUROESTE) <= x && (radar.get(suroeste) == 0 || radar.get(suroeste) == 3)) accion = "moveSW";
            else if(prioridades.get(NORTE) <= x && (radar.get(norte) == 0 || radar.get(norte) == 3)) accion = "moveN";
            else if(prioridades.get(SUR) <= x && (radar.get(sur) == 0 || radar.get(sur) == 3)) accion = "moveS";
            else if(prioridades.get(SURESTE) <= x && (radar.get(sureste) == 0 || radar.get(sureste) == 3)) accion = "moveSE";
            else if(prioridades.get(NORESTE) <= x && (radar.get(noreste) == 0 || radar.get(noreste) == 3)) accion = "moveNE";
            else if(prioridades.get(ESTE) <= x && (radar.get(este) == 0 || radar.get(este) == 3)) accion = "moveE";
        }
        // ESTE
        else if(x_objetivo > valorx && y_objetivo == valory){
            if(prioridades.get(ESTE) <= x && (radar.get(este) == 0 || radar.get(este) == 3)) accion = "moveE";
            else if(prioridades.get(NORESTE) <= x && (radar.get(noreste) == 0 || radar.get(noreste) == 3)) accion = "moveNE";
            else if(prioridades.get(SURESTE) <= x && (radar.get(sureste) == 0 || radar.get(sureste) == 3)) accion = "moveSE";
            else if(prioridades.get(NORTE) <= x && (radar.get(norte) == 0 || radar.get(norte) == 3)) accion = "moveN";
            else if(prioridades.get(SUR) <= x && (radar.get(sur) == 0 || radar.get(sur) == 3)) accion = "moveS";
            else if(prioridades.get(NOROESTE) <= x && (radar.get(noroeste) == 0 || radar.get(noroeste) == 3)) accion = "moveNW";
            else if(prioridades.get(SUROESTE) <= x && (radar.get(suroeste) == 0 || radar.get(suroeste) == 3)) accion = "moveSW";
            else if(prioridades.get(OESTE) <= x && (radar.get(oeste) == 0 || radar.get(oeste) == 3)) accion = "moveW";
        }
    System.out.println("Accion: " + accion);   
    vehiculoSeleccionado.getRadar().clear();
    return accion;
}
   
    
 public ArrayList<Integer> establecer(int X,int Y) {

  Posicion NW,N,NE,W,E,SW,S,SE;

   Posicion actual= new Posicion(X,Y);
   vehiculoSeleccionado.lista.Add(actual);
   NW = new Posicion(X-1,Y-1);
   N = new Posicion(X,Y-1);
   NE = new Posicion(X+1,Y-1);
   W = new Posicion(X-1,Y);
   E = new Posicion(X+1,Y);
   SW = new Posicion(X-1,Y+1);
   S = new Posicion(X,Y+1);
   SE = new Posicion(X+1,Y+1);
   ArrayList<Integer> devolver = new ArrayList<>(9);

   devolver.add(vehiculoSeleccionado.lista.getRepeticiones(NW));
   devolver.add(vehiculoSeleccionado.lista.getRepeticiones(N));
   devolver.add(vehiculoSeleccionado.lista.getRepeticiones(NE));
   devolver.add(vehiculoSeleccionado.lista.getRepeticiones(W));
   devolver.add(vehiculoSeleccionado.lista.getRepeticiones(actual));
   devolver.add(vehiculoSeleccionado.lista.getRepeticiones(E));
   devolver.add(vehiculoSeleccionado.lista.getRepeticiones(SW));
   devolver.add(vehiculoSeleccionado.lista.getRepeticiones(S));
   devolver.add(vehiculoSeleccionado.lista.getRepeticiones(SE));
  

return devolver;
}
   
    
    
    
    
 
    public int prioridadMejor(ArrayList<Integer> prioridades){
      int prioridadmejor = 1000000000;
        for(int i=0;i<prioridades.size();i++){
          if(prioridades.get(i) <= prioridadmejor){
              prioridadmejor = prioridades.get(i);
          }
      }  
        return prioridadmejor;
    }
    
    
    
    @Override
    public void finalize(){
        System.out.println("Agente "+this.getName()+" terminado.");
        super.finalize();
    } 
}
