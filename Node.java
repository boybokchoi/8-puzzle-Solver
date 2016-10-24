
public class Node{
  private int totalCost, gCost, hCost;
  private Board board;
  private Node parent;

  Node(Board b){
    gCost = 0;
    hCost = 0;
    totalCost = gCost + hCost;
    board = b;
    parent = null;
  }

  Node(Node parent, Board b, int g, int h){
    this.parent = parent;
    board = b;
    gCost = g;
    hCost = h;
    totalCost = g+h;
  }

  public void setParent(Node parent){this.parent = parent;}
  public void setCost(int cost){totalCost = cost;}
  public void setGCost(int cost){
    gCost = cost;
    calcCost();
  }
  public void setHCost(int cost){
    hCost = cost;
    calcCost();
  }
  public Node getParent(){return parent;}
  public Board getBoard(){return board;}
  public int calcCost(){
    totalCost = gCost + hCost;
    return totalCost;
  }
  public int getCost(){return totalCost;}
  public int getGCost(){return gCost;}
  public int getHCost(){return hCost;}
}
