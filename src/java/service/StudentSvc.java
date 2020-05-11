/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import javax.ws.rs.PathParam;
import model.CreateDB;

/**
 *
 * @author mihir
 */
public interface StudentSvc {
    public String createDB(String createDb);
    public String insertGrade(String grades);
    public String viewGrade(String grades);
    public String insertClass(String classes);
    public String insertPay(String classes);
    public String viewPayment(String studID);
    public String viewClasses(String studID);
}
