package it.vscalcione.sudoku.ui.logic;

import it.vscalcione.sudoku.constants.GameState;
import it.vscalcione.sudoku.constants.Messages;
import it.vscalcione.sudoku.logic.computation.GameLogic;
import it.vscalcione.sudoku.problemdomain.IStorage;
import it.vscalcione.sudoku.problemdomain.SudokuGame;
import it.vscalcione.sudoku.ui.IUserInterfaceContract;

import java.io.IOException;

public class ControlLogic implements IUserInterfaceContract.EventListener {

    private IStorage storage;
    private IUserInterfaceContract.View view;

    public ControlLogic(IStorage storage, IUserInterfaceContract.View view){
        this.storage = storage;
        this.view = view;
    }

    /**
     * Use Case:
     * 1. Retrieve current "state" of the data from IStorage
     * 2. Update it according to the input
     * 3. Write the result to IStorage
     * @param x X coordinate of the selected input
     * @param y Y ...
     * @param input Which key was entered, One of:
     *  - Numbers 0-9
     *
     */
    @Override
    public void onSudokuInput(int x, int y, int input) {
        try {
            SudokuGame gameData = storage.getGameData();
            int[][] newGridState = gameData.getCopyOfGridState();
            newGridState[x][y] = input;
            gameData = new SudokuGame(GameLogic.checkForCompletion(newGridState), newGridState);
            storage.updateGameData(gameData);

            // either way, update the view
            view.updateSquare(x, y, input);

            // if game is complete, show dialog
            if (gameData.getGameState() == GameState.COMPLETE){
                view.showDialog(Messages.GAME_COMPLETE);
            }
        } catch (IOException exception) {
            exception.printStackTrace();
            view.showError(Messages.ERROR);
        }
    }

    @Override
    public void onDialogClick() {
        try {
            storage.updateGameData(GameLogic.getNewGame());
            view.updateBoard(storage.getGameData());
        } catch (IOException exception) {
            view.showError(Messages.ERROR);
        }
    }
}