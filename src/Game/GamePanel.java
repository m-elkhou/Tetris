
package Game;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Dimension;
import java.awt.BorderLayout;
import java.awt.Point;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;

import javax.swing.JPanel;
import javax.swing.Timer;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JFrame;

import java.applet.AudioClip;
import java.applet.Applet;

public class GamePanel extends JPanel implements ComponentListener {
	private static final long serialVersionUID = 1L;

	public enum Status {
		START, PLAYING, PAUSED, COUNTDOWN, GAMEOVER
	};// enumeration

	public static Status status;

	public AudioClip tetrisTheme, countDownSound;
	private AudioClip moveSound, rotateSound, hardDropSound, gameOverSound, countOverSound;

	private int FPS = 25, compteur, minoDelay; // pour le timer
	protected boolean minoDelaying;

	private int countDown; // le compte decroissant (3...2...1)

	public boolean musicOn;

	public GameOver gameStats;

	private MatrixPanel matrix;
	private StatsPanel stats;
	private QueuePanel queue;

	private boolean[] keys;
	protected boolean hasHeld, ghosted;

	public static MinoQueue minos;
	protected Mino currentMino;

	protected Ghost ghost;
	public HighScore highScore;

	private int score, level, lines, singles, doubles, triples, tetrises, minoCount, comboCount;

	int id;
	// -----------------------------
	public static int width = 1000;
	public static int height = 655;
	public static int dim = 440 / 20;

	public static NavigationBar navigation;

	protected static ImageIcon backIcon;
	protected Image backImage;
	// ---------------------------------
	protected Timer timer;

	public GamePanel(JFrame frame) {
		setPreferredSize(new Dimension(width, height));
		setSize(getPreferredSize());
		setLayout(new FlowLayout());
		// addKeyListener(new KeyHandler());// l'ajout de l'ecouteur des touches
		setBackground(null);
		setFocusable(true);
		setOpaque(false);// la transparence
		setBorder(BorderFactory.createBevelBorder(0, Color.black, Color.black));
		setOpaque(true); //// true////////////////////
		frame.addKeyListener(new KeyHandler());

		tetrisTheme = Applet.newAudioClip(getClass().getResource("son/techno.wav"));// "tetris-theme.aiff"));
		rotateSound = Applet.newAudioClip(getClass().getResource("son/rotateSound.wav"));
		moveSound = Applet.newAudioClip(getClass().getResource("son/rotateSound.wav"));
		hardDropSound = Applet.newAudioClip(getClass().getResource("son/hardDropSound.wav"));
		countDownSound = Applet.newAudioClip(getClass().getResource("son/countDownSound.wav"));
		countOverSound = Applet.newAudioClip(getClass().getResource("son/countDownSound.wav"));
		gameOverSound = Applet.newAudioClip(getClass().getResource("son/GAME_OVER.wav"));

		backImage = getImg("img/background0.jpg");
		navigation = new NavigationBar();
		validate();
		resetGame();

		timer = new Timer(FPS, null);
		timer.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				action();
			}
		});
	}

	public void resetGame()// cette methode s'occupe de l'initialisation du jeu
	{
		setLayout(new FlowLayout());

		status = Status.START; // debuter le jeu

		if (NavigationBar.musicIsOn) {
			musicOn = true;
		} else
			musicOn = false;
		id = Vue.chooseLevel.getId();// ----------------------------------------------------

		lines = singles = doubles = triples = tetrises = minoCount = 0;
		score = 0;////////////////////// lock'at dath
		//
		level = 1;
		//
		comboCount = 0;
		countDown = 3; // le compte decroissant (3...2...1)
		minoDelay = 0;
		minoDelaying = false;

		compteur = 0;
		hasHeld = ghosted = false;

		keys = new boolean[8];

		for (int i = 0; i < keys.length; i++) // l'initialisation des touches par des booleens false
		{
			keys[i] = false;
		}

		minos = new MinoQueue();
		stats = new StatsPanel();
		queue = new QueuePanel();
		matrix = new MatrixPanel();

		JPanel gameAndQueue = new JPanel(); // pour positionner le held,time,queue de pieces
		gameAndQueue.setLayout(new BorderLayout(0, 0));
		gameAndQueue.setOpaque(false);

		JPanel game = new JPanel();
		game.setLayout(new BorderLayout(4, 4));
		game.setBackground(null);
		game.setOpaque(false);
		game.setBorder(BorderFactory.createLoweredBevelBorder());
		game.add(matrix, BorderLayout.CENTER);// centrer la zone du jeu

		gameAndQueue.add(game, BorderLayout.CENTER);// centrer l'espace du jeu
		gameAndQueue.add(queue, BorderLayout.EAST);// positionner la queue des pieces
		gameAndQueue.add(stats, BorderLayout.WEST);// positionner le held et le time du jeu

		add(navigation);
		add(gameAndQueue);

		revalidate();
		repaint();
	}

	public void action() {
		if (status == Status.GAMEOVER)
			timer.stop();
		else if (status == Status.PLAYING) {
			compteur++;

			if (compteur % 2 == 0) {
				stats.clockPanel.update();

				if (compteur % 4 == 0) {
					stats.repaint();
				}
			}

			if (minoDelaying) {
				if (minoDelay < 375)
					minoDelay += FPS;
				else {
					minoDelay = 0;
					minoDelaying = false;
					matrix.minoToMatrixElement(currentMino);
					setNextMino();
				}
			}

			///////////////////////////////////////////////////////////////////////////////////
			if (Vue.chooseLevel.getId() == 0) {
				if (score <= 500) {
					if (compteur % 30 == 0)
						setDown();
				} else if (score <= 1000) {
					if (compteur % 25 == 0)
						setDown();
				} else if (compteur % 20 == 0)
					setDown();
			}

			else if (Vue.chooseLevel.getId() == 1) {
				if (score <= 500) {
					if (compteur % 21 == 0)
						setDown();
				} else if (score <= 1500) {
					if (compteur % 15 == 0)
						setDown();
				} else if (compteur % 10 == 0)
					setDown();
			}

			else if (Vue.chooseLevel.getId() == 2) {

				if (score <= 500) {
					if (compteur % 10 == 0)
						setDown();
				} else if (score <= 1200) {
					if (compteur % 4 == 0)
						setDown();
				} else if (compteur % 3 == 0)
					setDown();
			}
			///////////////////////////////////////////////////
		}

		else if (status == Status.START) {
		}

		else if (status == Status.PAUSED) {
			// timer.stop();
		}

		else if (status == Status.COUNTDOWN) {
			compteur++;

			if (compteur % 40 == 0) {
				if (countDown > 0) {

					countDownSound.play();
					countDown--;
					repaint();

					if (countDown == 1) {
						if (musicOn)
							tetrisTheme.loop();
						// tetrisTheme.play();
					}
				}

				else {
					countOverSound.play();

					status = Status.PLAYING;

					setNextMino();

					ghost = new Ghost(currentMino);
					getGhostCoors();
				}
			}
		}
	}

	public void setDown() {

		currentMino.move(new Point(0, 1));

		checkMoveAgainstMatrix(currentMino, new Point(0, 1));

		getGhostCoors();

		this.repaint();
	}

	public void start() {
		timer.start();
		navigation.playButton.setEnabled(true);
	}

	public void stop() {
		timer.stop();
		navigation.playButton.setEnabled(false);
	}

	public void restart() {
		stop();

		removeAll();
		resetGame();
		revalidate();
		repaint();

		timer.restart();
		navigation.playButton.setEnabled(true);
		status = Status.START;
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(backImage, 0, 0, this.getWidth(), this.getHeight(), null);
	}

	public void setBackImage(Image image) {
		backImage = image;
		repaint();
	}

	public Image getImg(String sh) {
		Image img;
		try {
			img = new ImageIcon(getClass().getResource(sh)).getImage();
		} catch (Exception e) {
			System.out.println("!! Ereur : image {\"" + sh + "\" :: " + e.getMessage());
			img = new ImageIcon().getImage();
		}
		return img;
	}

	public ImageIcon getImg(String sh, int width, int height) {
		Image img;
		img = getImg(sh).getScaledInstance(width, height, Image.SCALE_SMOOTH);
		return new ImageIcon(img);
	}

	public void getGhostCoors() {
		if (status != Status.PLAYING) {
			return;
		}

		ghost = new Ghost(currentMino);

		while (!checkGhostAgainstMatrix(ghost, new Point(0, 1)))// 1// la fantome n'est pas contre la matrice
		{
			ghost.move(new Point(0, 1));// 1
		}
	}

	public boolean checkGhostAgainstMatrix(Mino mino, Point shift) { // vérifier le fantôme contre la matrice(hors?)
		for (int i = 0; i < 4; i++) {
			if (mino.pCoors[i].y == 19) {
				return true;// true
			}

			for (int j = 0; j < matrix.elements.length; j++) {
				for (int k = 0; k < matrix.elements[j].length; k++) {
					if (matrix.elements[j][k] != null && mino.pCoors[i].x == j && mino.pCoors[i].y == k - 1) {
						return true;// true
					}
				}
			}
		}
		return false;// false
	}

	public boolean checkMinoAgainstMatrix(Mino mino) {
		for (int i = 0; i < 4; i++) {
			if (mino.pCoors[i].y >= 19) {
				return true;
			}

			for (int j = 0; j < matrix.elements.length; j++) {
				for (int k = 0; k < matrix.elements[j].length; k++) {
					if (matrix.elements[j][k] != null && mino.pCoors[i].x == j && mino.pCoors[i].y == k - 1) {
						return true;
					}
				}
			}
		}
		return false;
	}

	public boolean minoOutOfBounds(Mino mino) {
		/*
		 * cette fonction teste si la piece est entre les bordures de al matrice
		 */
		int count = 0;
		for (int i = 0; i < 4; i++) {
			if (mino.pCoors[i].x <= 9 && mino.pCoors[i].x >= 0) {
				count++;

				if (count == 4) {
					return false;
				}
			}
		}
		return true;
	}

	public void checkMinoAgainstBorders(Mino mino) {
		if (!minoOutOfBounds(mino))// la piece est entre les bordures
		{
			return; // ne fait rien
		}

		int x = 1;
		int y = 0;

		for (int i = 0; i < 4; i++) {
			if (mino.pCoors[i].x > 9)// hors bordure
			{
				x = -1; // ne pas laisser la piece depasse les bordures
			}
		}

		Point shift = new Point(x, y);

		while (minoOutOfBounds(mino)) {
			mino.move(shift); // ajouter (0,0) au pcoors[i].x,pcoors[j].y;(c-a-d on ne fait rien)
		}
	}

	public boolean bottomEmpty(Mino mino) {
		for (int i = 0; i < 4; i++) {
			if (mino.pCoors[i].y == 19)
				return false;// la bas n'est pas vide

			if (matrix.elements[mino.pCoors[i].x][mino.pCoors[i].y + 1] == null) {
				return true;
			}
		}
		return false;
	}

	public void checkMinoAgainstMatrixAndCorrect(Mino mino) {
		if (!checkMinoAgainstMatrix(mino)) {
			return;
		}

		Point shift = new Point(0, -1);

		// int count = 0;
		while (checkMinoAgainstMatrix(mino)) {
			mino.move(shift);
			/*
			 * count++; if (count >= 4) { for(int i=0; i<count; i++) { mino.move(new
			 * Point(0, shift.y*-1)); }
			 * 
			 * mino.reverseRotate();
			 * 
			 * if ((!bottomEmpty(mino) && !checkMinoAgainstMatrix(mino))) {
			 * matrix.minoToMatrixElement(currentMino); setNextMino(); } return; }
			 */
		}
	}

	public void checkMoveAgainstMatrix(Mino mino, Point shift) {
		for (int i = 0; i < 4; i++) {
			if (mino.pCoors[i].y > 19) {
				currentMino.move(new Point(shift.x * -1, shift.y * -1));
				if (shift.x == 0) {
					// matrix.minoToMatrixElement(mino);
					// setNextMino();
					minoDelaying = true;
				}
				return;
			}

			if (mino.pCoors[i].x > 9 || mino.pCoors[i].x < 0) {
				currentMino.move(new Point(shift.x * -1, shift.y * -1));
			}

			for (int j = 0; j < matrix.elements.length; j++) {
				for (int k = 0; k < matrix.elements[j].length; k++) {
					if (matrix.elements[j][k] != null && mino.pCoors[i].x == j && mino.pCoors[i].y == k) {
						currentMino.move(new Point(shift.x * -1, shift.y * -1));

						if (shift.x == 0) {
							// matrix.minoToMatrixElement(mino);
							// setNextMino();
							minoDelaying = true;
						}
						return;
					}
				}
			}
		}
	}

	public void setNextMino() {
		minoCount++;
		minoDelay = 0;
		minoDelaying = false;

		matrix.checkLineClears();

		if (status == Status.PLAYING) {
			currentMino = new Mino(minos.newMino(), true);
			minos.shiftUp();
			queue.adjust();
			hasHeld = false;
		}
	}

	public void gameOverMethod() {//////////////////////////////////////////////////////////////////////////////////////
		for (int j = 0; j < keys.length; j++) {
			keys[j] = false;
		}

		status = Status.GAMEOVER;
		gameOverSound.play();
		tetrisTheme.stop();
		navigation.playLabel.removeAll();
		navigation.playLabel.setText("Play Again");
		navigation.playLabel.setIcon(getImg("img/Icons/refresh1.png", 32, 32));

		gameStats = new GameOver(stats.clockPanel.minutes, stats.clockPanel.seconds, stats.clockPanel.hundredths, score,
				lines, singles, doubles, triples, tetrises, minoCount);

		highScore = new HighScore();
		// highScore.highScoreManager = new HighScoreManager(highScore.filee);
		highScore.getCancelButton().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {

				removeAll();
				revalidate();
				setLayout(new BorderLayout(0, 0));
				add(navigation, BorderLayout.NORTH);// north
				add(gameStats);
				revalidate();
				repaint();
			}
		});

		highScore.getBackButton().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (highScore.getId() <= 0) {
					removeAll();
					revalidate();
					setLayout(new BorderLayout(0, 0));
					add(navigation, BorderLayout.NORTH);// north
					add(gameStats);
					revalidate();
					repaint();
				}
			}
		});

		if (highScore.testHighScore(score)) {
			highScore.signUp();
			removeAll();
			revalidate();
			setLayout(new BorderLayout(0, 0));
			add(highScore);
			revalidate();
			repaint();
		} else {
			removeAll();
			revalidate();
			setLayout(new BorderLayout(0, 0));
			add(navigation, BorderLayout.NORTH);// north
			add(gameStats);
			revalidate();
			repaint();
		}
	}

	public void keyUpdate() {

		if (keys[0]) {
			minoDelay = 0;
			minoDelaying = false;
			currentMino.rotate(1);

			while ((checkMinoAgainstMatrix(currentMino)) || (minoOutOfBounds(currentMino))) {
				checkMinoAgainstMatrixAndCorrect(currentMino);

				checkMinoAgainstBorders(currentMino);
			}
			keys[0] = false;
			rotateSound.play();
		}

		if (keys[1]) {
			currentMino.move(new Point(1, 0));
			checkMoveAgainstMatrix(currentMino, new Point(1, 0));
			minoDelaying = false;
			minoDelay = 0;
			keys[1] = false;
			moveSound.play();

		}

		if (keys[2]) {
			currentMino.move(new Point(0, 1));
			checkMoveAgainstMatrix(currentMino, new Point(0, 1));

		}

		if (keys[3]) {
			currentMino.move(new Point(-1, 0));
			checkMoveAgainstMatrix(currentMino, new Point(-1, 0));
			minoDelaying = false;
			minoDelay = 0;
			keys[3] = false;
			moveSound.play();
		}

		if (keys[4]) {
			currentMino.pCoors = ghost.pCoors;
			matrix.minoToMatrixElement(currentMino);
			setNextMino();

			keys[4] = false;
			hardDropSound.play();
		}

		if ((keys[5]) && !hasHeld) {
			if (minos.heldMino != null) {
				Mino tempC = new Mino(minos.heldMino, true);
				minos.heldMino = new Mino(currentMino, true);
				currentMino = new Mino(tempC, true);
			} else {
				Mino tempC = new Mino(currentMino, true);
				minos.heldMino = new Mino(tempC, true);

				currentMino = new Mino(minos.newMino(), true);

				minos.shiftUp();
				queue.adjust();
			}
			stats.hold.mino = minos.heldMino;
			hasHeld = true;
		}

		if (keys[6]) {
			navigation.playButton.doClick();
			navigation.repaint();
			repaint();
		}

		if (keys[7]) {
			currentMino.reverseRotate();

			while ((checkMinoAgainstMatrix(currentMino)) || (minoOutOfBounds(currentMino))) {
				checkMinoAgainstMatrixAndCorrect(currentMino);

				checkMinoAgainstBorders(currentMino);
			}
			keys[7] = false;
			minoDelaying = false;
			minoDelay = 0;
		}

	}

	public class KeyHandler extends KeyAdapter {
		@Override
		public void keyPressed(KeyEvent event) {
			if (status == Status.PLAYING) {
				if (event.getKeyCode() == KeyEvent.VK_UP) {
					keys[0] = true;
				}
				if (event.getKeyCode() == KeyEvent.VK_RIGHT) {
					keys[1] = true;
				}
				if (event.getKeyCode() == KeyEvent.VK_DOWN) {
					keys[2] = true;
				}
				if (event.getKeyCode() == KeyEvent.VK_LEFT) {
					keys[3] = true;
				}
				if (event.getKeyCode() == KeyEvent.VK_SPACE) {
					keys[4] = true;
				}
				if ((event.getKeyCode() == KeyEvent.VK_SHIFT) || (event.getKeyCode() == KeyEvent.VK_C)) {
					keys[5] = true;
				}
				if (event.getKeyCode() == KeyEvent.VK_Z) {
					keys[7] = true;
				}
			}
			if (event.getKeyCode() == KeyEvent.VK_P) {
				keys[6] = true;
			}
			keyUpdate();
			getGhostCoors();
			repaint();
		}

		@Override
		public void keyReleased(KeyEvent event) // les keys
		{
			if (status == Status.PLAYING) {
				if (event.getKeyCode() == KeyEvent.VK_UP) {
					keys[0] = false;
				}
				if (event.getKeyCode() == KeyEvent.VK_RIGHT) {
					keys[1] = false;
				}
				if (event.getKeyCode() == KeyEvent.VK_DOWN) {
					keys[2] = false;
				}
				if (event.getKeyCode() == KeyEvent.VK_LEFT) {
					keys[3] = false;
				}
				if (event.getKeyCode() == KeyEvent.VK_SPACE) {
					keys[4] = false;
				}
				if ((event.getKeyCode() == KeyEvent.VK_SHIFT) || (event.getKeyCode() == KeyEvent.VK_C)) {
					keys[5] = false;
				}
				if (event.getKeyCode() == KeyEvent.VK_Z) {
					keys[7] = false;
				}
			}
			if (event.getKeyCode() == KeyEvent.VK_P) {
				keys[6] = false;
			}
		}
	}

	// la classe de la zone

	class MatrixPanel extends JPanel {

		private static final long serialVersionUID = 1L;
		MatrixElement[][] elements; // creation de la zone du jeu

		public MatrixPanel() {
			setSize(270, 540);
			setOpaque(false);

			elements = new MatrixElement[10][20];

			for (int i = 0; i < 10; i++) {
				for (int j = 0; j < 20; j++) {
					elements[i][j] = null; // les carreaux de la zone sont vides au depart
				}
			}
		}

		public void minoToMatrixElement(Mino mino) {
			for (int i = 0; i < 4; i++) {
				if (mino.pCoors[i].y < 0) {
					gameOverMethod();// test du game over
					return;
				}
				elements[mino.pCoors[i].x][mino.pCoors[i].y] = new MatrixElement(mino.color, mino.pCoors[i]);
			} // fixer les pieces lorsqu'elles atteintent la "terre" et generer des nouveaux
				// pieces
		}

		public void checkLineClears() {
			for (int i = 0; i < 20; i++) {
				int count = 0;
				for (int j = 0; j < 10; j++) {
					if (elements[j][i] != null) {
						count++;
					}
					if (count == elements.length) {
						// System.out.println("Line clear at:" + i);
						removeRow(i);
						lines++;
						comboCount++;
						stats.linePanel.label.setText("" + lines);
						stats.levelPanel.label.setText("" + level);// mise a jour de stage
					}
				}
			}

			if (comboCount == 1) {
				singles++;
				score += 100;

			} else if (comboCount == 2) {
				doubles++;
				score += 300;
			} else if (comboCount == 3) {
				triples++;
				score += 500;
			} else if (comboCount == 4) {
				tetrises++;
				score += 700;
			}
			stats.scorePanel.label.setText("" + score);
			updateLevel();
			stats.levelPanel.label.setText("" + level);

			comboCount = 0;
		}

		public void updateLevel() {
			if (score < 500)
				level = 1;
			else if (score < 1000)
				level = 2;
			else if (score < 2000)
				level = 3;
			else if (score < 3100)
				level = 4;
			else if (score < 4500)
				level = 5;
			else if (score < 6000)
				level = 6;
			else if (score < 7500)
				level = 7;
			else if (score < 9000)
				level = 8;
			else if (score < 10000)
				level = 9;
			else if (score >= 10000)
				level = 10;
		}

		public void removeRow(int row)// cette methode s'occupe de la suppression
		// des lignes lorsqu'elles sont remplis
		{
			for (int i = 0; i < 10; i++) {
				elements[i][row] = null;
			}

			MatrixElement[][] temp = new MatrixElement[10][20];// creation de la zone de jeu temporaire

			for (int i = 0; i < 20; i++) {
				for (int j = 0; j < 10; j++) {
					if (elements[j][i] != null) {
						temp[j][i] = new MatrixElement(elements[j][i]);
					}
				}
			}

			for (int i = row; i >= 0; i--) {
				for (int j = 0; j < 10; j++) {
					elements[j][i] = null;

					if (i != 0) {
						if (temp[j][i - 1] != null) {
							elements[j][i] = new MatrixElement(temp[j][i - 1].color,
									new Point(temp[j][i - 1].coors.x, temp[j][i - 1].coors.y + 1));
						}
					}
				}
			}
		}

		@Override
		public void paintComponent(Graphics g) {
			super.paintComponent(g);
			drawBackground(g);

			if (status == Status.PLAYING || status == Status.PAUSED) {

				for (int i = 0; i < elements.length; i++) {
					for (int j = 0; j < elements[i].length; j++) {
						if (elements[i][j] != null)
							// elements[i][j].draw(g, Tetris.dim);
							elements[i][j].draw(g, dim);
					}
				}
				if (ghosted)
					ghost.draw(g); // dessiner la fantome de la piece
				// ---------------------------------------- Ghost ---------------
				currentMino.draw(g);// dessiner la piece courante
			}

			else if (status == Status.COUNTDOWN) {
				g.setColor(Color.white);
				g.setFont(new Font("Helvetica", Font.PLAIN, 36));// defimir le style de la police du compteur
																	// decroissant
				g.drawString("" + countDown, getWidth() / 2 - 9, getHeight() / 2 - 36);
			}
		}

		public void drawBackground(Graphics g) // dessiner les carreaux de la zone du jeu
		{

			for (int i = 0; i < elements.length; i++)// nombre de lignes
			{
				for (int j = 0; j < elements[i].length; j++)// nombre de colonnes
				{
					if ((i + j) % 2 == 0)
						g.setColor(new Color(40, 40, 40, 128));
					else
						g.setColor(new Color(50, 50, 50, 128));

					g.fillRoundRect(i * dim, j * dim, dim + 1, dim + 1, 4, 4);
				}
			}
		}

		@Override
		public Dimension getPreferredSize() {
			return new Dimension(220, 440);
		}
	}

	public void setTetrisTheme(String tetrisTheme) {
		try {
			this.tetrisTheme = Applet.newAudioClip(getClass().getResource(tetrisTheme));
		} catch (Exception e) {
			System.out.println("!! Ereur : Le Son {\"" + tetrisTheme + "\"} not found :: " + e.getMessage());
		}
	}

	@Override
	public void componentResized(ComponentEvent e) {
		setPreferredSize(new Dimension(getWidth(), getHeight()));
		setSize(getPreferredSize());
		revalidate();
		repaint();
	}

	@Override
	public void componentHidden(ComponentEvent e) {
		revalidate();
		repaint();
		timer.stop();
	}

	@Override
	public void componentShown(ComponentEvent e) {
		revalidate();
		repaint();
		timer.start();
	}

	@Override
	public void componentMoved(ComponentEvent arg0) {

	}
}