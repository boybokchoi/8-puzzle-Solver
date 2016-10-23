import java.util.*;

public class Board{
  private final int boardSize = 9;
  private char[] board;

  Board(){
    //initialize board
    board = new char[9];
    for(int i = 0; i < board.length; i++){
      board
    }
  }

  public void inputBoard(String places){
    int index = 0;
    for(int i = 0; i < 3; i++){
      for(int j = 0; j < 3; j++){
        board[i][j] = places.charAt(index);
        index++;
      }
    }
  }

  public Arraylist<Board> getMoves(){

  }

  public void display(){
    for(int i = 0; i < board.length; i++){
      for(int j = 0; j < board.length; j++){
        System.out.print(board[i][j] + " ");
      }
      System.out.println();
    }
  }

}
