package hw6;

import java.io.*;
import java.text.NumberFormat;
import java.util.*;

/**
 * Process the stock data and report on the trends for the stocks.
 * 
 * @author George
 */
public class Problem2 {
	
	/** Securities Information is found in an ArrayList. */
	static ArrayList securities = new ArrayList();
	
	/** 
	 * Pricing Information is found in an Hashtable. 
	 *    Key = String   Value=Information 
	 */
	static Hashtable values = new Hashtable ();

	/**
	 * Volume within each sector.
	 * 
	 *     Key = String   Value = Long
	 */
	static Hashtable sectors = new Hashtable();
	
	/** 
	 * Known Sectors.
	 */
	static ArrayList knownSectors = new ArrayList();
	
	/**
	 * Processes securities and prices data files to generate statistical report of 
	 * the trading activity over the recorded period.
	 */
	public static void main(String[] args) throws FileNotFoundException {
		// INPUT
		loadSecurities(new File ("hw6\\new_securities.dat"));
		loadPrices(new File ("hw6\\new_prices.dat"));
		
		// process
		processData();
		
		// OUTPUT
		outputReport();
	}

	/**
	 * Process all data to determine the sector by sector totals.
	 * 
	 * Update sectors by aggregating all sales within each sector.
	 */
	private static void processData() {

		for (int i = 0; i < securities.size(); i++) {
			Security sec = (Security) securities.get(i);
			
			Information val = (Information) values.get(sec.getId());
			String sector = sec.getSector();
			
			Long total = (Long) sectors.get(sector);
			if (total == null) {
				// start with known volume.
				sectors.put (sector, val.getVolume());
				knownSectors.add(sector);
			} else {
				sectors.put (sector, val.getVolume() + total);
			}
		}
	}

	/**
	 * Report is high/low for this security. Plus total sales of that security.
	 */
	private static void outputReport() {
		System.out.println ("Security\t" + "Open\t" + "Low\t" + "High\t" + "Close\t" + "Profit\t" + "Sector\t");
		System.out.println ("--------\t" + "----\t" + "---\t" + "----\t" + "-----\t" + "------\t" + "------\t");
		for (int i = 0; i < securities.size(); i++) {
			Security sec = (Security) securities.get(i);
			
			System.out.print (sec + "\t");
			Information it = (Information) values.get(sec.getId());
			
			System.out.print (it.getOpen() + "\t" + it.getLow() + "\t" + it.getHigh() + "\t");
			NumberFormat pformat = NumberFormat.getPercentInstance();
			System.out.print (it.getClose() + "\t" + pformat.format(it.getImprovement()) + "\t");
			System.out.print (sec.getSector());
			System.out.println();
		}
		
		System.out.println ();
		
		System.out.println ("Sector\t" + "Total Volume");
		System.out.println ("------\t" + "------------");
		long min=-1;
		long max=-1;
		String minSector="";
		String maxSector="";
		
		for (int i = 0; i < knownSectors.size(); i++) {
			String sector = (String) knownSectors.get(i);
			long v = (Long) sectors.get(sector);
			if (min == -1) {
				min = v;
				max = v;
				minSector=sector;
				maxSector=sector;
			} else {
				if (v < min) {
					min = v;
					minSector=sector;
				}
				if (v > max) {
					max = v;
					maxSector=sector;
				}
			}
			System.out.println (sector + "\t" + v);
		}
		
		System.out.println ("Sector with least volume:" + minSector + "[" + min + "]");
		System.out.println ("Sector with most volume:" + maxSector + "[" + max+ "]");
		
	}

	/**
	 * Load pricing information from a file and store all relevant information
	 * int the values Hashtable.
	 * 
	 * @param file
	 * @throws FileNotFoundException 
	 */
	private static void loadPrices(File file) throws FileNotFoundException {
		Scanner sc = new Scanner (file);
		
		// throw away the header row.
		sc.nextLine();
		
		// Id   TradeDate   HighPrice   LowPrice   ClosePrice   OpenPrice   Volume
		// Security_1   12/6/2006   69.68   62.98   67   67   190931991
		while (sc.hasNext()) {
			String id = sc.next();
			String date = sc.next();
			float high = sc.nextFloat();
			float low = sc.nextFloat();
			float close = sc.nextFloat();
			float open = sc.nextFloat();
			long volume = sc.nextLong();
			
			Information val = (Information) values.get(id);
			if (val == null) {
				val = new Information (open);
				values.put(id, val);
			} 
			
			// cover case whether stock goes up or down.
			val.setLow(high);
			val.setHigh(low);
			val.setLow(low);
			val.setHigh(high);
			
			// close is a known commodity.
			val.setClose(close);

			// store volume.
			val.aggregateSales(volume);

			sc.nextLine(); // drain
		}
			
	}

	/**
	 * Load securities information from a file and store all relevant information
	 * int the securities ArrayList.
	 * 
	 * @param file
	 * @throws FileNotFoundException 
	 */
	private static void loadSecurities(File file) throws FileNotFoundException {
		Scanner sc = new Scanner (file);
		
		// throw away the header row.
		sc.nextLine();
		
		// Id   Ex   SIC   SPR   Currency
		while (sc.hasNext()) {
			String id = sc.next();
			String ex = sc.next();
			String sic = sc.next();
			String spr = sc.next();
			String cur = sc.next();
			
			Security sec = new Security (id, sic, spr);
			securities.add(sec);
			
			sc.nextLine(); // drain
		}
		
	}

}
