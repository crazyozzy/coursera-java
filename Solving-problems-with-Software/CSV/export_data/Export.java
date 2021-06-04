
/**
 * Find exports data in CSV file.
 * 
 * @crazyozzy aka KDA (your name) 
 * @0.0.0 (a version number or a date)
 */
import edu.duke.*;
import org.apache.commons.csv.*;

public class Export {
	public String countryInfo (CSVParser parser, String country) {
		for (CSVRecord record : parser) {
			if ( record.get("Country").equals(country) ){
				//System.out.print(record.get("Country") + ": ");
				//System.out.print(record.get("Exports") + ": ");
				//System.out.println(record.get("Value (dollars)"));
				return ( record.get("Country") + ": " + record.get("Exports") + ": " + record.get("Value (dollars)") );
			}
		}

		return "NOT FOUND";
	}

	public void listExportersTwoProducts (CSVParser parser, String exportItem1, String exportItem2) {
		for (CSVRecord record : parser) {
			String exports = record.get("Exports");
			if ( (exports.indexOf(exportItem1) != -1) && (exports.indexOf(exportItem2) != -1) ) {
				System.out.println(record.get("Country"));
			}
		}
	}

	public int numberOfExporters (CSVParser parser, String exportItem) {
		int counter = 0;
		for (CSVRecord record : parser) {
			if ( record.get("Exports").indexOf(exportItem) != -1 ) {
				counter++;
			}
		}

		return counter;
	}

	public void bigExporters (CSVParser parser, String expAmount) {
		for (CSVRecord record : parser) {
			if (record.get("Value (dollars)").length() > expAmount.length()) {
				System.out.println(record.get("Country") + "\t\t" + record.get("Value (dollars)"));
			}
		}
	}

	public void tester () {
		FileResource fr = new FileResource("./exports/exportdata.csv");
		CSVParser parser = fr.getCSVParser();

		//System.out.println(countryInfo(parser, "Nauru"));
		//listExportersTwoProducts(parser, "fish", "nuts");
		//System.out.println("Number of countries: " + numberOfExporters(parser, "gold"));
		bigExporters(parser, "$999,999,000,000");
	}

	public static void main (String[] args) {
		Export exp = new Export();
		exp.tester();
	}
}
