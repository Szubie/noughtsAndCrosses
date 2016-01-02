import java.util.ArrayList;

public class TestClass{
	public static void main(String[] args){
		/*Board test = new Board();
		test.buildBoard();
		test.setBoard(1,0,1);
		test.setBoard(1,1,1);
		test.setBoard(1,2,1);
		test.printBoard();
		System.out.println(test.verticalVictory(1));
		System.out.println(test.horizontalVictory(1));

		test.setBoard(0,0,1);
		test.setBoard(2,0,1);
		test.printBoard();
		System.out.println(test.horizontalVictory(1));

		System.out.println("Diagonal Testing");
		System.out.println(test.diagonalVictory(1));
		test.setBoard(2,2,1);
		test.printBoard();
		System.out.println(test.diagonalVictory(1));
		test.setBoard(2,2,-1);
		test.setBoard(0,2,1);
		test.printBoard();
		System.out.println(test.diagonalVictory(1));
		System.out.println(test.victoryAchieved(1));

		NoughtsAndCrosses game = new NoughtsAndCrosses();
		int[] coordinates = game.takePlayerInput();
		for(int num: coordinates){
			System.out.print(num+" ");
		}
		System.out.println();
		*/

		//NoughtsAndCrosses game = new NoughtsAndCrosses();
//		for(Player player : game.playerList){
//			System.out.println(player);
//		}
		/*ArrayList<int[]> output = game.board.findValidMoves();
		for(int i=0; i<output.size(); i++){
			System.out.print(output.get(i)[0]+" ");
			System.out.println(output.get(i)[1]);
		}
		*/

		//Board board = new Board();
		//board.setBoard(0,0,0);
		//board.setBoard(0,1,0);
		//board.setBoard(1,0,0);
		//board.setBoard(2,0,1);
		//board.setBoard(1,1,1);
		//board.setBoard(1,2,1);
		//board.setBoard(2,1,0);
		
		//board.printBoard();

		//board.setBoard(0,2,0);
		//board.printBoard();

		//board.setBoard(0,2,1);
		//board.printBoard();

		//board.setBoard(2,2,0);
		//board.printBoard();

		//AIPlayer AI  = new AIPlayer(0, board);
		//System.out.println(AI.evaluateBoard(AI.board, AI.thisPlayer));

		//ArrayList<int[]> output = AI.findValidMoves(AI.board);
		//for(int i=0; i<output.size(); i++){
		//	System.out.print(output.get(i)[0]+" ");
		//	System.out.println(output.get(i)[1]);
		//}

		//int[] output2 =AI.miniMax(board, AI.thisPlayer, 2);
		//for(int num:output2){
		//	System.out.print(num+" ");
		//}

		//NoughtsAndCrosses game = new NoughtsAndCrosses();

		/*
		Board board = new Board();
		board.setBoard(0,0,1);
		board.setBoard(1,0,0);
		board.setBoard(2,0,1);
		board.setBoard(1,1,0);
		board.setBoard(0,2,0);
		board.printBoard();
		
		//System.out.println(board.victoryAchieved(0));
		AIPlayer AI  = new AIPlayer(1, board);
		int[] move= AI.miniMax(board, 1, 2, Integer.MIN_VALUE, Integer.MAX_VALUE);
		board.setBoard(move[1], move[2], 1);
		board.printBoard(); */
		//System.out.println(AI.evaluateBoard(AI.board, AI.thisPlayer));


		NoughtsAndCrosses game = new NoughtsAndCrosses();

		
	}
}