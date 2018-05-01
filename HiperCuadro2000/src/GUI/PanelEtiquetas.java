package GUI;

/*
 * Clase encargada de crear y controlar la opciones de mantenimiento que tendrá  nuestra
 * aplicación
 * 
 * 
 * @param valores Referencia al singleton valores
 * @param eventos Objeto de la clase Freno para saber el estado en el que se encuentra el freno
 * 
 * @param serialVersionUID 
 * 
 * @param deposito1 Etiqueta simple con el nombre Depósito
 * @param deposito2 Etiqueta simple que indica el % de gasolina restante
 * @param deposito3  Objeto de tipo Button de AWT, muestra el botón de repostar
 * @param pastillas1 Etiqueta simple con el nombre Pastillas
 * @param pastillas2 Etiqueta simple que indica el número de cambios de pastillas realizados
 * @param pastillas3 Objeto de tipo Button de AWT, muestra el botón de cambio de pastillas
 * @param aceite1 Etiqueta simple con el nombre Aceite
 * @param aceite2 Etiqueta simple que indica el número de cambios de aceite realizados
 * @param aceite3  Objeto de tipo Button de AWT, muestra el botón de
 * @param revision1 Etiqueta simple con el nombre Revisión
 * @param revision2 Etiqueta simple que indica el número de revisiones realizadas
 * @param revision3  Objeto de tipo Button de AWT, muestra el botón de
 * @param contaceite Entero que muestra el número de cambios de aceite realizados
 * @param contpastis Entero que muestra el número de cambios de pastillas realizados
 * @param contrevs Entero que muestra el número de revisiones realizadas

 */


import java.awt.Button;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.event.ActionEvent;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

import Cuadro.*;
import Monitor.*;

public class PanelEtiquetas extends JPanel implements Actualizador{
	
	private Valores valores;
	private Eventos eventos;
	
	private static final long serialVersionUID = 1L;
	
	private Label deposito1 ;
	private Label deposito2 ;
	private Button deposito3 ;
	
	private Label pastillas1 ;
	private Label pastillas2 ;
	private Button pastillas3 ;
	
	private Label aceite1 ;
	private Label aceite2 ;
	private Button aceite3 ;
	
	private Label revision1 ;
	private Label revision2 ;
	private Button revision3 ;
	
	private int contaceite;
	private int contpastis;
	private int contrevs;
	
	
	/*
	 * Constructor por parámetros
	 * 
	 * @param deposito referencia a la instancia del objeto que implementa
	 * Depósito
	 * 
	 */
	
	
	public PanelEtiquetas( Deposito deposito ) {
		
		valores = Valores.getInstance();
		eventos = new Eventos( deposito );
		
		contaceite = 0;
		contpastis = 0;
		contrevs = 0;
		
		this.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.black));
		
		this.deposito1 = new Label("Deposito");
		this.deposito2 = new Label("Deposito");
		this.deposito3 = new Button("Repostar");
		
		this.pastillas1 = new Label("Pastillas");
		this.pastillas2 = new Label("Cambios: " + contpastis);
		this.pastillas3 = new Button("Cambio");
		
		this.aceite1 = new Label("Aceite");
		this.aceite2 = new Label("Cambios: " + contaceite);
		this.aceite3 = new Button("Cambio");
		
		this.revision1 = new Label("Revision");
		this.revision2 = new Label("Cambios: " + contrevs);
		this.revision3 = new Button("Revisar");
		
		this.initComponents();
		
	}
	
	/*
	 * Función privada inicializada en el constructor por parámetros
	 * inicializa todos los botones y los sitúa
	 * 
	 */
	
	
	private void initComponents() {
		
		this.setLayout(new GridLayout(4,3));
		
		this.add(deposito1);
		this.add(deposito2);
		this.add(deposito3);
		
		deposito3.addActionListener( new java.awt.event.ActionListener() {
			   public void actionPerformed(java.awt.event.ActionEvent evt) {
				   deposito3ActionPerformed(evt); }

			synchronized private void deposito3ActionPerformed(ActionEvent evt) {
				
				
				deposito3.setEnabled( false );
				eventos.repostar();
				
			}
		});
		
		this.deposito3.setEnabled(false);
		
		this.add(pastillas1);
		this.add(pastillas2);
		this.add(pastillas3);
		
		pastillas3.addActionListener( new java.awt.event.ActionListener() {
			   public void actionPerformed(java.awt.event.ActionEvent evt) {
				   pastillas3ActionPerformed(evt); }

			synchronized private void pastillas3ActionPerformed(ActionEvent evt) {
				
				
				pastillas3.setEnabled( false );
				contpastis++;
				pastillas2.setText("Cambios: " + contpastis);
				eventos.cambioPastillas();
				
			}
		});
		
		this.pastillas3.setEnabled(false);
		
		this.add(aceite1);
		this.add(aceite2);
		this.add(aceite3);
		
		aceite3.addActionListener( new java.awt.event.ActionListener() {
			   public void actionPerformed(java.awt.event.ActionEvent evt) {
				   aceite3ActionPerformed(evt); }

			synchronized private void aceite3ActionPerformed(ActionEvent evt) {
				
				
				aceite3.setEnabled( false );
				contaceite++;
				aceite2.setText("Cambios: " + contaceite);
				eventos.cambioAceite();
				
			}
		});
		
		this.aceite3.setEnabled(false);
		
		this.add(revision1);
		this.add(revision2);
		this.add(revision3);
		
		revision3.addActionListener( new java.awt.event.ActionListener() {
			   public void actionPerformed(java.awt.event.ActionEvent evt) {
				   revision3ActionPerformed(evt); }

			synchronized private void revision3ActionPerformed(ActionEvent evt) {
				
				System.out.println("Revision");
				revision3.setEnabled( false );
				contrevs++;
				revision2.setText("Cambios: " + contrevs);
				eventos.revision();
				
			}
		});
		
		this.revision3.setEnabled(false);
		
	}
	
	/*
	 * 
	 * Función encargada de activar los botones en caso de que se cumpla
	 * la funcion asignada a cada uno
	 * 
	 */
	
	synchronized public void actualizar(){
		
		if( deposito2.getText() != valores.get_gasolina() + "%" )
			deposito2.setText( valores.get_gasolina() + "%" );
		
		if( valores.get_gasolina() == 0 )
			deposito3.setEnabled( true );
		
		if( valores.get_aceite() )
			this.aceite3.setEnabled( true );
		
		if( valores.get_pastillas() )
			this.pastillas3.setEnabled( true );
		
		if( valores.get_revision() )
			this.revision3.setEnabled( true );
		
	}
	
}
