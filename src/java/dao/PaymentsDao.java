/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import com.google.gson.JsonObject;
import constants.MsgConstants;
import constants.SqlQueryConstants;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import model.GradesModel;
import model.PaymentsModel;

/**
 *
 * @author mihir
 */
public class PaymentsDao {
    private Connection con;

    public PaymentsDao() {
    }

    public PaymentsDao(Connection con) {
        this.con = con;
    }
    
    public boolean insertValues(JsonObject paymentsJson) throws SQLException{
        boolean success = false;
        
        try{
            Timestamp tstamp = new Timestamp(System.currentTimeMillis());
            Long sharedPkPId = tstamp.getTime();
            //Long sharedPkEId = tstamp.getTime()%1000;
        
            StringBuilder query1 = new StringBuilder(SqlQueryConstants.INSERT_INTO_PAYMENTS);
            //+ "pId, amount, studId) VALUES ";
            query1.append("("+sharedPkPId+", "+paymentsJson.get("payAmount").getAsString()+", "+paymentsJson.get("studentId").getAsString()+")");
            System.out.println(query1);
            
            GradesDao gradesDao = new GradesDao(con);
            List<GradesModel> listStudGrades = gradesDao.viewValues(paymentsJson.get("studentId").getAsString());
            
            if(listStudGrades.isEmpty()){
                return false;
            }
            Statement stmt = con.createStatement();
            con.setAutoCommit(false);
            
            stmt.execute(query1.toString());
            //stmt.execute(query2.toString());
            con.commit();
            success = true;
            System.out.println(MsgConstants.VALUES_INSERTED_INTO_PAYMENTS_TABLE);
        }
        catch(Exception e){
            System.out.println(e); con.rollback();
        }
        finally{
            if(con!=null){
               con.close();
               System.out.println(MsgConstants.CONNEC_CLOSED);
            }
        }
        return success;
    }
    
    public List<PaymentsModel> viewValues(String studID) throws SQLException{
        //Executing the query
      List<PaymentsModel> list = new ArrayList<>();
      
      StringBuilder query = new StringBuilder(SqlQueryConstants.SELECT_PAYMENTS_STUDENT);
      //query.append(gradesJson.get("studentID").getAsString());
      query.append(studID);
      
      Statement stmt = con.createStatement();
      ResultSet rs = stmt.executeQuery(query.toString());
      
      while(rs.next()){
          //list.add(new GradesModel(rs.getString("Id"), rs.getString("fName"), rs.getString("lName"), rs.getString("regDate"), rs.getString("grade")));
          //+ "pId, amount, studId) VALUES ";
          list.add(new PaymentsModel(rs.getString("pId"), rs.getString("amount"), rs.getString("studId")));
      }
      
      return list;
    }
}
