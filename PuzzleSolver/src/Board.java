import java.util.ArrayList;

class Board {

    private int N;
    private ArrayList<Board> children = new ArrayList<>();
    private int [][] puzzle;
    private String type;


    /*
       This is Board class.

       @param puzzle    ... puzzle array of board

       Constructs Board object and initializes puzzle array of board.

     */

    Board(int[][] puzzle){

        this.N = puzzle.length;
        this.puzzle = new int[N][N];
        this.puzzle = puzzle;

    }

    /*
        Calculates and @ return Manhattan distance of Board.Manhattan distance is sum of steps required
        for each number at puzzle to reach the goal board from initial board. By using this calculation,
        smaller Manhattan distance bring us closer to solve puzzle.

     */

    int manhattanDistance(){

        int manhattan = 0;

        int number = 1;

        for (int i = 0;i<N;i++){
            for(int j = 0; j<N;j++) {

                //String [] realPosition = findPosition(goalBoard,number).split(" ");
                String [] puzzlePosition = findPosition(puzzle,number).split(" ");

                    manhattan += Math.abs(i - Integer.parseInt(puzzlePosition[0])) +
                            Math.abs(j- Integer.parseInt(puzzlePosition[1]));
                    if(number == N*N-1){
                        return manhattan;
                    }

                number++;
            }
        }

        return manhattan;
    }

    // Adds all possible child configuration of corresponding board.

    void addConfiguration(){


        leftMove(puzzle);
        rightMove(puzzle);
        upMove(puzzle);
        downMove(puzzle);
    }

    /*
        Creates a new board with blank position shifted to right
        Adds this board to children list.
        Sets type of board as a "R";
     */
    private void rightMove(int[][] puzzle){


        String blankIndex = getBlankIndex(puzzle);
        String [] position = blankIndex.split(" ");

        int X = Integer.parseInt(position[0]);
        int Y = Integer.parseInt(position[1]);

        if(Y + 1 < N){

            int[][] temp = new int[N][N];

            copyArray(temp,puzzle);

            int tempR = temp[X][Y+1];
            temp[X][Y+1] = 0;
            temp[X][Y] = tempR;

            Board board= new Board(temp);
            this.children.add(board);
            board.type = "R";


        }


    }
     /*
        Creates a new board with blank position shifted to up
        Adds this board to children list.
        Sets type of board as a "U";
     */

    private void upMove(int[][] puzzle){

        String blankIndex = getBlankIndex(puzzle);
        String [] position = blankIndex.split(" ");

        int X = Integer.parseInt(position[0]);
        int Y = Integer.parseInt(position[1]);

        if(X - 1 >= 0) {

            int[][] temp = new int[N][N];

            copyArray(temp,puzzle);

            int tempU = temp[X-1][Y];
            temp[X-1][Y] = 0;
            temp[X][Y] = tempU;

            Board board= new Board(temp);
            this.children.add(board);
            board.type = "U";



        }
    }

     /*
        Creates a new board with blank position shifted to down
        Adds this board to children list.
        Sets type of board as a "D";
     */

    private void downMove(int[][] puzzle){

        String blankIndex = getBlankIndex(puzzle);
        String [] position = blankIndex.split(" ");

        int X = Integer.parseInt(position[0]);
        int Y = Integer.parseInt(position[1]);

        if(X + 1 < N ) {

            int[][] temp = new int[N][N];

            copyArray(temp,puzzle);

            int tempD = temp[X+1][Y];
            temp[X+1][Y] = 0;
            temp[X][Y] = tempD;

            Board board = new Board(temp);
            this.children.add(board);
            board.type = "D";



        }
    }

     /*
        Creates a new board with blank position shifted to left
        Adds this board to children list.
        Sets type of board as a "L";
     */

    private void leftMove(int[][] puzzle){

        String blankIndex = getBlankIndex(puzzle);
        String [] position = blankIndex.split(" ");

        int X = Integer.parseInt(position[0]);
        int Y = Integer.parseInt(position[1]);

        if(Y - 1 >= 0 ) {

            int[][] temp = new int[N][N];

            copyArray(temp,puzzle);

            int tempL = temp[X][Y - 1];
            temp[X][Y - 1] = 0;
            temp[X][Y] = tempL;

            Board board = new Board(temp);
            this.children.add(board);
            board.type = "L";



        }

    }

    /*
        Calculates and @return X and Y position of blank index of given parameter.
     */

    private String getBlankIndex(int[][] puzzle){

        for (int i = 0;i<puzzle.length;i++){
            for (int j = 0; j<puzzle[0].length;j++){

                if(puzzle[i][j] == 0){
                    return i+" "+j;
                }

            }
        }

        throw new IllegalArgumentException("Blank Index is not found.");
    }

    /*
       Calculates and @return X and Y position of given num at given given 2D array.
     */

    private String findPosition(int[][] board,int num){

        for (int i = 0;i<N;i++){
            for(int j = 0; j<N;j++) {

                if(board[i][j] == num){

                    return i+" "+j;
                }
            }
        }

        throw new IllegalArgumentException("Number is not found.");

    }

    // Copies old puzzle to temp puzzle.

    private void copyArray(int[][] temp,int[][] oldPuzzle){

       for (int i = 0;i<oldPuzzle.length;i++){
           System.arraycopy(oldPuzzle[i], 0, temp[i], 0, oldPuzzle[0].length);
       }


    }


    // Prints the board.

    void printBoard(){

        for (int[] ints : puzzle) {
            for (int j = 0; j < puzzle[0].length; j++) {

                System.out.print(ints[j] + " ");

            }
            System.out.println();
        }

    }

    // @return children list of board.

    ArrayList<Board> getChildren() {
        return children;
    }

    // @return string type of board.

    String getType() {
        return type;
    }

    // @return puzzle array of board.

    int[][] getPuzzle() {
        return puzzle;
    }
}


