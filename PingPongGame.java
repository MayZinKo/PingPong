import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class PingPongGame 
{
	// Instance variables 
	JFrame frame;

	int frameWidth = 500;
	int frameHeight = 500;

	// ball starting position
	int ballPositionX = 70;
	int ballPositionY = 70;
	int ballSpeedX = 1;
	int ballSpeedY = 1;
	int ballWidth = 40;
	int ballHeight = 40;

	int paddleLeftX = 50;
	int paddleLeftY = 250;
	int paddleLeftWidth = 10;
	int paddleLeftHeight = 200;

	int paddleSpeed = 100;

	int paddleRightX = 400;
	int paddleRightY = 250;
	int paddleRightWidth = 10;
	int paddleRightHeight = 200;

	public static void main (String[] args)
	{
		PingPongGame gui = new PingPongGame();
		gui.go();
	}	
	public void go()
	{
		frame = new JFrame();
		
		KeyboardListener keyboardListener = new KeyboardListener();
		frame.addKeyListener(keyboardListener);
		
		MyDrawPanel myDrawPanel = new MyDrawPanel();
			
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().add(BorderLayout.CENTER, myDrawPanel);
		frame.setSize(frameWidth, frameHeight);
		frame.setVisible(true);
		
		// Loop
		while (true)
		{
			ballPositionX = ballPositionX + ballSpeedX;
			ballPositionY = ballPositionY + ballSpeedY;
			
			myDrawPanel.repaint(); // calls paintComponent
			
			try {
				Thread.sleep(2);
			} catch (Exception ex) {}
		
		}
	}
	


	class MyDrawPanel extends JPanel // now inner class
	{
		private void resetGame()
		{
			PingPongGame gui = new PingPongGame();
			ballPositionX = gui.ballPositionX ;
			ballPositionY = gui.ballPositionY ;
			ballSpeedX = gui.ballSpeedX;
			ballSpeedY = gui.ballSpeedY;
		}
		public void paintComponent(Graphics g) //Override!
		{
			// set background to black
			g.fillRect(0, 0, this.getWidth(), this.getHeight());

			
			// hit the right paddle?
			if 	(
					((ballPositionX + ballWidth) >= paddleRightX) && // ball hits right paddle left side
					(ballPositionY > paddleRightY) &&
					((ballPositionY) < (paddleRightY + paddleRightHeight))
				)
			{
				ballSpeedX = -ballSpeedX;
			}



			// hit the right border?
			if (ballPositionX > (this.getWidth() - ballWidth))
			{
				resetGame();
			}
			
			

			// hit the left paddle?
			if 	(
					(ballPositionX < (paddleLeftX + paddleLeftWidth)) && // ball hits paddle right side
					(ballPositionY > paddleLeftY) &&
					(ballPositionY < (paddleLeftY + paddleLeftHeight))
				)
			{
				ballSpeedX = -ballSpeedX;
			}
			// hit the left border?
			
			if (ballPositionX < 0)
			{
				resetGame();
			}
			
			// hit the lower border?
			if (ballPositionY > (this.getHeight() - ballHeight))
			{
				//change ball move reflects
				ballSpeedY = -ballSpeedY;	
			}
			
			// hit the upper border?
			if (ballPositionY < 0)
			{
				//change ball move reflects
				ballSpeedY = -ballSpeedY;	
			}
			
			// display the ball
			g.setColor(Color.blue);
			g.fillOval(ballPositionX,ballPositionY,ballWidth,ballHeight);
			
			// display left paddle
			g.setColor(Color.orange);
			g.fillRect(paddleLeftX,paddleLeftY,paddleLeftWidth,paddleLeftHeight);

			// display right paddle
			g.setColor(Color.red);
			g.fillRect(paddleRightX,paddleRightY,paddleRightWidth,paddleRightHeight);
		}	
	} // end of draw panel inner class

	// inner class for Listener
	class KeyboardListener implements KeyListener
	{
		public void keyPressed(KeyEvent e) 
		{
			// code for moving paddle left
			// check if "Left" is pressed -> move paddle up
			if (e.getKeyCode() == KeyEvent.VK_LEFT)
			{
				// move paddle left up
				paddleLeftY = paddleLeftY - paddleSpeed;
			}
			// check if "Right" is pressed -> move paddle down
			if (e.getKeyCode() == KeyEvent.VK_RIGHT)
			{
				// move paddle left up
				paddleLeftY = paddleLeftY + paddleSpeed;
			}

			if (e.getKeyCode() == KeyEvent.VK_UP)
			{
				// move paddle left up
				paddleRightY = paddleRightY - paddleSpeed;
			}
			// check if "Right" is pressed -> move paddle down
			if (e.getKeyCode() == KeyEvent.VK_DOWN)
			{
				// move paddle left up
				paddleRightY = paddleRightY + paddleSpeed;
			}
		}
		public void keyReleased(KeyEvent e) {}
		public void keyTyped(KeyEvent e) {}
	}

} // end of class ping















