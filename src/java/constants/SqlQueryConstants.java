/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package constants;

/**
 *
 * @author mihir
 */
public class SqlQueryConstants {
    public static final String CREATE_STUDENT_TABLE = "CREATE TABLE Students( "
                + "Id INT NOT NULL, "
                + "fName VARCHAR(30), "
                + "lName VARCHAR(30), "
                + "PRIMARY KEY (Id))";
            
    public static final String CREATE_CLASSES_TABLE = "CREATE TABLE Classes( "
                + "cId INT NOT NULL, "
                + "cTitle VARCHAR(30), "
                + "cDesc VARCHAR(30), "
                + "PRIMARY KEY (cId))";
            
    public static final String CREATE_ENROLLEMTS_TABLE = "CREATE TABLE Enrollments( "
                + "eId INT NOT NULL, "
                + "classId INT NOT NULL, "
                + "studId INT NOT NULL, "
                + "FOREIGN KEY (classId) REFERENCES Classes(cId), "
                + "FOREIGN KEY (studId) REFERENCES Students(Id)) ";
    
    public static final String CREATE_GRADES_TABLE = "CREATE TABLE Grades( "
                + "studId INT NOT NULL, "
                + "grade REAL NOT NULL, "
                + "FOREIGN KEY (studId) REFERENCES Students(Id)) ";
    
    public static final String INSERT_INTO_GRADES = "INSERT INTO Grades VALUES ";
         //+ "(1234, 2.44)";
            
}
