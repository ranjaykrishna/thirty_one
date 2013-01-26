package game;

public class EasyIntelligence {
	private boolean special;
	
	public EasyIntelligence(){
		special = false;
	}
	
	public Tuple nextStep(int score, int[][] m){
		if (score == 3 || score == 10 || score == 17 || score == 24) special = true;
		else special = false;
		int num = getNumber(score);
		return findCard(num-1,m);
	}

	public Tuple findCard(int num, int[][] m) {
		if (m[1][num] == 1){ return new Tuple(1,num);}
		else if (m[2][num] == 1){ return new Tuple(2,num);}
		else if (m[3][num] == 1){ return new Tuple(3,num);}
		else if (m[0][num] == 1){ return new Tuple(0,num);}
		else return pickRandom(m);
	}

	private Tuple pickRandom(int[][] m) {
		for (int i = 0; i < 4; i ++){
			for (int j = 0; j < 6; j ++){
				if (m[i][j] == 1) return new Tuple(i,j);
			}
		}
		return null;
	}

	public int getNumber(int score) {
		if (special == false){
			if (score == 1){return 2;}
			else if (score == 2){return 1;}
			else if ((score-3)%7 == 0){special = true; return getNumber(score);}
			else {return 7 - (score-3)%7;}
			
		}
		else{
			if (score == 24) return 4;
			else return 3;
		}
	}
	
}
