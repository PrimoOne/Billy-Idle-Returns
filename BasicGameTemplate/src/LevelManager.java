import util.GameObject;
import util.Point3f;

public class LevelManager {

	public void populateLevelOne(GameWorld gameWorld, String goblinmodel) {
		int tilecounter=0, currentx=0, currenty=0;
		gameWorld.getGem().add(new GameObject("res/gemgreen.png", 32, 32, new Point3f(900,600,0)));
		gameWorld.getGem().add(new GameObject("res/gemgreen.png", 32, 32, new Point3f(344,500,0)));
		gameWorld.getGem().add(new GameObject("res/teleport.png", 40, 50, new Point3f(76,356,0)));
		gameWorld.getSign().add(new GameObject("res/exitsign.png", 50, 50, new Point3f(900, 126, 0)));

		gameWorld.getEnemies().add(new GameObject(goblinmodel,45,45,new Point3f(950,880,0)));
		gameWorld.getEnemies().add(new GameObject(goblinmodel,45,45,new Point3f(950,150,0)));

		tilecounter=0; currentx=0; currenty=928;
		for(tilecounter=0; tilecounter < 31; tilecounter++) {
			gameWorld.getTiles().add(new GameObject("res/brick.png", 32, 32, new Point3f(currentx, currenty, 0)));
			currentx+=32;
		}

		currentx= 0; currenty= 810;
		for(tilecounter=0; tilecounter < 26; tilecounter++) {
			gameWorld.getTiles().add(new GameObject("res/brick.png", 32, 32, new Point3f(currentx, currenty,0)));
			currentx+=32;
		}

		tilecounter=0; currentx=150; currenty=715;
		for(tilecounter=0; tilecounter < 6; tilecounter++) {
			gameWorld.getTiles().add(new GameObject("res/brick.png", 32, 32, new Point3f(currentx, currenty, 0)));
			currentx+=32;
			gameWorld.getTiles().add(new GameObject("res/brick.png", 32, 32, new Point3f(currentx, currenty, 0)));
			currentx+=96;
			currenty-=40;
		}

		tilecounter=0; currentx= 0; currenty= 410;
		for(tilecounter=0; tilecounter < 23 ; tilecounter++) {
			gameWorld.getTiles().add(new GameObject("res/brick.png", 32, 32, new Point3f(currentx,  currenty,0)));
			currentx+=32;
		}

		//"stairs"
		tilecounter=0; currentx=150; currenty=270;
		for(tilecounter=0; tilecounter < 6; tilecounter++) {
			gameWorld.getTiles().add(new GameObject("res/brick.png", 32, 32, new Point3f(currentx, currenty,0)));
			currentx+=32;
			currenty-=16;
		}

		tilecounter=0; currentx=340; currenty=174;
		for(tilecounter=0; tilecounter < 5; tilecounter++) {
			gameWorld.getTiles().add(new GameObject("res/brick.png", 32, 32, new Point3f(currentx, currenty, 0)));
			currentx+=32;
		}

		tilecounter=0; currentx=766; currenty=174;
		for(tilecounter=0; tilecounter < 7; tilecounter++) {
			gameWorld.getTiles().add(new GameObject("res/brick.png", 32, 32, new Point3f(currentx, currenty, 0)));
			currentx+=32;
		}
	}

	public void populateLevelTwo(GameWorld gameWorld) {
		int tilecounter=0, currentx=0, currenty=0;
		gameWorld.getGem().add(new GameObject("res/gemgreen.png", 32, 32, new Point3f(900,550,0)));
		gameWorld.getSign().add(new GameObject("res/exitsign.png", 50, 50, new Point3f(900, 135, 0)));
		gameWorld.getSign().add(new GameObject("res/door.png", 45, 50, new Point3f(0, 429, 0)));
		gameWorld.getSign().add(new GameObject("res/door.png", 45, 50, new Point3f(0, 141, 0)));
		gameWorld.getGem().add(new GameObject("res/key.png", 45, 50, new Point3f(150, 600, 0)));

		//block 1
		tilecounter=0; currentx=0; currenty=928;
		for(tilecounter=0; tilecounter < 3; tilecounter++) {
			gameWorld.getTiles().add(new GameObject("res/brick.png", 32, 32, new Point3f(currentx, currenty, 0)));
			currentx+=32;
		}
		//Spikes
		tilecounter=0; currentx=910; currenty=928;
		for(tilecounter=0; tilecounter < 26; tilecounter++) {
			gameWorld.getSpikes().add(new GameObject("res/spike.png", 32, 32, new Point3f(currentx, currenty,0)));
			currentx-=32;
		}
		//block2
		tilecounter=0; currentx=160; currenty=820;
		for(tilecounter=0; tilecounter < 3; tilecounter++) {
			gameWorld.getTiles().add(new GameObject("res/brick.png", 32, 32, new Point3f(currentx, currenty, 0)));
			currentx+=32;
		}
		//block3
		tilecounter=0; currentx=424; currenty=750;
		for(tilecounter=0; tilecounter < 5; tilecounter++) {
			gameWorld.getTiles().add(new GameObject("res/brick.png", 32, 32, new Point3f(currentx, currenty, 0)));
			currentx+=32;
		}
		//block4
		tilecounter=0; currentx=710; currenty=680;
		for(tilecounter=0; tilecounter < 1; tilecounter++) {
			gameWorld.getTiles().add(new GameObject("res/brick.png", 32, 32, new Point3f(currentx, currenty, 0)));
			currentx+=32;
		}
		//block5
		tilecounter=0; currentx=820; currenty=610;
		for(tilecounter=0; tilecounter < 5; tilecounter++) {
			gameWorld.getTiles().add(new GameObject("res/brick.png", 32, 32, new Point3f(currentx, currenty, 0)));
			currentx+=32;
		}
		//block6
		tilecounter=0; currentx=720; currenty=530;
		for(tilecounter=0; tilecounter < 5; tilecounter++) {
			gameWorld.getTiles().add(new GameObject("res/brick.png", 32, 32, new Point3f(currentx, currenty, 0)));
			currentx-=32;
		}
		//block7
		tilecounter=0; currentx=424; currenty=590;
		for(tilecounter=0; tilecounter < 5; tilecounter++) {
			gameWorld.getTiles().add(new GameObject("res/brick.png", 32, 32, new Point3f(currentx, currenty, 0)));
			currentx-=32;
		}
		//block8
		tilecounter=0; currentx=140; currenty=480;
		for(tilecounter=0; tilecounter < 6; tilecounter++) {
			gameWorld.getTiles().add(new GameObject("res/brick.png", 32, 32, new Point3f(currentx, currenty, 0)));
			currentx-=32;
		}
		//block9
		tilecounter=0; currentx=0; currenty=192;
		for(tilecounter=0; tilecounter < 31; tilecounter++) {
			gameWorld.getTiles().add(new GameObject("res/brick.png", 32, 32, new Point3f(currentx, currenty, 0)));
			currentx+=32;
		}
	}

	public GameObject populateTestingLevel(GameWorld gameWorld, String goblinmodel) {
		for(int x=0; x<=992; x+=32) {
			gameWorld.getTiles().add(new GameObject("res/brick.png", 32, 32, new Point3f(x,900,0)));
		}

		gameWorld.getSpikes().add(new GameObject("res/spike.png", 32, 32, new Point3f(160,868,0)));

		for(int y=708; y<=868; y+=32) {
			gameWorld.getTiles().add(new GameObject("res/brick.png", 32, 32, new Point3f(288,y,0)));
		}

		for(int x=416; x<=544; x+=32) {
			gameWorld.getTiles().add(new GameObject("res/brick.png", 32, 32, new Point3f(x,760,0)));
		}

		for(int x=640; x<=864; x+=32) {
			gameWorld.getTiles().add(new GameObject("res/brick.png", 32, 32, new Point3f(x,800,0)));
		}

		for(int y=672; y<=768; y+=32) {
			gameWorld.getTiles().add(new GameObject("res/brick.png", 32, 32, new Point3f(640,y,0)));
			gameWorld.getTiles().add(new GameObject("res/brick.png", 32, 32, new Point3f(832,y,0)));
		}

		GameObject testingEnemy = new GameObject(goblinmodel,45,45,new Point3f(700,755,0));
		gameWorld.getEnemies().add(testingEnemy);
		return testingEnemy;
	}
}
