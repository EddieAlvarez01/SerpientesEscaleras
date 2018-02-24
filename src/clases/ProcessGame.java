package clases;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ProcessGame {
	
	private String regex;
	Snakke_Ladder snakeLadder = new Snakke_Ladder();
	
	public String menu(String initialValidation, String option){
		System.out.println("\n\n------------------------------------------------------------------------------------------------------");
		System.out.println("                                 "+"SERPIENTES Y ESCALERAS");
		System.out.println("------------------------------------------------------------------------------------------------------");
		System.out.println("                             "+"1.  Iniciar juego");
		System.out.println("                             "+"2.  Regresar al juego");
		System.out.println("                             "+"3.  Salir");
		Scanner select_option = new Scanner(System.in);
		option = select_option.next();
		if(validate(initialValidation, option) == false){
			System.out.println("\n\n!Error porfavor ingrese una opcion valida");
			menu(initialValidation, "");
		}
		return option;
	}
	
	public boolean validate(String initialValidation, String select_option){
		if(initialValidation.equals("m")){
			regex = "[13]";
		}else if(initialValidation.equals("p")){
			regex = "[12]";
		}else if(initialValidation.equals("c")){
			regex = "^([0-8],[0-9];?)+$";
		}
		Pattern coincidence = Pattern.compile(regex);
		Matcher match = coincidence.matcher(select_option);
		if(match.find() == true){
			return true;
		}else{
			return false;
		}
	}
	
	public void startGame(String initialValidation, String option){
		System.out.println("\n\nSeleccione el numero de jugadores");
		System.out.println("1. 2 jugadores");
		System.out.println("2. 3 jugadores");
		Scanner select_option = new Scanner(System.in);
		option = select_option.next();
		if(validate(initialValidation, option) == false){
			System.out.println("\n\n!Error porfavor ingrese una opcion valida");
			startGame(initialValidation, "");
		}
		switch(option){
		case "1":
			System.out.println("\n\nIngrese el nombre del jugador 1");
			String name1 = select_option.next();
			System.out.println("\n\nIngrese el nombre del jugador 2");
			String name2 = select_option.next();
			new Player(name1, name2);
			break;
		case "2":
			System.out.println("\n\nIngrese el nombre del jugador 1");
		    name1 = select_option.next();
			System.out.println("\n\nIngrese el nombre del jugador 2");
			name2 = select_option.next();
			System.out.println("\n\nIngrese el nombre del jugador 3");
			String name3 = select_option.next();
			new Player(name1, name2, name3);
			break;
		}
	}
	
	public void placement(String initialValidation){
		System.out.println("\n\n Ingrese las coordenadas de las serpientes");
		System.out.println("Con el siguiente formato (x1,y1;x2,y2;x3,y3...)");
		System.out.println("Ej.(1,2;2,2;3,2)");
		Scanner select_option = new Scanner(System.in);
		String coordinate = select_option.next();
		if(validate(initialValidation, coordinate) == false){
			System.out.println("\n\n!Error: ingrese una coordenada con el formato indicado, no puede haber serpiente"
					+ " en la casilla incio(10,10), en la casilla final(0,0), ni tampoco en la fila 10 (10,*)");
			placement(initialValidation);
		}
		snakeLadder.setsnakeCoordinate(coordinate);
		
		
		System.out.println("\n\n Ingrese las coordenadas de las escaleras");
	}
	
	public void checkbox (){
		
	}
	
}
