package config;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ReadProperties {

	public String getprop(String s) throws IOException {
		// create file input stream object for the properties file
		FileInputStream fis = new FileInputStream("C:\\Users\\snesingh3\\Documents\\Seleniumjava\\ZebraProject\\src\\testData\\testData.properties");
		// create Properties class object to access properties file
		Properties prop = new Properties();
		// load the properties file
		prop.load(fis);
		String value= prop.getProperty(s);
		return value;
		
	}
}
