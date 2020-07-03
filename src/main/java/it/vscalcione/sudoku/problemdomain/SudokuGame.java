package it.vscalcione.sudoku.problemdomain;

import it.vscalcione.sudoku.costants.GameState;

import java.io.Serializable;

public class SudokuGame implements Serializable {

    private final GameState gameState;
    private final int[][] gridState;
    public static final int GRID_BOUNDARY = 9;

    public SudokuGame(GameState gameState, int[][] gridState) {
        this.gameState = gameState;
        this.gridState = gridState;
    }

    public GameState getGameState() {
        return gameState;
    }

    public int[][] getCopyOfGrdiState() {
        return SudokuUtilies.copyToNewArray(gridState);
    }
}
