import java.util.ArrayList;
//import java.util.LinkedList;
import java.util.List;

public class Game {
	public char[][] board;
	public Game parent;
	public int cost;

	public Game(Game parent, char[][] board, int cost) {
		char[][] temp = new char[board.length][board[0].length];
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[0].length; j++) {
				temp[i][j] = board[i][j];
			}
		}
		this.parent = parent;
		this.board = temp;
		this.cost = cost;
	}

	public void print() {
		for (int i = 0; i < this.board.length; i++) {
			for (int j = 0; j < this.board[0].length; j++) {
				System.out.print(this.board[i][j] + "  ");
			}
			System.out.println();
		}

		System.out.println();
		System.out.println("Cost: " + this.cost);
		System.out.println("===============================================");
	}

	public boolean isFinal() {
		for (int i = 0; i < this.board.length; i++) {
			for (int j = 0; j < this.board[0].length; j++) {
				if (this.board[i][j] == 'o')
					return false;
			}
		}
		return true;
	}

	public boolean CanMoveUp() {
		for (int i = 0; i < this.board.length; i++) {
			for (int j = 0; j < this.board[0].length; j++) {
				if (this.board[i][j] == 'o' || this.board[i][j] == '$' || this.board[i][j] == 'U'
						|| this.board[i][j] == '0') {
					if (i > 0 && (this.board[i - 1][j] == '-'
							|| ((this.board[i - 1][j] == 'o' || this.board[i - 1][j] == '$') && this.board[i][j] == 'U'))) {
						return true;
					}
				}
			}
		}
		return false;
	}

	public boolean CanMoveDown() {
		for (int i = 0; i < this.board.length; i++) {
			for (int j = 0; j < this.board[0].length; j++) {
				if (this.board[i][j] == 'o' || this.board[i][j] == '$' || this.board[i][j] == 'U'
						|| this.board[i][j] == '0') {
					if (i < this.board.length - 1 && (this.board[i + 1][j] == '-'
							|| ((this.board[i][j] == 'o' || this.board[i][j] == '$') && this.board[i + 1][j] == 'U'))) {
						return true;
					}
				}
			}
		}
		return false;
	}

	public boolean CanMoveLeft() {
		for (int i = 0; i < this.board.length; i++) {
			for (int j = 0; j < this.board[0].length; j++) {
				if (this.board[i][j] == 'o' || this.board[i][j] == '$' || this.board[i][j] == 'U'
						|| this.board[i][j] == '0') {
					if (j > 0 && this.board[i][j - 1] == '-') {
						return true;
					}
				}
			}
		}
		return false;
	}

	public boolean CanMoveRight() {
		for (int i = 0; i < this.board.length; i++) {
			for (int j = 0; j < this.board[0].length; j++) {
				if (this.board[i][j] == 'o' || this.board[i][j] == '$' || this.board[i][j] == 'U'
						|| this.board[i][j] == '0') {
					if (j < this.board[0].length - 1 && this.board[i][j + 1] == '-') {
						return true;
					}
				}
			}
		}
		return false;
	}

	public Game MoveUp() {
		Game newGameState = new Game(this, this.board, this.cost + 1);

		for (int i = 0; i < newGameState.board.length; i++) {
			for (int j = 0; j < newGameState.board[0].length; j++) {
				if (newGameState.board[i][j] == 'o' || newGameState.board[i][j] == '$'
						|| newGameState.board[i][j] == 'U' || newGameState.board[i][j] == '0') {
					int k = i - 1;
					while (k > 0 && newGameState.board[k][j] == '-') {
						k--;
					}
					if (k < 0 || newGameState.board[k][j] != '-') {
						k++;
					}

					if ((newGameState.board[i][j] == 'U')
							&& (k > 0 && (newGameState.board[k - 1][j] == 'o' || newGameState.board[k - 1][j] == '$'))) {
						newGameState.board[i][j] = '-';
						newGameState.board[k - 1][j] = 'U';
					} else {
						char temp = newGameState.board[i][j];
						newGameState.board[i][j] = newGameState.board[k][j];
						newGameState.board[k][j] = temp;
					}
				}
			}
		}
		return newGameState;
	}

	public Game MoveDown() {
		Game newGameState = new Game(this, this.board, this.cost + 1);

		for (int i = newGameState.board.length - 1; i >= 0; i--) {
			for (int j = 0; j < newGameState.board[0].length; j++) {
				if (newGameState.board[i][j] == 'o' || newGameState.board[i][j] == '$'
						|| newGameState.board[i][j] == 'U' || newGameState.board[i][j] == '0') {
					int k = i + 1;
					while (k < newGameState.board.length - 1 && newGameState.board[k][j] == '-') {
						k++;
					}
					if (k >= newGameState.board.length || newGameState.board[k][j] != '-') {
						k--;
					}

					if ((newGameState.board[i][j] == 'o' || newGameState.board[i][j] == '$')
							&& k < newGameState.board.length - 1 && newGameState.board[k + 1][j] == 'U') {
						newGameState.board[i][j] = '-';
					} else {
						char temp = newGameState.board[i][j];
						newGameState.board[i][j] = newGameState.board[k][j];
						newGameState.board[k][j] = temp;
					}
				}
			}
		}
		return newGameState;
	}

	public Game MoveLeft() {
		Game newGameState = new Game(this, this.board, this.cost + 1);

		for (int i = 0; i < newGameState.board.length; i++) {
			for (int j = 0; j < newGameState.board[0].length; j++) {
				if (newGameState.board[i][j] == 'o' || newGameState.board[i][j] == '$'
						|| newGameState.board[i][j] == 'U' || newGameState.board[i][j] == '0') {
					int k = j - 1;
					while (k > 0 && newGameState.board[i][k] == '-') {
						k--;
					}
					if (k < 0 || newGameState.board[i][k] != '-') {
						k++;
					}

					char temp = newGameState.board[i][j];
					newGameState.board[i][j] = newGameState.board[i][k];
					newGameState.board[i][k] = temp;
				}
			}
		}
		return newGameState;
	}

	public Game MoveRight() {
		Game newGameState = new Game(this, this.board, this.cost + 1);

		for (int i = 0; i < newGameState.board.length; i++) {
			for (int j = newGameState.board[0].length - 1; j >= 0; j--) {
				if (newGameState.board[i][j] == 'o' || newGameState.board[i][j] == '$'
						|| newGameState.board[i][j] == 'U' || newGameState.board[i][j] == '0') {
					int k = j + 1;
					while (k < newGameState.board[0].length - 1 && newGameState.board[i][k] == '-') {
						k++;
					}
					if (k >= newGameState.board[0].length || newGameState.board[i][k] != '-') {
						k--;
					}

					char temp = newGameState.board[i][j];
					newGameState.board[i][j] = newGameState.board[i][k];
					newGameState.board[i][k] = temp;

				}
			}
		}
		return newGameState;
	}

	public List<Game> PossibleMoves() {
		List<Game> list = new ArrayList<>();

		if (this.CanMoveUp()) {
			list.add(this.MoveUp());
		}
		if (this.CanMoveDown()) {
			list.add(this.MoveDown());
		}
		if (this.CanMoveLeft()) {
			list.add(this.MoveLeft());
		}
		if (this.CanMoveRight()) {
			list.add(this.MoveRight());
		}
		return list;
	}

	public String getHash(){
		String hash = " ";
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[0].length; j++) {
				hash += board[i][j];
			}
		}
		 return hash;
	}

	public int getEstimatedHeuristicCost() {
		  // TODO
		    int h=0;
			for (int i = 0; i < this.board.length; i++) 
			     for (int j = 0; j < this.board[0].length; j++) 
				  if (this.board[i][j] == 'o')
				  h++;
			 //return h;	  
		return h;
	}
	
	public int getF() {
	  return this.cost + this.getEstimatedHeuristicCost();
	}
}
