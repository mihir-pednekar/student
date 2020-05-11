/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import com.google.gson.JsonObject;
import constants.ErrorConstants;
import constants.MsgConstants;
import constants.SqlQueryConstants;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.List;
import model.GradesModel;

/**
 *
 * @author mihir
 */
public class ClassesDao {
    private Connection con;

    public ClassesDao() {
    }

    public ClassesDao(Connection con) {
        this.con = con;
    }
    
    public boolean insertValues(JsonObject classesJson) throws SQLException{
        boolean success = false;
        
        try{
            Timestamp tstamp = new Timestamp(System.currentTimeMillis());
            Long sharedPkCId = tstamp.getTime();
            Long sharedPkEId = tstamp.getTime()%1000;
        
            StringBuilder query1 = new StringBuilder(SqlQueryConstants.INSERT_INTO_CLASSES);
            //+ "cId, cTitle, cDesc) VALUES ";
            query1.append("("+sharedPkCId+", '"+classesJson.get("classTitle").getAsString()+"', '"+classesJson.get("classDesc").getAsString()+"')");
            System.out.println(query1);
            
            StringBuilder query2 = new StringBuilder(SqlQueryConstants.INSERT_INTO_ENROLLEMTS);
            //+ "eId, classId, studId) VALUES ";
            query2.append("("+sharedPkEId+", "+sharedPkCId+", "+classesJson.get("studentId").getAsString()+")");
            System.out.println(query2);
            
            GradesDao gradesDao = new GradesDao(con);
            List<GradesModel> listStudGrades = gradesDao.viewValues(classesJson.get("studentId").getAsString());
            
            if(listStudGrades.isEmpty()){
                return false;
            }
            Statement stmt = con.createStatement();
            con.setAutoCommit(false);
            
            stmt.execute(query2.toString());
            stmt.execute(query2.toString());
            con.commit();
            success = true;
            System.out.println(MsgConstants.VALUES_INSERTED_INTO_CLASSES_TABLE);
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
}
