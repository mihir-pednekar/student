/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import com.google.gson.JsonObject;
import constants.SqlQueryConstants;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import model.GradesModel;

/**
 *
 * @author mihir
 * This is an eg. of One to One relationship between tables Students and Grades
 */
public class GradesDao {
    private Connection con;

    public GradesDao(Connection con) {
        this.con = con;
    }
    
    public boolean insertValues(JsonObject gradesJson) throws SQLException{
        boolean success = false;
        
        try{
            //Timestamp tstamp = new Timestamp(System.currentTimeMillis());
            String sharedPk = gradesJson.get("studentId").getAsString();
        
            StringBuilder query1 = new StringBuilder(SqlQueryConstants.INSERT_INTO_STUDENT);
            query1.append("("+sharedPk+", '"+gradesJson.get("studentName").getAsString()+"', '"+gradesJson.get("studentLastName").getAsString()+"', CURRENT_DATE)");
            
            StringBuilder query2 = new StringBuilder(SqlQueryConstants.INSERT_INTO_GRADES);
            query2.append("("+sharedPk+", "+gradesJson.get("studentGrade").getAsString()+")");

            Statement stmt = con.createStatement();
            con.setAutoCommit(false);
            
            stmt.execute(query1.toString());
            stmt.execute(query2.toString());
            con.commit();
            success = true;
            System.out.println("Values inserted into Grades...");
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
        return success;
    }
    public List<GradesModel> viewValues(String studID) throws SQLException{
        //Executing the query
      List<GradesModel> list = new ArrayList<>();
      
      StringBuilder query = new StringBuilder(SqlQueryConstants.SELECT_GRADES_JOIN_STUDENT);
      //query.append(gradesJson.get("studentID").getAsString());
      query.append(studID);
      
      Statement stmt = con.createStatement();
      ResultSet rs = stmt.executeQuery(query.toString());
      
      while(rs.next()){
          list.add(new GradesModel(rs.getString("Id"), rs.getString("fName"), rs.getString("lName"), rs.getString("regDate"), rs.getString("grade")));
      }
      
      return list;
    }
}
