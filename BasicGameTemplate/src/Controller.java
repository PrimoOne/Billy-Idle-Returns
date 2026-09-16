import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import java.util.Timer;


public class Controller implements KeyListener {
        
	   private static boolean KeyAPressed= false;
	   private static boolean KeyOPressed= false;
	   private static boolean KeySPressed= false;
	   private static boolean KeyDPressed= false;
	   private static boolean KeyWPressed= false;
	   private static boolean KeyPPressed= false;
	   private static boolean KeyQPressed= false;
	   private static boolean KeyEPressed= false;
	   private static boolean KeySpacePressed= false;
	   public long lastpressed=0;
	   Timer timer = new Timer();
	   private static final Controller instance = new Controller();
	   
	 public Controller() { 
	}
	 
	 public static Controller getInstance(){
	        return instance;
	    }
	   
	@Override
	// Key pressed , will keep triggering 
	public void keyTyped(KeyEvent e) { 
		 
	}

	@Override
	public void keyPressed(KeyEvent e) 
	{ 
		switch (e.getKeyChar()) 										//Added CAPS-LOCK-on options
		{
			case 'a':setKeyAPressed(true);break;
			case 'A':setKeyAPressed(true);break;
			case 's':setKeySPressed(true);break;
			case 'S':setKeySPressed(true);break;
			case 'w':setKeyWPressed(true);break;
			case 'W':setKeyWPressed(true);break;
			case 'd':setKeyDPressed(true);break;
			case 'D':setKeyDPressed(true);break;
			case 'p':setKeyPPressed(true);break;
			case 'P':setKeyPPressed(true);break;
			case 'o':setKeyOPressed(true);break;
			case 'O':setKeyOPressed(true);break;
			case 'q':setKeyQPressed(true);break;
			case 'Q':setKeyQPressed(true);break;
			case 'e':setKeyEPressed(true);break;
			case 'E':setKeyEPressed(true);break;
			case ' ':setKeySpacePressed(true);break;   
		    default:
		    	//System.out.println("Controller test:  Unknown key pressed");
		        break;
		}  
		
	 // You can implement to keep moving while pressing the key here . 
		
	}

	@Override
	public void keyReleased(KeyEvent e) 
	{ 
		switch (e.getKeyChar()) 
		{
			case 'a':setKeyAPressed(false);break; 
			case 'A':setKeyAPressed(false);break; 
			case 's':setKeySPressed(false);break;
			case 'S':setKeySPressed(false);break;
			case 'w':setKeyWPressed(false);break;
			case 'W':setKeyWPressed(false);break;
			case 'd':setKeyDPressed(false);break;
			case 'D':setKeyDPressed(false);break;
			case 'p':setKeyPPressed(false);break;
			case 'P':setKeyPPressed(false);break;
			case 'o':setKeyOPressed(false);break;
			case 'O':setKeyOPressed(false);break;
			case 'q':setKeyQPressed(false);break; 
			case 'Q':setKeyQPressed(false);break; 
			case 'e':setKeyEPressed(false);break; 
			case 'E':setKeyEPressed(false);break; 
			case ' ':setKeySpacePressed(false);break;   
		    default:
		    	//System.out.println("Controller test:  Unknown key pressed");
		        break;
		}  
		 //upper case 
	
	}


	public boolean isKeyAPressed() {
		return KeyAPressed;
	}


	public void setKeyAPressed(boolean keyAPressed) {
		KeyAPressed = keyAPressed;
	}


	public boolean isKeySPressed() {
		return KeySPressed;
	}


	public void setKeySPressed(boolean keySPressed) {
		KeySPressed = keySPressed;
	}

	
	public boolean isKeyPPressed() {
		return KeyPPressed;
	}


	public void setKeyPPressed(boolean keyPPressed) {				//throw
		KeyPPressed = keyPPressed;
	}
	
	public boolean isKeyOPressed() {
		return KeyOPressed;
	}
	
	public void setKeyOPressed(boolean keyOPressed) {				//stab
		KeyOPressed = keyOPressed;
	}


	public boolean isKeyDPressed() {
		return KeyDPressed;
	}


	public void setKeyDPressed(boolean keyDPressed) {
		KeyDPressed = keyDPressed;
	}


	public boolean isKeyWPressed() {
		return KeyWPressed;
	}


	public void setKeyWPressed(boolean keyWPressed) {
		KeyWPressed = keyWPressed;
	}
	
	public boolean isKeyEPressed() {
		return KeyEPressed;
	}


	public void setKeyEPressed(boolean keyEPressed) {
		KeyEPressed = keyEPressed;
	}
	
	public boolean isKeyQPressed() {
		return KeyQPressed;
	}


	public void setKeyQPressed(boolean keyQPressed) {
		KeyQPressed = keyQPressed;
	}


	public boolean isKeySpacePressed() {
			return KeySpacePressed;	
	}


	public void setKeySpacePressed(boolean keySpacePressed) {
		KeySpacePressed = keySpacePressed;
	} 
	
	 
}