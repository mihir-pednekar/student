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
                + "Id BIGINT NOT NULL, "
                + "fName VARCHAR(30), "
                + "lName VARCHAR(30), "
                + "regDate DATE, "
                + "PRIMARY KEY (Id))";
            
    public static final String CREATE_CLASSES_TABLE = "CREATE TABLE Classes( "
                + "cId BIGINT NOT NULL, "
                + "cTitle VARCHAR(30), "
                + "cDesc VARCHAR(30), "
                + "PRIMARY KEY (cId))";
            
    public static final String CREATE_ENROLLEMTS_TABLE = "CREATE TABLE Enrollments( "
                + "eId BIGINT NOT NULL, "
                + "classId BIGINT NOT NULL, "
                + "studId BIGINT NOT NULL, "
                + "FOREIGN KEY (classId) REFERENCES Classes(cId), "
                + "FOREIGN KEY (studId) REFERENCES Students(Id)) ";
    
    public static final String CREATE_GRADES_TABLE = "CREATE TABLE Grades( "
                + "studId BIGINT NOT NULL, "
                + "grade REAL NOT NULL, "
                + "FOREIGN KEY (studId) REFERENCES Students(Id)) ";
    
    public static final String INSERT_INTO_GRADES = "INSERT INTO Grades ("
         + "studId, grade) VALUES ";
    
    public static final String INSERT_INTO_STUDENT = "INSERT INTO Students ("
         + "Id, fName, lName, regDate) VALUES ";
    
    public static final String SELECT_GRADES_JOIN_STUDENT = "SELECT s.Id, s.fName, s.lName, s.regDate, g.grade FROM Students s "
            + "INNER JOIN Grades g "
            + "ON s.Id = g.studId "
            + "WHERE s.Id = ";
}
