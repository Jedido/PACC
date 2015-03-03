package units;

import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.HashMap;

/*
 * This class is the general data storage strategy for a unit of information i.e. Player and Match
 */
public abstract class Unit {
	private HashMap<GregorianCalendar, int[]> dataSets;
	private ArrayList<GregorianCalendar> dates;
	
	public Unit() {
		dataSets = new HashMap<GregorianCalendar, int[]>();
		dates = new ArrayList<GregorianCalendar>(); 
	}

	public abstract void addEntry(String[] entryData);
	
	public int calcTotal(int position) {
		int total = 0;
		for (int[] daily : dataSets.values())
			total += daily[position];
		return total;
	}

	public int calcAverage(int position) {
		return calcTotal(position) / dataSets.size();
	}

	// int[0] == lower bound; int[1] == upper bound;
	public int[] calcRange(int position) {
		int[] range = { Integer.MAX_VALUE, -1 };
		for (int[] daily : dataSets.values()) {
			if (range[0] > daily[position])
				range[0] = daily[position];
			if (range[1] < daily[position])
				range[1] = daily[position];
		}
		return range;
	}

	public int[] returnPosition(int position) {
		int[] givenPos = new int[dataSets.size()];
		for (int i = 0; i < givenPos.length; i++)
			givenPos[i] = dataSets.get(dates.get(i))[position];
		return givenPos;
	}
	
	public boolean isWeekend(int day) {
		return day == 1 || day == 7;
	}
	
	public boolean isWeekday(int day) {
		return day != 1 && day != 7;
	}
	
	public HashMap<GregorianCalendar, int[]> getData() {
		return dataSets;
	}
	
	public ArrayList<GregorianCalendar> getDates() {
		return dates;
	}
}
