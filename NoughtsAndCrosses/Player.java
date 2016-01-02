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

	int thisPlayer = this.playerNum;
	int opponentPlayer = 1-thisPlayer;

	public AIPlayer(int playerNum, Board board){
		super(playerNum, board);
		updateboard();
	}

	@Override
	public String toString(){
		return "AIPlayer "+Integer.toString(playerNum);
	}

	@Override
	public void takeTurn(){
		printTurn();
		int[] move = miniMax(this.board, this.thisPlayer, 9, Integer.MIN_VALUE, Integer.MAX_VALUE); //Higher depth required to never lose.
		board.setBoard(move[1], move[2], this.thisPlayer);
		board.printBoard();
	}

	public int[] miniMax(Board board, int player, int depth, int alpha, int beta){  //with alpha-beta pruning
		ArrayList<int[]> validMoves = findValidMoves(board);
		int currentScore;
		int[] bestMove=new int[2];
		
		if(validMoves.size()==0 || depth<=0){
			currentScore=(evaluateBoard(board, player));
			return new int[] {currentScore, bestMove[0], bestMove[1]};
		}
		else{
			for(int[] moves : validMoves){
				board.setBoard(moves[0], moves[1], player);

				if(player==thisPlayer){
					currentScore=miniMax(board, opponentPlayer, depth-1, alpha, beta)[0];
					if(currentScore> alpha){
						alpha=currentScore;
						bestMove[0]=moves[0];
						bestMove[1]=moves[1];
						if(alpha>=beta){
							board.setBoard(moves[0], moves[1], -1); //reset the prospective move when pruning
							break;
						}
					}
				}
				else{
					currentScore = miniMax(board, this.thisPlayer, depth-1, alpha, beta)[0];
					if(currentScore<beta){
						beta=currentScore;
						bestMove[0]=moves[0];
						bestMove[1]=moves[1];
						if(beta<=alpha){
							board.setBoard(moves[0], moves[1], -1); //reset the prospective move when pruning
							break;
						}
					}
				}
				//Backtrack
				board.setBoard(moves[0], moves[1], -1);
				//Cut branches

			}
			return new int[] {(player == thisPlayer) ? alpha : beta, bestMove[0], bestMove[1]};
		}

	}

	public void updateboard(){
		for(int i=0; i<board.boardList.length; i++){
			for(int j=0; j<board.boardList.length; j++){				
				board.boardList[i][j]=board.boardList[i][j];
			}
		}
	}

	/**
	* Takes in a copied board and evaluates it. */
	public int evaluateBoard(Board board, int player){
		int evaluation = 0;
		if(player==this.thisPlayer){
			if(board.victoryAchieved(opponentPlayer)==true){
				evaluation=-100;
				return evaluation;
			}
			else if(board.victoryAchieved(player)==true){
				evaluation=100;
				return evaluation;
			}
			else{
				return evaluation;
			}
		}
		else{
			if(board.victoryAchieved(opponentPlayer)==true){
				evaluation=-100;
				return evaluation;
			}
			else if(board.victoryAchieved(thisPlayer)==true){
				evaluation=100;
				return evaluation;
			}
			else{
				return evaluation;
			}
		}	
	}

	public ArrayList<int[]> findValidMoves(Board board){
		ArrayList<int[]> validMoves = new ArrayList<int[]>();

		if(board.victoryAchieved(0) || board.victoryAchieved(1)){
			return validMoves;
		}
		
		for(int i=0; i<board.boardList.length; i++){
			for(int j=0; j<board.boardList.length; j++){
				if(board.boardList[i][j]==-1){
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

		System.out.println("Input a move, using coordinate values separated by a space.");
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
