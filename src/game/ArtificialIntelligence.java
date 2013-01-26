package game;

public class ArtificialIntelligence {
	private boolean special;
	private int turn = 1;
	
	public ArtificialIntelligence(){
		special = false;
		turn = 1;
	}
	
	public Tuple nextStep(int score, int[][] m){
		//handle A problem
		if (turn==2&&score==4){
			turn++;
			return findCard(5-1,m);
		}
		else if (turn==3&&score==10&&numGone(1,m)==3){
			turn++;
			return findCard(6-1,m);
		}
		else if(turn==4&&score==17&&numGone(1,m)==4){
			turn++;
			return findCard(6-1,m);
		}
		//handle 2 problem
		else if (score==5&&numGone(2,m)==2){
			turn++;
			return findCard(3-1,m);
		}
		else if (score<15&&score>8&&numGone(2,m)>=2){
			turn++;
			return findCard(15-score-1,m);
		}
		else if (score>15&&score<22&&numGone(2,m)>=3){
			turn++;
			return findCard(22-score-1,m);
		}
		else if (score>22&&numGone(2,m)==4){
			turn++;
			return findCard(29-score-1,m);
		}
		//handle 3 problem
		else if (score==3){
			return findCard(4-1,m);
		}
		else if (score==10&&numGone(3,m)==2){
			return findCard(4-1,m);
		}
		else if(score==13&&numGone(3,m)==3){
			return findCard(4-1,m);
		}
		else if(score<21&&numGone(3,m)>=3){
			return findCard(21-score-1,m);
		}
		//handle 5 problem
		else if (numGone(5,m)==3&&score<19){
			turn++;
			return findCard(19-score-1,m);
		}
		//default
		else{
			turn++;
			return normalStep(score,m);
		}
	}

	private int numGone(int i, int[][] m){ 
		return 4-(m[0][i-1]+m[1][i-1]+m[2][i-1]+m[3][i-1]);
	}

	public Tuple normalStep(int score,int[][] m){
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
