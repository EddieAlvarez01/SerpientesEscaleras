package clases;

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
	
	public void namePlayer2(String namePlayer2){
		 this.namePlayer2 = namePlayer2;
	}
	
	public String namePlayer2(){
		return namePlayer2;
	}
	
	public void namePlayer3(String namePlayer3){
		 this.namePlayer3 = namePlayer3;
	}
	
	public String namePlayer3(){
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
}
