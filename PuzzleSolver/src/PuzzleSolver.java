import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.*;
import java.util.HashSet;

public class PuzzleSolver{

    /*
        This is a N-Puzzle Solver.
        It creates a puzzle board whose cells filled numbers from 1 to (N^2-1) and remaining is blank.
        Only the blank box can be moved on the table and the moving direction can be either horizontal or vertical(not diagonal).
        When the blank box is moved, the blank one and the box in the destination position of the blank box are swapped.
        When puzzle board is not solvable,it prints "N" (No solution).

     */

    public static void main(String[] args) throws FileNotFoundException {



       Scanner console = new Scanner(new File(args[0]));
       PrintStream printStream = new PrintStream(args[1]);

       /*

       Input ->> 1-2-3-4-5-0-7-8-6

        */

        while (console.hasNextLine()) {


            String[] position = console.nextLine().split("-");

            int N = (int) Math.sqrt(position.length);

            int[][] puzzleBoard = new int[N][N];

            for (int i = 0; i < puzzleBoard.length; i++) {
                for (int j = 0; j < puzzleBoard[0].length; j++) {

                    puzzleBoard[i][j] = Integer.parseInt(position[i * N + j]);

                }

            }

            Board initial = new Board(puzzleBoard);

            PuzzleSolver puzzle = new PuzzleSolver(initial);

            puzzle.solve();

            printStream.println(puzzle.pathToSolution(puzzle.getFinalNode()));

        }


        console.close();
        printStream.close();





}


    private PriorityQueue<Node> priorityQueue;
    private Set<Node> viewedNodes;
    private Board initial;
    private Node finalNode;
    private int[][] goalBoard;
    //private static PrintStream printStream;
 


    /*
        This is Puzzle class.
        Constructs Puzzle object with given parameter and
        initializes initial and goal board,priorityQueue,viewedNodes.

        @param initial      ... initial board of puzzle board.

     */


    PuzzleSolver(Board initial){

        this.initial = initial;
        int N  = initial.getPuzzle().length;
        this.goalBoard = new int[N][N];
        this.viewedNodes = new HashSet<>();
        this.priorityQueue = new PriorityQueue<>();
        for (int i = 0;i<N;i++){
            for(int j = 0; j<N;j++){
                goalBoard[i][j] = (i*N)+j + 1;
            }
        }
        goalBoard[N-1][N-1] = 0;
    }



    /*
        This method solves puzzle with A* algorithm.
        In this part,priorityQueue and viewNodes structures are used.
        Each puzzle has priority over its level depth and Manhattan distance at priorityQueue.
        While searching in this queue,in order to avoid control same puzzle,
        controlled puzzles are being added to viewedNodes set.
        When goal board is found, it sets to finalNode,
        otherwise when it has no solution,priorityQueue will be empty and  it gives no solution.

     */


    void solve() {



        Node initialNode = new Node(initial, 0, null);

        priorityQueue.add(initialNode);

    

        while (!priorityQueue.isEmpty()) {

            Node current = priorityQueue.poll();
            
            viewedNodes.add(current);

          
            if(goalConfiguration(current.getBoard().getPuzzle())){

                finalNode = current;
                //System.out.println("Puzzle is found.");
                break;
            }
            current.getBoard().addConfiguration();



            Iterable<Board> iterable = current.getBoard().getChildren();

            for (Board board : iterable) {

                Node temp = new Node(board, current.getDepth() + 1, current);

              // to check viewedNode contains childNode and childNode equals grandParentNode.
                if ( !viewedNodes.contains(temp) && (!temp.equals(current.getParent()))) {
                            priorityQueue.add(temp);
                        }

                }

        
            }

        }

    /*

       Prints type of nodes from initial board to goal board.

     */
    String  pathToSolution(Node node){

	if(node == null){

	return "N";
}

       
       StringBuilder pathSolution = new StringBuilder();

        Stack<Board> path = new Stack<>();

        Node current = node;

        path.add(current.getBoard());

        while ( current.getParent() != null){

            current= current.getParent();

            path.add(current.getBoard());
        }

        while (!path.isEmpty()){

           

            Board temp = path.pop();


            if(temp.getType() != null) {
                
                pathSolution.append(temp.getType());


               
            }


        }
      
        return pathSolution.toString();

    }

    /*
        Checks and @return true if given parameter equals goal board, otherwise false.
     */

     private boolean goalConfiguration(int[][] board){


        for (int i = 0;i<board.length;i++){
            for(int j = 0; j<board[0].length;j++) {

                if (board[i][j] != goalBoard[i][j]) {
                    return false;
                }
            }

        }

        return true;

    }


    // @return final node.

    Node getFinalNode() {
        return finalNode;
    }



}

