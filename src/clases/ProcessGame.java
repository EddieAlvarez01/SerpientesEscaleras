package clases;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ProcessGame {
	
	public void menu(boolean back){
		System.out.println("\n\n------------------------------------------------------------------------------------------------------");
		System.out.println("                                 "+"SERPIENTES Y ESCALERAS");
		System.out.println("------------------------------------------------------------------------------------------------------");
		System.out.println("                             "+"1.  Iniciar juego");
		System.out.println("                             "+"2.  Regresar al juego");
		System.out.println("                             "+"3.  Salir");
		Scanner select_option = new Scanner(System.in);
		String option = select_option.next();
		if(validate(back, option) == false){
			System.out.println("\n\n!Error porfavor ingrese una opcion valida");
			menu(back);
		}
	}
	
	public boolean validate(boolean back, String select_option){
		String regex = "[13]";
		Pattern coincidence = Pattern.compile(regex);
		Matcher match = coincidence.matcher(select_option);
		if(match.find() == true){
			return true;
		}else{
			return false;
		}
	}
}
