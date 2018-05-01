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
// Controlador.java

package algoritmobully;

import java.util.ArrayList;

public class Controlador { 
    private ArrayList<Proceso> procesos ;
    private Interfaz interfaz;
    
    public Controlador(Interfaz interfaz){
        this.interfaz = interfaz;
        this.procesos = new ArrayList() ;
    }
    
    public void aniadirProceso(Proceso elproceso){
        elproceso.setControlador(this) ;
        procesos.add(elproceso) ;
    }
    
    public Proceso getProcesoConID(int elid){
        return procesos.get(elid) ;
    }
    
    /*
        Para evitar problemas a la hora de arrancar los procesos, inicializaremos
        empezando por el final   
    */

    @SuppressWarnings({"SleepWhileInLoop", "CallToPrintStackTrace"})
    public void arrancar(){
        for (int i = Proceso.getNumProcesos() - 1 ; i > -1 ; i--){
            System.out.println("Lanzando hebra " + i) ;
            this.getProcesoConID(i).start() ;
        }
        
        while (true){
            this.interfaz.getPanelBully().actualizarPaneles();
        }
    }
    
    public void pararProceso(int idProceso) {
        this.getProcesoConID(idProceso).setEstado(Proceso.ERROR);
        this.interfaz.getPanelBully().actualizarPaneles();
    }
}
