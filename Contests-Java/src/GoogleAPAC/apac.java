package GoogleAPAC;
/*
* Name: GoogleAPAC.apac.java
* Author: Somnath Rakshit
* Date: Jul 10, 2016
*/

import java.io.*;
import java.util.*;

class apac {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new FileReader("/home/somnath/Downloads/A-large.in"));
        PrintWriter writer = new PrintWriter("/home/somnath/Downloads/A-large.out", "UTF-8");
        int n, i, j, k, l, s, t = Integer.parseInt(br.readLine().trim());
        int ms = 0, max = 0;
        char ch;

        for (i = 1; i <= t; i++) {

            n = Integer.parseInt(br.readLine().trim());
            String arr[] = new String[n];
            for (j = 0; j < n; j++)
                arr[j] = br.readLine().trim();

            Arrays.sort(arr);
            ms=0;
            max=0;

            for (j = 0; j < n; j++) {

                l = arr[j].length();
                HashSet hs = new HashSet();

                for (k = 0; k < l; k++) {
                    ch = arr[j].charAt(k);
                    if (ch != ' ')
                        hs.add(ch);
                }
                s = hs.size();
                if (s > ms) {
                    ms = s;
                    max = j;
                }
            }

            //System.out.println("Max= "+max);
            writer.println("Case #" + i + ": " + arr[max]);
        }
        writer.close();
    }
}