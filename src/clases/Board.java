package clases;

import java.util.StringTokenizer;

public class Board {

	private String start = "*";
	private String end = "$";
	private String player1 = "1";
	private String player2 = "2";
	private String player3 = "3";
	private String ladder = "E";
	private String snake= "S";
	
	public String getStart() {
		return start;
	}
	public String getEnd() {
		return end;
	}
	public String getPlayer1() {
		return player1;
	}
	public String getPlayer2() {
		return player2;
	}
	public String getPlayer3() {
		return player3;
	}
	public String getLadder() {
		return ladder;
	}
	public String getSnake() {
		return snake;
	}
	
	public void placement(){
		ProcessGame process = new ProcessGame();
		if(process.player.getnamePlayer3() != null){
			process.board[9][9] = start+ "," + player1 + "," + player2 + "," + player3;
		}else{
			process.board[9][9] = start+ "," + player1 + "," + player2;
		}
		process.board[0][0] = end;
		StringTokenizer delimiterSnake = new StringTokenizer(process.snakeLadder.getsnakeCoordinate(),",;");
		StringTokenizer delimiterLadder = new StringTokenizer(process.snakeLadder.getladderCoordinate(),",;");
		while(delimiterSnake.hasMoreTokens()){
			Integer x = Integer.valueOf(delimiterSnake.nextToken()).intValue();
			Integer y = Integer.valueOf(delimiterSnake.nextToken()).intValue();
			process.board[x][y] = snake;
		}
		while(delimiterLadder.hasMoreTokens()){
			Integer x = Integer.valueOf(delimiterLadder.nextToken()).intValue();
			Integer y = Integer.valueOf(delimiterLadder.nextToken()).intValue();
			process.board[x][y] = ladder;
		}
	}
	
}
