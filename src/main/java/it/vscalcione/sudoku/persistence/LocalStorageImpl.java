package it.vscalcione.sudoku.persistence;

import it.vscalcione.sudoku.problemdomain.SudokuGame;
import it.vscalcione.sudoku.problemdomain.IStorage;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.util.Arrays;
import java.util.Collections;

public class LocalStorageImpl implements IStorage {

    private static File GAME_DATA = new File(System.getProperty("user.home"), "gamedata.txt");

    @Override
    public void updateGameData(SudokuGame game) throws IOException {
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(GAME_DATA);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(game);
            objectOutputStream.close();
        } catch (IOException e) {
            throw new IOException("Unable to access Game Data");
        }
    }

    @Override
    public SudokuGame getGameData() throws IOException {

        FileInputStream fileInputStream = new FileInputStream(GAME_DATA);
        ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
        try {
            SudokuGame gameState = (SudokuGame) objectInputStream.readObject();
            objectInputStream.close();
            return gameState;
        } catch (ClassNotFoundException e) {
            throw new IOException("File Not Found");
        }
    }
}
