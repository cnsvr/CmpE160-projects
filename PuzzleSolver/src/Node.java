import java.util.Arrays;

public class Node implements Comparable<Node> {

    private Board board;
    private Node parent;
    private int depth;
    private int manhattanDistance;

    /*
        This is Node class for Tree Structure.

        @param board        ...  board object of node.
        @param depth        ...  depth level of node.
        @param parent       ...  parent Node of corresponding node.


        Constructs a Node object by initializing parameters and
        calculating Manhattan distance of node.
     */



    Node(Board board,int depth,Node parent){

        this.board = board;
        this.parent= parent;
        this.depth = depth;
        this.manhattanDistance = board.manhattanDistance();


    }

    /*
        @return true if nodes are equal,otherwise false.
     */


    @Override
    public int hashCode(){

        /*
        int h = 0;

        int n = this.board.getPuzzle().length^2;        // base number

        for (int i = 0;i<board.getPuzzle().length;i++){
            for(int j = 0; j<board.getPuzzle()[0].length;j++){

                double num = Math.pow(board.getPuzzle().length,n-1);
                h += board.getPuzzle()[i][j]*num;
                n = n-1;
            }
        }

         */

        int hash = 1;
        hash = 31*hash + Arrays.deepHashCode(board.getPuzzle());

        return hash;

    }

    @Override
    public boolean equals(Object ob){

        if(ob == null){
            return false;
        }
        if(!(ob instanceof Node)){

            return false;
        }

        Node node = (Node) ob;


        if(this.hashCode() != node.hashCode()){

            return false;
        }

        for (int i = 0; i<board.getPuzzle().length;i++) {
            for (int j = 0; j < board.getPuzzle()[0].length; j++) {
                if (this.getBoard().getPuzzle()[i][j] != node.getBoard().getPuzzle()[i][j]) {
                    return false;

                }
            }

        }

        return true;
    }

    // @return board of node.

    Board getBoard() {
        return board;
    }

    // @return depth level of node.

    int getDepth() {
        return depth;
    }

    // @return Manhattan distance of node.

    public int getManhattanDistance() {
        return manhattanDistance;
    }

    // @return parent node of corresponding node.

    Node getParent() {
        return parent;
    }

    /*

      Compares nodes according to their depth levels and Manhattan distances
      and @return corresponding number.

     */
    @Override
    public int compareTo(Node o) {

        return Integer.compare(this.depth + this.manhattanDistance, o.depth + o.manhattanDistance);

    }
}
