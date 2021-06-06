
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

	public CSVRecord lowestHumidityInManyFiles () {
		DirectoryResource dr = new DirectoryResource();
		FileResource lowestHumidityFr = null;

		for (File file : dr.selectedFiles()) {
			FileResource fr = new FileResource(file);
			if (lowestHumidityFr != null) {
				CSVRecord lowestHumidityFileRecord = lowestHumidityInFile(lowestHumidityFr.getCSVParser());
				CSVRecord frRecord = lowestHumidityInFile(fr.getCSVParser());
				int humidityInLowestHumidityFrRecord = Integer.parseInt(lowestHumidityFileRecord.get("Humidity"));
				int humidityInFrRecord = Integer.parseInt(frRecord.get("Humidity"));

				if (humidityInFrRecord < humidityInLowestHumidityFrRecord) {
					lowestHumidityFr = fr;
				}
			}
			else {
				lowestHumidityFr = fr;
			}
		}

		return lowestHumidityInFile(lowestHumidityFr.getCSVParser());
	}

	public double averageTempInFile (CSVParser parser) {
		int counter = 0;
		double sum = 0;

		for (CSVRecord record : parser) {
			if ( !("-9999".equals(record.get("TemperatureF"))) ) {
				sum = sum + Double.parseDouble(record.get("TemperatureF"));
				counter++;
			}
		}

		return (sum / counter);
	}

	public double averageTempWithHighHumidityInFile (CSVParser parser, int value) {
		int counter = 0;
		double averageTemp = 0;

		for (CSVRecord record : parser) {
			if ( !("N/A".equals(record.get("Humidity"))) && !("-9999".equals(record.get("TemperatureF"))) ) {
				int humidity = Integer.parseInt(record.get("Humidity"));
				double temp = Double.parseDouble(record.get("TemperatureF"));

				if ( humidity >= value ) {
					averageTemp += temp;
					counter++;
				}
			}
		}

		if ( counter == 0 ) {
			return -1000;
		}
		else {
			return ( averageTemp / counter );
		}
	}

	public void testAverageTempWithHighHumidityInFile () {
		FileResource fr = new FileResource("./nc_weather/2014/weather-2014-03-20.csv");
		double averageTemp = averageTempWithHighHumidityInFile(fr.getCSVParser(), 80);

		if ( averageTemp < -999 ) {
			System.out.println("No temperatures with that humidity");
		}
		else {
			System.out.println("Average temp when high humidity is " + averageTemp);
		}
	}

	public void testAverageTempInFile () {
		FileResource fr = new FileResource("./nc_weather/2014/weather-2014-01-20.csv");
		CSVParser parser = fr.getCSVParser();
		System.out.println("Average temp: " + averageTempInFile(parser));
	}

	public void testFileWithColdestTemperature () {
		System.out.println("File name with coldest temp: " + fileWithColdestTemperature());
	}

	public void testerColdestHourInFile () {
		FileResource fr = new FileResource("./nc_weather/2014/weather-2014-05-01.csv");
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
		tmp.testerColdestHourInFile();
		//tmp.testFileWithColdestTemperature();
		//tmp.testLowestHumidityInFile();
		//tmp.testAverageTempInFile();
		//tmp.testAverageTempWithHighHumidityInFile(); 
	}
}
