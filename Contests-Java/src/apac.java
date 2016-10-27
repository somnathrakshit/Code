/*
* Name: apac.java
* Author: Somnath Rakshit
* Date: Jul 10, 2016
*/

import java.io.*;
import java.util.*;

class apac {
    public static void main(String[] args) throws Exception {
        Scanner in=new Scanner(new FileReader("/home/somnath/Downloads/A-small-attempt0.in"));
        PrintWriter writer = new PrintWriter("/home/somnath/Downloads/A-small.out", "UTF-8");
        int numOfTestCases, i, n;
        numOfTestCases = in.nextInt();
        double a, b, ans;

        for (i = 1; i <= numOfTestCases; i++) {
            a = in.nextDouble();
            b = in.nextDouble();
            ans = b / (a + b);
            if (b == 0)
                ans = 1;
            else if (a == 0 || (a == 0 && b == 0))
                ans = 0;
            writer.println("Case #" + i + ": " +ans);
        }
        writer.close();
    }
}