package Codevita;

import java.io.*;

class Compounding {
    public static void main(String[] args) {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        try {
            double p = Double.parseDouble(br.readLine().trim());
            double r = Double.parseDouble(br.readLine().trim());
            int t = Integer.parseInt(br.readLine().trim());
            double ans = 0;
            for (int i = t; i >= 1; i--) {
                double a = get11(1 + r / 1200);
                a = get11(Math.pow(a, i));
                a = get11(p * a);
                ans += a;
            }
            double a = get11(ans);
            a = Math.round(a);
            System.out.println("Final_Amount " + (long) a);
        } catch (Exception e) {
            System.out.println("Invalid Input");
        }
    }

    public static double get11(double a) {
        String s = (String.format("%.11f", a));
        double x = Double.parseDouble(s);
        return x;
    }
}