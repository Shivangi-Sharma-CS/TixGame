/**
 * This class indexes the board positions starting from 0 to V - 1 where V is the size of the board
 */
public class BoardPositionHash {

    private int position;
    private int[][] board;
    private int rowSize;
    private int colSize;

    /**
     * Constructs the hash for a board given a row size and column size
     * @param rowSize the size of the row
     * @param colSize the size of the column
     */
    public BoardPositionHash(int rowSize, int colSize) {
        position = -199;
        this.rowSize = rowSize;
        this.colSize = colSize;
        this.board = new int[rowSize][colSize];
        indexingBoard();
    }

    /**
     * Helper method that indexes the board positions from 0 to V-1 where V is the size of the board
     * @return the cache value of the index of the board if calculated
     */
    private int indexingBoard() {
        int p = position;
        if(p != -199) return p;
        int i = 0;
        for(int r = 0; r < rowSize; r++) {
            for(int c=0; c < colSize; c++) {
                board[r][c] = i;
                p = board[r][c];
                i++;
            }
        }
        position = p;
        return p;
    }

    /**
     * Returns the index on the board given a row and column of the rowSize * colSize board
     * @param row the row of the board
     * @param col the column of the board
     * @return the integer index for the board position
     */
    public int getIndex(int row, int col) {
        return board[row][col];
    }

}
