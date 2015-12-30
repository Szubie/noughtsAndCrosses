import java.util.Scanner;

public class NoughtsAndCrosses{
	Scanner scanner = new Scanner(System.in);
	Player[] playerList = new Player[2];
	boolean gameOver=false;
	Board board = new Board();

	public NoughtsAndCrosses(){
		selectGameType();
		playGame();
	}

	public static void main(String[] args){

	}


	public void selectGameType(){
		System.out.println("Would you like to play against a human or a computer? Press 0 for a human, 1 for a computer opponent");
		String input=scanner.nextLine();
		if(selectGameInputTest(input)==false){
			System.out.println("That wasn't a valid input.");
			selectGameType();
		}
		else{
			createPlayerList(input);
		}
	}

	public boolean selectGameInputTest(String input){
		if(input.equals("1") || input.equals("0")){
			return true;
		}
		else{
			return false;
		}
	}

	public void createPlayerList(String selectedMode){
		if(selectedMode.equals("0")){
			for(int i=0; i<2; i++){
				HumanPlayer human = new HumanPlayer(i, board);
				this.playerList[i]=human;
			}
		}
		else if(selectedMode.equals("1")){
			chooseTurnOrder();
		}
	}

	public void chooseTurnOrder(){
		System.out.println("Would you like to go first or second? 0 for first, 1 for second.");
		String input = scanner.nextLine();
		if(selectGameInputTest(input)==false){
			System.out.println("That wasn't a valid input.");
			chooseTurnOrder();
		}
		else{
			createAIPlayerList(input);
		}

	}

	public void createAIPlayerList(String turnOrderChoice){
		if(turnOrderChoice.equals("0")){
			this.playerList[0]=new HumanPlayer(0, board);
			this.playerList[1]=new AIPlayer(1, board);
		}
		else if(turnOrderChoice.equals("1")){
			this.playerList[0]=new AIPlayer(0, board);
			this.playerList[1]=new HumanPlayer(1, board);
		}
	}

	public void playGame(){
		while(gameOver==false){
			for(Player player: playerList){
				player.takeTurn();
				if(board.victoryAchieved(0)==true){
					gameOver=true;
					System.out.println("Player 0 won!");
					break;
				}
				else if(board.victoryAchieved(1)==true){
					gameOver=true;
					System.out.println("Player 1 won!");
					break;
				}

				else if(board.checkBoardFull()==true){
					gameOver=true;
					System.out.println("It's a draw!");
					break;
				}
			}

		}
	}
}

class Board{
	int[][] boardList = new int[3][3];

	public Board(){
		buildBoard();
	}

	public void buildBoard(){
		for(int i=0; i<3; i++){
			int[] innerList = new int[3];
			for(int j=0; j<3; j++){
				innerList[j]=-1;
			}
			this.boardList[i]=innerList;
		}
	}

	public void printBoard(){
		for(int i=0; i<3; i++){
			for(int j=0; j<3; j++){
				System.out.printf("%4s",this.boardList[i][j]+" ");
			}
			System.out.println("\n");
		}
	}

	public void setBoard(int xAxis, int yAxis, int value){ //y axis first, but player input takes x axis first and reverses it.
		this.boardList[yAxis][xAxis]=value;
	}

	public boolean victoryAchieved(int player){
		if(diagonalVictory(player)||verticalVictory(player)||horizontalVictory(player)){
			return true;
		}
		return false;
	}

	public boolean diagonalVictory(int player){
		if(checkAscending(player)||checkDescending(player)){
			return true;
		}
		return false;
	}

	public boolean checkAscending(int player){
		boolean flag=true;
		int j=0;
		for(int i=0; i<3; i++){
			if(boardList[i][j]!=player){
				flag=false;
			}
			j+=1;
		}
		return flag;
	}

	public boolean checkDescending(int player){
		boolean flag=true;
		int j=0;
		for(int i=2; i>0; i--){
			if(boardList[i][j]!=player){
				flag=false;
			}
			j+=1;
		}
		return flag;
	}

	public boolean verticalVictory(int player){
		boolean flag=true;
		for(int i=0; i<3; i++){
			for(int j=0; j<3; j++){
				if(boardList[j][i]!=player){
					flag=false;
				}
			}
			if(flag==true){
				return flag;
			}
			else if(i<2){
				flag=true;
			}
		}
		return flag;
	}

	public boolean horizontalVictory(int player){
		boolean flag=false;
		for(int i=0; i<3; i++){
			if(checkList(player, boardList[i])==true){
				flag=true;
				return flag;
			}
		}
		return flag;
	}

	public boolean checkList(int player, int[] array){
		boolean flag=true;
		for(int num: array){
			if(num!=player){
				flag=false;
			}
		}
		return flag;
	}

	public boolean checkBoardFull(){
		boolean flag=true;

		for(int[] intList : boardList){
			for(int num : intList){
				if(num==-1){
					flag=false;
				}
			}
		}
		return flag;
	}
	
}

abstract class Player{
	public static final int firstPLayer=0;
	public static final int secondPlayer=1;
	public int playerNum;
	public Board board;

	public Player(int playerNum, Board board){
		this.playerNum=playerNum;
		this.board=board;
	}

	public String toString(){
		return "Player "+Integer.toString(playerNum);
	}

	public abstract void takeTurn();
}

class AIPlayer extends Player{
	public AIPlayer(int playerNum, Board board){
		super(playerNum, board);
	}

	@Override
	public String toString(){
		return "AIPlayer "+Integer.toString(playerNum);
	}

	@Override
	public void takeTurn(){
		System.out.println("Not yet implemented.");
	}
}

class HumanPlayer extends Player{
	Scanner scanner = new Scanner(System.in);

	public HumanPlayer(int playerNum, Board board){
		super(playerNum, board);
	}

	@Override
	public void takeTurn(){
		board.printBoard();
		printTurn();
		int[] move = takePlayerInput();
		while(testMove(move)==false){
			System.out.println("Invalid move.");
			move = takePlayerInput();
		}
		board.setBoard(move[0], move[1], playerNum);
	}

	public int[] takePlayerInput(){
		int[] coordinateOutput=new int[2];

		System.out.println("Input a move, using coordinate vlaues separated by a space.");
		String input = scanner.nextLine();
		String[] inputList = input.split(" ");
		if(testInput(inputList)==false){
			System.out.println("Invalid input");
			return takePlayerInput();
		}
		else{
			for(int i=0; i<2; i++){
				coordinateOutput[i]=Integer.parseInt(inputList[i]);
			}
		}
		return coordinateOutput;
	}

	public boolean testInput(String[] inputList){
		if(inputList.length>2 || inputList.length<2){
			return false;
		}
		for(int i=0; i<inputList.length; i++){
			try{
				Integer.parseInt(inputList[i]);
			}
			catch(Exception e){
				return false;
			}
		}
		return true;
	}

	public boolean testMove(int[] move){
		if(board.boardList[move[1]][move[0]]!=-1){
			return false;
		}
		else{
			return true;
		}
	}

	public void printTurn(){
		System.out.println("Player "+playerNum+"'s turn to play.");
	}


}

