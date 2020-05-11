/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author mihir
 */
public class ClassesModel {
    
    private String cId, cTitle, Id, fName;

    public ClassesModel() {
    }

    public ClassesModel(String cId, String cTitle, String Id, String fName) {
        this.cId = cId;
        this.cTitle = cTitle;
        this.Id = Id;
        this.fName = fName;
    }

    public String getcId() {
        return cId;
    }

    public void setcId(String cId) {
        this.cId = cId;
    }

    public String getcTitle() {
        return cTitle;
    }

    public void setcTitle(String cTitle) {
        this.cTitle = cTitle;
    }

    public String getId() {
        return Id;
    }

    public void setId(String Id) {
        this.Id = Id;
    }

    public String getfName() {
        return fName;
    }

    public void setfName(String fName) {
        this.fName = fName;
    }
    
}
