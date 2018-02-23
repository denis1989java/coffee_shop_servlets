package ru.mail.controller.listener;


import org.apache.log4j.Logger;
import ru.mail.service.InitClasses.ScriptService;
import ru.mail.service.model.Coffee;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class InitListener implements ServletContextListener {

    private ResourceBundle resourceBundle = ResourceBundle.getBundle("root");
    private static final Logger logger = Logger.getLogger(InitListener.class);

    @Override
    public void contextInitialized(ServletContextEvent event) {
        logger.debug("Initialization started");
        logger.debug("check orders saving method");
        if (resourceBundle.getString("method.of.orders.saving").equals("mysql")) {
            logger.debug("initialization of mysql DB");
            ServletContext servletContext = event.getServletContext();
            ScriptService.getInstance().process(servletContext.getInitParameter("initSQLFile"));
        } else if (resourceBundle.getString("method.of.orders.saving").equals("file")) {
            logger.debug("initialization of files");
            iniDataForTesting();
        }
        logger.debug("Initialization finished");
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        logger.debug("ServletContextListener destroyed");
    }


    private void iniDataForTesting() {
        logger.debug("writing the file with coffees");
        List<Coffee> coffees = new ArrayList<>();
        coffees.add(new Coffee((long) 1, "Americano", "bitter", BigDecimal.valueOf(8)));
        coffees.add(new Coffee((long) 2, "Cappuccino", "sweet", BigDecimal.valueOf(10)));
        coffees.add(new Coffee((long) 3, "Espresso", "5 stars", BigDecimal.valueOf(9)));
        coffees.add(new Coffee((long) 4, "Latte", "milk", BigDecimal.valueOf(10)));
        coffees.add(new Coffee((long) 5, "Lungo", "half strong", BigDecimal.valueOf(10)));
        coffees.add(new Coffee((long) 6, "Doppiio", "dig glass", BigDecimal.valueOf(12)));
        coffees.add(new Coffee((long) 7, "Glace", "eggs", BigDecimal.valueOf(12)));
        coffees.add(new Coffee((long) 8, "Raf", "with tube", BigDecimal.valueOf(11)));
        try {
            ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(resourceBundle.getString("coffeeRoot")));
            out.writeObject(coffees);
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
