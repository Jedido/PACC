package units;

import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.HashMap;

public abstract class Unit {

	public abstract void addEntry(String[] entryData);

	public abstract int calcTotal(int position);
	

	public int calcAverage(int position) {
		return calcTotal(position) / dailyData.size();
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
