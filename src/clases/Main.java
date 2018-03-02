package clases;

import java.io.IOException;

public class Main {
	
	private static boolean winner = false;
	
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
		Board board = new Board();
		Player player = new Player();
		while(winner = true){
			process.printBoard();
			option = board.choicetoAdvance();
			switch(option){
			case "1":
				int dice = player.throwDice();
				System.out.println("\nDado = " + " " + dice);
				System.out.println("\nPresione enter para aceptar....");
				System.in.read();
				winner = process.advanceinBoard(dice);
				break;
			case "2":
				winner = process.advanceinBoard(player.advance());
				break;
			case "f":
				process.turnPlayer("");
				break;
			}
		}
	}
}
