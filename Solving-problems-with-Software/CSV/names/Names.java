
/**
 * Find exports data in CSV file.
 * 
 * @crazyozzy aka KDA (your name) 
 * @0.0.0 (a version number or a date)
 */
import edu.duke.*;
import org.apache.commons.csv.*;
import java.io.*;

public class Names {
	public void totalBirths (FileResource fr) {
		int totalBirths = 0;
		int totalNames = 0;
		int totalBoys = 0;
		int totalBoysNames = 0;
		int totalGirls = 0;
		int totalGirlsNames = 0;

		for (CSVRecord rec : fr.getCSVParser(false)) {
			int numBorn = Integer.parseInt(rec.get(2));
			totalBirths += numBorn;
			totalNames++;

			if (rec.get(1).equals("M")) {
				totalBoys += numBorn;
				totalBoysNames++;
			}
			else {
				totalGirls += numBorn;
				totalGirlsNames++;
			}
		}

		System.out.println("Total Births:\t" + totalBirths);	
		System.out.println("Total Boys:\t" + totalBoys);	
		System.out.println("Total Girls:\t" + totalGirls);	
		System.out.println("Total Names:\t" + totalNames);	
		System.out.println("Total Boys Names:\t" + totalBoysNames);
		System.out.println("Total Girls Names:\t" + totalGirlsNames);
	}

	public int getRank (int year, String name, String gender) {
		int rank = 1;
		
		FileResource fr = new FileResource("./us_babynames_by_year/yob2014.csv");

		for (CSVRecord rec : fr.getCSVParser(false)) {
			if (rec.get(1).equals(gender)) {
				if (rec.get(0).equals(name)) {
					return rank;
				}
				else {
					rank++;
				}
			}
		}

		return -1;
	}

	public String getName (int year, int rank, String gender) {
		int tempRank = 1;

		FileResource fr = new FileResource("./us_babynames_by_year/yob2014.csv");

		for (CSVRecord rec : fr.getCSVParser(false)) {
			if (rec.get(1).equals(gender)){
				if (tempRank == rank) {
					return rec.get(0);
				}
				else {
					tempRank++;
				}
			}
		}

		return "NO NAME";
	}

	public static void main (String[] args) {
		Names names = new Names();
		FileResource fr = new FileResource("./us_babynames_test/yob2014short.csv");

		names.totalBirths(fr);

		String name = "Olga";
		System.out.println("Total Name: " + name + " " + names.getRank(2014, name, "F"));
	}
}
