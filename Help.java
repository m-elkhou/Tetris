package Game;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.Timer;

public class Help extends JPanel {

	private static final long serialVersionUID = 1L;
	private int id, x;
	private JButton nextButton;
	private JButton backButton;
	private Timer timer;
	private Image backImage,img;
	private Vector<String> text;

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);// clear and repaint
		g.drawImage(backImage, 0, 0, getWidth(), getHeight(), this);
		nextButton.setBounds(getWidth() * 8 / 10 - 4, getHeight() * 24 / 28, getWidth() / 13, getHeight() / 13);
		backButton.setBounds(getWidth() / 10 - 6, getHeight() * 24 / 28, getWidth() / 13, getHeight() / 13);
		draw(g);
		if (id == 0)
			backButton.setVisible(false);
		else
			backButton.setVisible(true);
		
		if (id == 6)
			nextButton.setVisible(false);
		else
			nextButton.setVisible(true);
	}

	public Help() {

		setPreferredSize(new Dimension(1000, 700));
		setSize(1000, 700);
		setLayout(null);// new FlowLayout());
		setBorder(BorderFactory.createBevelBorder(0, Color.black, Color.black));
		setOpaque(true);
		addComponentListener(new ComponentAdapter() {

			@Override
			public void componentResized(ComponentEvent e) {
				revalidate();
				repaint();
			}
		});

		backImage = getImg("img/background4.jpg");
		id = x = 0;
		
		nextButton = new JButton(getImg("img/Icons/right.png", getWidth() / 13, getHeight() / 13));//"../img/nextt.png"
		nextButton.setBackground(new Color(0, 0, 0, 0));
		nextButton.setFocusable(false);
		nextButton.setOpaque(false);
		nextButton.setBounds(getWidth() * 8 / 10 - 4, getHeight() * 24 / 28, getWidth() / 13, getHeight() / 13);

		backButton = new JButton(getImg("img/Icons/left.png", getWidth() / 13, getHeight() / 13));//"../img/bac.png"
		backButton.setBackground(new Color(0, 0, 0, 0));
		backButton.setFocusable(false);
		backButton.setOpaque(false);
		backButton.setBounds(getWidth() / 10 - 6, getHeight() * 24 / 28, getWidth() / 13, getHeight() / 13);
		backButton.setVisible(false);

		nextButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (id < 6)
					x=0;
					Effait(1);
				repaint();
			}
		});

		backButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (id > 0)
					x=0;
					Effait(-1);
				repaint();
			}
		});

		add(backButton);
		add(nextButton);
		addKeyListener(new KeyListener() {

			@Override
			public void keyTyped(KeyEvent e) {
			}

			@Override
			public void keyReleased(KeyEvent e) {
			}

			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_RIGHT)
					nextButton.doClick();
				if (e.getKeyCode() == KeyEvent.VK_LEFT)
					backButton.doClick();
			}
		});
		
		validate();
		setVisible(true);
	}

	private void Effait(int i) {
		
		timer = new Timer(5, null);
		timer.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				x = x - i * 15;
				if ((x <= -1000 && i == 1) || (x >= 1000 && i == -1)){
					timer.stop();
					Effait2(i);
				}
				repaint();
			}
		});
		timer.start();
		backButton.setEnabled(false);
		nextButton.setEnabled(false);
	}

	private void Effait2(int i) {
		
		id += i;
		x = i * 1000;
		timer = new Timer(5, null);
		timer.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				x = x - i * 7;
				if ((x <= 0 && i == 1) || (x >= 0 && i == -1)) {
					timer.stop();
					x=0;
					backButton.setEnabled(true);
					nextButton.setEnabled(true);
				}		
				repaint();
			}
		});
		timer.start();
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

	private void draw(Graphics g) {
		switch (id) {
		case 0:
			draw0(g);
			break;
		case 1:
			draw1(g);
			break;

		case 2:
			draw2(g);
			break;

		case 3:
			draw3(g);
			break;

		case 4:
			draw4(g);
			break;
		case 5:
			draw5(g);
			break;
		case 6:
			draw6(g);
			break;
		default:
			id=0;
			draw0(g);
			break;
		}
	}

	private void draw0(Graphics g) {
		
		img = getImg("img/help/matrix.PNG");
		g.drawImage(img, 650 + x, 45, this);

		img = getImg("img/help/tetrimino.PNG");
		g.drawImage(img, 200 + x, 460, this);
		
		g.setColor(Color.white);
		g.setFont(new Font("Helvetica", Font.BOLD, 30));
		g.drawString("The Matrix :", 270 + x, 45);

		text = new Vector<>();
		text.add("Tetris is played on a 10 by 20 grid called the Matrix.");
		text.add("Shapes called Tetriminos fall from the top of the Matrix");
		text.add("and come to rest at the bottom. first the Tetriminos ");
		text.add("Only one Tetrimino falls at a time. At fall rather slowly;");
		text.add("as the game progresses they will fall faster and faster.");
		text.add("");
		text.add("");
		text.add("");
		text.add("Tip: There are seven shapes of Tetriminos, each made up of four small squares");
		text.add("called Minos. Check out the diagram below for the appearance of each Tetrimino.");

		g.setFont(new Font("Helvetica", Font.BOLD, 22));
		int l = 100;
		for (String t : text) {
			g.drawString(t, 50 + x, l);
			l += 38;
		}
	}

	private void draw1(Graphics g) {

		img = getImg("img/help/move.PNG");
		g.drawImage(img, 550 + x, 45, this);

		g.setColor(Color.white);

		text = new Vector<>();
		text.add("Using the arrow keys, you can adjust where and ");
		text.add("how the Tetriminos fall. By pressing the LEFT and");
		text.add("RIGHT Arrow keys, you can slide the falling ");
		text.add("Tetrimino from side-to-side.");
		text.add("You can’t slide a Tetrimino past the edge of the Matrix.");
		text.add("By pressing the UP Arrow key,");
		text.add("you can rotate the Tetrimino 90 degrees clockwise. ");
		text.add("You can move he Tetriminos even after they land ");
		text.add("at the bottom of the Matrix briefly. The Tetrimino will");
		text.add("Lock Down as next Tetrimino will begin to fall.");
		text.add("As your game improves and you begin to pay attention to the Tetriminos in your Next Queue,");
		text.add("you might find yourself getting a little impatient waiting for the piece to fall.");
		text.add("You have two options to speed up the game—the Soft Drop and the Hard Drop—and they’re");
		text.add("both really easy to do. The Soft Drop is performed by pressing the DOWN Arrow key—the Tetrimino ");
		text.add("will fall much faster than usual while you hold down the key, but as soon as you let go the piece ");
		text.add("will resume its normal pace. You retain complete control over the piece while doing a Soft Drop.");

		g.setFont(new Font("Helvetica", Font.BOLD, 30));
		g.drawString("Moving the Tetriminos", 150 + x, 45);
		g.setFont(new Font("Helvetica", Font.BOLD, 20));
		int l = 100;
		for (String t : text) {
			g.drawString(t, 20 + x, l);
			l += 32;
		}
	}

	private void draw2(Graphics g) {
		img = getImg("img/clav.PNG");
		g.drawImage(img, 480 + x, 20, 480, 230, this);

		text = new Vector<>();

		text.add("The Hard Drop is much less forgiving—hit");
		text.add("the Space Bar to cause the Tetrimino to fall");
		text.add("straight down, forgoing any further ");
		text.add("opportunity to move it. The Hard Drop ");
		text.add("is great for timed games where your goal ");
		text.add("is to get pieces into position as quickly ");
		text.add("as possible.");
		text.add("Pay attention to the Ghost Piece to help you see where the Tetrimino will fall ,");
		text.add("and don’t press the space bar until you’re ready!");

		g.setColor(Color.white);
		g.setFont(new Font("Helvetica", Font.BOLD, 22));
		int l = 100;
		for (String t : text) {
			g.drawString(t, 30 + x, l);
			l += 34;
		}
	}

	private void draw3(Graphics g) {
		
		img = getImg("img/help/line.PNG");
		g.drawImage(img, 650 + x, 20, this);
		img = getImg("img/help/clear.PNG");
		g.drawImage(img, 650 + x, 340, this);

		text = new Vector<>();
		text.add("The primary way to score points in Tetris is to");
		text.add("clear lines by manipulating the pieces so that");
		text.add("they fill horizontal row within the Matrix. As");
		text.add("the pieces fall, your goal is to move and spin ");
		text.add("them so that they line up evenly at the bottom");
		text.add("of the Matrix. To clear a line, every square of ");
		text.add("the row has to be filled.");
		text.add("");
		text.add("Tip: Fill in multiple lines at once for");
		text.add("bonus points. Drop Tetriminos so that");
		text.add("there is a gap at least two squares");
		text.add("deep, then drop a J-Tetrimino or");
		text.add("L-Tetrimino to clear two lines at");
		text.add("once, for example.");

		g.setColor(Color.white);
		g.setFont(new Font("Helvetica", Font.BOLD, 30));
		g.drawString("Clearing Lines", 270 + x, 45);
		g.setFont(new Font("Helvetica", Font.BOLD, 22));
		int l = 100;
		for (String t : text) {
			g.drawString(t, 30 + x, l);
			l += 34;
		}
	}

	private void draw4(Graphics g) {
		
		img = getImg("img/help/shift.PNG");
		g.drawImage(img, 670 + x, 45, this);
		
		text = new Vector<>();

		text.add("Ever get a Tetrimino that you just don’t know what to do with? ");
		text.add("Take care of the problem by using the Hold Queue—the place");
		text.add("to stash a Tetrimino that you can’t find a good place for.");
		text.add("The Hold Queue is the circle next to the upper-left corner of");
		text.add("the Matrix. When you begin a game, the Hold Queue is empty; ");
		text.add("to put a Tetrimino into it, press the SHIFT key. ");
		text.add("Thatpiece will jump into the Hold Queue and the next Tetrimino  ");
		text.add("in the Next Queue will begin to fall. The next time you hit ");
		text.add("the SHIFT key, the piece that is falling currently will be replaced ");
		text.add("by the piece in the Hold Queue. ");
		text.add("When the action gets really fast, you can also use the Hold Queue ");
		text.add(" to buy yourself a little time. If you can’t find a place for the Tetrimino that’s falling ,");
		text.add("wait until it falls most of the way down, then swap it out for the Tetrimino in the Hold Queue ,");
		text.add(" which will begin its descent at the top of the Matrix .");

		g.setColor(Color.white);
		g.setFont(new Font("Helvetica", Font.BOLD, 25));
		g.drawString("The Hold Queue", 270, 45);
		g.setFont(new Font("Helvetica", Font.BOLD, 20));

		int l = 100;
		for (String t : text) {
			g.drawString(t, 20 + x, l);
			l += 32;
		}
	}

	private void draw5(Graphics g) {
		
		img = getImg("img/help/next.PNG");
		g.drawImage(img, 670 + x, 45, this);
		
		g.setColor(Color.white);// new Color(220,180,200,170));
		g.setFont(new Font("Helvetica", Font.CENTER_BASELINE, 30));
		g.drawString("The Next Queue", 270, 45);
		g.setFont(new Font("Helvetica", Font.BOLD, 21));

		text = new Vector<>();
		text.add("The concept of the Next Queue is simple enough: as you play, ");
		text.add("you can see the next five Tetriminos that will fall.");
		text.add("But with so much else to pay attention to, the hard part  ");
		text.add("is actually putting the Next Queue to good use. ");
		text.add("There are two basic ways to get value out of the Next Queue. ");
		text.add("The first is to see which Tetrimino immediately follows the one");
		text.add("the one that is currently falling. Knowing that, you can make  ");
		text.add("better decisions about where to drop the current one. ");
		text.add("Or, you may see that the Tetrimino in the Hold Queue would ");
		text.add("work better with the next Tetrimino to drop. ");

		int l = 100;
		for (String t : text) {
			g.drawString(t, 20 + x, l);
			l += 38;
		}
	}

	private void draw6(Graphics g) {
		
		img = getImg("img/help/ghost.PNG");
		g.drawImage(img, 700 + x, 45, this);

		text = new Vector<>();

		text.add("Glimpse into the future with the Ghost Piece, the shaded  ");
		text.add("representation of where the current Tetrimino will land if you  ");
		text.add("don’t move it. The Ghost Piece is most helpful for lining up ");
		text.add("Hard and Soft Drops, especially when the stack is very low. ");
		text.add("And as the Tetrimino falls via a Soft Drop, glance at the Next ");
		text.add("Queue to start planning where to place the next Tetrimino. ");
		text.add("This kind of combined application of all the different techniques ");
		text.add("is  what makes good players great. ");
		text.add("Tip: The Ghost Piece can also be deceiving. It will show you only  ");
		text.add("where a Tetrimino will go if it falls straight down and stops.");
		text.add("But because you can continue to move Tetriminos side-to-side  ");
		text.add("after they land, you may be able to squeeze into a space that the Ghost Piece ");
		text.add("never showed you. This goes especially for T-Spins, where the Ghost Piece will never ");
		text.add("display the final resting spot of a T-Tetrimino. ");

		g.setColor(Color.white);
		g.setFont(new Font("Helvetica", Font.BOLD, 30));
		g.drawString("The Ghost Piece", 270, 45);

		g.setFont(new Font("Helvetica", Font.BOLD, 21));

		int l = 100;
		for (String t : text) {
			g.drawString(t, 20 + x, l);
			l += 33;
		}
	}
}
