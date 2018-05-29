package utility;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public final class LoggerUtil {
	
	private final static Logger logger = Logger.getLogger("Logger");
	

	private static File log = Language.userLog;
	private static File countLog = Language.userCountLog;
	
	private static FileHandler fileHandler;
	
	private static Map<String, Integer> featureCount = new TreeMap<String, Integer>();
	
	private LoggerUtil() {
		
	}
	
	
	public static void initialize() {
		
		try {
			fileHandler = new FileHandler(log.toString());
			logger.addHandler(fileHandler);
			logger.setUseParentHandlers(false);
			SimpleFormatter formatter = new SimpleFormatter();
			fileHandler.setFormatter(formatter);
			
		}catch(IOException e) {
			logger.warning("Logger File handler thrown exception: " + e.getMessage());
			e.printStackTrace();
		}catch(Exception e) {
			logger.warning("Logger thrown exception: " + e.getMessage());
			e.printStackTrace();
		}
		
	}
	
	public static void log(String feature, String info) {
		logger.info(info);
		if(!featureCount.containsKey(feature)) {
			featureCount.put(feature, 1);
		}else {
			featureCount.put(feature, featureCount.get(feature)+1);
		}
	}
	
	public static void close() {
		countLogWrite();
		
		fileHandler.flush();
		fileHandler.close();
	}

	private static void countLogWrite() {
		try {
			FileWriter fw = new FileWriter(countLog);
			Integer count;
			String feature;
			
			Set<Entry<String,Integer>> entrySet = featureCount.entrySet();
			List<Entry<String,Integer>> sortedList = new ArrayList<Entry<String, Integer>>(entrySet);
			Collections.sort(sortedList, new Comparator<Map.Entry<String, Integer>>() {
	            public int compare(Map.Entry<String, Integer> o1,
	                    Map.Entry<String, Integer> o2) {
	                return o2.getValue().compareTo(o1.getValue());
	            }
	        });
			
			
			
			for(Entry<String,Integer> entry : sortedList) {
				count = entry.getValue();
				feature = entry.getKey();
				
				fw.write(count + "\t" + feature + "\n");
			}
			
			
			fw.flush();
			fw.close();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	
	
}
