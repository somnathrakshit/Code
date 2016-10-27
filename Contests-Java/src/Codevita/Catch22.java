package Codevita;

import java.util.Scanner;

public class Catch22 {
    public static void main(String args[]) {
        int f, b, t, fd, bd, i, j, test, time = 0, loc = 0;
        Scanner sc = new Scanner(System.in);
        test = sc.nextInt();
        while (test-- > 0) {
            f = sc.nextInt();
            b = sc.nextInt();
            t = sc.nextInt();
            fd = sc.nextInt();
            bd = sc.nextInt();
            bd = bd * (-1);
            if (f == b && fd > f) {
                System.out.println("No Ditch");
            } else {
                OUT:
                while (5 > 3) {
                    for (i = 0; i < f; i++) {
                        time += t;
                        loc += 1;
                        if (loc >= fd) {
                            System.out.println(time + " F");

                            break OUT;
                        }
                    }
                    for (i = 0; i < b; i++) {
                        time += t;
                        loc -= 1;
                        if (loc <= bd) {
                            System.out.println(time + " B");

                            break OUT;
                        }
                    }
                }
            }
        }
    }
}