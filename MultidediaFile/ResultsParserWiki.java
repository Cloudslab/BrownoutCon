import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

/**
 * This class is used to analyze the results from the parsed Wikipedia raw data.
 * With file, we can calculate the number of requests at second or minute rate. 
 * @author minxianx
 *
 */

public class ResultsParserWiki {
	
	public static void main(String[] args) {
		
		try {
		//"sampleparseddata01" file the the output file generated by WikiParser.java
		BufferedReader br = new BufferedReader(new FileReader("src/sampleparseddata01"));
		//Output the time and the number of requests at minute rate
		BufferedWriter bw = new BufferedWriter(new FileWriter("src/samepleresutlbyminute01"));
		
		String string = null; 
		String[] stringLine = null;
        Map<String, Integer> map = new TreeMap<String, Integer>();
		int number = 0;
		String timeMinute;
		
		while((string = br.readLine()) != null) {
	   
			stringLine = string.split("\t");
			timeMinute = stringLine[0].substring(0, stringLine[0].length() - 3);
			if(map.get(timeMinute) != null) {
				number = map.get(timeMinute);
				map.put(timeMinute , number + Integer.parseInt(stringLine[2]));
			}else {
			   map.put(timeMinute , Integer.parseInt(stringLine[2]));
			}
			
		}
		
		int index = 1;
		for (Map.Entry entry : map.entrySet()) {
			bw.write(entry.getKey() + "\t" + index++ + "\t" + entry.getValue() + "\n");
		}
		
		
		br.close();
		bw.close();
		System.out.println("complete");
		
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		}

}