/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shenron;


import es.upv.dsic.gti_ia.core.AgentID;
import es.upv.dsic.gti_ia.core.AgentsConnection;
import java.util.Scanner;

/**
 *
 * @author Luis Castillo
 */
public class Shenron {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        String 
                user="Allende",
                password="Unicornio";
        
        System.out.println("Conectando ...");
        AgentsConnection.connect("isg2.ugr.es",6000, "test", "Allende", "Unicornio", false);
        String opcion = "1";
       
        Scanner entradaEscaner = new Scanner (System.in); //Creación de un objeto Scanner
        opcion = entradaEscaner.nextLine (); //Invocamos un método sobre un objeto Scanner
        
        try {
            System.out.println("Lanzando a Mutenroshi "
                    + "...");
            Mutenroshi m = new Mutenroshi(new AgentID("Mutenroshi"), user, password,opcion);
            m.start();
        } catch (Exception ex) {
        }
        
    }
    
}
