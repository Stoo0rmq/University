using UnityEngine;
using System.Collections;

public class PlayerControl : MonoBehaviour {

	public float VelocidadMovimiento;
	public float AlturaSalto;

	/*
	 * Para hacer que sólo salte
	 * un numero limitado de veces
	 * tendremos que saber cuando tocamos
	 * el suelo
*/


	public Transform TocaSueloCheck;
	public float groundCheckRadius;
	public LayerMask whatIsGround;
	private bool TocaSuelo;
	private int nsaltos=0;
	private bool DobleSalto;
	// Use this for initialization
	private bool frozen=false;
	private Animator animacion;

	public void activarMovimiento(bool resp){
		frozen = resp;
	}

	void Start () {
		// Iniciamos el animador por defecto
		animacion = GetComponent<Animator> ();

	}

	void FixedUpdate(){
		TocaSuelo = Physics2D.OverlapCircle(TocaSueloCheck.position, groundCheckRadius, whatIsGround);
	}


	void Update(){
		if (frozen == false) {
			if (TocaSuelo) {
				DobleSalto = false;

			}
			animacion.SetBool ("tocasuelo", TocaSuelo);
			// Si pulsamos espacio la velocidad en la Y cambia a altura salto 
			if (Input.GetKeyDown (KeyCode.Space) && TocaSuelo) {
				Saltar ();
			}

			// Si pulsamos espacio la velocidad en la Y cambia a altura salto 
			if (Input.GetKeyDown (KeyCode.Space) && !TocaSuelo && !DobleSalto) {
				Saltar ();
				DobleSalto = true;
			}
		

			// Para movernos a izq o derecha solo mantendremos pulsado D o A 

			if (Input.GetKey (KeyCode.D)) {
				GetComponent<Rigidbody2D> ().velocity = new Vector2 (VelocidadMovimiento, GetComponent<Rigidbody2D> ().velocity.y);
			}
			if (Input.GetKey (KeyCode.A)) {
				GetComponent<Rigidbody2D> ().velocity = new Vector2 (-VelocidadMovimiento, GetComponent<Rigidbody2D> ().velocity.y);
			}
			// Fijar a la variable velocidad de la animacion la velocidad que tenga en el eje x nuestro personajes, un valor absoluto
			animacion.SetFloat ("velocidad", Mathf.Abs (GetComponent<Rigidbody2D> ().velocity.x));
		}
	}

	public void Saltar(){
		GetComponent<Rigidbody2D> ().velocity = new Vector2 (GetComponent<Rigidbody2D> ().velocity.x, AlturaSalto);
	}

	
}
