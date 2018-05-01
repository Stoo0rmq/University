/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gcsociety;

import com.eclipsesource.json.Json;
import com.eclipsesource.json.JsonArray;
import com.eclipsesource.json.JsonObject;
import com.eclipsesource.json.JsonValue;
import es.upv.dsic.gti_ia.core.ACLMessage;
import es.upv.dsic.gti_ia.core.AgentID;
import es.upv.dsic.gti_ia.core.SingleAgent;

/**
 *
 * @author Mingo y Agu.
 */
public class Vehiculo extends SingleAgent {
    private String idVehiculo;
    private ACLMessage entrada, salida;
    private boolean finalizado;
    private int estado;
    private String convID;
    private String reply;
    private Propiedades capabilities;
    private final int SUBSCRIBIRSE = 0, RECIBIR = 1, ENVIARMENSAJES = 2, ESPERAR = 3, FINALIZAR = 4;
    
    public Vehiculo(AgentID aid) throws Exception {
        super(aid);
    }

    public Vehiculo(AgentID aid, String nombre) throws Exception {
        super(aid);
        this.idVehiculo = nombre;
    }
    
    
    @Override
    public void init(){
        System.out.println("\nAgente "+this.getName()+" iniciado.");
        entrada = null;
        salida = null;
        finalizado = false;
        estado = SUBSCRIBIRSE;
    }
    
    
    @Override
    public void execute(){
        System.out.println("Agente "+this.getName()+" ejecutando.");
        
        while(!finalizado){
            switch(estado){
                case SUBSCRIBIRSE:
                    subscribirseAlMundo();
                break;
                
                case RECIBIR:
                    recibirMensajes();
                break;
                
                case ENVIARMENSAJES:
                    //enviarMensajes();
                break;
                
                case ESPERAR:
                    //No hacemos nada solo esperamos a los demas vehiculos.
                    esperarCancel();
                break;
                
                case FINALIZAR:
                    muerto();
                break;
            }
        }

    }
     
    
    public void subscribirseAlMundo(){
        
        try{
            //Obtenemos la entrada.
            entrada = receiveACLMessage();//Recibimos de cortana la orden de hacer el chekin

            if(entrada.getPerformativeInt() == ACLMessage.REQUEST){
                convID = entrada.getConversationId();
                System.out.println("Agente: "+this.getName() + " recibe CID: "+convID);
                
                JsonObject mensajeCheck = new JsonObject();
                mensajeCheck.add("command","checkin");
                crearEnviar(this.getAid(),"Jih", mensajeCheck.toString(), ACLMessage.REQUEST, convID);//Enviamos el chekin al servidor.     
            }
            
            entrada = receiveACLMessage(); //Recibimos la respuesta del servidor.
            
            if(entrada.getPerformativeInt() == ACLMessage.INFORM){//Si todo ha ido bien, el servidor nos responde con nuestras capabilities
                System.out.println("Chekin correcto del agente: "+this.getName());
                
                //Cogemos el CID y el reply.
                convID = entrada.getConversationId();
                reply = entrada.getReplyWith();
                
                JsonObject respuestaCheck = Json.parse(entrada.getContent()).asObject();
                
                //System.out.println("Result: " +respuestaCheck.get("result").asString());
                //System.out.println("Result: " +respuestaCheck.toString());
                
                JsonObject vector2 = respuestaCheck.get("capabilities").asObject();
                //System.out.println("fuel: "+vector2.get("fuelrate").asInt());
                //System.out.println("range: "+vector2.get("range").asInt());
                //System.out.println("fly: "+vector2.get("fly").asBoolean());
                
                this.capabilities = new Propiedades(vector2.get("fuelrate").asInt(), 
                        vector2.get("range").asInt(), 
                        vector2.get("fly").asBoolean());//Le asignamos al vehiculo sus capabilities
                
                /*System.out.println("fuel: "+this.capabilities.getFuelrate());
                System.out.println("range: "+this.capabilities.getRange());
                System.out.println("fly: "+this.capabilities.isFly());*/
                System.out.println("El tipo es: "+this.capabilities.getTipoVehiculo());
                
                comunicarCortana();//Le decimos a cortana el tipo que somos.(Solo una vez).
                estado = RECIBIR;//Ya sabemos que tipo de vehiculos somos, pasamos a recibir las
                                // percepciones.
            }
            else if(entrada.getPerformativeInt() == ACLMessage.NOT_UNDERSTOOD){
                enviarCancelCortana();//Le decimos a cortana que no estamos disponibles y esperamos hasta terminar.
                estado = ESPERAR;//Algo ha ido mal.
                JsonObject respuestaCheck = Json.parse(entrada.getContent()).asObject();
                System.err.println("ERROR del agente: "+this.getName()+" "+ respuestaCheck.get("details").asString());
            }
            
        }
        catch(InterruptedException e){
            //estado = FINALIZAR;//Algo ha ido mal.
            System.err.println("Agente("+this.getName()+") Error de comunicacion");
            System.err.println("ERROR: "+e.getMessage());
        }
    }
    
    public void recibirMensajes(){
        
        try{
            //Obtenemos la entrada.
            entrada = receiveACLMessage();
            
            if(entrada.getSender().getLocalName().equals("Jih")){
                if(entrada.getPerformativeInt() == ACLMessage.INFORM){ //Respues del servidor a nuestra accion correcta.
                        //Cogemos el CID y el reply.
                        convID = entrada.getConversationId();
                        reply = entrada.getReplyWith();
                        
                        //Despues de realizar una accion le pedimos informacion de los sensores
                        crearEnviar(this.getAid(), "Jih", "", ACLMessage.QUERY_REF, convID, reply);
                        
                        entrada = receiveACLMessage();

                        if(entrada.getPerformativeInt() == ACLMessage.INFORM){//Respuesta del servido a la consulta de los sensores correcta
                            //Cogemos el CID y el reply.
                            convID = entrada.getConversationId();
                            reply = entrada.getReplyWith();
                            crearEnviar(this.getAid(), GCSociety.n5, entrada.getContent(), ACLMessage.QUERY_REF);
                        }
                        else if(entrada.getPerformativeInt() == ACLMessage.NOT_UNDERSTOOD){//Algo a ido mal
                            crearEnviar(this.getAid(), GCSociety.n5, "", ACLMessage.CANCEL);//Pruebas
                            //estado = FINALIZAR;
                            estado = ESPERAR;//Esperamos el cancel de cortana, pero no hacemos nada.
                            JsonObject respuestaCheck = Json.parse(entrada.getContent()).asObject();
                            System.err.println("ERROR NOT_UNDERSTOOD 1 del agente: "+this.getName()+" "+ respuestaCheck.get("details").asString());
                        }
                    }
                    else if(entrada.getPerformativeInt() == ACLMessage.FAILURE){//Algo esta mal Choque o bateria agotada.
                        crearEnviar(this.getAid(), GCSociety.n5, "", ACLMessage.CANCEL);//Pruebas
                        //estado = FINALIZAR;
                        estado = ESPERAR;//Esperamos el cancel de cortana, pero no hacemos nada.
                        JsonObject respuestaCheck = Json.parse(entrada.getContent()).asObject();
                        System.err.println("ERROR FAILURE del agente: "+this.getName()+" "+ respuestaCheck.get("details").asString());
                    }
                    else if(entrada.getPerformativeInt() == ACLMessage.NOT_UNDERSTOOD){//Algo a ido mal
                        crearEnviar(this.getAid(), GCSociety.n5, "", ACLMessage.CANCEL);//Pruebas
                        //estado = FINALIZAR;
                        estado = ESPERAR;//Esperamos el cancel de cortana, pero no hacemos nada.
                        JsonObject respuestaCheck = Json.parse(entrada.getContent()).asObject();
                        System.err.println("ERROR NOT_UNDERSTOOD 2 del agente: "+this.getName()+" "+ respuestaCheck.get("details").asString());
                    }
            }
            else if(entrada.getSender().getLocalName().equals(GCSociety.n5)){
                
                if(entrada.getPerformativeInt() == ACLMessage.CANCEL){//Cortana nos dice que terminemos nuestra ejecucion.
                    System.out.println("Agente: "+this.getName() + " recibe Cancel:");
                    estado = FINALIZAR;
                }
                else if(entrada.getPerformativeInt() == ACLMessage.REQUEST){//Cortana nos pide que hagamos una accion (mover o refuel)
                    
                    //JsonObject mensajeCortana = Json.parse(entrada.getContent()).asObject();
                    //System.out.println("Mensaje recibido de cortana para que haga: "+mensajeCortana.get("accion"));
                    
                    //JsonObject mensajeAccion = Json.parse(entrada.getContent()).asObject();
                    JsonObject mensajeAccion = new JsonObject();
                    //String accion = mensajeCortana.get("accion").toString();
                    String accion = entrada.getContent();
                    //mensajeAccion.add("command", accion);
                    mensajeAccion.add("command", entrada.getContent());
                    
                    crearEnviar(this.getAid(), "Jih", mensajeAccion.toString(), ACLMessage.REQUEST, convID, reply);
                }
            }
            
            
        }
        catch(InterruptedException e){
            System.err.println("Agente("+this.getName()+") Error de comunicacion");
            System.err.println("ERROR: "+e.getMessage());
        }
        
    }
    
    
    public void comunicarCortana(){
        JsonObject mensajeCapabilities = new JsonObject();
        mensajeCapabilities.add("tipoVehiculo",this.capabilities.getTipoVehiculo());
        crearEnviar(this.getAid(),GCSociety.n5, mensajeCapabilities.toString(), ACLMessage.UNKNOWN);//Enviamos el chekin al servidor.     
        
    }
    
    public void enviarCancelCortana(){
        //Hacemos el cancel con el servidor
        crearEnviar(this.getAid(), GCSociety.n5, "", ACLMessage.CANCEL);//Enviamos el chekin al servidor.
    }
    
    public void esperarCancel(){
        System.out.println("Agente: "+this.getName()+" recibido cancel");
        try{
            entrada = receiveACLMessage(); //Recibimos mensaje del los vehiculos

            if(entrada.getPerformativeInt() == ACLMessage.CANCEL){
                estado = FINALIZAR;
            }
        }
        catch (InterruptedException e) {
            estado = FINALIZAR;
            System.err.println("Error al recibir entrada, en Login. " + e.getLocalizedMessage());
        }
        
    }
    
    public void muerto(){
        finalizado = true;
    }
    
    private void crearEnviar(String receiver, String mensaje, int performative, String contentid) {
        salida = new ACLMessage();
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
    
    private void crearEnviar(AgentID aid, String receiver, String mensaje, int performative, String contentid) {
        salida = new ACLMessage();
        salida.setSender(aid);
        salida.setReceiver(new AgentID(receiver));
        salida.setConversationId(contentid);
        salida.setContent(mensaje);
        salida.setPerformative(performative);
        this.send(salida);
    }

    private void crearEnviar(AgentID aid, String receiver, String mensaje, int performative, String contentid, String replyTo) {
        salida = new ACLMessage();
        salida.setSender(aid);
        salida.setReceiver(new AgentID(receiver));
        salida.setContent(mensaje);
        salida.setPerformative(performative);
        salida.setConversationId(contentid);
        salida.setInReplyTo(replyTo);
        this.send(salida);
    }
    
    
    @Override
    public void finalize(){
        System.out.println("Agente "+this.getName()+" terminado.");
        super.finalize();
    }    
 
}
