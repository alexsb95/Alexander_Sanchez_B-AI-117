import java.util.Scanner;

public class Solution {

	private static ASearch aSearch;
	
	public static void main (String [ ] args) {
		aSearch = new ASearch();
		
 		Scanner scanner = new Scanner(System.in);
		readInput(scanner);
		scanner.close();

 	 }
	 
	 private static void readInput(Scanner pScanner){
		 
		 readPacman(pScanner);
		 readFood(pScanner);
		 readSizeMatrix(pScanner);
		 readMatrix(pScanner);
		 
		
		 System.out.println("pacman x: "+aSearch.getPacmanPos().getX()+" y: "+aSearch.getPacmanPos().getY());  
		 System.out.println("food x: "+aSearch.getFoodPos().getX()+" y: "+aSearch.getFoodPos().getY()); 
		 System.out.println("matrix x: "+aSearch.getMatrixSize().getX()+" y: "+aSearch.getMatrixSize().getY()); 
		 System.out.println("matrix "+aSearch.getMatrix()[0][0]);

	 }
	 
	 private static void readPacman(Scanner pScanner){ 
	        int x = -1;
	        int y = -1;
	        
	        try {
	        	x = pScanner.nextInt();
	        	y = pScanner.nextInt();
	        }
	        catch (Exception e) {
	            System.out.println("No se pudo convertitr a numero");
	        }

	        if(x >= 0 && y >= 0){
	        	aSearch.setPacmanPos(new position(x, y));
	        }
	 }
	 
	 private static void readFood(Scanner pScanner){
 
	        int x = -1;
	        int y = -1;
	        
	        try {
	        	x = pScanner.nextInt();
	        	y = pScanner.nextInt();
	        }
	        catch (Exception e) {
	            System.out.println("No se pudo convertitr a numero");
	        }

	        if(x >= 0 && y >= 0){
	        	aSearch.setFoodPos(new position(x, y));
	        }
	 }
	 
	 private static void readSizeMatrix(Scanner pScanner){
		 
	        int x = -1;
	        int y = -1;
	        
	        try {
	        	x = pScanner.nextInt();
	        	y = pScanner.nextInt();
	        }
	        catch (Exception e) {
	            System.out.println("No se pudo convertitr a numero");
	        }

	        if(x >= 0 && y >= 0){
	        	aSearch.setMatrixSize(new position(x, y));
	        }
	 }
	 
	 private static void readMatrix(Scanner pScanner){
		 ma newMatrix = new ma();
	     aSearch.setMatrix(newMatrix.matrixPac);
	        
	 }
	
}

class ASearch{
	private position pacmanPos;
	private position foodPos;
	private position matrixSize;
	private char[][] matrix;
	
	public position getPacmanPos() {
		return pacmanPos;
	}
	public void setPacmanPos(position pacmanPos) {
		this.pacmanPos = pacmanPos;
	}
	public position getFoodPos() {
		return foodPos;
	}
	public void setFoodPos(position foodPos) {
		this.foodPos = foodPos;
	}
	public position getMatrixSize() {
		return matrixSize;
	}
	public void setMatrixSize(position arraySize) {
		this.matrixSize = arraySize;
	}
	public char[][] getMatrix() {
		return matrix;
	}
	public void setMatrix(char[][] matrix) {
		this.matrix = matrix;
	}
	
	
	
}

class position {
	private int x;
	private int y;
	
	position (int pX, int pY){
		x =pX;
		y = pY;
	}
	
	public int getX() {
		return x;
	}
	public void setX(int x) {
		this.x = x;
	}
	public int getY() {
		return y;
	}
	public void setY(int y) {
		this.y = y;
	}
	
}

class ma{
	public char matrixPac[][] =    
	       {{'%','%','%','%','%','%','%','%','%','%','%','%','%','%','%','%','%','%','%','%','%','%','%','%','%','%','%','%','%','%','%','%','%','%','%','%','%'},
	       {'%','-','-','-','-','-','-','-','%','-','%','-','%','-','-','-','-','-','-','-','-','-','-','-','%','-','-','-','%','-','-','-','-','-','%','-','%'},
	       {'%','-','%','%','%','%','%','%','%','-','%','-','%','%','%','-','%','-','%','%','%','-','%','%','%','-','%','%','%','%','%','%','%','-','%','-','%'},
	       {'%','-','-','-','-','-','-','-','%','-','-','-','-','-','-','-','%','-','%','-','-','-','-','-','%','-','-','-','-','-','%','-','%','-','-','-','%'},
	       {'%','%','%','%','%','-','%','%','%','%','%','-','%','%','%','-','%','-','%','-','%','-','%','%','%','-','%','%','%','%','%','-','%','-','%','%','%'},
	       {'%','-','-','-','%','-','%','-','%','-','%','-','-','-','%','-','%','-','%','-','%','-','-','-','%','-','%','-','-','-','%','-','%','-','-','-','%'},
	       {'%','-','%','%','%','-','%','-','%','-','%','-','%','%','%','-','%','%','%','%','%','-','%','%','%','-','%','-','%','%','%','-','%','%','%','-','%'},
	       {'%','-','-','-','-','-','-','-','%','-','-','-','-','-','%','-','-','-','%','-','-','-','%','-','-','-','-','-','%','-','%','-','%','-','-','-','%'},
	       {'%','%','%','-','%','%','%','%','%','%','%','%','%','-','%','%','%','%','%','%','%','-','%','%','%','-','%','%','%','-','%','-','%','-','%','-','%'},
	       {'%','-','-','-','-','-','-','-','-','-','-','-','-','-','%','-','-','-','-','-','-','-','%','-','%','-','-','-','%','-','-','-','-','-','%','-','%'},
	       {'%','-','%','-','%','%','%','%','%','-','%','-','%','%','%','-','%','-','%','-','%','%','%','-','%','-','%','%','%','-','%','%','%','-','%','-','%'},
	       {'%','-','%','-','%','-','-','-','-','-','%','-','%','-','%','-','%','-','%','-','-','-','-','-','%','-','-','-','%','-','%','-','%','-','%','-','%'},
	       {'%','-','%','-','%','-','%','%','%','%','%','%','%','-','%','-','%','%','%','%','%','%','%','%','%','-','%','%','%','-','%','-','%','%','%','-','%'},
	       {'%','-','%','-','%','-','%','-','-','-','-','-','%','-','-','-','%','-','-','-','-','-','%','-','-','-','-','-','%','-','-','-','%','-','-','-','%'},
	       {'%','%','%','-','%','%','%','-','%','-','%','%','%','%','%','-','%','%','%','%','%','-','%','%','%','-','%','%','%','-','%','%','%','%','%','-','%'},
	       {'%','-','-','-','-','-','%','-','%','-','%','-','-','-','-','-','%','-','%','-','-','-','-','-','%','-','%','-','-','-','%','-','%','-','%','-','%'},
	       {'%','-','%','-','%','-','%','-','%','-','%','%','%','-','%','%','%','-','%','%','%','-','%','%','%','-','%','-','%','-','%','-','%','-','%','-','%'},
	       {'%','-','%','-','%','-','%','-','%','-','-','-','-','-','-','-','-','-','-','-','-','-','-','-','-','-','%','-','%','-','%','-','-','-','-','-','%'},
	       {'%','%','%','-','%','%','%','%','%','%','%','-','%','-','%','-','%','%','%','%','%','-','%','%','%','-','%','-','%','%','%','-','%','%','%','%','%'},
	       {'%','-','-','-','-','-','-','-','%','-','%','-','%','-','%','-','-','-','-','-','%','-','-','-','%','-','-','-','-','-','%','-','%','-','-','-','%'},
	       {'%','%','%','%','%','-','%','-','%','-','%','%','%','%','%','%','%','%','%','-','%','%','%','%','%','%','%','%','%','%','%','-','%','-','%','%','%'},
	       {'%','-','-','-','%','-','%','-','-','-','-','-','-','-','-','-','-','-','%','-','%','-','-','-','-','-','%','-','-','-','%','-','%','-','-','-','%'},
	       {'%','-','%','%','%','-','%','%','%','%','%','-','%','%','%','%','%','%','%','%','%','-','%','%','%','%','%','-','%','-','%','-','%','%','%','-','%'},
	       {'%','-','%','-','-','-','%','-','-','-','-','-','-','%','-','-','-','-','-','-','-','-','%','-','-','-','-','-','%','-','-','-','-','-','-','-','%'},
	       {'%','-','%','-','%','-','%','%','%','%','%','-','%','%','%','-','%','-','%','-','%','-','%','-','%','%','%','%','%','%','%','%','%','%','%','%','%'},
	       {'%','-','%','-','%','-','-','-','%','-','-','-','-','-','%','-','%','-','%','-','%','-','-','-','-','-','-','-','%','-','-','-','%','-','%','-','%'},
	       {'%','-','%','-','%','%','%','-','%','%','%','-','%','-','%','-','%','-','%','%','%','%','%','%','%','%','%','-','%','%','%','-','%','-','%','-','%'},
	       {'%','-','%','-','-','-','%','-','%','-','-','-','%','-','%','-','%','-','-','-','%','-','%','-','-','-','%','-','%','-','%','-','-','-','-','-','%'},
	       {'%','-','%','%','%','-','%','%','%','-','%','%','%','%','%','-','%','%','%','-','%','-','%','-','%','%','%','%','%','-','%','-','%','%','%','%','%'},
	       {'%','-','-','-','-','-','-','-','%','-','-','-','%','-','-','-','-','-','%','-','%','-','-','-','-','-','%','-','-','-','%','-','%','-','-','-','%'},
	       {'%','%','%','-','%','-','%','%','%','%','%','-','%','%','%','%','%','-','%','%','%','-','%','%','%','-','%','-','%','%','%','-','%','-','%','%','%'},
	       {'%','-','%','-','%','-','%','-','%','-','%','-','%','-','%','-','-','-','-','-','%','-','%','-','-','-','%','-','%','-','-','-','%','-','%','-','%'},
	       {'%','-','%','-','%','%','%','-','%','-','%','-','%','-','%','-','%','%','%','%','%','%','%','%','%','-','%','-','%','-','%','-','%','-','%','-','%'},
	       {'%','-','-','-','%','-','-','-','%','-','-','-','%','-','-','-','-','-','-','-','-','-','-','-','-','-','-','-','-','-','%','-','-','-','-','-','%'},
	       {'%','-','%','-','%','-','%','-','%','%','%','-','%','%','%','-','%','%','%','%','%','%','%','-','%','%','%','-','%','%','%','-','%','%','%','-','%'},
	       {'%','.','%','-','%','-','%','-','-','-','-','-','-','-','%','-','-','-','%','-','-','-','-','-','-','-','%','-','-','-','%','-','%','-','-','P','%'},
	       {'%','%','%','%','%','%','%','%','%','%','%','%','%','%','%','%','%','%','%','%','%','%','%','%','%','%','%','%','%','%','%','%','%','%','%','%','%'}};
	
}