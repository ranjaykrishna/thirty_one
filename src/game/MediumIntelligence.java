package game;

public class MediumIntelligence {
	private boolean special;
	
	public MediumIntelligence(){
		special = false;
	}
	
	public Tuple nextStep(int score, int[][] m){
		if (score == 24){
			int number = whichGone(m);
			if (number == 0){
				return pickRandom(m);
			}
			else{
				return findCard(6-number-1, m);
			}
		}
		if (score > 17 && score < 23 && allGone(m)){
			return findCard(twentyThree(score,m)-1,m);
		}
		if (score == 3 || score == 10 || score == 17 || score == 24) special = true;
		else special = false;
		int num = getNumber(score);
		return findCard(num-1,m);
	}

	private int whichGone(int[][] m) {
		for (int i = 0; i < 6; i++){
			if (m[0][i]==0&& m[1][i]==0&&m[2][i]==0&&m[3][i]==0){
				return i;
			}
		}
		return 0;
	}

	private int twentyThree(int score, int[][] m) {
		return 23-score;
	}

	private boolean allGone(int[][] m) {
		for (int i = 0; i < 6; i++){
			if (m[0][i]==0&& m[1][i]==0&&m[2][i]==0&&m[3][i]==0){
				return true;
			}
		}
		return false;
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
