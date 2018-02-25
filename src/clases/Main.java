package clases;

public class Main {
	
	public static void main(String args[]){
		
		ProcessGame process = new ProcessGame();
		String option = process.menu("m", "");
		switch(option){
		case "1":
			process.startGame("p", "");
			break;
		case "3":
			System.exit(0);
			break;
		}
		process.placementSnake("c");
		process.placementLadder("e");
		Board board = new Board();
		board.placement();
	}
}
