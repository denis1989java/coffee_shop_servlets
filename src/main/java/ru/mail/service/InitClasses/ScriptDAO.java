package ru.mail.service.InitClasses;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class ScriptDAO {


    private static ScriptDAO instance = null;

    private ScriptDAO() {
        // Exists only to defeat instantiation.
    }

    public static ScriptDAO getInstance() {
        if (instance == null) {
            instance = new ScriptDAO();
        }
        return instance;
    }

    public void executeUserDBScript(List<String> commands) throws SQLException {
        try {
            Connection cn = DataSource.getConnection();
            Statement statement = cn.createStatement();
            for (String command : commands) {
                    statement.executeUpdate(command);
            }
            DataSource.returnConnection(DataSource.getConnection());
            statement.close();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
