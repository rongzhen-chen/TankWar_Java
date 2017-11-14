import java.awt.*;
import java.awt.event.*;

public class TankClient extends Frame {
	
	public void lanuchFrame() {
		this.setLocation(300, 400);
		this.setSize(800, 600);
		// window can not be resized
		this.setResizable(false);
		this.setBackground(Color.GREEN);
		// add window listener for closing the frame
		this.addWindowListener(new CloseWinMonitor());
		this.setVisible(true);	
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
		g.fillOval(50, 50, 30, 30);
		g.setColor(c);
	}
	
	// also can be replaced by an anonymous class
	class CloseWinMonitor extends WindowAdapter{
		public void windowClosing(WindowEvent e) {
			setVisible(false);
			System.exit(0);
		}

		
		
		
	}
	
	

}
