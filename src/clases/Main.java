package clases;

public class Main {
	
	public static void main(String args[]){
		
		ProcessGame process = new ProcessGame();
		String option = process.menu("m", "");
		switch(option){
		case "1":
			process.startGame("p", "");
			break;
		case "2":
			break;
		}
	}
}
