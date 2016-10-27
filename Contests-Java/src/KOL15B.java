/*
* Name	: KOL15B.java
* Author: Somnath Rakshit
* Date	: Oct 21, 2016
*/

import java.io.*;
import java.util.InputMismatchException;

class KOL15B {
    static int A[][] = new int[1003][1003];
    static int left[][] = new int[1003][1003];
    static int right[][] = new int[1003][1003];
    static int north[][] = new int[1003][1003];
    static int south[][] = new int[1003][1003];

    public static void main(String[] args) throws Exception {
        InputReader in = new InputReader(System.in);
        OutputWriter out = new OutputWriter(System.out);
        int numOfTestCases, n, m, min1, min2;
        int i, j;
        numOfTestCases = in.readInt();

        while (numOfTestCases-- > 0) {
            n = in.readInt();
            m = in.readInt();
            for (i = 0; i < n; i++) {
                for (j = 0; j < m; j++)
                    A[i][j] = in.readInt();
            }
            min1 = A[0][0];
            for (i = 0; i < n; i++) {
                for (j = 0; j < m; j++) {
                    if (j == 0) right[i][j] = 0;
                    else {
                        if (right[i][j - 1] >= 0) right[i][j] = A[i][j - 1];
                        else right[i][j] = right[i][j - 1] + A[i][j - 1];
                    }
                }
            }
            for (i = 0; i < n; i++) {
                for (j = m - 1; j >= 0; j--) {
                    if (j == m - 1) left[i][j] = 0;
                    else {
                        if (left[i][j + 1] >= 0) left[i][j] = A[i][j + 1];
                        else left[i][j] = left[i][j + 1] + A[i][j + 1];
                    }
                }
            }
            for (j = 0; j < m; j++) {
                for (i = 0; i < n; i++) {
                    if (i == 0) south[i][j] = 0;
                    else {
                        if (south[i - 1][j] >= 0) south[i][j] = A[i - 1][j];
                        else south[i][j] = south[i - 1][j] + A[i - 1][j];
                    }
                }
            }
            for (j = 0; j < m; j++) {
                for (i = n - 1; i >= 0; i--) {
                    if (i == n - 1) north[i][j] = 0;
                    else {
                        if (north[i + 1][j] >= 0) north[i][j] = A[i + 1][j];
                        else north[i][j] = north[i + 1][j] + A[i + 1][j];
                    }
                }
            }
            for (i = 0; i < n; i++) {
                for (j = 0; j < m; j++) {
                    min2 = A[i][j];
                    if (left[i][j] < 0) min2 += left[i][j];
                    if (right[i][j] < 0) min2 += right[i][j];
                    if (north[i][j] < 0) min2 += north[i][j];
                    if (south[i][j] < 0) min2 += south[i][j];
                    if (min1 > min2) min1 = min2;
                }
            }
            out.printLine(min1);
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
