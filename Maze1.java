import java.awt.Color;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Maze1 {
	static PrintWriter out;
	static BufferedReader in;
	public static Socket socket;
	public static int N; // dimension of maze
	public static boolean[][] north; // is there a wall to north of cell i, j
	public static boolean[][] east;
	public static boolean[][] south;
	public static boolean[][] west;
	private boolean[][] visited;
	public static JFrame frame = new JFrame("Maze Game Client Score");
    public static JLabel msg1 = new JLabel("");
    public static JPanel boardPanel;
    

	static int p = 1;
	static int q = 5;
	public static int count = 0;
	public static boolean[][] vis = {
			{ true, true, true, true, true, true, true },
			{ true, true, true, true, true, false, true },
			{ true, true, true, true, true, true, true },
			{ true, true, true, true, true, true, true },
			{ true, true, true, true, true, true, true },
			{ true, true, true, true, true, true, true },
			{ true, true, true, true, true, true, true } };

	// private boolean done = false;
	public Maze1() {

	}

	public Maze1(int N, Socket socket) {
		this.N = N;
		this.socket = socket;
		StdDraw.setXscale(0, N + 2);
		StdDraw.setYscale(0, N + 2);
		try {
			out = new PrintWriter(socket.getOutputStream(), true);
			out.println("Welcome Client");
			in = new BufferedReader(new InputStreamReader(
					socket.getInputStream()));
			msg1.setBackground(Color.GRAY);
	        frame.getContentPane().add(msg1, "Center");
	    	boardPanel = new JPanel();
	    	boardPanel.setBackground(Color.WHITE);
	    	msg1.setText("Server Score is 0");
	    	boardPanel.add(msg1);
	        frame.add(boardPanel);
	        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			frame.setSize(300,300);
			frame.setVisible(true);
			frame.setResizable(false);

		} catch (Exception e) {
		}
		init();
		generate();
	}

	private void init() {
		// initialize border cells as already visited
		visited = new boolean[N + 2][N + 2];
		for (int x = 0; x < N + 2; x++) {
			visited[x][0] = true;
			visited[x][N + 1] = true;
		}
		for (int y = 0; y < N + 2; y++) {
			visited[0][y] = true;
			visited[N + 1][y] = true;
		}

		// initialze all walls as present
		north = new boolean[N + 2][N + 2];
		east = new boolean[N + 2][N + 2];
		south = new boolean[N + 2][N + 2];
		west = new boolean[N + 2][N + 2];
		for (int x = 0; x < N + 2; x++) {
			for (int y = 0; y < N + 2; y++) {
				north[x][y] = true;
				east[x][y] = true;
				south[x][y] = true;
				west[x][y] = true;

			} // end for
		} // end for
		for (int x = 1; x <= N; x++) {
			for (int y = 1; y <= N; y++) {
				StdDraw.filledCircle(x + 0.5, y + 0.5, 0.25);
			}
		}
	}

	// generate the maze
	private void generate(int x, int y) {
		visited[x][y] = true;
		
			
	double er[] = {0.0, 1.0, 3.0, 3.0, 2.0, 2.0, 3.0, 2.0, 1.0, 0.0, 0.0};
			
	/*}new double[11];
	double xy[] = {1.0, 0.0, 3.0, 2.0, 1.0, 0.0, 3.0, 3.0, 2.0, 0.0, 2.0, 1.0, 0.0, 0.0, 3.0, 2.0, 3.0, 3.0, 2.0, 2.0, 0.0, 2.0, 2.0, 2.0, 0.0,
			2.0, 0.0, 1.0, 2.0, 0.0, 2.0, 2.0, 0.0, 3.0, 1.0, 1.0, 0.0, 0.0, 3.0, 0.0, 3.0, 0.0, 3.0, 1.0, 1.0, 0.0, 2.0, 2.0, 3.0, 1.0,
			2.0, 1.0, 0.0, 0.0, 0.0, 0.0, 3.0, 0.0, 3.0, 1.0, 1.0, 0.0, 2.0, 3.0, 0.0, 2.0, 2.0, 2.0, 1.0, 2.0, 3.0, 1.0, 2.0, 0.0, 3.0,
			3.0, 2.0, 1.0, 1.0, 2.0, 2.0, 0.0, 3.0, 0.0, 1.0, 2.0, 0.0, 3.0, 1.0, 1.0, 1.0, 0.0, 3.0, 1.0, 3.0, 2.0, 0.0, 3.0, 1.0, 3.0,
			2.0, 3.0, 3.0, 3.0, 3.0, 0.0, 2.0, 0.0, 1.0, 1.0, 1.0, 0.0, 0.0, 3.0, 3.0, 3.0, 2.0, 1.0, 3.0, 1.0, 3.0, 1.0, 2.0, 2.0, 2.0,
			2.0, 0.0, 0.0, 1.0, 1.0, 2.0, 0.0, 2.0, 3.0, 1.0, 0.0, 2.0, 1.0, 0.0, 2.0, 2.0, 1.0, 0.0, 2.0, 2.0, 1.0, 1.0, 2.0, 1.0, 2.0,
			0.0, 2.0, 0.0, 3.0, 2.0, 0.0, 2.0, 1.0, 1.0, 0.0, 0.0, 1.0, 3.0, 2.0, 1.0, 2.0, 2.0, 1.0, 0.0, 1.0, 0.0, 3.0, 3.0, 2.0, 3.0,
			2.0, 0.0, 3.0, 3.0, 1.0, 0.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 2.0, 2.0, 0.0, 2.0, 2.0, 0.0, 2.0, 0.0, 2.0, 0.0, 2.0, 2.0,
			2.0, 2.0, 2.0, 2.0, 0.0, 1.0, 1.0, 1.0, 2.0, 0.0, 3.0, 2.0, 0.0, 1.0, 3.0, 2.0, 2.0, 1.0, 2.0, 3.0, 3.0, 0.0, 2.0, 1.0, 1.0,
			3.0, 2.0, 3.0, 2.0, 2.0, 0.0, 1.0, 3.0, 2.0, 1.0, 2.0, 0.0, 3.0, 3.0, 0.0, 3.0, 0.0, 2.0, 0.0, 3.0, 3.0, 3.0, 1.0, 2.0, 0.0,
			0.0, 0.0, 3.0, 2.0, 0.0, 3.0, 1.0, 2.0, 3.0, 0.0, 3.0, 0.0, 2.0, 1.0, 1.0, 1.0, 3.0, 2.0, 1.0, 2.0, 3.0, 0.0, 2.0, 2.0, 0.0};
	int v = 0;
	for(int j = 0; j<er.length;j++){
		//er[j] =StdRandom.uniform(4);
		 
			while (v<25){
			er[j]= xy[v];
			v++;
		    }
			v=26;
			while (v<50){
				er[j]= xy[v];
				v++;
			    }
			v=51;
			while (v<75){
				er[j]= xy[v];
				v++;
			    }
			v=76;
			while (v<100){
				er[j]= xy[v];
				v++;
			    }
			v=101;
			while (v<125){
				er[j]= xy[v];
				v++;
			    }
			v=126;
			while (v<150){
				er[j]= xy[v];
				v++;
			    }
			v=151;
			while (v<175){
				er[j]= xy[v];
				v++;
			    }
			while (v<200){
				er[j]= xy[v];
				v++;
			    }
			while (v<225){
				er[j]= xy[v];
				v++;
			    }
			while (v<250){
					er[j]= xy[v];
					v++;
				    }
			
			while (v<275){
				er[j]= xy[v];
				v++;
			    }
		System.out.println(er[j]);}
		// double r = 0.0;*/
		// while there is an unvisited neighbor
		while (!visited[x][y + 1] || !visited[x + 1][y] || !visited[x][y - 1]
				|| !visited[x - 1][y]) {
		int i=0;
			
			
			// pick random neighbor (could use Knuth's trick instead)
			while (true) {
							
				double r = er[i];
				//System.out.println(r);
				i++;	
				
						
				if ((r == 0) && !visited[x][y + 1]) {
					north[x][y] = false;
					south[x][y + 1] = false;
					generate(x, y + 1);
					//System.out.println("c:"+r);

					break;
				} else if (r == 1 && !visited[x + 1][y]) {
					east[x][y] = false;
					west[x + 1][y] = false;
					generate(x + 1, y);
					//System.out.println("c1:"+r);
					break;
				} else if (r == 2 && !visited[x][y - 1]) {
					south[x][y] = false;
					north[x][y - 1] = false;
					generate(x, y - 1);
					//System.out.println("c2:"+r);

					break;
				} else if (r == 3 && !visited[x - 1][y]) {
					west[x][y] = false;
					east[x - 1][y] = false;
					generate(x - 1, y);
					//System.out.println("c3:"+r);

					break;
				}

			}
		}
	}

	// generate the maze starting from lower left
	private void generate() {
		generate(1, 1);

		/*
		 * // delete some random walls for (int i = 0; i < N; i++) { int x = 1 +
		 * StdRandom.uniform(N-1); int y = 1 + StdRandom.uniform(N-1);
		 * north[x][y] = south[x][y+1] = false; }
		 * 
		 * // add some random walls for (int i = 0; i < 10; i++) { int x = N/2 +
		 * StdRandom.uniform(N/2); int y = N/2 + StdRandom.uniform(N/2);
		 * east[x][y] = west[x+1][y] = true; }
		 */

	}

	// solve the maze using depth-first search
	/*
	 * private void solve(int x, int y) { if (x == 0 || y == 0 || x == N+1 || y
	 * == N+1) return; if (done || visited[x][y]) return; visited[x][y] = true;
	 * 
	 * StdDraw.setPenColor(StdDraw.BLUE); StdDraw.filledCircle(x + 0.5, y + 0.5,
	 * 0.25); StdDraw.show(30);
	 * 
	 * // reached middle if (x == N/2 && y == N/2) done = true;
	 * 
	 * if (!north[x][y]) solve(x, y + 1); if (!east[x][y]) solve(x + 1, y); if
	 * (!south[x][y]) solve(x, y - 1); if (!west[x][y]) solve(x - 1, y);
	 * 
	 * if (done) return;
	 * 
	 * StdDraw.setPenColor(StdDraw.GRAY); StdDraw.filledCircle(x + 0.5, y + 0.5,
	 * 0.25); StdDraw.show(30); }
	 * 
	 * // solve the maze starting from the start state public void solve() { for
	 * (int x = 1; x <= N; x++) for (int y = 1; y <= N; y++) visited[x][y] =
	 * false; done = false; solve(1, 1); }
	 */
	// draw the maze
	public void draw() {
		StdDraw.setPenColor(StdDraw.RED);
		StdDraw.filledCircle(1 + 0.5, 5 + 0.5, 0.25);
		// StdDraw.filledCircle(1.5, 1.5, 0.375);

		StdDraw.setPenColor(StdDraw.BLACK);
		for (int x = 1; x <= N; x++) {
			for (int y = 1; y <= N; y++) {

				if (south[x][y])
					StdDraw.line(x, y, x + 1, y);
				if (north[x][y])
					StdDraw.line(x, y + 1, x + 1, y + 1);
				if (west[x][y])
					StdDraw.line(x, y, x, y + 1);
				if (east[x][y])
					StdDraw.line(x + 1, y, x + 1, y + 1);
			}
		}
		StdDraw.show(1000);
	}

	public static boolean[][] getsouth() {
		return south;
	}

	public static boolean[][] getnorth() {
		return north;
	}

	public static boolean[][] getwest() {
		return west;
	}

	public static boolean[][] geteast() {
		return east;
	}

	public static int giveN() {
		return N;
	}

	public static void mazeprogramDown() {

		boolean[][] south = getsouth();

		if ((q > 0) && (q < 6)) {

			if ((south[p][q])) {

				//System.out.println("Invalid Move: Please check if you have hit the wall ");
				StdDraw.setPenColor(StdDraw.BLUE);
				StdDraw.filledCircle(p + 0.5, q + 0.5, 0.25);
				StdDraw.show(10);

			}// end second if

			else

			{   if ((vis[p][q - 1] != false)) {
				count++;
				}
				StdDraw.setPenColor(StdDraw.WHITE);
			    StdDraw.filledCircle(p + 0.5, q + 0.5, 0.3);
			    out.println("Down Direction the position played is"+ p+ q+ "count is"+countvalue());
			    StdDraw.setPenColor(StdDraw.GREEN);
				StdDraw.filledCircle(p + 0.5, q + 0.5-1, 0.25);
				StdDraw.show(10);
								
				// out.println("Down Direction");
				q--;
				vis[p][q] = false;
				//System.out.println("The value of p & q " + p + q);
				
			 // end inner else
			} // end else
		}// end if
		else {
			System.out.println("Wrong Move : You have hit the Border of maze");
		}
	}// end mazeprogramDown

	public static void mazeprogramRight() {
		boolean[][] east = geteast();

		/*for (int p = 1; p < 6; p++) {
			for (int q = 1; q < 2; q++) {

				System.out.print(east[p][q]);
			} 
		}*/ // end both for

		if ((p < 6) || (p > 0)) {
			if ((east[p][q])) {

		//		System.out.println("Wrong move:Please check if you have hit the wall of maze");
				StdDraw.setPenColor(StdDraw.BLUE);
				StdDraw.filledCircle(p + 0.5, q + 0.5, 0.25);
				StdDraw.show(10);

			}// end second if

			else

			{
				if ((vis[p + 1][q] != false))
				{
				count++;}
				StdDraw.setPenColor(StdDraw.WHITE);
				StdDraw.filledCircle(p + 0.5, q + 0.5, 0.3);
				out.println("Right Direction the position played is"+ p+ q+ "count is"+countvalue());
				StdDraw.setPenColor(StdDraw.GREEN);
				StdDraw.filledCircle(p + 0.5 + 1, q + 0.5, 0.25);
				StdDraw.show(10);
				
				// out.println("Right Direction");
				p++;
				vis[p][q] = false;
				//System.out.println("The value of p & q " + p + q);
				 // end if 
				     }// end else
		 
		}// end if
		else {
			System.out.println("Wrong Move : You have hit the Border of maze");
		}
	}// end mazeprogramRight

	public static void mazeprogramLeft() {
		boolean[][] west = getwest();

		/*for (int p = 1; p < 6; p++) {
			for (int q = 1; q < 2; q++) {

				System.out.print(west[p][q]);
			}
		} */ // end both for

		if ((p < 6) || (p > 0)) {
			if ((west[p][q])) {

				//System.out.println("Wrong move:Please check if you have hit the wall of maze");
				StdDraw.setPenColor(StdDraw.BLUE);
				StdDraw.filledCircle(p + 0.5, q + 0.5, 0.25);
				StdDraw.show(10);

			}// end second if

			else

			{ 
				if ((vis[p - 1][q] != false)){
					count++; }
				StdDraw.setPenColor(StdDraw.WHITE);
				StdDraw.filledCircle(p + 0.5, q + 0.5, 0.3);
				out.println("Left Direction the position played is"+ p+ q+ "count is"+countvalue());
				StdDraw.setPenColor(StdDraw.GREEN);
				StdDraw.filledCircle(p + 0.5 - 1, q + 0.5, 0.25);
				StdDraw.show(10);
				
				
				// out.println("Left Direction");
				p--;
				vis[p][q] = false;
				
				
			} // end else
		}// end if
		else {
			System.out.println("Wrong Move : You have hit the Border of maze");
		}
	}// end mazeprogramRight

	public static void mazeprogramUp() {

		boolean[][] north = getnorth();

		if ((q < 6) || (q > 0)) {
			if ((north[p][q])) {

				//System.out.println("Wrong move:Please check if you have hit the wall of maze");
				StdDraw.setPenColor(StdDraw.BLUE);
				StdDraw.filledCircle(p + 0.5, q + 0.5, 0.25);
				StdDraw.show(10);

			}// end second if

			else

			{  if ((vis[p][q + 1] != false)){
				count++;
				}
				StdDraw.setPenColor(StdDraw.WHITE);
				StdDraw.filledCircle(p + 0.5, q + 0.5, 0.3);
				out.println("Up Direction the position played is"+ p+ q+ "count is"+countvalue());
				StdDraw.setPenColor(StdDraw.GREEN);
				StdDraw.filledCircle(p + 0.5, q + 0.5 + 1, 0.25);
				StdDraw.show(10);
				
				// out.println("Left Direction");
				q++;
				vis[p][q] = false;
				
				
			} // end else
		}// end if
		else {
			System.out.println("Wrong Move : You have hit the Border of maze");
		}
	}// end mazeprogramUp

	public static int countvalue() {
		return count;
	}

	public static int positionp(){
		return p;
	}
	
	public static int positionq(){
		return q;
	}
	// a test client
	public static void main(String[] args) throws IOException {
		/*Maze1 M1 = new Maze1(5, null);
		StdDraw.show(0);
		M1.draw();*/

	}

}