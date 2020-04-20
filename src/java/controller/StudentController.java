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
import util.DbConnection;

/**
 *
 * @author mihir
 */
@Path("student-rest")
public class StudentController implements StudentSvc{

    @Override
    @GET
    @Path("createDB")
    @Produces(MediaType.APPLICATION_JSON)
    public String createDB() {
        
        DbConnection dbConn = new DbConnection();
        try {
            dbConn.createConn();
        } catch (SQLException ex) {
            Logger.getLogger(StudentController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "Hello from mihir";
    }
    
}
