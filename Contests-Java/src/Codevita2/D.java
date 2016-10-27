/*
* Name	: D.java
* Author: Somnath Rakshit
* Date	: Aug 19, 2016
*/
package Codevita2;

import java.io.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.InputMismatchException;

class D {
	public static void main(String[] args) throws Exception {
		InputReader in = new InputReader(System.in);
		int day_week=0;

		String day = in.readString();
		HashSet hs = new HashSet();
		hs.add("Sunday");
		hs.add("Monday");
		hs.add("Tuesday");
		hs.add("Wednesday");
		hs.add("Thursday");
		hs.add("Friday");
		hs.add("Saturday");

		if (!hs.contains(day)) {
			System.out.println("Invalid Day");
			return;
		}
		if(day.equals("Sunday"))
			day_week=1;
		else if(day.equals("Monday"))
			day_week=2;
		else if(day.equals("Tuesday"))
			day_week=3;
		else if(day.equals("Wednesday"))
			day_week=4;
		else if(day.equals("Thursday"))
			day_week=5;
		else if(day.equals("Friday"))
			day_week=6;
		else if(day.equals("Saturday"))
			day_week=7;

		String date = in.readString().trim();

		if (invalidDateFormat(date) || date.charAt(date.length() - 1) == '/') {
			System.out.println("Invalid Date");
			return;
		}


		if (day.equals("Saturday") || day.equals("Sunday")) {
			System.out.println(0);
			return;
		}

		String arr[] = date.split("/");

		if (arr[0].length() != 2 || arr[1].length() != 2 || arr[2].length() != 4) {
			System.out.println("Invalid Date");
			return;
		}

		int d = Integer.parseInt(arr[0]);
		int m = Integer.parseInt(arr[1]);
		int y = Integer.parseInt(arr[2]);

		Calendar c = Calendar.getInstance();
		c.set(y, m, d);
		int day_of_week = c.get(Calendar.DAY_OF_WEEK);

		if(day_week!=day_of_week) {
			System.out.println("Invalid Date");
			return;
		}

		if (d == 1 && m == 1 && y == 1) {
			System.out.println(1);
			return;
		}

		long diff = diff(y, m, d, 1, 1, 1) + 1;
		long year_diff = diff(y, m, d, y, 1, 1) + 1;
		//System.out.println(diff + " " + year_diff);

		if (diff % 4 == 0) {
			System.out.println(0);
		} else {
			if (year_diff >= 50)
				year_diff = 50;
			System.out.println(year_diff);
		}
	}

	public static int julianDay(int year, int month, int day) {
		int a = (14 - month) / 12;
		int y = year + 4800 - a;
		int m = month + 12 * a - 3;
		int jdn = day + (153 * m + 2) / 5 + 365 * y + y / 4 - y / 100 + y / 400 - 32045;
		return jdn;
	}

	public static int diff(int y1, int m1, int d1, int y2, int m2, int d2) {
		return julianDay(y1, m1, d1) - julianDay(y2, m2, d2);
	}

	public static boolean invalidDateFormat(final String date) {
		String[] formatStrings = {"dd/MM/yyyy"};
		boolean isInvalidFormat = false;
		Date dateObj;
		for (String formatString : formatStrings) {
			try {
				SimpleDateFormat sdf = (SimpleDateFormat) DateFormat.getDateInstance();
				sdf.applyPattern(formatString);
				sdf.setLenient(false);
				dateObj = sdf.parse(date);
				if (date.equals(sdf.format(dateObj))) {
					isInvalidFormat = false;
					break;
				}
			} catch (ParseException e) {
				isInvalidFormat = true;
			}
		}
		return isInvalidFormat;
	}

	//FAST I/P
	private static class InputReader {
		private InputStream stream;
		private byte[] buf = new byte[1024];
		private int curChar;
		private int numChars;
		private SpaceCharFilter filter;

		public int[] readIntArray(int size) {
			int[] array = new int[size];
			for (int i = 0; i < size; i++)
				array[i] = readInt();
			return array;
		}

		public InputReader(InputStream stream) {
			this.stream = stream;
		}

		public int read() {
			if (numChars == -1)
				throw new InputMismatchException();
			if (curChar >= numChars) {
				curChar = 0;
				try {
					numChars = stream.read(buf);
				} catch (IOException e) {
					throw new InputMismatchException();
				}
				if (numChars <= 0)
					return -1;
			}
			return buf[curChar++];
		}

		public int readInt() {
			int c = read();
			while (isSpaceChar(c))
				c = read();
			int sgn = 1;
			if (c == '-') {
				sgn = -1;
				c = read();
			}
			int res = 0;
			do {
				if (c < '0' || c > '9')
					throw new InputMismatchException();
				res *= 10;
				res += c - '0';
				c = read();
			} while (!isSpaceChar(c));
			return res * sgn;
		}

		public String readString() {
			int c = read();
			while (isSpaceChar(c))
				c = read();
			StringBuilder res = new StringBuilder();
			do {
				res.appendCodePoint(c);
				c = read();
			} while (!isSpaceChar(c));
			return res.toString();
		}

		public String readStringFull() {
			int c = read();
			while (isSpaceChar(c))
				c = read();
			StringBuilder res = new StringBuilder();
			do {
				res.appendCodePoint(c);
				c = read();
			} while (c != '\n');
			return res.toString();
		}

		public double readDouble() {
			int c = read();
			while (isSpaceChar(c))
				c = read();
			int sgn = 1;
			if (c == '-') {
				sgn = -1;
				c = read();
			}
			double res = 0;
			while (!isSpaceChar(c) && c != '.') {
				if (c == 'e' || c == 'E')
					return res * Math.pow(10, readInt());
				if (c < '0' || c > '9')
					throw new InputMismatchException();
				res *= 10;
				res += c - '0';
				c = read();
			}
			if (c == '.') {
				c = read();
				double m = 1;
				while (!isSpaceChar(c)) {
					if (c == 'e' || c == 'E')
						return res * Math.pow(10, readInt());
					if (c < '0' || c > '9')
						throw new InputMismatchException();
					m /= 10;
					res += (c - '0') * m;
					c = read();
				}
			}
			return res * sgn;
		}

		public long readLong() {
			int c = read();
			while (isSpaceChar(c))
				c = read();
			int sgn = 1;
			if (c == '-') {
				sgn = -1;
				c = read();
			}
			long res = 0;
			do {
				if (c < '0' || c > '9')
					throw new InputMismatchException();
				res *= 10;
				res += c - '0';
				c = read();
			} while (!isSpaceChar(c));
			return res * sgn;
		}

		public boolean isSpaceChar(int c) {
			if (filter != null)
				return filter.isSpaceChar(c);
			return c == ' ' || c == '\n' || c == '\r' || c == '\t' || c == -1;
		}

		public String next() {
			return readString();
		}

		public interface SpaceCharFilter {
			public boolean isSpaceChar(int ch);
		}
	}
}
