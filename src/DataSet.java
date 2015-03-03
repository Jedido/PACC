import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Scanner;

public class DataSet {

	private HashMap<Integer, Player> roster;

	public DataSet(String fileName) throws FileNotFoundException {
		Scanner input = new Scanner(new File(fileName));
		input.nextLine();

		while (input.hasNextLine()) {
			String[] line = input.nextLine().split(",");
			int id = Integer.parseInt(line[0]);

			if (!roster.containsKey(id)) {
				roster.put(id, new Player(id));
				roster.get(id).addEntry(line);
			}

		}
		
		input.close();
	}
	
	public HashMap<Integer, Player> getRoster() {
		return roster;
	}
}