package it.vscalcione.sudoku.logic.build;

import it.vscalcione.sudoku.logic.computation.GameLogic;
import it.vscalcione.sudoku.persistence.LocalStorageImpl;
import it.vscalcione.sudoku.problemdomain.IStorage;
import it.vscalcione.sudoku.problemdomain.SudokuGame;
import it.vscalcione.sudoku.ui.IUserInterfaceContract;
import it.vscalcione.sudoku.ui.logic.ControlLogic;

import java.io.IOException;

public class SudokuBuildLogic {

    public static void build(IUserInterfaceContract.View userInterface) throws IOException {
        SudokuGame initialState;
        IStorage storage = new LocalStorageImpl();

        try {
            initialState = storage.getGameData();
        } catch (IOException e) {
            initialState = GameLogic.getNewGame();
            storage.updateGameData(initialState);
        }

        IUserInterfaceContract.EventListener uiLogic = new ControlLogic(storage, userInterface);
        userInterface.setListener(uiLogic);
        userInterface.updateBoard(initialState);
    }
}
