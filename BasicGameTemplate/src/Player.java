import util.GameObject;
import util.Point3f;

public class Player extends GameObject {
	private float velocityY = 0.0f;

	public Player(String textureLocation, int width, int height, Point3f centre) {
		super(textureLocation, width, height, centre);
	}

	public float getVelocityY() {
		return velocityY;
	}

	public void setVelocityY(float velocityY) {
		this.velocityY = velocityY;
	}
}
