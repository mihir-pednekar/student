/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import com.google.gson.JsonObject;
import constants.SqlQueryConstants;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author mihir
 */
public class DbConnection {
    private String driver , url ,username ,passwd, dbName;
    Connection con = null;
    Statement stmt = null;

    public DbConnection() {
    }

    public DbConnection(JsonObject jsonObj) {
        this.driver = jsonObj.get("driver").getAsString();
        this.username = jsonObj.get("user").getAsString();
        this.passwd = jsonObj.get("pass").getAsString();
        this.dbName = jsonObj.get("dbName").getAsString();
        this.url = jsonObj.get("url").getAsString();
    }

    @Override
    public String toString() {
        return "DbConnection{" + "driver=" + driver + ", url=" + url + ", username=" + username + ", passwd=" + passwd + ", dbName=" + dbName + ", con=" + con + ", stmt=" + stmt + '}';
    }
    
    //get connection properties from JSON file written at Server path
    public Connection getConnection() throws SQLException, ClassNotFoundException{
        //Connection con = null;
        url = url + dbName + ";create=true";
        System.out.println(this.toString());
        Class.forName(driver);
        con = DriverManager.getConnection(url,username,passwd);
        System.out.println("Connection established to DB....");
        return con;
    }
    
    public void createTables() throws SQLException{

        try{
            System.out.println("Creating table in given database...");
            con = getConnection();
            stmt = con.createStatement();
            con.setAutoCommit(false);

            stmt.executeUpdate(SqlQueryConstants.CREATE_STUDENT_TABLE);
            stmt.executeUpdate(SqlQueryConstants.CREATE_CLASSES_TABLE);
            stmt.executeUpdate(SqlQueryConstants.CREATE_ENROLLEMTS_TABLE);
            stmt.executeUpdate(SqlQueryConstants.CREATE_GRADES_TABLE);
            stmt.executeUpdate(SqlQueryConstants.CREATE_PAYMENTS_TABLE);
            stmt.execute(SqlQueryConstants.INSERT_DEFAULT_INTO_CLASSES_1);
            stmt.execute(SqlQueryConstants.INSERT_DEFAULT_INTO_CLASSES_2);
            con.commit();
            System.out.println("Created table in given database...");
            System.out.println("Database created successfully...");
        }
        catch(Exception e){
            System.out.println(e); con.rollback();
        }
        finally{
            if(con!=null){
               con.close();
               System.out.println("Connection closed...");
            }
        }
    }
}
