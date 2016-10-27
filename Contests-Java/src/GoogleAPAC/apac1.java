package GoogleAPAC;

import java.io.*;
import java.util.InputMismatchException;
import java.util.Scanner;

class apac1 {
	public static void main(String[] args) throws Exception {
		Scanner sc = new Scanner(new File("/home/somnath/Downloads/A-large (1).in"));
		PrintWriter out = new PrintWriter("/home/somnath/Downloads/ans.out", "UTF-8");
		int numOfTestCases, i;
		numOfTestCases = sc.nextInt();

		for (i = 1; i <= numOfTestCases; i++) {
			long l = sc.nextLong();
			long r = sc.nextLong();
			long n = Math.min(l, r);
			long ans = n * (n + 1) / 2;
			out.println("Case #" + i + ": " + ans);
		}

		out.flush();
		out.close();
	}
}