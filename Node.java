
public class Node{
  private double totalCost, gCost, hCost;
  private Board board;
  private Node parent;

  Node(Board b){
    totalCost = 0;
    gCost = 0;
    hCost = 0;
    board = b;
    parent = null;
  }

  /*Node(Node parent, Board b, double g, h){
    this.parent = parent;
    board = b;
    gCost = g;
    hCost = h;
  }*/

  public void setParent(Node parent){this.parent = parent;}
  public void setCost(double cost){totalCost = cost;}
  public void setGCost(double cost){gCost = cost;}
  public void setHCost(double cost){hCost = cost;}
  public Node getParent(){return parent;}
  public Board getBoard(){return board;}
  public double getCost(){return totalCost;}
  public double getGCost(){return gCost;}
  public double getHCost(){return hCost;}
}
