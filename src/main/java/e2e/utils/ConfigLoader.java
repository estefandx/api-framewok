package e2e.utils;

import java.util.Properties;
import e2e.api.enums.EnvType;

public class ConfigLoader {

    private static final String BASE_URI_API = "base_uri_api";
    private static final String RETRY_FAILED_TESTS = "retry_failed_tests";

    private static final String ENV = "env";
    private static final String CONFIG_PROPERTIES = "_config.properties";

    private static final String PROD_CONFIG_PROPERTIES = "prod" + CONFIG_PROPERTIES;
    private static final String QA_CONFIG_PROPERTIES = "qa" + CONFIG_PROPERTIES;
    private static final String DEV_CONFIG_PROPERTIES = "dev" + CONFIG_PROPERTIES;


    private static final String RESOURCES_PATH = System.getProperty("user.dir") + "/src/test/resources/";
    private final Properties properties;
    private static ConfigLoader configLoader;

    private ConfigLoader() {

        String env = System.getProperty(ENV, EnvType.QA.toString());

        switch (EnvType.valueOf(env)) {

            case DEV: {
                properties = getConfigPropertyFile(DEV_CONFIG_PROPERTIES);
                break;
            }
            case QA: {
                properties = getConfigPropertyFile(QA_CONFIG_PROPERTIES);
                break;
            }
            case PRODUCTION: {
                properties = getConfigPropertyFile(PROD_CONFIG_PROPERTIES);
                break;
            }
            default: {
                throw new IllegalStateException("Invalid EnvType: " + env);
            }

        }
    }

    private Properties getConfigPropertyFile(String configFile) {
        return PropertyUtils.propertyLoader(RESOURCES_PATH + configFile);
    }

    private String getPropertyValue(String propertyKey) {
        String prop = properties.getProperty(propertyKey);
        if (prop != null) {
            return prop.trim();
        } else {
            throw new RuntimeException("Property " + propertyKey + " is not specified in the config.properties file");
        }
    }

    public static ConfigLoader getInstance() {
        if (configLoader == null) {
            configLoader = new ConfigLoader();
        }
        return configLoader;
    }

    public String getBaseUriAPI() {
        return getPropertyValue(BASE_URI_API);
    }

    public String getRetryFailedTests() {
        System.out.println("==============================================================");
        System.out.println("RETRY_FAILED_TESTS"+RETRY_FAILED_TESTS);
        System.out.println("==============================================================");
        return getPropertyValue(RETRY_FAILED_TESTS);
    }

}
