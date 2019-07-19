
package Game;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Image;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JLabel;


public class NavigationBar extends JPanel implements ActionListener {

	private static final long serialVersionUID = 1L;

	public static boolean musicIsOn;

	JButton playButton;
	JButton soundToggle;
	JButton resetButton;
	JButton menuButton;

	JLabel playLabel;
	JLabel soundIcon;

	public NavigationBar() {

		setLayout(new BorderLayout(0, 0));
		setPreferredSize(new Dimension(1120, 110));
		setSize(getPreferredSize());
		setOpaque(false);
		// setFocusable(false);
		setBackground(new Color(200, 200, 200, 148));
		musicIsOn = false;

		playLabel = new JLabel("Play");
		playLabel.setFont(new Font("Showcard Gothic", Font.PLAIN, 23));
		playLabel.setForeground(Color.BLACK);
		playLabel.setIcon(getImg("img/play.png", 32, 32));
		
		playButton = new JButton();
		playButton.add(playLabel);
		playButton.setBackground(new Color(0, 0, 0, 0));
		playButton.setFocusable(false);
		playButton.setOpaque(false);
		playButton.addActionListener(this);
		playButton.setEnabled(false);
		
		soundIcon = new JLabel();
		soundIcon.setIcon(getImg("img/sound-off.png", 32, 32));

		soundToggle = new JButton();
		soundToggle.add(soundIcon);
		soundToggle.setBackground(new Color(0, 0, 0, 0));
		soundToggle.setFocusable(false);
		soundToggle.setOpaque(false);
		soundToggle.addActionListener(this);

		resetButton = new JButton(getImg("img/replay2.png", 32, 32));
		resetButton.addActionListener(this);
		resetButton.setBackground(new Color(0, 0, 0, 0));
		resetButton.setFocusable(false);
		resetButton.setOpaque(false);
		resetButton.addActionListener(this);

		menuButton = new JButton(getImg("img/Icons/menu0.png", 45, 45));
		menuButton.setBackground(new Color(0, 0, 0, 0));
		menuButton.setFocusable(false);
		menuButton.setOpaque(false);
		menuButton.addActionListener(this);

		JPanel pad1 = new JPanel();
		pad1.setLayout(new FlowLayout());
		pad1.setOpaque(false);
		pad1.setBackground(null);
		pad1.add(playButton);
		pad1.add(resetButton);

		JPanel pad2 = new JPanel();
		pad2.setOpaque(false);
		pad2.setBackground(null);
		pad2.add(soundToggle);

		JPanel pad3 = new JPanel();
		pad3.setLayout(new BorderLayout());
		pad3.setOpaque(false);
		pad3.setBackground(null);
		pad3.setPreferredSize(new Dimension(getWidth(), 50));
		pad3.setSize(pad3.getPreferredSize());
		pad3.add(pad1, BorderLayout.WEST);
		pad3.add(pad2, BorderLayout.EAST);

		JPanel pad4 = new JPanel();
		pad4.setOpaque(false);
		pad4.setBackground(null);
		pad4.add(menuButton);

		add(pad3, BorderLayout.NORTH);
		add(pad4, BorderLayout.WEST);

		setButtonsOff();

		validate();
		setVisible(true);
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

	public void setButtonsOff() {
		playButton.setFocusable(false);
		soundToggle.setFocusable(false);
		menuButton.setFocusable(false);
		resetButton.setFocusable(false);
	}

	public JButton getMenuButton() {
		return menuButton;
	}

	@Override
	public void actionPerformed(ActionEvent event) {

		if (event.getSource() == playButton) {
			if (GamePanel.status == GamePanel.Status.START) {
				playLabel.removeAll();
				playLabel.setText("Pause");
				playLabel.setIcon(getImg("img/pause.png", 32, 32));
				Vue.gamePanel.countDownSound.play();
				GamePanel.status = GamePanel.Status.COUNTDOWN;
				Vue.start();
			}

			else if (GamePanel.status == GamePanel.Status.PAUSED) {
				playLabel.removeAll();
				playLabel.setText("Pause");
				playLabel.setIcon(getImg("img/pause.png", 32, 32));
				GamePanel.status = GamePanel.Status.PLAYING;
				if (Vue.gamePanel.musicOn)
					Vue.gamePanel.tetrisTheme.loop();
				Vue.start();
			}

			else if (GamePanel.status == GamePanel.Status.PLAYING) {
				playLabel.removeAll();
				playLabel.setText("Play");
				playLabel.setIcon(getImg("img/play.png", 32, 32));
				GamePanel.status = GamePanel.Status.PAUSED;
				Vue.gamePanel.tetrisTheme.stop();
				Vue.stop();
				playButton.setEnabled(true);

			} else if (GamePanel.status == GamePanel.Status.GAMEOVER) {
				resetButton.doClick();
				playButton.doClick();
			}
			
			revalidate();
			Vue.gamePanel.repaint();
		}

		if (event.getSource() == soundToggle) {
			musicIsOn = Vue.gamePanel.musicOn;
			if (musicIsOn) {
				soundIcon.setIcon(getImg("img/sound-off.png", 32, 32));
				Vue.option.sonLabel.setIcon(
						getImg("img/sound-off.png", Vue.option.getWidth() / 13, Vue.option.getHeight() / 13));
				Vue.gamePanel.tetrisTheme.stop();
				Vue.gamePanel.musicOn = false;
			} else {
				soundIcon.setIcon(getImg("img/sound-on.png", 32, 32));
				Vue.option.sonLabel.setIcon(
						getImg("img/sound-on.png", Vue.option.getWidth() / 13, Vue.option.getHeight() / 13));
				if (GamePanel.status == GamePanel.Status.PLAYING) {
					Vue.gamePanel.tetrisTheme.play();
				}
				Vue.gamePanel.musicOn = true;
			}

			musicIsOn = musicIsOn == true ? false : true;
			revalidate();
			Vue.option.revalidate();
			Vue.option.repaint();
		}

		if (event.getSource() == resetButton) {
			Vue.restart();
			playLabel.removeAll();
			playLabel.setText("Play");
			playLabel.setIcon(GamePanel.navigation.getImg("img/play.png", 32, 32));
		}

		if (event.getSource() == menuButton) {
			
			if (GamePanel.status == GamePanel.Status.PLAYING) {

				GamePanel.status = GamePanel.Status.PAUSED;
				playLabel.removeAll();
				playLabel.setText("Play");
				playLabel.setIcon(getImg("img/play.png", 32, 32));
				Vue.gamePanel.tetrisTheme.stop();
				Vue.stop();
				Vue.gamePanel.revalidate();
				Vue.gamePanel.repaint();
			} 
			else if (GamePanel.status != GamePanel.Status.PAUSED) {
				GamePanel.status = GamePanel.Status.START;
				playLabel.removeAll();
				playLabel.setText("Play");
				playLabel.setIcon(getImg("img/play.png", 32, 32));
			}
				Vue.chooseLevel.Choose2();
				Vue.inter.removeAll();
				Vue.inter.add(Vue.tabbePane, BorderLayout.CENTER);
				Vue.inter.revalidate();
				Vue.inter.repaint();
		}

		revalidate();
		repaint();
		setButtonsOff();
	}
}