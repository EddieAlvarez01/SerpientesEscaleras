package clases;

import java.io.IOException;

public class Main {
	
	public static void main(String args[]) throws IOException{
		
		ProcessGame process = new ProcessGame();
		String option = process.menu("m", "");
		switch(option){
		case "1":
			process.namePlayers(process.startGame("p", ""));
			break;
		case "3":
			System.exit(0);
			break;
		}
		process.placementSnake("c");
		process.placementLadder("e");
		process.placement();
		process.turnPlayer("r");
		process.printTurns();
		process.printBoard();
		Board board = new Board();
		option = board.choicetoAdvance();
		Player player = new Player();
		switch(option){
		case "1":
			process.advanceinBoard(player.throwDice());
			break;
		case "2":
			process.advanceinBoard(player.advance());
			break;
		}
	}
}
