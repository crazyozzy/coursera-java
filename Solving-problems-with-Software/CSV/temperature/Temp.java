
/**
 * Find exports data in CSV file.
 * 
 * @crazyozzy aka KDA (your name) 
 * @0.0.0 (a version number or a date)
 */
import edu.duke.*;
import org.apache.commons.csv.*;
import java.io.*;

public class Temp {
	public CSVRecord coldestHourInFile (CSVParser parser) {
		CSVRecord lowestRow = null;
		for (CSVRecord record : parser) {
			if (lowestRow == null) {
				lowestRow = record;
			}
			else {
				double recordTemp = Double.parseDouble(record.get("TemperatureF"));
				double lowestRowTemp = Double.parseDouble(lowestRow.get("TemperatureF"));

				if ( (recordTemp < lowestRowTemp) && (recordTemp > -9998) ) {
					lowestRow = record;
				}
			}
		}

		return lowestRow;
	}

	public CSVRecord lowestHumidityInFile (CSVParser parser) {
		CSVRecord lowestRow = null;

		for (CSVRecord record : parser) {
			if (lowestRow == null) {
				lowestRow = record;
			}
			else {
				int recordHumidity = Integer.parseInt(record.get("Humidity"));
				int lowestRowHumidity = Integer.parseInt(lowestRow.get("Humidity"));

				if ( (recordHumidity < lowestRowHumidity) && !("N/A".equals(record.get("Humidity"))) ) {
					lowestRow = record;
				}
			}
		}

		return lowestRow;
	}

	public String fileWithColdestTemperature () {
		DirectoryResource dr = new DirectoryResource();
		File lowestTempFile = null;

		for (File file : dr.selectedFiles()) {
			if ( lowestTempFile != null ) {
				FileResource lowestTempFr = new FileResource(lowestTempFile);
				FileResource fr = new FileResource(file);
				CSVRecord lowestTempFileRecord = coldestHourInFile(lowestTempFr.getCSVParser());
				CSVRecord frRecord = coldestHourInFile(fr.getCSVParser());
				double tempLowestTempFileParser = Double.parseDouble(lowestTempFileRecord.get("TemperatureF"));
				double tempFrParser = Double.parseDouble(frRecord.get("TemperatureF"));

				if (tempFrParser < tempLowestTempFileParser) {
					lowestTempFile = file;
				}
			}
			else {
				lowestTempFile = file;
			}
		}

		return lowestTempFile.getName();
	}

	public void testFileWithColdestTemperature () {
		System.out.println("File name with coldest temp: " + fileWithColdestTemperature());
	}

	public void testerColdestHourInFile () {
		FileResource fr = new FileResource("./nc_weather/2014/weather-2014-01-08.csv");
		CSVParser parser = fr.getCSVParser();
		CSVRecord lowestRow = coldestHourInFile(parser);

		System.out.println("Lowest temp: " + lowestRow.get("TemperatureF") + " at " + lowestRow.get("DateUTC"));
	}

	public void testLowestHumidityInFile () {
		FileResource fr = new FileResource("./nc_weather/2014/weather-2014-01-20.csv");
		CSVParser parser = fr.getCSVParser();
		CSVRecord lowestRow = lowestHumidityInFile(parser);

		System.out.println("Lowest humidity: " + lowestRow.get("Humidity") + " at " + lowestRow.get("DateUTC"));
	}

	public static void main (String[] args) {
		Temp tmp = new Temp();
		//tmp.testerColdestHourInFile();
		//tmp.testFileWithColdestTemperature();
		tmp.testLowestHumidityInFile();
	}
}
