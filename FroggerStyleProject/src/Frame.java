import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.concurrent.TimeUnit;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;
import java.util.concurrent.TimeUnit;
public class Frame extends JPanel implements ActionListener, MouseListener, KeyListener {
	public static boolean debugging = true;
	//Timer related variables
	int waveTimer = 5; //each wave of enemies is 20s
	long ellapseTime = 0;
	Font timeFont = new Font("Courier", Font.BOLD, 70);
	int level = 0;
	int score = 0;
	
	Font myFont = new Font("Courier", Font.BOLD, 40);
	SimpleAudioPlayer backgroundMusic = new SimpleAudioPlayer("scifi.wav", false);
//	Music soundBang = new Music("bang.wav", false);
//	Music soundHaha = new Music("haha.wav", false);
	
	TheGuy guy = new TheGuy(275,510);
	Background background = new Background(0,0);
	Water water = new Water(-300, 290);
	//MovingGuy objects
	
	GuyMoving[] row1 = new GuyMoving[3];
	GuyMovingLeft[] row2 = new GuyMovingLeft[3];
	LogLeft[] row3 = new LogLeft[3];
	LogRight[] row4 = new LogRight[3];
	LogLeft[] row5 = new LogLeft[3];
	LogRight[] row6 = new LogRight[3];
	
	//frame width/height
	int width = 600;
	int height = 600;	
	boolean colliding = false;

	public void paint(Graphics g) {
		super.paintComponent(g);
		
		
		
		background.paint(g);
		//have row1 objects appear on screen
		for(GuyMoving obj : row1) {
			obj.paint(g);
		}
		for(GuyMovingLeft obj : row2) {
			obj.paint(g);
		}
		for(LogLeft obj : row3) {
			obj.paint(g);
		}
		for(LogRight obj : row4) {
			obj.paint(g);
		}
		for(LogLeft obj : row5) {
			obj.paint(g);
		}
		for(LogRight obj : row6) {
			obj.paint(g);
		}
		guy.paint(g);
		water.paint(g);
		
		if(guy.getY() >= 360) {
			guy.vx = 0;
			colliding = false;
		}
		if(guy.getY() <= 210) {
			guy.vx = 0;
			colliding = false;
		}
		
	

		/*if(guy.getY() > 210 && guy.getY() < 360) {
			if(colliding){
				for(LogLeft obj: row3) {
					//invoke collided method for the main character
					if(obj.collided(guy)) {
						guy.vx = -5;
						break;
					}
				}
				for(LogRight obj: row4) {
					//invoke collided method for the main character
					if(obj.collided(guy)) {
						guy.vx = 5;
						break;
					}
				}
			}else if(!colliding) {
				guy.x = 275;
				guy.y = 510;
				score -= 3;
				System.out.println("SCORE IS NOW " + score );
			}
		}*/
		
		
		for(LogRight obj : row4) {
			//invoke collided method for the main character
				if(obj.collided(guy)) {
					guy.vx = 5;
					colliding = true;
				}
		}
		for(LogRight obj : row6) {
			//invoke collided method for the main character
				if(obj.collided(guy)) {
					guy.vx = 5;
					colliding = true;
				}
		}
		for(LogLeft obj : row3) {
			//invoke collided method for the main character
				if(obj.collided(guy)) {
					guy.vx = -5;
					colliding = true;
				}
		}
		for(LogLeft obj : row5) {
			//invoke collided method for the main character
				if(obj.collided(guy)) {
					guy.vx = -5;
					colliding = true;
				}
		}
		
		
		
		/*
		for(int i = 0; i < 3; i++) {
		
		
		
		
		for(LogRight obj : row4) {
		//invoke collided method for the main character
			if(obj.collided(guy)) {
				guy.vx = 5;
				colliding = true;
			}else if(!obj.collided(guy)) {
				colliding = false;
			}
		}
		for(LogRight obj : row6) {
			//invoke collided method for the main character
				if(obj.collided(guy)) {
					guy.vx = 5;
					colliding = true;
				}else if(!obj.collided(guy)) {
					colliding = false;
				}
			}
		for(LogLeft obj : row3) {
			//invoke collided method for the main character
				if(obj.collided(guy)) {
					guy.vx = -5;
					colliding = true;
				}else if(!obj.collided(guy)) {
					colliding = false;
				}
		}
		for(LogLeft obj : row5) {
			//invoke collided method for the main character
				if(obj.collided(guy)) {
					guy.vx = -5;
					colliding = true;
				}else if(!obj.collided(guy)) {
					colliding = false;
				}
		}
		
		
		if(water.collided(guy) && colliding == true) {
			System.out.println("that was close");
		}else if(water.collided(guy) && colliding == false) {
			guy.x = 275;
			guy.y = 510;
			score -= 3;
			System.out.println("SCORE IS NOW " + score );
			}
		}
			*/

		//keep broskie on screen
		if(guy.getX() < 25) {
			guy.x = 25;
		}else if(guy.getX() >= 575) {
			guy.x = 525;
		}else if(guy.getY() >= 560) {
			guy.y = 510;
		}else if(guy.getY() < 160) {
			guy.y = 160;
		}
		
		if(guy.getY() < 210) {
			score++;
			System.out.println("SCORE IS NOW " + score );
			guy.x = 275;
			guy.y = 510;
		}
		
		//collision detection
		for(GuyMoving obj: row1) {
			//invoke collided method for the main character
			if(obj.collided(guy)) {
				System.out.println("ouch");
				guy.x = 275;
				guy.y = 510;
				score -= 3;
				System.out.println("SCORE IS NOW " + score );
			}
			
		}
		
		for(GuyMovingLeft obj: row2) {
			//invoke collided method for the main character
			if(obj.collided(guy)) {
				System.out.println("ouch");
				guy.x = 275;
				guy.y = 510;
				score -= 3;
				System.out.println("SCORE IS NOW " + score );
			}
			
		}
		
		/**/
		
	}

	
	
	
	public static void main(String[] arg) {
		Frame f = new Frame();
		
	}
	
	public Frame() {
		JFrame f = new JFrame("Duck Hunt");
		f.setSize(new Dimension(width, height));
		f.setBackground(Color.white);
		f.add(this);
		f.setResizable(false);
 		f.addMouseListener(this);
		f.addKeyListener(this);
	
		//backgroundMusic.play();
		
		/*
		 * Set up 1D arrays here - create objects that go in them
		 * Traverse the Array
		 */
		
		for(int i = 0; i < row1.length; i++) {
			row1[i] = new GuyMoving(i*230,460);
			row2[i] = new GuyMovingLeft(i*230, 410);
			row3[i] = new LogLeft(i*230, 325);
			row4[i] = new LogRight(i*230, 275);
			row5[i] = new LogLeft(i*230+25, 325);
			row6[i] = new LogRight(i*230+25, 275);
		}
		
		//the cursor image must be outside of the src folder
		//you will need to import a couple of classes to make it fully 
		//functional! use eclipse quick-fixes
		setCursor(Toolkit.getDefaultToolkit().createCustomCursor(
				new ImageIcon("torch.png").getImage(),
				new Point(0,0),"custom cursor"));	
		
		
		Timer t = new Timer(16, this);
		t.start();
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setVisible(true);
	}
	
	
	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent m) {
		
	
		
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		repaint();
	}

	@Override
	public void keyPressed(KeyEvent arg0) {
		// TODO Auto-generated method stub
		if(arg0.getKeyCode()==87) {
			//w
			guy.move(0);
		}else if(arg0.getKeyCode()==83) {
			//s
			guy.move(1);
		}else if(arg0.getKeyCode()==65) {
			//a
			guy.move(2);
		}else if(arg0.getKeyCode()==68) {
			//d
			guy.move(3);
		}else if(arg0.getKeyCode()==101) {
			//d
			System.out.println(guy.getX());
			System.out.println(guy.getY());
		}
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

}
