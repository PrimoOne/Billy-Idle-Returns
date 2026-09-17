import java.awt.Graphics;
import java.awt.Image;
import java.awt.LayoutManager;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class Viewer extends JPanel {
	private long CurrentAnimationTime= 0; 
	Model gameworld; 
	 
	public Viewer(Model World) {
		this.gameworld=World;
	}

	public Viewer(LayoutManager layout) {
		super(layout);
	}

	public Viewer(boolean isDoubleBuffered) {
		super(isDoubleBuffered);
	}

	public Viewer(LayoutManager layout, boolean isDoubleBuffered) {
		super(layout, isDoubleBuffered);
	}

	public void updateview() {
		
		this.repaint();
		
	}
	
	
	public void paintComponent(Graphics g) {
		
		super.paintComponent(g);
		CurrentAnimationTime++; // runs animation time step 

		//Draw player Game Object 
		int x = (int) gameworld.getPlayer().getCentre().getX();
		int y = (int) gameworld.getPlayer().getCentre().getY();
		int width = (int) gameworld.getPlayer().getWidth();
		int height = (int) gameworld.getPlayer().getHeight();
		String texture = gameworld.getPlayer().getTexture();
		
		//Draw background 
		drawBackground(g);
		drawHeart(g);
		

	 
		//Draw Gems
		gameworld.getGem().forEach((temp) -> 
		{ 
			drawGems((int) temp.getCentre().getX(), (int) temp.getCentre().getY(), (int) temp.getWidth(), (int) temp.getHeight(), temp.getTexture(),g);	 
		});
		gameworld.getSign().forEach((temp) -> 
		{ 
			drawSign((int) temp.getCentre().getX(), (int) temp.getCentre().getY(), (int) temp.getWidth(), (int) temp.getHeight(), temp.getTexture(),g);	 
		});
	
		//Draw Enemies   
		gameworld.getEnemies().forEach((temp) -> 
		{
			drawEnemies((int) temp.getCentre().getX(), (int) temp.getCentre().getY(), (int) temp.getWidth(), (int) temp.getHeight(), temp.getTexture(),g);	  
	    });
		
		//Draw Tiles
		gameworld.getTiles().forEach((temp) -> 
		{
			drawTiles((int) temp.getCentre().getX(), (int) temp.getCentre().getY(), (int) temp.getWidth(), (int) temp.getHeight(), temp.getTexture(),g);	 
	    });
		
		//Draw Spikes
		gameworld.getSpikes().forEach((temp) -> 
		{
			drawSpikes((int) temp.getCentre().getX(), (int) temp.getCentre().getY(), (int) temp.getWidth(), (int) temp.getHeight(), temp.getTexture(),g);	 
	    });

		//Draw Player
		drawPlayer(x, y, width, height, texture,g);
		
		//Draw Projectile
		gameworld.getBullets().forEach((temp) -> 
		{ 
			drawBullet((int) temp.getCentre().getX(), (int) temp.getCentre().getY(), (int) temp.getWidth(), (int) temp.getHeight(), temp.getTexture(),g);	 
		});
		//Draw Melee Weapon		
		gameworld.getSword().forEach((temp) -> 
		{ 
			drawSword((int) temp.getCentre().getX(), (int) temp.getCentre().getY(), (int) temp.getWidth(), (int) temp.getHeight(), temp.getTexture(),g);	 
		});


		
	/////////////////////////	DRAW TILES ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	}
	
	
	private void drawTiles(int x, int y, int width, int height, String texture, Graphics g)	{

		File TextureToLoad = new File(texture);  //should work okay on OSX and Linux but check if you have issues depending your eclipse install or if your running this without an IDE 
		try {		
			Image myImage = ImageIO.read(TextureToLoad); 
			//
			 g.drawImage(myImage, x,y, x+width, y+height, 0 , 0, 16, 16, null);
			//}
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private void drawSpikes(int x, int y, int width, int height, String texture, Graphics g)	{

		File TextureToLoad = new File(texture);  //should work okay on OSX and Linux but check if you have issues depending your eclipse install or if your running this without an IDE 
		try {		
			Image myImage = ImageIO.read(TextureToLoad); 
			//
			 g.drawImage(myImage, x,y, x+width, y+height, 0 , 0, 16, 16, null);
			//}
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/////////////////////////	DRAW ENVIRONMENT OBJECTS /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	private void drawGems(int x, int y, int width, int height, String texture, Graphics g)	{

		File TextureToLoad = new File(texture);
		try {		
			Image myImage = ImageIO.read(TextureToLoad); 
			//
			 g.drawImage(myImage, x,y, x+width, y+height, 0 , 0, 16, 13, null);
			//}
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	private void drawSign(int x, int y, int width, int height, String texture,Graphics g)
	{
		File TextureToLoad = new File(texture);  
		try {
			Image myImage = ImageIO.read(TextureToLoad); 
			//32 by 8
			 g.drawImage(myImage, x,y, x+width, y+height, 0 , 0, 15, 17, null); 
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/////////////////////////	DRAW ENEMIES ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	
	private void drawEnemies(int x, int y, int width, int height, String texture, Graphics g) {
		File TextureToLoad = new File(texture);  
		try {
			Image myImage = ImageIO.read(TextureToLoad);
			//The spirte is 32x32 pixel wide and 4 of them are placed together so we need to grab a different one each time 
			//remember your training :-) computer science everything starts at 0 so 32 pixels gets us to 31  
			int currentPositionInAnimation= ((int) ((CurrentAnimationTime%8)/2))*16; //slows down animation so every 10 frames we get another frame so every 100ms 
			g.drawImage(myImage, x,y, x+width, y+height, currentPositionInAnimation  , 0, currentPositionInAnimation+15, 16, null); 
			
		} catch (IOException e) {
			e.printStackTrace();
		} 
		
	}
	
	
	/////////////////////////	DRAW HEARTS ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	private void drawHeart(Graphics g)
	{
		String heart = "res/heart3.png";
		
		if(gameworld.getLives() == 2) {
			heart = "res/heart2.png";
		}
		if(gameworld.getLives()==1) {
			heart="res/heart1.png";
		}
		if(gameworld.getLives()==0) {
			heart="res/heart0.png";
		}
		File TextureToLoad = new File(heart);
		try {
			Image myImage = ImageIO.read(TextureToLoad); 
			 g.drawImage(myImage, 0,0,300, 300, 0 , 0, 100, 100, null); 
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	/////////////////////////	DRAW BACKGROUND ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	private void drawBackground(Graphics g)
	{	

		File TextureToLoad = new File("res/wallbackground.png");  
		try {
			Image myImage = ImageIO.read(TextureToLoad); 
			 g.drawImage(myImage, 0,0,1000, 1000, 0 , 0, 1000, 900, null); 
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	
	/////////////////////////	DRAW PROJECTILE ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	private void drawBullet(int x, int y, int width, int height, String texture,Graphics g)
	{

		File TextureToLoad = new File(texture);  
		try {		
			Image myImage = ImageIO.read(TextureToLoad); 
			//64 by 128 
			 g.drawImage(myImage, x,y, x+width, y+height, 0 , 0, 31, 31, null);
			//}
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/////////////////////////	DRAW MELEE WEAPON ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	private void drawSword(int x, int y, int width, int height, String texture,Graphics g)
	{
		File TextureToLoad = new File(texture);  
		try {
			Image myImage = ImageIO.read(TextureToLoad); 
			//32 by 8
			 g.drawImage(myImage, x,y, x+width, y+height, 0 , 0, 31, 7, null); 
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
/////////////////////////////////////////////////////////////	ANIMATE PLAYER CHARACTER	////////////////////////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	
	
	private void drawPlayer(int x, int y, int width, int height, String texture,Graphics g) { 
		boolean lastpressedA=false;
		boolean lastpressedD=false;
		//BILLY IDLE
		String howtodrawbilly = "res/billy_idle.png";																// Variable for changing billy animation
		int sprite1=8, sprite2=2;																					//and variables for the animating process (sprite1/sprite2 = number of animated guys)
		
		//BILLY RUNNIG LEFT
		if(Controller.getInstance().isKeyAPressed()) {
			howtodrawbilly="res/billy_run_left.png";
			sprite1=6;
			sprite2=1;
			lastpressedA=true;
			lastpressedD=false;
		}
		//BILLY RUNNING RIGHT
		if(Controller.getInstance().isKeyDPressed()) {
			howtodrawbilly="res/billy_run_right.png";
			sprite1=6;
			sprite2=1;
			lastpressedA=false;
			lastpressedD=true;
			
		}
		//BILLY JUMPING DIAGONAL RIGHT
		if(Controller.getInstance().isKeySpacePressed() && Controller.getInstance().isKeyDPressed()) {
			howtodrawbilly="res/billy_jump_right.png";
			sprite1=3;
			sprite2=1;
		
		}
		//BILLY JUMPING DIAGONAL LEFT
		if(Controller.getInstance().isKeySpacePressed() && Controller.getInstance().isKeyAPressed()) {
			howtodrawbilly="res/billy_jump_left.png";
			sprite1=3;
			sprite2=1;
		
		}
		if((Controller.getInstance().isKeyAPressed() == false 
				&& Controller.getInstance().isKeyDPressed() ==false )
					&& (lastpressedA==true)) {
				howtodrawbilly="res/billy_idle_left.png";
				System.out.println("reaching");
			}

		
		//DRAW BILLY
		File TextureToLoad = new File(howtodrawbilly);  
		try {
			Image myImage = ImageIO.read(TextureToLoad);
			//The spirte is 32x32 pixel wide and 4 of them are placed together so we need to grab a different one each time 
			//remember your training :-) computer science everything starts at 0 so 32 pixels gets us to 31  
			int currentPositionInAnimation= ((int) ((CurrentAnimationTime%sprite1)/sprite2))*16; //slows down animation so every 10 frames we get another frame so every 100ms 
			g.drawImage(myImage, x,y, x+width, y+height, currentPositionInAnimation  , 0, currentPositionInAnimation+15, 16, null); 
			
		} catch (IOException e) {
			e.printStackTrace();
		} 
		
	}
		 

}
