/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;


/**
 *
 * @author Alex
 */
public class InputReader {
    
    public char[][] readFileMatrix(String pFilename){
        ArrayList<String> tempStr = new ArrayList<>();
        char[][] matrix = null;
        
        try (BufferedReader br = new BufferedReader(new FileReader(pFilename))) {
            String sCurrentLine;
            while ((sCurrentLine = br.readLine()) != null) {
                tempStr.add(sCurrentLine);
            }
            
            // Transfrma el arraylist en un char[][]
            matrix = new char[tempStr.size()][tempStr.get(0).length()];
            for(int index = 0; index < tempStr.size(); index++){
                matrix[index] = tempStr.get(index).toCharArray();
            }
            
        } catch (IOException e) {
                e.printStackTrace();
        }
        
        return matrix;
    }
    
        public ArrayList<String> readFileSrings(String pFilename){
        ArrayList<String> stringList = new ArrayList<>();
    
        try (BufferedReader br = new BufferedReader(new FileReader(pFilename))) {
            String sCurrentLine;
            while ((sCurrentLine = br.readLine()) != null) {
                stringList.add(sCurrentLine);
            }
            
        } catch (IOException e) {
                e.printStackTrace();
        }
        
        return stringList;
    }
   
}
