package org.example.tool;/**
 * @fileName EnvConfig
 * @author pengfei
 * @date 2024/6/7
 * @description EnvConfig
 */

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * @author pengfei
 * @date 2024/6/7
 * @description EnvConfig
 */
public class EnvConfig {

    private static final Logger logger = LoggerFactory.getLogger(EnvConfig.class);
    private final static String CONFIG_FILE = "config.properties";

    public static Properties properties = new Properties();


    public static void init() {
        Properties properties = new Properties();

        // Load properties file from classpath
        try (InputStream input = EnvConfig.class.getClassLoader().getResourceAsStream(CONFIG_FILE)) {
            if (input == null) {
                logger.error("Sorry, unable to find config.properties");
                return;
            }

            // Load properties from input stream
            properties.load(input);
        } catch (IOException ex) {
            logger.error("IOException occurred while reading config.properties", ex);
            return;
        }
        logger.info("load config success");
        EnvConfig.properties = properties;
    }

    public static Object getConfig(String key) {
        return properties.get(key);
    }

}
