import java.util.*;

public class Board{
  private final int BOARD_SIZE = 9;
  private final char[] GOAL_STATE = {'0','1','2','3','4','5','6','7','8'};
  private char[] board;
  private int emptyIndex;

  Board(){
    emptyIndex = 0;
    //initialize board
    board = new char[BOARD_SIZE];
    for(int i = 0; i < BOARD_SIZE; i++){
      board[i] = '0';
    }
  }

  /*
   * Accepts string of values as Board's corresponding values.
   */
  public void inputBoard(String places){
    int index = 0;
    for(int i = 0; i < BOARD_SIZE; i++){
      board[i] = places.charAt(i);
      if(places.charAt(i) == '0')
        emptyIndex = i;
    }
  }

  /*     0 1 2
   *     3 4 5
   *     6 7 8
   *     Possible Moves::
   *     if 0, go to right, down
   *     if 1, go to left, right, down
   *     if 2, go to left, down
   *     if 3, go to up, right, down
   *     if 4, go to up, right, down, left
   *     if 5, go to up, left, down
   *     if 6, go to up, right
   *     if 7, go to up, left, right
   *     if 8, go to up, left
   *
   *     Returns an arraylist of Boards containing possible successors.
   */
  public ArrayList<Board> getMoves(){
    ArrayList<Board> boards = new ArrayList<Board>();

    //Move left
    if(emptyIndex != 0 && emptyIndex != 3 && emptyIndex != 6){
      Board newMove = copyBoard();
      newMove.swap(emptyIndex, emptyIndex-1);
      boards.add(newMove);
    }
    //Move right
    if(emptyIndex != 2 && emptyIndex != 5 && emptyIndex != 8){
      Board newMove = copyBoard();
      newMove.swap(emptyIndex, emptyIndex+1);
      boards.add(newMove);
    }
    //Move Up
    if(emptyIndex != 0 && emptyIndex != 1 && emptyIndex != 2){
      Board newMove = copyBoard();
      newMove.swap(emptyIndex, emptyIndex-3);
      boards.add(newMove);
    }
    //Move Down
    if(emptyIndex != 6 && emptyIndex != 7 && emptyIndex != 8){
      Board newMove = copyBoard();
      newMove.swap(emptyIndex, emptyIndex+3);
      boards.add(newMove);
    }

    return boards;
  }

  /* Heuristic Function:
   * Returns number of misplaced tiles.
   */
  public int getNumMisplacedTiles(){
    int count = 0;
    for(int i = 0; i < BOARD_SIZE; i++){
      if(board[i] != GOAL_STATE[i]){
        count++;
      }
    }
    return count;
  }

  /* Heuristic Function:
   * Returns manhattan distance of current board.
   */
  public int getManhattanDistance(){
    int mDist = 0;
    int index = 0;  //actual index of board

    //Double for-loop to simulate the indices for a 2-D array
    for(int i = 0; i < 3; i++){
      for(int j = 0; j < 3; j++){
        int val = Character.getNumericValue(board[index]);
        if(val != 0){
          int xDist = val%3;
          int yDist = val/3;
          mDist += Math.abs(yDist - i) + Math.abs(xDist - j);
        }
        index++;
      }
    }
    return mDist;
  }

  /*
   * Returns a different Board object with the same values as the Board passed.
   */
  private Board copyBoard(){
    Board b = new Board();
    String places = "";
    for(int i = 0; i < BOARD_SIZE; i++){
      places += board[i];
    }
    b.inputBoard(places);
    return b;
  }

  /*
   * Returns whether or not the board is the goal state.
   */
  public boolean isGoal(){
    for(int i = 0; i < BOARD_SIZE; i++){
      if(board[i] != GOAL_STATE[i])
        return false;
    }
    return true;
  }
  /*
   *  Returns whether or not the Board can be solved.
   *  Counts the number of inversions and checks if it is even.
   */
  public boolean isSolveable(){
    int numInverted = 0;

    for(int i = 0; i < BOARD_SIZE-1; i++){
      int iboardVal = Character.getNumericValue(board[i]);
      for(int j = i+1; j < BOARD_SIZE; j++){
        int jboardVal = Character.getNumericValue(board[j]);
        if(iboardVal != 0 && jboardVal != 0 && iboardVal > jboardVal)
          numInverted++;
      }
    }
    return (numInverted%2==0);
  }

  /*
   * Returns the string value for this specific board.
   */
  public String getString(){
    String str = "";
    for(int i = 0; i < BOARD_SIZE; i++){
      str += board[i];
    }
    return str;
  }

  /*
   * Swaps values of Board.
   */
  public void swap(int index1, int index2){
    char temp = board[index1];
    board[index1] = board[index2];
    board[index2] = temp;
    //Need to redefine emptyIndex because we moved it around
    locateEmpty();
  }

  /*
   *  Sets emptyIndex to the index of the empty tile.
   */
  private void locateEmpty(){
    for(int i = 0; i < BOARD_SIZE; i++){
      if(board[i] == '0')
        emptyIndex = i;
    }
  }

  /*
   * Displays the current state of Board.
   */
  public void display(){
    for(int i = 0; i < BOARD_SIZE; i++){
      System.out.print(board[i] + " ");
      if(i == 2 || i == 5 || i == 8)
        System.out.println();
    }
    //System.out.println("empty Index:: " + emptyIndex);
  }
}
