package clases;

import java.util.Random;
import java.util.Scanner;

public class Player {
	
	private String namePalyer1;
	private String namePlayer2;
	private String namePlayer3;
	
	
	public void setnamePalyer1(String namePalyer1){
		 this.namePalyer1 = namePalyer1;
	}
	
	public String getnamePalyer1(){
		return namePalyer1;
	}
	
	public void setnamePlayer2(String namePlayer2){
		 this.namePlayer2 = namePlayer2;
	}
	
	public String getnamePlayer2(){
		return namePlayer2;
	}
	
	public void setnamePlayer3(String namePlayer3){
		 this.namePlayer3 = namePlayer3;
	}
	
	public String getnamePlayer3(){
		return namePlayer3;
	}
	
	public Player(String namePalyer1, String namePlayer2){
		this.namePalyer1 = namePalyer1;
		this.namePlayer2 = namePlayer2;
	}
	
	public Player(String namePalyer1, String namePlayer2, String namePlayer3){
		this.namePalyer1 = namePalyer1;
		this.namePlayer2 = namePlayer2;
		this.namePlayer3 = namePlayer3;
	}
	
	public Player(){
		
	}
	
	public int throwDice(){
		Random random = new Random(System.currentTimeMillis());
		int dice = random.nextInt(13);
		if(dice == 0){
			dice = 1;
		}
		random.setSeed(System.currentTimeMillis());
		return dice;
	}
	
	public int advance(){
		System.out.println("Escriba el numero de espacios a avanzar");
		Scanner select_option = new Scanner(System.in);
		String box = select_option.next();
		ProcessGame process = new ProcessGame();
		if(process.validate("b", box) == false){
			System.out.println("\n!Error porfavor ingrese una opcion valida");
			box = String.valueOf(advance());
		}
		return Integer.parseInt(box);
	}
	
}
