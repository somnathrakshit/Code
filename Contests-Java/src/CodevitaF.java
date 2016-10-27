import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;


public class CodevitaF {
	static Football ob[];

	public static int getIndex(String s) {
		int i;
		for (i = 0; i < ob.length; i++)
			if (ob[i].getName() == s)
				break;
		return i;
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		Scanner sc = new Scanner(System.in);
		int i, n = sc.nextInt();
		String team[] = br.readLine().split(" ");
		ob = new Football[n];
		for (i = 0; i < n; i++)
			ob[i].setName(team[i]);

		int m = sc.nextInt();
		while (m-- > 0) {
			String a = sc.next();
			String b = sc.next();
			int c = sc.nextInt();
			int d = sc.nextInt();
			int t = getIndex(a);

			//Team A
			int p = 1;
			ob[t].setGf(c);
			if (c > d)
				p = 2;
			else if (c < d)
				p = 0;
			ob[t].setPoints(p);
			ob[t].setGa(d);
			ob[t].setGd(c - d);

			//Team B
			t = getIndex(b);
			p = 1;
			ob[t].setGf(d);
			if (c < d)
				p = 2;
			else if (c > d)
				p = 0;
			ob[t].setPoints(p);
			ob[t].setGa(c);
			ob[t].setGd(d - c);
		}


		List<Football> footballList = new ArrayList<>();
		for (i = 0; i < n; i++)
			footballList.add(ob[i]);

		//Do not change anything after this

		Collections.sort(footballList, new Comparator<Football>() {
			@Override
			public int compare(final Football record1, final Football record2) {
				int c;
				c = (record2.getPoints() + "").compareTo(record1.getPoints() + "");
				if (c == 0)
					c = (record2.getGd() + "").compareTo(record1.getGd() + "");
				if (c == 0)
					c = (record2.getGf() + "").compareTo(record1.getGf() + "");
				if (c == 0)
					c = record1.getName().compareToIgnoreCase(record2.getName());
				return c;
			}
		});

		for (i = 0; i < footballList.size(); i++)
			System.out.println(footballList.get(i).getName());
	}

	private static class Football {

		private String name;
		private int points;
		private int gf;
		private int ga;
		private int gd;

		public Football(String name, int points, int gf, int ga) {
			this.name = name;
			this.points = points;
			this.gf = gf;
			this.ga = ga;
			this.gd = gf - ga;
		}

		public String getName() {
			return name;
		}

		public int getPoints() {
			return points;
		}

		public int getGf() {
			return gf;
		}

		public int getGa() {
			return ga;
		}

		public int getGd() {
			return gd;
		}

		public void setName(String name) {
			this.name = name;
		}

		public void setPoints(int points) {
			this.points += points;
		}

		public void setGf(int gf) {
			this.gf += gf;
		}

		public void setGa(int ga) {
			this.ga += ga;
		}

		public void setGd(int gd) {
			this.gd += gd;
		}
	}
}