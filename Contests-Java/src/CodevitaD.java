//Author: Soumik Sarkar

import java.io.*;
import java.util.*;
import java.math.*;

class CodevitaD {
	public static void main(String args[]) throws Exception {
		Reader re = new Reader(System.in);
		String b[] = re.next().split("/");  // split FEN notation into rows
		boolean p = re.next().equals("w");  // p denotes player is white

		char board[][] = new char[8][8];
		for (int i = 0; i < 8; i++) {
			String r = b[i];        // r = ith row
			int j = 0;
			for (char c : r.toCharArray()) {  // iterating over chars in r
				if (c >= '1' && c <= '8') {       // if char is a digit, denotes that many consecutive blanks
					while (c-- > '0')
						board[i][j++] = ' ';
				} else {
					// if char is a piece belonging to the player, store it in caps for convenience
					if ((p && Character.isUpperCase(c)) || (!p && Character.isLowerCase(c)))
						board[i][j++] = Character.toUpperCase(c);

						// if char is a piece belonging to opponent
						// pretend it is blank (because over can move to that place by capturing)
					else
						board[i][j++] = ' ';
				}
			}
		}

		ArrayList<String> moves = new ArrayList<>();
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				if (board[i][j] == 'N') {       // if current position has a knight
					int r = 8 - i;
					char c = (char) (j + 'a');
					String from = "" + c + r;
					// check all 8 possible moves
					checkAdd(from, i - 2, j - 1, board, moves);
					checkAdd(from, i - 2, j + 1, board, moves);
					checkAdd(from, i - 1, j - 2, board, moves);
					checkAdd(from, i - 1, j + 2, board, moves);
					checkAdd(from, i + 1, j - 2, board, moves);
					checkAdd(from, i + 1, j + 2, board, moves);
					checkAdd(from, i + 2, j - 1, board, moves);
					checkAdd(from, i + 2, j + 1, board, moves);
				}
			}
		}

		System.out.println(moves);
	}

	static void checkAdd(String from, int i1, int j1, char board[][], ArrayList<String> moves) {
		if (i1 >= 0 && i1 < 8 && j1 >= 0 && j1 < 8) {
			if (board[i1][j1] == ' ') {
				int r = 8 - i1;
				char c = (char) (j1 + 'a');
				from = from + c + r;
				moves.add(from);  // store only the valid moves
			}
		}
	}
}

class Reader {
	BufferedReader br;
	StringTokenizer st;

	Reader(InputStream in) throws Exception {
		br = new BufferedReader(new InputStreamReader(in));
		st = new StringTokenizer("");
	}

	String next() throws Exception {
		while (!st.hasMoreTokens())
			st = new StringTokenizer(br.readLine());
		return st.nextToken();
	}

	int nextInt() throws Exception {
		return Integer.parseInt(next());
	}

	long nextLong() throws Exception {
		return Long.parseLong(next());
	}
}