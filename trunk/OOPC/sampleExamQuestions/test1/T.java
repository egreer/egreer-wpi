package test1;

public class T {
	static Exception e = new Exception();
	static boolean d;
	static int i,r,c,q,y,l,z,m,k;
	static int[]t={7,56,448,73,146,292,273,84};
	public static String b = "";
	public static String[] f = {"---+---+---", "\n", "  ", " X", " O", " |", "", "Player ", " make your move: two integers in range 1..3 representing [row column]", "That space is taken" , "Invalid input. Try entering two integers between 1 and 3.",
		"X wins\n", "O wins\n", "No winner. Game is drawn\n"};
	public static void main(String[] args) {
		java.util.Scanner x = new java.util.Scanner (System.in);
		m=t[7]+(t[0]+1)/2;
		l=t[0]+t[1]+t[2];
		while (k == 0) {
			b=f[6];
			for (q=1,y=4;q<= 256;q*=2) {
				if ((l&q)==q){b+=f[2];}
				else if ((z&q)==q){b+=f[3];}
				else b +=f[4];
				if (q<256){if (q == y) { b += f[1] + f[0] + f[1]; y*=8;} else b += f[5];}
			}		
			System.out.println(b + f[1]);
			b = f[6];
			q=0;
			d=false;
			System.out.println (f[7]+ (char)m + f[8]);
			try {
				if ((r=(x.nextInt()-1)) < 0 || r > 3) { throw e; }
				if ((c=(x.nextInt()-1)) < 0 || c > 3) { throw e; }
				q=(int)Math.pow(2,3*r+c);
				if ((l&q)==0) {
					System.out.println (f[9]);
				} else {
					l-=q;
					if (m%2==0)z+=q;
					if (!d)
						for (i=0;i<8;i++){
							if (((l&t[i])==0)&&(((z&t[i])==t[i])|((z&t[i])==0))) {
								k=m;
								d=true;
							}
						}
					if (!d)
						if (l==0) {
							k = t[0]/2;
							d=true;
						}
					
					if (!d) m += (9*(2*(m%2)-1));
				}
			} catch (Exception e1) {
				q = 1;
				System.out.println (f[10]);
				x.nextLine();
			}			
			if (q == 0) k = (9*(2*(m%2)-1));
		}
		if (k == 2*t[0]+t[3]+1) {
			b= f[11];
		} else if (k == t[3]+t[2]-1) {
			b= f[12];
		} else if (k == t[0]/2) {
			b= f[13];
		}
		System.out.println (b);
		b = f[6];
		for (q =1,y=4;q<=256;q*=2) {
			if ((l&q)==q){b+=f[2];}
			else if ((z&q)==q){b+=f[3];}
			else {b +=f[4];}
			if (q<256){if (q == y) { b += f[1] + f[0] + f[1]; y*=8;} else b += f[5];}
		}		
		System.out.println(b + f[1]);
		b = f[6];
	}
}