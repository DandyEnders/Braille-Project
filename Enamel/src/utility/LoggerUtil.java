package utility;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Map;
import java.util.TreeMap;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public final class LoggerUtil {
	
	private final static Logger logger = Logger.getLogger("Logger");
	

	private static File log = Language.userLog;
	private static File countLog = Language.userCountLog;
	
	private static FileHandler fileHandler;
	
	private static boolean isInitialized = false;
	
	private static Map<String, Integer> featureCount = new TreeMap<String, Integer>();
	
	private LoggerUtil() {
		
	}

	public static Logger 		getLogger() {return logger;}
	public static File 			getLog() {return log;}
	public static FileHandler 	getFileHandler() {
		if(!isInitialized)
			initialize();
		return fileHandler;
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
		
		isInitialized = true;
		
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
		
		try {
			FileWriter fw = new FileWriter(countLog);
			Integer count;
			
			for(String feature : featureCount.keySet()) {
				count = featureCount.get(feature);
				fw.write(feature + "\t" + count + "\n");
				
			}
			fw.close();
			fw.flush();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("asdf");
		
		fileHandler.flush();
		fileHandler.close();
	}
	
	
	
	
}
