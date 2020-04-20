/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import service.StudentSvc;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Request;
import model.CreateDB;
import util.DbConnection;

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
        String jsonReq = createDb.toString();
        System.out.println(jsonReq);
        DbConnection dbConn = new DbConnection();
        try {
            dbConn.createConn();
        } catch (SQLException ex) {
            Logger.getLogger(StudentController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "Hello from mihir";
    }
    
}
