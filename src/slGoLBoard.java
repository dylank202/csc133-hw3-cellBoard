import java.util.Random;
abstract class slGoLBoard {
    protected int NUM_ROWS;
    protected int NUM_COLS;

    protected boolean[][]  cellArrayA, cellArrayB, liveCellArray, nextCellArray;

    protected slGoLBoard(int numRows, int numCols) {
        NUM_ROWS = numRows;
        NUM_COLS = numCols;
        cellArrayA = new boolean[NUM_ROWS][NUM_COLS];
        cellArrayB = new boolean[NUM_ROWS][NUM_COLS];

        Random myRandom = new Random();
        for (int row = 0; row < NUM_ROWS; ++row) {
            for (int col = 0; col < NUM_COLS; ++col) {
                cellArrayA[row][col] = myRandom.nextBoolean();
                cellArrayB[row][col] = myRandom.nextBoolean();
            }
        }
        liveCellArray = cellArrayA;
        nextCellArray = cellArrayB;
    }  //  public slGoLBoard(int numRows, int numCols)

    // Create a Board with a given number of cells alive - the alive cells
    // are placed randomly placed applying Durstenfeld-Knuth random shuffling
    private slGoLBoard(int numRows, int numCols, int numAlive) {
        int MAX_ROWS = numRows;
        int MAX_COLS = numCols;
        int setAliveNum = Math.min(MAX_ROWS * MAX_COLS, numAlive);

        cellArrayA = new boolean[MAX_ROWS][MAX_COLS];
        cellArrayB = new boolean[MAX_ROWS][MAX_COLS];

        if (setAliveNum == 0 || setAliveNum == MAX_ROWS * MAX_COLS){
            /* All cells are either dead or all are alive */
            boolean tmpVal  = (setAliveNum == MAX_ROWS * MAX_COLS);

            for (int r = 0; r < cellArrayA.length; ++r){
                for (int c = 0; c < cellArrayA[0].length; ++c) {
                    cellArrayA[r][c] = tmpVal;
                }
            }
        } else {
            /* setAliveNum != 0 && setAliveNum != MAX_ROWS*MAX_COLS */
            boolean[] tmpArray = new boolean[MAX_ROWS * MAX_COLS];
            for (int i = 0; i < setAliveNum; ++i) {
                tmpArray[i] = true;
            }
            for (int i = setAliveNum; i < MAX_ROWS * MAX_COLS; ++i) {
                tmpArray[i] = false;
            }
            Random myRandom = new Random();
            // Durstenfeld-Knuth random shuffle:
            for (int i = 0; i < MAX_ROWS * MAX_COLS - 2; ++i) {
                int j = myRandom.nextInt(i, MAX_ROWS * MAX_COLS);
                boolean tmp = tmpArray[i];
                tmpArray[i] = tmpArray[j];
                tmpArray[j] = tmp;
            }

            int i = 0;
            for (int row = 0; row < MAX_ROWS; ++row) {
                for (int col = 0; col < MAX_COLS; ++col) {
                    cellArrayA[row][col] = tmpArray[i++];
                    cellArrayB[row][col] = false;
                }
            }  //  for (int row = 0; row < NUM_ROWS; ++row)
        }  //  if (setAliveNum == 0) else ...

        liveCellArray = cellArrayA;
        nextCellArray = cellArrayB;
    }  //  public slGoLBoard(int numRows, int numCols, int numAlive)

    private boolean[][] getLiveCellArray() {
        return liveCellArray;
    }
    private boolean[][] getNextCellArray() {
        return nextCellArray;
    }

    private void setCellAlive(int row, int col){
        liveCellArray[row][col] = true;
    }

    private void setCellDead(int row, int col){
        liveCellArray[row][col] = false;
    }

    private void setAllCells(boolean value) {
        for (boolean[] rows : liveCellArray) {
            for (boolean cell : rows) {
                cell = value;
            }
        }
    }  //  void setAllCells()

    private void copyNextToLive() {
        for (int row = 0; row < nextCellArray.length; ++row){
            System.arraycopy(nextCellArray[row], 0, liveCellArray[row], 0, nextCellArray[row].length);
        }
        return;
    }  //  void copyNextToLive()

    protected void printGoLBoard() {
        for (boolean[] my_row : liveCellArray) {
            for (boolean my_val : my_row) {
                if (my_val) {
                    System.out.print("\u001B[34m" + 1 + "\u001B[0m "); // hope it's okay i made it blue, makes my eyes feel better :)
                } else {
                    System.out.print(0 + " ");
                }
            }  //  for (bool my_val : my_row)
            System.out.println();
        }  //  for (bool[] my_row : my_array)
    }  //  void printGoLBoard()

    protected abstract int countLiveTwoDegreeNeighbors(int row, int col);
    protected abstract int updateNextCellArray();

}  //  public class slGoLBoard