package it.vscalcione.sudoku;

import it.vscalcione.sudoku.logic.build.SudokuBuildLogic;
import it.vscalcione.sudoku.ui.IUserInterfaceContract;
import it.vscalcione.sudoku.ui.UserInterfaceImpl;
import javafx.application.Application;
import javafx.stage.Stage;

import java.io.IOException;

public class SudokuApplication extends Application {

    private IUserInterfaceContract.View uiImpl;

    @Override
    public void start(Stage primaryStage) throws IOException {

        // Get SudokuGame object for a new game
        uiImpl = new UserInterfaceImpl(primaryStage);
        try {
            SudokuBuildLogic.build(uiImpl);
        } catch (IOException exception) {
            exception.printStackTrace();
            throw exception;
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
