import java.util.*;

public class Board{
  private char[][] board;
  private char[] piece;

  Board(){
    //initialize board
    board = new char[3][3];
    for(int i = 0; i < board.length; i++){
      for(int j = 0; j < board.length; j++){
        board[i][j] = '0';
      }
    }

    //initalize possible values
    for(int i = 0; i < piece.length; i++){
      piece[i] = (char)(i + '0');
    }
  }
/*"012  [0 0] [0 1] [0 2]
   345  [1 0] [1 1] [1 1]
   678" [2 0] [2 1] [2 2]
 */
  public void inputBoard(String places){
    for(int i = 0; i < 3; i++){
      for(int j = 0; j < 3; j++){
        board[i][j] = places.charAt(i*j);
        if(places.charAt(i*j) == '0')

      }
    }
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
