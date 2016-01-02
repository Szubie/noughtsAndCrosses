
public class Board{
	public static final int boardSize=3;

	int[][] boardList = new int[boardSize][boardSize];

	public Board(){
		buildBoard(boardSize);
	}

	public void buildBoard(int boardSize){
		for(int i=0; i<boardSize; i++){
			int[] innerList = new int[boardSize];
			for(int j=0; j<boardSize; j++){
				innerList[j]=-1;
			}
			this.boardList[i]=innerList;
		}
	}

	public void printBoard(){
		for(int i=0; i<boardSize; i++){
			for(int j=0; j<boardSize; j++){
				System.out.printf("%4s",this.boardList[i][j]+" ");
			}
			System.out.println("\n");
		}
		System.out.println();
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
		for(int i=0; i<boardSize; i++){
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
		for(int i=boardSize-1; i>=0; i--){
			if(boardList[i][j]!=player){
				flag=false;
			}
			j+=1;
		}
		return flag;
	}

	public boolean verticalVictory(int player){
		boolean flag=true;
		for(int i=0; i<boardSize; i++){
			for(int j=0; j<boardSize; j++){
				if(boardList[j][i]!=player){
					flag=false;
				}
			}
			if(flag==true){
				return flag;
			}
			else if(i<boardSize-1){
				flag=true;
			}
		}
		return flag;
	}

	public boolean horizontalVictory(int player){
		boolean flag=false;
		for(int i=0; i<boardSize; i++){
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
