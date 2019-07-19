package Game;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.util.Vector;

import javax.swing.Timer;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class About extends JPanel {
	private static final long serialVersionUID = 1L;
	
	public Timer timer;
	private int y = 1000,x=0;
	private Vector<String> text;
	private Vector<Image> img;
	
	public About() {
		
		img = new Vector<>();
		
		img.add(getImg("img/background10.jpg"));
		img.add(getImg("img/fsdm.png"));
		img.add(getImg("img/usmba.png"));
		img.add(getImg("img/contact_us.png"));
		img.add(getImg("img/el_khou1.png"));
		img.add(getImg("img/M.EL_KASMI2.png"));
		
		text = new Vector<>();

		text.add("               ** About Us **");
		text.add(" ");
		text.add("Le travail présenté dans cette jeux a été effectué dans le cadre");
		text.add("de la préparation de la  Licence fondamentale en filière Science");
		text.add("Mathématique et informatique (Projet de Fin d’étude SMI S6 2018)");
		text.add("à la faculté des sciences Dhar El  Mehraz FSDM .");
		text.add("");
		text.add("Nous sommes des étudiants en FSDM,"); //
		text.add("3 éme années filiere science Math Informatique (smi s6).");
		text.add("");
		text.add("Mohammed EL KHOU   ,  CIN :  1513784799");
		text.add("Email : mhmh2702@gmail.com");
		text.add("            mohammed.elkhou1@usmba.ac.ma");
		text.add("Tél   :   0614585293");
		text.add("");
		text.add("");
		text.add("");
		text.add("                                Mohammed KASMI  ,  CNE : 1513742761");
		text.add("                                Email  :  mohammed.kasmi1@usmba.ac.ma");
		text.add("                                Tél      :  0655721335");
		text.add("");
		text.add("");
		text.add("Encadrant   :   M. Mohammed El Mohajir");
		text.add("");
		text.add("                                                 - - - -");
		text.add("");
		text.add("         ** Remerciement **");
		text.add("");
		text.add("Au terme de ce projet, Nous tenons à exprimer notre profonde ");
		text.add("gratitude et notre immense respect à notre encadrant Monsieur");
		text.add("Mohammed El Mohajir, Professeur  en informatique à la faculté");
		text.add("des sciences Dhar El Mehraz pour sa disponibilité, ses avis  ");
		text.add("éclairés, et ses conseils judicieux. Nos vifs remerciements");
		text.add("accompagnés de toute notre gratitude s'adressent également à ");
		text.add("tous nos professeurs qui ont gardé un œil attentif sur ");
		text.add("et l’avancement de notre projet, donnant toujours des remarques ");
		text.add("le déroulement très constructives ainsi pour la confiance qu’ils");
		text.add("nous ont fait .Nous  tenons à exprimer notre plaisir de travailler");
		text.add("sous leurs directives.");
		text.add("");
		text.add("    A Nos Très Chers Parents");
		text.add("Tous les mots du monde ne sauraient exprimer l’immense amour ");
		text.add("que nous portons pour vous, ni la profonde gratitude que nous");//
		text.add("vous témoignons pour vous pour tous les efforts et les ");
		text.add("sacrifices que vous avez fournis à fin de nous garantir une ");
		text.add("bonne  instruction. C’est à travers vos encouragements et vos ");//
		text.add("critiques que nous nous sommes réalisés. Nous espérons avoir ");//
		text.add("répondu aux espoirs que vous avez fondés en nous. Nous vous ");//
		text.add("rendons hommage par ce modeste travail en guise de notre  ");
		text.add("reconnaissance éternelle et de notre amour infini . ");
		text.add("Que Dieu tout puissant vous garde et vous procure la santé, ");
		text.add("le bonheur et la longue vie pour que vous demeuriez le flambeau ");
		text.add("illuminant notre chemin.");
		text.add("       Nous dédions aussi ce travail à nos familles");
		text.add("et à tous nos professeurs de la FSDM");
		text.add("       A tous nos collègues, et nous  leurs souhaitons le succès ");
		text.add("et l’atteinte de leurs ambitions pour leurs encouragements ");
		text.add("incessants…");
		text.add("                                             ...");
		text.add("");

		timer = new Timer(7, null);
		timer.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				action();
			}
		});
		timer.start();

		addComponentListener(new ComponentAdapter() {
			@Override
			public void componentResized(ComponentEvent e) {
				x=(getRootPane().getWidth()-940)/2-70;
				repaint();
			}
			@Override
			public void componentHidden(ComponentEvent e) {
				timer.stop();
				y=880;
			}

			@Override
			public void componentShown(ComponentEvent e) {
				timer.start();
			}
		});
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);// clear and repaint
		x=(getRootPane().getWidth()-940)/2-70;
		g.drawImage(img.get(0), 0, 0,getRootPane().getWidth(), getRootPane().getHeight(), this);
		g.drawImage(img.get(1), 780+x, y - 300, 164, 164, this);											
		g.drawImage(img.get(2), 50+x, y - 300, 138, 170, this);
		g.drawImage(img.get(3), 320+x, y + 200 + 17 * 38, 186*2, 230*2, this);
		g.drawImage(img.get(4), 720+x, y + 174 + 4 * 38, 186, 230, this);
		g.drawImage(img.get(5), 50+x, y + 200 + 9 * 38, 186, 230, this);
		
		g.setFont(new Font("Broadway", Font.ITALIC, 24));
		g.setColor(Color.black);
		g.drawString("Université Sidi Mohamed Ben Abdallah", 190 + 50+x, y - 230);
		g.drawString("Faculté des Sciences Dhar Mahraz – Fès", 190 + 50+x, y - 200);

		int l = 0;
		
		for (String t : text) {
			if (l == 0 || l == 26 * 38)
				g.setFont(new Font("Broadway", Font.ITALIC, 60)); 								
			else
				g.setFont(new Font("World of Water", Font.LAYOUT_NO_START_CONTEXT, 30)); // Font.HANGING_BASELINE
			if ((y + l) > 380 && (y + l) < 410) {
				g.setColor(Color.white);
			}
			g.drawString(t, 50+x, y + l); // g.drawString(text.get(0), 50, y);
			g.setColor(Color.black);
			l += 38;
		}	
	}


	private void action() {
		y--;
		repaint();
		if (y <= -2100)
			y = 1000;
		repaint();
	}

	public Image getImg(String sh) {
		Image img0;
		try {
			img0 = new ImageIcon(getClass().getResource(sh)).getImage();
			return img0;
		} catch (Exception e) {
			System.out.println("!! Ereur : image {\"" + sh + "\" :: " + e.getMessage());
			return new ImageIcon().getImage();
		}
	}

}
