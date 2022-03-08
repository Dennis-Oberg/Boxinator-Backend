package com.Dennis.Boxinator.DBConn;

import java.sql.Connection;
import java.sql.SQLException;

public class SQLConnection {

    public static Connection dbConnector() {

        try {
            DatabaseInit dbInit = new DatabaseInit();
            //This is a pretty dirty solution, big no no but for this example it should work fine.
            //you´ll have to change the path so it matches your local path to the file aswell as change the content in your dbcredentials.properties-file
            dbInit.init("C:\\Users\\Denni\\Desktop\\Boxinator\\Boxinator\\src\\main\\java\\com\\Dennis\\Boxinator\\DBConn\\dbcredentials.properties");
            return dbInit.getConnection();

        } catch (SQLException e) {
            System.out.println(e.getErrorCode());
        }
        return null;
    }
}
