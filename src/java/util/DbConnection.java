/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import com.google.gson.JsonObject;
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
    
    public void createConn() throws SQLException{

        try{
            
            url = url + dbName + ";create=true";
            System.out.println(this.toString());
            Class.forName(driver);
            con = DriverManager.getConnection(url,username,passwd);
            System.out.println("Connection established....");
            //STEP 4: Execute a query
            System.out.println("Creating table in given database...");
            stmt = con.createStatement();
            con.setAutoCommit(false);

            String query1 = "CREATE TABLE Students( "
                + "Id INT NOT NULL, "
                + "fName VARCHAR(30), "
                + "lName VARCHAR(30), "
                + "PRIMARY KEY (Id))";
            
            String query2 = "CREATE TABLE Classes( "
                + "cId INT NOT NULL, "
                + "cTitle VARCHAR(30), "
                + "cDesc VARCHAR(30), "
                + "PRIMARY KEY (cId))";
            
            String query3 = "CREATE TABLE Enrollments( "
                + "eId INT NOT NULL, "
                + "classId INT NOT NULL, "
                + "studId INT NOT NULL, "
                + "FOREIGN KEY (classId) REFERENCES Classes(cId), "
                + "FOREIGN KEY (studId) REFERENCES Students(Id)) ";
            
            stmt.executeUpdate(query1);
            stmt.executeUpdate(query2);
            stmt.executeUpdate(query3);
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
