package GUI;

import java.awt.BorderLayout;

import javax.swing.JApplet;

import Cuadro.*;
import Monitor.*;

/*
 * Clase Interfaz y esqueleto del Applet que pone en comun los paneles
 * e inicializa la simulación lógica del programa.
 * 	
 * @param botones Instancia de PanelBotones
 * @param info Instancia de PanelEtiquetas
 * @param velocimetro Instancia de PanelVelocidad
 * @param simulacion Instancia de Simulacion
 * @param cuadro Instancia de Cuadro
 * @param monitor Instancia de Monitor_etiquetas
 * @param deposito Instancia de Deposito
 * @param tecnico Instancia de Tecnico
 * 
 *  
 */
public class Interfaz extends JApplet{
		
		private static final long serialVersionUID = 1L;
		private PanelBotones botones;
		private PanelEtiquetas info;
		private PanelVelocidad velocimetro;
		
		private Simulacion simulacion;
		private Cuadro cuadro;
		private Monitor_etiquetas monitor;
		private Deposito deposito;
		private Tecnico tecnico;
		
		/*
		 * Constructor por defecto
		 * 
		 * 
		 */
		public Interfaz (){
			
			deposito = new Deposito();
			cuadro= new Cuadro();
			
			tecnico = new Tecnico();
			
			tecnico.addElemento( new Aceite() );
			tecnico.addElemento( new Pastillas() );
			tecnico.addElemento( new Revision() );
			
			monitor = new Monitor_etiquetas( deposito,tecnico );
			
		}
		
		/*
		 * Funcion de inicializacion de componentes y hebras del Applet
		 * 
		 * (non-Javadoc)
		 * @see java.applet.Applet#start()
		 */
		public void start() {
		
			this.setSize(600, 600);
			
			botones = new PanelBotones();
			info = new PanelEtiquetas ( deposito );
			velocimetro = new PanelVelocidad();
			
			ObservadorBotones o1 = new ObservadorBotones( botones );
			ObservadorEtiquetas o2 = new ObservadorEtiquetas( info );
			ObservadorVelocidad o3 = new ObservadorVelocidad( velocimetro );
			
			getContentPane().add( botones,  BorderLayout.WEST );	
			getContentPane().add( info,  BorderLayout.EAST );
			getContentPane().add( velocimetro,  BorderLayout.CENTER );
			
			HebraCuadro hebra1 = this.cuadro.get_hebra_cuadro();
			HebraMonitor hebra2 = this.monitor.get_hebra();
			HebraDistancia hebra3 = this.cuadro.get_hebra_distancia();
			HebraVelocidad hebra4 = this.cuadro.get_hebra_velocidad();
			
			simulacion = new Simulacion( hebra1, hebra2,hebra3,hebra4 );
			simulacion.setObserver( o1 );
			simulacion.setObserver( o2 );
			simulacion.setObserver( o3 );
			
			simulacion.start();
					
		}		
}
