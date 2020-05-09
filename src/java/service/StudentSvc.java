/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import model.CreateDB;

/**
 *
 * @author mihir
 */
public interface StudentSvc {
    public String createDB(String createDb);
    public String insertGrade(String grades);
    public String viewGrade(String grades);
}
