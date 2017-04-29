/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package taxi;

/**
 *
 * @author Alex
 */
public class TaxiCab {
    private int currentPosI;
    private int currentPosJ;

    public int getCurrentPosI() {
        return currentPosI;
    }

    public void setCurrentPosI(int currentPosI) {
        this.currentPosI = currentPosI;
    }

    public int getCurrentPosJ() {
        return currentPosJ;
    }

    public void setCurrentPosJ(int currentPosJ) {
        this.currentPosJ = currentPosJ;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getDesI() {
        return desI;
    }

    public void setDesI(int desI) {
        this.desI = desI;
    }

    public int getDesJ() {
        return desJ;
    }

    public void setDesJ(int desJ) {
        this.desJ = desJ;
    }
    private String status;
    private int desI;
    private int desJ;
}
