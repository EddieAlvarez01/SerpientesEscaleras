package clases;

public class Snakke_Ladder {
	
	public String snakeCoordinate;
	public String ladderCoordinate;
	
	public void setsnakeCoordinate(String snakeCoordinate){
		this.snakeCoordinate = snakeCoordinate;
	}
	
	public String getsnakeCoordinate(){
		return snakeCoordinate;
	}
	
	public void setladderCoordinate(String ladderCoordinate){
		this.ladderCoordinate = ladderCoordinate;
	}
	
	public String getladderCoordinate(){
		return ladderCoordinate;
	}
	
	public Snakke_Ladder(String snakeCoordinate, String ladderCoordinate){
		this.snakeCoordinate = snakeCoordinate;
		this.ladderCoordinate = ladderCoordinate;
	}
	
}
