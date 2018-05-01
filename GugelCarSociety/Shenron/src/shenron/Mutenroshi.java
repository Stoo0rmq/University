/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shenron;

import com.eclipsesource.json.JsonObject;
import es.upv.dsic.gti_ia.core.ACLMessage;
import es.upv.dsic.gti_ia.core.AgentID;
import es.upv.dsic.gti_ia.core.SingleAgent;

/**
 *
 * @author Luis Castillo
 */
public class Mutenroshi extends SingleAgent {
    ACLMessage outbox, inbox;
    JsonObject injson, outjson;
    String  user, password,opcion;

    public Mutenroshi(AgentID aid, String u, String p,String opcion) throws Exception {
        super(aid);
        user = u;
        password = p;
        this.opcion = opcion;
    }
    
    @Override
    public void execute()  {
        System.out.println("Ejecutando a Mutenroshi");
        if(opcion.equals("1")){
                    Reboot();

        }
         if(opcion.equals("2")){
                    Info();

        }

        System.out.println("Fin de Mutenroshi");
    }
    
    void Reboot()  {
        System.out.println("Reseteando server ");
        outbox = new ACLMessage();
        outbox.setSender(this.getAid());
        outbox.setReceiver(new AgentID("Shenron"));
        outjson = new JsonObject();
        outjson.add("user",user);
        outjson.add("password", password);
        outbox.setPerformative(ACLMessage.REQUEST);
        outbox.setContent(outjson.toString());
        this.send(outbox);
        try {
            System.out.println("Obteniendo respuesta");
            inbox = this.receiveACLMessage();
            if(inbox.getPerformativeInt() == ACLMessage.FAILURE)
                System.out.println("Reseteo fallido");
            else if(inbox.getPerformativeInt() == ACLMessage.INFORM)
                System.out.println("Reseteo Correcto");
            System.out.println("Respuesta recibida de "+inbox.getSender().getLocalName()+" = " +inbox.getContent());
        } catch (InterruptedException ex) {
        }
    }
 
    
    void Info()  {
        System.out.println("Sacando datos del server ");
        outbox = new ACLMessage();
        outbox.setSender(this.getAid());
        outbox.setReceiver(new AgentID("Shenron"));
        outjson = new JsonObject();
        outjson.add("user",user);
        outjson.add("password", password);
        outbox.setPerformative(ACLMessage.QUERY_REF);

        outbox.setContent(outjson.toString());
        this.send(outbox);
        try {
            System.out.println("Obteniendo respuesta");
            inbox = this.receiveACLMessage();
            if(inbox.getPerformativeInt() == ACLMessage.FAILURE)
                System.out.println("oBTENCION DE DATOS ERRONEA");
            else if(inbox.getPerformativeInt() == ACLMessage.INFORM)
                System.out.println("Respuesta recibida: "+inbox.getContent());
            
        } catch (InterruptedException ex) {
        }
    }
     
    
}
