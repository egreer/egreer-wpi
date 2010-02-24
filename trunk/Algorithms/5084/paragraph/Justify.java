package paragraph;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Justify {

	static long[][] spaces;
	static long[][] cost;
	static long[] opt;
	static int[] point;
	static int m = 80; //Length of a line
	static int n; // number of words
	static ArrayList<Integer> words = new ArrayList<Integer>();;
	static ArrayList<Line> lines = new ArrayList<Line>(); 
	static long objective;
	static Justify t = new Justify();
	
	/**
	 * @param args
	 * @throws FileNotFoundException 
	 */
	public static void main(String[] args) throws FileNotFoundException {
		
		//Read from file place in n
		File f = new File("sampleinput");
		Scanner scan = new Scanner(f);
		while (scan.hasNext()){
			String s = scan.next();
			words.add(Integer.parseInt(s));
		}
		
		words.remove(0);
		
		//get n words
		n = words.size();
		 
		//initialize 
		n++; //Corrects for a 0-origin arrary
		spaces = new long[n][n];
		cost = new long[n][n];
		opt = new long[n];
		point = new int[n];
		n--;
		
		fillSpaces();
		fillCost();
		fillOpt();
		findLines(n);
		output();
		
		System.out.println("Done!");
	}
	
	public static void fillSpaces(){
		for (int i = 1; i <= n; i++){
			spaces[i][i] = m - words.get(i-1);
			for(int j = i + 1 ; j <= n ; j ++){
				spaces[i][j] = spaces[i][j-1] - words.get(j-1) - 1;
			}
		}
	}
	
	public static void fillCost(){
		for (int i = 1; i <= n; i++){
			for(int j = i ; j <= n ; j ++){
				
				if (spaces[i][j] < 0) cost[i][j] = Integer.MAX_VALUE;
				else if(j == n && spaces[i][j] >= 0) cost[i][j] = 0;
				else cost[i][j] = spaces[i][j] * spaces[i][j] *spaces[i][j];
			}
		}
	}
	
	public static void fillOpt(){
		opt[0] = 0;
		for (int j = 1 ; j <= n ; j++){
			opt[j] = Long.MAX_VALUE;
			
			for(int i = 1 ; i <= j ; i++){
				if (opt[i-1] + cost[i][j] < opt[j] ){
					opt[j] = opt[i-1] + cost[i][j];
					point[j] = i;
				}
			}
		}
	}
	
	public static int findLines(int j){
		int i = point[j];
		int k;

		objective += i;
		
		if (i == 1)	k = 1;
		else k = findLines(i-1) + 1;
	
		lines.add(t.new Line(i,j));
		
		return k;
	}
	
	public static void output() {
		int r = lines.size();
		System.out.println ("r = " + r + "       Objective : " + objective );
		
		for (int q = 0 ; q < r ; q++ ){
			Line temp = lines.get(q);
	
			int total = 0; 
			for (int y = temp.start ; y < temp.end ; y ++){
				int wordLen = words.get(y);
				System.out.print(wordLen+ ", ");
				total += wordLen + 1;
			}
			
			if (total == m+1) total = m;
			
			System.out.println("          "+ total);
		}
	}
	
	//Making use of java's object oriented programming!
	public class Line{
		public int start;
		public int end;
		Line(int start, int end){
			this.start = start;
			this.end = end;
		}
	}
}
