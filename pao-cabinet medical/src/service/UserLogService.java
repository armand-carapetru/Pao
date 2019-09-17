package service;

import JDBC.ConnectionUtils;
import model.UserLog;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class UserLogService {
    public static List<UserLog> users;
    public static Integer numberOfUsers = 0;
    private static UserLogService ourInstance = new UserLogService();

    public static UserLogService getInstance() {
        return ourInstance;
    }

    private UserLogService() {
        users = new ArrayList<>(Arrays.asList());
    }

    public static  void incarcaUsers()
    {  Connection connection=new ConnectionUtils().getDBConnection();
        Statement statement = null;
        try {
            statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("select * from user_passwords");
            while (rs.next()) {
                UserLog m = new UserLog (rs.getString(1),rs.getString(2));
                users.add(m);
                numberOfUsers++;
            }
            connection.close();
        } catch (SQLException e) {
                e.printStackTrace();
            }
    }

}
