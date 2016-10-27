package Codechef;

import java.util.Scanner;

class SegmentTree {
	int st[];
	static boolean prime[];

	public static void main(String args[]) {
		Scanner sc = new Scanner(System.in);
		int i, numOfTestCases = sc.nextInt();
		prime = new boolean[1000001];
		sieveOfEratosthenes(1000000);
		while (numOfTestCases-- > 0) {
			int n = sc.nextInt(), m = sc.nextInt();
			int arr[] = new int[n];
			for (i = 0; i < n; i++) {
				arr[i] = sc.nextInt();

			}
			SegmentTree tree = new SegmentTree(arr, n);
			while (m-- > 0) {
				int a = sc.nextInt(), b = sc.nextInt() - 1, c = sc.nextInt() - 1;

				if (a == 1) {
					System.out.print(tree.getSum(n, b, c) + " ");
				} else {
					for (i = b; i <= c; i++)
						tree.updateValue(arr, n, i, arr[i] / leastPrimeDivisor(arr[i]));
				}
			}
			System.out.println();
		}
	}

	SegmentTree(int arr[], int n) {
		// Allocate memory for segment tree
		//Height of segment tree
		int x = (int) (Math.ceil(Math.log(n) / Math.log(2)));

		//Maximum size of segment tree
		int max_size = 2 * (int) Math.pow(2, x) - 1;

		st = new int[max_size]; // Memory allocation

		constructSTUtil(arr, 0, n - 1, 0);
	}

	int getMid(int s, int e) {
		return s + (e - s) / 2;
	}

	int getSum(int n, int as, int ae) {
		return getSumUtil(0, n - 1, as, ae, 0);
	}

	int getSumUtil(int ss, int se, int as, int ae, int si) {
		// If segment of this node is a part of given range, then return
		// the sum of the segment
		if (as <= ss && ae >= se)
			return st[si];

		// If segment of this node is outside the given range
		if (se < as || ss > ae)
			return Integer.MIN_VALUE;

		// If a part of this segment overlaps with the given range
		int mid = getMid(ss, se);
		return Math.max(getSumUtil(ss, mid, as, ae, 2 * si + 1),
				getSumUtil(mid + 1, se, as, ae, 2 * si + 2));
	}

	void updateValueUtil(int ss, int se, int i, int si) {
		// Base Case: If the input index lies outside the range of
		// this segment
		if (i < ss || i > se)
			return;

		// If the input index is in range of this node, then update the
		// value of the node and its children
		st[si] = Math.max(st[2 * si], st[2 * si + 1]);
		if (se != ss) {
			int mid = getMid(ss, se);
			updateValueUtil(ss, mid, i, 2 * si + 1);
			updateValueUtil(mid + 1, se, i, 2 * si + 2);
		}
	}

	void updateValue(int arr[], int n, int i, int new_val) {

		// Update the value in array
		arr[i] = arr[i] / leastPrimeDivisor(arr[i]);

		// Update the values of nodes in segment tree
		updateValueUtil(0, n - 1, i, 0);
	}

	int constructSTUtil(int arr[], int as, int ae, int si) {
		// If there is one element in array, store it in current node of
		// segment tree and return
		if (as == ae) {
			st[si] = arr[as];
			return arr[as];
		}

		// If there are more than one elements, then recur for left and
		// right subtrees and store the sum of values in this node
		int mid = getMid(as, ae);
		st[si] = Math.max(constructSTUtil(arr, as, mid, si * 2 + 1), constructSTUtil(arr, mid + 1, ae, si * 2 + 2));
		return st[si];
	}

	static void sieveOfEratosthenes(int n) {
		// Create a boolean array "prime[0..n]" and initialize
		// all entries it as true. A value in prime[i] will
		// finally be false if i is Not a prime, else true.
		for (int i = 0; i < n; i++)
			prime[i] = true;

		for (int p = 2; p * p <= n; p++) {
			// If prime[p] is not changed, then it is a prime
			if (prime[p] == true) {
				// Update all multiples of p
				for (int i = p * 2; i <= n; i += p)
					prime[i] = false;
			}
		}
	}

	static int leastPrimeDivisor(int n) {
		if (prime[n])
			return n;
		else if (n % 2 == 0)
			return 2;
		else {
			for (int i = 3; i <= n / 2; i += 2)
				if (prime[i] == true && n % i == 0)
					return i;
		}
		return n;
	}
}