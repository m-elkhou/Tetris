
package Game;

import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.BorderFactory;
import javax.swing.JLabel;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Font;

public class GameElementPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	private int id = 2;
	public Mino mino;
	public JLabel label;

	public GameElementPanel(String sh, int id) {
		this.id = id;
		setLayout(new BorderLayout(0,0));
		setBackground(null);
		setOpaque(false);
		setBorder(BorderFactory.createLineBorder(Color.black));
		setPreferredSize(new Dimension(100, 60));
		label = new JLabel(sh);
		label.setHorizontalAlignment(SwingConstants.CENTER);
		if(id==0) {
			label.setFont(new Font("Helvetica", Font.PLAIN, 20));
			label.setForeground(Color.white);
		}
		else if(id==1) {
			label.setForeground(Color.black);
			label.setFont(new Font("Helvetica", Font.PLAIN, 22));
		}
		add(label,BorderLayout.CENTER);
	}

	public GameElementPanel(Mino mino) {
		this.mino = mino;
		setBackground(null);
		setOpaque(false);
		setBorder(BorderFactory.createLineBorder(Color.darkGray));
		setPreferredSize(new Dimension(70, 60));
	}
	
	public GameElementPanel() {
		setBackground(null);
		setOpaque(false);
		setBorder(BorderFactory.createLineBorder(Color.black));
		setPreferredSize(new Dimension(70, 60));
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);

		if (id == 0) {
			g.setColor(new Color(12, 12, 12, 128));
			g.fillRoundRect(0, 0, this.getWidth(), this.getHeight(), 10, 10);
		} else if (id == 1) {
			g.setColor(new Color(100, 100, 100, 120));
			g.fillRoundRect(0, 0, this.getWidth(), this.getHeight(), 10, 10);
		} else if (id == 2) {
			g.setColor(new Color(128, 128, 128, 128));
			g.fillRoundRect(0, 0, this.getWidth(), this.getHeight(), 10, 10);

			if (mino != null)
				mino.drawInQueue(g, this.getWidth(), this.getHeight());
		}
	}
}
