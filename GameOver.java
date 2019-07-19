/*cette classe est responsable de l'interface graphique 
 * du jeu y compris les boutons,les carreaux de timer,
 * l'affichage des statistiques du jeu ....
 * */
package Game;

import javax.swing.JPanel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameOver extends JPanel {

	private static final long serialVersionUID = 1L;

	int wight, hight;
	JLabel title;
	JPanel stats;
	ScoreTablePanel[] scores;
	JButton playAgainButton, submitButton;

	String time;
	int tetrominos, Score, lines, singles, doubles, triples, tetrises;
	float linesPerMinute, minosPerMinute;
	// dans cette classe il ya une autre classe qui s'appelle scoretablePanel !

	public GameOver(int min, int sec, int milli, int score, int line, int single, int doub, int trip, int tetris,
			int tetros) {
		String hundredth, second, minute;

		if (milli < 10) {
			hundredth = "0" + milli; // millisecondes
		} else
			hundredth = "" + milli;

		if (sec < 10) {
			second = "0" + sec;
		} else
			second = "" + sec;

		if (min < 10) {
			minute = "0" + min;
		} else
			minute = "" + min;

		time = new String(minute + ":" + second + ":" + hundredth); // definition de format de timer 00:00:00

		Score = score;
		lines = line;
		singles = single;
		doubles = doub;
		triples = trip;
		tetrises = tetris;

		tetrominos = tetros;

		linesPerMinute = lines / (min + ((float) ((float) sec + ((float) milli / 1000)) / 60));// lignes / minutes
		minosPerMinute = tetrominos / (min + ((float) ((float) sec + ((float) milli / 1000)) / 60));// minos / minutes

		setPreferredSize(new Dimension(1120, 700));
		setSize(getPreferredSize());
		setLayout(new FlowLayout());
		setOpaque(false);

		title = new JLabel(getImg("img/gameover1.png", 800, 220));
		setBackground(new Color(0, 0, 0, 0));
//		setOpaque(false);

		stats = new JPanel();
		stats.setLayout(new FlowLayout());
		stats.setOpaque(false);
		stats.setPreferredSize(new Dimension(850, 250));

		// cette table s'occupe d'afficher les statistiques de jeu sous forme d'un
		// tableau

		scores = new ScoreTablePanel[9];// 8

		scores[0] = new ScoreTablePanel(new Color(0, 0, 0, 128));
		scores[1] = new ScoreTablePanel(new Color(128, 128, 128, 148));
		scores[2] = new ScoreTablePanel(new Color(200, 200, 200, 148));
		scores[3] = new ScoreTablePanel(new Color(200, 200, 200, 148));
		scores[4] = new ScoreTablePanel(new Color(128, 128, 128, 148));
		scores[5] = new ScoreTablePanel(new Color(128, 128, 128, 148));
		scores[6] = new ScoreTablePanel(new Color(200, 200, 200, 148));
		scores[7] = new ScoreTablePanel(new Color(200, 200, 200, 148));
		scores[8] = new ScoreTablePanel(new Color(128, 128, 128, 148));
		

		scores[0].setLabel("  Score: ");
		scores[0].setValue("" + Score+"    ");
		scores[0].label.setForeground(Color.WHITE);
		scores[0].label.setFont(new Font("Tahoma", Font.BOLD, 20));
		scores[0].value.setForeground(Color.white);
		scores[0].value.setFont(new Font("Tahoma", Font.BOLD, 20));
		scores[1].setLabel("Rows: ");
		scores[1].setValue("" + lines);

		scores[2].setLabel("Lines Per Minute: ");
		scores[2].setValue("" + linesPerMinute);

		scores[3].setLabel("Minos Locked Down: ");
		scores[3].setValue("" + tetrominos);

		scores[4].setLabel("Minos Per Minute: ");
		scores[4].setValue("" + minosPerMinute);

		scores[5].setLabel("Singles: ");
		scores[5].setValue("" + singles);

		scores[6].setLabel("Doubles: ");
		scores[6].setValue("" + doubles);

		scores[7].setLabel("Triples: ");
		scores[7].setValue("" + triples);

		scores[8].setLabel("Tetrises: ");
		scores[8].setValue("" + tetrises);
		
		stats.add(scores[0]);
		JPanel pad1 = new JPanel();
		pad1.setPreferredSize(new Dimension(2000,5));
		pad1.setOpaque(false);
		stats.add(pad1);
		
		for (int i = 1; i < scores.length; i++)
			stats.add(scores[i]); // * l'ajout des statistiques a l'objet de type panel

		JPanel textPanel = new JPanel();
		textPanel.setLayout(new FlowLayout());
		textPanel.setOpaque(false);// la transparence ==false
		playAgainButton = new JButton(getImg("img/Icons/replay.png", 150, 50));
		playAgainButton.setOpaque(false);
		playAgainButton.setBackground(new Color(0, 0, 0, 0));
		playAgainButton.setForeground(Color.black);
		playAgainButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				Vue.restart();
				GamePanel.navigation.playButton.doClick();
//				GamePanel.status=Status.COUNTDOWN;
			}
		}); // l'ajout de l'ecouteur de l'action au bouton paly again
		
		JPanel pad = new JPanel();
		pad.setOpaque(false);
		pad.setPreferredSize(new Dimension(2000, 2));

		add(title);
		add(stats);
		add(pad);
		add(playAgainButton);

		setVisible(true);
	}

	// la creation d'une autre classe ...

	class ScoreTablePanel extends JPanel // le tableau qui contient les statistiques de jeu
	{
		private static final long serialVersionUID = 1L;
		Color color;
		JLabel value;
		JLabel label;
		
		public ScoreTablePanel(Color clr) {
			setLayout(new BorderLayout(5, 5));
			setPreferredSize(new Dimension(300, 38));
			setForeground(Color.BLACK);
			color = clr;
		}

		public void setLabel(String sh)// methode d'ajouter les champs d'elements
		{
			label = new JLabel("   " + sh);
			label.setForeground(Color.BLACK);
			this.add(label, BorderLayout.WEST);
		}

		public void setValue(String sh) // ajouter la valeur au label
		{
			value = new JLabel(sh + "   ");
			value.setForeground(Color.BLACK);
			this.add(value, BorderLayout.EAST);
		}

		@Override
		public void paintComponent(Graphics g) {
			g.setColor(color);
			g.fillRoundRect(0, 0, this.getWidth(), this.getHeight(), 4, 4);// dessiner les rectangles sous forme ovale
		}
	}

	public Image getImg(String sh) {
		try {
			return new ImageIcon(getClass().getResource(sh)).getImage();
		} catch (Exception e) {
			System.out.println("!! Ereur : image {\"" + sh + "\"} not found :: " + e.getMessage());
		}
		return null;
	}

	public ImageIcon getImg(String sh, int width, int height) {
		try {
			return new ImageIcon(getImg(sh).getScaledInstance(width, height, Image.SCALE_SMOOTH));
		} catch (Exception e) {
			System.out.println("!! Ereur : image {\"" + sh + "\"} not found :: " + e.getMessage());
			return null;
		}
	}
}