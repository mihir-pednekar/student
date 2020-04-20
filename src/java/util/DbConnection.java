/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author mihir
 */
public class DbConnection {
    String driver = "org.apache.derby.jdbc.ClientDriver";
    String url = "jdbc:derby://localhost:1527/students_1234";
    String username = "mihir";
    String passwd  = "mihir";
    Connection con = null;
    
    public void createConn() throws SQLException{

        try{
            Class.forName(driver);  
            con = DriverManager.getConnection(url,username,passwd);
            System.out.println("Connection established....");
            //here sonoo is database name, root is username and password  
            //Statement stmt=con.createStatement();con.
        }
        catch(Exception e){
            System.out.println(e);
        }
        finally{
            if(con!=null){
               con.close();
            }
        }
    }
}
