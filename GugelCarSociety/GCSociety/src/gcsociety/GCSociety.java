/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gcsociety;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.Scanner;
import es.upv.dsic.gti_ia.core.AgentID;
import es.upv.dsic.gti_ia.core.AgentsConnection;
/**
 *
 * @author Borja @Stoo0rmQ
 * 
 */
public class GCSociety  {
    static Boolean existe;
    static Mapa map;
    static Cortana cortana = null;
    static String n1 = "alfa2", n2 = "bravo2", n3 = "charlie2", n4 = "delta2", n5 = "cortana2";
    
    public static void main(String [] arg) throws Exception {
        Vehiculo alfa = null;
        Vehiculo bravo = null;
        Vehiculo charlie = null;
        Vehiculo delta = null;
        
        System.out.println("Introduce mapa " );
        String entradaTeclado;
        Scanner entradaEscaner = new Scanner (System.in); //Creación de un objeto Scanner
        entradaTeclado = entradaEscaner.nextLine (); //Invocamos un método sobre un objeto Scanner
        
        comprobacionMapa(entradaTeclado);
        AgentsConnection.connect("isg2.ugr.es", 6000, "Jih", "Allende", "Unicornio", false);
        try{
            if(existe){
                cortana = new Cortana(new AgentID(n5),map);
            }
            else{
                cortana = new Cortana((new AgentID(n5)),entradaTeclado);
            }
            alfa = new Vehiculo(new AgentID(n1),"alfa");
            bravo = new Vehiculo(new AgentID(n2),"bravo");
            charlie = new Vehiculo(new AgentID(n3),"charlie");
            delta = new Vehiculo(new AgentID(n4),"delta");
        }
        catch (Exception ex) {
            System.err.println("Error creando agentes. " + ex.getLocalizedMessage());
            System.exit(1);
        }        
        alfa.start();
        bravo.start();
        charlie.start();
        delta.start();
        
        cortana.start();
    }
    
    
    
    public static void comprobacionMapa(String entradaTeclado){
        
        System.out.println ("Buscando " + entradaTeclado +" en el directorio de mapas");
        File archivo = null;
        FileReader fr = null;
        BufferedReader br = null;
        try {
         // Apertura del fichero y creacion de BufferedReader para poder
         // hacer una lectura comoda (disponer del metodo readLine()).
            archivo = new File ("mapas/"+entradaTeclado+".txt");
            if(archivo.exists()) {
                existe=true;
                fr = new FileReader (archivo);
                br = new BufferedReader(fr);
                System.out.println ("El mapa ha sido encontrado.");
                map = new Mapa(br,entradaTeclado);
                }
            else{
                System.out.println ("El mapa NO ha sido encontrado.");
                existe = false;

                
            }
         
        }
        catch(Exception e){
         e.printStackTrace();
        }finally{
         // En el finally cerramos el fichero, para asegurarnos
         // que se cierra tanto si todo va bien como si salta 
         // una excepcion.
         try{                    
            if( null != fr ){   
               fr.close();     
            }                  
         }catch (Exception e2){ 
            e2.printStackTrace();
         }
        }
    }  
}