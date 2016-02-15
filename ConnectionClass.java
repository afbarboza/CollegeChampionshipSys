package TrabalhoBD;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/*
 * ConnectionClass.java: this class defines the basic properties and methods for
 * connecting to a Oracle Express Database.
 * Also, implementes the Singleton design patter, giving a guarantee that one,
 * and only one connections is defined for the database. 
 */

public class ConnectionClass {
    private String hostName;
    private String port;
    private String strSID;
    private String userName;
    private String passwordName;
    private boolean statusConnection;
    private Connection connection;
   
    private static ConnectionClass instance = null;
    
    protected ConnectionClass() {
    }
    
    public static ConnectionClass getInstance() {
        if (instance == null) {
            instance = new ConnectionClass();
        }
        return instance;
    }
    
    public String getHostName() {
        return hostName;
    }

    public void setHostName(String hostName) {
        if (hostName != null)
            this.hostName = hostName;
    }

    public String getPort() {
        return port;
    }

    public void setPort(String port) {
        if (port != null)
            this.port = port;
    }

    public String getStrSID() {
        return strSID;
    }

    public void setStrSID(String strSID) {
        if (strSID != null)
            this.strSID = strSID;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        if (userName != null)
            this.userName = userName;
    }

    public String getPasswordName() {
        return passwordName;
    }

    public void setPasswordName(String passwordName) {
        if (passwordName != null)
            this.passwordName = passwordName;
    }
    
    public boolean isConnected() {
        return statusConnection;
    }
    
    /*
     * connect(): this function trys to connect to a database.
     * returns: 0, if suceed
     *          1, if connection failed.
     *          2, for internal java errors.
     */
    public int connect() {
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            /*defines the string connection*/
            String connInfo = "jdbc:oracle:thin:@" +  this.getHostName() +
                              ":" + this.getPort() + ":" +
                              this.getStrSID();
            connection = DriverManager.getConnection(connInfo, 
                        this.getUserName(), this.getPasswordName());
            this.statusConnection = true;
            return 0;
        } catch (SQLException ex) {
            this.statusConnection = false;
            return 1;
        } catch (ClassNotFoundException ex) {
            this.statusConnection = false;
            System.err.println("Class Class not found. Please, contact admin");
            return 2;
        } catch (Exception ex) {
            this.statusConnection = false;
            ex.printStackTrace();
            return 3;
        }
    }
    
    public boolean isStatusConnection() {
        return statusConnection;
    }

    public Connection getConnection() {
        return connection;
    }
}
