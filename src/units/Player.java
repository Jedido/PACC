package units;
import java.util.GregorianCalendar;

public class Player extends Unit {
	private int id; // tentative
	// sleep is recorded as number of hours * 10

	public static final int DAY = 0;
	public static final int FATIGUE = 1;
	public static final int SLEEP_QUALITY = 2;
	public static final int SORENESS = 3;
	public static final int STRESS = 4;
	public static final int MOOD = 5;
	public static final int SLEEP_TIME = 6;

	public Player(int playerID) {
		super();
		id = playerID;
	}

	public void addEntry(String[] entryData) {
		// Month, day, year
		String[] fullDate = entryData[3].split("/");

		// Gregorian Calendar param = int year - 1990, int month from 0 - 11, 
		// int day from 1 - 31
		GregorianCalendar added = new GregorianCalendar(
				Integer.parseInt(fullDate[2]) - 1990,
				Integer.parseInt(fullDate[0]) - 1,
				Integer.parseInt(fullDate[1]));
		getDates().add(added);

		int[] playerData = new int[7];
		playerData[0] = Integer.parseInt(entryData[1]);
		for (int i = 3; i < 8; i++)
			playerData[i - 2] = Integer.parseInt(entryData[i]);
		playerData[6] = (int) (10 * Double.parseDouble(entryData[9]));

		getData().put(added, playerData);
	}

	public int calcAverage(int position, int startOfWeek, int endOfWeek) {
		int total = 0;
		int numOfDays = 0;
		for (int[] daily : getData().values()) {
			if (daily[DAY] >= startOfWeek && daily[DAY] <= endOfWeek) {
				numOfDays++;
				total += daily[position];
			}
		}
		return total / numOfDays;
	}

}
