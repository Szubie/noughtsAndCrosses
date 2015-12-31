
public class Board{
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
			System.out.println("diagonalVictory");
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
		for(int i=2; i>=0; i--){
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
				System.out.println("verticalVictory");
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
				System.out.println("horizontalVictory");
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
