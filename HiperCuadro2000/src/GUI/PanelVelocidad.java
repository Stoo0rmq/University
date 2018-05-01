package GUI;

/*
 * Clase encargada de crear y actualizar el visor de información de nuestra
 * aplicación
 * 
 * @param serialVersionUID 
 * @param revoluciones JPanel reservado a las revoluciones
 * @param velocidad JPanel reservado a la velocidad
 * @param velocidad_mantenida JPanel reservado a la velocidad automática
 * @param velocidad_media JPanel reservado a la velocidad media
 * @param distancia JPanel reservado a la distancia recorrida
 * @param Acelerador JPanel reservado a la posiciín del acelerador
 * @param Freno JPanel reservado a la posiciín del freno
 * @param lrevoluciones Etiqueta JLabel , en ella se muestra el numero de revoluciones total
 * @param lvelocidad Etiqueta JLabel, en ella , se muestra la velocidad actual en Km/h
 * @param lvelocidad_mantenida Etiqueta JLabel, en ella se muestra la velocidad a la que irá en automático
 * @param lvelocidad_medio Etiqueta JLabel , en ella se muestra la velocidad media actual
 * @param ldistancia Etiqueta JLabel , en ella se muestra la distancia total recorrida en Km
 * @param lAcelerador Etiqueta JLabel , en ella se muestra la posición del acelerador entre 0 y 10
 * @param lFreno Etiqueta JLabel, en ella se muestra la posición del freno entre 0 y 10 
 * @param valores Referencia al singleton valores
 */




import java.awt.Color;
import java.awt.GridLayout;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;

import Cuadro.Valores;

public class PanelVelocidad extends JPanel implements Actualizador{

	private static final long serialVersionUID = 1L;
	
	private JPanel revoluciones ;
	private JPanel velocidad ;
	private JPanel velocidad_mantenida ;
	private JPanel velocidad_media ;
	private JPanel distancia ;
	private JPanel Acelerador;
	private JPanel Freno;
	
	private JLabel lrevoluciones ;
	private JLabel lvelocidad ;
	private JLabel lvelocidad_mantenida;
	private JLabel lvelocidad_media ;
	private JLabel ldistancia ;
	private JLabel lAcelerador;
	private JLabel lFreno;
	
	private Valores valores;
	
	/*
	 * Constructor por defecto
	 * 
	 * 
	 */
	
	
	public PanelVelocidad(){
		
		valores = Valores.getInstance();
		
		Border blackline = BorderFactory.createLineBorder(Color.black);
		TitledBorder title;
		
		title = BorderFactory.createTitledBorder(blackline, "Revoluciones");
		title.setTitleJustification(TitledBorder.CENTER);
		this.revoluciones = new JPanel();
		this.lrevoluciones = new JLabel( String.valueOf( valores.get_revoluciones()) );
		revoluciones.add( lrevoluciones );
		revoluciones.setBorder(title);
		
		title = BorderFactory.createTitledBorder(blackline, "Velocidad");
		title.setTitleJustification(TitledBorder.CENTER);
		this.velocidad = new JPanel();
		this.lvelocidad = new JLabel( String.valueOf( valores.leerVelocidad()) + " km/h" );
		velocidad.add( lvelocidad );
		velocidad.setBorder(title);
		
		title = BorderFactory.createTitledBorder(blackline, "velocidad_mantenida");
		title.setTitleJustification(TitledBorder.CENTER);
		this.velocidad_mantenida = new JPanel();
		this.lvelocidad_mantenida = new JLabel( String.valueOf( valores.leerVelMantenida()) + " km/h" );
		velocidad_mantenida.add( lvelocidad_mantenida );
		velocidad_mantenida.setBorder(title);
		
		title = BorderFactory.createTitledBorder(blackline, "Velocidad Media");
		title.setTitleJustification(TitledBorder.CENTER);
		this.velocidad_media = new JPanel();
		this.lvelocidad_media = new JLabel( String.valueOf( valores.leerVelocidadMedia()) + " km/h" );
		velocidad_media.add( lvelocidad_media );
		velocidad_media.setBorder(title);
		
		title = BorderFactory.createTitledBorder(blackline, "Distancia");
		title.setTitleJustification(TitledBorder.CENTER);
		this.distancia = new JPanel();
		this.ldistancia = new JLabel( String.valueOf( valores.leerDistancia()) + " km" );
		distancia.add( ldistancia );
		distancia.setBorder(title);
		
		title = BorderFactory.createTitledBorder(blackline, "Acelerador");
		title.setTitleJustification(TitledBorder.CENTER);
		this.Acelerador = new JPanel();
		this.lAcelerador = new JLabel( "Posicion: " + String.valueOf( valores.get_acelerador().leerposicion() ));
		Acelerador.add( lAcelerador );
		Acelerador.setBorder(title);
		
		title = BorderFactory.createTitledBorder(blackline, "Freno");
		title.setTitleJustification(TitledBorder.CENTER);
		this.Freno = new JPanel();
		this.lFreno = new JLabel( "Posicion: " + String.valueOf( valores.get_freno().get_posicion() ));
		Freno.add( lFreno );
		Freno.setBorder(title);
		
		this.initComponents();	
		
	}
	
	
	/*
	 * Función privada inicializada en el constructor por defecto
	 * inicializa todos los paneles
	 * 
	 */
	
	public void initComponents(){
		
		this.setLayout(new GridLayout(7,1));
		
		this.add(revoluciones);
		this.add(velocidad);
		this.add(velocidad_mantenida);
		this.add(velocidad_media);
		this.add(distancia);
		this.add(Acelerador);
		this.add(Freno);
		
	}
	
	/*
	 * 
	 * Función encargada de mostrar los valores actualizados en cada momento
	 * del panel
	 * 
	 */
	
	
	public void actualizar(){
		
		this.lrevoluciones.setText( String.valueOf( valores.get_revoluciones()) );
		this.lvelocidad.setText( String.valueOf( (int)valores.leerVelocidad()) + " km/h" );
		this.lvelocidad_mantenida.setText( String.valueOf( (int)valores.leerVelMantenida()) + " km/h" );
		this.lvelocidad_media.setText( String.valueOf( (int)valores.leerVelocidadMedia()) + " km/h" );
		this.ldistancia.setText( String.valueOf( (int)valores.leerDistancia()) + " km" );
		this.lAcelerador.setText( "Posicion: " + String.valueOf( valores.get_acelerador().leerposicion() ));
		this.lFreno.setText( "Posicion: " + String.valueOf( valores.get_freno().get_posicion() ));
		
	}
	
}
