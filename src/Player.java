import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.HashMap;

public class Player {
	private int id; // tentative
	// sleep is recorded as number of hours * 10
	private HashMap<GregorianCalendar, int[]> dailyData;
	private ArrayList<GregorianCalendar> dates;

	public static final int DAY = 0;
	public static final int FATIGUE = 1;
	public static final int SLEEP_QUALITY = 2;
	public static final int SORENESS = 3;
	public static final int STRESS = 4;
	public static final int MOOD = 5;
	public static final int SLEEP_TIME = 6;

	public Player(int playerID) {
		id = playerID;
		dailyData = new HashMap<GregorianCalendar, int[]>();
		dates = new ArrayList<GregorianCalendar>(); 
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
		dates.add(added);

		int[] playerData = new int[7];
		playerData[0] = Integer.parseInt(entryData[1]);
		for (int i = 3; i < 8; i++)
			playerData[i - 2] = Integer.parseInt(entryData[i]);
		playerData[6] = (int) (10 * Double.parseDouble(entryData[9]));

		dailyData.put(added, playerData);
	}

	public int calcTotal(int position) {
		int total = 0;
		for (int[] daily : dailyData.values())
			total += daily[position];
		return total;
	}

	public int calcAverage(int position) {
		return calcTotal(position) / dailyData.size();
	}

	public int calcAverage(int position, int startOfWeek, int endOfWeek) {
		int total = 0;
		int numOfDays = 0;
		for (int[] daily : dailyData.values()) {
			if (daily[DAY] >= startOfWeek && daily[DAY] <= endOfWeek) {
				numOfDays++;
				total += daily[position];
			}
		}
		return total / numOfDays;
	}

	// int[0] == lower bound; int[1] == upper bound;
	public int[] calcRange(int position) {
		int[] range = { Integer.MAX_VALUE, -1 };
		for (int[] daily : dailyData.values()) {
			if (range[0] > daily[position])
				range[0] = daily[position];
			if (range[1] < daily[position])
				range[1] = daily[position];
		}
		return range;
	}

	public int[] returnPosition(int position) {
		int[] givenPos = new int[dailyData.size()];
		for (int i = 0; i < givenPos.length; i++)
			givenPos[i] = dailyData.get(dates.get(i))[position];
		return givenPos;
	}
	
	public boolean isWeekend(int day) {
		return day == 1 || day == 7;
	}
	
	public boolean isWeekday(int day) {
		return day != 1 && day != 7;
	}
}
