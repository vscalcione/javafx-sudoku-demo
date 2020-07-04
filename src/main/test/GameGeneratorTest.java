import it.vscalcione.sudoku.logic.computation.GameLogic;
import org.junit.jupiter.api.Test;

public class GameGeneratorTest {

    @Test
    public void onGenerateNewPuzzle() {
        int[][] newPuzzle = GameLogic.getNewGame().getCopyOfGridState();
        int numberOfFilledSquares = 0;
        for (int xIndex = 0; xIndex < 9; xIndex++){
            for (int yIndex = 0; yIndex < 9; yIndex++ ){
                if (newPuzzle[xIndex][yIndex] != 0)
                    numberOfFilledSquares++;
            }
        }
        assert (!GameLogic.rowsAreInvalid(newPuzzle));
        assert (!GameLogic.columnsAreInvalid(newPuzzle));
        assert (!GameLogic.squaresAreInvalid(newPuzzle));
        assert (numberOfFilledSquares == 81);
    }

    @Test
    public void test100NewPuzzles(){
        for (int testIndex = 0; testIndex < 100; testIndex++){
            int[][] newPuzzle = GameLogic.getNewGame().getCopyOfGridState();
            assert (!GameLogic.rowsAreInvalid(newPuzzle));
            assert (!GameLogic.columnsAreInvalid(newPuzzle));
            assert (!GameLogic.squaresAreInvalid(newPuzzle));
            assert (!GameLogic.tilesAreNotFilled(newPuzzle));
        }
    }
}