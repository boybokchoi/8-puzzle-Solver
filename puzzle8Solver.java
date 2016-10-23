import java.util.*;
public class puzzle8Solver{
  
  puzzle8Solver(){

  }

  public static void main(String[] args){
    Board b = new Board();
    b.inputBoard("210345678");

    ArrayList<Board> boards = b.getMoves();
    for(int i = 0; i < boards.size(); i++){
      boards.get(i).display();
    }
  }
}
