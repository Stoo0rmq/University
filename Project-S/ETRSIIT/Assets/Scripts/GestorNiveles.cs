using UnityEngine;
using System.Collections;

public class GestorNiveles : MonoBehaviour {

	public GameObject CheckpointActual;
	private PlayerControl jugador;
	public GameObject Efecto_muerte;
	public GameObject Efecto_respawn;
	public float retraso_respawn = 4;
	public bool Frozen=false;
	// Use this for initialization
	void Start () {
		jugador = FindObjectOfType<PlayerControl> ();
	}
	
	// Update is called once per frame
	void Update () {
		if (Frozen)
			jugador.activarMovimiento(true);
		else
			jugador.activarMovimiento (false);
	}
	public void RespawnJugador(){
		// Es necesario iniciar usar una corutina:
		/*
		 * 
		 * http://docs.unity3d.com/ScriptReference/WaitForSeconds.html
		 */
		StartCoroutine(Example());
	
	}

	IEnumerator Example() {
		Debug.Log ("Player respawn");
		//Donde lo aplicaremos
		Instantiate(Efecto_muerte,jugador.transform.position,jugador.transform.rotation);
		Frozen = true;
		yield return new WaitForSeconds (retraso_respawn);
		Frozen = false;
		jugador.transform.position = CheckpointActual.transform.position;

		Instantiate (Efecto_respawn, CheckpointActual.transform.position, CheckpointActual.transform.rotation);
	}

}
