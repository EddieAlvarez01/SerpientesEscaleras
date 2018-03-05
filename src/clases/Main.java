package clases;

import java.io.IOException;

public class Main {
	
	private static boolean winner = false;
	private static boolean reset = false;
	
	public static void main(String args[]) throws IOException{
		
		ProcessGame process = new ProcessGame();
		String optionPause = null;
		boolean endTurn = false;
		String option = process.menu("m", "");
		do{
			optionPause = null;
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
			winner = false;
			reset = false;
			while(winner == false){
				optionPause = null;
				process.printBoard();
				if(endTurn == true){
					option = board.endTurn();
					switch(option){
					case "f":
						process.turnPlayer("");
						process.printBoard();
						break;
					case "F":
						process.turnPlayer("");
						process.printBoard();
						break;
					}
				}
				option = board.choicetoAdvance();
				switch(option){
				case "1":
					int dice = player.throwDice();
					System.out.println("\nDado = " + " " + dice);
					winner = process.advanceinBoard(dice);
					endTurn = true;
					if(winner == true){
						reset = true;
					}
					break;
				case "2":
					winner = process.advanceinBoard(player.advance());
					endTurn = true;
					if(winner == true){
						reset = true;
					}
					break;
				case "f":
					process.turnPlayer("");
					endTurn = false;
					break;
				case "F":
					process.turnPlayer("");
					endTurn = false;
					break;
				case "p":
					process.cls();
					option = process.menu("g", "");
					optionPause = "";
					break;
				case "P":
					process.cls();
					option = process.menu("g", "");
					optionPause = "";
					break;
				}
				if(optionPause != null){
					switch(option){
					case "1":
						endTurn = false;
						winner = true;
						reset = false;
						option = "1";
						process.cleanBoard();
						break;
					case "2":
						winner = false;
						endTurn = false;
						break;
					case "3":
						System.exit(0);
						break;
					}
				}
			}
			if(reset != false){
				process.winningMessage();
				if(board.gameOver().equals("y")){
					reset = false;
					option = "1";
					process.cleanBoard();
				}
			}
			
		}while(reset == false);
	}
	
}
