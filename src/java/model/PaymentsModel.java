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
public class PaymentsModel {
    private String pId, amount, studId;

    public PaymentsModel() {
    }

    public PaymentsModel(String pId, String amount, String studId) {
        this.pId = pId;
        this.amount = amount;
        this.studId = studId;
    }

    public String getpId() {
        return pId;
    }

    public void setpId(String pId) {
        this.pId = pId;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getStudId() {
        return studId;
    }

    public void setStudId(String studId) {
        this.studId = studId;
    }
    
    
}
