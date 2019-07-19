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

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class ChooseLevel extends JPanel {

	private static final long serialVersionUID = 1L;
	private JButton easyLevel, mediumLevel, hardLevel, resumeButton, restartButton;
	private Image img, backImage;
	private int id;
	private JLabel label;

	public ChooseLevel() {
		id = 0;
		backImage = getImg("img/background0.jpg");

		setSize(1000, 700);// getPreferredSize());
		setLayout(new FlowLayout());
		setBorder(BorderFactory.createBevelBorder(0, Color.black, Color.black));
		setOpaque(true);
		addComponentListener(new ComponentAdapter() {

			@Override
			public void componentResized(ComponentEvent e) {
				revalidate();
				repaint();
			}
			@Override
			public void componentShown(ComponentEvent e) {
				
			}
			
			@Override
			public void componentHidden (ComponentEvent e) {
//				playingIn = false;
//				playingOut = true;
			}
			
		});

		label = new JLabel("Choose Level :");
		label.setFont(new Font("Helvetica", Font.BOLD, 40));// "Helvetica"
		label.setForeground(Color.black);

		easyLevel = new JButton(getImg("img/easy.png", getWidth() * 9 / 20, getHeight() / 12));
		easyLevel.setBackground(new Color(0, 0, 0, 0));
		easyLevel.setFocusable(false);
		easyLevel.setOpaque(false);

		mediumLevel = new JButton(getImg("img/medium.png", getWidth() * 9 / 20, getHeight() / 12));
		mediumLevel.setBackground(new Color(0, 0, 0, 0));
		mediumLevel.setFocusable(false);
		mediumLevel.setOpaque(false);
		
		hardLevel = new JButton(getImg("img/hard.png", getWidth() * 9 / 20, getHeight() / 12));
		hardLevel.setBackground(new Color(0, 0, 0, 0));
		hardLevel.setFocusable(false);
		hardLevel.setOpaque(false);
		

		easyLevel.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				id = 0;
			}
		});

		mediumLevel.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				id = 1;
			}
		});

		hardLevel.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				id = 2;
			}
		});
		
		resumeButton = new JButton(getImg("img/resume.png", getWidth() * 9 / 20, getHeight() / 12));
		resumeButton.setBackground(new Color(0, 0, 0, 0));
		resumeButton.setFocusable(false);
		resumeButton.setOpaque(false);
		resumeButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				Choose1();
			}
		});
		

		restartButton = new JButton(getImg("img/restart.png", getWidth() * 9 / 20, getHeight() / 12));
		restartButton.setBackground(new Color(0, 0, 0, 0));
		restartButton.setFocusable(false);
		restartButton.setOpaque(false);
		restartButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
//				Tetris.navigation.resetButton.doClick();
				GamePanel.navigation.resetButton.doClick();
				Choose1();
			}
		});
		
		validate();
	}

	public void setBackImage(Image backImage) {
		this.backImage = backImage;
	}
	
	public void Choose1() {
		removeAll();
			
		getPanel(80);
		add(label);
		getPanel(10);
		add(easyLevel);
		getPanel(80);	
		add(mediumLevel);
		getPanel(80);
		add(hardLevel);		
		
		revalidate();
		repaint();
	}
	
	public void Choose2() {
		
		removeAll();	
		getPanel(200);	
		add(resumeButton);
		getPanel(100);
		add(restartButton);
		revalidate();
		repaint();
	}
	
	public void getPanel(int hight) {
		JPanel panel =new JPanel();
		panel.setPreferredSize(new Dimension(2000, hight));
		panel.setSize(getPreferredSize());
		panel.setOpaque(false);
		add(panel);
	}
	
	@Override
	public void paintComponent(Graphics g) {

		super.paintComponent(g);// clear and repaint

		g.drawImage(backImage, 0, 0, getWidth(), getHeight(), this);

		img = getImg("img/Tetris-logo2.png");
		g.drawImage(img, getWidth() * 7 / 9, getHeight() * 6 / 9, getWidth() * 5 / 19, getHeight() * 6 / 18, this);
		img = getImg("img/Tetris-db-logo.png");
		g.drawImage(img, getWidth() * 1 / 25, getHeight() * 11 / 18, getWidth() * 5 / 19, getHeight() * 4/9, this);
	}

	public JButton getResumeButton() {
		return resumeButton;
	}
	
	public JButton getRestartButton() {
		return restartButton;
	}
	public JButton getEasyLevel() {
		return easyLevel;
	}

	public JButton getMediumLevel() {
		return mediumLevel;
	}

	public JButton getHardLevel() {
		return hardLevel;
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

	public int getId() {
		return id;
	}

}
