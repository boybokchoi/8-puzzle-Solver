import java.util.*;

public class puzzle8Solver{
  puzzle8Solver(){

  }

  /*
   * Finds a solution to the provided Board, using
   * A* algorithm and the given heuristic(1 for misplaced Tiles, 0 for Manhattan Distance)
   */
  public Stack<Node> a_Star_Search(Board board, boolean heuristic){
    //starting node w/ starting board state
    Node root = new Node(board);

    //If board is unsolveable, end search.
    if(!board.isSolveable()){
      System.out.println("This puzzle is unsolvable");
      return null;
    }

    PriorityQueue<Node> frontier = new PriorityQueue<Node>(10, (n1,n2) -> {
      return (n1.getCost() < n2.getCost()) ? -1 : 1;
    });

    frontier.add(root);
    HashSet<String> explored = new HashSet<>();
    ArrayList<Board> possibleSuccessors = null;

    Node tempNode = null;

    while(!frontier.isEmpty()){
      tempNode = frontier.poll();
      if(!tempNode.getBoard().isGoal()){
        explored.add(tempNode.getBoard().getString());
        possibleSuccessors = tempNode.getBoard().getMoves();
        for(int i = 0; i < possibleSuccessors.size(); i++){
          Node expandedNode;
          if(heuristic){ //h(n) = # of misplaced tiles
            expandedNode = new Node(tempNode,
                                    possibleSuccessors.get(i),
                                    tempNode.getGCost()+1,
                                    possibleSuccessors.get(i).getNumMisplacedTiles());
          }
          else{
            expandedNode = new Node(tempNode,
                                    possibleSuccessors.get(i),
                                    tempNode.getGCost()+1,
                                    possibleSuccessors.get(i).getManhattanDistance());
          }
          //Check for repeats
          if(!explored.contains(expandedNode.getBoard().getString())){
            //System.out.println("Not Repeated.");
            frontier.add(expandedNode);
          }
        }
      }
      else{//Goal State
        if(heuristic)
          System.out.println("Heuristic used: # of misplaced tiles");
        else
          System.out.println("Heuristic used: Manhattan Distance");
        System.out.println("Depth: "+tempNode.getCost());
        System.out.println("Nodes Gen: "+(frontier.size() + explored.size()));
        break;
      }
    }
    //No more nodes to explore, solution path has been found
    Stack<Node> path = new Stack<>();
    while(tempNode != null){
      path.push(tempNode);
      tempNode = tempNode.getParent();
    }
    return path;
  }

  public void printPath(Stack<Node> path){
    int step = 0;

    while(!path.isEmpty()){
      System.out.println("********\nStep "+step+":\n");
      step++;
      Node temp = path.pop();
      temp.getBoard().display();
    }
  }



  public static void main(String[] args){
    Board b = new Board();
    //b.inputBoard("541632078");
    //b.inputBoard("312645078");
    b.inputBoard("541763820");
    //b.inputBoard("580362148");
    b.display();
    System.out.println("Manhattan Distance: "+b.getManhattanDistance()+"|Mis Tiles:"+b.getNumMisplacedTiles());
    //System.out.println("Hash: " + b.getString());
    puzzle8Solver p = new puzzle8Solver();
    //p.a_Star_Search(b,false);
    p.printPath(p.a_Star_Search(b,false));

    //System.out.println("ManDist:" + b.getManhattanDistance());
    /*ArrayList<Board> boards = b.getMoves();
    System.out.println(boards.size());
    for(int i = 0; i < boards.size(); i++){
      System.out.println();
      boards.get(i).display();
      System.out.println("Hash: " + boards.get(i).getString());
      //System.out.println("misplaced:: "+boards.get(i).getMisplacedTiles());
    }*/

  }
}
