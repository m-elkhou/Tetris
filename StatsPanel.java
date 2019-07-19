package Game;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

// creation de panel a gauche

class StatsPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	public GameElementPanel hold;
	public Timer clockPanel;
	public GameElementPanel levelPanel, linePanel, scorePanel;

	public StatsPanel() {
		
		setLayout( new FlowLayout() );
		setOpaque(false);
		setPreferredSize(new Dimension (175, 540));
		setSize(getPreferredSize());

		GameElementPanel holdL = new GameElementPanel("Hold", 0);
		holdL.setPreferredSize(new Dimension(100, 28));
		hold = new GameElementPanel();
		hold.setPreferredSize(new Dimension(88, 88));
		hold.setBorder(BorderFactory.createLineBorder(Color.darkGray));
		hold.setOpaque(false);
		
		GameElementPanel time = new GameElementPanel("Time", 0);
		time.setPreferredSize(new Dimension(100, 28));
		clockPanel = new Timer();
		
		GameElementPanel lineTPanel = new GameElementPanel("Lines", 0);
		lineTPanel.setPreferredSize(new Dimension(100, 28));
		linePanel = new GameElementPanel("0", 1);
		linePanel.setPreferredSize(new Dimension(82, 32));

		GameElementPanel scoreTPanel = new GameElementPanel("Score", 0);
		scoreTPanel.setPreferredSize(new Dimension(100, 28));
		scorePanel = new GameElementPanel("0", 1);
		scorePanel.setPreferredSize(new Dimension(100, 28));
		

		GameElementPanel levelTPanel = new GameElementPanel("Level", 0);
		levelTPanel.setPreferredSize(new Dimension(100, 28));
		levelPanel = new GameElementPanel("1", 1);
		levelPanel.setPreferredSize(new Dimension(82, 28));

		

		add(holdL);
		add(hold);

		JPanel pad = new JPanel();
		pad.setPreferredSize(new Dimension(100, 10));
		pad.setOpaque(false);
		add(pad);// l'ajout des differents panels dans l'interface a gauche ...

		add(time);
		add(clockPanel);

		JPanel pad2 = new JPanel();
		pad2.setPreferredSize(new Dimension(100, 8));
		pad2.setOpaque(false);
		add(pad2);

		add(lineTPanel);
		add(linePanel);

		add(scoreTPanel);
		add(scorePanel);

		add(levelTPanel);
		add(levelPanel);

		validate();
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
	}

	@Override
	public Dimension getPreferredSize() {
		return new Dimension(150, 440);
	}
}