import java.util.concurrent.CopyOnWriteArrayList;
import util.GameObject;

public class GameWorld {
	private Player Player;
	private CopyOnWriteArrayList<GameObject> EnemiesList = new CopyOnWriteArrayList<GameObject>();
	private CopyOnWriteArrayList<GameObject> BulletList = new CopyOnWriteArrayList<GameObject>();
	private CopyOnWriteArrayList<GameObject> SwordList = new CopyOnWriteArrayList<GameObject>();
	private CopyOnWriteArrayList<GameObject> TilesList = new CopyOnWriteArrayList<GameObject>();
	private CopyOnWriteArrayList<GameObject> SpikeList = new CopyOnWriteArrayList<GameObject>();
	private CopyOnWriteArrayList<GameObject> SignList = new CopyOnWriteArrayList<GameObject>();
	private CopyOnWriteArrayList<GameObject> GemList = new CopyOnWriteArrayList<GameObject>();

	public Player getPlayer() {
		return Player;
	}

	public void setPlayer(Player player) {
		Player = player;
	}

	public CopyOnWriteArrayList<GameObject> getEnemies() {
		return EnemiesList;
	}

	public CopyOnWriteArrayList<GameObject> getBullets() {
		return BulletList;
	}

	public CopyOnWriteArrayList<GameObject> getSword() {
		return SwordList;
	}

	public CopyOnWriteArrayList<GameObject> getTiles() {
		return TilesList;
	}

	public CopyOnWriteArrayList<GameObject> getSpikes() {
		return SpikeList;
	}

	public CopyOnWriteArrayList<GameObject> getGem() {
		return GemList;
	}

	public CopyOnWriteArrayList<GameObject> getSign() {
		return SignList;
	}
}
