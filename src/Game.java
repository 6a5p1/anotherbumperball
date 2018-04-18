import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.image.BufferStrategy;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class Game extends Canvas {
	private static final long serialVersionUID = 1L;
	public static final int REZX = 800, REZY = 600;
	private BufferStrategy strategy;
	private Sprite back = null;
	private boolean gameRunning = false;
	Entitate ball=new BallE("image/ball.png",387,287);
	Entitate car1=new CarE("image/car1.png",50,300,1);
	Entitate car2=new CarE("image/car2.png",700,300,1);
	
	public Game() {
		JFrame container = new JFrame("Another Bumper Ball");
		JPanel panel = (JPanel) container.getContentPane();
		container.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		panel.setPreferredSize(new Dimension(REZX, REZY));
		panel.setLayout(null);
		setBounds(0, 0, REZX, REZY);
		panel.add(this);
		setIgnoreRepaint(true);
		container.pack();
		container.setLocationRelativeTo(null);
		container.setResizable(false);
		container.setVisible(true);
		addKeyListener(new KeyInputHandler());
		/*
		 * GraphicsEnvironment graphEnv = GraphicsEnvironment
		 * .getLocalGraphicsEnvironment(); GraphicsDevice graphDevice =
		 * graphEnv.getDefaultScreenDevice(); DisplayMode disMode = new
		 * DisplayMode(REZX, REZY, 32, DisplayMode.REFRESH_RATE_UNKNOWN);
		 * graphDevice.setFullScreenWindow(container);
		 * graphDevice.setDisplayMode(disMode);
		 */
		createBufferStrategy(2);
		strategy = getBufferStrategy();
		back = SpritesCollection.get().getTextura("image/back.jpg");
	}

	public void gameLoop() {

		long lastLoopTime = System.currentTimeMillis();
		long delta;
		Graphics2D g;

		while (gameRunning) {
			delta = System.currentTimeMillis() - lastLoopTime;
			lastLoopTime = System.currentTimeMillis();
			g = (Graphics2D) strategy.getDrawGraphics();
			back.draw(g, 0, 0);
			car1.draw(g);
			car2.draw(g);
			ball.move(delta);
			ball.draw(g);
			car1.move(delta);
			car1.setHorizontalMovement(0);
			car1.setVerticalMovement(0);
			/*for (int p = 0; p < entities.size() - 1; p++) {
				for (int s = p + 1; s < entities.size(); s++) {
					Entitate me = (Entitate) entities.get(p);
					Entitate him = (Entitate) entities.get(s);*/

					if (car1.collidesWith(ball)) {
						car1.collidedWith(ball);
						ball.collidedWith(car1);
					}
			
			g.dispose();
			strategy.show();
			try {
				Thread.sleep(15);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	private class KeyInputHandler extends KeyAdapter {

		public void keyPressed(KeyEvent e) {
			switch (e.getKeyCode()){
			case KeyEvent.VK_LEFT:
				car1.setHorizontalMovement(-300);
				break;
			case KeyEvent.VK_RIGHT:
				car1.setHorizontalMovement(300);
				break;
			case KeyEvent.VK_UP:
				car1.setVerticalMovement(-300);
				break;
			case KeyEvent.VK_DOWN:
				car1.setVerticalMovement(+300);
				break;
				
				
			}
		}
	}

	public static void main(String[] args) {
		Game g = new Game();
		g.gameRunning=true;
		g.gameLoop();

	}

}
