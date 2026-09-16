import java.io.File;
import java.util.concurrent.CopyOnWriteArrayList;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.*;
import util.GameObject;
import util.Point3f;
import util.Vector3f; 


public class Model {
	
	 private  GameObject Player;														///Bunch of variables for use throughout
	 private CopyOnWriteArrayList<GameObject> EnemiesList  = new CopyOnWriteArrayList<GameObject>();
	 private CopyOnWriteArrayList<GameObject> BulletList  = new CopyOnWriteArrayList<GameObject>();
	 private CopyOnWriteArrayList<GameObject> SwordList  = new CopyOnWriteArrayList<GameObject>();
	 private CopyOnWriteArrayList<GameObject> TilesList = new CopyOnWriteArrayList<GameObject>();
	 private CopyOnWriteArrayList<GameObject> SpikeList = new CopyOnWriteArrayList<GameObject>();
	 private CopyOnWriteArrayList<GameObject> SignList = new CopyOnWriteArrayList<GameObject>();
	 private CopyOnWriteArrayList<GameObject> GemList = new CopyOnWriteArrayList<GameObject>();
	
	  
	 public int Lives=3;
	 public int gemcount=0;
	 public int score=0;
	 public long beforejump;
	 public long afterjump;

	 public String playermodel;
	 public boolean enemygoleft=false;
	 public boolean bulletgoleft=false;
	 public boolean encounter = false;
	 public boolean clearlevel=true;
	 
	 public boolean enemygrounded=false;
	 public boolean grounded=false;

	 boolean spawntutorialguy=true;
	 boolean spawnguy=true;
	 boolean spawnguy2=true;
	 boolean spawnguy3=true;
	 public int evilbilly=10;
	 public boolean evilbillyexists=false;
	 
	 public boolean playingtutorial = true;
	 public boolean tutorialcomplete = false;
	 
	 public boolean buildlevelone = false;
	 public boolean playinglevel1 = false;
	 public boolean level1complete = false;
	 
	 public boolean buildleveltwo = false;
	 public boolean playinglevel2 = false;
	 public boolean level2complete = false;
	 
	 public boolean buildlevelthree = false;
	 public boolean playinglevel3 = false;
	 public boolean level3complete = false;
	 
	 public boolean buildlevelfour = false;
	 public boolean playinglevel4 = false;
	 public boolean level4complete = false;

	 public boolean GameOver = false;
	 
	 public String goblinmodel = "res/goblino_run_left.png";													//Default goblin direction
	 File background = new File("res/background2.wav");
	 File enemydead = new File("res/shotlanded.wav");
	 File shoot = new File("res/shoot.wav");
	 File jump = new File("res/jump.wav");
	 File treasure = new File("res/gem.wav");
	 File win = new File("res/achievement.wav");
	 File lose = new File("res/lose.wav");
	 File billyhit = new File("res/billyhit2.wav");
	 
	 
	 
	 
	public Model() {
		

		//Player 
		Player= new GameObject(playermodel,50,50,new Point3f(10,850,0));										//Create Player

		
		
		PlaySound(background, -22.0f, 10000000);
		BuildTutorial();
		

		


			//##################################################################################################################################################################
			//##################################################################################################################################################################
			//##################################################################################################################################################################
			//##################################################################################################################################################################
			
		
	}
	
	// This is the heart of the game , where the model takes in all the inputs ,decides the outcomes and then changes the model accordingly. 
	public void gamelogic() 
	{
		// Player Logic first 
		playerLogic(); 
		// Enemy Logic next
		enemyLogic();
		// Bullets move next 
		bulletLogic();
		// interactions between objects 
		swordLogic();
		tileLogic();
		spikeLogic();
		gameLogic();
		
		//##################################################################################################################################################################
		//##################################################################################################################################################################
		//################################################################   		  TRIGGERS		 	####################################################################
		//##################################################################################################################################################################
		//##################################################################################################################################################################
		
		//IF PLAYER COLLECTS BOTH GEMS AND THE DAGGER SPAWN THE TUTORIAL GOBLIN
		if(gemcount > 2 && playingtutorial==true && spawntutorialguy==true) {
			EnemiesList.add(new GameObject(goblinmodel,45,45,new Point3f(950,350,0)));
			spawntutorialguy=false;
		}
		
		if(grounded==true) {
			//System.out.println("grounded");
		}
		if(grounded==false) {
			//System.out.println("NOT grounded");
		}
		
		//IF PLAYER COMPLETES TUTORIAL
		if(Player.getCentre().getX() >= 890.0 && Player.getCentre().getY() <= 360.0 								//conditional statement for completing the tutorial
			&& playingtutorial==true && gemcount > 2) {		
			
				for (GameObject tile : TilesList){																		//wipe all the bricks
					TilesList.remove(tile);
				}TilesList.clear();
				
				for(GameObject sign : SignList){																		//remove the exit sign
					SignList.remove(sign);
				}SignList.clear();	
				
				for(GameObject spike : SpikeList){																		//wipe the spikes
					SpikeList.remove(spike);
				}SpikeList.clear();	
				
				PlaySound(win, -10.0f, 10000);
				playingtutorial=false;																					//no longer playing tutorial, so don't check this conditional statement anymore
				buildlevelone=true;																						//approve the building of the first level
				Player.setCentre(new Point3f(10,850,0));																//and teleport my boy billy into his new starting location
		}
		

		
		BuildLevelOne();																							//build the first level

		//IF PLAYER COMPLETES LEVEL ONE
		if(Player.getCentre().getX() >= 884 && Player.getCentre().getY() <=127
			&& playinglevel1==true && gemcount > 5 && playinglevel2==false && playinglevel3==false) {
				PlaySound(win, -10.0f, 10000);
				for (GameObject tile : TilesList){																		//wipe all the bricks
					TilesList.remove(tile);
				}TilesList.clear();
				
				for(GameObject sign : SignList){																		//remove the exit sign
					SignList.remove(sign);
				}SignList.clear();
				
				for(GameObject spike : SpikeList){																		//wipe the spikes
					SpikeList.remove(spike);
				}SpikeList.clear();	
				
				Player.setCentre(new Point3f(10,850,0));															//teleport billy
				playinglevel1=false;																				//stop the clearing process
				buildleveltwo=true;																					//approve building of level two
		}
		

		BuildLevelTwo();																							//build level two
		
		//SPAWN JUMPSCARE GOBLINS
		if(playinglevel2==true && Player.getCentre().getX()>=540 && Player.getCentre().getY()<=694&&spawnguy==true) {
			EnemiesList.add(new GameObject(goblinmodel,45,45,new Point3f(910,550,0)));
			EnemiesList.add(new GameObject(goblinmodel,45,45,new Point3f(910,110,0)));
			spawnguy=false;
		}
		//TELEPORTING DOOR LEVEL 2
		if(playinglevel2==true && Player.getCentre().getX() <=2 && Player.getCentre().getY() <= 432 && Player.getCentre().getY() > 300 && gemcount > 7) {	 //2 Y conditions so that it doesnt trigger on top
			Player.setCentre(new Point3f(48,135,0));
		}
		
		//IF PLAYER COMPLETES LEVEL TWO
		if(Player.getCentre().getX() >= 890 && Player.getCentre().getY() <= 140
			&& playinglevel2==true && gemcount > 7) {
				
			for (GameObject tile : TilesList){																		//wipe all the bricks
					TilesList.remove(tile);
				}TilesList.clear();
				
				for(GameObject sign : SignList){																		//remove the exit sign
					SignList.remove(sign);
				}SignList.clear();
				
				for(GameObject spike : SpikeList){																		//wipe the spikes
					SpikeList.remove(spike);
				}SpikeList.clear();	
				
				Player.setCentre(new Point3f(10, 110, 0));
				playinglevel2=false;
				buildlevelthree=true;	
		}
		
		BuildLevelThree();
		
		//TWO GOBLIN SPAWN TRIGGERS
		if(playinglevel3==true && gemcount > 8 && spawnguy2==true) {
			EnemiesList.add(new GameObject(goblinmodel,45,45,new Point3f(910,140,0)));
			EnemiesList.add(new GameObject(goblinmodel,45,45,new Point3f(910,550,0)));
			spawnguy2=false;
		}
		if(playinglevel3==true && spawnguy3==true && Player.getCentre().getX() >= 590 && Player.getCentre().getY() >= 750 ) {
			EnemiesList.add(new GameObject(goblinmodel,45,45,new Point3f(910,770,0)));
			spawnguy3=false;
		}
		//TELEPORTING DOOR LEVEL 3
		if(playinglevel3==true && Player.getCentre().getX() >=868 && Player.getCentre().getY() >= 540 && Player.getCentre().getY() < 560) {	 //2 Y conditions so that it doesnt trigger on top
			Player.setCentre(new Point3f(10, 110, 0));
		}
		
		
		//IF PLAYER COMPLETES LEVEL THREE
		if(Player.getCentre().getX() >= 852 && Player.getCentre().getY() >= 750
			&& playinglevel3==true && gemcount > 13) {
				
			for (GameObject tile : TilesList){																		//wipe all the bricks
					TilesList.remove(tile);
				}TilesList.clear();
				
				for(GameObject sign : SignList){																		//remove the exit sign
					SignList.remove(sign);
				}SignList.clear();
				
				for(GameObject spike : SpikeList){																		//wipe the spikes
					SpikeList.remove(spike);
				}SpikeList.clear();	
				
				Player.setCentre(new Point3f(10, 850, 0));
				playinglevel3=false;
				buildlevelfour=true;	
		}
		
		BuildLevelFour();
		//IF PLAYER COMPLETES LEVEL FOUR
		if(Player.getCentre().getX() >= 850 && Player.getCentre().getY()>=850
				&& playinglevel4 == true && evilbillyexists==false) {
			level4complete=true;
		}
		
		
		
		// THE GOOD ENDING WHERE YOU COMPLETE THE GAME
		if(level4complete==true) {
			ClearLevel();
			for(int a=0; a<10; a++) {
			System.out.println("You Saved The Princess From Billy's Nemesis, Willy." + "\nCongratulations!\n");
			}
			System.exit(0);
		}
		
		//THE BAD ENDING WHEN YOU DIE
		if(GameOver==true){
			ClearLevel();
			for(int a=0; a<10; a++) {
			System.out.println("Game Over. \nBetter Luck Next Time.\n");
			}
			System.exit(0);
				//Player.setCentre(new Point3f(1100,1100,0));	
		}
		
	}
	
	
	static void PlaySound(File Sound, float volume, int pause)										//Found this guide on YouTube
	{																								// https://www.youtube.com/watch?v=QVrxiJyLTqU&ab_channel=JavaTutorials101
		try {
			Clip clip = AudioSystem.getClip();
			clip.open(AudioSystem.getAudioInputStream(Sound));
			FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
			gainControl.setValue(volume);
			clip.start();
			Thread.sleep(clip.getMicrosecondLength()/pause);
		}catch(Exception e) {}
	}

	//##################################################################################################################################################################
	//##################################################################################################################################################################
	//################################################################   		  LOGIC		 	########################################################################
	//##################################################################################################################################################################
	//##################################################################################################################################################################
	
	
	private void gameLogic() { 																					
		
		for( GameObject gem : GemList) {
																													//Collision detection between gem and player
			if ( Math.abs(gem.getCentre().getX()+8 - Player.getCentre().getX())< gem.getWidth() 				//Collision detection between projectile and enemy
				&& Math.abs(gem.getCentre().getY()- Player.getCentre().getY()) < gem.getHeight()){
					GemList.remove(gem);
					PlaySound(treasure, -10.0f, 10000);
					gemcount++;
			}
		}
			
		if(Lives < 0) {
			GameOver=true;
		}
		
		for (GameObject enemy : EnemiesList) 
		{
		for (GameObject Bullet : BulletList) 
		{	
			if ( Math.abs(enemy.getCentre().getX()- Bullet.getCentre().getX())< enemy.getWidth() 								//Collision detection between projectile and enemy for everything except last boss
				&& Math.abs(enemy.getCentre().getY()- Bullet.getCentre().getY()) < enemy.getHeight()&&evilbillyexists==false)
			{
				EnemiesList.remove(enemy);
				PlaySound(enemydead, -20.0f, 10000);
				score+=1;
				//BulletList.remove(Bullet);
			}
			
			if(Math.abs(enemy.getCentre().getX()+50- Bullet.getCentre().getX())< enemy.getWidth() 
					&& Math.abs(enemy.getCentre().getY()- Bullet.getCentre().getY()) < enemy.getHeight()&&evilbillyexists==true) {
				
				evilbilly--;
				BulletList.remove(Bullet);
				PlaySound(enemydead, -20.0f, 10000);
			}
			if(evilbilly==0) {
				EnemiesList.remove(enemy);
				evilbillyexists=false;
				SignList.add(new GameObject("res/win.png", 200, 200, new Point3f(375, 450, 0)));
			}
		}
		for (GameObject Sword : SwordList) 
		{
			if ( Math.abs(enemy.getCentre().getX()- Sword.getCentre().getX())< enemy.getWidth() 				//Collision detection between melee weapon and enemy
				&& Math.abs(enemy.getCentre().getY()- Sword.getCentre().getY()) < enemy.getHeight()&&evilbillyexists==false)
			{
				EnemiesList.remove(enemy);
				PlaySound(enemydead, -20.0f, 10000);
				score+=1;
				BulletList.remove(Sword);
			}
			if(Math.abs(enemy.getCentre().getX()- Sword.getCentre().getX())< enemy.getWidth() 
					&& Math.abs(enemy.getCentre().getY()- Sword.getCentre().getY()) < enemy.getHeight()&&evilbillyexists==true) {
				BulletList.remove(Sword);
				evilbilly--;
				PlaySound(enemydead, -20.0f, 10000);
			}
			if(evilbilly==0) {
				EnemiesList.remove(enemy);
				evilbillyexists=false;
			}
		}
		}
		
		
		for( GameObject enemy : EnemiesList) {
			if(encounter == false) {																				//Collision detection between enemy and player
				if(Math.abs(enemy.getCentre().getX()- Player.getCentre().getX())< enemy.getWidth() 
						&& Math.abs(enemy.getCentre().getY()- Player.getCentre().getY()) < enemy.getHeight()&&evilbillyexists==false) {
					Lives--;
					
					EnemiesList.remove(enemy);
					PlaySound(enemydead, -20.0f, 10000);
					encounter=true;
					Player.getCentre().ApplyVector( new Vector3f(-8,10,0));											//push the player to the left if hit by goblin
					encounter=false;
				}
				if(Math.abs(enemy.getCentre().getX()- Player.getCentre().getX())< enemy.getWidth() 
						&& Math.abs(enemy.getCentre().getY()- Player.getCentre().getY()) < enemy.getHeight()&&evilbillyexists==true) {
					Lives--;
					PlaySound(enemydead, -20.0f, 10000);
					encounter=true;
					Player.getCentre().ApplyVector( new Vector3f(-8,10,0));											//push the player to the left if hit by goblin
					encounter=false;
				}
			}
		}
	}

	private void enemyLogic() {
		// TODO Auto-generated method stub
		
		for (GameObject enemy : EnemiesList) 
		{																										// Enemy Pathing Logic
			if(enemy.getCentre().getX() < 10) {																 	// if Gobbo hits left side of the screen, turn off going left
				enemygoleft=false;	
			}
			if(enemy.getCentre().getX() > 880) {																//if Gobbo hits right side of the screen, turn on going left
				enemygoleft=true;
			}
			if(enemygoleft == true && enemygrounded == true) {
				enemy.getCentre().ApplyVector(new Vector3f(-2,0,0));											//Apply going left condition into left/right movement
			}
			if(enemygoleft == false && enemygrounded == true) {
				enemy.getCentre().ApplyVector(new Vector3f(2,0,0));
				goblinmodel = "res/goblino_run_right.png";
			}
			enemy.getCentre().ApplyVector(new Vector3f(0,-4,0));												//Apply gravity to the Gobbos
			
			if(enemy.getCentre().getY() > 890) {																//Enemies are also vulnerable to the off-screen death
				EnemiesList.remove(enemy);
				}
			
		}	
		/*
		if (EnemiesList.size()<1)																				//Adjust this to change how many gobbos you want on screen
		{
			while (EnemiesList.size()<7 && (Player.getCentre().getX() < 550 && Player.getCentre().getY() > 150) && tutorialcomplete == true)
			{
				EnemiesList.add(new GameObject(goblinmodel,45,45,new Point3f(900,10,0))); 						//Point3f(((float)Math.random()*1000),1000,0)));
			}
		}*/
	}

	private void bulletLogic() {
  
		for (GameObject bullet : BulletList) 
		{													
			bullet.getCentre().ApplyVector(new Vector3f(10,0,0));							
			
			if (bullet.getCentre().getX()==900 || bullet.getCentre().getX()==0)										//make bullet disappear if it hits the edge of the screen
			{
			 	BulletList.remove(bullet);
			}
		} 
	}
	

	
	private void swordLogic() {
		
		for(GameObject sword: SwordList) {
			sword.getCentre().ApplyVector(new Vector3f(-9,0,0));																//works same as bullet
			
			if(Player.getCentre().getX() - sword.getCentre().getX()  > 100 || sword.getCentre().getX()==0) {					// but it disappears if it gets too far away from the player
				SwordList.remove(sword);
			}
		}
	}

	private void tileLogic() {
		for (GameObject tile: TilesList) {
					
					//COLLISION: huge thanks to Issac for helping me fix my collision
			
				
					//Collision above the tile -> if Billy is standing on top of it, keep him on it
					if(     (tile.getCentre().getY() - Player.getCentre().getY() >0 && 															//if Billy is ABOVE the brick AND close to it
							(tile.getCentre().getY()-2 - Player.getCentre().getY()) < Player.getHeight())										// 			AND
							&&(( (Player.getCentre().getX()-tile.getCentre().getX() 	) < tile.getWidth()	)									// player is not too far right or left
							&& tile.getCentre().getX() - Player.getCentre().getX() < Player.getWidth()))										
					{				
								Player.getCentre().ApplyVector(new Vector3f(0,4,0));															// then push billy up a little (against gravity)						
					}
					
					//Collision under the tile -> if Billy bumps his head into it while jumping, push him down
					if(     (Player.getCentre().getY() - tile.getCentre().getY() > 0 &&															//if the brick is above billy AND close to it
							(Player.getCentre().getY() - tile.getCentre().getY()-1) < tile.getHeight())											// 		AND
							&&(( (Player.getCentre().getX()-tile.getCentre().getX() 	) < tile.getWidth()	) 									// player is not too far right or left
							&& tile.getCentre().getX() - Player.getCentre().getX() < Player.getWidth()))	{ 										
						Player.getCentre().ApplyVector(new Vector3f(0,-5,0));																	// then push billy down, as if he hit his head on the ceiling
					}
					
					/*if(		(tile.getCentre().getY() - Player.getCentre().getY() > 0 ) &&
							((tile.getCentre().getY() - Player.getCentre().getY()) < Player.getHeight()) ){
							//grounded=true;	
							System.out.println("qiwjheoqwe" + Player.getHeight());
					}*/
					
					
					
				//APPLY SAME TILE LOGIC TO ENEMIES  
					for(GameObject enemy: EnemiesList) {
						//Collision above the tile -> if goblin is standing on top of it, keep him on it
						if(     (tile.getCentre().getY() - enemy.getCentre().getY() >0 && 
								(tile.getCentre().getY()-2 - enemy.getCentre().getY()) < enemy.getHeight())
								&&(( (enemy.getCentre().getX()-tile.getCentre().getX() 	) < tile.getWidth()	)
								&& tile.getCentre().getX() - enemy.getCentre().getX() < enemy.getWidth()))	
						{				
							enemy.getCentre().ApplyVector(new Vector3f(0,4,0));
							enemygrounded = true;
						}
						
						if(     (enemy.getCentre().getY() - tile.getCentre().getY() > 0 &&
								(enemy.getCentre().getY() - tile.getCentre().getY()-1) < tile.getHeight())
								&&(( (enemy.getCentre().getX()-tile.getCentre().getX() 	) < tile.getWidth()	) 
								&& tile.getCentre().getX() - enemy.getCentre().getX() < enemy.getWidth()))	{ 										
							enemy.getCentre().ApplyVector(new Vector3f(0,-5,0));
						}
					}

		}
	}
	
	
	
	private void spikeLogic() {
		for (GameObject spike: SpikeList) {		
				
					//Collision above the tile -> if Billy is standing on top of it, keep him on it
					if(     (spike.getCentre().getY() - Player.getCentre().getY() >0 && 															
							(spike.getCentre().getY()-2 - Player.getCentre().getY()) < Player.getHeight())									
							&&(( (Player.getCentre().getX()-spike.getCentre().getX() 	) < spike.getWidth()	)									
							&& spike.getCentre().getX() - Player.getCentre().getX() < Player.getWidth()))										
					{			
						if(playinglevel3==false && GameOver==false) {														//Player hitting the bottom of the screen
							Player.setCentre(new Point3f(10,850,0));
							Lives--;
							PlaySound(billyhit, -15.0f, 10000);

							}		
							if(playinglevel3==true && GameOver==false) {														//Special condition for lvl 3
							Player.setCentre(new Point3f(10, 110, 0));
							Lives--;
							PlaySound(billyhit, -15.0f, 10000);
							}
						
					}
			
		}
	}
	
	//##################################################################################################################################################################
	//##################################################################################################################################################################
	//################################################################     		CONTROLS	 	########################################################################
	//##################################################################################################################################################################
	//##################################################################################################################################################################

	private void playerLogic() {
	
		if(Player.getCentre().getY() > 890 && playinglevel3==false && GameOver==false) {														//Player hitting the bottom of the screen
		Player.setCentre(new Point3f(10,850,0));
		Lives--;
		PlaySound(billyhit, -15.0f, 10000);
		}		
		if(Player.getCentre().getY() > 890 && playinglevel3==true && GameOver==false) {														//Special condition for lvl 3
		Player.setCentre(new Point3f(10, 110, 0));
		Lives--;
		PlaySound(billyhit, -15.0f, 10000);
		}
	
		Player.getCentre().ApplyVector(new Vector3f(0,-4,0));										//Constant pull of gravity
	
		if(Controller.getInstance().isKeyAPressed())												//PESS A or a
		{
			Player.getCentre().ApplyVector( new Vector3f(-4,0,0)); 
		}
		
		if(Controller.getInstance().isKeyDPressed())												//PESS D or d
		{
			Player.getCentre().ApplyVector( new Vector3f(4,0,0));
		}
		
		if(Controller.getInstance().isKeyWPressed())												//PESS W or w
		{
			Player.getCentre().ApplyVector( new Vector3f(0,4,0));
		}
		
		if(Controller.getInstance().isKeySPressed()){												//PESS S or s
			Player.getCentre().ApplyVector( new Vector3f(0,-4,0));
		}
		
		if(Controller.getInstance().isKeySpacePressed())											//PESS Space
		{	

			Player.getCentre().ApplyVector( new Vector3f(0,9,0));
		}
		
		if(Controller.getInstance().isKeyPPressed())												//PESS P or p
		{
			CreateBullet();
			Controller.getInstance().setKeyPPressed(false);
		}
		
		if(Controller.getInstance().isKeyOPressed()) {												//PESS O or o
			CreateSword();
			Controller.getInstance().setKeyOPressed(false);
		}
		
		
		if(Controller.getInstance().isKeyQPressed() && gemcount >=6) {												//PESS Q or q
			Player.setCentre(new Point3f (Player.getCentre().getX() - 200 , Player.getCentre().getY(),0) );
			Controller.getInstance().setKeyQPressed(false);
		}
		
		if(Controller.getInstance().isKeyEPressed() && gemcount >=6) {												//PESS E or e
			Player.setCentre(new Point3f (Player.getCentre().getX() + 200 , Player.getCentre().getY(),0) );
			Controller.getInstance().setKeyEPressed(false);
		}
	}
	
	//##################################################################################################################################################################
	//##################################################################################################################################################################
	//################################################################     ATTACKS   ETC  		########################################################################
	//##################################################################################################################################################################
	//##################################################################################################################################################################

	private void CreateBullet() {
		if(BulletList.size() < 1 && gemcount > 2) {
			PlaySound(shoot, -15.0f, 10000);
			BulletList.add(new GameObject("res/fireball.png",50,50,new Point3f(Player.getCentre().getX(),Player.getCentre().getY(),0.0f)));
		}
	}
	
	private void CreateSword() {
		if(SwordList.size() < 1 && gemcount > 2) {
			PlaySound(shoot, -15.0f, 10000);
			SwordList.add(new GameObject("res/sword3.png",70,15,new Point3f(Player.getCentre().getX(), Player.getCentre().getY(), 0.f)));
		}
	}

	public GameObject getPlayer() {
		return Player;
	}

	public CopyOnWriteArrayList<GameObject> getEnemies() {
		return EnemiesList;
	}
	
	public CopyOnWriteArrayList<GameObject> getBullets() {
		return BulletList;
	}
	
	public CopyOnWriteArrayList<GameObject> getSword(){
		return SwordList;
	}
	
	public CopyOnWriteArrayList<GameObject> getTiles(){
		return TilesList;
	}
	public CopyOnWriteArrayList<GameObject> getSpikes(){
		return SpikeList;
	}
	
	public CopyOnWriteArrayList<GameObject> getGem(){
		return GemList;
	}
	
	public CopyOnWriteArrayList<GameObject> getSign(){
		return SignList;
	}

	public int getGems() { 
		return gemcount;
	}
	
	public int getLives() {
		return Lives;
	}
	
	public boolean GameOver() {
		return GameOver;
	}

	//##################################################################################################################################################################
	//##################################################################################################################################################################
	//################################################################     BUILD TUTORIAL LEVEL 		################################################################
	//##################################################################################################################################################################
	//##################################################################################################################################################################
	
	public void BuildTutorial() {
		playingtutorial=true;
		GemList.add(new GameObject("res/gemgreen.png", 32, 32, new Point3f(530,750,0)));
		GemList.add(new GameObject("res/gemgreen.png", 32, 32, new Point3f(450,500,0)));
		GemList.add(new GameObject("res/fireball.png",32, 32, new Point3f(110, 492, 0)));
		SignList.add(new GameObject("res/exitsign.png", 50, 50, new Point3f(900, 358, 0)));
		
		int tilecounter=0, currentx= 0, currenty= 928;
		// starting platform
		for(tilecounter=0; tilecounter < 10 ; tilecounter++) {
			TilesList.add(new GameObject("res/brick.png", 32, 32, new Point3f(currentx, currenty,0)));
			currentx+=32;
		}
		//Spikes
		tilecounter=0; currentx=500; currenty=928;
		for(tilecounter=0; tilecounter < 18; tilecounter++) {
			SpikeList.add(new GameObject("res/spike.png", 32, 32, new Point3f(currentx, currenty,0)));
			currentx+=32;
		}
		//"stairs"
		tilecounter=0; currentx=320; currenty=928;
		for(tilecounter=0; tilecounter < 6; tilecounter++) {
			TilesList.add(new GameObject("res/brick.png", 32, 32, new Point3f(currentx, currenty,0)));
			currentx+=32;
			currenty-=16;
		}
		//block 3
		tilecounter=0; currentx=750; currenty=690;
		for(tilecounter=0; tilecounter < 10; tilecounter++) {
			TilesList.add(new GameObject("res/brick.png", 32, 32, new Point3f(currentx, currenty,0)));
			currentx-=32;
			currenty-=16;
		}
		//block 4
		tilecounter=0; currentx=430; currenty=546;
		for(tilecounter=0; tilecounter < 15 ; tilecounter++) {
			TilesList.add(new GameObject("res/brick.png", 32, 32, new Point3f(currentx, currenty,0)));
			currentx-=32;
		}
		//block 5
		tilecounter=0; currentx=600; currenty= 800;
		for(tilecounter=0; tilecounter < 12; tilecounter++) {
			TilesList.add(new GameObject("res/brick.png", 32, 32, new Point3f(currentx, currenty,0)));
			currentx+=32;
		}
		//block 6
		if(Player.getCentre().getX() <= 75 && Player.getCentre().getY() <= 500 && spawntutorialguy==true) {
			EnemiesList.add(new GameObject(goblinmodel,45,45,new Point3f(300,493,0)));
			spawntutorialguy=false;
		}
		//block 7
		tilecounter=0; currentx=950; currenty=410;
		for(tilecounter=0; tilecounter < 20; tilecounter++) {
			TilesList.add(new GameObject("res/brick.png", 32, 32, new Point3f(currentx, currenty,0)));
			currentx-=32;
		}
	
		//##################################################################################################################################################################
		//##################################################################################################################################################################
		//################################################################     BUILD LEVEL ONE 		########################################################################
		//##################################################################################################################################################################
		//##################################################################################################################################################################
		

	}
	
	public void BuildLevelOne() {

	if(buildlevelone==true) {
		PlaySound(win, -10.0f, 10000);
		 int tilecounter=0, currentx=0, currenty=0;
		 GemList.add(new GameObject("res/gemgreen.png", 32, 32, new Point3f(900,600,0)));
		 GemList.add(new GameObject("res/gemgreen.png", 32, 32, new Point3f(344,500,0)));
		 GemList.add(new GameObject("res/teleport.png", 40, 50, new Point3f(76,356,0)));
		 SignList.add(new GameObject("res/exitsign.png", 50, 50, new Point3f(900, 126, 0)));
		
		 EnemiesList.add(new GameObject(goblinmodel,45,45,new Point3f(950,880,0)));
		 EnemiesList.add(new GameObject(goblinmodel,45,45,new Point3f(950,150,0)));
		 
			 tilecounter=0; currentx=0; currenty=928;
			for(tilecounter=0; tilecounter < 31; tilecounter++) {
				TilesList.add(new GameObject("res/brick.png", 32, 32, new Point3f(currentx, currenty, 0)));
				currentx+=32;
			}
			
			currentx= 0; currenty= 810;
			for(tilecounter=0; tilecounter < 26; tilecounter++) {
				TilesList.add(new GameObject("res/brick.png", 32, 32, new Point3f(currentx, currenty,0)));
				currentx+=32;
			}
			
			tilecounter=0; currentx=150; currenty=715;
			for(tilecounter=0; tilecounter < 6; tilecounter++) {
				TilesList.add(new GameObject("res/brick.png", 32, 32, new Point3f(currentx, currenty, 0)));
				currentx+=32;
				TilesList.add(new GameObject("res/brick.png", 32, 32, new Point3f(currentx, currenty, 0)));
				currentx+=96;
				currenty-=40;
			}
			
			tilecounter=0; currentx= 0; currenty= 410;
			for(tilecounter=0; tilecounter < 23 ; tilecounter++) {
				TilesList.add(new GameObject("res/brick.png", 32, 32, new Point3f(currentx,  currenty,0)));
				currentx+=32;
			}
			
			//"stairs"
			tilecounter=0; currentx=150; currenty=270;
			for(tilecounter=0; tilecounter < 6; tilecounter++) {
				TilesList.add(new GameObject("res/brick.png", 32, 32, new Point3f(currentx, currenty,0)));
				currentx+=32;
				currenty-=16;
			}
			
			tilecounter=0; currentx=340; currenty=174;
			for(tilecounter=0; tilecounter < 5; tilecounter++) {
				TilesList.add(new GameObject("res/brick.png", 32, 32, new Point3f(currentx, currenty, 0)));
				currentx+=32;	
			}
			
			tilecounter=0; currentx=766; currenty=174;
			for(tilecounter=0; tilecounter < 7; tilecounter++) {
				TilesList.add(new GameObject("res/brick.png", 32, 32, new Point3f(currentx, currenty, 0)));
				currentx+=32;	
			}
			
			
		}
		playinglevel1=true;	
		buildlevelone=false;																									//set the build boolean to false so that it only builds once
	}
	
	//##################################################################################################################################################################
	//##################################################################################################################################################################
	//################################################################     BUILD LEVEL TWO 		########################################################################
	//##################################################################################################################################################################
	//##################################################################################################################################################################
	public void BuildLevelTwo() {
	if(buildleveltwo==true) {
		PlaySound(win, -10.0f, 10000);
		playinglevel2=true;	
		
		 int tilecounter=0, currentx=0, currenty=0;
		 GemList.add(new GameObject("res/gemgreen.png", 32, 32, new Point3f(900,550,0)));
		 SignList.add(new GameObject("res/exitsign.png", 50, 50, new Point3f(900, 135, 0)));
		 SignList.add(new GameObject("res/door.png", 45, 50, new Point3f(0, 429, 0)));
		 SignList.add(new GameObject("res/door.png", 45, 50, new Point3f(0, 141, 0)));
		 GemList.add(new GameObject("res/key.png", 45, 50, new Point3f(150, 600, 0)));
		 	
		 	//block 1
			tilecounter=0; currentx=0; currenty=928;
			for(tilecounter=0; tilecounter < 3; tilecounter++) {
				TilesList.add(new GameObject("res/brick.png", 32, 32, new Point3f(currentx, currenty, 0)));
				currentx+=32;
			}
			//Spikes
			tilecounter=0; currentx=910; currenty=928;
			for(tilecounter=0; tilecounter < 26; tilecounter++) {
				SpikeList.add(new GameObject("res/spike.png", 32, 32, new Point3f(currentx, currenty,0)));
				currentx-=32;
			}
			//block2
			tilecounter=0; currentx=160; currenty=820;
			for(tilecounter=0; tilecounter < 3; tilecounter++) {
				TilesList.add(new GameObject("res/brick.png", 32, 32, new Point3f(currentx, currenty, 0)));
				currentx+=32;
			}
			//block3
			tilecounter=0; currentx=424; currenty=750;
			for(tilecounter=0; tilecounter < 5; tilecounter++) {
				TilesList.add(new GameObject("res/brick.png", 32, 32, new Point3f(currentx, currenty, 0)));
				currentx+=32;
			}
			//block4
			tilecounter=0; currentx=710; currenty=680;
			for(tilecounter=0; tilecounter < 1; tilecounter++) {
				TilesList.add(new GameObject("res/brick.png", 32, 32, new Point3f(currentx, currenty, 0)));
				currentx+=32;
			}
			//block5
			tilecounter=0; currentx=820; currenty=610;
			for(tilecounter=0; tilecounter < 5; tilecounter++) {
				TilesList.add(new GameObject("res/brick.png", 32, 32, new Point3f(currentx, currenty, 0)));
				currentx+=32;
			}
			//block6
			tilecounter=0; currentx=720; currenty=530;
			for(tilecounter=0; tilecounter < 5; tilecounter++) {
				TilesList.add(new GameObject("res/brick.png", 32, 32, new Point3f(currentx, currenty, 0)));
				currentx-=32;
			}
			//block7
			tilecounter=0; currentx=424; currenty=590;
			for(tilecounter=0; tilecounter < 5; tilecounter++) {
				TilesList.add(new GameObject("res/brick.png", 32, 32, new Point3f(currentx, currenty, 0)));
				currentx-=32;
			}
			//block8
			tilecounter=0; currentx=140; currenty=480;
			for(tilecounter=0; tilecounter < 6; tilecounter++) {
				TilesList.add(new GameObject("res/brick.png", 32, 32, new Point3f(currentx, currenty, 0)));
				currentx-=32;
			}
			//block9
			tilecounter=0; currentx=0; currenty=192;
			for(tilecounter=0; tilecounter < 31; tilecounter++) {
				TilesList.add(new GameObject("res/brick.png", 32, 32, new Point3f(currentx, currenty, 0)));
				currentx+=32;
			}		
		}

		buildleveltwo=false;
	}

	//##################################################################################################################################################################
	//##################################################################################################################################################################
	//################################################################     BUILD LEVEL THREE 		####################################################################
	//##################################################################################################################################################################
	//##################################################################################################################################################################
	
	public void BuildLevelThree() {
		if(buildlevelthree==true) {
			PlaySound(win, -10.0f, 10000);
			playinglevel3=true;	
			spawnguy=true;
			spawnguy2=true;
			
			 int tilecounter=0, currentx=0, currenty=0;
			 GemList.add(new GameObject("res/gemgreen.png", 32, 32, new Point3f(560,210,0 )));
			 GemList.add(new GameObject("res/gemgreen.png", 32, 32, new Point3f(890,130,0 )));
			 GemList.add(new GameObject("res/gemgreen.png", 32, 32, new Point3f(750,546,0 )));
			 GemList.add(new GameObject("res/gemgreen.png", 32, 32, new Point3f(184,530,0 )));
			 GemList.add(new GameObject("res/gemgreen.png", 32, 32, new Point3f(16,697 ,0 )));
			 GemList.add(new GameObject("res/gemgreen.png", 60, 60, new Point3f(750,750,0 )));
			 SignList.add(new GameObject("res/exitsign.png", 50, 50, new Point3f(850,798,0)));	//should have 13 gems at exit
			 SignList.add(new GameObject("res/door.png", 45, 50, new Point3f(0, 99, 0)));
			 SignList.add(new GameObject("res/door.png", 45, 50, new Point3f(890,549,0)));
	
			 	
				//block1
				tilecounter=0; currentx=0; currenty=150;
				for(tilecounter=0; tilecounter < 4; tilecounter++) {
					TilesList.add(new GameObject("res/brick.png", 32, 32, new Point3f(currentx, currenty, 0)));
					currentx+=32;
				}
				//Spikes
				tilecounter=0; currentx=910; currenty=928;
				for(tilecounter=0; tilecounter < 28; tilecounter++) {
					SpikeList.add(new GameObject("res/spike.png", 32, 32, new Point3f(currentx, currenty,0)));
					currentx-=32;
				}
				//block2
				tilecounter=0; currentx=228; currenty=250;
				for(tilecounter=0; tilecounter < 4; tilecounter++) {
					TilesList.add(new GameObject("res/brick.png", 32, 32, new Point3f(currentx, currenty, 0)));
					currentx+=32;
				}
				//block3
				//tilecounter=0; currentx=550; currenty=350;
				//for(tilecounter=0; tilecounter < 2; tilecounter++) {
					//TilesList.add(new GameObject("res/brick.png", 32, 32, new Point3f(currentx, currenty, 0)));
					//currentx+=32;
				//}
				//block4
				tilecounter=0; currentx=950; currenty=200;
				for(tilecounter=0; tilecounter < 6; tilecounter++) {
					TilesList.add(new GameObject("res/brick.png", 32, 32, new Point3f(currentx, currenty, 0)));
					currentx-=32;
				}
				//block5
				tilecounter=0; currentx=950; currenty=600;
				for(tilecounter=0; tilecounter < 15; tilecounter++) {
					TilesList.add(new GameObject("res/brick.png", 32, 32, new Point3f(currentx, currenty, 0)));
					currentx-=32;
				}
				//block6
				tilecounter=0; currentx=200; currenty=600;
				for(tilecounter=0; tilecounter < 1; tilecounter++) {
					TilesList.add(new GameObject("res/brick.png", 32, 32, new Point3f(currentx, currenty, 0)));
					currentx-=32;
					TilesList.add(new GameObject("res/brick.png", 32, 32, new Point3f(currentx, currenty, 0)));
					currentx-=90;
				}
				//block7
				tilecounter=0; currentx=0; currenty=750;
				for(tilecounter=0; tilecounter < 4; tilecounter++) {
					TilesList.add(new GameObject("res/brick.png", 32, 32, new Point3f(currentx, currenty, 0)));
					currentx+=32;
				}
				//block8
				tilecounter=0; currentx=950; currenty=850;
				for(tilecounter=0; tilecounter < 12; tilecounter++) {
					TilesList.add(new GameObject("res/brick.png", 32, 32, new Point3f(currentx, currenty, 0)));
					currentx-=32;
				}

				
				playinglevel2=false;
				
			}

			buildlevelthree=false;
		
		}
	
		//##################################################################################################################################################################
		//##################################################################################################################################################################
		//################################################################     BUILD LEVEL FOUR 		####################################################################
		//##################################################################################################################################################################
		//##################################################################################################################################################################
		
		public void BuildLevelFour() {
			if(buildlevelfour==true) {
				PlaySound(win, -10.0f, 10000);
				playinglevel4=true;	
				evilbillyexists=true;
				 
				 SignList.add(new GameObject("res/princess.png", 50, 70, new Point3f(872,874,0)));
		
				 	
					//block1
				 	int tilecounter=0, currentx=0, currenty=928;
					for(tilecounter=0; tilecounter < 31 ; tilecounter++) {
						TilesList.add(new GameObject("res/brick.png", 32, 32, new Point3f(currentx, currenty,0)));
						currentx+=32;
					}
					
					//block2
					tilecounter=0; currentx=200; currenty=750 ;
					for(tilecounter=0; tilecounter < 5 ; tilecounter++) {
						TilesList.add(new GameObject("res/brick.png", 32, 32, new Point3f(currentx, currenty,0)));
						currentx+=32;
					}
					
					tilecounter=0; currentx=700; currenty=750 ;
					for(tilecounter=0; tilecounter < 5 ; tilecounter++) {
						TilesList.add(new GameObject("res/brick.png", 32, 32, new Point3f(currentx, currenty,0)));
						currentx+=32;
					}

					EnemiesList.add(new GameObject("res/billy_evil_right.png",80,80,new Point3f(910,870,0)));
					playinglevel3=false;
					
				}

				buildlevelfour=false;
			
			}
		
		//##################################################################################################################################################################
		//##################################################################################################################################################################
		//################################################################    		 THE END	 		####################################################################
		//##################################################################################################################################################################
		//##################################################################################################################################################################
	
		
		public void ClearLevel(){
			if(clearlevel==true) {
				for(GameObject tile:TilesList) {
					TilesList.remove(tile);
					TilesList.clear();
				}
				for(GameObject sign:SignList) {
					SignList.remove(sign);
					SignList.clear();
				}
				for(GameObject gem:GemList) {
					GemList.remove(gem);
				}
				for(GameObject enemy:EnemiesList) {
					EnemiesList.remove(enemy);
				}
				for(GameObject spike:SpikeList) {
					SpikeList.remove(spike);
				}
			}
			clearlevel=false;
		}
	
}