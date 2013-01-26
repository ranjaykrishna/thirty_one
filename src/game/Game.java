package game;

public class Game {
	private int total = 0;
	private int player = 1;
	private int[][] gameMatrix = new int[4][6];
	
	public Game(){
		for (int i = 0; i < 4; i++){
			for (int j = 0; j < 6; j ++){
				gameMatrix[i][j] = 1;
			}
		}
	}
	
	public void add(int x){
		total += x;
		if (player == 1){
			player = 2;
		}
		else{
			player = 1;
		}
	}
	
	/**return 0 if the game is not over. Otherwise, it returns the player that won.*/
	public int checkStatus(){
		if (total < 31){ return 0; }
		else if (total > 31){ return player;}
		else if (player == 1){ return 2;}
		else { return 1;}
	}

	public String getScore() {
		return total+"";
	}
	
	public void updateScore(int x,int column){
		add(column+1);
		gameMatrix[x][column] = 0;
	}
	
	public Tuple aiChoice(ArtificialIntelligence ai){
		Tuple tuple = ai.nextStep(total, gameMatrix);
		gameMatrix[tuple.first][tuple.second] = 0;
		return tuple;
	}
	public void setTotal(int x){
		total = x;
		player = 1;
	}
}
