import it.vscalcione.sudoku.logic.computation.GameLogic;
import org.junit.Test;

public class GameGeneratorTest {

    @Test
    public void onGenerateNewPuzzle(){
        int[][] newPuzzle = GameLogic.getNewGame().getCopyOfGridState();
        int numberOfFilledSquares = 0;

        // Trasverse array
        for(int i = 0; i < 9; i++){
            for(int y = 0; y < 9; y++){
                if(newPuzzle[i][y] != 0){
                    numberOfFilledSquares++;
                }
            }
        }

        // Check of invalid set up
        assert(!GameLogic.rowsAreInvalid(newPuzzle));
        assert(!GameLogic.columnsAreInvalid(newPuzzle));
        assert(!GameLogic.squaresAreInvalid(newPuzzle));
        assert(numberOfFilledSquares == 81);
    }

    public void test100NewPuzzles(){
        for (int i = 0; i < 100; i++){
            int [][] newPuzzle = GameLogic.getNewGame().getCopyOfGridState();
            assert (!GameLogic.rowsAreInvalid(newPuzzle));
            assert (!GameLogic.columnsAreInvalid(newPuzzle));
            assert (!GameLogic.squaresAreInvalid(newPuzzle));
            assert (!GameLogic.tilesAreNotFilled(newPuzzle));
        }
    }
}
