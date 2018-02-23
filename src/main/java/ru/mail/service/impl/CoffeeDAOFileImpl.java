package ru.mail.service.impl;

import org.apache.log4j.Logger;
import ru.mail.service.CoffeeDAO;
import ru.mail.service.model.Coffee;


import java.io.*;
import java.util.List;
import java.util.ResourceBundle;


/**
 * @author Denis Monich
 * this class realise all logic to get list of existing coffee from file
 */
public class CoffeeDAOFileImpl implements CoffeeDAO {

    private ResourceBundle resourceBundle = ResourceBundle.getBundle("root");
    private static final Logger logger = Logger.getLogger(CoffeeDAOFileImpl.class);

    /**
     * getting all coffees
     * @return list of coffees
     */
    @Override
    public List<Coffee> list() {
        List<Coffee> coffees = null;
        //Create new FileInputStream object to read file
        try {
            FileInputStream fis = new FileInputStream(resourceBundle.getString("coffeeRoot"));
            //Create new ObjectInputStream object to read object from file
            ObjectInputStream obj = new ObjectInputStream(fis);
            logger.debug("reading exists coffees ");
            while (true) {
                coffees = (List<Coffee>) obj.readObject();
                if (fis.available() != -1) {
                    break;
                }
                //Read object from file
            }
        } catch (ClassNotFoundException | IOException e) {
            logger.debug("ClassNotFoundException | IOException e" + e);
        }
        return coffees;
    }
}
