import java.util.*;

public class Board{
  private final int BOARD_SIZE = 9;
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
   *     if 6, go to top, right
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
    if(emptyIndex != 0 && emptyIndex != 1 && emptyIndex != 2 && emptyIndex != 6){
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

  /*
   * Returns a different Board object with the same values as the Board passed.
   */
  private Board copyBoard(){
    Board b = new Board();
    String places = "";
    for(int i = 0; i < b.BOARD_SIZE; i++){
      places += board[i];
    }
    b.inputBoard(places);
    return b;
  }

  public void swap(int index1, int index2){
    char temp = board[index1];
    board[index1] = board[index2];
    board[index2] = temp;
  }

  public void display(){
    for(int i = 0; i < BOARD_SIZE; i++){
      System.out.print(board[i] + " ");
      if(i == 2 || i == 5 || i == 8)
        System.out.println();
    }
    //System.out.println("empty Index:: " + emptyIndex);
  }

}
