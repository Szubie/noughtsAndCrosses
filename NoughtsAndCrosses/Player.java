import java.util.ArrayList;
import java.util.Scanner;

public abstract class Player{
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

	public void printTurn(){
		System.out.println("Player "+playerNum+"'s turn to play.");
	}
}

class AIPlayer extends Player{
	
	Board copiedBoard = new Board();

	int thisPlayer = this.playerNum;
	int opponentPlayer = 1-thisPlayer;

	public AIPlayer(int playerNum, Board board){
		super(playerNum, board);
		updateCopiedBoard();
	}

	@Override
	public String toString(){
		return "AIPlayer "+Integer.toString(playerNum);
	}

	@Override
	public void takeTurn(){
		printTurn();
		//validMoves=board.findValidMoves();
		//board.setBoard(validMoves.get(0)[0], validMoves.get(0)[0], this.playerNum);
		//board.printBoard();
	}

	public int[] miniMax(Board copiedBoard, int player){
		ArrayList<int[]> validMoves = findValidMoves(copiedBoard);
		int bestScore;
		int currentScore=0;
		int[] bestMove=new int[2];

		//int bestScore = (player == this.thisPlayer) ? Integer.MIN_VALUE : Integer.MAX_VALUE;
		
		if(player==this.thisPlayer){
			bestScore=Integer.MIN_VALUE;
		}else{
			bestScore=Integer.MAX_VALUE;
		}
		System.out.println(bestScore);

		if(validMoves.size()==0){
			bestScore=(evaluateBoard(copiedBoard));
			System.out.println("Assigning bestScore (FINAL).");
			System.out.println(bestScore);
		}
		else{
			for(int[] moves : validMoves){
				copiedBoard.setBoard(moves[0], moves[1], player);
				copiedBoard.printBoard();

				if(player==thisPlayer){
					currentScore=miniMax(copiedBoard, opponentPlayer)[0];
					if(currentScore>bestScore){
						System.out.println("Assigning bestScore. (MAX)");
						bestScore=currentScore;
						bestMove[0]=moves[0];
						bestMove[1]=moves[1];
						System.out.println(bestScore);
						System.out.println(bestMove[0]);
						System.out.println(bestMove[1]);
					}
				}
				else{
					currentScore = miniMax(copiedBoard, thisPlayer)[0];
					if(currentScore<bestScore){
						System.out.println("Assigning bestScore. (MIN)");
						bestScore=currentScore;
						bestMove[0]=moves[0];
						bestMove[1]=moves[1];
					}
				}
				//Backtrack
				copiedBoard.setBoard(moves[0], moves[1], -1);
			}
		}

		return new int[] {bestScore, bestMove[0], bestMove[1]};
	}

	public void updateCopiedBoard(){
		for(int i=0; i<copiedBoard.boardList.length; i++){
			for(int j=0; j<copiedBoard.boardList.length; j++){				
				copiedBoard.boardList[i][j]=board.boardList[i][j];
			}
		}
	}

	/**
	* Takes in a copied board and evaluates it. */
	public int evaluateBoard(Board copiedBoard){
		int evaluation = 0;
		if(copiedBoard.victoryAchieved(thisPlayer)==true){
			evaluation+=100;
			return evaluation;
		}
		else if(copiedBoard.victoryAchieved(opponentPlayer)==true){
			evaluation-=100;
			return evaluation;
		}
		else{
			return evaluation;
		}
	}

	public ArrayList<int[]> findValidMoves(Board copiedBoard){
		ArrayList<int[]> validMoves = new ArrayList<int[]>();
		
		for(int i=0; i<copiedBoard.boardList.length; i++){
			for(int j=0; j<copiedBoard.boardList.length; j++){
				if(copiedBoard.boardList[i][j]==-1){
					int[] coordinates = new int[2];
					coordinates[0] = j; //reversed
					coordinates[1] = i;
					validMoves.add(coordinates);
				}
			}
		}

		return validMoves;
	}


}

class HumanPlayer extends Player{
	Scanner scanner = new Scanner(System.in);

	public HumanPlayer(int playerNum, Board board){
		super(playerNum, board);
	}

	@Override
	public void takeTurn(){
		printTurn();
		int[] move = takePlayerInput();
		while(testMove(move)==false){
			System.out.println("Invalid move.");
			move = takePlayerInput();
		}
		board.setBoard(move[0], move[1], playerNum);
		board.printBoard();
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


}
