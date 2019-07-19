package Game;

import java.io.Serializable;

/*
   
  Cette classe nous permet de faire un objet (un arraylist dans notre cas) 
  du type Score qui contient le nom et le score d'un joueur.
  Nous implémentons sérialisable pour pouvoir trier ce type.

*/


public class Score implements Serializable{

	private static final long serialVersionUID = 2029520982775816888L;
	private int score;
	private String player;

	public int getScore() {
		return score;
	}

	public String getPlayer() {
		return player;
	}

	public Score(String player, int score) {
		this.score = score;
		this.player = player;
	}
}