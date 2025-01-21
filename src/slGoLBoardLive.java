public class slGoLBoardLive extends slGoLBoard {
    public slGoLBoardLive(int numRows, int numCols) {
        super(numRows, numCols);
    }

    @Override
    public int countLiveTwoDegreeNeighbors(int row, int col) {

        return java.util.stream.IntStream.rangeClosed(-1, 1).flatMap(i -> java.util.stream.IntStream.rangeClosed(-1, 1).map(j -> (!(Math.floorMod((row + i + NUM_ROWS), NUM_ROWS) == row && Math.floorMod((col + j + NUM_COLS), NUM_COLS) == col)) ? ((liveCellArray[Math.floorMod((row + i + NUM_ROWS), NUM_ROWS)][Math.floorMod((col + j + NUM_COLS), NUM_COLS)]) ? 1 : 0) : 0)).sum();

//        return IntStream.rangeClosed(-1, 1).flatMap(i -> IntStream.rangeClosed(-1, 1).map(j -> (!(Math.floorMod((row + i + NUM_ROWS), NUM_ROWS) == row && Math.floorMod((col + j + NUM_COLS), NUM_COLS) == col)) ? ((liveCellArray[Math.floorMod((row + i + NUM_ROWS), NUM_ROWS)][Math.floorMod((col + j + NUM_COLS), NUM_COLS)]) ? 1 : 0) : 0 )).sum();

//        for (int i = -1; i <= 1; i++) for (int j = -1; j <= 1; j++) count += (!(Math.floorMod((row + i + NUM_ROWS), NUM_ROWS) == row && Math.floorMod((col + j + NUM_COLS), NUM_COLS) == col)) ? ((liveCellArray[Math.floorMod((row + i + NUM_ROWS), NUM_ROWS)][Math.floorMod((col + j + NUM_COLS), NUM_COLS)]) ? 1 : 0) : 0;

//        for (int i = -1; i <= 1; i++) {
//            currentRow = Math.floorMod((row + i + NUM_ROWS), NUM_ROWS);
//            for (int j = -1; j <= 1; j++) {
//                currentCol = Math.floorMod((col + j + NUM_COLS), NUM_COLS);
//                count += (!(currentRow == row && currentCol == col)) ? ((liveCellArray[currentRow][currentCol]) ? 1 : 0) : 0;
////                if (!(currentRow == row && currentCol == col)) if (liveCellArray[currentRow][currentCol]) count++;
//            }
//        }

//
//        int count = 0;
//        for (int i = -1; i <= 1; i++) for (int j = -1; j <= 1; j++) count += (!(Math.floorMod((row + i + NUM_ROWS), NUM_ROWS) == row && Math.floorMod((col + j + NUM_COLS), NUM_COLS) == col)) ? ((liveCellArray[Math.floorMod((row + i + NUM_ROWS), NUM_ROWS)][Math.floorMod((col + j + NUM_COLS), NUM_COLS)]) ? 1 : 0) : 0; // haha funny one linerrr (i couldnt make it into a single return statement using lambdas in time :( next time tho)
//        return count;
    }

    // return how many live cells are in the updated board
    /*
        Rules:
        1. Live Two Degree Neighbors < 2 --> Kill
        2. Live Two Degree Neighbors == 2 || Live Neighbors == 3 --> Retain
        3. Live Two Degree Neighbors > 3 --> Kill
        4. Dead with Live Two Degree Neighbors == 3 --> Alive again
    */
    @Override
    public int updateNextCellArray() {
        int retVal = 0;

        int nln = 0;  // Number Live Neighbors
        boolean ccs = true; // Current Cell Status
        for (int row = 0; row < NUM_ROWS; ++row){
            for (int col = 0; col < NUM_COLS; ++col) {
                ccs = liveCellArray[row][col];
                nln = countLiveTwoDegreeNeighbors(row, col);
                if (!ccs && nln == 3) {
                    nextCellArray[row][col] = true;
                    ++retVal;
                } else {
                    // Current Cell Status is true
                    if (nln < 2 || nln > 3) {
                        nextCellArray[row][col] = false;
                    } else {
                        // nln == 2 || nln == 3
                        nextCellArray[row][col] = true;
                        ++retVal;
                    }
                }
            }  // for (int row = 0; ...)
        }  //  for (int col = 0; ...)

        boolean[][] tmp = liveCellArray;
        liveCellArray = nextCellArray;
        nextCellArray = tmp;

        return retVal;
    }  //  int updateNextCellArray()
}