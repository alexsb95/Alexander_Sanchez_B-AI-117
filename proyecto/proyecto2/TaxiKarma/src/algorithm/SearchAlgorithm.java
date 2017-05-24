/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package algorithm;

import java.util.ArrayList;

/**
 *
 * @author Alex
 */
public interface SearchAlgorithm {
    public void setInitialCell(Cell initialCell);
    public Cell getInitialCell();
    public void setObjetiveCell(Cell pDestination);    
    public Cell getObjetiveCell();
    public void setMatrix(Cell[][] pMatrix, int pXLen, int pYLen);
    public Cell[][] getMatrix();
    
    public ArrayList<Cell> calculatePath(int pIniX, int pIniY, int pFinX, int pFinY);
}
