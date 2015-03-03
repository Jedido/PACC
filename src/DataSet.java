import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Scanner;

import units.Player;

public class DataSet {

	private HashMap<Integer, Player> roster;
	private int players;
	private int entries;

	public DataSet(String fileName) throws FileNotFoundException {
		players = 0;
		entries = 0;
		roster = new HashMap<Integer, Player>();
		Scanner input = new Scanner(new File(fileName));
		input.nextLine();

		while (input.hasNextLine()) {
			entries++;
			String[] line = input.nextLine().split(",");
			int id = Integer.parseInt(line[0]);

			if (!roster.containsKey(id)) {
				players++;
				roster.put(id, new Player(id));
				roster.get(id).addEntry(line);
			} else {
				roster.get(id).addEntry(line);
			}
		}
		
		input.close();
	}
	
	public HashMap<Integer, Player> getRoster() {
		return roster;
	}
	
	public double calcAverage(int info) {
		double sum = 0;
		int entri = 0;
		for(Player p : roster.values()) {
			sum += p.calcTotal(info);
			entri += p.getDates().size();
		}
		System.out.println(sum);
		System.out.println(entri);
		return sum / entries;
	}
	
//	public double calcAverage(Player player, int info) {
//		return 0;
//	}
//	
//	//leave player as null to get for all players
//	public double calcAverage(Player player, int info, int start, int end) {
//		return 0;
//	}
//	
//	public double calcAverageWeekends(int info) {
//		return 0;
//	}
//	
//	public double calcAverageWeekdays(int info) {
//		return 0;
//	}
	
	public int getEntries() {
		return entries;
	}
}