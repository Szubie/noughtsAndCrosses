import java.util.Scanner;
import java.util.ArrayList;

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
		board.printBoard();
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
