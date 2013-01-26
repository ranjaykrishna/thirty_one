package game;

public class TestBot {
	public static void main(String[] args){
		for(int i = 1; i < 31; i++){
			ArtificialIntelligence ai = new ArtificialIntelligence();
			int num = ai.getNumber(i);
			System.out.print(num+", ");
		}
	}
}
