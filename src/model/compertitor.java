package model;


public class compertitor {

	public int [][]block;
	public int score;
	public boolean state;
	public boolean isOline;
	
	public compertitor(){
		Init();
	}
	
	public void Init() {
		block = new int [10][20];
		score = 0;
		state = false;
		isOline = false;
	}
	
	public String getScore() {
		return "" + score;
	}
	
	public void setOtherWholeBlockMap(int[][] blockMap, int s) {
		score = s;
		for (int i = 0; i < blockMap.length; i++) {
			for (int j = 0; j < blockMap[i].length; j++) {
				block[i][j] = blockMap[i][j];
			}
		}
	}
	
}
