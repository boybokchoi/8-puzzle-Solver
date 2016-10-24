import java.util.*;
public class puzzle8Solver{
  puzzle8Solver(){

  }

  /*
   * Finds a solution to the provided Board, using
   * A* algorithm and the given heuristic(1 for misplaced Tiles, 0 for Manhattan Distance)
   */
  public void a_Star_Search(Board board, boolean heuristic){
    Node root = new Node(board);
    ArrayList<Node> checkedNodes = new ArrayList<Node>();
    Queue<Node> frontier = new LinkedList<Node>();
    frontier.add(root);

    int iterations = 0;

    while(!frontier.isEmpty()){
        Node tempNode = frontier.poll();

        if(!tempNode.getBoard().isGoal()){
          ArrayList<Board> possibleSuccessors = tempNode.getBoard().getMoves();
          ArrayList<Node> actualSuccessors = new ArrayList<Node>();

          for(int i = 0; i < possibleSuccessors.size(); i++){
              Node expandedNode;
              expandedNode = new Node(tempNode.getBoard());
              expandedNode.setGCost(tempNode.getCost() + 1);
              if(heuristic){ //h(n) = # of misplaced tiles
                expandedNode.setHCost(possibleSuccessors.get(i).getNumMisplacedTiles());
              }
              else{
                expandedNode.setHCost(possibleSuccessors.get(i).getManhattanDistance());
              }
              //Check for repeats
              if(checkedNodes.indexOf(expandedNode) == -1){
                actualSuccessors.add(expandedNode);
                checkedNodes.add(expandedNode);
              }
          }

          if(!actualSuccessors.isEmpty()){

          }

        }
    }
  }

  public static void main(String[] args){
    Board b = new Board();
    b.inputBoard("210345678");
    System.out.println("ManDist:" + b.getManhattanDistance());
    /*ArrayList<Board> boards = b.getMoves();
    for(int i = 0; i < boards.size(); i++){
      boards.get(i).display();
      System.out.println("misplaced:: "+boards.get(i).getMisplacedTiles());
    }*/
  }
}
