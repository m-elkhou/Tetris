package Game;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;

public class WindowLanch extends JPanel {
	private static final long serialVersionUID = 1L;
	private JFrame window;
	private JFrame frame;
	private JButton exitButton, exitBarButton;
	private JButton nextButton;
	private int width, height, sleep = 0;;
	private Efait efait;
	private Image backImage, iconImage;
	public static Vue vue;
	public static Exit exit;

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);// clear and repaint
		g.drawImage(backImage, 0, 0, width, height, this);
	}

	public WindowLanch(int i) {
		super();
	}

	public WindowLanch() {

		width = 1000 - 150;
		height = 700 - 150;
		backImage = getImg("img/background20.jpg");
		iconImage = getImg("img/Tetris-logo1.png", 60, 50).getImage();
	
		window = new JFrame();
		window.setPreferredSize(new Dimension(width + 100, height + 100));
		window.setSize(window.getPreferredSize());
		window.setLocationRelativeTo(null);
		window.setUndecorated(true);
		window.setBackground(new Color(0, 0, 0, 0));
		window.setIconImage(iconImage);
		window.addKeyListener(new KeyListener() {

			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_RIGHT)
					nextButton.doClick();
				else if (e.getKeyCode() == KeyEvent.VK_LEFT)
					exitButton.doClick();
			}

			@Override
			public void keyReleased(KeyEvent e) {
			}

			@Override
			public void keyTyped(KeyEvent e) {
			}

		});

		nextButton = new JButton(getImg("img/next.png", width / 10, height / 10));
		exitButton = new JButton(getImg("img/back.png", width / 10, height / 10));//"img/back.png"

		nextButton.setBackground(new Color(0, 0, 0, 0));
		nextButton.setFocusable(false);
		nextButton.setOpaque(false);
		nextButton.setBounds(width * 8 / 10 - 4, height * 19 / 23 - 6, width / 9, height / 9);
		
		exitButton.setBackground(new Color(0, 0, 0, 0));
		exitButton.setFocusable(false);
		exitButton.setOpaque(false);
		exitButton.setBounds(width *1 / 10 - 6, height * 19 / 23 + 6, width / 9, height / 9);

		setLayout(null);
		setPreferredSize(new Dimension(width, height));
		setSize(getPreferredSize());
		setBorder(BorderFactory.createBevelBorder(0, Color.black, Color.black));
		setOpaque(true);
		add(nextButton);
		add(exitButton);
		nextButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent ev) {

				frame.remove(efait);
				efait = new Efait(1, frame);
				frame.setVisible(true);
				frame.add(efait);
				window.setVisible(false);
				window.removeAll();
			}
		});

		exitButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent ev) {
				exit.setVisible(true);
				revalidate();
			}
		});

		exitBarButton = new JButton(" ");
		exitBarButton.setIcon(new ImageIcon(getImg("img/cancel.png")));
		exitBarButton.setBackground(null);
		exitBarButton.setBorder(null);
		exitBarButton.setFocusable(false);
		exitBarButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				exit.setVisible(true);
				revalidate();
			}
		});

		JLabel label = new JLabel("   Tetris");
		label.setFont(new Font("Showcard Gothic", Font.PLAIN, 23));
		label.setForeground(Color.WHITE);
		label.setBackground(null);
		label.setOpaque(false);

		JPanel barPanel = new JPanel();
		barPanel.setPreferredSize(new Dimension(width + 100, 40));
		barPanel.setSize(barPanel.getPreferredSize());
		barPanel.setLayout(new BorderLayout());
		barPanel.add(exitBarButton, BorderLayout.EAST);// WEST
		barPanel.add(label, BorderLayout.WEST);
		barPanel.setBackground(new Color(18, 30, 49));// 18,30,49//54, 64, 69
		barPanel.setBorder(BorderFactory.createBevelBorder(0, Color.DARK_GRAY, Color.black));

		JPanel panel = new JPanel();
		panel.setPreferredSize(new Dimension(width + 100, 40));
		panel.setSize(barPanel.getPreferredSize());
		panel.setBackground(new Color(18, 30, 49));// 18,30,49//54, 64, 69
		panel.setBorder(BorderFactory.createBevelBorder(1, Color.DARK_GRAY, Color.black));

		window.setLayout(new FlowLayout());
		window.add(barPanel);
		window.add(this);
		window.add(panel);
		window.validate();

		frame = new JFrame();
		frame.setSize(1000, 700); // new
		frame.setLocationRelativeTo(null);
		frame.setUndecorated(true); // pour enlever la bordre et le cadre
		frame.setBackground(new Color(0, 0, 0, 0));
		efait = new Efait(0, frame);
		frame.setVisible(true);
		frame.setIconImage(iconImage);
		frame.add(efait);

		exit = new Exit();
//		vue = new Vue();
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
			Image img = getImg(sh);
			img = img.getScaledInstance(width, height, Image.SCALE_SMOOTH);//getImg(sh).getScaledInstance(width, height, Image.SCALE_SMOOTH)
			return new ImageIcon(img);
		} catch (Exception e) {
			System.out.println("!! Ereur : image {\"" + sh + "\"} not found :: " + e.getMessage());
			return null;
		}
	}

	public class Efait extends JPanel {
		private static final long serialVersionUID = 1L;
		protected int w, h, w1, h1, i, y;//
		public Timer timer;
		Image img;

		public Efait(int i, JFrame frame) { // i=0 => ^ ogmantie
			this.i = i;
			img = getImg("img/Tetris-logo1.png");
			setSize(1000, 700);
			setBackground(new Color(0, 0, 0, 0));
			setOpaque(false);
			w = width * i - 200;
			h = height * i;
			y = height - 40;
			timer = new Timer(6, null);
			timer.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent arg0) {
					action(i);
					repaint();
				}
			});

			exit = new Exit();
			timer.start();
		}

		public void action(int i) {
			if (i == 1) {
				if (h > 0) {
					w = w - (width - 100) / 50;
					h = h - height / 50;
				} else {
					action2();
				}
			} else if (i == 0) {
				if (h < height) {
					w = w + (width - 100) / 50;
					h = h + height / 50;
				} else {
					timer.stop();
					window.setVisible(true);
					frame.setVisible(false);
					vue = new Vue();
					nextButton.setEnabled(true);
				}
			}

		}

		public void action2() {
			// timer.setDelay(0);
			if (y > 250) {
				w = h = 0;
				w1 += 500 / 140;
				h1 += 350 / 140;
				y -= 2;
			} else {
				if (sleep < 200)
					sleep += 6;
				else {
					timer.stop();
					frame.setVisible(false);
					vue.setVisible(true);
				}
			}
		}

		@Override
		public void paintComponent(Graphics g) {
			super.paintComponent(g);// clear and repaint
			g.drawImage(backImage, 50 + (width - w) / 2, (height - h) / 2 + 40, w, h, this);
			g.drawImage(img, 50 + (width - w1) / 2, y, w1, h1, this);
		}
	}

	public class Exit extends JDialog {
		private static final long serialVersionUID = 1L;

		JButton noButton, yesButton, exitBarButton;

		public Exit() {
			setTitle("Exit");
			setUndecorated(true);
			setPreferredSize(new Dimension(700, 250));
			setSize(getPreferredSize());
			setBackground(new Color(0, 0, 0, 0));
			setLocationRelativeTo(null);
			setLayout(new FlowLayout());

			JLabel txt = new JLabel("  Do you really want to exit ?         ");
			txt.setFont(new Font("World of Water", Font.HANGING_BASELINE, 45));
			txt.setForeground(Color.black);
			txt.setLocation(0, 30);

			noButton = new JButton(getImg("img/Icons/no.png", 100, 100));
			noButton.setBackground(null);
			noButton.setFocusable(true);

			yesButton = new JButton(getImg("img/Icons/yes.png", 100, 100));
			yesButton.setBackground(null);
			yesButton.setFocusable(true);

			noButton.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent ev) {
					exit.setVisible(false);
				}
			});
			yesButton.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent ev) {
					System.exit(0);
				}
			});

			JPanel panel = new JPanel();
			panel.setPreferredSize(new Dimension(650, 200));
			panel.setSize(panel.getPreferredSize());
			panel.setBackground(Color.darkGray);
			panel.setBorder(BorderFactory.createBevelBorder(0, Color.black, Color.black));// gray));
			panel.add(txt);
			panel.add(noButton);
			panel.add(yesButton);

			exitBarButton = new JButton(" ");
			exitBarButton.setIcon(new ImageIcon(getImg("img/cancel.png")));
			exitBarButton.setBackground(null);
			exitBarButton.setBorder(null);
			exitBarButton.setFocusable(false);
			exitBarButton.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent arg0) {
					setVisible(false);
					revalidate();
				}
			});

			JLabel label = new JLabel("   Exit");
			label.setFont(new Font("Showcard Gothic", Font.PLAIN, 23));
			label.setForeground(Color.WHITE);
			label.setBackground(null);
			label.setOpaque(false);

			JPanel barPanel = new JPanel();
			barPanel.setPreferredSize(new Dimension(700, 40));
			barPanel.setSize(barPanel.getPreferredSize());
			barPanel.setLayout(new BorderLayout());
			barPanel.add(exitBarButton, BorderLayout.EAST);// WEST
			barPanel.add(label, BorderLayout.WEST);
			barPanel.setBackground(new Color(18, 30, 49));// 18,30,49//54, 64, 69
			barPanel.setBorder(BorderFactory.createBevelBorder(0, new Color(18, 30, 49), Color.black));

			add(barPanel);
			add(panel);

			validate();
			setVisible(false);
		}
	}
}
