/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Algorithm;

import Matrix.Cell;
import java.util.LinkedList;

/**
 *
 * @author Alex
 */
public class BFS {
    
    private int iLen;
    private int jLen;
    private Cell[][] matrix;
    
    public BFS(Cell[][] pMatrix, int pILen, int pJLen){
        iLen = pILen;
        jLen = pJLen;
        matrix = pMatrix;
    }
 
        // prints BFS traversal from a given source s
    public LinkedList<Cell> BFS(int pI, int pJ)
    {
        LinkedList<Cell> route = new LinkedList<Cell>();
        boolean visited[][] = new boolean[jLen][jLen];
 
        // Create a queue for BFS
        LinkedList<Cell> queue = new LinkedList<Cell>();
 
        // Mark the current node as visited and enqueue it
        visited[pI][pJ] = true;
        queue.add(matrix[pI][pJ]);
 
        while (queue.size() != 0)
        {
            // Dequeue a vertex from queue and print it
            Cell current = queue.poll();
            
 
            // Get all adjacent vertices of the dequeued vertex s
            // If a adjacent has not been visited, then mark it
            // visited and enqueue it
            
            if(matrix[current.getX() - 1][current.getY()] != null){
                visited[current.getX() - 1][current.getY()] = true;
                queue.add(matrix[current.getX() - 1][current.getY()]);
                route.add(matrix[current.getX() + 1][current.getY()]);
            }

            if(matrix[current.getX()][current.getY() - 1] != null){
                visited[current.getX()][current.getY() - 1] = true;
                queue.add(matrix[current.getX()][current.getY() - 1]);
                route.add(matrix[current.getX() + 1][current.getY()]);
            }

            if( matrix[current.getX() + 1][current.getY()] != null){
                visited[current.getX() + 1][current.getY()] = true;
                queue.add(matrix[current.getX() + 1][current.getY()]);
                route.add(matrix[current.getX() + 1][current.getY()]);
            }

            if(matrix[current.getX()][current.getY() + 1] !=  null){
                visited[current.getX()][current.getY() + 1] = true;
                queue.add(matrix[current.getX()][current.getY() + 1]);
                route.add(matrix[current.getX()][current.getY() + 1]);
            }

            
        }
        
        return route;
    }
}
