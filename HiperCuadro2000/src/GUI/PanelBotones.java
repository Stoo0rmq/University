package GUI;

/*
 * Clase encargada de crear y actualizar los botones de nuestra interfaz
 * Hay 6 Botones
 * 
 * 
 * @param BotonMantener Objeto de tipo Button de AWT, muestra el botón de mantener la velocidad
 * @param BotonAcelerar Objeto de tipo Button de AWT, muestra el botón de acelerar
 * @param BotonFrenar Objeto de tipo Button de AWT, muestra el botón de frenar
 * @param BotonReiniciar Objeto de tipo Button de AWT, muestra el botón de reiniciar
 * @param BotonEncender Objeto de tipo Button de AWT, muestra el botón de encender/apagar el motor
 * @param BotonApagar Objeto de tipo Button de AWT, muestra el botón de encender/apagar el panel
 * @param valores Referencia al singleton valores
 * @param frenado Objeto de la clase Freno para saber el estado en el que se encuentra el freno
 */



import java.awt.Button;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;

import javax.swing.JPanel;

import Cuadro.Motor;
import Cuadro.Palanca;
import Cuadro.Valores;

public class PanelBotones extends JPanel implements Actualizador {

	private static final long serialVersionUID = 1L;
	
	private Button BotonMantener;
	private Button BotonAcelerar;
	private Button BotonFrenar;
	private Button BotonReiniciar;
	private Button BotonEncender;
	private Button BotonApagar;
	
	private Valores valores;
	
	private Palanca frenado;
	
	/*
	 * Constructor por defecto
	 * 
	 * 
	 */
	
	
	public PanelBotones() {
		BotonMantener = new	Button("Mantener");
		BotonAcelerar = new	Button("Acelerar");
		BotonFrenar = new	Button("Frenar");
		BotonReiniciar = new	Button("Reiniciar");
		BotonEncender = new	Button("Encender motor");
		BotonApagar = new	Button("Apagar");
		
		valores = Valores.getInstance();
		frenado = Palanca.APAGADO;
		
		this.initComponents(); 		   
	}
	
	/*
	 * Función privada inicializada en el constructor por defecto
	 * inicializa todos los botones y los sitúa
	 * 
	 */
	
	private void initComponents() {
		
		this.setLayout(new GridLayout(3,2, 4,4));
		this.setBotonesInicial();
		
		BotonEncender.addActionListener( new java.awt.event.ActionListener() {
			   public void actionPerformed(java.awt.event.ActionEvent evt) {
				   BotonEncenderActionPerformed(evt); }

			synchronized private void BotonEncenderActionPerformed(ActionEvent evt) {
				
				if( BotonEncender.getLabel() != "Apagar motor" ){
					BotonEncender.setLabel("Apagar motor");
					valores.almacenarMotor( Motor.ENCENDIDO );
					BotonAcelerar.setEnabled(true);
					BotonApagar.setEnabled(true);
					BotonFrenar.setEnabled(true);
				}else{
					valores.almacenarMotor( Motor.APAGADO );
					BotonEncender.setLabel("Encender motor");
					BotonAcelerar.setEnabled(false);
					BotonApagar.setEnabled(false);
					BotonFrenar.setEnabled(false);
					BotonFrenar.setBackground( Color.white );
				}
			}
		});
				
		BotonApagar.addActionListener( new java.awt.event.ActionListener() {
			   public void actionPerformed(java.awt.event.ActionEvent evt) {
				   BotonApagarActionPerformed(evt); }

			   synchronized private void BotonApagarActionPerformed(ActionEvent evt) {
				   
				valores.almacenarPalanca( Palanca.APAGADO );
				if( !BotonEncender.isEnabled() )
					BotonEncender.setEnabled(true);
				BotonAcelerar.setEnabled(true);
				BotonApagar.setEnabled(false);
				BotonReiniciar.setEnabled(true);
				BotonMantener.setEnabled(false);
			}
		});
			
		BotonMantener.addActionListener( new java.awt.event.ActionListener() {
			   public void actionPerformed(java.awt.event.ActionEvent evt) {
				   BotonMantenerActionPerformed(evt); }

			
			   synchronized private void BotonMantenerActionPerformed(ActionEvent evt) {

				valores.almacenarPalanca( Palanca.MANTENIENDO );
				BotonAcelerar.setEnabled(true);
				BotonMantener.setEnabled(false);
				
			}
		});
				
		BotonAcelerar.addActionListener( new java.awt.event.ActionListener() {
			   public void actionPerformed(java.awt.event.ActionEvent evt) {
			       BotonAcelerarActionPerformed(evt); }

			   synchronized private void BotonAcelerarActionPerformed(ActionEvent evt) {

				valores.almacenarPalanca( Palanca.ACELERAR );
				if( BotonEncender.isEnabled() )
					BotonEncender.setEnabled(false);
				BotonApagar.setEnabled(true);
				BotonAcelerar.setEnabled(false);
				BotonMantener.setEnabled(true);
			}
		});
			   
		BotonFrenar.addActionListener( new java.awt.event.ActionListener() {
			   public void actionPerformed(java.awt.event.ActionEvent evt) {
				   BotonFrenarActionPerformed(evt); }

			   synchronized private void BotonFrenarActionPerformed(ActionEvent evt) {
				   
				if( valores.get_palanca() == Palanca.FRENAR ){
					valores.almacenarPalanca( frenado );
					BotonFrenar.setBackground( Color.white );
				}else{
					frenado = valores.get_palanca();
					BotonFrenar.setBackground( Color.red );
					valores.almacenarPalanca( Palanca.FRENAR );
					
				}
			}
		});
			   
		BotonReiniciar.addActionListener( new java.awt.event.ActionListener() {
			   public void actionPerformed(java.awt.event.ActionEvent evt) {
				   BotonReiniciarActionPerformed(evt); }

			   synchronized private void BotonReiniciarActionPerformed(ActionEvent evt) {
				
			   if( BotonEncender.isEnabled() )
					BotonEncender.setEnabled(false);   
				
				valores.almacenarPalanca( Palanca.REINICIANDO );
				BotonApagar.setEnabled(true);
				BotonReiniciar.setEnabled(false);
			}
		});
		
		this.add(BotonEncender);
		this.add(BotonApagar);
		this.add(BotonAcelerar);
		this.add(BotonFrenar );
		this.add(BotonMantener);
		this.add(BotonReiniciar);
		
	}
	
	/*
	 * Función privada inicializada en initComponents,
	 * establece los estados iniciales a cada boton
	 * 
	 * 
	 */
	
	private void setBotonesInicial(){
		
		BotonEncender.setEnabled(true);
		BotonMantener.setEnabled(false);
		BotonFrenar.setEnabled(false);
		BotonFrenar.setBackground( Color.white );
		BotonAcelerar.setEnabled(false);
		BotonReiniciar.setEnabled(false);
		BotonApagar.setEnabled(false);
		
		BotonEncender.setBackground( Color.white );
		BotonMantener.setBackground( Color.white );
		BotonFrenar.setBackground( Color.white );
		BotonAcelerar.setBackground( Color.white );
		BotonReiniciar.setBackground( Color.white );
		BotonApagar.setBackground( Color.white );
		
	}
	
	/*
	 * 
	 * Función encargada de actualizar el fondo del botón de frenar
	 *
	 * 
	 */
	
	
	public void actualizar(){
		
		if( valores.get_palanca() != Palanca.FRENAR ){
			
			BotonFrenar.setBackground( Color.white );
			
		}
		
	}	
}
