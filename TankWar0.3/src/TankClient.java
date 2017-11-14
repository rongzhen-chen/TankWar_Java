import java.awt.*;
import java.awt.event.*;

public class TankClient extends Frame {
	

	// constants: width and height of the window
	public static final int GAME_WIDTH = 800, GAME_HEIGHT = 600;
	

	int x = 100, y = 100;
	// note: I in Image is capital
	Image offScreenImage = null;
	
	public void lanuchFrame() {
		this.setLocation(300, 400);
		this.setSize(GAME_WIDTH, GAME_HEIGHT);
		// window can not be resized
		this.setResizable(false);
		this.setBackground(Color.GREEN);
		// add window listener for closing the frame
		this.addWindowListener(new CloseWinMonitor());
		this.setVisible(true);	
		// create a thread which repaints all the time
		new Thread( new PaintThread()).start();
		// add a keyListener to monitor the key action
		this.addKeyListener(new KeyMonitor());
	}
	
	// main code
	public static void main(String[] args) {
		TankClient tc = new TankClient();
		// Launch the frame/window
		tc.lanuchFrame();
	}
	
	
	public void paint(Graphics g) {
		Color c = g.getColor();
		g.setColor(Color.RED);
		// plot a circle as a simple tank
		g.fillOval(x, y, 30, 30);
		g.setColor(c);
	}
	
	
	// call update before paint (avoid the double buffer probelm)
	public void update(Graphics g) {
		if (offScreenImage == null) {
			offScreenImage = this.createImage(GAME_WIDTH, GAME_HEIGHT);
		}
		Graphics gOffScreenImage = offScreenImage.getGraphics();
		Color c = gOffScreenImage.getColor();
		gOffScreenImage.setColor(Color.GREEN);
		gOffScreenImage.fillRect(0, 0, GAME_WIDTH, GAME_HEIGHT);
		gOffScreenImage.setColor(c);
		paint(gOffScreenImage);
		g.drawImage(offScreenImage, 0, 0, null);
	}
	
	// thread which repaints
	private class PaintThread implements Runnable {

		public void run() {
			while(true) {
				repaint();
				try {
					Thread.sleep(50);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
		
	}
	
	class KeyMonitor extends KeyAdapter{
		public void keyPressed(KeyEvent k) {
			int keyCode = k.getKeyCode();
			
			switch(keyCode) {
				case KeyEvent.VK_RIGHT:
					x = x + 5;
					break;
				case KeyEvent.VK_LEFT:
					x = x - 5;
					break;
				case KeyEvent.VK_UP:
					y = y - 5;
					break;
				case KeyEvent.VK_DOWN:
					y = y + 5;	
					break;
			}
			
	
			}
		}
		
	
	
	// also can be replaced by an anonymous class
	class CloseWinMonitor extends WindowAdapter{
		public void windowClosing(WindowEvent e) {
			setVisible(false);
			System.exit(0);
		}

		
		
		
	}
	
	

}
