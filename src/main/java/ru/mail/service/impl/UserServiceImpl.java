package ru.mail.service.impl;

import org.apache.log4j.Logger;
import ru.mail.service.UserService;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Denis Monich
 * this class realise all logic to get user
 */
public class UserServiceImpl implements UserService {

    private Map<String,String> users;
    private static final Logger logger = Logger.getLogger(UserServiceImpl.class);

    private static UserServiceImpl instance = null;


    private UserServiceImpl() {
        iniDataForTesting();
    }

    public static synchronized UserServiceImpl getInstance() {
        if (instance == null) {
            instance = new UserServiceImpl();
        }
        return instance;
    }

    /**
     *
     * @param userEmail login from UI
     * @param password  password from UI
     * @return true if credentials valid? else false
     */
    @Override
    public boolean isValidUser(String userEmail, String password) {
        //checking is login and password valid
        boolean isValid = false;
        logger.debug("checking userName and password");
        if(users.get(userEmail).equals(password)){
            isValid=true;
        }
        logger.debug("user isValid: "+ isValid);
        return isValid;
    }


    //creating default users
    private void iniDataForTesting() {
        logger.debug("creating users");
        users = new HashMap<>();
        users.put("admin", "admin");
    }
}
