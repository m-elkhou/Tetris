package Game;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;

import javax.swing.BorderFactory;
import javax.swing.JPanel;


// la classe de la queue des pieces

class QueuePanel extends JPanel {
	
	private static final long serialVersionUID = 1L;
	GameElementPanel[] minoPanels;

	public QueuePanel() {
		setLayout(new BorderLayout());
		setOpaque(false);
		JPanel queue = new JPanel(); // dessiner l'interface de la queue
		queue.setLayout(new FlowLayout());
		queue.setOpaque(false);

		minoPanels = new GameElementPanel[5];

		GameElementPanel queueL = new GameElementPanel("Next", 0);
		queueL.setPreferredSize(new Dimension(88, 28));
		queue.add(queueL);

		for (int i = 0; i < 5; i++)// creation des pieces aleatoires
		{
			minoPanels[i] = new GameElementPanel(GamePanel.minos.minos.get(i));

			if (i == 0)// test sur l'indice de 1er
			{
				minoPanels[i].setPreferredSize(new Dimension(88, 88));
				minoPanels[i].setBorder(BorderFactory.createLoweredBevelBorder());
			}

			if (i == 1) // et 2eme zonne de la queue
			{
				minoPanels[i].setPreferredSize(new Dimension(79, 69));
			}
			queue.add(minoPanels[i]);
		}

		add(queue, BorderLayout.CENTER);
	}

	public void adjust() {
		for (int i = 0; i < 5; i++) {
			minoPanels[i].mino = new Mino(GamePanel.minos.minos.get(i), true);
		}
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
	}

	@Override
	public Dimension getPreferredSize() // retourne la dimension de panneau
	{
		return new Dimension(110, 440);
	}
}