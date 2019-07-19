package Game;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import Game.HighScoreManager;
import Game.Score;

public class HighScore extends JPanel {

	private static final long serialVersionUID = 1L;

	private JLabel highScorelabel, playerlabel, scorelabel,conglabel;
	private JPanel panel, playersPanel, scoresPanel;
	private JButton backButton, nextButton, signUpButton, cancelButton;
	private JTextField textField;
	
	private final int max = 10;
	private int id, paint, score;
	
	HighScoreManager highScoreManager;
	private Vector<String> level;
	Vector<Score> list;
	
	Boolean usedIn;
	
	public HighScore() {
		
		level = new Vector<>();
		level.add("img/easy.png");
		level.add("img/medium.png");
		level.add("img/hard.png");
		
		id = Vue.chooseLevel.getId();
		
		highScoreManager = new HighScoreManager(id);
		list = new Vector<>();
		list = highScoreManager.getListe(id);
		
		paint = 0;
		
		setPreferredSize(new Dimension(1000, 700));
		setSize(getPreferredSize());
		setLayout(new FlowLayout());
		setOpaque(false);
		setBackground(null);
		setBorder(BorderFactory.createBevelBorder(0, Color.black, Color.black));
		addComponentListener(new ComponentAdapter() {

			@Override
			public void componentResized(ComponentEvent e) {

				highScorelabel.setPreferredSize(new Dimension(getWidth() * 4 / 10, getHeight() * 2 / 30));

				playerlabel.setPreferredSize(new Dimension(getWidth() * 7 / 20, getHeight() * 1 / 20));
				scorelabel.setPreferredSize(new Dimension(getWidth() * 7 / 20, getHeight() * 1 / 20));

				panel.setPreferredSize(new Dimension(getWidth() * 7 / 10, getHeight() * 13 / 20 + 10));

				playersPanel.setPreferredSize(new Dimension(getWidth() * 7 / 20 - 19, getHeight() * 18 / 20));
				scoresPanel.setPreferredSize(new Dimension(getWidth() * 7 / 20 - 19, getHeight() * 18 / 20));

				conglabel.setPreferredSize(new Dimension(getWidth() / 2, getHeight() * 5 / 20));

				revalidate();
				repaint();
			}
		});
		
		backButton = new JButton(getImg("img/Icons/suivantt0.png", 45, 45));
		backButton.setBackground(new Color(0, 0, 0, 0));
		backButton.setFocusable(false);
		backButton.setOpaque(false);
		backButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (id > 0) {
					id--;
					list = highScoreManager.getListe(id);
					showTop10();
				}
					
				revalidate();
				repaint();
			}
		});
		
		highScorelabel = new JLabel("High Score");
		highScorelabel.setFont(new Font("Tahoma", Font.BOLD, 26));
		highScorelabel.setHorizontalAlignment(SwingConstants.CENTER);
		highScorelabel.setForeground(new Color(250, 67, 78));// Color.black);
		highScorelabel.setBackground(new Color(30, 30, 30, 141));
		highScorelabel.setOpaque(true);
		highScorelabel.setBorder(BorderFactory.createBevelBorder(0, Color.darkGray, Color.black));
		highScorelabel.setPreferredSize(new Dimension(getWidth() * 4 / 10, getHeight() * 2 / 30));
		highScorelabel.setSize(getPreferredSize());

		nextButton = new JButton(getImg("img/Icons/suivant0.png", 45, 45));
		nextButton.setBackground(new Color(0, 0, 0, 0));
		nextButton.setFocusable(false);
		nextButton.setOpaque(false);
		nextButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (id < 2) {
					id++;
				}
				else
					id=0;

				list = highScoreManager.getListe(id);
				showTop10();
				revalidate();
				repaint();
			}
		});
		
		cancelButton = new JButton(getImg("img/Icons/cancel.png",getWidth()/6,getHeight()/13));
		cancelButton.setBackground(new Color(0, 0, 0, 0));
		cancelButton.setFocusable(false);
		cancelButton.setOpaque(false);
		
		validate();
		showTop10();
		signUp();
	}

	public void showTop10() {
		paint = 1;
		
		playerlabel = new JLabel("Player");
		playerlabel.setFont(new Font("Showcard Gothic", Font.PLAIN, 23));
		playerlabel.setHorizontalAlignment(SwingConstants.CENTER);
		playerlabel.setForeground(Color.black);
		playerlabel.setBackground(new Color(0, 0, 0, 150));
		playerlabel.setOpaque(true);
		playerlabel.setBorder(BorderFactory.createBevelBorder(0, new Color(250, 67, 78), Color.black));
		playerlabel.setPreferredSize(new Dimension(getWidth() * 7 / 20, getHeight() * 1 / 20));
		playerlabel.setSize(getPreferredSize());
		
		scorelabel = new JLabel("Score");
		scorelabel.setFont(new Font("Showcard Gothic", Font.PLAIN, 23));
		scorelabel.setHorizontalAlignment(SwingConstants.CENTER);
		scorelabel.setForeground(Color.gray);
		scorelabel.setBackground(new Color(0, 0, 0, 150));
		scorelabel.setOpaque(true);
		scorelabel.setBorder(BorderFactory.createBevelBorder(1, new Color(250, 67, 78), Color.black));
		scorelabel.setPreferredSize(new Dimension(getWidth() * 7 / 20, getHeight() * 1 / 20));
		scorelabel.setSize(getPreferredSize());
		
		panel = new JPanel();
		panel.setPreferredSize(new Dimension(getWidth() * 7 / 10, getHeight() * 13 / 20 + 10));
		panel.setSize(getPreferredSize());
		panel.setLayout(new FlowLayout());
		panel.setBorder(BorderFactory.createBevelBorder(1, Color.darkGray, Color.black));
		panel.setOpaque(true);
		panel.setBackground(new Color(0, 0, 0, 150));// 105,105,105,120));//
		
		playersPanel = new JPanel();
		playersPanel.setLayout(new FlowLayout());
		playersPanel.setBorder(BorderFactory.createBevelBorder(0, Color.darkGray, Color.black));
		playersPanel.setPreferredSize(new Dimension(getWidth() * 7 / 20 - 19, getHeight() * 18 / 20));
		playersPanel.setSize(getPreferredSize());
		playersPanel.setBackground(new Color(105, 105, 105, 120));// 0,0,0,150));//

		scoresPanel = new JPanel();
		scoresPanel.setLayout(new FlowLayout());
		scoresPanel.setBorder(BorderFactory.createBevelBorder(1, Color.darkGray, Color.black));
		scoresPanel.setPreferredSize(new Dimension(getWidth() * 7 / 20 - 19, getHeight() * 18 / 20));
		scoresPanel.setSize(getPreferredSize());
		scoresPanel.setBackground(new Color(105, 105, 105, 120));// 0,0,0,150));//

		panel.add(playersPanel);
		panel.add(scoresPanel);
	
		remplir();
		removeAll();
		
		add(backButton);

		add(highScorelabel);

		add(nextButton);

		remplir("", this, 20);

		add(playerlabel);

		add(scorelabel);

		add(panel);

		revalidate();
		setVisible(true);
	}
	
	public Boolean testHighScore(int score) {
		this.score = score;
		if(score > list.get(9).getScore() )
			return true;
		return false;			
	}

	private void remplir() {
		list = new Vector<>();
		list = highScoreManager.getListe(id);
		for (int i = 0; i < max; i++) {
			remplir(list.get(i).getPlayer(), playersPanel, 1);
			remplir("" + list.get(i).getScore(), scoresPanel, 1);
		}
	}
		
	private void remplir(String sh, JPanel panel, int esp) {
		if (sh != "") {
			JLabel label = new JLabel(" " + sh + " ");
			label.setFont(new Font("Tahoma", Font.PLAIN, 23));
			label.setHorizontalAlignment(SwingConstants.CENTER);
			label.setForeground(new Color(250, 67, 78));// new Color(112,112,112)
			label.setOpaque(true);
			label.setBackground(new Color(0, 0, 0, 150));// 105,105,105,120));//
			label.setBorder(BorderFactory.createBevelBorder(0, Color.darkGray, Color.black));
			panel.add(label);
		}
		
		JPanel pad2 = new JPanel();
		pad2.setPreferredSize(new Dimension(2000, esp));
		pad2.setSize(pad2.getPreferredSize());
		pad2.setOpaque(false);
		panel.add(pad2);
	}

	public void signUp() {
		paint = 2;
		removeAll();
		                   //          vvvvvvvvvvvVVVVVVVVVVVVVVVVVVVV    !!!!!!!!!!!!!!!!!!!!!!!!!
		conglabel = new JLabel("<html><h1 align = \"center\" style=\"font-size: 30px;\">Congratulations</h1><h1 align = \"center\" style=\"font-size: 21px;\">On your High-Score : <br>"+score+"</h1></html>");
		conglabel.setFont(new Font("Showcard Gothic", Font.PLAIN, 60));
		conglabel.setHorizontalAlignment(SwingConstants.CENTER);
		conglabel.setForeground(new Color(250, 67, 78));
		conglabel.setBackground(new Color(0, 0, 0, 100));
		conglabel.setOpaque(true);
		conglabel.setBorder(BorderFactory.createBevelBorder(0, Color.gray, Color.black));
		conglabel.setPreferredSize(new Dimension(getWidth() / 2, getHeight() * 5 / 20));
		conglabel.setSize(getPreferredSize());
		
		textField = new JTextField();
		textField.setText("Unknown");
		textField.setColumns(30);
		textField.setFont(new Font("Tahoma", Font.PLAIN, 23));
		textField.setHorizontalAlignment(SwingConstants.CENTER);
		textField.setForeground(new Color(0, 0, 0));
		textField.setBorder(BorderFactory.createBevelBorder(0, Color.black, new Color(82, 82, 82)));
		
		signUpButton = new JButton(getImg("img/signupnow.png",getWidth()/5,getHeight()/11));
		signUpButton.setBackground(new Color(0, 0, 0, 0));
		signUpButton.setFocusable(false);
		signUpButton.setOpaque(false);
		signUpButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				list = highScoreManager.getListe(id);
				highScoreManager.setListe(list,new Score(textField.getText(), score), id);
				list = highScoreManager.getListe(id);
				showTop10();
				if(id==0) {
					nextButton.doClick();
					backButton.doClick();
				}
				else {
					backButton.doClick();
					nextButton.doClick();
				}
				revalidate();
				repaint();
			}
		});
		
		remplir("", this, 30);
		
		add(conglabel);
		
		remplir("", this, 220);
		
		add(textField);
		
		remplir("", this, 10);
		
		add(signUpButton);
		
		remplir("", this, 10);
		
		add(cancelButton);
		
		revalidate();
		repaint();
	}///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);// clear and repaint
		Image img = getImg("img/background6.jpg");
		g.drawImage(img, 0, 0, getWidth(), getHeight(), this);// larire plant !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!

		g.drawImage(getImg("img/Tetris-logo2.png"), getWidth() * 7 / 9, getHeight() * 6 / 9, getWidth() * 5 / 19,
				getHeight() * 6 / 18, this);

		

		if (paint == 1) {
			
			g.drawImage(getImg("img/high-scores.png"), getWidth() * 10 / 26, getHeight() * 6 / 19, getWidth() * 5 / 21,
					getHeight() * 6 / 19, this);
			
			g.setColor(Color.black);
			g.setFont(new Font("Tahoma", Font.BOLD, 23));
			g.drawImage(getImg(level.get(id)), getWidth() * 15/ 42, 50, getWidth() * 6/ 21, getHeight() /16, this);

			img = getImg("img/Icons/arp.png");
			g.setFont(new Font("Tahoma", Font.BOLD, 15));
			g.setColor(new Color(192, 192, 192));// 131,131,131));
			for (int i = 0; i < max; i++) {
				g.drawString((i + 1) + ".", 90, 160 + i * 44);
				g.drawImage(img, 120, 160 + i * 44 - 27, 31, 33, this);
			}

			if (id == 0)
				backButton.setIcon(getImg("img/Icons/suivantt1.png", 45, 45));
			else
				backButton.setIcon(getImg("img/Icons/suivantt0.png", 45, 45));
		}

		if (paint == 2) {
			g.drawImage(getImg("img/high-scores.png"), getWidth() * 10 / 26, getHeight() * 1 / 29, getWidth() * 5 / 21,
					getHeight() * 6 / 19, this);
			
			g.drawImage(getImg("img/SignUpNow.gif"), getWidth() * 10 / 26, getHeight() * 6 / 19, getWidth() * 5 / 21,getHeight() * 6 / 19, this);
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
	
	public void setId(int id) {
		this.id = id;
	}

	public int getId() {
		return id;
	}
	
	public JButton getBackButton() {
		return backButton;
	}
	
	public JButton getCancelButton() {
		return cancelButton;
	}
}
