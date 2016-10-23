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

  /*public Arraylist<Board> getMoves(){

  }*/

  public void display(){
    for(int i = 0; i < BOARD_SIZE; i++){
      System.out.print(i + " ");
      if(i == 2 || i == )
        System.out.println();
    }
    System.out.println("empty Index:: " + emptyIndex);
  }

}
