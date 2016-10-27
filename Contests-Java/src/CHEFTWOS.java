    /*
    * Name	: CHEFTWOS.java
    * Author: Somnath Rakshit
    * Date	: Oct 16, 2016
    */

    import java.io.*;
    import java.util.InputMismatchException;

    class CHEFTWOS {
        static int MOD = 1000000007;

        public static void main(String[] args) throws Exception {
            InputReader in = new InputReader(System.in);
            OutputWriter out = new OutputWriter(System.out);
            int numOfTestCases, i, n, c, t, x, y, z;
            numOfTestCases = in.readInt();
            char p, q, r, e, f, g, temp;
            boolean v;

            while (numOfTestCases-- > 0) {
                char a[] = in.readString().trim().toCharArray();
                char b[] = in.readString().trim().toCharArray();
                c = 0;
                n = a.length;
                v = true;
                if (n == 1 || n == 2) {
                    if (a[0] == '2' || b[0] == '2' || a[1] == '2' || b[1] == '2') {
                        out.printLine(0);
                        continue;
                    }

                }
                if (a[a.length - 1] == '2' || a[a.length - 2] == '2' || b[b.length - 1] == '2' || b[b.length - 2] == '2') {
                    out.printLine(0);
                    continue;
                }
                for (i = 0; i < n; i++)
                    if (a[i] == b[i])
                        c++;
                c = powermod(2, c, MOD);

                for (i = 0; i < n - 2; i++) {
                    if (a[i] == '2' || b[i] == '2') {
                        e = a[i];
                        f = a[i + 1];
                        g = a[i + 2];
                        p = b[i];
                        q = b[i + 1];
                        r = b[i + 2];
                        x = y = z = 0;
                        if (e == '2') x++;
                        if (p == '2') x++;
                        if (f == '2') y++;
                        if (q == '2') y++;
                        if (g == '1') z++;
                        if (r == '1') z++;
                        t = x * y * z;
                        t = powermod(2, t, MOD);
                        c = add_mod(c, t, MOD);
                        i += 2;
                        if (t == 0)
                            v = false;
                    }
                }
                if (v)
                    out.printLine(c);
                else
                    out.printLine(0);
            }

            out.flush();
            out.close();
        }

        public static boolean checkGood(char[] arr) {
            boolean v = true;
            if (arr[arr.length - 2] == '2' || arr[arr.length - 1] == '2')
                v = false;
            for (int i = 0; i < arr.length - 3 && v; i++) {
                if (arr[i] == '2') {
                    if (arr[i + 1] != '2' || arr[i + 2] != '1')
                        v = false;
                    else
                        i += 2;
                }
            }
            return v;
        }

        public static int powermod(int base, int exponent, int modulus) {
            if (base < 1 || exponent < 0 || modulus < 1)
                return -1;

            int result = 1;
            while (exponent > 0) {
                if ((exponent % 2) == 1) {
                    result = (result * base) % modulus;
                }
                base = (base * base) % modulus;
                exponent = exponent / 2;
            }
            return result;
        }

        public static int add_mod(int a, int b, int m) {
            if (0 == b) return a;

            // return sub_mod(a, m-b, m);
            b = m - b;
            if (a >= b)
                return a - b;
            else
                return m - b + a;
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
