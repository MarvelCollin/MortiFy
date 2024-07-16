package utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Connect {
	private final String USERNAME = "root";
	private final String PASSWORD = "";
	private final String HOST = "localhost:3306";
	private final String DATABASE = "mortify";
	private final String FORMAT = String.format("jdbc:mysql://%s/%s", HOST, DATABASE);
	 
	private static volatile Connect instance;
    private Connection conn;

    private Connect() {
        try {
            conn = DriverManager.getConnection(FORMAT, USERNAME, PASSWORD);
        } catch (SQLException e) {
            throw new RuntimeException("bobo", e);
        }
    }

    public static Connect getInstance() {
    	if (instance == null) {
    		instance = new Connect();
    	}
		return instance;
    }

    public Connection getConnection() {
        return conn;
    }

    public void closeConnection() {
        try {
            if (conn != null && !conn.isClosed()) {
                conn.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
