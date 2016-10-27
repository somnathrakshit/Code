/*
* Name	: CHEFCRUN.java
* Author: Somnath Rakshit
* Date	: Aug 13, 2016
*/

package Codechef;

import java.io.*;
import java.util.InputMismatchException;

class CHEFCRUN {
	public static void main(String[] args) throws Exception {
		InputReader in = new InputReader(System.in);
		OutputWriter out = new OutputWriter(System.out);
		int numOfTestCases, i, n, sumRight, sumLeft, start, end, a, b, index;
		numOfTestCases = in.readInt();

		while (numOfTestCases-- > 0) {
			n = in.readInt();
			int arr[] = in.readIntArray(n);
			int count[] = new int[n];
			a = in.readInt();
			b = in.readInt();

			sumRight = 0;
			sumLeft = 0;

			//Travel right
			start = a - 1;
			end = b - 1;
			for (i = start; i != end; i = (i + 1) % n)
				if (count[i] < 2) {
					sumRight += arr[i];
					count[i]++;
				}

			//Travel left
			start = a == 1 ? n - 1 : a - 2;
			end = b == 1 ? n - 1 : b - 2;
			for (i = start; i != end; i = (i + n - 1) % n)
				if (count[i] < 2) {
					sumLeft += arr[i];
					count[i]++;
				}

			//Right left most
			index = (a - 1 + n - 1) % n;
			if (count[index] < 2) {
				sumRight = Math.min(sumRight, sumRight + 2 * arr[index]);
				count[index] += 2;
			}

			//Right right most
			index = b % n;
			if (count[index] < 2)
				sumRight = Math.min(sumRight, sumRight + 2 * arr[index]);

			//Left left most
			index = a - 1;
			if (count[index] < 2) {
				sumLeft = Math.min(sumLeft, sumLeft + 2 * arr[index]);
				count[index] += 2;
			}

			//Left right most
			index = (b - 1 + n - 1) % n;
			if (count[index] < 2)
				sumLeft = Math.min(sumLeft, sumLeft + 2 * arr[index]);

			out.printLine(Math.min(sumLeft, sumRight));
		}

		out.flush();
		out.close();
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
			for (int i = 0; i < objects.length; i++) {
				writer.print(objects[i]);
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
