import org.junit.jupiter.api.*;

import java.io.FileReader;
import java.math.BigInteger;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class TicTacToeTest {

    static final int FIRST_POSITION = 0;

    int[][] empty3x3;
    int[][] empty4x4;
    int[][] empty5x5;
    int[][] empty3x4;
    int[][] empty5x3;

    @BeforeEach
    void setUp() {
        empty3x3 = new int[][]{{0, 0, 0},
                {0, 0, 0},
                {0, 0, 0}};

        empty4x4 = new int[][]{{0, 0, 0, 0},
                {0, 0, 0, 0},
                {0, 0, 0, 0},
                {0, 0, 0, 0}};

        empty5x5 = new int[][]{{0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0}};

        empty3x4 = new int[][]{{0, 0, 0, 0},
                {0, 0, 0, 0},
                {0, 0, 0, 0}};

        empty5x3 = new int[][]{{0, 0, 0},
                {0, 0, 0},
                {0, 0, 0},
                {0, 0, 0},
                {0, 0, 0}};

    }

    // ***********************************************************************************************************************************

    // ******************** createBoard() tests ********************
    // Assume rows/columns are valid positive integers in inclusive range [3,5].


    // Test the creation of a 3x3 2d int array.
    @Test
    void test3x3() {
        assertArrayEquals(empty3x3, TicTacToe.createBoard(3, 3));
    }

    // Test the creation of a 4x4 2d int array.
    @Test
    void test4x4() {
        assertArrayEquals(empty4x4, TicTacToe.createBoard(4, 4));
        ;
    }

    // Test the creation of a 5x5 2d int array.
    @Test
    void test5x5() {
        assertArrayEquals(empty5x5, TicTacToe.createBoard(5, 5));
        ;
    }

    // Test the creation of a 3x4 2d int array.
    @Test
    void test3x4() {
        assertArrayEquals(empty3x4, TicTacToe.createBoard(3, 4));
    }

    // Test the creation of a 5x3 2d int array.
    @Test
    void test5x3() {
        assertArrayEquals(empty5x3, TicTacToe.createBoard(5, 3));
        ;
    }

    // ***********************************************************************************************************************************

    // ******************** rowsIn() tests ********************
    // Assume board is valid 2D int array.

    // Test that the correct number of rows is returned for a 3x3 2d int array.
    @Test
    void rows3x3() {
        assertEquals(3, TicTacToe.rowsIn(empty3x3));
    }

    // Test that the correct number of rows is returned for a 4x4 2d int array.
    @Test
    void rows4x4() {
        assertEquals(4, TicTacToe.rowsIn(empty4x4));
    }

    // Test that the correct number of rows is returned for a 5x5 2d int array.
    @Test
    void rows5x5() {
        assertEquals(5, TicTacToe.rowsIn(empty5x5));
    }

    // Test that the correct number of rows is returned for a 3x4 2d int array.
    @Test
    void rows3x4() {
        assertEquals(3, TicTacToe.rowsIn(empty3x4));
    }

    // Test that the correct number of rows is returned for a 5x3 2d int array.
    @Test
    void rows5x3() {
        assertEquals(5, TicTacToe.rowsIn(empty5x3));
    }

    // ***********************************************************************************************************************************

    // ******************** columnsIn() test ********************
    // Assume board is valid 2D int array.

    // Test that the correct number of columns is returned for a 3x3 2d int array.
    @Test
    void cols3x3() {
        assertEquals(3, TicTacToe.columnsIn(empty3x3));
    }

    // Test that the correct number of columns is returned for a 4x4 2d int array.
    @Test
    void cols4x4() {
        assertEquals(4, TicTacToe.columnsIn(empty4x4));
    }

    // Test that the correct number of columns is returned for a 5x5 2d int array.
    @Test
    void cols5x5() {
        assertEquals(5, TicTacToe.columnsIn(empty5x5));
    }

    // Test that the correct number of columns is returned for a 3x4 2d int array.
    @Test
    void cols3x4() {
        assertEquals(4, TicTacToe.columnsIn(empty3x4));
    }

    // Test that the correct number of columns is returned for a 5x3 2d int array.
    @Test
    void cols5x3() {
        assertEquals(3, TicTacToe.columnsIn(empty5x3));
    }

    // ***********************************************************************************************************************************

    // ******************** canPlay() tests ********************
    // Assume board is valid 2D int array and row/column are valid indices in the board.

    // Test if the function returns true when the piece in the top left corner of a 3x3 2d int array is 0.
    @Test
    void canPlay3x3Piece0TopLeft() {
        assertTrue(TicTacToe.canPlay(empty3x3, FIRST_POSITION, FIRST_POSITION));
    }

    // Test if the function returns false when the piece in the top right corner of a 4x4 2d int array is 1.
    @Test
    void canNotPlay4x4Piece1TopRight() {
        final int LAST_COL = TicTacToe.columnsIn(empty4x4) - 1;

        empty4x4[FIRST_POSITION][LAST_COL] = 1;
        assertFalse(TicTacToe.canPlay(empty4x4, FIRST_POSITION, LAST_COL));
    }

    // Test if the function returns false when the piece in the middle of a 5x5 2d int array is 2.
    @Test
    void canNotPlay5x5Piece2BottomLeft() {
        final int MIDDLE_ROW = TicTacToe.rowsIn(empty5x5) - 3;
        final int MIDDLE_COL = TicTacToe.columnsIn(empty5x5) - 3;

        empty5x5[MIDDLE_ROW][MIDDLE_COL] = 2;
        assertFalse(TicTacToe.canPlay(empty5x5, MIDDLE_ROW, MIDDLE_COL));
    }

    // Test if the function returns true when the piece in the bottom left corner of a 3x4 2d int array is 1.
    @Test
    void canPlay3x4Piece2() {
        final int LAST_ROW = TicTacToe.rowsIn(empty3x4) - 1;

        assertTrue(TicTacToe.canPlay(empty3x4, LAST_ROW, FIRST_POSITION));
    }

    // Test if the function returns false when the piece in the bottom right corner of a 5x3 2d int array is 2.
    @Test
    void canNotPlay5x3Piece0() {
        final int LAST_ROW = TicTacToe.rowsIn(empty5x3) - 1;
        final int LAST_COL = TicTacToe.columnsIn(empty5x3) - 1;

        empty5x3[LAST_ROW][LAST_COL] = 2;
        assertFalse(TicTacToe.canPlay(empty5x3, LAST_ROW, LAST_COL));
    }

    // ***********************************************************************************************************************************

    // ******************** play() tests ********************
    // Assume board is valid 2D int array, row/column are valid indices in the board, piece is
    // X==1/O==2. Assume location (row, column) is EMPTY in the board.

    // Test if the function plays piece 1 correctly in the top left corner of a 3x3 2d int array.
    @Test
    void play3x3Piece1TopLeft() {
        TicTacToe.play(empty3x3, FIRST_POSITION, FIRST_POSITION, 1);
        int playedPiece = empty3x3[FIRST_POSITION][FIRST_POSITION];

        assertEquals(1, playedPiece);
    }

    // Test if the function plays piece 2 correctly in the top right corner of a 4x4 2d int array.
    @Test
    void play4x4Piece2TopRight() {
        final int LAST_COL = TicTacToe.columnsIn(empty4x4) - 1;

        TicTacToe.play(empty4x4, FIRST_POSITION, LAST_COL, 2);
        int playedPiece = empty4x4[FIRST_POSITION][LAST_COL];

        assertEquals(2, playedPiece);
    }

    // Test if the function plays piece 1 correctly in the middle of a 5x5 2d int array.
    @Test
    void play5x5Piece1Middle() {
        final int MIDDLE_POSITION = TicTacToe.rowsIn(empty5x5) - 3;

        TicTacToe.play(empty5x5, MIDDLE_POSITION, MIDDLE_POSITION, 1);
        int playedPiece = empty5x5[MIDDLE_POSITION][MIDDLE_POSITION];

        assertEquals(1, playedPiece);
    }

    // Test if the function plays piece 1 correctly in the bottom left corner of a 5x3 2d int array.
    @Test
    void play5x3Piece1BottomLeft() {
        final int LAST_ROW = TicTacToe.rowsIn(empty5x3) - 1;

        TicTacToe.play(empty5x3, LAST_ROW, FIRST_POSITION, 1);
        int playedPiece = empty5x3[LAST_ROW][FIRST_POSITION];

        assertEquals(1, playedPiece);
    }

    // Test if the function plays piece 2 correctly in the bottom right corner of a 3x4 2d int array.
    @Test
    void play3x4Piece2BottomRight() {
        final int LAST_ROW = TicTacToe.rowsIn(empty3x4) - 1;
        final int LAST_COL = TicTacToe.columnsIn(empty3x4) - 1;

        TicTacToe.play(empty3x4, LAST_ROW, LAST_COL, 2);
        int playedPiece = empty3x4[LAST_ROW][LAST_COL];

        assertEquals(2, playedPiece);
    }

    // ***********************************************************************************************************************************

    // ******************** full() tests ********************
    // Assume board is valid 2D int array.

    // Test if the function correctly determines that a 3x3 2d int array is not full.
    @Test
    void notFull3x3() {
        assertFalse(TicTacToe.full(empty3x3));
    }

    // Test if the function correctly determines that a 4x4 2d int array is full.
    @Test
    void full4x4() {
        for (int i = 0; i < TicTacToe.rowsIn(empty4x4); i++) {
            for (int j = 0; j < TicTacToe.columnsIn(empty4x4); j++) {
                empty4x4[i][j] = 1;
            }
        }

        assertTrue(TicTacToe.full(empty4x4));
    }

    // Test if the function correctly determines that a 5x5 2d int array is not full.
    @Test
    void notFull5x5() {
        for (int i = 1; i < TicTacToe.rowsIn(empty5x5); i++) {
            for (int j = 1; j < TicTacToe.rowsIn(empty5x5); j++) {
                empty5x5[i][j] = 1;
            }
        }

        assertFalse(TicTacToe.full(empty5x5));
    }

    // Test if the function correctly determines that a 3x4 2d int array is not full.
    @Test
    void notFull3x4() {
        for (int i = 0; i < TicTacToe.rowsIn(empty3x4); i++) {
            for (int j = 0; j < TicTacToe.columnsIn(empty3x4); j++) {
                if (i % 2 != 0 && j % 2 == 0) {
                    empty3x4[i][j] = 2;
                }
            }
        }

        assertFalse(TicTacToe.full(empty3x4));
    }

    // Test if the function correctly determines that a 5x3 2d int array is full.
    @Test
    void full5x3() {
        for (int i = 0; i < TicTacToe.rowsIn(empty5x3); i++) {
            for (int j = 0; j < TicTacToe.columnsIn(empty5x3); j++) {
                if (i % 2 != 0 && j % 2 == 0) {
                    empty5x3[i][j] = 2;
                } else {
                    empty5x3[i][j] = 1;
                }
            }
        }

        assertTrue(TicTacToe.full(empty5x3));
    }

    // ***********************************************************************************************************************************

    // ******************** winInRow() tests ********************
    // Assume board is valid 2D int array, row is valid index in the board, piece is X==1/O==2.

    // Test if the function correctly determines that the first row wins the game for piece 1 in a 3x3 2d int array.
    @Test
    void firstRowWin3x3Piece1() {
        for (int i = 0; i < TicTacToe.columnsIn(empty3x3); i++) {
            empty3x3[FIRST_POSITION][i] = 1;
        }

        assertTrue(TicTacToe.winInRow(empty3x3, FIRST_POSITION, 1));
    }

    // Test if the function correctly determines that no row wins the game in a 4x4 2d int array.
    @Test
    void lastRowNoWIn4x4Piece2() {
        final int LAST_ROW = TicTacToe.rowsIn(empty4x4) - 1;

        for (int i = 0; i < TicTacToe.columnsIn(empty3x3); i++) {
            if (i != 2) {
                empty4x4[LAST_ROW][i] = 2;
            }
        }

        assertFalse(TicTacToe.winInRow(empty4x4, LAST_ROW, 2));
    }

    // Test if the function correctly determines that the second row wins the game for piece 3 in a 5x5 2d int array.
    @Test
    void secondRowWin5x5Piece2() {
        for (int i = 0; i < TicTacToe.columnsIn(empty5x5); i++) {
            if (i != 0 && i != 1) {
                empty5x5[1][i] = 1;
            }
        }
        assertTrue(TicTacToe.winInRow(empty5x5, 1, 1));
    }

    // Test if the function correctly determines that no row wins the game in an empty 3x4 2d int array.
    @Test
    void thirdRowNoWin3x4Piece2() {
        for (int i = 0; i < TicTacToe.columnsIn(empty3x4); i++) {
            if (i != 0 && i != 2) {
                empty3x4[2][i] = 1;
            }
        }

        assertFalse(TicTacToe.winInRow(empty3x4, 2, 2));
    }

    // Test if the function correctly determines that the last row wins the game for piece 1 piece in a 5x3 2d int array.
    @Test
    void lastRowWin5x3Piece1() {
        final int LAST_ROW = TicTacToe.rowsIn(empty5x3) - 1;

        for (int i = 0; i < TicTacToe.columnsIn(empty3x3); i++) {
            empty5x3[LAST_ROW][i] = 1;
        }
        assertTrue(TicTacToe.winInRow(empty5x3, LAST_ROW, 1));
    }

    // ***********************************************************************************************************************************

    // ******************** winInColumn() tests ********************
    // Assume board is valid 2D int array, column is valid index in the board, piece is X==1/O==2.

    // Test if the function correctly determines that the first column wins the game for piece 1 in a 3x3 2d int array.
    // The necessary entries in the array are set to 1.
    @Test
    void firstColWin3x3Piece1() {
        for (int i = 0; i < TicTacToe.columnsIn(empty3x3); i++) {
            empty3x3[i][FIRST_POSITION] = 1;
        }

        assertTrue(TicTacToe.winInColumn(empty3x3, FIRST_POSITION, 1));
    }

    // Test if the function correctly determines that no column wins the game in a 4x4 2d int array.
    // All entries in the last column but the one o the third row are set to 2 to try to trick the function.
    @Test
    void lastColNoWin4x4Piece2() {
        final int LAST_COl = TicTacToe.columnsIn(empty4x4) - 1;

        for (int i = 0; i < TicTacToe.columnsIn(empty4x4); i++) {
            if (i != 2) {
                empty4x4[i][LAST_COl] = 2;
            }
        }

        assertFalse(TicTacToe.winInColumn(empty4x4, LAST_COl, 2));
    }

    // Test if the function correctly determines that the second column wins the game for piece 3 in a 5x5 2d int array.
    // The necessary entries in the array are set to 2.
    @Test
    void secondColWin5x5Piece2() {
        for (int i = 0; i < TicTacToe.columnsIn(empty5x5); i++) {
            if (i != 0 && i != 1) {
                empty5x5[i][1] = 1;
            }
        }
        assertTrue(TicTacToe.winInColumn(empty5x5, 1, 1));
    }

    // Test if the function correctly determines that no column wins the game in a 3x4 2d int array.
    // The entry in the middle of the third column is set to 1 to try to trick the function.
    @Test
    void thirdColNoWin3x4Piece2() {
        for (int i = 0; i < TicTacToe.columnsIn(empty3x4) - 1; i++) {
            if (i != 0 && i != 2) {
                empty3x4[i][2] = 1;
            }
        }

        assertFalse(TicTacToe.winInColumn(empty3x4, 2, 2));
    }

    // Test if the function correctly determines that the last column wins the game for piece 1 in a 5x3 2d int array.
    // The necessary entries in the array are set to 1.
    @Test
    void lastColWin5x3Piece1() {
        final int LAST_COL = TicTacToe.columnsIn(empty5x3) - 1;

        for (int i = 0; i < TicTacToe.columnsIn(empty5x3); i++) {
            empty5x3[i][LAST_COL] = 1;

        }

        assertTrue(TicTacToe.winInColumn(empty5x3, LAST_COL, 1));
    }

    // ***********************************************************************************************************************************

    // ******************** winInDiagonalBS() tests ********************
    // Assume board is valid 2D int array, piece is X==1/O==2.

    // Test if the function correctly determines that the main diagonal wins the game for piece 1 in a 3x3 2d int array.
    // The necessary entries in the array are set to 1.
    @Test
    void diagonalBSWin3x3Piece1() {
        for (int i = 0; i < TicTacToe.rowsIn(empty3x3); i++) {
            for (int j = 0; j < TicTacToe.columnsIn(empty3x3); j++) {
                if (i == j) {
                    empty3x3[i][j] = 1;
                }
            }
        }

        assertTrue(TicTacToe.winInDiagonalBS(empty3x3, 1));
    }

    // Test if the function correctly determines that no diagonal wins the game in an empty 4x4 2d int array.
    // The empty4x4 2D array is left unchanged.
    @Test
    void diagonalBSNoWin4x4Piece2() {
        assertFalse(TicTacToe.winInDiagonalBS(empty4x4, 2));
    }

    // Test if the function correctly determines that the diagonal below the main one wins the game for piece 1 in a 5x5 2d int array.
    // The necessary entries in the array are set to 1.
    @Test
    void diagonalBSWin5x5Piece1() {
        empty5x5[0][2] = 1;
        empty5x5[1][3] = 1;
        empty5x5[2][4] = 1;

        assertTrue(TicTacToe.winInDiagonalBS(empty5x5, 1));
    }

    // Test if the function correctly determines that no diagonal wins the game in a 3x4 2d int array.
    // First and last row, and first and last column are set to 1 to try to trick the function.
    @Test
    void diagonalBSNoWin3x4Piece1() {
        final int LAST_ROW = TicTacToe.rowsIn(empty3x4) - 1;
        final int LAST_COl = TicTacToe.columnsIn(empty3x4) - 1;

        for (int i = 0; i <= LAST_ROW; i++) {
            for (int j = 0; j <= LAST_COl; j++) {
                if (i == 0 || i == LAST_ROW || j == 0 || j == LAST_COl) {
                    empty3x4[i][j] = 1;
                }
            }
        }

        assertFalse(TicTacToe.winInDiagonalBS(empty3x4, 1));
    }

    // Test if the function correctly determines that the diagonal above the main one wins the game for piece 2 in a 5x3 2d int array.
    // The necessary entries in the array are set to 2.
    @Test
    void diagonalBSWin5x3Piece2() {
        empty5x3[0][0] = 2;
        empty5x3[1][1] = 2;
        empty5x3[2][2] = 2;

        assertTrue(TicTacToe.winInDiagonalBS(empty5x3, 2));
    }

    // ***********************************************************************************************************************************

    // ******************** winInDiagonalFS() tests ********************
    // Assume board is valid 2D int array, piece is X==1/O==2.

    // Test if the function correctly determines that the main diagonal wins the game for piece 1 in a 3x3 2d int array.
    // The necessary entries in the array are set to 1.
    @Test
    void diagonalFSWin3x3Piece1() {
        empty3x3[0][2] = 1;
        empty3x3[1][1] = 1;
        empty3x3[2][0] = 1;

        assertTrue(TicTacToe.winInDiagonalFS(empty3x3, 1));
    }

    // Test if the function correctly determines that no diagonal wins the game in an empty 4x4 2d int array.
    // The empty4x4 2D array is left unchanged.
    @Test
    void diagonalFSNoWin4x4Piece2() {
        assertFalse(TicTacToe.winInDiagonalFS(empty4x4, 2));
    }

    // Test if the function correctly determines that the diagonal below the main one wins the game for piece 1 in a 5x5 2d int array.
    // The necessary entries in the array are set to 1.
    @Test
    void diagonalFSWin5x5Piece1() {
        empty5x5[2][4] = 1;
        empty5x5[3][3] = 1;
        empty5x5[4][2] = 1;

        assertTrue(TicTacToe.winInDiagonalFS(empty5x5, 1));
    }

    // Test if the function correctly determines that no diagonal wins the game in a 3x4 2d int array.
    // First and last row, and first and last column are set to 1 to try to trick the function.
    @Test
    void diagonalFSNoWin3x4Piece1() {
        final int LAST_ROW = TicTacToe.rowsIn(empty3x4) - 1;
        final int LAST_COl = TicTacToe.columnsIn(empty3x4) - 1;

        for (int i = 0; i <= LAST_ROW; i++) {
            for (int j = 0; j <= LAST_COl; j++) {
                if (i == 0 || i == LAST_ROW || j == 0 || j == LAST_COl) {
                    empty3x4[i][j] = 1;
                }
            }
        }
        System.out.println(Arrays.deepToString(empty3x4));
        assertFalse(TicTacToe.winInDiagonalFS(empty3x4, 1));
    }

    // Test if the function correctly determines that the diagonal above the main one wins the game for piece 2 in a 5x3 2d int array.
    // The necessary entries in the array are set to 2.
    @Test
    void diagonalFSWin5x3Piece2() {

    }

    // ***********************************************************************************************************************************

    // ******************** hint() tests ********************
    // Assume board is valid 2D int array, piece is X==1/O==2.

    // Test if the function correctly determines that a hint should be given so that piece 1 wins by completing the first row.
    // Hint should be given in the first row, second column of the 3x3 2d int array.
    @Test
    void rowHint3x3Piece1() {
        empty3x3[FIRST_POSITION][FIRST_POSITION] = 1;
        empty3x3[FIRST_POSITION][2] = 1;

        assertArrayEquals(new int[]{0, 1}, TicTacToe.hint(empty3x3, 1));
    }

    // Test if the function correctly determines that no hint should be given.
    // 4x4 2d int array.
    @Test
    void noHint4x4Piece2() {
        assertArrayEquals(new int[]{-1, -1}, TicTacToe.hint(empty4x4, 2));
    }

    // Test if the function correctly determines that a hint should be given so that piece 1 wins because of the last column.
    // Hint should be given in the third row, last column of the 5x5 2d int array.
    @Test
    void columnHint5x5Piece1() {
        final int LAST_COL = TicTacToe.columnsIn(empty5x5) - 1;

        empty5x5[1][LAST_COL] = 1;
        empty5x5[3][LAST_COL] = 1;

        assertArrayEquals(new int[]{2, LAST_COL}, TicTacToe.hint(empty5x5, 1));
    }

    // Test if the function correctly determines that a hint should be given so that piece 1 wins by a BS diagonal.
    // Hint should be given in the second row, third column of the 3x4 2d int array.
    @Test
    void diagonalBSHint3x4Piece1() {
        empty3x4[0][1] = 1;
        empty3x4[2][3] = 1;

        assertArrayEquals(new int[]{1, 2}, TicTacToe.hint(empty3x4, 1));
    }

    // Test if the function correctly determines that a hint should be given so that piece 2 wins by FS diagonal.
    // Hint should be given in the fourth row, first column of the 5x3 2d int array.
    @Test
    void diagonalFSHint5x3Piece2() {
        empty5x3[3][1] = 2;
        empty5x3[2][2] = 2;

        assertArrayEquals(new int[]{4, 0}, TicTacToe.hint(empty5x3, 2));
    }

    // ***********************************************************************************************************************************

    // ******************** factorial() ********************

    // Test if the function correctly returns the factorial of 0.
    // The function should return the number stored in factorialResult.
    @Test
    void factorial_0() {
        BigInteger factorialResult = new BigInteger("1");

        assertEquals(factorialResult, TicTacToe.factorial(0));
    }

    // Test if the function correctly returns the factorial of 1.
    // The function should return the number stored in factorialResult.
    @Test
    void factorial_5() {
        BigInteger factorialResult = new BigInteger("120");

        assertEquals(factorialResult, TicTacToe.factorial(5));
    }

    // Test if the function correctly returns the factorial of 10.
    // The function should return the number stored in factorialResult.
    @Test
    void factorial_10() {
        BigInteger factorialResult = new BigInteger("3628800");

        assertEquals(factorialResult, TicTacToe.factorial(10));
    }

    // Test if the function correctly returns the factorial of 20.
    // The function should return the number stored in factorialResult.
    @Test
    void factorial_20() {
        BigInteger factorialResult = new BigInteger("2432902008176640000");

        assertEquals(factorialResult, TicTacToe.factorial(20));
    }

    // Test if the function correctly returns the factorial of 27.
    // The function should return the number stored in factorialResult.
    @Test
    void factorial_27() {
        BigInteger factorialResult = new BigInteger("10888869450418352160768000000");

        assertEquals(factorialResult, TicTacToe.factorial(27));
    }

}