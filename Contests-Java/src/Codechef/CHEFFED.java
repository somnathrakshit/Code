/*
* Name	: CHEFFED.java
* Author: Somnath Rakshit
* Date	: Jul 25, 2016
*/

package Codechef;

import java.io.*;
import java.util.InputMismatchException;
import java.util.Scanner;

class CHEFFED {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt(), c = 0;
		for (int i = n; (i >= n - 20000) && i >= 1; i--) {
			if (i + getDigits(i) + getDigits(getDigits(i)) == n) {
				c++;
			}
		}
		/*
        for(int i=n;(i>=n-2000) && i>=1 ;i--)
        {
            for(int j=1;j<=100 && j>=1;j++){
                for(int k=1;k<=100;k++)
                    if(i+j+k==n){
                        if()
                        c++;
                    }
            }
        }
        */
		System.out.println(c);

	}

	private static int getDigits(int num) {
		int sum = 0;
		while (num > 0) {
			sum = sum + num % 10;
			num = num / 10;
		}
		return sum;
	}

	public static int addDigits(int N) {
		int temp1 = 0, temp2 = 0;
		while (N > 0) {
			temp1 = N % 10;
			temp2 = temp1 + temp2;
			temp2 = getDigits(temp2);
			N = N / 10;
		}
		return temp2;
	}
}
