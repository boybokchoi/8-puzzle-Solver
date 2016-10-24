import java.util.*;

public class puzzle8Solver{
  private int depth;
  private int nodesGenerated;
  puzzle8Solver(){
    depth = 0;
    nodesGenerated = 0;
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
      System.out.println("This puzzle is unsolvable.");
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
        /*if(heuristic)
          System.out.println("Heuristic used: # of misplaced tiles");
        else
          System.out.println("Heuristic used: Manhattan Distance");*/
        //System.out.println("Depth: "+tempNode.getCost());
        depth = tempNode.getCost();
        //System.out.println("Nodes Gen: "+(frontier.size() + explored.size()));
        nodesGenerated = frontier.size() + explored.size();
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
    if(path == null)
      return;

    int step = 0;

    while(!path.isEmpty()){
      System.out.println("********\nStep "+step+":\n");
      step++;
      Node temp = path.pop();
      temp.getBoard().display();
    }
  }

  private void displayStatistics(boolean heuristic){
    System.out.println("Depth: " + depth);
    System.out.println("Search Cost: " + nodesGenerated);
    if(heuristic)
      System.out.println("Heuristic: Number of Misplaced Tiles.");
    else
      System.out.println("Heuristic: Manhattan Distance.");
  }

  public void reset(){
    depth = 0;
    nodesGenerated = 0;
  }

  public void randomTest(int num){
    int count = 0;
    while(count <= num){
      Board b = generateBoard();
      if(b.isSolveable()){
        System.out.println("=================");
        System.out.println("Test " + count);
        count++;
        //Using Misplaced Tiles heuristic
        //printPath(a_Star_Search(b,true));
        long startTime = System.nanoTime();
        a_Star_Search(b,true);
        long endTime = System.nanoTime();
        long time = (endTime-startTime)/1000;
        displayStatistics(true);
        System.out.println("Time::"+time+" ms");
        reset();
        System.out.println("=================");
        //Using Manhattan Distance heuristic
        //printPath(a_Star_Search(b,false));
        startTime = System.nanoTime();
        a_Star_Search(b,false);
        endTime = System.nanoTime();
        System.out.println("*****************");
        displayStatistics(false);
        time = (endTime-startTime)/1000;
        System.out.println("Time::"+time+" ms");
        reset();
      }
    }
  }

  public void specificDepthTest(int depthVal, int numTests){
    if(depthVal % 2 != 0){
      System.out.println("Choose an even depth.");
      return;
    }
    int count = 0;
    while(count < numTests){
      Board b = generateBoard();
      if(b.isSolveable()){
        a_Star_Search(b, true);
        if(depth == depthVal){
          count++;
          System.out.println("Test "+count);
          System.out.println("=================");
          displayStatistics(true);
          reset();
          System.out.println("=================");
          a_Star_Search(b, false);
          displayStatistics(false);
          reset();
          System.out.println("*****************");
        }
      }
    }
  }

  public Board generateBoard(){
    StringBuilder sb = new StringBuilder(9);
    ArrayList<Character> list = new ArrayList<Character>();
    list.add('0');
    list.add('1');
    list.add('2');
    list.add('3');
    list.add('4');
    list.add('5');
    list.add('6');
    list.add('7');
    list.add('8');

    while(!list.isEmpty()){
      Collections.shuffle(list);
      sb.append(list.remove(0));
    }
    Board newBoard = new Board();
    newBoard.inputBoard(sb.toString());
    return newBoard;
  }


  public static void main(String[] args){
    Scanner sc = new Scanner(System.in);
    int choice = 1;
    while(choice == 1 || choice == 2){
      System.out.println("=========== 8-Puzzle Solver ===========");
      System.out.println("1. Randomly Generate 200 puzzles and solve.");
      System.out.println("2. Input puzzle manually.");
      System.out.println("3. Exit.");
      if(sc.hasNextInt()){
        choice = sc.nextInt();
      }else{
        System.out.println("Invalid input.");
        choice = 3;
        break;
      }
      puzzle8Solver p = new puzzle8Solver();
      if(choice == 1){
        sc.nextLine();
        p.randomTest(200);
      }
      else if(choice == 2){
        sc.nextLine();
        System.out.println("Input the puzzle in 1 line (no separation)\n Example:(012345678) ::");
        String input = sc.nextLine();
        Board b = new Board();
        b.inputBoard(input);
        long startTime = System.nanoTime();
        Stack<Node> path = p.a_Star_Search(b,true);
        long endTime = System.nanoTime();
        p.printPath(path);
        long time = (endTime - startTime)/1000;
        System.out.println("Time::"+time+"ms");
        p.displayStatistics(true);
        p.reset();
        System.out.println("=================");
        startTime = System.nanoTime();
        path = p.a_Star_Search(b,false);
        endTime = System.nanoTime();
        p.printPath(path);
        time = (endTime - startTime)/1000;
        System.out.println("Time::"+time+"ms");
        p.displayStatistics(false);
        p.reset();
      }

    }
    System.out.println("Exiting... ");
  }
}
