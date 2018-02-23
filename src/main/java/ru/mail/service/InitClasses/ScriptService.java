package ru.mail.service.InitClasses;


import org.apache.log4j.Logger;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class ScriptService {

    private static final Logger logger = Logger.getLogger(ScriptService.class);
    private ScriptDAO scriptDAO = ScriptDAO.getInstance();
    private static ScriptService instance = null;

    private ScriptService() {
        // Exists only to default instantiation.
    }

    public static synchronized ScriptService getInstance() {
        if (instance == null) {
            instance = new ScriptService();
        }
        return instance;
    }

    public void process(String initSQLFile) {
        List<String> commands = new ArrayList<>();
        URL resource = getClass().getClassLoader().getResource(initSQLFile);
        URI uri = null;
        try {
            uri = new URI(resource.toString());
        } catch (URISyntaxException e) {
            logger.debug("Reading sql file fault: " + e.getMessage());
            System.exit(0);
        }
        File file = new File(uri.getPath());
        try (
                BufferedReader in = new BufferedReader(new FileReader(file))
        ) {
            String str;
            while ((str = in.readLine()) != null) {
                commands.add(str);
            }
            scriptDAO.executeUserDBScript(commands);
        } catch (Exception e) {
            logger.debug("Failed to execute " + initSQLFile + ". The error is " + e.getMessage());
        }
    }
}
