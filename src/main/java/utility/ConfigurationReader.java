package utility;

import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class ConfigurationReader {

    private static Properties properties;
    private static FileReader reader;

    private static void initializeProperty() throws IOException {
        properties = new Properties();
        reader = new FileReader(System.getProperty("user.dir")+
                "\\src\\main\\resources\\config.properties");
        properties.load(reader);
    }

    public static String getConfigurationData(String key) throws IOException {
        initializeProperty();
        String value = properties.getProperty(key);
        closeProperty();
        return value;
    }

    private static void closeProperty() throws IOException {
        reader.close();
    }
}
