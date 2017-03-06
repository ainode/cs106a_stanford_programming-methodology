import java.util.ArrayList;


public class Soduko {
	public static final int MATRIX_DIMENTION = 9;
	
	public static void main(String[] args){
		fillMatrix();
		System.out.println(checkUpperLeft());
	}
	
	public static void fillMatrix(){
		int contents = 1;
		for(int i = 0; i < 3; i ++)
			for(int j = 0; j < 3; j++){
				matrix[i][j] = contents;System.out.println(contents);
				contents++;
			}
	}
	
	public static boolean checkUpperLeft(){
		for (int i = 0; i < 3; i++){
			for (int j = 0; j < 3; j++){
				if(upperLeftList.contains(matrix[i][j]))
					return false;
				else{
					upperLeftList.add(matrix[i][j]);
				}
			}
		}
		return true;
	}
	
	public static ArrayList<Integer> upperLeftList = new ArrayList<>();  
	public static int[][] matrix = new int[9][9];
}
