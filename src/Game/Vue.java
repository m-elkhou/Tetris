package Game;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GraphicsEnvironment;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

public class Vue extends JFrame {

	private static final long serialVersionUID = 1L;

	static JTabbedPane tabbePane = new JTabbedPane(JTabbedPane.LEFT);
	private JPanel barPanel;

	static JPanel inter;

//	public static Tetris tetris;
	public static GamePanel gamePanel;
	public static ChooseLevel chooseLevel;
	private JPanel homeTP;

	public static Option option;
	public static HighScore highScore;
	private JPanel optionTP;

	public static Help helpTP;

	public static About aboutTP;

	public Boolean maximized;
	public JButton maximizer;
	public JButton exitButton;
	public JButton ReduireButton;
	public int xx, xy;

	public Vue() {

		barPanel = new JPanel();
		inter = new JPanel();
		barPanel.addMouseMotionListener(new MouseMotionAdapter() {
			public void mouseDragged(MouseEvent evt) {
				barPanelMouseDragged(evt);
			}
		});
		barPanel.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent evt) {
				barPanelMousePressed(evt);
			}
		});

		exitButton = new JButton(" ");
		exitButton.setIcon(new ImageIcon(getImg("img/cancel.png")));
		exitButton.setBackground(null);
		exitButton.setBorder(null);
		exitButton.setFocusable(false);
		exitButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				WindowLanch.exit.setVisible(true);
			}
		});

		maximizer = new JButton(" ");
		maximizer.setBackground(null);
		maximizer.setBorder(null);
		maximizer.setFocusable(false);
		maximized = false;
		maximizer.setIcon(new ImageIcon(getImg("img/maximize.png")));
		maximizer.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent evt) {
				maximizerMouseClicked(evt);
			}
		});

		ReduireButton = new JButton(" ");
		ReduireButton.setBackground(null);
		ReduireButton.setBorder(null);
		ReduireButton.setFocusable(false);
		ReduireButton.setIcon(new ImageIcon(getImg("img/upload.png")));
		ReduireButton.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent evt) {
				Undecorated();
			}
		});

		JPanel barPanelButton = new JPanel();
		barPanelButton.add(ReduireButton);
		barPanelButton.add(maximizer);
		barPanelButton.add(exitButton);
		barPanelButton.setBackground(null);

		JLabel iconLabel = new JLabel("     Tetris", getImg("img/Tetris-logo1.png", 30, 25), 2);
		iconLabel.setFont(new Font("Showcard Gothic", Font.PLAIN, 23));
		iconLabel.setForeground(Color.WHITE);
		iconLabel.setBackground(null);
		iconLabel.setOpaque(false);

		JLabel esp = new JLabel("            ", 2);
		barPanel.setLayout(new BorderLayout());
		barPanel.add(barPanelButton, BorderLayout.EAST);// WEST
		barPanel.add(esp, BorderLayout.WEST);
		barPanel.add(iconLabel);// , BorderLayout.CENTER);
		barPanel.setBackground(new Color(18, 30, 49));// 18,30,49//54, 64, 69
		// barPanel.setFocusable(false);
		barPanel.validate();
		// --------------------------------------------------------------------------------------------------------------------------------------------------
		aboutTP = new About();

		chooseLevel = new ChooseLevel();
		chooseLevel.Choose1();
		chooseLevel.getEasyLevel().addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				openGame();
			}
		});

		chooseLevel.getMediumLevel().addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				openGame();
			}
		});

		chooseLevel.getHardLevel().addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				openGame();
			}
		});

		chooseLevel.getResumeButton().addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				openGame();
			}
		});

		highScore = new HighScore();
		highScore.showTop10();
		highScore.getBackButton().addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (highScore.getId() <= 0) {
					optionTP.removeAll();
					optionTP.add(option, BorderLayout.CENTER);
					optionTP.revalidate();
					optionTP.repaint();
				}

			}
		});


		gamePanel = new GamePanel(this);

		option = new Option();
		option.getHighScoreButton().addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				optionTP.removeAll();
				optionTP.add(highScore, BorderLayout.CENTER);
				optionTP.revalidate();
				optionTP.repaint();
			}
		});
		option.getUndecoratedButton().addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
//				if(JFrame.isDefaultLookAndFeelDecorated())
				Undecorated();
			}
		});

		helpTP = new Help();
		homeTP = new JPanel();
		optionTP = new JPanel();

		homeTP.setLayout(new BorderLayout(0, 0));
		homeTP.add(chooseLevel, BorderLayout.CENTER);
		homeTP.validate();

		optionTP.setLayout(new BorderLayout(0, 0));
		optionTP.add(option, BorderLayout.CENTER);
		optionTP.validate();

		inter.setLayout(new BorderLayout());

		tabbePane.addTab("    Home  ", getImg("img/Icons/home1.png", 40, 40), homeTP);//"../img/home1.png"
		tabbePane.addTab("  Option  ", getImg("img/Icons/option.png", 40, 40), optionTP);//"../img/option.png"
		tabbePane.addTab("   Help    ", getImg("img/Icons/help2.png", 40, 40), helpTP);//"../img/aide1.png"
		tabbePane.addTab("About-us", getImg("img/Icons/about-us2.png", 40, 40), aboutTP);//"../img/Icons/about-us.jpg" // "../img/Icons/about.png"
		tabbePane.setSelectedIndex(0);
		tabbePane.setFont(new Font("Tahoma", Font.BOLD, 16));// setFont(new Font("Segoe UI Semilight", 5, 15));
		tabbePane.setBackground(new Color(24, 43, 53));// 72, 86, 98
		tabbePane.setForeground(Color.black);
		tabbePane.setFocusable(true);
		tabbePane.validate();

		inter.add(tabbePane);
		inter.setBackground(new Color(18, 33, 43));// 54, 63, 73
		inter.setFocusable(true);
		inter.validate();

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setPreferredSize(new Dimension(1120, 700));
		setSize(getPreferredSize());
		setLocationRelativeTo(null);
		setUndecorated(true);
		setResizable(true);
		setIconImage(getImg("img/Tetris-logo1.png", 60, 50).getImage());
		setTitle("Tetris");
		add(barPanel, BorderLayout.NORTH);
		add(inter, BorderLayout.CENTER);
		setFocusable(true);
		validate();
		setVisible(false);
//		gamePanel.setFocusable(true);
	}

	public void openGame() {
		inter.removeAll();
		inter.add(gamePanel, BorderLayout.CENTER);
		start();
		inter.revalidate();
		inter.repaint();
	}
	private void Undecorated() {
		dispose();
		if(this.isUndecorated())
			this.setUndecorated(false);
		else
			this.setUndecorated(true);
		pack();
		revalidate();
		repaint();
		setVisible(true);
	}
	private void maximizerMouseClicked(MouseEvent evt) {
		if (!maximized) {
			// handle fullscreen - taskbar
			setExtendedState(JFrame.MAXIMIZED_BOTH);
			GraphicsEnvironment env = GraphicsEnvironment.getLocalGraphicsEnvironment();
			setMaximizedBounds(env.getMaximumWindowBounds());
			ImageIcon ii = new ImageIcon(getImg("img/minimize.png"));
			maximizer.setIcon(ii);
		} else {
			setExtendedState(JFrame.NORMAL);
			ImageIcon ii = new ImageIcon(getImg("img/maximize.png"));
			maximizer.setIcon(ii);
		}
		maximized = maximized == true ? false : true;
	}

	private void barPanelMouseDragged(MouseEvent evt) {
		int x = evt.getXOnScreen();
		int y = evt.getYOnScreen();
		this.setLocation(x - xx, y - xy);
	}

	private void barPanelMousePressed(MouseEvent evt) {
		xx = evt.getX();
		xy = evt.getY();
	}

	public void setBarButton() {
		exitButton.setIcon(getImg("img/Icons/exit.png", 30, 30));
		ReduireButton.setIcon(getImg("img/Icons/reduir.png", 30, 30));
	}

	public Image getImg(String sh) {
		try {
//			return new ImageIcon(sh).getImage();
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

	public static void start() {
		gamePanel.start();
	}

	public static void stop() {
		gamePanel.stop();
	}

	public static void restart() {
		gamePanel.restart();
	}

}
