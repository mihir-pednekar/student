/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import constants.ErrorConstants;
import constants.MsgConstants;
import dao.ClassesDao;
import dao.GradesDao;
import dao.PaymentsDao;
import java.io.FileNotFoundException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import service.StudentSvc;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Request;
import model.CreateDB;
import model.GradesModel;
import model.PaymentsModel;
import util.DbConnection;
import util.ReadWriteJson;

/**
 *
 * @author mihir
 */
@Path("student-rest")
public class StudentController implements StudentSvc{

    @Override
    @POST
    @Path("createDB")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public String createDB(String createDb) {
        System.out.println("<------------------------CreateDB Service------------------------->");
        System.out.println(createDb);
        try {
            JsonObject jsonObj = ReadWriteJson.readJson(createDb);
            ReadWriteJson.writeJson(jsonObj , "createDB.json");
            DbConnection dbConn = new DbConnection(jsonObj);
            dbConn.createTables();
        } catch (SQLException ex) {
            Logger.getLogger(StudentController.class.getName()).log(Level.SEVERE, null, ex);
            return "Error in DB & table creation!!!";
        }
        return "DB & tables created!!!";
    }
    
    @Override
    @POST
    @Path("insertGrade")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public String insertGrade(String grades) {
        System.out.println("<------------------------InsertGrade Service------------------------->");
        System.out.println(grades);
        try {
            JsonObject gradesJson = ReadWriteJson.readJson(grades);
            JsonObject dbConnJson = ReadWriteJson.readJsonFile("createDB.json");
            
            DbConnection dbConn = new DbConnection(dbConnJson);
            GradesDao dao = new GradesDao(dbConn.getConnection());
            
            if(!dao.insertValues(gradesJson)){
                throw new Exception("Error inserting into Grades Table!!!");
            }
        } catch (SQLException ex) {
            Logger.getLogger(StudentController.class.getName()).log(Level.SEVERE, null, ex);
            return "Error inserting into Grades Table!!!";
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(StudentController.class.getName()).log(Level.SEVERE, null, ex);
            return "Error inserting into Grades Table!!!";
        } catch (FileNotFoundException ex) {
            Logger.getLogger(StudentController.class.getName()).log(Level.SEVERE, null, ex);
            return "Error inserting into Grades Table!!!";
        } catch (Exception ex) {
            Logger.getLogger(StudentController.class.getName()).log(Level.SEVERE, null, ex);
            return "Error inserting into Grades Table!!!";
        }
        return "Inserted into Grades Table!!!";
    }
    
    @Override
    @GET
    @Path("viewGrade/{studID}")
    @Produces(MediaType.APPLICATION_JSON)
    //@Consumes(MediaType.TEXT_PLAIN)
    public String viewGrade(@PathParam("studID") String studID) {
        System.out.println("<------------------------ViewGrade Service------------------------->");
        System.out.println(studID);
        List<GradesModel> listGrades = null;
        String responseTxt = null;
        
        try {
            //JsonObject gradesJson = ReadWriteJson.readJson(grades);
            JsonObject dbConnJson = ReadWriteJson.readJsonFile("createDB.json");
            
            DbConnection dbConn = new DbConnection(dbConnJson);
            
            GradesDao dao = new GradesDao(dbConn.getConnection());
            listGrades = dao.viewValues(studID);
            
            if(listGrades.isEmpty() ){
                throw new Exception("Error viewing into Grades Table!!!");
            }
            Gson gson = new Gson();
            responseTxt = gson.toJson(listGrades);
            
        } catch (SQLException ex) {
            Logger.getLogger(StudentController.class.getName()).log(Level.SEVERE, null, ex);
            return "Error viewing into Grades Table!!!";
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(StudentController.class.getName()).log(Level.SEVERE, null, ex);
            return "Error viewing into Grades Table!!!";
        } catch (FileNotFoundException ex) {
            Logger.getLogger(StudentController.class.getName()).log(Level.SEVERE, null, ex);
            return "Error viewing into Grades Table!!!";
        } catch (Exception ex) {
            Logger.getLogger(StudentController.class.getName()).log(Level.SEVERE, null, ex);
            return "Error viewing into Grades Table!!!";
        }
        return responseTxt;
    }
    
    @Override
    @POST
    @Path("insertClass")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public String insertClass(String classes) {
        System.out.println("<------------------------InsertClass Service------------------------->");
        System.out.println(classes);
        try {
            JsonObject classesJson = ReadWriteJson.readJson(classes);
            JsonObject dbConnJson = ReadWriteJson.readJsonFile("createDB.json");
            
            DbConnection dbConn = new DbConnection(dbConnJson);
            ClassesDao dao = new ClassesDao(dbConn.getConnection());
            
            if(!dao.insertValues(classesJson)){
                throw new Exception(ErrorConstants.ERROR_INSERT_INTO_CLASSES_TABLE);
            }
        } catch (SQLException ex) {
            Logger.getLogger(StudentController.class.getName()).log(Level.SEVERE, null, ex);
            return ErrorConstants.ERROR_INSERT_INTO_CLASSES_TABLE;
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(StudentController.class.getName()).log(Level.SEVERE, null, ex);
            return ErrorConstants.ERROR_INSERT_INTO_CLASSES_TABLE;
        } catch (FileNotFoundException ex) {
            Logger.getLogger(StudentController.class.getName()).log(Level.SEVERE, null, ex);
            return ErrorConstants.ERROR_INSERT_INTO_CLASSES_TABLE;
        } catch (Exception ex) {
            Logger.getLogger(StudentController.class.getName()).log(Level.SEVERE, null, ex);
            return ErrorConstants.ERROR_INSERT_INTO_CLASSES_TABLE;
        }
        return "Inserted into Grades Table!!!";
    }
    
    @Override
    @POST
    @Path("insertPay")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public String insertPay(String payment) {
        System.out.println("<------------------------InsertPay Service------------------------->");
        System.out.println(payment);
        try {
            JsonObject classesJson = ReadWriteJson.readJson(payment);
            JsonObject dbConnJson = ReadWriteJson.readJsonFile("createDB.json");
            
            DbConnection dbConn = new DbConnection(dbConnJson);
            PaymentsDao dao = new PaymentsDao(dbConn.getConnection());
            
            if(!dao.insertValues(classesJson)){
                throw new Exception(ErrorConstants.ERROR_INSERT_INTO_PAYMENTS_TABLE);
            }
        } catch (SQLException ex) {
            Logger.getLogger(StudentController.class.getName()).log(Level.SEVERE, null, ex);
            return ErrorConstants.ERROR_INSERT_INTO_PAYMENTS_TABLE;
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(StudentController.class.getName()).log(Level.SEVERE, null, ex);
            return ErrorConstants.ERROR_INSERT_INTO_PAYMENTS_TABLE;
        } catch (FileNotFoundException ex) {
            Logger.getLogger(StudentController.class.getName()).log(Level.SEVERE, null, ex);
            return ErrorConstants.ERROR_INSERT_INTO_PAYMENTS_TABLE;
        } catch (Exception ex) {
            Logger.getLogger(StudentController.class.getName()).log(Level.SEVERE, null, ex);
            return ErrorConstants.ERROR_INSERT_INTO_PAYMENTS_TABLE;
        }
        return MsgConstants.VALUES_INSERTED_INTO_PAYMENTS_TABLE;
    }
    
    @Override
    @GET
    @Path("viewPayment/{studID}")
    @Produces(MediaType.APPLICATION_JSON)
    //@Consumes(MediaType.TEXT_PLAIN)
    public String viewPayment(@PathParam("studID") String studID) {
        System.out.println("<------------------------ViewPayment Service------------------------->");
        System.out.println(studID);
        List<PaymentsModel> listPayments = null;
        String responseTxt = null;
        
        try {
            //JsonObject gradesJson = ReadWriteJson.readJson(grades);
            JsonObject dbConnJson = ReadWriteJson.readJsonFile("createDB.json");
            
            DbConnection dbConn = new DbConnection(dbConnJson);
            
            PaymentsDao dao = new PaymentsDao(dbConn.getConnection());
            listPayments = dao.viewValues(studID);
            
            if(listPayments.isEmpty() ){
                throw new Exception(ErrorConstants.ERROR_VIEWING_INTO_PAYMENTS);
            }
            Gson gson = new Gson();
            responseTxt = gson.toJson(listPayments);
            System.out.println(responseTxt);
            
        } catch (SQLException ex) {
            Logger.getLogger(StudentController.class.getName()).log(Level.SEVERE, null, ex);
            return ErrorConstants.ERROR_VIEWING_INTO_PAYMENTS;
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(StudentController.class.getName()).log(Level.SEVERE, null, ex);
            return ErrorConstants.ERROR_VIEWING_INTO_PAYMENTS;
        } catch (FileNotFoundException ex) {
            Logger.getLogger(StudentController.class.getName()).log(Level.SEVERE, null, ex);
            return ErrorConstants.ERROR_VIEWING_INTO_PAYMENTS;
        } catch (Exception ex) {
            Logger.getLogger(StudentController.class.getName()).log(Level.SEVERE, null, ex);
            return ErrorConstants.ERROR_VIEWING_INTO_PAYMENTS;
        }
        return responseTxt;
    }
    
}
