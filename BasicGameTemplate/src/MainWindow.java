import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

import util.UnitTests;



public class MainWindow {
	 private static  JFrame frame = new JFrame("Billy's Quest Episode V: Willy Strikes Back");   // Change to the name of your game 
	 private static   Model gameworld= new Model();
	 private static   Viewer canvas = new  Viewer( gameworld);
	 private KeyListener Controller =new Controller()  ; 
	 private static   int TargetFPS = 60;
	 private static boolean startGame= false; 

	 private   JLabel BackgroundImageForStartMenu ;
	  
	public MainWindow() {
	        frame.setSize(1000, 1000);  // you can customise this later and adapt it to change on size.  
	      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);   //If exit // you can modify with your way of quitting , just is a template.
	        frame.setLayout(null);
	        frame.add(canvas);
	        canvas.setBounds(0, 0, 1000, 1000); 
			   canvas.setBackground(new Color(153,153, 153)); //white background  replaced by Space background but if you remove the background method this will draw a white screen 
		      canvas.setVisible(false);   // this will become visible after you press the key.       
	        JButton startMenuButton = new JButton("Start Game");  // start button 
	        startMenuButton.setOpaque(false);
	        startMenuButton.setContentAreaFilled(false);
	        startMenuButton.setBorderPainted(false);
	        startMenuButton.addActionListener(new ActionListener()
	           { 
				@Override
				public void actionPerformed(ActionEvent e) { 
					startMenuButton.setVisible(false);
					BackgroundImageForStartMenu.setVisible(false); 
					canvas.setVisible(true); 
					canvas.addKeyListener(Controller);    //adding the controller to the Canvas  
	            canvas.requestFocusInWindow();   // making sure that the Canvas is in focus so keyboard input will be taking in .
					startGame=true;
				}});  
	        startMenuButton.setBounds(400, 500, 200, 40); 
	        
	        //loading background image 
	        File BackroundToLoad = new File("res/startscreen.png");  //should work okay on OSX and Linux but check if you have issues depending your eclipse install or if your running this without an IDE 
			try {
				 
				 BufferedImage myPicture = ImageIO.read(BackroundToLoad);
				 BackgroundImageForStartMenu = new JLabel(new ImageIcon(myPicture));
				 BackgroundImageForStartMenu.setBounds(0, 0, 1000, 1000);
				frame.add(BackgroundImageForStartMenu); 
			}  catch (IOException e) { 
				e.printStackTrace();
			}   
			 
	         frame.add(startMenuButton);  
	       frame.setVisible(true);  

	}


	public static void main(String[] args) {
		System.out.println("Current working directory: " + System.getProperty("user.dir"));

		/////////////////////////////////////////////////////////////////
		// DO NOT DELETE THIS LINE DESPITE THE FACT THAT IT LOOKS LIKE IT DOES NOTHING
		// IT ACTUALLY INITIALIZES THE GAMEWORLD AND IS REQUIRED FOR THE WINDOW TO OPEN
		// LOL
		MainWindow hello = new MainWindow();
		/////////////////////////////////////////////////////////////////
		/// 
		while(true)   //not nice but remember we do just want to keep looping till the end.  // this could be replaced by a thread but again we want to keep things simple 
		{ 
			//swing has timer class to help us time this but I'm writing my own, you can of course use the timer, but I want to set FPS and display it 
			
			int TimeBetweenFrames =  1000 / TargetFPS;
			long FrameCheck = System.currentTimeMillis() + (long) TimeBetweenFrames; 
			
			//wait till next time step 
		 while (FrameCheck > System.currentTimeMillis()){} 
			
			
			if(startGame)
				 {
				 gameloop();
				 }
			
			//UNIT test to see if framerate matches 
		 UnitTests.CheckFrameRate(System.currentTimeMillis(),FrameCheck, TargetFPS); 
			  
		}
		
		
	} 
	//Basic Model-View-Controller pattern 
	private static void gameloop() { 
		// GAMELOOP  
		// model update   
		gameworld.gamelogic();
		// view update 
		
		 canvas.updateview(); 
		
		// Both these calls could be setup as  a thread but we want to simplify the game logic for you.  
		//score update  
		// frame.setTitle("Score =  "+ gameworld.getScore()); 
		  												
		frame.setTitle("[Player(X) = " + gameworld.getPlayer().getCentre().getX() + "] [Player(Y) = " + gameworld.getPlayer().getCentre().getY() +
				        "] [Collectibles: " + gameworld.getGems() + " ]"	);			//Player coordinates
		//lives update
		// frame.setTitle("Lives = " + gameworld.getLives());
		
		 
	}

}