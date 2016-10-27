/*
* Name	: SavingLives.java
* Author: Somnath Rakshit
* Date	: Oct 20, 2016
*/

import java.io.*;
import java.util.InputMismatchException;

class SavingLives {
    static boolean arr[][];
    static boolean check[][];
    static int n, r, x1, x2, y1, y2;

    public static void main(String[] args) throws Exception {
        InputReader in = new InputReader(System.in);
        OutputWriter out = new OutputWriter(System.out);
        int numOfTestCases, i, j, a, b, c, d, totalPassengers, totalDead;
        numOfTestCases = in.readInt();
        while (numOfTestCases-- > 0) {
            totalPassengers = 0;
            totalDead = 0;
            n = in.readInt();
            r = in.readInt();
            x1 = in.readInt();
            y1 = in.readInt();
            x2 = in.readInt();
            y2 = in.readInt();
            arr = new boolean[n][n];
            check = new boolean[n][n];
            for (i = 0; i < n; i++) {
                for (j = 0; j < n; j++) {
                    arr[i][j] = in.readInt() == 1 ? true : false;
                    if (arr[i][j])
                        totalPassengers++;
                }
            }
            a = x1 - r < 0 ? 0 : x1 - r;
            b = x1 + r >= n ? n - 1 : x1 + r;
            c = y1 - r < 0 ? 0 : y1 - r;
            d = y1 + r >= n ? n - 1 : y1 + r;
            for (i = c; i <= d; i++)
                for (j = a; j <= b; j++)
                    if (arr[i][j] && checkDead(i, j, 1)) {
                        check[i][j] = true;
//                      out.printLine(i + " " + j);
                        totalDead++;
                    }
            a = x2 - r < 0 ? 0 : x2 - r;
            b = x2 + r >= n ? n - 1 : x2 + r;
            c = y2 - r < 0 ? 0 : y2 - r;
            d = y2 + r >= n ? n - 1 : y2 + r;
            for (i = c; i <= d; i++)
                for (j = a; j <= b; j++)
                    if (arr[i][j] && checkDead(i, j, 2) && !check[i][j]) {
                        check[i][j] = true;
//                        out.printLine(i + " " + j);
                        totalDead++;
                    }
            out.printLine(totalPassengers - totalDead);
        }

        out.flush();
        out.close();
    }

    static boolean checkDead(int i, int j, int num) {
        if (num == 1)
            return ((x1 - i) * (x1 - i) + (y1 - j) * (y1 - j) <= r * r) ? true : false;
        else
            return ((x2 - i) * (x2 - i) + (y2 - j) * (y2 - j) <= r * r) ? true : false;
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
