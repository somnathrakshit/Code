/*
* Name	: newFootball.java
* Author: Somnath Rakshit
* Date	: Jul 29, 2016
*/

import java.io.*;
import java.util.*;

class newFootball {
	static Football ob[];
	static String team[];

	public static int getIndex(String s) {
		int i;
		for (i = 0; i < ob.length; i++)
			if (team[i].equalsIgnoreCase(s))
				break;
		return i;
	}

	public static void main(String[] args) throws Exception {
		InputReader in = new InputReader(System.in);
		boolean v = false;
		int i, n = in.readInt();
		team = in.readStringFull().split(" ");
		ob = new Football[n];
		for (i = 0; i < n; i++) {
			ob[i] = new Football("", 0, 0, 0);
			ob[i].setName(team[i]);
		}

		int m = in.readInt();
		HashSet hs = new HashSet();
		while (m-- > 0) {
			String s = in.readString() + " " + in.readString();
			String a = s.substring(0, s.indexOf(" "));
			String b = s.substring(s.indexOf(" ") + 1, s.length());
			int c = in.readInt();
			int d = in.readInt();
			int t = getIndex(a);
			int u = getIndex(b);
			try {
				if (hs.contains(s) || a.equalsIgnoreCase(b)) {
					throw new Exception();
				}
			} catch (Exception e) {
				System.out.println("Invalid Input");
				v = true;
			}
			hs.add(s);

			//Team A
			int p = 1;
			ob[t].setGf(c);
			if (c > d)
				p = 2;
			else if (c < d)
				p = 0;
			ob[t].setPoints(p);
			ob[t].setGa(d);
			ob[t].setGd(c - d);

			//Team B
			t = u;
			p = 1;
			ob[t].setGf(d);
			if (c < d)
				p = 2;
			else if (c > d)
				p = 0;
			ob[t].setPoints(p);
			ob[t].setGa(c);
			ob[t].setGd(d - c);
		}

		//Do not change anything after this

		List<Football> footballList = new ArrayList<>();
		for (i = 0; i < n; i++)
			footballList.add(ob[i]);

		Collections.sort(footballList, new Comparator<Football>() {
			@Override
			public int compare(final Football record1, final Football record2) {
				int c;
				c = (record2.getPoints() + "").compareTo(record1.getPoints() + "");
				if (c == 0)
					c = (record2.getGd() + "").compareTo(record1.getGd() + "");
				if (c == 0)
					c = (record2.getGf() + "").compareTo(record1.getGf() + "");
				if (c == 0)
					c = record1.getName().compareToIgnoreCase(record2.getName());
				return c;
			}
		});

		if (!v)
			for (i = 0; i < footballList.size(); i++)
				System.out.println(footballList.get(i).getName());
	}

	private static class Football {

		private String name;
		private int points;
		private int gf;
		private int ga;
		private int gd;

		public Football(String name, int points, int gf, int ga) {
			this.name = name;
			this.points = points;
			this.gf = gf;
			this.ga = ga;
			this.gd = gf - ga;
		}

		public String getName() {
			return name;
		}

		public int getPoints() {
			return points;
		}

		public int getGf() {
			return gf;
		}

		public int getGa() {
			return ga;
		}

		public int getGd() {
			return gd;
		}

		public void setName(String name) {
			this.name = name;
		}

		public void setPoints(int points) {
			this.points += points;
		}

		public void setGf(int gf) {
			this.gf += gf;
		}

		public void setGa(int ga) {
			this.ga += ga;
		}

		public void setGd(int gd) {
			this.gd += gd;
		}
	}

	//FAST I/O
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
