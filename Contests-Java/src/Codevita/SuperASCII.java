package Codevita;
/*
* Name: Codevita.SuperASCII.java
* Author: Somnath Rakshit
* Date: Jul 18, 2016
*/

import java.io.*;
import java.util.Arrays;
import java.util.HashSet;
import java.util.InputMismatchException;

class SuperASCII {
    static int extra = 0;
    static int cost = 0;
    static int length = 0;

    public static void main(String[] args) throws Exception {
        InputReader in = new InputReader(System.in);
        OutputWriter out = new OutputWriter(System.out);
        int numOfTestCases, i;
        numOfTestCases = in.readInt();

        while (numOfTestCases-- > 0) {
            cost = 0;
            extra = 0;
            length = 0;

            String s = in.readString().trim();
            char arr[] = s.toCharArray();
            Arrays.sort(arr);

            int c[] = new int[26];
            for (i = 0; i < 26; i++)
                c[i] = count(s, (char) (97 + i));

            setRequiredLength(arr);
            setAddCost(arr);
            extra = getExtra(c);
            setReplaceCost(c);
            setDeleteCost(arr);

            out.printLine(cost);
        }

        out.flush();
        out.close();
    }

    public static int count(String line, char ch) {
        return (line.length() - line.replace(ch + "", "").length());
    }

    public static void setRequiredLength(char arr[]) {
        HashSet hs = new HashSet();
        length = 0;
        for (char ch : arr)
            hs.add(ch);

        Character[] t = new Character[hs.size()];
        t = (Character[]) hs.toArray(t);
        for (char ch : t)
            length += (int) (ch) - 96;
    }

    public static void setAddCost(char arr[]) {
        int a = arr.length - length;
        if (a < 0)
            cost += 2 * (-1) * a;
    }

    public static void setDeleteCost(char arr[]) {
        int a = arr.length - length;
        if (a > 0)
            cost += 3 * a;
    }

    public static int getExtra(int c[]) {
        int extra = 0;
        for (int i = 0; i < 26; i++) {
            int a = c[i] - i - 1;
            if (a > 0 && c[i] > 0)
                extra += a;
        }
        return extra;
    }

    public static void setReplaceCost(int c[]) {
        if (extra == 0)
            return;
        for (int i = 0; i < 26; i++) {
            int a = c[i] - (i + 1);
            if (a < 0 && c[i] > 0) {
                if (extra >= a) {
                    extra += a;
                    cost += (-1) * a;
                }
            }
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
            for (int i : objects) {
                writer.print(i);
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
