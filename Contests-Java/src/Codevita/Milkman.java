package Codevita;

import java.util.*;

class Milkman {
    static int INT_MAX = 999999;

    static int minCoins(int a[], int N, int S) {
        int min[] = new int[S + 1];
        int i, j;
        for (i = 0; i <= S; i++)
            min[i] = INT_MAX; // start with extremly large value

        min[0] = 0;
        for (i = 1; i <= S; i++) {
            for (j = 0; j < N; j++) {
                if (i >= a[j]) {
                    if (min[i - a[j]] + 1 < min[i])
                        min[i] = min[i - a[j]] + 1;
                }
            }
        }

        if (min[S] == INT_MAX)
            return -1;

        return min[S];
    }

    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);
        int a[] = {
                1,
                5,
                7,
                10
        };
        int size = a.length;
        int t, n;
        t = sc.nextInt();
        while (t-- > 0) {
            n = sc.nextInt();
            int d = minCoins(a, size, n);
            System.out.println(d);
        }
    }
}