package za.co.social_engineer.www.socialengineer;

import java.sql.*;

/**
 * Class used to manage database CRUD
 *
 * Created by Marcel Teixeira on 2016/07/03.
 */
public class DatabaseHandler {

    private String databaseUrl;
    private String databasePort;
    private String databaseName;
    private String username;
    private String password;

    public DatabaseHandler(String databaseUrl, String databasePort, String databaseName, String username, String password) {
        this.databaseUrl = databaseUrl;
        this.databasePort = databasePort;
        this.databaseName = databaseName;
        this.username = username;
        this.password = password;
    }

    public ResultSet getFirstQuestion() throws Exception {
        Class.forName("com.mysql.jdbc.Driver");

        Connection conn = DriverManager.getConnection("jdbc:mysql://" + databaseUrl + ":" +
                databasePort + "/" + databaseName, username, password);

        Statement stmnt = conn.createStatement();

        ResultSet rs = stmnt.executeQuery("SELECT * FROM questions WHERE id = 2");

        conn.close();

        return rs;
    }
}