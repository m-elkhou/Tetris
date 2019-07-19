package Game;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FileDialog;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.io.File;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;

public class Option extends JPanel implements ActionListener {

	private static final long serialVersionUID = 1L;
	boolean musicIsOn;
	public JLabel sonLabel;
	private JButton sonButton, SonButton;
	private JButton BackButton, backButton;
	private JButton ghostButton, exitButton , UndecoratedButton;
	private JButton highScoreButton;
	private JPanel panel;
	private Image img, backImage;
	private Boolean backIsChanged;
	private int i = 0, j = 0, y, dely;
	private Timer timer;
	private Vector<String> sonString;

	public Option() {

		setSize(1000, 700);
		setLayout(new FlowLayout());
		setBorder(BorderFactory.createBevelBorder(0, Color.black, Color.black));
		setOpaque(true);
		addComponentListener(new ComponentAdapter() {

			@Override
			public void componentResized(ComponentEvent e) {
				repaint();
			}
		});

		img = getImg("img/background4.jpg");
		backIsChanged = false;

		BackButton = new JButton("Change Background",
				getImg("img/background.png", getWidth() / 13, getHeight() / 13));
		BackButton.setFont(new Font("Showcard Gothic", Font.PLAIN, 23));
		BackButton.setBackground(new Color(0, 0, 0, 0));
		BackButton.setFocusable(false);
		BackButton.setOpaque(false);
		BackButton.addActionListener(this);

		sonLabel = new JLabel("Music");
		sonLabel.setFont(new Font("Showcard Gothic", Font.PLAIN, 23));
		sonLabel.setForeground(Color.WHITE);
		sonLabel.setIcon(getImg("img/sound-off.png", getWidth() / 13, getHeight() / 13));

		sonButton = new JButton();
		sonButton.setBackground(new Color(0, 0, 0, 0));
		sonButton.setFocusable(false);
		sonButton.setOpaque(false);
		sonButton.add(sonLabel);
		sonButton.addActionListener(this);

		highScoreButton = new JButton("High Scores",
				getImg("img/high-scores.png", getWidth() / 13, getHeight() / 13));
		highScoreButton.setFont(new Font("Showcard Gothic", Font.PLAIN, 23));
		highScoreButton.setForeground(Color.WHITE);
		highScoreButton.setBackground(new Color(0, 0, 0, 0));
		highScoreButton.setFocusable(false);
		highScoreButton.setOpaque(false);
		highScoreButton.addActionListener(this);

		exitButton = new JButton(getImg("img/exit1.png", getWidth() / 13, getHeight() / 13));
		exitButton.setFont(new Font("Showcard Gothic", Font.PLAIN, 23));
		exitButton.setBackground(new Color(0, 0, 0, 0));
		exitButton.setFocusable(false);
		exitButton.setOpaque(false);
		exitButton.addActionListener(this);

		backButton = new JButton(getImg("img/Icons/refresh.png", getWidth() / 13, getHeight() / 13));
		backButton.setFont(new Font("Showcard Gothic", Font.PLAIN, 23));
		backButton.setBackground(new Color(0, 0, 0, 0));
		backButton.setFocusable(false);
		backButton.setOpaque(false);
		backButton.addActionListener(this);

		SonButton = new JButton(getImg("img/music.png", getWidth() / 13, getHeight() / 13));
		SonButton.setBackground(new Color(0, 0, 0, 0));
		SonButton.setFocusable(false);
		SonButton.setOpaque(false);
		SonButton.addActionListener(this);

		ghostButton = new JButton("Tetrominoes's shadow",
				getImg("img/Icons/ghostN.png", getWidth() / 13, getHeight() / 13));
		ghostButton.setFont(new Font("Showcard Gothic", Font.PLAIN, 23));
		ghostButton.setForeground(Color.WHITE);
		ghostButton.setBackground(new Color(0, 0, 0, 0));
		ghostButton.setFocusable(false);
		ghostButton.setOpaque(false);
		ghostButton.addActionListener(this);

		UndecoratedButton = new JButton("Undecorated Frame",
				getImg("img/Icons/undecorated0.png", getWidth() / 13, getHeight() / 13));
		UndecoratedButton.setFont(new Font("Showcard Gothic", Font.PLAIN, 23));
		UndecoratedButton.setForeground(Color.WHITE);
		UndecoratedButton.setBackground(new Color(0, 0, 0, 0));
		UndecoratedButton.setFocusable(false);
		UndecoratedButton.setOpaque(false);
		UndecoratedButton.addActionListener(this);
		
		
		
		panel = new JPanel();
		panel.setOpaque(false);
		panel.setPreferredSize(new Dimension(500, 650));
		panel.add(BackButton);
		panel.add(backButton);
		panel.add(SonButton);
		panel.add(sonButton);
		panel.add(ghostButton);
		panel.add(highScoreButton);
		panel.add(exitButton);
		panel.add(UndecoratedButton);
		
		JPanel pad = new JPanel();
		pad.setBackground(null);
		pad.setPreferredSize(new Dimension(this.getWidth(), 200));
		pad.setOpaque(false);
		add(pad, BorderLayout.NORTH);
		add(panel, BorderLayout.CENTER);
		validate();

		j = 0;
		sonString = new Vector<>();
		sonString.add("son/techno.wav");
		sonString.add("son/i_win.wav");
		sonString.add("son/pb/Yoshi's-Island.pb");
		sonString.add("son/pb/Cristophe_Colomb.sn");
		sonString.add("son/pb/Super_Bomberman.pb");
		sonString.add("son/pb/Tetri_Arcade.pb");
		sonString.add("son/pb/Tetris.pb");
		sonString.add("son/pb/Tetris_Classique.pb");
	}

	public JButton getHighScoreButton() {
		return highScoreButton;
	}
	
	public JButton getUndecoratedButton() {
		return UndecoratedButton;
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

	public void efait() {
		backIsChanged = true;
		y = -100;
		dely = 0;

		timer = new Timer(4, null);
		timer.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				if (y < 350)
					y++;
				else {
					if (dely < 700)
						dely++;
					else {
						backIsChanged = false;
						timer.stop();
					}
				}
				repaint();
			}
		});
		timer.start();
	}

	public void draw(Graphics g) {
		if (backIsChanged) {

			g.setColor(Color.black);
			g.drawLine(getWidth() * 6 / 10, 250, getWidth() * 7 / 10 - 2, y - 6 - 2);
			g.drawLine(getWidth() * 6 / 10, 250, getWidth() * 7 / 10 - 2, y + 30 + 210 + 2);
			g.drawLine(getWidth() * 7 / 10, y + 6, getWidth() * 7 / 10 + 260, y + 6);

			g.drawRect(getWidth() * 7 / 10 - 2, y + 30 - 2, 300 + 4, 210 + 4);
			g.drawRect(getWidth() * 7 / 10 - 1, y + 30 - 1, 300 + 2, 210 + 2);
			g.drawRect(getWidth() * 7 / 10, y + 30, 300, 210);

			g.setColor(Color.white);

			g.setFont(new Font("Tahoma", Font.BOLD, 20));
			g.drawString("Background is changed !!", getWidth() * 7 / 10, y);

			g.drawImage(backImage, getWidth() * 7 / 10, 30 + y, 300, 210, this);
		}
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);// clear and repaint
		g.drawImage(img, 0, 0, getWidth(), getHeight(), this);
		draw(g);
	}

	@SuppressWarnings("deprecation")
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == backButton) {
			if (i < 16)
				i++;
			else
				i = 0;
			backImage = getImg("img/background" + i + ".jpg");
			Vue.gamePanel.setBackImage(backImage);
			Vue.chooseLevel.setBackImage(backImage);
			Vue.chooseLevel.repaint();
			Vue.gamePanel.repaint();

			efait();
			repaint();
		}

		if (e.getSource() == BackButton) {//
//			try {
//				JFileChooser chooseDirec = new JFileChooser();
//				chooseDirec.setFileSelectionMode(JFileChooser.FILES_ONLY);// importer l'image de background
//				chooseDirec.showOpenDialog(Vue.gamePanel);
//				File file = chooseDirec.getSelectedFile();
//				backImage = new ImageIcon(file.getAbsolutePath()).getImage();
//				Vue.gamePanel.setBackImage(backImage);
//				Vue.chooseLevel.setBackImage(backImage);
//			} catch (Exception ereur) {
//				System.out.println("!! Error: " + ereur.getMessage());
//			}
			
			FileDialog fd = new FileDialog(new JFrame(), "son/Techno-Tetris(Remix).wav");
			// afficher le FileChooser...
			fd.show();
			try {
				// assigner à notre fichier de départ, qui était "null" jusqu'à présent, un
				// fichier réel
				File file = new File(fd.getDirectory() + "/" + fd.getFile());
				// si l'assignation a bien fonctionné, le fichier n'est plus "null", donc:
				if (file != null) {
					// ouvrir notre flux d'entrée sur ce fichier
					backImage = new ImageIcon(file.getAbsolutePath()).getImage();
					Vue.gamePanel.setBackImage(backImage);
					Vue.chooseLevel.setBackImage(backImage);
				}
			} catch (Exception ereur) {
				System.err.println(ereur);
			}
			
			Vue.chooseLevel.repaint();
			Vue.gamePanel.repaint();
			efait();
			repaint();
		}

		if (e.getSource() == sonButton) {
			musicIsOn = Vue.gamePanel.musicOn;//NavigationBar.musicIsOn;
			if (musicIsOn) {
				sonLabel.setIcon(getImg("img/sound-off.png", getWidth() / 13, getHeight() / 13));
				GamePanel.navigation.soundIcon.setIcon(getImg("img/sound-off.png", 32, 32));
				Vue.gamePanel.tetrisTheme.stop();
				Vue.gamePanel.musicOn = false;
			} else {
				sonLabel.setIcon(getImg("img/sound-on.png", getWidth() / 13, getHeight() / 13));
				GamePanel.navigation.soundIcon.setIcon(getImg("img/sound-on.png", 32, 32));
				if (GamePanel.status == GamePanel.Status.PLAYING) {
					Vue.gamePanel.tetrisTheme.loop();
				}
				Vue.gamePanel.musicOn = true;
			}

			musicIsOn = musicIsOn == true ? false : true;
//			NavigationBar.musicIsOn = musicIsOn;
			Vue.gamePanel.musicOn = musicIsOn;
			
			revalidate();
			repaint();
			GamePanel.navigation.revalidate();
			GamePanel.navigation.repaint();
		}

		if (e.getSource() == SonButton) {

			if (j < 7)
				j++;
			else
				j = 0;

			Vue.gamePanel.tetrisTheme.stop();
//			NavigationBar.musicIsOn =false;
			Vue.gamePanel.musicOn = false;
			
			Vue.gamePanel.setTetrisTheme(sonString.get(j));
//			Vue.gamePanel.tetrisTheme.loop();
//			Vue.gamePanel.musicOn = true;
			sonButton.doClick();
			Vue.gamePanel.tetrisTheme.loop();
			Vue.gamePanel.musicOn = true;
		}

		if (e.getSource() == ghostButton) {
			if (Vue.gamePanel.ghosted) {
				Vue.gamePanel.ghosted = false;
				ghostButton.setIcon(getImg("img/Icons/ghostN.png", getWidth() / 13, getHeight() / 13));
			} else {
				Vue.gamePanel.ghosted = true;
				ghostButton.setIcon(getImg("img/Icons/ghost.png", getWidth() / 13, getHeight() / 13));
			}
			revalidate();
			repaint();
		}

		if (e.getSource() == exitButton) {
			WindowLanch.exit.setVisible(true);
		}
	}
}
