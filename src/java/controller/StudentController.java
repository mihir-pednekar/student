/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import service.StudentSvc;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Request;
import model.CreateDB;
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
        System.out.println(createDb);
        try {
            JsonObject jsonObj = ReadWriteJson.readJson(createDb);
            ReadWriteJson.writeJson(jsonObj , "createDB.json");
            DbConnection dbConn = new DbConnection(jsonObj);
            dbConn.createConn();
        } catch (SQLException ex) {
            Logger.getLogger(StudentController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "hello from mihir";
    }
    
}
