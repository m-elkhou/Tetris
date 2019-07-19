package Game;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Vector;

public class HighScoreManager {
	private Vector<Score> liste;

	private String HIGHSCORE_FILE;

	// Initialising an in and outputStream for working with the file
	ObjectOutputStream outputStream = null;
	ObjectInputStream inputStream = null;
	int id;

	public HighScoreManager(String file) {
		HIGHSCORE_FILE = file;
		liste = new Vector<>();
	}

	public HighScoreManager(int id) {
		this.id=id;
		HIGHSCORE_FILE = "text/HighScore_" + id + ".dat";
		liste = new Vector<>();
	}

	@SuppressWarnings("unchecked")
	public void loadScoreFile() {
		try {
			liste = new Vector<>();
			inputStream = new ObjectInputStream(getClass().getResourceAsStream(HIGHSCORE_FILE));//ObjectInputStream(new FileInputStream(HIGHSCORE_FILE));
			liste = (Vector<Score>) inputStream.readObject();
			inputStream.close();
		} catch (FileNotFoundException e) {
			System.out.println("[Laad] FNF Error: " + e.getMessage());
		} catch (IOException e) {
			System.out.println("[Laad] IO Error: " + e.getMessage());
		} catch (ClassNotFoundException e) {
			System.out.println("[Laad] CNF Error: " + e.getMessage());
		} finally {

			try {
				if (outputStream != null) {
					outputStream.flush();
					outputStream.close();
				}
			} catch (IOException e) {
				System.out.println("[Laad] IO Error: " + e.getMessage());
			}
		}
	}

	public void updateScoreFile(Boolean rested) {					
		try {

//			outputStream = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream("src/Game/text/HighScore_" + id + ".dat")));
			outputStream = new ObjectOutputStream(new FileOutputStream("src/Game/"+HIGHSCORE_FILE));
//			outputStream = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(getClass().getResource(HIGHSCORE_FILE).getFile())));
//			outputStream = new ObjectOutputStream(new FileOutputStream("/"+getClass().getResource(HIGHSCORE_FILE).getFile()));
//			outputStream = new ObjectOutputStream(new FileOutputStream(getClass().getResourceAsStream(HIGHSCORE_FILE).toString()));
			
			if (rested)
				outputStream.reset();
			else
				outputStream.writeObject(liste);

			outputStream.close();

		} catch (FileNotFoundException e) {
			System.out.println("[Update] FNF Error: " + e.getMessage() + ",the program will try and make a new file");
		} catch (IOException e) {
			System.out.println("[Update] IO Error: " + e.getMessage());
		} finally {
			try {
				if (outputStream != null) {
					outputStream.flush();
					outputStream.close();
				}
			} catch (IOException e) {
				System.out.println("[Update] Error: " + e.getMessage());
			}
		}
	}

	public Vector<Score> getListe(int id) {
		this.id=id;
		HIGHSCORE_FILE = "text/HighScore_" + id + ".dat";
		loadScoreFile();
		return liste;
	}

	public void setListe(Vector<Score> liste, Score score, int id) {
		this.id=id;
		this.liste = trieListe(score, liste);
		HIGHSCORE_FILE = "text/HighScore_" + id + ".dat";
		updateScoreFile(true);
		updateScoreFile(false);
	}

	public Vector<Score> trieListe(Score score, Vector<Score> liste) {
		int j = 0;
		Vector<Score> liste2 = new Vector<>();
		while (score.getScore() <= liste.get(j).getScore()) {
			liste2.add(liste.get(j));
			j++;
		}
		liste2.add(score);

		for (int i = j ; i < 9; i++)
			liste2.add(liste.get(i));
		return liste2;
	}

	// ------------------------------------------------------------------------------------------------------------------------------------
//	public void remplaireFile(HighScoreManager highScoreManager) {
//		liste.add(new Score("Mohammed EL KHOU", 9000000));
//		liste.add(new Score("Mohammed KASIMI", 9000000));
//		liste.add(new Score("Mohammed", 30));
//		liste.add(new Score("EL KHOU", 80));
//		liste.add(new Score("KASIMI", 60));
//		liste.add(new Score("Mh", 140));
//		liste.add(new Score("Mk", 390));
//		liste.add(new Score("Unknown", 190));
//		liste.add(new Score("Unknown", 40));
//		liste.add(new Score("Unknown", 0));
//		liste.add(new Score("EL KHOU", 80));
//		liste.add(new Score("KASIMI", 60));
//
//		// updateScoreFile(true);
//		updateScoreFile(false);
//	}
//
//	public void remplire() {
//		Vector<Score> liste = new Vector<>();
//		liste.add(new Score("Mohammed EL KHOU", 9000000));
//		liste.add(new Score("Mohammed KASIMI", 9000000));
//		liste.add(new Score("Mohammed", 30));
//		liste.add(new Score("EL KHOU", 80));
//		liste.add(new Score("KASIMI", 60));
//		liste.add(new Score("Mh", 140));
//		liste.add(new Score("Mk", 390));
//		liste.add(new Score("Unknown", 190));
//		liste.add(new Score("Unknown", 40));
//		liste.add(new Score("Unknown", 0));
//		liste.add(new Score("EL KHOU", 80));
//		liste.add(new Score("KASIMI", 60));
//		setListe(liste, new Score("player", 1234567), 2);
//	}
//
//	public void Lire() {
//		liste = getListe(2);
//		for (Score t : liste)
//			System.out.println(t.getPlayer() + "  : " + t.getScore());
//	}
//
//	public static void main(String[] args) {
//
//		HighScoreManager highScoreManager = new HighScoreManager("src/text/HighScore-1.dat");
//		highScoreManager.remplaireFile(highScoreManager);
//
//		// highScoreManager.remplire();
//		highScoreManager.Lire();
//
//	}

}