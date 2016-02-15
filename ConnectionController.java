/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TrabalhoBD;

import javax.swing.JOptionPane;

/**
 * @author Alex Barboza
 */
public class ConnectionController {
    private static ConnectionController instance = null;
    private ConnectionController() {}
    
    public static ConnectionController getInstance() {
        if (instance == null) {
            instance = new ConnectionController();
        }
        return instance;
    }
    
    public static void setData(String hostName, String port, String strSID, 
                               String userName, String password) {
        /*checks whether user typed valid values for connection*/
        boolean errorConnectionFlag = false;
        if (hostName == null || hostName.length() == 0) {
            errorConnectionFlag = true;
        }
        
        if (port == null || port.length() == 0) {
            errorConnectionFlag = true;
        }
        
        if (strSID == null || strSID.length() == 0) {
            errorConnectionFlag = true;
        }
        
        if (userName == null || userName.length() == 0) {
            errorConnectionFlag = true;      
        }
        
        if (password == null || password.length() == 0) {
            errorConnectionFlag = true;
        }
        
       if (errorConnectionFlag) {
           String title = "Dados inválidos!";
           String message = "Dados incorretos. Por favor, insira os dados corretamente";
           JOptionPane.showMessageDialog(null, message, title, JOptionPane.ERROR);
           return;
       }
       
       /*instanciate a singleton connection class*/
       ConnectionClass conn_class = ConnectionClass.getInstance();
       conn_class.setHostName(hostName);
       conn_class.setPort(port);
       conn_class.setStrSID(strSID);
       conn_class.setUserName(userName);
       conn_class.setPasswordName(password);
       
       /*Connect to the database and selects the appropriate view*/
        ConnectionClass conn = ConnectionClass.getInstance();
        int connection_status = conn.connect();
        switch (connection_status) {
            case 0:
                JOptionPane.showMessageDialog(null, "OK!", ":)", JOptionPane.OK_OPTION);
                break;
            case 1:
                JOptionPane.showMessageDialog(null, "Erro while trying to connect to database.", ":(", connection_status);
                System.exit(1);
                break;
            case 2:
                JOptionPane.showMessageDialog(null, "ERROR!", ":|", connection_status);
                System.exit(1);
                break;
            default:
                break;
        }
    }
}
