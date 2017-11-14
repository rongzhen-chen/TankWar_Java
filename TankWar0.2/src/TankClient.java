import java.awt.*;
import java.awt.event.*;

public class TankClient extends Frame {
	




	int x = 50;
	// note: I in Image is capital
	Image offScreenImage = null;
	
	public void lanuchFrame() {
		this.setLocation(300, 400);
		this.setSize(800, 600);
		this.setResizable(false);
		this.setBackground(Color.GREEN);
		// add window listener for closing the frame
		this.addWindowListener(new CloseWinMonitor());
		this.setVisible(true);	
		// create a thread which repaints all the time
		new Thread( new PaintThread()).start();
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
		g.fillOval(x, 50, 30, 30);
		g.setColor(c);
		x = x + 5;
	}
	
	
	// call update before paint (avoid the double buffer problem)
	public void update(Graphics g) {
		if (offScreenImage == null) {
			offScreenImage = this.createImage(800, 600);
		}
		Graphics gOffScreenImage = offScreenImage.getGraphics();
		Color c = gOffScreenImage.getColor();
		gOffScreenImage.setColor(Color.GREEN);
		gOffScreenImage.fillRect(0, 0, 800, 600);
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
	
	
	// also can be replaced by an anonymous class
	class CloseWinMonitor extends WindowAdapter{
		public void windowClosing(WindowEvent e) {
			setVisible(false);
			System.exit(0);
		}

		
		
		
	}
	
	

}
