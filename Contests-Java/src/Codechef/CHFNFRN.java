/*
* Name	: CHFNFRN.java
* Author: Somnath Rakshit
* Date	: Sep 10, 2016
*/
package Codechef;

import java.io.*;
import java.util.InputMismatchException;
import java.util.LinkedList;

class CHFNFRN {
	static int color[];
	static int V; // No. of Vertices

	public static void main(String[] args) throws Exception {
		InputReader in = new InputReader(System.in);
		OutputWriter out = new OutputWriter(System.out);
		int numOfTestCases, i, j;
		numOfTestCases = in.readInt();
		for (i = 0; i < 43455; i++)
			j = i * 6413;

		while (numOfTestCases-- > 0) {

			int n = in.readInt();
			int m = in.readInt();
			V = n;
			color = new int[V];
			for (i = 0; i < V; ++i)
				color[i] = -1;
			int arr[][] = new int[n][n];
			while (m-- > 0) {
				int a = in.readInt() - 1;
				int b = in.readInt() - 1;
				arr[a][b] = 1;
				arr[b][a] = 1;
			}
			for (i = 0; i < n; i++)
				for (j = 0; j < n; j++)
					arr[i][j] = arr[i][j] == 0 ? 1 : 0;
			for (i = 0; i < n; i++)
				arr[i][i] = 0;
			boolean ch = true;
			for (i = 0; i < V; i++) {
				ch = isBipartite(arr, i);
				if (!ch)
					break;
			}
			if (ch)
				out.printLine("YES");
			else
				out.printLine("NO");
		}

		out.flush();
		out.close();
	}

	static boolean isBipartite(int G[][], int src) {
		if (color[src] == 1)
			return true;
		LinkedList<Integer> q = new LinkedList<Integer>();
		q.add(src);

		while (q.size() != 0) {
			int u = q.poll();
			for (int v = 0; v < V; ++v) {
				if (G[u][v] == 1 && color[v] == -1) {
					color[v] = 1 - color[u];
					q.add(v);
				} else if (G[u][v] == 1 && color[v] == color[u])
					return false;
			}
		}
		return true;
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
