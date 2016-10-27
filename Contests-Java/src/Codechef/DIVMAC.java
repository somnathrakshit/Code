/*
* Name	: DIVMAC.java
* Author: Somnath Rakshit
* Date	: Sep 08, 2016
*/

package Codechef;

import java.io.*;
import java.util.Hashtable;
import java.util.InputMismatchException;

class DIVMAC {
	private static Hashtable<Integer, Integer> arr;
	private static final Hashtable<Integer, Integer> primes = new Hashtable<>();

	public static void main(String args[]) throws Exception {
		InputReader in = new InputReader(System.in);
		OutputWriter out = new OutputWriter(System.out);
		int numOfTestCases, n, m, i;
		int[] num, q;
		sieve();

		numOfTestCases = in.readInt();
		while (numOfTestCases-- > 0) {
			n = in.readInt();
			m = in.readInt();

			i = 0;
			arr = new Hashtable<>();
			num = in.readIntArray(n);
			for (int s : num) {
				arr.put(i, s);
				i++;
			}

			for (int j = 0; j < m; j++) {
				q = in.readIntArray(3);
				if (q[0] == 0) {
					update(q[1], q[2]);
				} else {
					out.print(get(q[1], q[2]) + " ");
				}
			}
			out.printLine();
		}
		out.flush();
		out.close();
	}

	private static void sieve() {
		long limit = 1000000;
		long sqrt_n = (long) Math.sqrt(limit) + 1;
		primes.put(0, 1);
		primes.put(1, 1);
		primes.put(2, 1);
		for (int i = 3; i <= limit; i += 2) {
			primes.put(i, 1);
		}
		for (int i = 3; i < sqrt_n; i += 2) {
			if (primes.containsKey(i) && primes.get(i) == 1) {
				int j = 3;
				int result = j * i;
				while (j <= limit && result <= limit) {
					if (primes.containsKey(result) && primes.get(result) == 1)
						primes.put(result, i);
					j += 2;
					result = i * j;
				}
			}
		}
	}

	private static int least_prime_divisor(int n) {
		long s_millis = System.currentTimeMillis();
		if (n == 0 || n == 1) {
			return 1;
		} else if (n % 2 == 0) {
			return 2;
		} else {
			if (primes.containsKey(n)) {
				if (primes.get(n) == 1) {
					return n;
				} else {
					return primes.get(n);
				}
			} else {
				return 1;
			}
		}
	}

	private static void update(int L, int R) {
		for (int i = L - 1; i < R; i++) {
			int x = arr.get(i);
			if (x == 0 || x == 1) {
				continue;
			} else if (x % 2 == 0) {
				arr.put(i, x >> 1);
				continue;
			} else if (primes.containsKey(x) && primes.get(x) == 1) {
				arr.put(i, 1);
				continue;
			}
			arr.put(i, x / least_prime_divisor(x));
		}
	}

	private static int get(int L, int R) {
		int result = 1;
		for (int i = L - 1; i < R; i++) {
			int x = arr.get(i);
			if (x == 1 || x == 0) {
				;    //Do nothing
			} else if (x % 2 == 0) {
				result = Math.max(result, 2);
			} else if (primes.containsKey(x) && primes.get(x) == 1) {
				result = Math.max(result, x);
			} else {
				result = Math.max(result, least_prime_divisor(x));
			}
		}
		return result;
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
