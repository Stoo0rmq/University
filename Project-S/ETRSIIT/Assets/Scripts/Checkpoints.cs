using UnityEngine;
using System.Collections;

public class Checkpoints : MonoBehaviour {

	public GestorNiveles gnivel;

	// Use this for initialization
	void Start () {
		gnivel = FindObjectOfType<GestorNiveles>();
	}

	
	// Update is called once per frame
	void Update () {
		
	}

	// Si entramos en el collider del elemento entonces...

	void OnTriggerEnter2D(Collider2D other){
		Debug.Log("is in the trigger");
		if (other.name == "goodguy") {
			Debug.Log ("Checkpoint activado");
			gnivel.CheckpointActual = gameObject;
		}
	}


}
