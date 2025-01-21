public class Main {
    private static slGoLBoard my_board;
    private static final int ROWS = 7, COLS = 9;
    public static void main(String[] args) {
        test_case_1();
        test_case_2();
        test_case_3();
        test_case_4();
        test_case_5();
    }

    // print TwoDegreeNeighbors(0,0)
    private static void test_case_1() {
        my_board = new slGoLBoardLive(ROWS, COLS);
        my_board.printGoLBoard();
        int my_row = 0, my_col = 0;
        System.out.println("TwoDegreeNeighbors(" + my_row + ", " + my_col + ") --> "
            + my_board.countLiveTwoDegreeNeighbors(my_row, my_col));
        System.out.println();
        return;
    }

    // print TwoDegreeNeighbors(ROWS-1, COLS-1)
    private static void test_case_2() {
        my_board = new slGoLBoardLive(ROWS, COLS);
        my_board.printGoLBoard();
        int my_row = ROWS-1, my_col = COLS-1;
        System.out.println("TwoDegreeNeighbors(" + my_row +", " + my_col + ") --> " +
                my_board.countLiveTwoDegreeNeighbors(ROWS-1, COLS-1));
        System.out.println();
        return;
    }

    // print TwoDegreeNeighbors((int)(ROWS/2), (int)(COLS/2))
    private static void test_case_3() {
        my_board = new slGoLBoardLive(ROWS, COLS);
        my_board.printGoLBoard();
        int my_row = (int)(ROWS / 2), my_col = (int)(COLS / 2);
        System.out.println("TwoDegreeNeighbors(" + my_row + ", " + my_col + ") --> "
                + my_board.countLiveTwoDegreeNeighbors(my_row, my_col));
        System.out.println();
        return;
    }

    // print TwoDegreeNeighbors(0, (int)(ROWS/2))
    private static void test_case_4() {
        my_board = new slGoLBoardLive(ROWS, COLS);
        my_board.printGoLBoard();
        int my_row = 0, my_col = (int)(ROWS / 2);
        System.out.println("TwoDegreeNeighbors(" + my_row + ", " + my_col + ") --> "
                + my_board.countLiveTwoDegreeNeighbors(my_row, my_col));
        System.out.println();
        return;
    }

    // print the board and the updated board
    private static void test_case_5() {
        my_board = new slGoLBoardLive(ROWS, COLS);
        my_board.printGoLBoard();
        my_board.updateNextCellArray();
        System.out.println("----------\nLive cells in new board: " + my_board.updateNextCellArray());
        my_board.printGoLBoard();
        System.out.println();
        return;
    }
}