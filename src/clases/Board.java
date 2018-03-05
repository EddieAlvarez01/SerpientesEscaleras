package clases;

import java.util.Scanner;

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
	
	public String choicetoAdvance(){
		System.out.println("\n\nElija una de las siguientes opciones para avanzar");
		System.out.println("1.Tirar dados" + "\t\t\t\t\t Escriba f para saltar el turno");
		System.out.println("2.Escribir los espacios a avanzar" + "\t\t Escriba p para pausar");
		Scanner select_option = new Scanner(System.in);
		String option = select_option.next();
		ProcessGame process = new ProcessGame();
		if(process.validate("a", option) == false){
			System.out.println("\n!Error porfavor ingrese una opcion valida");
			option = choicetoAdvance();
		}
		return option;
	}
	
	public String endTurn(){
		System.out.println("\n\nIngrese f si desea finalizar su turno");
		Scanner select_option = new Scanner(System.in);
		String option = select_option.next();
		ProcessGame process = new ProcessGame();
		if(process.validate("d", option) == false){
			System.out.println("\n!Error porfavor ingrese una opcion valida");
			option = endTurn();
		}
		return option;
	}
	
	
	public String gameOver(){
		System.out.println("\n\n¿Desea Iniciar una nueva partida?");
		System.out.println("(y \\ n)");
		Scanner select_option = new Scanner(System.in);
		String option = select_option.next();
		ProcessGame process = new ProcessGame();
		if(process.validate("f", option) == false){
			System.out.println("\n!Error porfavor ingrese una opcion valida");
			option = gameOver();
		}
		return option;
	}
	
}
