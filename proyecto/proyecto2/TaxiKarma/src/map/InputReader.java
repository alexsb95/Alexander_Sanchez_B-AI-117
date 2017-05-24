/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package map;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

/**
 *
 * @author Alex
 */
public class InputReader {
    private char[][] charMatrix;
    
    public void readFile(String pFilename){
        
        try (BufferedReader br = new BufferedReader(new FileReader(pFilename))) {

            String sCurrentLine;
            while ((sCurrentLine = br.readLine()) != null) {
                    System.out.println(sCurrentLine);
            }

        } catch (IOException e) {
                e.printStackTrace();
        }
    }
    
     public void readMatrix(){
       
            
    }
}
