/*
* Name	: RESCALC.java
* Author: Somnath Rakshit
* Date	: Sep 03, 2016


Input:
3
2
6 1 2 3 4 5 6
9 3 3 3 4 4 4 5 5 5
2
5 2 3 4 5 6
7 1 1 2 2 3 3 4
3
4 1 1 2 3
4 1 2 2 3
4 1 2 3 3

Output:
chef
2
tie
 */

package Codechef;

import java.io.*;
import java.util.*;

class RESCALC {
	public static void main(String[] args) throws Exception {
		InputReader in = new InputReader(System.in);
		OutputWriter out = new OutputWriter(System.out);
		int numOfTestCases, i, j, n, s, p, h, sum;
		numOfTestCases = in.readInt();

		while (numOfTestCases-- > 0) {
			n = in.readInt();
			p = 0;
			int res[] = new int[n];
			for (j = 0; j < n; j++) {
				s = in.readInt();
				int arr[] = new int[s];
				int temp[] = new int[7];
				for (i = 0; i < s; i++) {
					arr[i] = in.readInt();
					temp[arr[i]]++;
				}
				Arrays.sort(temp);
				for (i = 1; i < 7; i++)
					if (temp[i] != 0) {
						p = i;
						break;
					}
				int freq[] = new int[7 - p];
				for (i = 0; i < 7 - p; i++)
					freq[i] = temp[i + p];
				h = 0;
				sum = s;
				while (h < freq.length) {
					if (freq[h] == 0) {
						h++;
					} else if ((freq.length - h) >= 6) {
						sum += 4;
						for (i = h; i < h + 6; i++)
							freq[i]--;
					} else if ((freq.length - h) >= 5) {
						sum += 2;
						for (i = h; i < h + 5; i++)
							freq[i]--;
					} else if ((freq.length - h) >= 4) {
						sum += 1;
						for (i = h; i < h + 4; i++)
							freq[i]--;
					} else
						h++;
				}
				res[j] = sum;
			}
			if (n == 1)
				out.printLine("chef");
			else {
				int res2[] = new int[res.length];
				for (i = 0; i < res.length; i++)
					res2[i] = res[i];
				Arrays.sort(res2);
				if (res2[res2.length - 1] == res2[res2.length - 2])
					out.printLine("tie");
				else {
					p = getMaxIndex(res);
					if (p == 0)
						out.printLine("chef");
					else
						out.printLine(p + 1);
				}

			}
		}

		out.flush();
		out.close();
	}

	static int getMaxIndex(final int[] res) {
		int maxIndex = 0;
		for (int i = 1; i < res.length; i++) {
			int newnumber = res[i];
			if ((newnumber > res[maxIndex])) {
				maxIndex = i;
			}
		}
		return maxIndex;
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
			boolean isSpaceChar(int ch);
		}
	}

	private static class OutputWriter {
		private final PrintWriter writer;

		public OutputWriter(OutputStream outputStream) {
			writer = new PrintWriter(new BufferedWriter(new OutputStreamWriter(outputStream)));
		}

		public OutputWriter(Writer writer) {
			this.writer = new PrintWriter(writer);
		}

		public void print(Object... objects) {
			for (int i = 0; i < objects.length; i++) {
				if (i != 0)
					writer.print(' ');
				writer.print(objects[i]);
			}
		}

		public void printIntArray(int objects[]) {
			for (int object : objects) {
				writer.print(object);
				writer.print(' ');
			}
			writer.println();
		}

		public void printLine(Object... objects) {
			print(objects);
			writer.println();
		}

		public void close() {
			writer.close();
		}

		public void flush() {
			writer.flush();
		}

	}
}
