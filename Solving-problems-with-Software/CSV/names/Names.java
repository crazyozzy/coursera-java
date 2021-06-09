
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
		int numBorn = 0;

		for (CSVRecord rec : fr.getCSVParser(false)) {
			numBorn = Integer.parseInt(rec.get(2));
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

	public static int getRank (int year, String name, String gender) {
		int rank = 1;
		
		FileResource fr = new FileResource("./us_babynames_by_year/yob" + year + ".csv");

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

	public static int getRank (File file, String name, String gender) {
		int rank = 1;
		
		FileResource fr = new FileResource(file);

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

	public static String getName (int year, int rank, String gender) {
		int tempRank = 1;

		FileResource fr = new FileResource("./us_babynames_by_year/yob" + year + ".csv");

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

	public static String whatIsNameInYear (String name, int year, int newYear, String gender) {
		int oldRank = getRank(year, name, gender);
		return getName(newYear, oldRank, gender);
	}

	public static int yearOfHighestRank (String name, String gender) {
		DirectoryResource dr = new DirectoryResource();
		int rank = 0;
		File tempFile = null;

		for (File file : dr.selectedFiles()) {
			int tempRank = getRank(file, name, gender);
			if (rank != 0) {
				if (rank > tempRank ) {
					rank = tempRank;
					tempFile = file;
				}
			}
			else {
				rank = tempRank;
				tempFile = file;
			}
		}

		String fileName = tempFile.getName();
		
		if (rank == 0) {
			return -1;
		}
		else {
			return Integer.parseInt(fileName.substring(fileName.indexOf("yob") + 3, fileName.indexOf(".csv")));
		}
	}

	public static double getAverageRank (String name, String gender) {
		DirectoryResource dr = new DirectoryResource();
		int counter = 0;
		double sumRank = 0;

		for (File file : dr.selectedFiles()) {
			int tempRank = getRank(file, name, gender);

			if (tempRank != -1) {
				counter++;
				sumRank += tempRank;
			}
		}

		if (counter != 0) {
			return (sumRank / counter);
		}
		else {
			return -1;
		}
	}

	public static int getTotalBirthsRankedHigher (int year, String name, String gender) {
		FileResource fr = new FileResource("./us_babynames_by_year/yob" + year + ".csv");
		int births = 0;

		for (CSVRecord rec : fr.getCSVParser(false)) {
			if (rec.get(1).equals(gender)) {
				if (rec.get(0).equals(name)) {
					return births;
				}
				else {
					births += Integer.parseInt(rec.get(2));
				}
			}
		}

		return -1;
	}

	public static void main (String[] args) {
		Names names = new Names();
		FileResource fr = new FileResource("./us_babynames_by_year/yob1900.csv");

		names.totalBirths(fr);

		String name = "Drew";
		String gender = "M";
		int year = 1990;
		int newYear = 2014;
		int rank = 450;
		System.out.println("Rank " + rank + " has name " + getName(year, rank, gender) + " in year " + year);
		System.out.println("Name: " + name + " rank " + names.getRank(year, name, gender));
		System.out.println("Name " + name + " born in " + year + " would be " + whatIsNameInYear(name, year, newYear, gender) + " in " + newYear);
		//System.out.println("Name " + name + " average rank is " + getAverageRank(name, gender));
		System.out.println("Name " + name + " births ranked higher is " + getTotalBirthsRankedHigher(year, name, gender));
		//System.out.println("Name " + name + " has highest rank in " + yearOfHighestRank(name, gender));
		System.out.println("Births ranked higher than " + name + " in " + year + " is " + getTotalBirthsRankedHigher(year, name, gender));
	}
}
