package codechef;

import java.io.*;
import java.util.Hashtable;

public class divmac {
//    private static int[] a;
    private static Hashtable<Integer, Integer> arr;
    private static final Hashtable<Integer, Integer> primes =
            new Hashtable<>();
    private static long t_lpd = 0, t_check_even = 0, t_check_prime = 0;

    private static void initialize_primes() {
        long limit = 1000000;
        long sqrt_n = (long) Math.sqrt(limit) + 1;
        primes.put(0, 1);
        primes.put(1, 1);
        primes.put(2, 1);
        for ( int i = 3; i <= limit; i+=2) {
            primes.put(i, 1);
        }
        for ( int i = 3; i < sqrt_n; i+=2) {
            if (primes.containsKey(i) && primes.get(i) == 1) {
                int j = 3;
                int result = j * i;
                while ( j <= limit && result <= limit ) {
                    if ( primes.containsKey(result) && primes.get(result) == 1)
                        primes.put(result, i);
                    j += 2;
                    result = i * j;
                }
            }
        }
    }

    private static int least_prime_divisor(int n) {
        long s_millis = System.currentTimeMillis();
        if ( n == 0 || n == 1 ) {
            long e_millis = System.currentTimeMillis();
            t_lpd += (e_millis - s_millis);
            return 1;
        } else if ( n % 2 == 0 ) {
            long e_millis = System.currentTimeMillis();
            t_lpd += (e_millis - s_millis);
            return 2;
        } else {
            if ( primes.containsKey(n) ) {
                if (primes.get(n) == 1){
                    long e_millis = System.currentTimeMillis();
                    t_lpd += (e_millis - s_millis);
                    return n;
                } else  {
                    long e_millis = System.currentTimeMillis();
                    t_lpd += (e_millis - s_millis);
                    return primes.get(n);
                }
            } else {
                long e_millis = System.currentTimeMillis();
                t_lpd += (e_millis - s_millis);
                return 1;
            }
        }
    }

    private static void update(int L, int R) {
        for ( int i = L-1; i < R; i++ ) {
            int x = arr.get(i);
            if (x == 0 || x == 1) {
                continue;
            } else if ( x % 2 == 0 ) {
                long s_millis = System.currentTimeMillis();
                arr.put(i, x >> 1);
                long e_millis = System.currentTimeMillis();
                t_check_even += (e_millis - s_millis);
                continue;
            } else if (primes.containsKey(x) && primes.get(x) == 1) {
                arr.put(i, 1);
                continue;
            }
//            a[i] = a[i] / least_prime_divisor(a[i]);
            arr.put(i, x/least_prime_divisor(x));
        }
    }

    private static int get(int L, int R) {
        int result = 1;
        for ( int i = L-1; i < R; i++ ) {
            int x = arr.get(i);
            if ( x == 1 || x == 0 ) {
                // Do nothing
            } else if (x % 2 == 0 ) {
                long s_millis = System.currentTimeMillis();
                result = Math.max(result, 2);
                long e_millis = System.currentTimeMillis();
                t_check_even += (e_millis - s_millis);
            } else if (primes.containsKey(x) && primes.get(x) == 1) {
                long s_millis = System.currentTimeMillis();
                result = Math.max(result, x);
                long e_millis = System.currentTimeMillis();
                t_check_prime += (e_millis - s_millis);
            } else {
                result = Math.max(result, least_prime_divisor(x));
            }
        }
        return result;
    }

    public static void main(String[] args) {
        try {
            int T, N, M, i;
            long t_update = 0, t_get = 0, t_sb = 0;
            String[] N_M, n_numbers, query;
            initialize_primes();
            StringBuilder sb;
            BufferedReader brdr = new BufferedReader(
                    new InputStreamReader(System.in));
            BufferedWriter bwtr = new BufferedWriter(
                    new OutputStreamWriter(System.out));
            T = Integer.parseInt(brdr.readLine());
            while ( T > 0 ) {
                N_M = brdr.readLine().split(" ");
                N = Integer.parseInt(N_M[0]);
                M = Integer.parseInt(N_M[1]);
                sb = new StringBuilder();
                i = 0;
//                a = new int[N];
                arr = new Hashtable<>();
                n_numbers = brdr.readLine().split(" ");
                for (String s: n_numbers) {
//                    a[i] = Integer.parseInt(s);
                    arr.put(i, Integer.parseInt(s));
                    i++;
                }
                for (int j = 0; j < M; j++) {
                    query = brdr.readLine().split(" ");
                    if ( query[0].equals("0") ) {
                        long s_millis = System.currentTimeMillis();
                        update(Integer.parseInt(query[1]),
                                Integer.parseInt(query[2]));
                        long e_millis = System.currentTimeMillis();
                        t_update += (e_millis - s_millis);
                    } else {
                        long s_millis = System.currentTimeMillis();
                        String res = get(Integer.parseInt(query[1]),
                                Integer.parseInt(query[2])) + " ";
                        long e_millis = System.currentTimeMillis();
                        t_get += (e_millis - s_millis);
                        s_millis = System.currentTimeMillis();
                        sb.append(res);
                        e_millis = System.currentTimeMillis();
                        t_sb += (e_millis - s_millis);
                    }
                }
                bwtr.write(sb.toString());
                bwtr.flush();
                bwtr.write("\n");
                T--;
            }
            System.out.println("\n\nUpdate: " + t_update +
                    ", Get: " + t_get +
                    ", SB: " + t_sb +
                    ", LPD: " + t_lpd +
                    ", \n Check Even: " + t_check_even);
        } catch(IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
}
