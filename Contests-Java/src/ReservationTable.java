/*
* Name	: ReservationTable.java
* Author: Somnath Rakshit
* Date	: Sep 15, 2016
*/

import java.io.PrintWriter;
import java.util.*;

class ReservationTable {
	static Scanner in = new Scanner(System.in);
	static PrintWriter out = new PrintWriter(System.out);
	static HashMap<String, Integer> hm = new HashMap<>();
	static int iter;

	public static void main(String args[]) throws Exception {
		TreeSet<Integer> ts = new TreeSet<>();
		int i = 0;
		iter = 0;
		int row = in.nextInt();
		int column = in.nextInt();
		int reservationTable[][] = getInput(row, column);
		ts = buildTS(ts, reservationTable, row, column);
		Integer tsList[] = new Integer[ts.size()];
		for (Integer temp : ts)
			tsList[i++] = temp;
		ArrayList<Integer> icv = getICV(tsList);
		out.println("ICV: " + icv);
		out.println("Forbidden: " + ts);
		out.println("Permissible: " + getPermissible(tsList, 1, column));
		//hm.put(icv,0);
		solve(icv);
		out.println(iter);
		Iterator it = hm.entrySet().iterator();
		while (it.hasNext()) {
			Map.Entry pair = (Map.Entry) it.next();
			out.println("State : " + pair.getValue() + " = " + pair.getKey());
			it.remove();
		}
		out.close();
	}

// --Commented out by Inspection START (17/9/16 11:45 PM):
//	public static ArrayList<Integer> intToList(int array[]) {
//		ArrayList<Integer> list = new ArrayList<Integer>(array.length);
//		for (int i = 0; i < array.length; i++)
//			list.add(Integer.valueOf(array[i]));
//		return list;
//	}
// --Commented out by Inspection STOP (17/9/16 11:45 PM)

	public static ArrayList<Integer> copyArrayList(ArrayList<Integer> oldList) {
		ArrayList<Integer> newList = new ArrayList<>();

		for (Integer anOldList : oldList) {
			newList.add(anOldList);
		}
		return newList;
	}

	public static void solve(ArrayList<Integer> icv) {
		ArrayList<Integer> arr = copyArrayList(icv);
		if (!arr.contains(0))
			return;
		int i = 1;
		for (Integer e : icv) {
			i++;
			if (e == 0) {
				arr = leftShift(arr, i);
				//out.println(icv + "\n" + arr + " ");
				int j = 0;
				for (Integer b : arr) {
					arr.set(j, b | icv.get(j));
					j++;
				}
				if (!hm.containsKey(arr)) {
					hm.put(arr, iter);
					iter++;
					solve(arr);
				}
			}
		}
	}

	public static ArrayList<Integer> leftShift(ArrayList<Integer> arr, int n) {
		if (arr.size() < 2)
			return arr;
		int i;
		for (i = 0; i < n; i++)
			arr.remove(0);
		for (i = 0; i < n; i++)
			arr.add(0);
		return arr;
	}

	public static ArrayList<Integer> getPermissible(Integer[] a, int first, int last) {
		last--;
		ArrayList<Integer> arrayList = new ArrayList<>();
		for (int i = first; i < a[0]; i++) {
			arrayList.add(i);
		}
		for (int i = 1; i < a.length; i++) {
			for (int j = 1 + a[i - 1]; j < a[i]; j++) {
				arrayList.add(j);
			}
		}
		for (int i = 1 + a[a.length - 1]; i <= last; i++) {
			arrayList.add(i);
		}
		return arrayList;
	}

//	public static int getIndex(TreeSet<Integer> set, int element) {
//		return set.contains(element) ? set.headSet(element).size() : -1;
//	}

	public static int[][] getInput(int r, int c) {
		int i, j;

		int arr[][] = new int[r][c];
		for (i = 0; i < r; i++)
			for (j = 0; j < c; j++)
				arr[i][j] = in.nextInt();
		return arr;
	}

	public static TreeSet<Integer> buildTS(TreeSet<Integer> ts, int arr[][], int r, int c) {
		int i, j, k;
		for (i = 0; i < r; i++)
			for (j = 0; j < c; j++)
				for (k = j + 1; k < c && arr[i][j] == 1; k++)
					if (arr[i][k] == 1)
						ts.add(k - j);
		return ts;
	}

	public static ArrayList<Integer> getICV(Integer list[]) {
		int i, max = list[list.length - 1];
		ArrayList<Integer> latency = new ArrayList<>();
		for (i = 0; i < max; i++)
			latency.add(0);
		for (i = 0; i < list.length; i++)
			latency.set(list[i] - 1, 1);
		/*
		for (i = 0; i < latency.length / 2; i++) {
			t = latency[i];
			latency[i] = latency[latency.length - i - 1];
			latency[latency.length - i - 1] = t;
		}
		*/
		return latency;
	}
}
