import java.io.FileNotFoundException;
import java.util.HashMap;

import units.Player;


public class Solutions {
	public static void main(String[] args) throws FileNotFoundException {
		DataSet ds = new DataSet("Resources/data/player_data_volleyball.csv");
		HashMap<Integer, Player> roster = ds.getRoster();
		System.out.println(ds.calcAverage(Player.STRESS));
	}
	
	private double mostSleepAverage() {
		return -1;
	}
	
	private double weekendVsWeekdaySleep() {
		return -1;
	}
}
