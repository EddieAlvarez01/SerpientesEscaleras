package clases;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.StringTokenizer;
import java.io.IOException;
import java.util.Random;

public class ProcessGame {
	
	private String regex;
	Snakke_Ladder snakeLadder = new Snakke_Ladder();
	public String[][] boardPrint = new String[10][10];
	Player player;
	private String inTurn;

	private int[] predefinedTurns;
	int turn;
	int round = 1;
	
	public void cls(){
		for(int i=0; i<2; i++){
			System.out.println("\n");
		}
	}
	public String getInTurn() {
		return inTurn;
	}
	
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
			if(option.equals("2")){
				System.out.println("\n\n!Error no hay ningun juego guardado");
				option = menu(initialValidation, "");
			}else{
				System.out.println("\n\n!Error porfavor ingrese una opcion valida");
				option = menu(initialValidation, "");
			}
		}
		return option;
	}
	
	public boolean validate(String initialValidation, String select_option){
		if(initialValidation.equals("m")){
			regex = "^[13]$";
		}else if(initialValidation.equals("p")){
			regex = "^[12]$";
		}else if(initialValidation.equals("c")){
			regex = "^([0-8],[0-9];?)+$";
		}else if(initialValidation.equals("e")){
			regex = "^([1-9],[0-9];?)+$";
		}else if(initialValidation.equals("a")){
			regex = "^[12fFpP]$";
		}else if(initialValidation.equals("g")){
			regex = "^[123]$";
		}else if(initialValidation.equals("f")){
			regex = "^[yn]$";
		}
		Pattern coincidence = Pattern.compile(regex);
		Matcher match = coincidence.matcher(select_option);
		if(match.find() == true){
			return true;
		}else{
			return false;
		}
	}
	
	public String startGame(String initialValidation, String option){
		System.out.println("\n\nSeleccione el numero de jugadores");
		System.out.println("1. 2 jugadores");
		System.out.println("2. 3 jugadores");
		Scanner select_option = new Scanner(System.in);
		option = select_option.next();
		if(validate(initialValidation, option) == false){
			System.out.println("\n\n!Error porfavor ingrese una opcion valida");
			option = startGame(initialValidation, "");
		}
		return option;
	}
	
	public void namePlayers(String option){
		Scanner select_option = new Scanner(System.in);
		switch(option){
		case "1":
			System.out.println("\n\nIngrese el nombre del jugador 1");
			String name1 = select_option.next();
			System.out.println("\n\nIngrese el nombre del jugador 2");
			String name2 = select_option.next();
			player = new Player(name1, name2);
			break;
		case "2":
			System.out.println("\n\nIngrese el nombre del jugador 1");
		    name1 = select_option.next();
			System.out.println("\n\nIngrese el nombre del jugador 2");
			name2 = select_option.next();
			System.out.println("\n\nIngrese el nombre del jugador 3");
			String name3 = select_option.next();
			player = new Player(name1, name2, name3);
			break;
		}
	}
	
	public void placementSnake(String initialValidation){
		System.out.println("\n\n Ingrese las coordenadas de las serpientes");
		System.out.println("Con el siguiente formato (x1,y1;x2,y2;x3,y3...)");
		System.out.println("Ej.(1,2;2,2;3,2)");
		System.out.println("nota: no puede haber serpiente en la casilla incio(9,9), en la casilla final(0,0)"
				+ ", ni tampoco en la fila 9 (9,*)");
		Scanner select_option = new Scanner(System.in);
		String coordinate = select_option.next();
		if(validate(initialValidation, coordinate) == false){
			System.out.println("\n\n!Error: ingrese una coordenada con el formato indicado");
			placementSnake(initialValidation);
			coordinate = snakeLadder.getsnakeCoordinate();
			
		}else if(checkboxSnake(coordinate) == false){
			System.out.println("\n\n!Error: ingrese una coordenada con el formato indicado");
			placementSnake(initialValidation);
			coordinate = snakeLadder.getsnakeCoordinate();
		}
		snakeLadder.setsnakeCoordinate(coordinate);
	}
	
	public void placementLadder(String initialValidation){
		System.out.println("\n\n Ingrese las coordenadas de las Escaleras");
		System.out.println("Con el siguiente formato (x1,y1;x2,y2;x3,y3...)");
		System.out.println("Ej.(1,2;2,2;3,2)");
		System.out.println("nota: no puede haber escalera en la casilla inicio(9,9), en la casilla final(0,0), en la fila 0 (0,*)"
				+ "\nen donde este colocada una serpiente y abajo de una serpiente (generaria bucle infinito)");
		System.out.println("Coordenadas ingresadas de la(s) serpiente(s):"+""+snakeLadder.getsnakeCoordinate());
		Scanner select_option = new Scanner(System.in);
		String coordinate = select_option.next();
		if(validate(initialValidation, coordinate) == false){
			System.out.println("\n\n!Error: ingrese una coordenada con el formato");
			placementLadder(initialValidation);
			coordinate = snakeLadder.getladderCoordinate();
		}else if(checkboxLadder(coordinate) == false){
			System.out.println("\n\n!Error: ingrese una coordenada con el formato indicado");
			placementLadder(initialValidation);
			coordinate = snakeLadder.getladderCoordinate();
		}
		snakeLadder.setladderCoordinate(coordinate);
	}
	
	public boolean checkboxSnake(String coordinate){
		if(coordinate.equals("0,0")){
			return false;
		}
		StringTokenizer st = new StringTokenizer(coordinate,";");
		while(st.hasMoreTokens()){
			String xy = st.nextToken();
			if(xy.equals("0,0")){
				return false;
			}
		}
		return true;
	}
	
	public boolean checkboxLadder(String coordinate){
		if(coordinate.equals("9,9")||coordinate.equals(snakeLadder.getsnakeCoordinate())){
			return false;
		}
		StringTokenizer st = new StringTokenizer(coordinate,";");
		StringTokenizer st1 = new StringTokenizer(snakeLadder.getsnakeCoordinate(),";");
		StringTokenizer nt = new StringTokenizer(coordinate,",;");
		StringTokenizer nt1 = new StringTokenizer(snakeLadder.getsnakeCoordinate(),",;");
		String[] snake = new String[st1.countTokens()];
		String[] ladder = new String[st.countTokens()];
		Integer[] snakeCor = new Integer[nt1.countTokens()]; 
		Integer[] ladderCor = new Integer[nt.countTokens()]; 
		int ii = 0;
		int kk = 0;
		while(st.hasMoreTokens()){
			String xy = st.nextToken();
			if(xy.equals("9,9")){
				return false;
			}
			ladder[ii] = xy;
			ii++;
		}
		ii=0;
		while(st1.hasMoreTokens()){
			String xy = st1.nextToken();
			snake[kk] = xy;
			kk++;
		}
		for(int i=0; i<ladder.length; i++){
			for(int k=0; k<snake.length; k++){
				if(ladder[i].equals(snake[k])){
					return false;
				}
			}
		}
		kk=0;
		while(nt.hasMoreTokens()){
			String xy = nt.nextToken();
			xy = xy + nt.nextToken();
			ladderCor[ii] = Integer.valueOf(xy).intValue();
			ii++;
		}
		while(nt1.hasMoreTokens()){
			String xy = nt1.nextToken();
			xy = xy + nt1.nextToken();
			snakeCor[kk] = Integer.valueOf(xy).intValue()+ 10;
			kk++;
		}
		for(int i=0; i<ladderCor.length; i++){
			for(int k=0; k<snakeCor.length; k++){
				if(ladderCor[i] == snakeCor[k] && ladderCor[i] != null && snakeCor[i] != null){
					return false;
				}
			}
		}
		return true;
	}
	
	public void placement(){
		Board board = new Board();
		if(player.getnamePlayer3() != null){
			boardPrint[9][9] = board.getStart()+ "," + board.getPlayer1() + "," + board.getPlayer2() + "," + board.getPlayer3();
		}else{
			boardPrint[9][9] = board.getStart()+ "," + board.getPlayer1() + "," + board.getPlayer2();
		}
		boardPrint[0][0] = board.getEnd();
		StringTokenizer delimiterSnake = new StringTokenizer(snakeLadder.getsnakeCoordinate(),",;");
		StringTokenizer delimiterLadder = new StringTokenizer(snakeLadder.getladderCoordinate(),",;");
		while(delimiterSnake.hasMoreTokens()){
			Integer x = Integer.valueOf(delimiterSnake.nextToken()).intValue();
			Integer y = Integer.valueOf(delimiterSnake.nextToken()).intValue();
			boardPrint[x][y] = board.getSnake();
		}
		while(delimiterLadder.hasMoreTokens()){
			Integer x = Integer.valueOf(delimiterLadder.nextToken()).intValue();
			Integer y = Integer.valueOf(delimiterLadder.nextToken()).intValue();
			boardPrint[x][y] = board.getLadder();
		}
		for(int i=0; i<boardPrint.length; i++){
			for(int k=0; k<boardPrint[i].length; k++){
				if(boardPrint[i][k] == null){
					boardPrint[i][k] = "";
				}
			}
		}
	}
	
	public void turnPlayer(String moment){
		if(player.getnamePlayer3() != null){
			if(moment.equals("r")){
				predefinedTurns = new int[3];
				for(int i=0; i<predefinedTurns.length; i++){
					predefinedTurns[i] = i;
				}
				Random random = new Random();
				for(int i = predefinedTurns.length; i>0; i--){
					int position = random.nextInt(i);
					int decomposition = predefinedTurns[i-1];
					predefinedTurns[i-1] = predefinedTurns[position];
					predefinedTurns[position] = decomposition;
				}
				if(predefinedTurns[0] == 0){
					inTurn = player.getnamePalyer1();
					
				}else if(predefinedTurns[0] == 1){
					inTurn = player.getnamePlayer2();
				}else{
					inTurn = player.getnamePlayer3();
				}
				turn = predefinedTurns[0];
			}else{
				if(turn == predefinedTurns[2]){
					if(predefinedTurns[0] == 0){
						inTurn = player.getnamePalyer1();	
					}else if(predefinedTurns[0] == 1){
						inTurn = player.getnamePlayer2();
					}else{
						inTurn = player.getnamePlayer3();
					}
					round++;
					turn = predefinedTurns[0];
				}else if(turn == predefinedTurns[0]){
					if(predefinedTurns[1] == 0){
						inTurn = player.getnamePalyer1();
							
					}else if(predefinedTurns[1] == 1){
						inTurn = player.getnamePlayer2();
					}else{
						inTurn = player.getnamePlayer3();
					}
					round++;
					turn = predefinedTurns[1];
				}else{
					if(predefinedTurns[2] == 0){
						inTurn = player.getnamePalyer1();
							
					}else if(predefinedTurns[2] == 1){
						inTurn = player.getnamePlayer2();
					}else{
						inTurn = player.getnamePlayer3();
					}
					round++;
					turn = predefinedTurns[2];
				}
			}
		}else{
			if(moment.equals("r")){
				predefinedTurns = new int[2];
				for(int i=0; i<2; i++){
					predefinedTurns[i] = i;
				}
				Random random = new Random();
				for(int i = predefinedTurns.length; i>0; i--){
					int position = random.nextInt(i);
					int decomposition = predefinedTurns[i-1];
					predefinedTurns[i-1] = predefinedTurns[position];
					predefinedTurns[position] = decomposition;
				}
				if(predefinedTurns[0] == 0){
					inTurn = player.getnamePalyer1();
					
				}else if(predefinedTurns[0] == 1){
					inTurn = player.getnamePlayer2();
				}
				turn = predefinedTurns[0];
			}else{
				if(turn == predefinedTurns[1]){
					if(predefinedTurns[0] == 0){
						inTurn = player.getnamePalyer1();
							
					}else if(predefinedTurns[0] == 1){
						inTurn = player.getnamePlayer2();
					}
					round++;
					turn = predefinedTurns[0];
				}else{
					if(predefinedTurns[1] == 0){
						inTurn = player.getnamePalyer1();
							
					}else if(predefinedTurns[1] == 1){
						inTurn = player.getnamePlayer2();
					}
					round++;
					turn = predefinedTurns[1];
				}
			}
		}
	}
	
	public void printTurns() throws IOException{
		if(player.getnamePlayer3() != null){
			for(int i=0; i<3; i++){
				if(predefinedTurns[i] == 0 ){
					System.out.println("\n\nTurno "+(i+1)+ ":" +  " " + player.getnamePalyer1());
				}
				if(predefinedTurns[i] == 1 ){
					System.out.println("\n\nTurno "+ (i+1) +":" + " " + player.getnamePlayer2());
				}
				if(predefinedTurns[i] == 2 ){
					System.out.println("\n\nTurno "+ (i+1) +":" + " " + player.getnamePlayer3());
				}
			}
		}else{
			for(int i=0; i<2; i++){
				if(predefinedTurns[i] == 0 ){
					System.out.println("\n\nTurno "+(i+1)+ ":" + " " + player.getnamePalyer1());
				}
				if(predefinedTurns[i] == 1 ){
					System.out.println("\n\nTurno "+ (i+1) +":" + " " + player.getnamePlayer2());
				}
			}
		}
	}
	
	public void printBoard(){
		cls();
		System.out.println("Turno del jugador " + ":" + " "+ inTurn + "\t\t" 
		                   + "Ronda:" + " "+ round);
		System.out.println("                                                                                          ");
		for(int i=0; i<boardPrint.length; i++){
			System.out.println("---------------------------------------------------------------------------------"
					+ "--------------------------------------------------------------------------------");
			for(int k=0; k<boardPrint[i].length; k++){
				System.out.print("|" + "\t" + boardPrint[i][k]  +"\t" );
			}
			System.out.println("|");
			if(i == 9){
				System.out.println("---------------------------------------------------------------------------------"
						+ "--------------------------------------------------------------------------------");
			}
		}
	}
	
	public boolean advanceinBoard(int boxes){
		Board board = new Board();
		int x = 0;
		int y = 0;
		boolean changeMovement = true;
		if(turn == predefinedTurns[0]){
			for(int i=0; i<boardPrint.length; i++){
				for(int k=0; k<boardPrint[i].length; k++){
					if(boardPrint[i][k] != ""){
						if(boardPrint[i][k].equals(board.getPlayer1())){
							 x = i;
							 y = k;
							 boardPrint[i][k] = boardPrint[i][k].replace("1", "");
							 break;
						}
						StringTokenizer players = new StringTokenizer(boardPrint[i][k], ",");
						while(players.hasMoreTokens()){
							if(players.nextToken().equals(board.getPlayer1())){
								 x = i;
								 y = k;
								Pattern p = Pattern.compile("(,?1)||(1,?)");
								Matcher m = p.matcher(boardPrint[i][k]);
								if(m.find()){
									boardPrint[i][k] = m.replaceAll("");
								}
							}
						}
					}
				}
			}
			for(int i = 1; i<=boxes; i++){
				y = y-1;
				if(y == 0 && x == 0){
					return true;
				}
				if(y == 0){
					x = x - 1;
					y = 9;
					i++;
				}
				if(x<0){
					x = 0;
				}
			}
			do{
				if(boardPrint[x][y].equals(board.getSnake())){
					x = x + 1;
					changeMovement = false;
				}else if(boardPrint[x][y].equals(board.getLadder())){
					x = x -1;
					changeMovement = false;
				}else{
					changeMovement = true;
				}
				if(x == 0 && y==0){
					return true;
				}
			}while(changeMovement == false);
			if(boardPrint[x][y] != ""){
				boardPrint[x][y] = boardPrint[x][y] + ",1";
			}else{
				boardPrint[x][y] = "1";
			}
			turnPlayer("");
		}else if(turn == predefinedTurns[1]){
			for(int i=0; i<boardPrint.length; i++){
				for(int k=0; k<boardPrint[i].length; k++){
					if(boardPrint[i][k] != ""){
						if(boardPrint[i][k].equals(board.getPlayer2())){
							 x = i;
							 y = k;
							 boardPrint[i][k] = boardPrint[i][k].replace("2", "");
							 break;
						}
						StringTokenizer players = new StringTokenizer(boardPrint[i][k], ",");
						while(players.hasMoreTokens()){
							if(players.nextToken().equals(board.getPlayer2())){
								 x = i;
								 y = k;
								Pattern p = Pattern.compile("(,?2)");
								Matcher m = p.matcher(boardPrint[i][k]);
								if(m.find()){
									boardPrint[i][k] = m.replaceAll("");
								}
							}
						}
					}
				}
			}
			for(int i = 1; i<=boxes; i++){
				y = y-1;
				if(y == 0 && x == 0){
					return true;
				}
				if(y == 0){
					x = x - 1;
					y = 9;
					i++;
				}
				if(x<0){
					x = 0;
				}
			}
			do{
				if(boardPrint[x][y].equals(board.getSnake())){
					x = x + 1;
					changeMovement = false;
				}else if(boardPrint[x][y].equals(board.getLadder())){
					x = x -1;
					changeMovement = false;
				}else{
					changeMovement = true;
				}
				if(x == 0 && y==0){
					return true;
				}
			}while(changeMovement == false);
			if(boardPrint[x][y] != ""){
				boardPrint[x][y] = boardPrint[x][y] + ",2";
			}else{
				boardPrint[x][y] = "2";
			}
			turnPlayer("");
		}else{
			for(int i=0; i<boardPrint.length; i++){
				for(int k=0; k<boardPrint[i].length; k++){
					if(boardPrint[i][k] != ""){
						if(boardPrint[i][k].equals(board.getPlayer3())){
							 x = i;
							 y = k;
							 boardPrint[i][k] = boardPrint[i][k].replace("3", "");
							 break;
						}
						StringTokenizer players = new StringTokenizer(boardPrint[i][k], ",");
						while(players.hasMoreTokens()){
							if(players.nextToken().equals(board.getPlayer3())){
								 x = i;
								 y = k;
								Pattern p = Pattern.compile("(,?3)");
								Matcher m = p.matcher(boardPrint[i][k]);
								if(m.find()){
									boardPrint[i][k] = m.replaceAll("");
								}
							}
						}
					}
				}
			}
			for(int i = 1; i<=boxes; i++){
				y = y-1;
				if(y == 0 && x == 0){
					return true;
				}
				if(y == 0){
					x = x - 1;
					y = 9;
					i++;
				}
				if(x<0){
					x = 0;
				}
			}
			do{
				if(boardPrint[x][y].equals(board.getSnake())){
					x = x + 1;
					changeMovement = false;
				}else if(boardPrint[x][y].equals(board.getLadder())){
					x = x -1;
					changeMovement = false;
				}else{
					changeMovement = true;
				}
				if(x == 0 && y==0){
					return true;
				}
			}while(changeMovement == false);
			if(boardPrint[x][y] != ""){
				boardPrint[x][y] = boardPrint[x][y] + ",3";
			}else{
				boardPrint[x][y] = "3";
			}
			turnPlayer("");
			turnPlayer("");
		}
		return false;
	}
	
	public void winningMessage(){
		cls();
		System.out.println("----------Felicidades------------");
		System.out.println("El ganador es el jugador :" + " " +  inTurn);
	}
	
	public void cleanBoard(){
		round = 1;
		for(int i=0; i<boardPrint.length; i++){
			for(int k=0; k<boardPrint[i].length; k++){
				boardPrint[i][k] = null;
			}
		}
	}
}
