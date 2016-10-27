package Codechef;
/*
* Name: Codechef.CHEFELEC.java
* Author: Somnath Rakshit
* Date: Jul 10, 2016
*/

import java.io.*;
import java.util.InputMismatchException;

class CHEFELEC {
    static int a = 0, b = 0;

    public static void main(String[] args) throws Exception {
        InputReader in = new InputReader(System.in);
        OutputWriter out = new OutputWriter(System.out);
        int numOfTestCases, i, j, l, prev, next;
        numOfTestCases = in.readInt();

        while (numOfTestCases-- > 0) {
            l = in.readInt();

            int cost;
            char val[] = in.readString().toCharArray();
            int pos[] = in.readIntArray(l);
            int left = 0, right = 0, dist_left = 0, dist_right = 0;
            boolean v = false;

            a = 0;
            b = l - 1;

            cost = getEndCost(val, pos, l);
            //out.printLine("Cost= " + cost + " a= " + a + " b= " + b);

            for (i = a + 1; i <= b; i++) {
                if (val[i] == '0') {
                    for (j = i; j >= a; j--)
                        if (val[j] == '1') {
                            left = j;
                            break;
                        }

                    for (j = i; j <= b; j++)
                        if (val[j] == '1') {
                            right = j;
                            break;
                        }
                    dist_left = pos[i] - pos[left];
                    dist_right = pos[right] - pos[i];

                    if (dist_left < dist_right) {
                        cost += dist_left;
                        for (j = i; j >= left; j--)
                            val[j] = '1';
                    } else {
                        cost += dist_right;
                        for (j = i; j <= right; j++)
                            val[j] = '1';
                    }
                }

            }

            out.printLine(cost);
        }

        out.flush();
        out.close();
    }

    public static int getEndCost(char val[], int pos[], int l) {
        int i, next = 1, cost = 0;

        if (val[0] == '0') {
            for (i = 1; i < l; i++)
                if (val[i] == '1') {
                    next = pos[i];
                    a = i;
                    break;
                }
            cost += next - pos[0];
        }

        if (val[l - 1] == '0') {
            for (i = l - 1; i >= 0; i--)
                if (val[i] == '1') {
                    next = pos[i];
                    b = i;
                    break;
                }
            cost += pos[l - 1] - next;
        }
        return cost;
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

        public void printCharArray(char objects[]) {
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