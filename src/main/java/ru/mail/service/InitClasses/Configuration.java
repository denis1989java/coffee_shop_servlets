package ru.mail.service.InitClasses;

import java.util.Properties;
import java.util.ResourceBundle;

public class Configuration {

    public Properties PROPERTIES;

    public String DB_URL;

    public String DB_DRIVER;

    public Integer DB_MAX_CONNECTIONS;

    public Configuration(){
        init();
    }

    private static Configuration configuration = new Configuration();

    public static Configuration getInstance(){
        return configuration;
    }

    private void init(){
        ResourceBundle resourceBundleRoot = ResourceBundle.getBundle("root");
        ResourceBundle resourceBundleDB = ResourceBundle.getBundle("database");
        if(resourceBundleRoot.getString("method.of.orders.saving").equals("mysql")){
            this.PROPERTIES = new Properties();
            PROPERTIES.put("user", resourceBundleDB.getString("mysql.user"));
            PROPERTIES.put("password", resourceBundleDB.getString("mysql.password"));
            PROPERTIES.put("characterEncoding", "UTF-8");
            PROPERTIES.put("useUnicode", "true");
            DB_URL = resourceBundleDB.getString("mysql.url");
            DB_DRIVER = "com.mysql.jdbc.Driver";
            DB_MAX_CONNECTIONS = 5;
        }else{
            this.PROPERTIES = new Properties();
            PROPERTIES.put("user", resourceBundleDB.getString("oracle.user"));
            PROPERTIES.put("password", resourceBundleDB.getString("oracle.password"));
            DB_URL = resourceBundleDB.getString("oracle.url");
            DB_DRIVER = "oracle.jdbc.driver.OracleDriver";
            DB_MAX_CONNECTIONS = 5;
        }

    }
}
